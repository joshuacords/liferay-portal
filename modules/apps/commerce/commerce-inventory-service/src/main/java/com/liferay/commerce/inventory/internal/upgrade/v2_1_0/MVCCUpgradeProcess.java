/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.inventory.internal.upgrade.v2_1_0;

import com.liferay.commerce.inventory.model.impl.CommerceInventoryBookedQuantityImpl;
import com.liferay.commerce.inventory.model.impl.CommerceInventoryReplenishmentItemImpl;
import com.liferay.commerce.inventory.model.impl.CommerceInventoryWarehouseImpl;
import com.liferay.commerce.inventory.model.impl.CommerceInventoryWarehouseItemImpl;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Luca Pellizzon
 */
public class MVCCUpgradeProcess extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		if (hasTable(CommerceInventoryBookedQuantityImpl.TABLE_NAME)) {
			_addColumn(
				CommerceInventoryBookedQuantityImpl.class,
				CommerceInventoryBookedQuantityImpl.TABLE_NAME, "mvccVersion",
				"LONG default 0 not null");
		}

		if (hasTable(CommerceInventoryReplenishmentItemImpl.TABLE_NAME)) {
			_addColumn(
				CommerceInventoryReplenishmentItemImpl.class,
				CommerceInventoryReplenishmentItemImpl.TABLE_NAME,
				"mvccVersion", "LONG default 0 not null");
		}

		if (hasTable(CommerceInventoryWarehouseImpl.TABLE_NAME)) {
			_addColumn(
				CommerceInventoryWarehouseImpl.class,
				CommerceInventoryWarehouseImpl.TABLE_NAME, "mvccVersion",
				"LONG default 0 not null");
		}

		if (hasTable(CommerceInventoryWarehouseItemImpl.TABLE_NAME)) {
			_addColumn(
				CommerceInventoryWarehouseItemImpl.class,
				CommerceInventoryWarehouseItemImpl.TABLE_NAME, "mvccVersion",
				"LONG default 0 not null");
		}
	}

	private void _addColumn(
			Class entityClass, String tableName, String columnName,
			String columnType)
		throws Exception {

		if (_log.isInfoEnabled()) {
			_log.info(
				String.format(
					"Adding column %s to table %s", columnName, tableName));
		}

		if (!hasColumn(tableName, columnName)) {
			alter(
				entityClass,
				new AlterTableAddColumn(
					columnName + StringPool.SPACE + columnType));
		}
		else {
			if (_log.isInfoEnabled()) {
				_log.info(
					String.format(
						"Column %s already exists on table %s", columnName,
						tableName));
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		MVCCUpgradeProcess.class);

}