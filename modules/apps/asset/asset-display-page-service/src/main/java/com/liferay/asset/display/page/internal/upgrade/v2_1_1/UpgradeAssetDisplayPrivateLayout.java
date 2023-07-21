/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.display.page.internal.upgrade.v2_1_1;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.ResourceLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Pavel Savinov
 * @author Roberto Díaz
 */
public class UpgradeAssetDisplayPrivateLayout extends UpgradeProcess {

	public UpgradeAssetDisplayPrivateLayout(
		LayoutLocalService layoutLocalService,
		ResourceLocalService resourceLocalService) {

		_layoutLocalService = layoutLocalService;
		_resourceLocalService = resourceLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		_upgradeAssetDisplayLayouts();
	}

	private void _addResources(long groupId, long plid) throws PortalException {
		Layout layout = _layoutLocalService.fetchLayout(plid);

		_resourceLocalService.addResources(
			layout.getCompanyId(), groupId, layout.getUserId(),
			Layout.class.getName(), layout.getPlid(), false, true, true);
	}

	private String _getFriendlyURL(
			PreparedStatement ps, long groupId, String friendlyURL)
		throws SQLException {

		String initialFriendlyURL = friendlyURL;

		ps.setLong(1, groupId);
		ps.setBoolean(2, false);
		ps.setString(3, friendlyURL);

		for (int i = 1;; i++) {
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					friendlyURL = initialFriendlyURL + StringPool.DASH + i;

					ps.setString(3, friendlyURL);
				}
				else {
					break;
				}
			}
		}

		return friendlyURL;
	}

	private void _upgradeAssetDisplayLayouts() throws Exception {
		try (PreparedStatement ps1 = connection.prepareStatement(
				"select groupId, friendlyURL, plid from  Layout where " +
					"privateLayout = ? and type_ = ?");
			PreparedStatement ps2 = connection.prepareStatement(
				"select plid from Layout where groupId = ? and privateLayout " +
					"= ? and friendlyURL = ?");
			PreparedStatement ps3 = AutoBatchPreparedStatementUtil.autoBatch(
				connection.prepareStatement(
					"update Layout set privateLayout = ?, layoutId = ?, " +
						"friendlyURL = ? where plid = ?"));
			PreparedStatement ps4 = AutoBatchPreparedStatementUtil.autoBatch(
				connection.prepareStatement(
					"update LayoutFriendlyURL set privateLayout = ?, " +
						"friendlyURL = ? where plid = ?"))) {

			ps1.setBoolean(1, true);
			ps1.setString(2, LayoutConstants.TYPE_ASSET_DISPLAY);

			try (ResultSet rs = ps1.executeQuery()) {
				while (rs.next()) {
					long groupId = rs.getLong("groupId");
					String friendlyURL = rs.getString("friendlyURL");
					long plid = rs.getLong("plid");

					_addResources(groupId, plid);

					String newFriendlyURL = _getFriendlyURL(
						ps2, groupId, friendlyURL);

					if (!newFriendlyURL.equals(friendlyURL)) {
						if (_log.isWarnEnabled()) {
							_log.warn(
								StringBundler.concat(
									"Friendly URL for PLID ", plid,
									" in group ", groupId, " changed from ",
									friendlyURL, " to ", newFriendlyURL));
						}
					}

					ps3.setBoolean(1, false);
					ps3.setLong(
						2, _layoutLocalService.getNextLayoutId(groupId, false));
					ps3.setString(3, newFriendlyURL);
					ps3.setLong(4, plid);

					ps3.addBatch();

					ps4.setBoolean(1, false);
					ps4.setString(2, newFriendlyURL);
					ps4.setLong(3, plid);

					ps4.addBatch();
				}

				ps3.executeBatch();

				ps4.executeBatch();
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UpgradeAssetDisplayPrivateLayout.class);

	private final LayoutLocalService _layoutLocalService;
	private final ResourceLocalService _resourceLocalService;

}