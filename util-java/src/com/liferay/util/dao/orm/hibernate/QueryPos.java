/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.util.dao.orm.hibernate;

import java.sql.Timestamp;

import org.hibernate.Query;

/**
 * @author     Brian Wing Shun Chan
 * @deprecated As of Bunyan (6.0.x), moved to {@link
 *             com.liferay.portal.kernel.dao.orm.QueryPos}
 */
@Deprecated
public class QueryPos {

	public static QueryPos getInstance(Query query) {
		return new QueryPos(query);
	}

	public void add(boolean value) {
		_query.setBoolean(_pos++, value);
	}

	public void add(Boolean value) {
		if (value != null) {
			_query.setBoolean(_pos++, value.booleanValue());
		}
		else {
			addNull();
		}
	}

	public void add(double value) {
		_query.setDouble(_pos++, value);
	}

	public void add(Double value) {
		if (value != null) {
			_query.setDouble(_pos++, value.doubleValue());
		}
		else {
			addNull();
		}
	}

	public void add(float value) {
		_query.setFloat(_pos++, value);
	}

	public void add(Float value) {
		if (value != null) {
			_query.setFloat(_pos++, value.intValue());
		}
		else {
			addNull();
		}
	}

	public void add(int value) {
		_query.setInteger(_pos++, value);
	}

	public void add(Integer value) {
		if (value != null) {
			_query.setInteger(_pos++, value.intValue());
		}
		else {
			addNull();
		}
	}

	public void add(long value) {
		_query.setLong(_pos++, value);
	}

	public void add(Long value) {
		if (value != null) {
			_query.setLong(_pos++, value.longValue());
		}
		else {
			addNull();
		}
	}

	public void add(short value) {
		_query.setShort(_pos++, value);
	}

	public void add(Short value) {
		if (value != null) {
			_query.setShort(_pos++, value.shortValue());
		}
		else {
			addNull();
		}
	}

	public void add(String value) {
		_query.setString(_pos++, value);
	}

	public void add(String[] values) {
		add(values, 1);
	}

	public void add(String[] values, int count) {
		for (String value : values) {
			for (int j = 0; j < count; j++) {
				add(value);
			}
		}
	}

	public void add(Timestamp value) {
		_query.setTimestamp(_pos++, value);
	}

	public int getPos() {
		return _pos;
	}

	protected void addNull() {
		_query.setSerializable(_pos++, null);
	}

	private QueryPos(Query query) {
		_query = query;
	}

	private int _pos;
	private final Query _query;

}