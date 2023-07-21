/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.data.partitioning.sql.builder.exporter;

import javax.sql.DataSource;

/**
 * @author Manuel de la Peña
 */
public interface DBProvider {

	public DataSource getDataSource();

	public int getFetchSize();

	public String getTableNameFieldName();

}