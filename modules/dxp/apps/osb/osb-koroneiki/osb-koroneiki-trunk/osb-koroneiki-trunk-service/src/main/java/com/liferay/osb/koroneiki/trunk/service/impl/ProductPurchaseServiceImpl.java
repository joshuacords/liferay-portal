/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.service.impl;

import com.liferay.osb.koroneiki.root.model.ExternalLink;
import com.liferay.osb.koroneiki.root.service.ExternalLinkLocalService;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.permission.AccountPermission;
import com.liferay.osb.koroneiki.taproot.permission.ContactPermission;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactLocalService;
import com.liferay.osb.koroneiki.trunk.constants.TrunkActionKeys;
import com.liferay.osb.koroneiki.trunk.model.ProductEntry;
import com.liferay.osb.koroneiki.trunk.model.ProductField;
import com.liferay.osb.koroneiki.trunk.model.ProductPurchase;
import com.liferay.osb.koroneiki.trunk.permission.ProductPurchasePermission;
import com.liferay.osb.koroneiki.trunk.service.ProductEntryLocalService;
import com.liferay.osb.koroneiki.trunk.service.base.ProductPurchaseServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	property = {
		"json.web.service.context.name=koroneiki",
		"json.web.service.context.path=ProductPurchase"
	},
	service = AopService.class
)
public class ProductPurchaseServiceImpl extends ProductPurchaseServiceBaseImpl {

	public ProductPurchase addProductPurchase(
			long accountId, long productEntryId, Date startDate, Date endDate,
			Date originalEndDate, int quantity, int status,
			List<ProductField> productFields)
		throws PortalException {

		_productPurchasePermission.check(
			getPermissionChecker(), TrunkActionKeys.ADD_PRODUCT_PURCHASE);

		return productPurchaseLocalService.addProductPurchase(
			getUserId(), accountId, productEntryId, startDate, endDate,
			originalEndDate, quantity, status, productFields);
	}

	public ProductPurchase addProductPurchase(
			String accountKey, String productEntryKey, Date startDate,
			Date endDate, Date originalEndDate, int quantity, int status,
			List<ProductField> productFields)
		throws PortalException {

		Account account = _accountLocalService.getAccount(accountKey);
		ProductEntry productEntry = _productEntryLocalService.getProductEntry(
			productEntryKey);

		return addProductPurchase(
			account.getAccountId(), productEntry.getProductEntryId(), startDate,
			endDate, originalEndDate, quantity, status, productFields);
	}

	public ProductPurchase deleteProductPurchase(long productPurchaseId)
		throws PortalException {

		_productPurchasePermission.check(
			getPermissionChecker(), productPurchaseId, ActionKeys.DELETE);

		return productPurchaseLocalService.deleteProductPurchase(
			productPurchaseId);
	}

	public ProductPurchase deleteProductPurchase(String productPurchaseKey)
		throws PortalException {

		ProductPurchase productPurchase =
			productPurchaseLocalService.getProductPurchase(productPurchaseKey);

		_productPurchasePermission.check(
			getPermissionChecker(), productPurchase, ActionKeys.DELETE);

		return productPurchaseLocalService.deleteProductPurchase(
			productPurchase.getProductPurchaseId());
	}

	public List<ProductPurchase> getAccountProductEntryProductPurchases(
			long accountId, long productEntryId, int start, int end)
		throws PortalException {

		_accountPermission.check(
			getPermissionChecker(), accountId, ActionKeys.VIEW);

		return productPurchaseLocalService.
			getAccountProductEntryProductPurchases(
				accountId, productEntryId, start, end);
	}

	public List<ProductPurchase> getAccountProductPurchases(
			long accountId, int start, int end)
		throws PortalException {

		_accountPermission.check(
			getPermissionChecker(), accountId, ActionKeys.VIEW);

		return productPurchaseLocalService.getAccountProductPurchases(
			accountId, start, end);
	}

