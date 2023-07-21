/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.service.impl;

import com.liferay.commerce.product.constants.CPActionKeys;
import com.liferay.commerce.product.model.CPOption;
import com.liferay.commerce.product.service.base.CPOptionServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.permission.PortalPermissionUtil;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Marco Leo
 */
public class CPOptionServiceImpl extends CPOptionServiceBaseImpl {

	@Override
	public CPOption addCPOption(
			Map<Locale, String> nameMap, Map<Locale, String> descriptionMap,
			String ddmFormFieldTypeName, boolean facetable, boolean required,
			boolean skuContributor, String key, ServiceContext serviceContext)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(), CPActionKeys.ADD_COMMERCE_PRODUCT_OPTION);

		return cpOptionLocalService.addCPOption(
			getUserId(), nameMap, descriptionMap, ddmFormFieldTypeName,
			facetable, required, skuContributor, key, null, serviceContext);
	}

	@Override
	public void deleteCPOption(long cpOptionId) throws PortalException {
		_cpOptionModelResourcePermission.check(
			getPermissionChecker(), cpOptionId, ActionKeys.DELETE);

		cpOptionLocalService.deleteCPOption(cpOptionId);
	}

	@Override
	public CPOption fetchByExternalReferenceCode(
			long companyId, String externalReferenceCode)
		throws PortalException {

		CPOption cpOption = cpOptionLocalService.fetchByExternalReferenceCode(
			companyId, externalReferenceCode);

		if (cpOption != null) {
			_cpOptionModelResourcePermission.check(
				getPermissionChecker(), cpOption, ActionKeys.VIEW);
		}

		return cpOption;
	}

	@Override
	public CPOption fetchCPOption(long cpOptionId) throws PortalException {
		CPOption cpOption = cpOptionLocalService.fetchCPOption(cpOptionId);

		if (cpOption != null) {
			_cpOptionModelResourcePermission.check(
				getPermissionChecker(), cpOption, ActionKeys.VIEW);
		}

		return cpOption;
	}

	@Override
	public CPOption fetchCPOption(long companyId, String key)
		throws PortalException {

		CPOption cpOption = cpOptionLocalService.fetchCPOption(companyId, key);

		if (cpOption != null) {
			_cpOptionModelResourcePermission.check(
				getPermissionChecker(), cpOption, ActionKeys.VIEW);
		}

		return cpOption;
	}

	@Override
	public List<CPOption> findCPOptionByCompanyId(
			long companyId, int start, int end,
			OrderByComparator<CPOption> orderByComparator)
		throws PortalException {

		return cpOptionLocalService.findCPOptionByCompanyId(
			companyId, start, end, orderByComparator);
	}

	@Override
	public CPOption getCPOption(long cpOptionId) throws PortalException {
		_cpOptionModelResourcePermission.check(
			getPermissionChecker(), cpOptionId, ActionKeys.VIEW);

		return cpOptionLocalService.getCPOption(cpOptionId);
	}

	@Override
	public BaseModelSearchResult<CPOption> searchCPOptions(
			long companyId, String keywords, int start, int end, Sort sort)
		throws PortalException {

		return cpOptionLocalService.searchCPOptions(
			companyId, keywords, start, end, sort);
	}

	@Override
	public CPOption updateCPOption(
			long cpOptionId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, String ddmFormFieldTypeName,
			boolean facetable, boolean required, boolean skuContributor,
			String key, ServiceContext serviceContext)
		throws PortalException {

		_cpOptionModelResourcePermission.check(
			getPermissionChecker(), cpOptionId, ActionKeys.UPDATE);

		return cpOptionLocalService.updateCPOption(
			cpOptionId, nameMap, descriptionMap, ddmFormFieldTypeName,
			facetable, required, skuContributor, key, serviceContext);
	}

	@Override
	public CPOption upsertCPOption(
			Map<Locale, String> nameMap, Map<Locale, String> descriptionMap,
			String ddmFormFieldTypeName, boolean facetable, boolean required,
			boolean skuContributor, String key, String externalReferenceCode,
			ServiceContext serviceContext)
		throws PortalException {

		CPOption cpOption = cpOptionLocalService.fetchByExternalReferenceCode(
			serviceContext.getCompanyId(), externalReferenceCode);

		if (cpOption == null) {
			PortalPermissionUtil.check(
				getPermissionChecker(),
				CPActionKeys.ADD_COMMERCE_PRODUCT_OPTION);
		}

		return cpOptionLocalService.upsertCPOption(
			getUserId(), nameMap, descriptionMap, ddmFormFieldTypeName,
			facetable, required, skuContributor, key, externalReferenceCode,
			serviceContext);
	}

	private static volatile ModelResourcePermission<CPOption>
		_cpOptionModelResourcePermission =
			ModelResourcePermissionFactory.getInstance(
				CPOptionServiceImpl.class, "_cpOptionModelResourcePermission",
				CPOption.class);

}