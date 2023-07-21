/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.dao.jdbc.pool.metrics;

import org.apache.commons.dbcp.BasicDataSource;

/**
 * @author Mladen Cikara
 */
public class DBCPConnectionPoolMetrics extends BaseConnectionPoolMetrics {

	public DBCPConnectionPoolMetrics(BasicDataSource basicDataSource) {
		_basicDataSource = basicDataSource;
	}

	@Override
	public int getNumActive() {
		return _basicDataSource.getNumActive();
	}

	@Override
	public int getNumIdle() {
		return _basicDataSource.getNumIdle();
	}

	@Override
	protected Object getDataSource() {
		return _basicDataSource;
	}

	@Override
	protected String getPoolName() {
		return _basicDataSource.toString();
	}

	private final BasicDataSource _basicDataSource;

}