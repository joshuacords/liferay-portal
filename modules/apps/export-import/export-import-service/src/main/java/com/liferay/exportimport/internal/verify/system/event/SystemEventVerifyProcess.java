/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.exportimport.internal.verify.system.event;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.SystemEventLocalService;
import com.liferay.portal.verify.VerifyProcess;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author     Akos Thurzo
 * @deprecated As of Mueller (7.2.x), with no direct replacement
 */
@Component(
	immediate = true,
	property = "verify.process.name=com.liferay.systemevent.internal.verify",
	service = VerifyProcess.class
)
@Deprecated
public class SystemEventVerifyProcess extends VerifyProcess {

	protected void deleteInvalidSystemEvents() throws PortalException {
		ActionableDynamicQuery actionableDynamicQuery =
			_groupLocalService.getActionableDynamicQuery();

		actionableDynamicQuery.setAddCriteriaMethod(
			dynamicQuery -> {
				Property liveGroupIdProperty = PropertyFactoryUtil.forName(
					"liveGroupId");
				Property remoteStagingGroupCountProperty =
					PropertyFactoryUtil.forName("remoteStagingGroupCount");

				dynamicQuery.add(
					RestrictionsFactoryUtil.or(
						liveGroupIdProperty.ne(0L),
						remoteStagingGroupCountProperty.gt(0)));
			});
		actionableDynamicQuery.setPerformActionMethod(
			(Group group) -> {
				long liveGroupId = group.getLiveGroupId();

				if (liveGroupId == 0) {
					liveGroupId = group.getGroupId();
				}

				if (!_systemEventLocalService.validateGroup(liveGroupId)) {
					_systemEventLocalService.deleteSystemEvents(liveGroupId);
				}
			});

		actionableDynamicQuery.performActions();
	}

	@Override
	protected void doVerify() throws Exception {
		deleteInvalidSystemEvents();
	}

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private SystemEventLocalService _systemEventLocalService;

}