/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.service;

/**
 * Provides a wrapper for {@link ResourceBlockService}.
 *
 * @author Brian Wing Shun Chan
 * @see ResourceBlockService
 * @deprecated
 * @generated
 */
@Deprecated
public class ResourceBlockServiceWrapper
	implements ResourceBlockService, ServiceWrapper<ResourceBlockService> {

	public ResourceBlockServiceWrapper(
		ResourceBlockService resourceBlockService) {

		_resourceBlockService = resourceBlockService;
	}

	@Override
	public void addCompanyScopePermission(
			long scopeGroupId, long companyId, java.lang.String name,
			long roleId, java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_resourceBlockService.addCompanyScopePermission(
			scopeGroupId, companyId, name, roleId, actionId);
	}

	@Override
	public void addGroupScopePermission(
			long scopeGroupId, long companyId, long groupId,
			java.lang.String name, long roleId, java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_resourceBlockService.addGroupScopePermission(
			scopeGroupId, companyId, groupId, name, roleId, actionId);
	}

	@Override
	public void addIndividualScopePermission(
			long companyId, long groupId, java.lang.String name, long primKey,
			long roleId, java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_resourceBlockService.addIndividualScopePermission(
			companyId, groupId, name, primKey, roleId, actionId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _resourceBlockService.getOSGiServiceIdentifier();
	}

	@Override
	public void removeAllGroupScopePermissions(
			long scopeGroupId, long companyId, java.lang.String name,
			long roleId, java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_resourceBlockService.removeAllGroupScopePermissions(
			scopeGroupId, companyId, name, roleId, actionId);
	}

	@Override
	public void removeCompanyScopePermission(
			long scopeGroupId, long companyId, java.lang.String name,
			long roleId, java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_resourceBlockService.removeCompanyScopePermission(
			scopeGroupId, companyId, name, roleId, actionId);
	}

	@Override
	public void removeGroupScopePermission(
			long scopeGroupId, long companyId, long groupId,
			java.lang.String name, long roleId, java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_resourceBlockService.removeGroupScopePermission(
			scopeGroupId, companyId, groupId, name, roleId, actionId);
	}

	@Override
	public void removeIndividualScopePermission(
			long companyId, long groupId, java.lang.String name, long primKey,
			long roleId, java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_resourceBlockService.removeIndividualScopePermission(
			companyId, groupId, name, primKey, roleId, actionId);
	}

	@Override
	public void setCompanyScopePermissions(
			long scopeGroupId, long companyId, java.lang.String name,
			long roleId, java.util.List<java.lang.String> actionIds)
		throws com.liferay.portal.kernel.exception.PortalException {

		_resourceBlockService.setCompanyScopePermissions(
			scopeGroupId, companyId, name, roleId, actionIds);
	}

	@Override
	public void setGroupScopePermissions(
			long scopeGroupId, long companyId, long groupId,
			java.lang.String name, long roleId,
			java.util.List<java.lang.String> actionIds)
		throws com.liferay.portal.kernel.exception.PortalException {

		_resourceBlockService.setGroupScopePermissions(
			scopeGroupId, companyId, groupId, name, roleId, actionIds);
	}

	@Override
	public void setIndividualScopePermissions(
			long companyId, long groupId, java.lang.String name, long primKey,
			long roleId, java.util.List<java.lang.String> actionIds)
		throws com.liferay.portal.kernel.exception.PortalException {

		_resourceBlockService.setIndividualScopePermissions(
			companyId, groupId, name, primKey, roleId, actionIds);
	}

	@Override
	public void setIndividualScopePermissions(
			long companyId, long groupId, java.lang.String name, long primKey,
			java.util.Map<java.lang.Long, java.lang.String[]>
				roleIdsToActionIds)
		throws com.liferay.portal.kernel.exception.PortalException {

		_resourceBlockService.setIndividualScopePermissions(
			companyId, groupId, name, primKey, roleIdsToActionIds);
	}

	@Override
	public ResourceBlockService getWrappedService() {
		return _resourceBlockService;
	}

	@Override
	public void setWrappedService(ResourceBlockService resourceBlockService) {
		_resourceBlockService = resourceBlockService;
	}

	private ResourceBlockService _resourceBlockService;

}