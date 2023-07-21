/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.service.impl;

import com.liferay.osb.koroneiki.root.model.ExternalLink;
import com.liferay.osb.koroneiki.root.service.ExternalLinkLocalService;
import com.liferay.osb.koroneiki.taproot.permission.AccountPermission;
import com.liferay.osb.koroneiki.trunk.constants.TrunkActionKeys;
import com.liferay.osb.koroneiki.trunk.model.ProductEntry;
import com.liferay.osb.koroneiki.trunk.model.ProductField;
import com.liferay.osb.koroneiki.trunk.permission.ProductEntryPermission;
import com.liferay.osb.koroneiki.trunk.service.base.ProductEntryServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	property = {
		"json.web.service.context.name=koroneiki",
		"json.web.service.context.path=ProductEntry"
	},
	service = AopService.class
)
public class ProductEntryServiceImpl extends ProductEntryServiceBaseImpl {

	public ProductEntry addProductEntry(
			String name, List<ProductField> productFields)
		throws PortalException {

		_productEntryPermission.check(
			getPermissionChecker(), TrunkActionKeys.ADD_PRODUCT_ENTRY);

		return productEntryLocalService.addProductEntry(
			getUserId(), name, productFields);
	}

	@Override
	public ProductEntry deleteProductEntry(long productEntryId)
		throws PortalException {

		_productEntryPermission.check(
			getPermissionChecker(), productEntryId, ActionKeys.DELETE);

		return productEntryLocalService.deleteProductEntry(productEntryId);
	}

	@Override
	public ProductEntry deleteProductEntry(String productEntryKey)
		throws PortalException {

		ProductEntry productEntry = productEntryLocalService.getProductEntry(
			productEntryKey);

		_productEntryPermission.check(
			getPermissionChecker(), productEntry, ActionKeys.DELETE);

		return productEntryLocalService.deleteProductEntry(productEntry);
	}

	public List<ProductEntry> getProductEntries(int start, int end)
		throws PortalException {

		PermissionChecker permissionChecker = getPermissionChecker();

		if (!permissionChecker.isSignedIn()) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, ProductEntry.class.getName(), 0,
				ActionKeys.VIEW);
		}

		return productEntryLocalService.getProductEntries(start, end);
	}

	public List<ProductEntry> getProductEntries(
			String domain, String entityName, String entityId, int start,
			int end)
		throws PortalException {

		List<ProductEntry> productEntries = new ArrayList<>();

		List<ExternalLink> externalLinks =
			_externalLinkLocalService.getExternalLinks(
				classNameLocalService.getClassNameId(ProductEntry.class),
				domain, entityName, entityId, start, end);

		for (ExternalLink externalLink : externalLinks) {
			productEntries.add(getProductEntry(externalLink.getClassPK()));
		}

		return productEntries;
	}

	public int getProductEntriesCount() throws PortalException {
		PermissionChecker permissionChecker = getPermissionChecker();

		if (!permissionChecker.isSignedIn()) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, ProductEntry.class.getName(), 0,
				ActionKeys.VIEW);
		}

		return productEntryLocalService.getProductEntriesCount();
	}

	public int getProductEntriesCount(
			String domain, String entityName, String entityId)
		throws PortalException {

		return _externalLinkLocalService.getExternalLinksCount(
			classNameLocalService.getClassNameId(ProductEntry.class), domain,
			entityName, entityId);
	}

	public ProductEntry getProductEntry(long productEntryId)
		throws PortalException {

		_productEntryPermission.check(
			getPermissionChecker(), productEntryId, ActionKeys.VIEW);

		return productEntryLocalService.getProductEntry(productEntryId);
	}

	public ProductEntry getProductEntry(String productEntryKey)
		throws PortalException {

		ProductEntry productEntry = productEntryLocalService.getProductEntry(
			productEntryKey);

		_productEntryPermission.check(
			getPermissionChecker(), productEntry, ActionKeys.VIEW);

		return productEntry;
	}

	@Override
	public ProductEntry getProductEntryByName(String name)
		throws PortalException {

		ProductEntry productEntry =
			productEntryLocalService.getProductEntryByName(name);

		_productEntryPermission.check(
			getPermissionChecker(), productEntry, ActionKeys.VIEW);

		return productEntry;
	}

	public ProductEntry updateProductEntry(
			long productEntryId, String name, List<ProductField> productFields)
		throws PortalException {

		_productEntryPermission.check(
			getPermissionChecker(), productEntryId, ActionKeys.UPDATE);

		return productEntryLocalService.updateProductEntry(
			getUserId(), productEntryId, name, productFields);
	}

	public ProductEntry updateProductEntry(
			String productEntryKey, String name,
			List<ProductField> productFields)
		throws PortalException {

		ProductEntry productEntry = productEntryLocalService.getProductEntry(
			productEntryKey);

		_productEntryPermission.check(
			getPermissionChecker(), productEntry, ActionKeys.UPDATE);

		return productEntryLocalService.updateProductEntry(
			getUserId(), productEntry.getProductEntryId(), name, productFields);
	}

	@Reference
	private AccountPermission _accountPermission;

	@Reference
	private ExternalLinkLocalService _externalLinkLocalService;

	@Reference
	private ProductEntryPermission _productEntryPermission;

}