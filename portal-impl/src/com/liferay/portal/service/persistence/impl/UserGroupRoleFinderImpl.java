/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.UserGroupRole;
import com.liferay.portal.kernel.service.persistence.UserGroupRoleFinder;
import com.liferay.portal.model.impl.UserGroupRoleImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Drew Brokke
 */
public class UserGroupRoleFinderImpl
	extends UserGroupRoleFinderBaseImpl implements UserGroupRoleFinder {

	public static final String FIND_BY_GROUP_ROLE_TYPE =
		UserGroupRoleFinder.class.getName() + ".findByGroupRoleType";

	public static final String FIND_BY_USER_USER_GROUP_GROUP_ROLE =
		UserGroupRoleFinder.class.getName() + ".findByUserUserGroupGroupRole";

	@Override
	public List<UserGroupRole> findByGroupRoleType(long groupId, int roleType) {
		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_GROUP_ROLE_TYPE);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("UserGroupRole", UserGroupRoleImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);
			qPos.add(roleType);

			return q.list(true);
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public List<UserGroupRole> findByUserUserGroupGroupRole(
		long userId, long groupId) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_USER_USER_GROUP_GROUP_ROLE);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("UserGroupRole", UserGroupRoleImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);
			qPos.add(groupId);

			return q.list(true);
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
		finally {
			closeSession(session);
		}
	}

}