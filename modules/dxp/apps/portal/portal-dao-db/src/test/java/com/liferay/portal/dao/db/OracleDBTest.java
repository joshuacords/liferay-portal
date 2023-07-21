/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.dao.db;

import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.test.BaseDBTestCase;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.io.IOException;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Shinn Lok
 */
public class OracleDBTest extends BaseDBTestCase {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testRewordAlterColumnType() throws IOException {
		Assert.assertEquals(
			"alter table DLFolder modify name VARCHAR2(255 CHAR);\n",
			buildSQL("alter_column_type DLFolder name VARCHAR(255) null;"));
	}

	@Test
	public void testRewordAlterColumnTypeLowerCase() throws IOException {
		Assert.assertEquals(
			"alter table DLFolder modify name VARCHAR2(255 CHAR);\n",
			buildSQL("alter_column_type DLFolder name varchar(255);"));
	}

	@Test
	public void testRewordAlterColumnTypeString() throws IOException {
		Assert.assertEquals(
			"alter table BlogsEntry modify description VARCHAR2(4000 CHAR);\n",
			buildSQL("alter_column_type BlogsEntry description STRING;"));
	}

	@Test
	public void testRewordAlterColumnTypeStringNull() throws IOException {
		Assert.assertEquals(
			"alter table BlogsEntry modify description VARCHAR2(4000 CHAR);\n",
			buildSQL("alter_column_type BlogsEntry description STRING null;"));
	}

	@Override
	protected DB getDB() {
		return new OracleDB(0, 0);
	}

}