/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.data.partitioning.sql.builder.internal.exporter;

/**
 * @author Manuel de la Peña
 */
public interface SQLBuilder {

	public String buildField(Object object);

	public String buildInsert(String tableName, String[] fields);

}