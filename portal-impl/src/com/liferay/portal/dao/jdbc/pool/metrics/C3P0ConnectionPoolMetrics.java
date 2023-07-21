/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.dao.jdbc.pool.metrics;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import com.mchange.v2.c3p0.impl.AbstractPoolBackedDataSource;

import java.sql.SQLException;

/**
 * @author Mladen Cikara
 */
public class C3P0ConnectionPoolMetrics extends BaseConnectionPoolMetrics {

	public C3P0ConnectionPoolMetrics(
		AbstractPoolBackedDataSource abstractPoolBackedDataSource) {

		_abstractPoolBackedDataSource = abstractPoolBackedDataSource;
	}

	@Override
	public int getNumActive() {
		try {
			return _abstractPoolBackedDataSource.getNumBusyConnections();
		}
		catch (SQLException sqlException) {
			if (_log.isDebugEnabled()) {
				_log.debug(sqlException.getMessage());
			}

			return -1;
		}
	}

	@Override
	public int getNumIdle() {
		try {
			return _abstractPoolBackedDataSource.getNumIdleConnections();
		}
		catch (SQLException sqlException) {
			if (_log.isDebugEnabled()) {
				_log.debug(sqlException.getMessage());
			}

			return -1;
		}
	}

	@Override
	protected Object getDataSource() {
		return _abstractPoolBackedDataSource;
	}

	@Override
	protected String getPoolName() {
		return _abstractPoolBackedDataSource.getDataSourceName();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		C3P0ConnectionPoolMetrics.class);

	private final AbstractPoolBackedDataSource _abstractPoolBackedDataSource;

}