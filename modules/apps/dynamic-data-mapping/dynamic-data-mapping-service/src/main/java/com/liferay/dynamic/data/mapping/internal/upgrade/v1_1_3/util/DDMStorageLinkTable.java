/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.internal.upgrade.v1_1_3.util;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * @author	  Pedro Queiroz
 * @generated
 */
public class DDMStorageLinkTable {

	public static final String TABLE_NAME = "DDMStorageLink";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR},
		{"storageLinkId", Types.BIGINT},
		{"companyId", Types.BIGINT},
		{"classNameId", Types.BIGINT},
		{"classPK", Types.BIGINT},
		{"structureId", Types.BIGINT},
		{"structureVersionId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

static {
TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("storageLinkId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);

TABLE_COLUMNS_MAP.put("structureId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("structureVersionId", Types.BIGINT);

}
	public static final String TABLE_SQL_CREATE = "create table DDMStorageLink (uuid_ VARCHAR(75) null,storageLinkId LONG not null primary key,companyId LONG,classNameId LONG,classPK LONG,structureId LONG,structureVersionId LONG)";

	public static final String TABLE_SQL_DROP = "drop table DDMStorageLink";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
		"create unique index IX_702D1AD5 on DDMStorageLink (classPK)",
		"create index IX_81776090 on DDMStorageLink (structureId)",
		"create index IX_14DADA22 on DDMStorageLink (structureVersionId)",
		"create index IX_DB81EB42 on DDMStorageLink (uuid_[$COLUMN_LENGTH:75$], companyId)"
	};

}