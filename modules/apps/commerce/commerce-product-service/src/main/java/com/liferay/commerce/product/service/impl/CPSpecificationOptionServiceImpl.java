/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.service.impl;

import com.liferay.commerce.product.constants.CPActionKeys;
import com.liferay.commerce.product.model.CPSpecificationOption;
import com.liferay.commerce.product.service.base.CPSpecificationOptionServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.permission.PortalPermissionUtil;

import java.util.Locale;
import java.util.Map;

/**
 * @author Andrea Di Giorgi
 */
public class CPSpecificationOptionServiceImpl
	extends CPSpecificationOptionServiceBaseImpl {

	@Override
	public CPSpecificationOption addCPSpecificationOption(
			long cpOptionCategoryId, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, boolean facetable, String key,
			ServiceContext serviceContext)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CPActionKeys.ADD_COMMERCE_PRODUCT_SPECIFICATION_OPTION);

		return cpSpecificationOptionLocalService.addCPSpecificationOption(
			getUserId(), cpOptionCategoryId, titleMap, descriptionMap,
			facetable, key, serviceContext);
	}

	@Override
	public void deleteCPSpecificationOption(long cpSpecificationOptionId)
		throws PortalException {

		_cpSpecificationOptionModelResourcePermission.check(
			getPermissionChecker(), cpSpecificationOptionId, ActionKeys.DELETE);

		cpSpecificationOptionLocalService.deleteCPSpecificationOption(
			cpSpecificationOptionId);
	}

	@Override
	public CPSpecificationOption fetchCPSpecificationOption(
			long companyId, String key)
		throws PortalException {

		CPSpecificationOption cpSpecificationOption =
			cpSpecificationOptionLocalService.fetchCPSpecificationOption(
				companyId, key);

		if (cpSpecificationOption != null) {
			_cpSpecificationOptionModelResourcePermission.check(
				getPermissionChecker(), cpSpecificationOption, ActionKeys.VIEW);
		}

		return cpSpecificationOption;
	}

	@Override
	public CPSpecificationOption getCPSpecificationOption(
			long cpSpecificationOptionId)
		throws PortalException {

		_cpSpecificationOptionModelResourcePermission.check(
			getPermissionChecker(), cpSpecificationOptionId, ActionKeys.VIEW);

		return cpSpecificationOptionLocalService.getCPSpecificationOption(
			cpSpecificationOptionId);
	}

	@Override
	public BaseModelSearchResult<CPSpecificationOption>
			searchCPSpecificationOptions(
				long companyId, Boolean facetable, String keywords, int start,
				int end, Sort sort)
		throws PortalException {

		return cpSpecificationOptionLocalService.searchCPSpecificationOptions(
			companyId, facetable, keywords, start, end, sort);
	}

	@Override
	public CPSpecificationOption updateCPSpecificationOption(
			long cpSpecificationOptionId, long cpOptionCategoryId,
			Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
			boolean facetable, String key, ServiceContext serviceContext)
		throws PortalException {

		_cpSpecificationOptionModelResourcePermission.check(
			getPermissionChecker(), cpSpecificationOptionId, ActionKeys.UPDATE);

		return cpSpecificationOptionLocalService.updateCPSpecificationOption(
			cpSpecificationOptionId, cpOptionCategoryId, titleMap,
			descriptionMap, facetable, key, serviceContext);
	}

	private static volatile ModelResourcePermission<CPSpecificationOption>
		_cpSpecificationOptionModelResourcePermission =
			ModelResourcePermissionFactory.getInstance(
				CPSpecificationOptionServiceImpl.class,
				"_cpSpecificationOptionModelResourcePermission",
				CPSpecificationOption.class);

}