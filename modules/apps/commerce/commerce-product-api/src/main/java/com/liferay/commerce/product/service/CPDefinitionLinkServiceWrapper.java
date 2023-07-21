/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CPDefinitionLinkService}.
 *
 * @author Marco Leo
 * @see CPDefinitionLinkService
 * @generated
 */
public class CPDefinitionLinkServiceWrapper
	implements CPDefinitionLinkService,
			   ServiceWrapper<CPDefinitionLinkService> {

	public CPDefinitionLinkServiceWrapper(
		CPDefinitionLinkService cpDefinitionLinkService) {

		_cpDefinitionLinkService = cpDefinitionLinkService;
	}

	@Override
	public com.liferay.commerce.product.model.CPDefinitionLink
			addCPDefinitionLink(
				long cpDefinitionId, long cProductId, double priority,
				String type,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionLinkService.addCPDefinitionLink(
			cpDefinitionId, cProductId, priority, type, serviceContext);
	}

	@Override
	public void deleteCPDefinitionLink(long cpDefinitionLinkId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_cpDefinitionLinkService.deleteCPDefinitionLink(cpDefinitionLinkId);
	}

	@Override
	public com.liferay.commerce.product.model.CPDefinitionLink
			fetchCPDefinitionLink(long cpDefinitionLinkId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionLinkService.fetchCPDefinitionLink(
			cpDefinitionLinkId);
	}

	@Override
	public com.liferay.commerce.product.model.CPDefinitionLink
			getCPDefinitionLink(long cpDefinitionLinkId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionLinkService.getCPDefinitionLink(cpDefinitionLinkId);
	}

	@Override
	public java.util.List<com.liferay.commerce.product.model.CPDefinitionLink>
			getCPDefinitionLinks(long cpDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionLinkService.getCPDefinitionLinks(cpDefinitionId);
	}

	@Override
	public java.util.List<com.liferay.commerce.product.model.CPDefinitionLink>
			getCPDefinitionLinks(long cpDefinitionId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionLinkService.getCPDefinitionLinks(
			cpDefinitionId, start, end);
	}

	@Override
	public java.util.List<com.liferay.commerce.product.model.CPDefinitionLink>
			getCPDefinitionLinks(long cpDefinitionId, String type)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionLinkService.getCPDefinitionLinks(
			cpDefinitionId, type);
	}

	@Override
	public java.util.List<com.liferay.commerce.product.model.CPDefinitionLink>
			getCPDefinitionLinks(
				long cpDefinitionId, String type, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.commerce.product.model.CPDefinitionLink>
						orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionLinkService.getCPDefinitionLinks(
			cpDefinitionId, type, start, end, orderByComparator);
	}

	@Override
	public int getCPDefinitionLinksCount(long cpDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionLinkService.getCPDefinitionLinksCount(
			cpDefinitionId);
	}

	@Override
	public int getCPDefinitionLinksCount(long cpDefinitionId, String type)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionLinkService.getCPDefinitionLinksCount(
			cpDefinitionId, type);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _cpDefinitionLinkService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.commerce.product.model.CPDefinitionLink
			updateCPDefinitionLink(
				long cpDefinitionLinkId, double priority,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionLinkService.updateCPDefinitionLink(
			cpDefinitionLinkId, priority, serviceContext);
	}

	@Override
	public void updateCPDefinitionLinks(
			long cpDefinitionId, long[] cpDefinitionIds2, String type,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		_cpDefinitionLinkService.updateCPDefinitionLinks(
			cpDefinitionId, cpDefinitionIds2, type, serviceContext);
	}

	@Override
	public CPDefinitionLinkService getWrappedService() {
		return _cpDefinitionLinkService;
	}

	@Override
	public void setWrappedService(
		CPDefinitionLinkService cpDefinitionLinkService) {

		_cpDefinitionLinkService = cpDefinitionLinkService;
	}

	private CPDefinitionLinkService _cpDefinitionLinkService;

}