/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.service.impl;

import com.liferay.commerce.constants.CommerceActionKeys;
import com.liferay.commerce.model.CommerceRegion;
import com.liferay.commerce.service.base.CommerceRegionServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.permission.PortalPermissionUtil;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * @author Alessio Antonio Rendina
 * @author Andrea Di Giorgi
 */
public class CommerceRegionServiceImpl extends CommerceRegionServiceBaseImpl {

	@Override
	public CommerceRegion addCommerceRegion(
			long commerceCountryId, String name, String code, double priority,
			boolean active, ServiceContext serviceContext)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CommerceActionKeys.MANAGE_COMMERCE_COUNTRIES);

		return commerceRegionLocalService.addCommerceRegion(
			commerceCountryId, name, code, priority, active, serviceContext);
	}

	@Override
	public void deleteCommerceRegion(long commerceRegionId)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CommerceActionKeys.MANAGE_COMMERCE_COUNTRIES);

		commerceRegionLocalService.deleteCommerceRegion(commerceRegionId);
	}

	@Override
	public CommerceRegion getCommerceRegion(long commerceRegionId)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CommerceActionKeys.MANAGE_COMMERCE_COUNTRIES);

		return commerceRegionLocalService.getCommerceRegion(commerceRegionId);
	}

	@Override
	public List<CommerceRegion> getCommerceRegions(
		long commerceCountryId, boolean active) {

		return commerceRegionLocalService.getCommerceRegions(
			commerceCountryId, active);
	}

	@Override
	public List<CommerceRegion> getCommerceRegions(
			long commerceCountryId, boolean active, int start, int end,
			OrderByComparator<CommerceRegion> orderByComparator)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CommerceActionKeys.MANAGE_COMMERCE_COUNTRIES);

		return commerceRegionLocalService.getCommerceRegions(
			commerceCountryId, active, start, end, orderByComparator);
	}

	@Override
	public List<CommerceRegion> getCommerceRegions(
			long commerceCountryId, int start, int end,
			OrderByComparator<CommerceRegion> orderByComparator)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CommerceActionKeys.MANAGE_COMMERCE_COUNTRIES);

		return commerceRegionLocalService.getCommerceRegions(
			commerceCountryId, start, end, orderByComparator);
	}

	@Override
	public List<CommerceRegion> getCommerceRegions(
			long companyId, String countryTwoLettersISOCode, boolean active)
		throws PortalException {

		return commerceRegionLocalService.getCommerceRegions(
			companyId, countryTwoLettersISOCode, active);
	}

	@Override
	public int getCommerceRegionsCount(long commerceCountryId)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CommerceActionKeys.MANAGE_COMMERCE_COUNTRIES);

		return commerceRegionLocalService.getCommerceRegionsCount(
			commerceCountryId);
	}

	@Override
	public int getCommerceRegionsCount(long commerceCountryId, boolean active)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CommerceActionKeys.MANAGE_COMMERCE_COUNTRIES);

		return commerceRegionLocalService.getCommerceRegionsCount(
			commerceCountryId, active);
	}

	@Override
	public CommerceRegion setActive(long commerceRegionId, boolean active)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CommerceActionKeys.MANAGE_COMMERCE_COUNTRIES);

		return commerceRegionLocalService.setActive(commerceRegionId, active);
	}

	@Override
	public CommerceRegion updateCommerceRegion(
			long commerceRegionId, String name, String code, double priority,
			boolean active, ServiceContext serviceContext)
		throws PortalException {

		CommerceRegion commerceRegion =
			commerceRegionPersistence.findByPrimaryKey(commerceRegionId);

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CommerceActionKeys.MANAGE_COMMERCE_COUNTRIES);

		return commerceRegionLocalService.updateCommerceRegion(
			commerceRegion.getCommerceRegionId(), name, code, priority, active,
			serviceContext);
	}

}