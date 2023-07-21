/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.oauth2.provider.internal.upgrade.v2_0_0.util;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * @author	  Brian Wing Shun Chan
 * @generated
 */
public class OAuth2ScopeGrantTable {

	public static final String TABLE_NAME = "OAuth2ScopeGrant";

	public static final Object[][] TABLE_COLUMNS = {
		{"oAuth2ScopeGrantId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"oA2AScopeAliasesId", Types.BIGINT},
		{"applicationName", Types.VARCHAR},
		{"bundleSymbolicName", Types.VARCHAR}, {"scope", Types.VARCHAR},
		{"scopeAliases", Types.CLOB}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
new HashMap<String, Integer>();

static {
TABLE_COLUMNS_MAP.put("oAuth2ScopeGrantId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("oA2AScopeAliasesId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("applicationName", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("bundleSymbolicName", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("scope", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("scopeAliases", Types.CLOB);

}
	public static final String TABLE_SQL_CREATE =
"create table OAuth2ScopeGrant (oAuth2ScopeGrantId LONG not null primary key,companyId LONG,oA2AScopeAliasesId LONG,applicationName VARCHAR(255) null,bundleSymbolicName VARCHAR(255) null,scope VARCHAR(240) null,scopeAliases TEXT null)";

	public static final String TABLE_SQL_DROP = "drop table OAuth2ScopeGrant";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
		"create index IX_88938BF on OAuth2ScopeGrant (companyId, oA2AScopeAliasesId, applicationName[$COLUMN_LENGTH:255$], bundleSymbolicName[$COLUMN_LENGTH:255$], scope[$COLUMN_LENGTH:240$])",
		"create index IX_80FCAC23 on OAuth2ScopeGrant (oA2AScopeAliasesId)"
	};

}