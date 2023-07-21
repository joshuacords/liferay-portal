/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.test.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;

/**
 * @author Manuel de la Peña
 */
public class ServiceContextTestUtil {

	public static ServiceContext getServiceContext() throws PortalException {
		return getServiceContext(TestPropsValues.getGroupId());
	}

	public static ServiceContext getServiceContext(Group group, long userId) {
		return getServiceContext(
			group.getCompanyId(), group.getGroupId(), userId);
	}

	public static ServiceContext getServiceContext(long groupId)
		throws PortalException {

		if (groupId == TestPropsValues.getGroupId()) {
			return getServiceContext(groupId, TestPropsValues.getUserId());
		}

		Group group = GroupLocalServiceUtil.getGroup(groupId);

		User user = UserTestUtil.getAdminUser(group.getCompanyId());

		return getServiceContext(group, user.getUserId());
	}

	public static ServiceContext getServiceContext(long groupId, long userId)
		throws PortalException {

		if (groupId == TestPropsValues.getGroupId()) {
			return getServiceContext(
				TestPropsValues.getCompanyId(), groupId, userId);
		}

		Group group = GroupLocalServiceUtil.getGroup(groupId);

		return getServiceContext(
			group.getCompanyId(), group.getGroupId(), userId);
	}

	public static ServiceContext getServiceContext(
		long companyId, long groupId, long userId) {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);
		serviceContext.setCompanyId(companyId);
		serviceContext.setScopeGroupId(groupId);
		serviceContext.setUserId(userId);

		return serviceContext;
	}

}