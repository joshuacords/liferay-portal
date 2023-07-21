/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ProductPurchaseView;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.util.ProductPurchaseViewUtil;
import com.liferay.osb.koroneiki.phloem.rest.internal.odata.entity.v1_0.ProductPurchaseViewEntityModel;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ProductPurchaseViewResource;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.service.AccountService;
import com.liferay.osb.koroneiki.trunk.model.ProductEntry;
import com.liferay.osb.koroneiki.trunk.service.ProductConsumptionService;
import com.liferay.osb.koroneiki.trunk.service.ProductEntryLocalService;
import com.liferay.osb.koroneiki.trunk.service.ProductEntryService;
import com.liferay.osb.koroneiki.trunk.service.ProductFieldLocalService;
import com.liferay.osb.koroneiki.trunk.service.ProductPurchaseService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.resource.EntityModelResource;
import com.liferay.portal.vulcan.util.SearchUtil;

import javax.ws.rs.core.MultivaluedMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Kyle Bischof
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/product-purchase-view.properties",
	scope = ServiceScope.PROTOTYPE, service = ProductPurchaseViewResource.class
)
public class ProductPurchaseViewResourceImpl
	extends BaseProductPurchaseViewResourceImpl implements EntityModelResource {

	@Override
	public ProductPurchaseView
			getAccountAccountKeyProductProductKeyProductPurchaseView(
				String accountKey, String productKey)
		throws Exception {

		Account account = _accountService.getAccount(accountKey);
		ProductEntry productEntry = _productEntryLocalService.getProductEntry(
			productKey);

		return ProductPurchaseViewUtil.toProductPurchaseView(
			productEntry,
			_productConsumptionService.
				getAccountProductEntryProductConsumptions(
					account.getAccountId(), productEntry.getProductEntryId()),
			_productPurchaseService.getAccountProductEntryProductPurchases(
				account.getAccountId(), productEntry.getProductEntryId(),
				QueryUtil.ALL_POS, QueryUtil.ALL_POS));
	}

	@Override
	public EntityModel getEntityModel(MultivaluedMap multivaluedMap) {
		long classNameId = _classNameLocalService.getClassNameId(
			ProductEntry.class);

		return new ProductPurchaseViewEntityModel(
			_productFieldLocalService.getProductFieldNames(classNameId));
	}

	@Override
	public Page<ProductPurchaseView> getProductPurchaseViewsPage(
			String search, Filter filter, Pagination pagination, Sort[] sorts)
		throws Exception {

		return SearchUtil.search(
			booleanQuery -> {
			},
			filter,
			com.liferay.osb.koroneiki.trunk.model.view.ProductPurchaseView.
				class,
			search, pagination,
			queryConfig -> queryConfig.setSelectedFieldNames(
				"accountId", "productEntryId"),
			searchContext -> searchContext.setCompanyId(
				contextCompany.getCompanyId()),
			document -> ProductPurchaseViewUtil.toProductPurchaseView(
				_productEntryLocalService.getProductEntry(
					GetterUtil.getLong(document.get("productEntryId"))),
				_productConsumptionService.
					getAccountProductEntryProductConsumptions(
						GetterUtil.getLong(document.get("accountId")),
						GetterUtil.getLong(document.get("productEntryId"))),
				_productPurchaseService.getAccountProductEntryProductPurchases(
					GetterUtil.getLong(document.get("accountId")),
					GetterUtil.getLong(document.get("productEntryId")),
					QueryUtil.ALL_POS, QueryUtil.ALL_POS)),
			sorts);
	}

	@Reference
	private AccountService _accountService;

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private ProductConsumptionService _productConsumptionService;

	@Reference
	private ProductEntryLocalService _productEntryLocalService;

	@Reference
	private ProductEntryService _productEntryService;

	@Reference
	private ProductFieldLocalService _productFieldLocalService;

	@Reference
	private ProductPurchaseService _productPurchaseService;

}