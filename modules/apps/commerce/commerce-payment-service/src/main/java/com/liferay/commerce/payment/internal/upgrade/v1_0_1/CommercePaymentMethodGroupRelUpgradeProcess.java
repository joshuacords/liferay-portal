/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.payment.internal.upgrade.v1_0_1;

import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Marco Leo
 */
public class CommercePaymentMethodGroupRelUpgradeProcess
	extends UpgradeProcess {

	public CommercePaymentMethodGroupRelUpgradeProcess(
		ClassNameLocalService classNameLocalService,
		GroupLocalService groupLocalService) {

		_classNameLocalService = classNameLocalService;
		_groupLocalService = groupLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		try (Statement s = connection.createStatement();

			ResultSet rs = s.executeQuery(
				"select CPaymentMethodGroupRelId, groupId from " +
					"CommercePaymentMethodGroupRel")) {

			PreparedStatement ps = null;

			while (rs.next()) {
				long cPaymentMethodGroupRelId = rs.getLong(
					"CPaymentMethodGroupRelId");

				long groupId = rs.getLong("groupId");

				long channelGroupId = _getCommerceChannelGroupIdBySiteGroupId(
					groupId);

				if (channelGroupId == 0) {
					continue;
				}

				ps = connection.prepareStatement(
					"update CommercePaymentMethodGroupRel set groupId = ? " +
						"where CPaymentMethodGroupRelId = ?");

				ps.setLong(1, channelGroupId);
				ps.setLong(2, cPaymentMethodGroupRelId);

				ps.executeUpdate();
			}
		}
	}

	private long _getCommerceChannelGroupIdBySiteGroupId(long groupId)
		throws SQLException {

		long companyId = 0;
		long commerceChannelId = 0;

		String sql =
			"select * from CommerceChannel where siteGroupId = " + groupId;

		try (Statement s = connection.createStatement()) {
			s.setMaxRows(1);

			try (ResultSet rs = s.executeQuery(sql)) {
				if (rs.next()) {
					companyId = rs.getLong("companyId");
					commerceChannelId = rs.getLong("commerceChannelId");
				}
			}
		}

		long classNameId = _classNameLocalService.getClassNameId(
			CommerceChannel.class.getName());

		Group group = _groupLocalService.fetchGroup(
			companyId, classNameId, commerceChannelId);

		if (group != null) {
			return group.getGroupId();
		}

		return 0;
	}

	private final ClassNameLocalService _classNameLocalService;
	private final GroupLocalService _groupLocalService;

}