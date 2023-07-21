/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.security.permission;

import com.liferay.portal.kernel.util.ServiceProxyFactory;

/**
 * @author Raymond Augé
 * @see    InlineSQLHelper
 */
public class InlineSQLHelperUtil {

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public static InlineSQLHelper getInlineSQLHelper() {
		return _inlineSQLPermission;
	}

	public static boolean isEnabled() {
		return _inlineSQLPermission.isEnabled();
	}

	public static boolean isEnabled(long groupId) {
		return _inlineSQLPermission.isEnabled(groupId);
	}

	public static boolean isEnabled(long companyId, long groupId) {
		return _inlineSQLPermission.isEnabled(companyId, groupId);
	}

	public static boolean isEnabled(long[] groupIds) {
		return _inlineSQLPermission.isEnabled(groupIds);
	}

	public static String replacePermissionCheck(
		String sql, String className, String classPKField) {

		return _inlineSQLPermission.replacePermissionCheck(
			sql, className, classPKField);
	}

	public static String replacePermissionCheck(
		String sql, String className, String classPKField, long groupId) {

		return _inlineSQLPermission.replacePermissionCheck(
			sql, className, classPKField, groupId);
	}

	public static String replacePermissionCheck(
		String sql, String className, String classPKField, long groupId,
		String bridgeJoin) {

		return _inlineSQLPermission.replacePermissionCheck(
			sql, className, classPKField, groupId, bridgeJoin);
	}

	public static String replacePermissionCheck(
		String sql, String className, String classPKField, long[] groupIds) {

		return _inlineSQLPermission.replacePermissionCheck(
			sql, className, classPKField, groupIds);
	}

	public static String replacePermissionCheck(
		String sql, String className, String classPKField, long[] groupIds,
		String bridgeJoin) {

		return _inlineSQLPermission.replacePermissionCheck(
			sql, className, classPKField, groupIds, bridgeJoin);
	}

	public static String replacePermissionCheck(
		String sql, String className, String classPKField, String userIdField) {

		return _inlineSQLPermission.replacePermissionCheck(
			sql, className, classPKField, userIdField);
	}

	public static String replacePermissionCheck(
		String sql, String className, String classPKField, String userIdField,
		long groupId) {

		return _inlineSQLPermission.replacePermissionCheck(
			sql, className, classPKField, userIdField, groupId);
	}

	public static String replacePermissionCheck(
		String sql, String className, String classPKField, String userIdField,
		long groupId, String bridgeJoin) {

		return _inlineSQLPermission.replacePermissionCheck(
			sql, className, classPKField, userIdField, groupId, bridgeJoin);
	}

	public static String replacePermissionCheck(
		String sql, String className, String classPKField, String userIdField,
		long[] groupIds) {

		return _inlineSQLPermission.replacePermissionCheck(
			sql, className, classPKField, userIdField, groupIds);
	}

	public static String replacePermissionCheck(
		String sql, String className, String classPKField, String userIdField,
		long[] groupIds, String bridgeJoin) {

		return _inlineSQLPermission.replacePermissionCheck(
			sql, className, classPKField, userIdField, groupIds, bridgeJoin);
	}

	public static String replacePermissionCheck(
		String sql, String className, String classPKField, String userIdField,
		String bridgeJoin) {

		return _inlineSQLPermission.replacePermissionCheck(
			sql, className, classPKField, userIdField, bridgeJoin);
	}

	public static String replacePermissionCheck(
		String sql, String className, String classPKField, String userIdField,
		String groupIdField, long[] groupIds, String bridgeJoin) {

		return _inlineSQLPermission.replacePermissionCheck(
			sql, className, classPKField, userIdField, groupIdField, groupIds,
			bridgeJoin);
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public void setInlineSQLHelper(InlineSQLHelper inlineSQLPermission) {
	}

	private static volatile InlineSQLHelper _inlineSQLPermission =
		ServiceProxyFactory.newServiceTrackedInstance(
			InlineSQLHelper.class, InlineSQLHelperUtil.class,
			"_inlineSQLPermission", false);

}