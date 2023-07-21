/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.data.partitioning.sql.builder.exporter;

import com.liferay.portal.tools.data.partitioning.sql.builder.exporter.serializer.FieldSerializer;
import com.liferay.portal.tools.data.partitioning.sql.builder.internal.exporter.SQLBuilder;
import com.liferay.portal.tools.data.partitioning.sql.builder.internal.serializer.DefaultFieldSerializer;

/**
 * @author Manuel de la Peña
 */
public class InsertSQLBuilder implements SQLBuilder {

	public InsertSQLBuilder() {
		_fieldSerializer = new DefaultFieldSerializer();
	}

	public InsertSQLBuilder(FieldSerializer fieldSerializer) {
		_fieldSerializer = fieldSerializer;
	}

	@Override
	public String buildField(Object object) {
		return _fieldSerializer.serialize(object);
	}

	@Override
	public String buildInsert(String tableName, String[] fields) {
		if ((fields == null) || (fields.length == 0)) {
			throw new IllegalArgumentException("Fields are null");
		}

		StringBuilder sb = new StringBuilder();

		sb.append("insert into ");
		sb.append(tableName);
		sb.append(" values (");

		for (int i = 0; i < fields.length; i++) {
			String field = fields[i];

			sb.append(field);

			if (i != (fields.length - 1)) {
				sb.append(", ");
			}
		}

		sb.append(")");

		return sb.toString() + ";\n";
	}

	private final FieldSerializer _fieldSerializer;

}