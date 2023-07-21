/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.root.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

/**
 * @author Amos Fong
 */
public interface ModelPermission {

	public void check(
			PermissionChecker permissionChecker, long classPK, String actionId)
		throws PortalException;

	public boolean contains(
			PermissionChecker permissionChecker, long classPK, String actionId)
		throws PortalException;

	public String getClassName();

}