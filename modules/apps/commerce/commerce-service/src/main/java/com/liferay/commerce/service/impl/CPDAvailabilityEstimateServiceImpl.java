/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.service.impl;

import com.liferay.commerce.model.CPDAvailabilityEstimate;
import com.liferay.commerce.product.exception.NoSuchCPDefinitionException;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.service.CPDefinitionLocalService;
import com.liferay.commerce.product.service.CommerceCatalogLocalService;
import com.liferay.commerce.service.base.CPDAvailabilityEstimateServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.spring.extender.service.ServiceReference;

/**
 * @author Alessio Antonio Rendina
 */
public class CPDAvailabilityEstimateServiceImpl
	extends CPDAvailabilityEstimateServiceBaseImpl {

	@Override
	public CPDAvailabilityEstimate fetchCPDAvailabilityEstimateByCPDefinitionId(
			long cpDefinitionId)
		throws PortalException {

		_checkCommerceCatalogPermissionByCPDefinitionId(
			cpDefinitionId, ActionKeys.VIEW);

		return cpdAvailabilityEstimateLocalService.
			fetchCPDAvailabilityEstimateByCPDefinitionId(cpDefinitionId);
	}

	@Override
	public CPDAvailabilityEstimate updateCPDAvailabilityEstimate(
			long cpdAvailabilityEstimateId, long cpDefinitionId,
			long commerceAvailabilityEstimateId, ServiceContext serviceContext)
		throws PortalException {

		_checkCommerceCatalogPermissionByCPDefinitionId(
			cpDefinitionId, ActionKeys.VIEW);

		return cpdAvailabilityEstimateLocalService.
			updateCPDAvailabilityEstimate(
				cpdAvailabilityEstimateId, cpDefinitionId,
				commerceAvailabilityEstimateId, serviceContext);
	}

	@ServiceReference(type = CommerceCatalogLocalService.class)
	protected CommerceCatalogLocalService commerceCatalogLocalService;

	@ServiceReference(type = CPDefinitionLocalService.class)
	protected CPDefinitionLocalService cpDefinitionLocalService;

	private void _checkCommerceCatalogPermissionByCPDefinitionId(
			long cpDefinitionId, String actionId)
		throws PortalException {

		CPDefinition cpDefinition = cpDefinitionLocalService.fetchCPDefinition(
			cpDefinitionId);

		if (cpDefinition == null) {
			throw new NoSuchCPDefinitionException();
		}

		CommerceCatalog commerceCatalog =
			commerceCatalogLocalService.fetchCommerceCatalogByGroupId(
				cpDefinition.getGroupId());

		if (commerceCatalog == null) {
			throw new PrincipalException();
		}

		_commerceCatalogModelResourcePermission.check(
			getPermissionChecker(), commerceCatalog, actionId);
	}

	private static volatile ModelResourcePermission<CommerceCatalog>
		_commerceCatalogModelResourcePermission =
			ModelResourcePermissionFactory.getInstance(
				CPDAvailabilityEstimateServiceImpl.class,
				"_commerceCatalogModelResourcePermission",
				CommerceCatalog.class);

}