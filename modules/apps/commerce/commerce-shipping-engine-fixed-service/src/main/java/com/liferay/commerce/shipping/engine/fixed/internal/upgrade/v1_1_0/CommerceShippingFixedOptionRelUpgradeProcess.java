/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.shipping.engine.fixed.internal.upgrade.v1_1_0;

import com.liferay.commerce.shipping.engine.fixed.model.impl.CommerceShippingFixedOptionRelImpl;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceShippingFixedOptionRelUpgradeProcess
	extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		if (_log.isInfoEnabled()) {
			_log.info(
				String.format(
					"Renaming column %s to table %s", "commerceWarehouseId",
					CommerceShippingFixedOptionRelImpl.TABLE_NAME));
		}

		if (!hasColumn(
				CommerceShippingFixedOptionRelImpl.TABLE_NAME,
				"commerceInventoryWarehouseId")) {

			alter(
				CommerceShippingFixedOptionRelImpl.class,
				new AlterColumnName(
					"commerceWarehouseId",
					"commerceInventoryWarehouseId LONG"));
		}
		else {
			if (_log.isInfoEnabled()) {
				_log.info(
					String.format(
						"Column %s already exists on table %s",
						"commerceInventoryWarehouseId",
						CommerceShippingFixedOptionRelImpl.TABLE_NAME));
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceShippingFixedOptionRelUpgradeProcess.class);

}