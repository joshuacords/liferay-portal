/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.nested.portlets.web.internal.upgrade.v1_0_0;

import com.liferay.nested.portlets.web.internal.constants.NestedPortletsPortletKeys;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.BaseUpgradePortletId;
import com.liferay.portal.kernel.util.StringUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Jürgen Kappler
 */
public class UpgradePortletId extends BaseUpgradePortletId {

	@Override
	protected void doUpgrade() throws Exception {
		super.doUpgrade();

		updateNestedPortletLayoutRevisionTypeSettings();
		updateNestedPortletLayoutTypeSettings();
	}

	@Override
	protected String[][] getRenamePortletIdsArray() {
		return new String[][] {
			{"118", NestedPortletsPortletKeys.NESTED_PORTLETS}
		};
	}

	protected void updateNestedPortletLayoutRevisionTypeSettings()
		throws Exception {

		try (PreparedStatement ps = connection.prepareStatement(
				"select layoutRevisionId, typeSettings from LayoutRevision " +
					"where typeSettings LIKE '%nested-column-ids%'");
			ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				long layoutRevisionId = rs.getLong("layoutRevisionId");
				String typeSettings = rs.getString("typeSettings");

				String oldPortletId = "_118_INSTANCE_";
				String newPortletId =
					"_" + NestedPortletsPortletKeys.NESTED_PORTLETS +
						"_INSTANCE_";

				String newTypeSettings = StringUtil.replace(
					typeSettings, oldPortletId, newPortletId);

				updateLayoutRevision(layoutRevisionId, newTypeSettings);
			}
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn(exception, exception);
			}
		}
	}

	protected void updateNestedPortletLayoutTypeSettings() throws Exception {
		try (PreparedStatement ps = connection.prepareStatement(
				"select plid, typeSettings from Layout where typeSettings " +
					"LIKE '%nested-column-ids%'");

			ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				long plid = rs.getLong("plid");
				String typeSettings = rs.getString("typeSettings");

				String oldPortletId = "_118_INSTANCE_";
				String newPortletId =
					"_" + NestedPortletsPortletKeys.NESTED_PORTLETS +
						"_INSTANCE_";

				String newTypeSettings = StringUtil.replace(
					typeSettings, oldPortletId, newPortletId);

				updateLayout(plid, newTypeSettings);
			}
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn(exception, exception);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UpgradePortletId.class);

}