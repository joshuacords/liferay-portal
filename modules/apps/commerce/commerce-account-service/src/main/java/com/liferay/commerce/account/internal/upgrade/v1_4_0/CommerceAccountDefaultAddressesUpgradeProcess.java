/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.account.internal.upgrade.v1_4_0;

import com.liferay.commerce.account.model.impl.CommerceAccountImpl;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Alec Sloan
 */
public class CommerceAccountDefaultAddressesUpgradeProcess
	extends UpgradeProcess {

	protected void addColumn(
			Class<?> entityClass, String tableName, String columnName,
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
				new UpgradeProcess.AlterTableAddColumn(
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

	protected void doUpgrade() throws Exception {
		addColumn(
			CommerceAccountImpl.class, CommerceAccountImpl.TABLE_NAME,
			"defaultBillingAddressId", "LONG");
		addColumn(
			CommerceAccountImpl.class, CommerceAccountImpl.TABLE_NAME,
			"defaultShippingAddressId", "LONG");
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceAccountDefaultAddressesUpgradeProcess.class);

}