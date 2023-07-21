/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.util;

import com.liferay.petra.lang.CentralizedThreadLocal;
import com.liferay.portal.kernel.model.ResourcePermission;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author     Shuyang Zhou
 * @author     Brian Wing Shun Chan
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class ResourcePermissionsThreadLocal {

	public static Map<Long, ResourcePermission> getResourcePermissions() {
		return _resourcePermissions.get();
	}

	public static void setResourcePermissions(
		List<ResourcePermission> resourcePermissions) {

		if (resourcePermissions != null) {
			Map<Long, ResourcePermission> resourcePermissionMap =
				new HashMap<>();

			for (ResourcePermission resourcePermission : resourcePermissions) {
				resourcePermissionMap.put(
					resourcePermission.getRoleId(), resourcePermission);
			}

			_resourcePermissions.set(resourcePermissionMap);
		}
		else {
			_resourcePermissions.remove();
		}
	}

	private static final ThreadLocal<Map<Long, ResourcePermission>>
		_resourcePermissions = new CentralizedThreadLocal<>(
			ResourcePermissionsThreadLocal.class + "._resourcePermissions",
			() -> null, false);

}