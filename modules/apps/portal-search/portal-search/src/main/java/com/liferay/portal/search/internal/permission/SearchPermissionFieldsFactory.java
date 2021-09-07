/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.search.internal.permission;

import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.NoSuchResourceException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;

import java.util.ArrayList;
import java.util.List;

import com.liferay.portal.search.spi.model.permission.SearchPermissionDefinition;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Preston Crary
 */
@Component(service = SearchPermissionFieldsFactory.class)
public class SearchPermissionFieldsFactory {

	public SearchPermissionFields createSearchPermissionFields(
		long companyId, long groupId, String className, long classPK,
		String permissionName, String viewActionId) {

		SearchPermissionFields searchPermissionFields = null;

		SearchPermissionDefinition searchPermissionDefinition =
			_serviceTrackerMap.getService(className);

		if (searchPermissionDefinition != null) {
			for (SearchPermissionDefinition.RoleContributor roleProvider :
					searchPermissionDefinition.getRoleContributors()) {

				searchPermissionFields = null;
			}
		}

		if (searchPermissionFields != null) {
			return searchPermissionFields;
		}

		try {
			List<Role> roles = _resourcePermissionLocalService.getRoles(
				companyId, permissionName, ResourceConstants.SCOPE_INDIVIDUAL,
				String.valueOf(classPK), viewActionId);

			if (!roles.isEmpty()) {
				List<Long> roleIds = new ArrayList<>();
				List<String> groupRoleIds = new ArrayList<>();

				for (Role role : roles) {
					if ((role.getType() == RoleConstants.TYPE_ORGANIZATION) ||
						(role.getType() == RoleConstants.TYPE_SITE)) {

						groupRoleIds.add(
							groupId + StringPool.DASH + role.getRoleId());
					}
					else {
						roleIds.add(role.getRoleId());
					}
				}

				searchPermissionFields = new SearchPermissionFields(
					roleIds.toArray(new Long[0]),
					groupRoleIds.toArray(new String[0]));
			}
		}
		catch (NoSuchResourceException noSuchResourceException) {
			if (_log.isDebugEnabled()) {
				_log.debug(noSuchResourceException, noSuchResourceException);
			}
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					StringBundler.concat(
						"Unable to get permission fields for class name ",
						permissionName, " and class PK ", classPK),
					exception);
			}
		}

		return searchPermissionFields;
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceTrackerMap = ServiceTrackerMapFactory.openSingleValueMap(
			bundleContext, SearchPermissionDefinition.class, null,
			(serviceReference, emitter) -> {
				SearchPermissionDefinition searchPermissionDefinition =
					bundleContext.getService(serviceReference);

				emitter.emit(searchPermissionDefinition.getClassName());
			});
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerMap.close();
	}

	private ServiceTrackerMap<String, SearchPermissionDefinition>
		_serviceTrackerMap;

	private static final Log _log = LogFactoryUtil.getLog(
		SearchPermissionFieldsFactory.class);

	@Reference
	private ResourcePermissionLocalService _resourcePermissionLocalService;

}