/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.akismet.internal.util;

import com.liferay.message.boards.constants.MBCategoryConstants;
import com.liferay.message.boards.model.MBMessage;
import com.liferay.message.boards.service.MBMessageLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.List;

/**
 * @author Jamie Sammons
 */
public class ModerationUtil {

	public static List<MBMessage> getMBMessages(
			long scopeGroupId, int start, int end)
		throws PortalException {

		DynamicQuery dynamicQuery = buildMBMessageDynamicQuery(
			scopeGroupId, false);

		return MBMessageLocalServiceUtil.dynamicQuery(dynamicQuery, start, end);
	}

	public static int getMBMessagesCount(long scopeGroupId)
		throws PortalException {

		DynamicQuery dynamicQuery = buildMBMessageDynamicQuery(
			scopeGroupId, false);

		return (int)MBMessageLocalServiceUtil.dynamicQueryCount(dynamicQuery);
	}

	protected static DynamicQuery buildMBMessageDynamicQuery(
			long scopeGroupId, boolean discussion)
		throws PortalException {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			MBMessage.class);

		Group group = GroupLocalServiceUtil.getGroup(scopeGroupId);

		if (!group.isCompany()) {
			Property groupIdProperty = PropertyFactoryUtil.forName("groupId");

			Long[] scopeGroupIds = getChildScopeGroupIds(scopeGroupId);

			dynamicQuery.add(groupIdProperty.in(scopeGroupIds));
		}

		Property categoryIdProperty = PropertyFactoryUtil.forName("categoryId");

		if (discussion) {
			dynamicQuery.add(
				categoryIdProperty.eq(
					MBCategoryConstants.DISCUSSION_CATEGORY_ID));
		}
		else {
			dynamicQuery.add(
				categoryIdProperty.ne(
					MBCategoryConstants.DISCUSSION_CATEGORY_ID));
		}

		Property statusProperty = PropertyFactoryUtil.forName("status");

		dynamicQuery.add(statusProperty.eq(WorkflowConstants.STATUS_DENIED));

		return dynamicQuery;
	}

	protected static Long[] getChildScopeGroupIds(long parentGroupId) {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Group.class);

		Property parentGroupIdProperty = PropertyFactoryUtil.forName(
			"parentGroupId");

		dynamicQuery.add(parentGroupIdProperty.eq(parentGroupId));

		List<Group> groups = GroupLocalServiceUtil.dynamicQuery(dynamicQuery);

		Long[] scopeGroupIds = new Long[groups.size() + 1];

		scopeGroupIds[0] = parentGroupId;

		for (int i = 0; i < groups.size(); i++) {
			Group group = groups.get(i);

			scopeGroupIds[i + 1] = group.getGroupId();
		}

		return scopeGroupIds;
	}

}