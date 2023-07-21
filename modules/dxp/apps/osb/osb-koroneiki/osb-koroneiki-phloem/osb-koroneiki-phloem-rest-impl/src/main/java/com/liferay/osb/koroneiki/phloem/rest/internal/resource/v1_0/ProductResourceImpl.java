/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ExternalLink;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ProductPermission;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.util.ProductUtil;
import com.liferay.osb.koroneiki.phloem.rest.internal.odata.entity.v1_0.ProductEntryEntityModel;
import com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0.util.PhloemPermissionUtil;
import com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0.util.ServiceContextUtil;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ExternalLinkResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ProductResource;
import com.liferay.osb.koroneiki.trunk.constants.TrunkActionKeys;
import com.liferay.osb.koroneiki.trunk.model.ProductEntry;
import com.liferay.osb.koroneiki.trunk.model.ProductField;
import com.liferay.osb.koroneiki.trunk.permission.ProductEntryPermission;
import com.liferay.osb.koroneiki.trunk.service.ProductEntryLocalService;
import com.liferay.osb.koroneiki.trunk.service.ProductEntryService;
import com.liferay.osb.koroneiki.trunk.service.ProductFieldLocalService;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.resource.EntityModelResource;
import com.liferay.portal.vulcan.util.SearchUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Amos Fong
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/product.properties",
	scope = ServiceScope.PROTOTYPE, service = ProductResource.class
)
public class ProductResourceImpl
	extends BaseProductResourceImpl implements EntityModelResource {

	@Override
	public void deleteProduct(
			String agentName, String agentUID, String productKey)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		_productEntryService.deleteProductEntry(productKey);
	}

	@Override
	public void deleteProductProductPermission(
			String agentName, String agentUID, String productKey,
			ProductPermission productPermission)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		_updateProductPermission(productKey, "delete", productPermission);
	}

	@Override
	public EntityModel getEntityModel(MultivaluedMap multivaluedMap) {
		long classNameId = _classNameLocalService.getClassNameId(
			ProductEntry.class);

		return new ProductEntryEntityModel(
			_productFieldLocalService.getProductFieldNames(classNameId));
	}

	@Override
	public Product getProduct(String productKey) throws Exception {
		return ProductUtil.toProduct(
			_productEntryService.getProductEntry(productKey));
	}

	@Override
	public Page<Product> getProductByExternalLinkDomainEntityNameEntityPage(
			String domain, String entityName, String entityId,
			Pagination pagination)
		throws Exception {

		return Page.of(
			transform(
				_productEntryService.getProductEntries(
					domain, entityName, entityId, pagination.getStartPosition(),
					pagination.getEndPosition()),
				ProductUtil::toProduct),
			pagination,
			_productEntryService.getProductEntriesCount(
				domain, entityName, entityId));
	}

	@Override
	public Product getProductByNameProductName(String productName)
		throws Exception {

		return ProductUtil.toProduct(
			_productEntryService.getProductEntryByName(productName));
	}

	@Override
	public Page<Product> getProductsPage(
			String search, Filter filter, Pagination pagination, Sort[] sorts)
		throws Exception {

		return SearchUtil.search(
			booleanQuery -> {
			},
			filter, ProductEntry.class, search, pagination,
			queryConfig -> queryConfig.setSelectedFieldNames(
				Field.ENTRY_CLASS_PK),
			searchContext -> searchContext.setCompanyId(
				contextCompany.getCompanyId()),
			document -> ProductUtil.toProduct(
				_productEntryLocalService.getProductEntry(
					GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)))),
			sorts);
	}

	@Override
	public Product postProduct(
			String agentName, String agentUID, Product product)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		List<ProductField> productFields = getProductFields(
			product.getProperties(), Collections.emptyList());

		Product curProduct = ProductUtil.toProduct(
			_productEntryService.addProductEntry(
				product.getName(), productFields));

		if (!ArrayUtil.isEmpty(product.getExternalLinks())) {
			for (ExternalLink externalLink : product.getExternalLinks()) {
				_externalLinkResource.postProductProductKeyExternalLink(
					agentName, agentUID, curProduct.getKey(), externalLink);
			}
		}

		return curProduct;
	}

	@Override
	public Product putProduct(
			String agentName, String agentUID, String productKey,
			Product product)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		ProductEntry productEntry = _productEntryLocalService.getProductEntry(
			productKey);

		List<ProductField> productFields = getProductFields(
			product.getProperties(), productEntry.getProductFields());

		return ProductUtil.toProduct(
			_productEntryService.updateProductEntry(
				productKey, product.getName(), productFields));
	}

	@Override
	public void putProductProductPermission(
			String agentName, String agentUID, String productKey,
			ProductPermission productPermission)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		_updateProductPermission(productKey, "add", productPermission);
	}

	protected List<ProductField> getProductFields(
		Map<String, String> properties,
		List<ProductField> defaultProductFields) {

		if (properties == null) {
			return defaultProductFields;
		}

		List<ProductField> productFields = new ArrayList<>();

		for (Map.Entry<String, String> entry : properties.entrySet()) {
			ProductField productField =
				_productFieldLocalService.createProductField(0);

			productField.setName(entry.getKey());
			productField.setValue(entry.getValue());

			productFields.add(productField);
		}

		return productFields;
	}

	private void _updateProductPermission(
			String productKey, String operation,
			ProductPermission productPermission)
		throws Exception {

		ProductEntry productEntry = _productEntryLocalService.getProductEntry(
			productKey);

		_productEntryPermission.check(
			PermissionThreadLocal.getPermissionChecker(), productEntry,
			ActionKeys.PERMISSIONS);

		List<String> actionIds = new ArrayList<>();

		if (GetterUtil.getBoolean(productPermission.getConsume())) {
			actionIds.add(TrunkActionKeys.CONSUME);
		}

		if (GetterUtil.getBoolean(productPermission.getDelete())) {
			actionIds.add(ActionKeys.DELETE);
		}

		if (GetterUtil.getBoolean(productPermission.getPermissions())) {
			actionIds.add(ActionKeys.PERMISSIONS);
		}

		if (GetterUtil.getBoolean(productPermission.getUpdate())) {
			actionIds.add(ActionKeys.UPDATE);
		}

		if (GetterUtil.getBoolean(productPermission.getView())) {
			actionIds.add(ActionKeys.VIEW);
		}

		if (actionIds.isEmpty()) {
			return;
		}

		_phloemPermissionUtil.persistModelPermission(
			operation, contextCompany.getCompanyId(),
			ProductEntry.class.getName(), productEntry.getProductEntryId(),
			productPermission.getRoleNames(), actionIds);
	}

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private ExternalLinkResource _externalLinkResource;

	@Reference
	private PhloemPermissionUtil _phloemPermissionUtil;

	@Reference
	private ProductEntryLocalService _productEntryLocalService;

	@Reference
	private ProductEntryPermission _productEntryPermission;

	@Reference
	private ProductEntryService _productEntryService;

	@Reference
	private ProductFieldLocalService _productFieldLocalService;

}