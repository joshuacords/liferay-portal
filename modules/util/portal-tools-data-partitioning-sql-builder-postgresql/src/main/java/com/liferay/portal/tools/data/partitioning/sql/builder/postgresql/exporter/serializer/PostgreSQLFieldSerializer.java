/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.data.partitioning.sql.builder.postgresql.exporter.serializer;

import com.liferay.portal.tools.data.partitioning.sql.builder.exporter.serializer.FieldSerializer;

import java.sql.Date;
import java.sql.Timestamp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @author Manuel de la Peña
 */
public class PostgreSQLFieldSerializer implements FieldSerializer {

	@Override
	public String serialize(Object object) {
		StringBuilder sb = new StringBuilder();

		if (object == null) {
			sb.append("null");
		}
		else if (object instanceof Date || object instanceof Timestamp) {
			sb.append("to_timestamp('");
			sb.append(_dateFormat.format(object));
			sb.append("', 'YYYY-MM-DD HH24:MI:SS:MS')");
		}
		else if (object instanceof String) {
			sb.append("'");

			String value = (String)object;

			sb.append(value.replace("'", "''"));

			sb.append("'");
		}
		else {
			sb.append("'");
			sb.append(object);
			sb.append("'");
		}

		return sb.toString();
	}

	private final DateFormat _dateFormat = new SimpleDateFormat(
		"YYYY-MM-DD HH:MM:SS");

}