/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.roles.admin.kernel.util;

import com.liferay.portal.kernel.model.Role;

/**
 * @author Brian Wing Shun Chan
 */
public class RolesAdminUtil {

	/**
	 * @deprecated As of Wilberforce (7.0.x), with no direct replacement
	 */
	@Deprecated
	public static String getCssClassName(Role role) {
		return getRolesAdmin().getCssClassName(role);
	}

	public static String getIconCssClass(Role role) {
		return getRolesAdmin().getIconCssClass(role);
	}

	public static RolesAdmin getRolesAdmin() {
		return _rolesAdmin;
	}

	public void setRolesAdmin(RolesAdmin rolesAdmin) {
		_rolesAdmin = rolesAdmin;
	}

	private static RolesAdmin _rolesAdmin;

}