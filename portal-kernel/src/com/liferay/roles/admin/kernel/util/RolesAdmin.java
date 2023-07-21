/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.roles.admin.kernel.util;

import com.liferay.portal.kernel.model.Role;

/**
 * @author Brian Wing Shun Chan
 */
public interface RolesAdmin {

	/**
	 * @deprecated As of Wilberforce (7.0.x), with no direct replacement
	 */
	@Deprecated
	public String getCssClassName(Role role);

	public String getIconCssClass(Role role);

}