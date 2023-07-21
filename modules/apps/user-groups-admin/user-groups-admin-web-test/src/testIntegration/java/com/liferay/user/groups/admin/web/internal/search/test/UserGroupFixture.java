/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.user.groups.admin.web.internal.search.test;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.UserGroup;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserGroupLocalServiceUtil;
import com.liferay.portal.kernel.test.randomizerbumpers.NumericStringRandomizerBumper;
import com.liferay.portal.kernel.test.randomizerbumpers.UniqueStringRandomizerBumper;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Igor Fabiano Nazar
 * @author Luan Maoski
 */
public class UserGroupFixture {

	public UserGroupFixture(Group group) {
		_group = group;
	}

	public UserGroup createUserGroup() throws Exception {
		return createUserGroup(Collections.emptyMap());
	}

	public UserGroup createUserGroup(Map<String, Serializable> expandoValues)
		throws Exception {

		return createUserGroup(
			RandomTestUtil.randomString(
				NumericStringRandomizerBumper.INSTANCE,
				UniqueStringRandomizerBumper.INSTANCE),
			RandomTestUtil.randomString(50), expandoValues);
	}

	public UserGroup createUserGroup(String name) throws Exception {
		return createUserGroup(
			name, RandomTestUtil.randomString(50), Collections.emptyMap());
	}

	public UserGroup createUserGroup(
			String name, String description,
			Map<String, Serializable> expandoValues)
		throws PortalException {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(_group.getGroupId());

		serviceContext.setExpandoBridgeAttributes(expandoValues);

		UserGroup userGroup = UserGroupLocalServiceUtil.addUserGroup(
			serviceContext.getUserId(), serviceContext.getCompanyId(), name,
			description, serviceContext);

		_userGroups.add(userGroup);

		return userGroup;
	}

	public List<UserGroup> getUserGroups() {
		return _userGroups;
	}

	public void updateDisplaySettings(Locale locale) throws Exception {
		Group group = GroupTestUtil.updateDisplaySettings(
			_group.getGroupId(), null, locale);

		_group.setModelAttributes(group.getModelAttributes());
	}

	private final Group _group;
	private final List<UserGroup> _userGroups = new ArrayList<>();

}