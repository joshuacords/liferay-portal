/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.dao.orm.custom.sql;

import com.liferay.portal.kernel.dao.orm.QueryDefinition;
import com.liferay.portal.kernel.dao.orm.WildcardMode;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Brian Wing Shun Chan
 * @author Bruno Farache
 * @author Raymond Augé
 * @see    com.liferay.util.dao.orm.CustomSQL
 */
public interface CustomSQL {

	public String appendCriteria(String sql, String criteria);

	public String get(Class<?> clazz, String id);

	public String get(
		Class<?> clazz, String id, QueryDefinition<?> queryDefinition);

	public String get(
		Class<?> clazz, String id, QueryDefinition<?> queryDefinition,
		String tableName);

	public String[] keywords(String keywords);

	public String[] keywords(String keywords, boolean lowerCase);

	public String[] keywords(
		String keywords, boolean lowerCase, WildcardMode wildcardMode);

	public String[] keywords(String keywords, WildcardMode wildcardMode);

	public String[] keywords(String[] keywordsArray);

	public String[] keywords(String[] keywordsArray, boolean lowerCase);

	public String removeGroupBy(String sql);

	public String removeOrderBy(String sql);

	public String replaceAndOperator(String sql, boolean andOperator);

	public String replaceGroupBy(String sql, String groupBy);

	public String replaceIsNull(String sql);

	public String replaceKeywords(
		String sql, String field, boolean last, int[] values);

	public String replaceKeywords(
		String sql, String field, boolean last, long[] values);

	public String replaceKeywords(
		String sql, String field, String operator, boolean last,
		String[] values);

	public String replaceOrderBy(String sql, OrderByComparator<?> obc);

}