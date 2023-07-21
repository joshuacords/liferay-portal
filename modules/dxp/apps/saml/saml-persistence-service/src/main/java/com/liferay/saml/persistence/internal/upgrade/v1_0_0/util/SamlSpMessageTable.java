/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.saml.persistence.internal.upgrade.v1_0_0.util;

import java.sql.Types;

/**
 * @author Brian Wing Shun Chan
 */
public class SamlSpMessageTable {

	public static final Object[][] TABLE_COLUMNS = {
		{"samlSpMessageId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"createDate", Types.TIMESTAMP}, {"samlIdpEntityId", Types.VARCHAR},
		{"samlIdpResponseKey", Types.VARCHAR},
		{"expirationDate", Types.TIMESTAMP}
	};

	public static final String TABLE_NAME = "SamlSpMessage";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
		"create index IX_5615F9DD on SamlSpMessage (samlIdpEntityId, samlIdpResponseKey)"
	};

	public static final String TABLE_SQL_CREATE =
		"create table SamlSpMessage (samlSpMessageId LONG not null primary key,companyId LONG,createDate DATE null,samlIdpEntityId VARCHAR(75) null,samlIdpResponseKey VARCHAR(75) null,expirationDate DATE null)";

	public static final String TABLE_SQL_DROP = "drop table SamlSpMessage";

}