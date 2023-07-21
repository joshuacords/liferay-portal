/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.dao.orm;

/**
 * @author Brian Wing Shun Chan
 */
public interface DynamicQueryFactory {

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link #forClass(Class,
	 *             ClassLoader)}
	 */
	@Deprecated
	public DynamicQuery forClass(Class<?> clazz);

	public DynamicQuery forClass(Class<?> clazz, ClassLoader classLoader);

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link #forClass(Class,
	 *             String, ClassLoader)}
	 */
	@Deprecated
	public DynamicQuery forClass(Class<?> clazz, String alias);

	public DynamicQuery forClass(
		Class<?> clazz, String alias, ClassLoader classLoader);

}