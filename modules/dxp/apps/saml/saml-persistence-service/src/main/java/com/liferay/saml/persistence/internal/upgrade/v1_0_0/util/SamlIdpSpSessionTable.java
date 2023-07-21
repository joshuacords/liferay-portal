/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.saml.persistence.internal.upgrade.v1_0_0.util;

import java.sql.Types;

/**
 * @author Brian Wing Shun Chan
 */
public class SamlIdpSpSessionTable {

	public static final Object[][] TABLE_COLUMNS = {
		{"samlIdpSpSessionId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"samlIdpSsoSessionId", Types.BIGINT},
		{"samlSpEntityId", Types.VARCHAR}, {"nameIdFormat", Types.VARCHAR},
		{"nameIdValue", Types.VARCHAR}
	};

	public static final String TABLE_NAME = "SamlIdpSpSession";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
		"create index IX_8EDF9D43 on SamlIdpSpSession (samlIdpSsoSessionId)",
		"create index IX_F2B40CDF on SamlIdpSpSession (samlIdpSsoSessionId, samlSpEntityId)"
	};

	public static final String TABLE_SQL_CREATE =
		"create table SamlIdpSpSession (samlIdpSpSessionId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,samlIdpSsoSessionId LONG,samlSpEntityId VARCHAR(75) null,nameIdFormat VARCHAR(75) null,nameIdValue VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP = "drop table SamlIdpSpSession";

}