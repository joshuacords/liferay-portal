/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.service.impl;

import com.liferay.commerce.product.constants.CPActionKeys;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.service.base.CommerceCatalogServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.permission.PortalPermissionUtil;

import java.util.List;

/**
 * @author Alec Sloan
 * @author Alessio Antonio Rendina
 */
public class CommerceCatalogServiceImpl extends CommerceCatalogServiceBaseImpl {

	@Override
	public CommerceCatalog addCommerceCatalog(
			String name, String commerceCurrencyCode,
			String catalogDefaultLanguageId, String externalReferenceCode,
			ServiceContext serviceContext)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(), CPActionKeys.ADD_COMMERCE_CATALOG);

		return commerceCatalogLocalService.addCommerceCatalog(
			name, commerceCurrencyCode, catalogDefaultLanguageId,
			externalReferenceCode, serviceContext);
	}

	@Override
	public CommerceCatalog deleteCommerceCatalog(long commerceCatalogId)
		throws PortalException {

		_commerceCatalogModelResourcePermission.check(
			getPermissionChecker(), commerceCatalogId, ActionKeys.DELETE);

		return commerceCatalogLocalService.deleteCommerceCatalog(
			commerceCatalogId);
	}

	@Override
	public CommerceCatalog fetchByExternalReferenceCode(
			long companyId, String externalReferenceCode)
		throws PortalException {

		CommerceCatalog commerceCatalog =
			commerceCatalogLocalService.fetchByExternalReferenceCode(
				companyId, externalReferenceCode);

		if (commerceCatalog != null) {
			_commerceCatalogModelResourcePermission.check(
				getPermissionChecker(), commerceCatalog, ActionKeys.VIEW);
		}

		return commerceCatalog;
	}

	@Override
	public CommerceCatalog fetchCommerceCatalog(long commerceCatalogId)
		throws PortalException {

		CommerceCatalog commerceCatalog =
			commerceCatalogLocalService.fetchCommerceCatalog(commerceCatalogId);

		if (commerceCatalog != null) {
			_commerceCatalogModelResourcePermission.check(
				getPermissionChecker(), commerceCatalog, ActionKeys.VIEW);
		}

		return commerceCatalog;
	}

	@Override
	public CommerceCatalog fetchCommerceCatalogByGroupId(long groupId)
		throws PortalException {

		CommerceCatalog commerceCatalog =
			commerceCatalogLocalService.fetchCommerceCatalogByGroupId(groupId);

		if (commerceCatalog != null) {
			_commerceCatalogModelResourcePermission.check(
				getPermissionChecker(), commerceCatalog, ActionKeys.VIEW);
		}

		return commerceCatalog;
	}

	@Override
	public CommerceCatalog getCommerceCatalog(long commerceCatalogId)
		throws PortalException {

		_commerceCatalogModelResourcePermission.check(
			getPermissionChecker(), commerceCatalogId, ActionKeys.VIEW);

		return commerceCatalogLocalService.getCommerceCatalog(
			commerceCatalogId);
	}

	@Override
	public List<CommerceCatalog> getCommerceCatalogs(
		long companyId, int start, int end) {

		return commerceCatalogPersistence.filterFindByCompanyId(
			companyId, start, end);
	}

	@Override
	public List<CommerceCatalog> searchCommerceCatalogs(
			long companyId, String keywords, int start, int end, Sort sort)
		throws PortalException {

		return commerceCatalogLocalService.searchCommerceCatalogs(
			companyId, keywords, start, end, sort);
	}

	@Override
	public int searchCommerceCatalogsCount(long companyId, String keywords)
		throws PortalException {

		return commerceCatalogLocalService.searchCommerceCatalogsCount(
			companyId, keywords);
	}

	@Override
	public CommerceCatalog updateCommerceCatalog(
			long commerceCatalogId, String name, String commerceCurrencyCode,
			String catalogDefaultLanguageId)
		throws PortalException {

		_commerceCatalogModelResourcePermission.check(
			getPermissionChecker(), commerceCatalogId, ActionKeys.UPDATE);

		return commerceCatalogLocalService.updateCommerceCatalog(
			commerceCatalogId, name, commerceCurrencyCode,
			catalogDefaultLanguageId);
	}

	@Override
	public CommerceCatalog updateCommerceCatalogExternalReferenceCode(
			long commerceCatalogId, String externalReferenceCode)
		throws PortalException {

		_commerceCatalogModelResourcePermission.check(
			getPermissionChecker(), commerceCatalogId, ActionKeys.UPDATE);

		return commerceCatalogLocalService.
			updateCommerceCatalogExternalReferenceCode(
				commerceCatalogId, externalReferenceCode);
	}

	private static volatile ModelResourcePermission<CommerceCatalog>
		_commerceCatalogModelResourcePermission =
			ModelResourcePermissionFactory.getInstance(
				CommerceCatalogServiceImpl.class,
				"_commerceCatalogModelResourcePermission",
				CommerceCatalog.class);

}