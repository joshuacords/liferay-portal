/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.dao.orm;

/**
 * @author Brian Wing Shun Chan
 */
public class ProjectionFactoryUtil {

	public static Projection alias(Projection projection, String alias) {
		return getProjectionFactory().alias(projection, alias);
	}

	public static Projection avg(String propertyName) {
		return getProjectionFactory().avg(propertyName);
	}

	public static Projection count(String propertyName) {
		return getProjectionFactory().count(propertyName);
	}

	public static Projection countDistinct(String propertyName) {
		return getProjectionFactory().countDistinct(propertyName);
	}

	public static Projection distinct(Projection projection) {
		return getProjectionFactory().distinct(projection);
	}

	public static ProjectionFactory getProjectionFactory() {
		return _projectionFactory;
	}

	public static Projection groupProperty(String propertyName) {
		return getProjectionFactory().groupProperty(propertyName);
	}

	public static Projection max(String propertyName) {
		return getProjectionFactory().max(propertyName);
	}

	public static Projection min(String propertyName) {
		return getProjectionFactory().min(propertyName);
	}

	public static ProjectionList projectionList() {
		return getProjectionFactory().projectionList();
	}

	public static Projection property(String propertyName) {
		return getProjectionFactory().property(propertyName);
	}

	public static Projection rowCount() {
		return getProjectionFactory().rowCount();
	}

	public static Projection sqlGroupProjection(
		String sql, String groupBy, String[] columnAliases, Type[] types) {

		return getProjectionFactory().sqlGroupProjection(
			sql, groupBy, columnAliases, types);
	}

	public static Projection sqlProjection(
		String sql, String[] columnAliases, Type[] types) {

		return getProjectionFactory().sqlProjection(sql, columnAliases, types);
	}

	public static Projection sum(String propertyName) {
		return getProjectionFactory().sum(propertyName);
	}

	public void setProjectionFactory(ProjectionFactory projectionFactory) {
		_projectionFactory = projectionFactory;
	}

	private static ProjectionFactory _projectionFactory;

}