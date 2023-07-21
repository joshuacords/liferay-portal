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
import com.liferay.osb.koroneiki.trunk.exception.NoSuchProductConsumptionException;
import com.liferay.osb.koroneiki.trunk.model.ProductConsumption;
import com.liferay.osb.koroneiki.trunk.model.ProductEntry;
import com.liferay.osb.koroneiki.trunk.model.ProductField;
import com.liferay.osb.koroneiki.trunk.model.ProductPurchase;
import com.liferay.osb.koroneiki.trunk.permission.ProductConsumptionPermission;
import com.liferay.osb.koroneiki.trunk.permission.ProductEntryPermission;
import com.liferay.osb.koroneiki.trunk.service.ProductEntryLocalService;
import com.liferay.osb.koroneiki.trunk.service.ProductPurchaseLocalService;
import com.liferay.osb.koroneiki.trunk.service.base.ProductConsumptionServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.util.Validator;

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
		"json.web.service.context.path=ProductConsumption"
	},
	service = AopService.class
)
public class ProductConsumptionServiceImpl
	extends ProductConsumptionServiceBaseImpl {

	public ProductConsumption addProductConsumption(
			long accountId, long productEntryId, long productPurchaseId,
			Date startDate, Date endDate, List<ProductField> productFields)
		throws PortalException {

		_productEntryPermission.check(
			getPermissionChecker(), productEntryId, TrunkActionKeys.CONSUME);

		return productConsumptionLocalService.addProductConsumption(
			getUserId(), accountId, productEntryId, productPurchaseId,
			startDate, endDate, productFields);
	}

	public ProductConsumption addProductConsumption(
			String accountKey, String productEntryKey,
			String productPurchaseKey, Date startDate, Date endDate,
			List<ProductField> productFields)
		throws PortalException {

		ProductEntry productEntry = _productEntryLocalService.getProductEntry(
			productEntryKey);

		_productEntryPermission.check(
			getPermissionChecker(), productEntry, TrunkActionKeys.CONSUME);

		Account account = _accountLocalService.getAccount(accountKey);

		long productPurchaseId = 0;

		if (Validator.isNotNull(productPurchaseKey)) {
			ProductPurchase productPurchase =
				_productPurchaseLocalService.getProductPurchase(
					productPurchaseKey);

			productPurchaseId = productPurchase.getProductPurchaseId();
		}

		return productConsumptionLocalService.addProductConsumption(
			getUserId(), account.getAccountId(),
			productEntry.getProductEntryId(), productPurchaseId, startDate,
			endDate, productFields);
	}

	public ProductConsumption deleteProductConsumption(
			long productConsumptionId)
		throws PortalException {

		_productConsumptionPermission.check(
			getPermissionChecker(), productConsumptionId, ActionKeys.DELETE);

		return productConsumptionLocalService.deleteProductConsumption(
			productConsumptionId);
	}

	public ProductConsumption deleteProductConsumption(
			long accountId, long productEntryId)
		throws PortalException {

		List<ProductConsumption> productConsumptions =
			productConsumptionLocalService.getProductConsumptions(
				getUserId(), accountId, productEntryId);

		if (productConsumptions.isEmpty()) {
			throw new NoSuchProductConsumptionException();
		}

		ProductConsumption productConsumption = productConsumptions.get(0);

		_productConsumptionPermission.check(
			getPermissionChecker(), productConsumption, ActionKeys.DELETE);

		return productConsumptionLocalService.deleteProductConsumption(
			productConsumption.getProductConsumptionId());
	}

	public ProductConsumption deleteProductConsumption(
			String productConsumptionKey)
		throws PortalException {

		ProductConsumption productConsumption =
			productConsumptionLocalService.getProductConsumption(
				productConsumptionKey);

		_productConsumptionPermission.check(
			getPermissionChecker(), productConsumption, ActionKeys.DELETE);

		return productConsumptionLocalService.deleteProductConsumption(
			productConsumption);
	}

	public List<ProductConsumption> getAccountProductConsumptions(
			long accountId, int start, int end)
		throws PortalException {

		_accountPermission.check(
			getPermissionChecker(), accountId, ActionKeys.VIEW);

		return productConsumptionLocalService.getAccountProductConsumptions(
			accountId, start, end);
	}

	public List<ProductConsumption> getAccountProductConsumptions(
			String accountKey, int start, int end)
		throws PortalException {

		Account account = _accountLocalService.getAccount(accountKey);

		_accountPermission.check(
			getPermissionChecker(), account, ActionKeys.VIEW);

		return productConsumptionLocalService.getAccountProductConsumptions(
			account.getAccountId(), start, end);
	}

	public int getAccountProductConsumptionsCount(long accountId)
		throws PortalException {

		_accountPermission.check(
			getPermissionChecker(), accountId, ActionKeys.VIEW);

		return productConsumptionLocalService.
			getAccountProductConsumptionsCount(accountId);
	}

	public int getAccountProductConsumptionsCount(String accountKey)
		throws PortalException {

		Account account = _accountLocalService.getAccount(accountKey);

		_accountPermission.check(
			getPermissionChecker(), account, ActionKeys.VIEW);

		return productConsumptionLocalService.
			getAccountProductConsumptionsCount(account.getAccountId());
	}

	public List<ProductConsumption> getAccountProductEntryProductConsumptions(
			long accountId, long productEntryId)
		throws PortalException {

		_accountPermission.check(
			getPermissionChecker(), accountId, ActionKeys.VIEW);

		return productConsumptionLocalService.
			getAccountProductEntryProductConsumptions(
				accountId, productEntryId);
	}

	public List<ProductConsumption> getContactProductConsumptions(
			long contactId, int start, int end)
		throws PortalException {

		_contactPermission.check(
			getPermissionChecker(), contactId, ActionKeys.VIEW);

		return productConsumptionLocalService.getContactProductConsumptions(
			contactId, start, end);
	}

	public int getContactProductConsumptionsCount(long contactId)
		throws PortalException {

		_contactPermission.check(
			getPermissionChecker(), contactId, ActionKeys.VIEW);

		return productConsumptionLocalService.
			getContactProductConsumptionsCount(contactId);
	}

	public ProductConsumption getProductConsumption(long productConsumptionId)
		throws PortalException {

		_productConsumptionPermission.check(
			getPermissionChecker(), productConsumptionId, ActionKeys.VIEW);

		return productConsumptionLocalService.getProductConsumption(
			productConsumptionId);
	}

	public ProductConsumption getProductConsumption(
			String productConsumptionKey)
		throws PortalException {

		ProductConsumption productConsumption =
			productConsumptionLocalService.getProductConsumption(
				productConsumptionKey);

		_productConsumptionPermission.check(
			getPermissionChecker(), productConsumption, ActionKeys.VIEW);

		return productConsumption;
	}

	public List<ProductConsumption> getProductConsumptions(
			String domain, String entityName, String entityId, int start,
			int end)
		throws PortalException {

		List<ProductConsumption> productConsumptions = new ArrayList<>();

		List<ExternalLink> externalLinks =
			_externalLinkLocalService.getExternalLinks(
				classNameLocalService.getClassNameId(ProductConsumption.class),
				domain, entityName, entityId, start, end);

		for (ExternalLink externalLink : externalLinks) {
			productConsumptions.add(
				getProductConsumption(externalLink.getClassPK()));
		}

		return productConsumptions;
	}

	public int getProductConsumptionsCount(
			String domain, String entityName, String entityId)
		throws PortalException {

		return _externalLinkLocalService.getExternalLinksCount(
			classNameLocalService.getClassNameId(ProductConsumption.class),
			domain, entityName, entityId);
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
	private ProductConsumptionPermission _productConsumptionPermission;

	@Reference
	private ProductEntryLocalService _productEntryLocalService;

	@Reference
	private ProductEntryPermission _productEntryPermission;

	@Reference
	private ProductPurchaseLocalService _productPurchaseLocalService;

}