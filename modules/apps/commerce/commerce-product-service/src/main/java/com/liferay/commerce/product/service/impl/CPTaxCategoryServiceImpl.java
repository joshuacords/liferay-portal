/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.service.impl;

import com.liferay.commerce.product.constants.CPActionKeys;
import com.liferay.commerce.product.model.CPTaxCategory;
import com.liferay.commerce.product.service.base.CPTaxCategoryServiceBaseImpl;
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
public class CPTaxCategoryServiceImpl extends CPTaxCategoryServiceBaseImpl {

	@Override
	public CPTaxCategory addCPTaxCategory(
			Map<Locale, String> nameMap, Map<Locale, String> descriptionMap,
			ServiceContext serviceContext)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CPActionKeys.MANAGE_COMMERCE_PRODUCT_TAX_CATEGORIES);

		return cpTaxCategoryLocalService.addCPTaxCategory(
			nameMap, descriptionMap, serviceContext);
	}

	@Override
	public int countCPTaxCategoriesByCompanyId(long companyId, String keyword)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CPActionKeys.MANAGE_COMMERCE_PRODUCT_TAX_CATEGORIES);

		return cpTaxCategoryLocalService.countCPTaxCategoriesByCompanyId(
			companyId, keyword);
	}

	@Override
	public void deleteCPTaxCategory(long cpTaxCategoryId)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CPActionKeys.MANAGE_COMMERCE_PRODUCT_TAX_CATEGORIES);

		cpTaxCategoryLocalService.deleteCPTaxCategory(cpTaxCategoryId);
	}

	@Override
	public List<CPTaxCategory> findCPTaxCategoriesByCompanyId(
			long companyId, String keyword, int start, int end)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CPActionKeys.MANAGE_COMMERCE_PRODUCT_TAX_CATEGORIES);

		return cpTaxCategoryLocalService.findCPTaxCategoriesByCompanyId(
			companyId, keyword, start, end);
	}

	@Override
	public List<CPTaxCategory> getCPTaxCategories(long companyId)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CPActionKeys.MANAGE_COMMERCE_PRODUCT_TAX_CATEGORIES);

		return cpTaxCategoryLocalService.getCPTaxCategories(companyId);
	}

	@Override
	public List<CPTaxCategory> getCPTaxCategories(
			long companyId, int start, int end,
			OrderByComparator<CPTaxCategory> orderByComparator)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CPActionKeys.MANAGE_COMMERCE_PRODUCT_TAX_CATEGORIES);

		return cpTaxCategoryLocalService.getCPTaxCategories(
			companyId, start, end, orderByComparator);
	}

	@Override
	public int getCPTaxCategoriesCount(long companyId) throws PortalException {
		PortalPermissionUtil.check(
			getPermissionChecker(),
			CPActionKeys.MANAGE_COMMERCE_PRODUCT_TAX_CATEGORIES);

		return cpTaxCategoryLocalService.getCPTaxCategoriesCount(companyId);
	}

	@Override
	public CPTaxCategory getCPTaxCategory(long cpTaxCategoryId)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CPActionKeys.MANAGE_COMMERCE_PRODUCT_TAX_CATEGORIES);

		return cpTaxCategoryLocalService.getCPTaxCategory(cpTaxCategoryId);
	}

	@Override
	public CPTaxCategory updateCPTaxCategory(
			long cpTaxCategoryId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CPActionKeys.MANAGE_COMMERCE_PRODUCT_TAX_CATEGORIES);

		return cpTaxCategoryLocalService.updateCPTaxCategory(
			cpTaxCategoryId, nameMap, descriptionMap);
	}

}