/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.data.partitioning.sql.builder.internal.serializer;

import com.liferay.portal.tools.data.partitioning.sql.builder.exporter.serializer.FieldSerializer;

import java.sql.Date;
import java.sql.Timestamp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @author Miguel Pastor
 */
public class DefaultFieldSerializer implements FieldSerializer {

	public DefaultFieldSerializer() {
		_dateFormat = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
	}

	public DefaultFieldSerializer(DateFormat dateFormat) {
		_dateFormat = dateFormat;
	}

	@Override
	public String serialize(Object object) {
		StringBuilder sb = new StringBuilder();

		if (object == null) {
			sb.append("null");
		}
		else if (object instanceof Date || object instanceof Timestamp) {
			sb.append("'");
			sb.append(_dateFormat.format(object));
			sb.append("'");
		}
		else if (object instanceof String) {
			String value = (String)object;

			value = value.replace("'", "''");

			sb.append("'");
			sb.append(value);
			sb.append("'");
		}
		else {
			sb.append("'");
			sb.append(object);
			sb.append("'");
		}

		return sb.toString();
	}

	private final DateFormat _dateFormat;

}