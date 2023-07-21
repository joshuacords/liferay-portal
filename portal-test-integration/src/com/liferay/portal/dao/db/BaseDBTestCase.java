/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.dao.db;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.db.DB;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author     Miguel Pastor
 * @author     László Csontos
 * @deprecated As of Mueller (7.2.x), replaced by {@link
 *             com.liferay.portal.kernel.dao.db.BaseDBTestCase}
 */
@Deprecated
public abstract class BaseDBTestCase {

	@Test
	public void testReplaceTemplate() throws IOException {
		StringBundler sb = new StringBundler(5);

		sb.append("select * from SomeTable where someColumn1 = ");
		sb.append(_db.getTemplateFalse());
		sb.append(" and someColumn2 = ");
		sb.append(_db.getTemplateTrue());
		sb.append(StringPool.NEW_LINE);

		Assert.assertEquals(sb.toString(), buildSQL(_BOOLEAN_LITERAL_QUERY));

		Assert.assertEquals(
			_BOOLEAN_PATTERN_QUERY + StringPool.NEW_LINE,
			buildSQL(_BOOLEAN_PATTERN_QUERY));
	}

	protected String buildSQL(String query) throws IOException {
		return _db.buildSQL(query);
	}

	protected abstract DB getDB();

	protected static final String RENAME_TABLE_QUERY = "alter_table_name a b";

	private static final String _BOOLEAN_LITERAL_QUERY =
		"select * from SomeTable where someColumn1 = FALSE and someColumn2 = " +
			"TRUE";

	private static final String _BOOLEAN_PATTERN_QUERY =
		"select * from SomeTable where someColumn1 = [$FALSE$] and " +
			"someColumn2 = [$TRUE$]";

	private final DB _db = getDB();

}