	public List<ProductPurchase> getAccountProductPurchases(
			String accountKey, int start, int end)
		throws PortalException {

		Account account = _accountLocalService.getAccount(accountKey);

		_accountPermission.check(
			getPermissionChecker(), account, ActionKeys.VIEW);

		return productPurchaseLocalService.getAccountProductPurchases(
			account.getAccountId(), start, end);
	}

	public int getAccountProductPurchasesCount(long accountId)
		throws PortalException {

		_accountPermission.check(
			getPermissionChecker(), accountId, ActionKeys.VIEW);

		return productPurchaseLocalService.getAccountProductPurchasesCount(
			accountId);
	}

	public int getAccountProductPurchasesCount(String accountKey)
		throws PortalException {

		Account account = _accountLocalService.getAccount(accountKey);

		_accountPermission.check(
			getPermissionChecker(), account, ActionKeys.VIEW);

		return productPurchaseLocalService.getAccountProductPurchasesCount(
			account.getAccountId());
	}

	public List<ProductPurchase> getContactProductPurchases(
			long contactId, int start, int end)
		throws PortalException {

		_contactPermission.check(
			getPermissionChecker(), contactId, ActionKeys.VIEW);

		return productPurchaseLocalService.getContactProductPurchases(
			contactId, start, end);
	}

	public int getContactProductPurchasesCount(long contactId)
		throws PortalException {

		_contactPermission.check(
			getPermissionChecker(), contactId, ActionKeys.VIEW);

		return productPurchaseLocalService.getContactProductPurchasesCount(
			contactId);
	}

	public ProductPurchase getProductPurchase(long productPurchaseId)
		throws PortalException {

		_productPurchasePermission.check(
			getPermissionChecker(), productPurchaseId, ActionKeys.VIEW);

		return productPurchaseLocalService.getProductPurchase(
			productPurchaseId);
	}

	public ProductPurchase getProductPurchase(String productPurchaseKey)
		throws PortalException {

		ProductPurchase productPurchase =
			productPurchaseLocalService.getProductPurchase(productPurchaseKey);

		_productPurchasePermission.check(
			getPermissionChecker(), productPurchase, ActionKeys.VIEW);

		return productPurchase;
	}

	public List<ProductPurchase> getProductPurchases(
			String domain, String entityName, String entityId, int start,
			int end)
		throws PortalException {

		List<ProductPurchase> productPurchases = new ArrayList<>();

		List<ExternalLink> externalLinks =
			_externalLinkLocalService.getExternalLinks(
				classNameLocalService.getClassNameId(ProductPurchase.class),
				domain, entityName, entityId, start, end);

		for (ExternalLink externalLink : externalLinks) {
			productPurchases.add(getProductPurchase(externalLink.getClassPK()));
		}

		return productPurchases;
	}

	public int getProductPurchasesCount(
			String domain, String entityName, String entityId)
		throws PortalException {

		return _externalLinkLocalService.getExternalLinksCount(
			classNameLocalService.getClassNameId(ProductPurchase.class), domain,
			entityName, entityId);
	}

	public ProductPurchase updateProductPurchase(
			long productPurchaseId, Date startDate, Date endDate,
			Date originalEndDate, int quantity, int status,
			List<ProductField> productFields)
		throws PortalException {

		_productPurchasePermission.check(
			getPermissionChecker(), productPurchaseId, ActionKeys.UPDATE);

		return productPurchaseLocalService.updateProductPurchase(
			getUserId(), productPurchaseId, startDate, endDate, originalEndDate,
			quantity, status, productFields);
	}

	@Reference
	private AccountLocalService _accountLocalService;

	@Reference
	private AccountPermission _accountPermission;

	@Reference
	private ContactLocalService _contactLocalService;

	@Reference
	private ContactPermission _contactPermission;

	@Reference
	private ExternalLinkLocalService _externalLinkLocalService;

	@Reference
	private ProductEntryLocalService _productEntryLocalService;

	@Reference
	private ProductPurchasePermission _productPurchasePermission;

}