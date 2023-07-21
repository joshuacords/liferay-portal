/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.dao.jdbc.pool.metrics;

import com.zaxxer.hikari.HikariPoolMXBean;

import java.lang.reflect.Method;

/**
 * @author Mladen Cikara
 */
public class HikariConnectionPoolMetrics extends BaseConnectionPoolMetrics {

	public HikariConnectionPoolMetrics(Object dataSource)
		throws ReflectiveOperationException {

		_dataSource = dataSource;

		Class<?> clazz = dataSource.getClass();

		_getPoolNameMethod = clazz.getMethod("getPoolName");

		_getHikariPoolMXBeanMethod = clazz.getMethod("getHikariPoolMXBean");
	}

	@Override
	public int getNumActive() {
		try {
			HikariPoolMXBean hikariPoolMXBean =
				(HikariPoolMXBean)_getHikariPoolMXBeanMethod.invoke(
					_dataSource);

			return hikariPoolMXBean.getActiveConnections();
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	public int getNumIdle() {
		try {
			HikariPoolMXBean hikariPoolMXBean =
				(HikariPoolMXBean)_getHikariPoolMXBeanMethod.invoke(
					_dataSource);

			return hikariPoolMXBean.getIdleConnections();
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	protected Object getDataSource() {
		return _dataSource;
	}

	@Override
	protected String getPoolName() {
		try {
			return (String)_getPoolNameMethod.invoke(_dataSource);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	private final Object _dataSource;
	private final Method _getHikariPoolMXBeanMethod;
	private final Method _getPoolNameMethod;

}