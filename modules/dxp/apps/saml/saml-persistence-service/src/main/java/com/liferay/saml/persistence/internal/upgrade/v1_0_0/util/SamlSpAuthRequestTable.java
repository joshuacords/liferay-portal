/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.saml.persistence.internal.upgrade.v1_0_0.util;

import java.sql.Types;

/**
 * @author Brian Wing Shun Chan
 */
public class SamlSpAuthRequestTable {

	public static final Object[][] TABLE_COLUMNS = {
		{"samlSpAuthnRequestId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"createDate", Types.TIMESTAMP}, {"samlIdpEntityId", Types.VARCHAR},
		{"samlSpAuthRequestKey", Types.VARCHAR}
	};

	public static final String TABLE_NAME = "SamlSpAuthRequest";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
		"create index IX_10D77E09 on SamlSpAuthRequest (samlIdpEntityId, samlSpAuthRequestKey)"
	};

	public static final String TABLE_SQL_CREATE =
		"create table SamlSpAuthRequest (samlSpAuthnRequestId LONG not null primary key,companyId LONG,createDate DATE null,samlIdpEntityId VARCHAR(75) null,samlSpAuthRequestKey VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP = "drop table SamlSpAuthRequest";

}