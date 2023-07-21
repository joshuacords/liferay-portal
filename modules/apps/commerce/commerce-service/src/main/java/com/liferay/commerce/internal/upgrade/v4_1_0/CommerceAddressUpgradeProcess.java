/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.internal.upgrade.v4_1_0;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.constants.CommerceAddressConstants;
import com.liferay.commerce.internal.upgrade.base.BaseCommerceServiceUpgradeProcess;
import com.liferay.commerce.model.impl.CommerceAddressImpl;
import com.liferay.commerce.model.impl.CommerceAddressModelImpl;
import com.liferay.portal.dao.orm.common.SQLTransformer;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Alec Sloan
 */
public class CommerceAddressUpgradeProcess
	extends BaseCommerceServiceUpgradeProcess {

	public CommerceAddressUpgradeProcess(
		ClassNameLocalService classNameLocalService) {

		_classNameLocalService = classNameLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (!hasColumn("CommerceAccount", "defaultBillingAddressId") ||
			!hasColumn("CommerceAccount", "defaultShippingAddressId")) {

			throw new UpgradeException(
				"Upgrade fails as CommerceAccount does not have both " +
					"columns: 'defaultBillingAddressId' and" +
						"'defaultShippingAddressId'");
		}

		addColumn(
			CommerceAddressImpl.class, CommerceAddressImpl.TABLE_NAME, "type_",
			"INTEGER");

		PreparedStatement ps = null;

		if (hasColumn(CommerceAddressModelImpl.TABLE_NAME, "defaultBilling")) {
			ps = connection.prepareStatement(
				"update CommerceAccount set defaultBillingAddressId = ? " +
					"where commerceAccountId = ?");

			updateCommerceAccountAndSetType(
				ps, getCommerceAddressResultSet("defaultBilling"));
		}

		if (hasColumn(CommerceAddressModelImpl.TABLE_NAME, "defaultShipping")) {
			ps = connection.prepareStatement(
				"update CommerceAccount set defaultShippingAddressId = ? " +
					"where commerceAccountId = ?");

			updateCommerceAccountAndSetType(
				ps, getCommerceAddressResultSet("defaultShipping"));
		}
	}

	protected ResultSet getCommerceAddressResultSet(String type)
		throws Exception {

		long commerceAccountClassNameId = _classNameLocalService.getClassNameId(
			CommerceAccount.class);

		PreparedStatement ps = null;

		if (type.equals("defaultBilling")) {
			ps = connection.prepareStatement(
				SQLTransformer.transform(
					"select commerceAddressId, classPK, defaultBilling, " +
						"defaultShipping from CommerceAddress where " +
							"classNameId = ? and defaultBilling = [$TRUE$]"));
		}
		else {
			ps = connection.prepareStatement(
				SQLTransformer.transform(
					"select commerceAddressId, classPK, defaultBilling, " +
						"defaultShipping from CommerceAddress where " +
							"classNameId = ? and defaultShipping = [$TRUE$]"));
		}

		ps.setLong(1, commerceAccountClassNameId);

		return ps.executeQuery();
	}

	protected void setType(
			boolean defaultBilling, boolean defaultShipping,
			long commerceAddressId)
		throws Exception {

		PreparedStatement ps = connection.prepareStatement(
			"update CommerceAddress set type_ = ? where commerceAddressId = ?");

		int type = CommerceAddressConstants.ADDRESS_TYPE_SHIPPING;

		if (defaultBilling && !defaultShipping) {
			type = CommerceAddressConstants.ADDRESS_TYPE_BILLING;
		}
		else if (!defaultBilling && defaultShipping) {
			type = CommerceAddressConstants.ADDRESS_TYPE_SHIPPING;
		}

		ps.setInt(1, type);
		ps.setLong(2, commerceAddressId);

		ps.addBatch();
	}

	protected void updateCommerceAccountAndSetType(
			PreparedStatement ps, ResultSet rs)
		throws Exception {

		while (rs.next()) {
			long commerceAddressId = rs.getLong("commerceAddressId");

			ps.setLong(1, commerceAddressId);

			ps.setLong(2, rs.getLong("classPK"));

			setType(
				rs.getBoolean("defaultBilling"),
				rs.getBoolean("defaultShipping"), commerceAddressId);

			ps.addBatch();
		}
	}

	private final ClassNameLocalService _classNameLocalService;

}