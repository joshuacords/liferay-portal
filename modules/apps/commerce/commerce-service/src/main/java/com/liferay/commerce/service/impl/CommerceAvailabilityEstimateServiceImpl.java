/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.service.impl;

import com.liferay.commerce.constants.CommerceActionKeys;
import com.liferay.commerce.model.CommerceAvailabilityEstimate;
import com.liferay.commerce.service.base.CommerceAvailabilityEstimateServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.permission.PortalPermissionUtil;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceAvailabilityEstimateServiceImpl
	extends CommerceAvailabilityEstimateServiceBaseImpl {

	@Override
	public CommerceAvailabilityEstimate addCommerceAvailabilityEstimate(
			Map<Locale, String> titleMap, double priority,
			ServiceContext serviceContext)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CommerceActionKeys.MANAGE_COMMERCE_AVAILABILITY_ESTIMATES);

		return commerceAvailabilityEstimateLocalService.
			addCommerceAvailabilityEstimate(titleMap, priority, serviceContext);
	}

	@Override
	public void deleteCommerceAvailabilityEstimate(
			long commerceAvailabilityEstimateId)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CommerceActionKeys.MANAGE_COMMERCE_AVAILABILITY_ESTIMATES);

		commerceAvailabilityEstimateLocalService.
			deleteCommerceAvailabilityEstimate(commerceAvailabilityEstimateId);
	}

	@Override
	public CommerceAvailabilityEstimate getCommerceAvailabilityEstimate(
			long commerceAvailabilityEstimateId)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CommerceActionKeys.MANAGE_COMMERCE_AVAILABILITY_ESTIMATES);

		return commerceAvailabilityEstimateLocalService.
			getCommerceAvailabilityEstimate(commerceAvailabilityEstimateId);
	}

	@Override
	public List<CommerceAvailabilityEstimate> getCommerceAvailabilityEstimates(
			long companyId, int start, int end,
			OrderByComparator<CommerceAvailabilityEstimate> orderByComparator)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CommerceActionKeys.MANAGE_COMMERCE_AVAILABILITY_ESTIMATES);

		return commerceAvailabilityEstimateLocalService.
			getCommerceAvailabilityEstimates(
				companyId, start, end, orderByComparator);
	}

	@Override
	public int getCommerceAvailabilityEstimatesCount(long companyId)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CommerceActionKeys.MANAGE_COMMERCE_AVAILABILITY_ESTIMATES);

		return commerceAvailabilityEstimateLocalService.
			getCommerceAvailabilityEstimatesCount(companyId);
	}

	@Override
	public CommerceAvailabilityEstimate updateCommerceAvailabilityEstimate(
			long commerceAvailabilityEstimateId, Map<Locale, String> titleMap,
			double priority, ServiceContext serviceContext)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CommerceActionKeys.MANAGE_COMMERCE_AVAILABILITY_ESTIMATES);

		return commerceAvailabilityEstimateLocalService.
			updateCommerceAvailabilityEstimate(
				commerceAvailabilityEstimateId, titleMap, priority,
				serviceContext);
	}

}