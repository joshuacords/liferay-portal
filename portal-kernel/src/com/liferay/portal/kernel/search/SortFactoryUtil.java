/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.search;

import com.liferay.portal.kernel.util.ServiceProxyFactory;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public class SortFactoryUtil {

	public static Sort create(String fieldName, boolean reverse) {
		return _getSortFactory().create(fieldName, reverse);
	}

	public static Sort create(String fieldName, int type, boolean reverse) {
		return _getSortFactory().create(fieldName, type, reverse);
	}

	public static Sort[] getDefaultSorts() {
		return _getSortFactory().getDefaultSorts();
	}

	public static Sort getSort(
		Class<?> clazz, int type, String orderByCol, boolean inferSortField,
		String orderByType) {

		return _getSortFactory().getSort(
			clazz, type, orderByCol, inferSortField, orderByType);
	}

	public static Sort getSort(
		Class<?> clazz, int type, String orderByCol, String orderByType) {

		return _getSortFactory().getSort(clazz, type, orderByCol, orderByType);
	}

	public static Sort getSort(
		Class<?> clazz, String orderByCol, String orderByType) {

		return _getSortFactory().getSort(clazz, orderByCol, orderByType);
	}

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link #_getSortFactory()}
	 */
	@Deprecated
	public static SortFactory getSortFactory() {
		return _getSortFactory();
	}

	public static Sort[] toArray(List<Sort> sorts) {
		return _getSortFactory().toArray(sorts);
	}

	private static SortFactory _getSortFactory() {
		return _sortFactory;
	}

	private static volatile SortFactory _sortFactory =
		ServiceProxyFactory.newServiceTrackedInstance(
			SortFactory.class, SortFactoryUtil.class, "_sortFactory", false);

}