/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.inventory.internal.upgrade.v2_0_0;

import com.liferay.commerce.inventory.model.impl.CommerceInventoryAuditModelImpl;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceInventoryAuditUpgradeProcess extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		_addColumn(
			CommerceInventoryAuditModelImpl.class,
			CommerceInventoryAuditModelImpl.TABLE_NAME, "logType",
			"VARCHAR(75)");
		_addColumn(
			CommerceInventoryAuditModelImpl.class,
			CommerceInventoryAuditModelImpl.TABLE_NAME, "logTypeSettings",
			"TEXT");

		if (hasColumn(
				CommerceInventoryAuditModelImpl.TABLE_NAME, "description")) {

			runSQL("delete from CIAudit");

			runSQL("alter table CIAudit drop column description");
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
		CommerceInventoryAuditUpgradeProcess.class);

}