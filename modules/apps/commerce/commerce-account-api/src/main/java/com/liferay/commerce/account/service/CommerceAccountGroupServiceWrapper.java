/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.account.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceAccountGroupService}.
 *
 * @author Marco Leo
 * @see CommerceAccountGroupService
 * @generated
 */
public class CommerceAccountGroupServiceWrapper
	implements CommerceAccountGroupService,
			   ServiceWrapper<CommerceAccountGroupService> {

	public CommerceAccountGroupServiceWrapper(
		CommerceAccountGroupService commerceAccountGroupService) {

		_commerceAccountGroupService = commerceAccountGroupService;
	}

	@Override
	public com.liferay.commerce.account.model.CommerceAccountGroup
			addCommerceAccountGroup(
				long companyId, String name, int type,
				String externalReferenceCode,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceAccountGroupService.addCommerceAccountGroup(
			companyId, name, type, externalReferenceCode, serviceContext);
	}

	@Override
	public void deleteCommerceAccountGroup(long commerceAccountGroupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_commerceAccountGroupService.deleteCommerceAccountGroup(
			commerceAccountGroupId);
	}

	@Override
	public com.liferay.commerce.account.model.CommerceAccountGroup
			fetchByExternalReferenceCode(
				long companyId, String externalReferenceCode)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceAccountGroupService.fetchByExternalReferenceCode(
			companyId, externalReferenceCode);
	}

	@Override
	public com.liferay.commerce.account.model.CommerceAccountGroup
			getCommerceAccountGroup(long commerceAccountGroupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceAccountGroupService.getCommerceAccountGroup(
			commerceAccountGroupId);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.account.model.CommerceAccountGroup>
				getCommerceAccountGroups(
					long companyId, int start, int end,
					com.liferay.portal.kernel.util.OrderByComparator
						<com.liferay.commerce.account.model.
							CommerceAccountGroup> orderByComparator)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceAccountGroupService.getCommerceAccountGroups(
			companyId, start, end, orderByComparator);
	}

	@Override
	public int getCommerceAccountGroupsCount(long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceAccountGroupService.getCommerceAccountGroupsCount(
			companyId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceAccountGroupService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List
		<com.liferay.commerce.account.model.CommerceAccountGroup>
				searchCommerceAccountGroups(
					long companyId, String keywords, int start, int end,
					com.liferay.portal.kernel.search.Sort sort)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceAccountGroupService.searchCommerceAccountGroups(
			companyId, keywords, start, end, sort);
	}

	@Override
	public int searchCommerceAccountsGroupCount(long companyId, String keywords)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceAccountGroupService.searchCommerceAccountsGroupCount(
			companyId, keywords);
	}

	@Override
	public com.liferay.commerce.account.model.CommerceAccountGroup
			updateCommerceAccountGroup(
				long commerceAccountGroupId, String name,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceAccountGroupService.updateCommerceAccountGroup(
			commerceAccountGroupId, name, serviceContext);
	}

	@Override
	public CommerceAccountGroupService getWrappedService() {
		return _commerceAccountGroupService;
	}

	@Override
	public void setWrappedService(
		CommerceAccountGroupService commerceAccountGroupService) {

		_commerceAccountGroupService = commerceAccountGroupService;
	}

	private CommerceAccountGroupService _commerceAccountGroupService;

}