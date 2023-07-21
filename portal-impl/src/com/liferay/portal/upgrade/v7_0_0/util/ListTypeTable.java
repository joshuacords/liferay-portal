/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upgrade.v7_0_0.util;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * @author	  Brian Wing Shun Chan
 * @generated
 */
public class ListTypeTable {

	public static final String TABLE_NAME = "ListType";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT},
		{"listTypeId", Types.BIGINT},
		{"name", Types.VARCHAR},
		{"type_", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

static {
TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);

TABLE_COLUMNS_MAP.put("listTypeId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("type_", Types.VARCHAR);

}
	public static final String TABLE_SQL_CREATE = "create table ListType (mvccVersion LONG default 0 not null,listTypeId LONG not null primary key,name VARCHAR(75) null,type_ VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP = "drop table ListType";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
		"create index IX_77729718 on ListType (name[$COLUMN_LENGTH:75$], type_[$COLUMN_LENGTH:75$])",
		"create index IX_2932DD37 on ListType (type_[$COLUMN_LENGTH:75$])"
	};

}