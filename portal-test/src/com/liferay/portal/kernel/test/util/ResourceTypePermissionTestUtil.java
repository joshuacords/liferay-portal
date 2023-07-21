/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.test.util;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.model.ResourceTypePermission;
import com.liferay.portal.kernel.service.ResourceTypePermissionLocalServiceUtil;

/**
 * @author     Alberto Chaparro
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class ResourceTypePermissionTestUtil {

	public static ResourceTypePermission addResourceTypePermission(
			long actionIds, long groupId, String name)
		throws Exception {

		return addResourceTypePermission(
			actionIds, groupId, name, RandomTestUtil.nextLong());
	}

	public static ResourceTypePermission addResourceTypePermission(
			long actionIds, long groupId, String name, long roleId)
		throws Exception {

		long resourceTypePermissionId = CounterLocalServiceUtil.increment(
			ResourceTypePermission.class.getName());

		ResourceTypePermission resourceTypePermission =
			ResourceTypePermissionLocalServiceUtil.createResourceTypePermission(
				resourceTypePermissionId);

		resourceTypePermission.setCompanyId(TestPropsValues.getCompanyId());
		resourceTypePermission.setGroupId(groupId);
		resourceTypePermission.setName(name);
		resourceTypePermission.setRoleId(roleId);
		resourceTypePermission.setActionIds(actionIds);

		return ResourceTypePermissionLocalServiceUtil.addResourceTypePermission(
			resourceTypePermission);
	}

}