/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.internal.upgrade.v1_1_0;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Rodrigo Guedes de Souza
 */
public class BaseCommerceOrderUpgradeProcess extends UpgradeProcess {

	public BaseCommerceOrderUpgradeProcess(
		Class entityClass, String tableName, String columnName,
		String columnType) {

		_entityClass = entityClass;
		_tableName = tableName;
		_columnName = columnName;
		_columnType = columnType;
	}

	@Override
	protected void doUpgrade() throws Exception {
		_addColumn();
	}

	private void _addColumn() throws Exception {
		if (_log.isInfoEnabled()) {
			_log.info(
				String.format(
					"Adding column %s to table %s", _columnName, _tableName));
		}

		if (!hasColumn(_tableName, _columnName)) {
			alter(
				_entityClass,
				new AlterTableAddColumn(
					_columnName + StringPool.SPACE + _columnType));
		}
		else {
			if (_log.isInfoEnabled()) {
				_log.info(
					String.format(
						"Column %s already exists on table %s", _columnName,
						_tableName));
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		BaseCommerceOrderUpgradeProcess.class);

	private final String _columnName;
	private final String _columnType;
	private final Class _entityClass;
	private final String _tableName;

}