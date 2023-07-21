/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.lock.internal.upgrade.v1_0_0.util;

import java.sql.Types;

/**
 * @author	  Brian Wing Shun Chan
 * @generated
 */
public class LockTable {

	public static final String TABLE_NAME = "Lock_";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT},
		{"uuid_", Types.VARCHAR},
		{"lockId", Types.BIGINT},
		{"companyId", Types.BIGINT},
		{"userId", Types.BIGINT},
		{"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP},
		{"className", Types.VARCHAR},
		{"key_", Types.VARCHAR},
		{"owner", Types.VARCHAR},
		{"inheritable", Types.BOOLEAN},
		{"expirationDate", Types.TIMESTAMP}
	};

	public static final String TABLE_SQL_CREATE = "create table Lock_ (mvccVersion LONG default 0,uuid_ VARCHAR(75) null,lockId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,className VARCHAR(75) null,key_ VARCHAR(200) null,owner VARCHAR(1024) null,inheritable BOOLEAN,expirationDate DATE null)";

	public static final String TABLE_SQL_DROP = "drop table Lock_";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
		"create unique index IX_228562AD on Lock_ (className, key_)",
		"create index IX_E3F1286B on Lock_ (expirationDate)",
		"create index IX_2C418EAE on Lock_ (uuid_, companyId)"
	};

}