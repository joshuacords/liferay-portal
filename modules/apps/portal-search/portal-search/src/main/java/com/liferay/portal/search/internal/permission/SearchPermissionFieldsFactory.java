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
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.search.spi.model.permission.SearchPermissionDefinition;

import java.util.ArrayList;
import java.util.List;

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
			long companyId, long groupId, String className, long entryClassPK,
			long id, String permissionName, String viewActionId)
		throws PortalException {

		SearchPermissionFields searchPermissionFields = null;

		SearchPermissionDefinition<?> searchPermissionDefinition =
			_serviceTrackerMap.getService(className);

		RoleSetContributorContextImpl roleSetContributorContextImpl =
			_getRoleSetContributorContextImpl(
				companyId, groupId, entryClassPK, id,
				searchPermissionDefinition);

		if (searchPermissionFields != null) {
			return searchPermissionFields;
		}

		try {
			List<Role> roles = _resourcePermissionLocalService.getRoles(
				companyId, permissionName, ResourceConstants.SCOPE_INDIVIDUAL,
				String.valueOf(entryClassPK), viewActionId);

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
				_log.debug(noSuchResourceException);
			}
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					StringBundler.concat(
						"Unable to get permission fields for class name ",
						permissionName, " and class PK ", id),
					exception);
			}
		}

		return searchPermissionFields;
	}

	@Activate
	@SuppressWarnings("unchecked")
	protected void activate(BundleContext bundleContext) {
		_serviceTrackerMap = ServiceTrackerMapFactory.openSingleValueMap(
			bundleContext,
			(Class<SearchPermissionDefinition<?>>)
				(Class<?>)SearchPermissionDefinition.class,
			null,
			(serviceReference, emitter) -> {
				SearchPermissionDefinition<?> searchPermissionDefinition =
					bundleContext.getService(serviceReference);

				emitter.emit(searchPermissionDefinition.getClassName());
			});
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerMap.close();
	}

	private <T> RoleSetContributorContextImpl _getRoleSetContributorContextImpl(
			long companyId, long groupId, long entryClassPK, long id,
			SearchPermissionDefinition<T> searchPermissionDefinition)
		throws PortalException {

		RoleSetContributorContextImpl roleSetContributorContextImpl =
			new RoleSetContributorContextImpl(
				companyId, groupId, _roleLocalService);

		if (searchPermissionDefinition != null) {
			T model = searchPermissionDefinition.getModel(entryClassPK);

			if (model == null) {
				model = searchPermissionDefinition.getModel(id);
			}

			try {
				for (SearchPermissionDefinition.RoleSetContributor<T>
						roleProvider :
							searchPermissionDefinition.
								getRoleSetContributors()) {

					roleProvider.apply(
						roleSetContributorContextImpl, model, entryClassPK);

					//searchPermissionFields = null;
				}
			}
			catch (PortalException portalException) {
				if (_log.isDebugEnabled()) {
					_log.debug(portalException, portalException);
				}
			}
		}

		return roleSetContributorContextImpl;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SearchPermissionFieldsFactory.class);

	@Reference
	private ResourcePermissionLocalService _resourcePermissionLocalService;

	@Reference
	private RoleLocalService _roleLocalService;

	private ServiceTrackerMap<String, SearchPermissionDefinition<?>>
		_serviceTrackerMap;

}