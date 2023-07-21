/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ExternalLink;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ProductConsumption;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ProductConsumptionPermission;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.util.ProductConsumptionUtil;
import com.liferay.osb.koroneiki.phloem.rest.internal.odata.entity.v1_0.ProductConsumptionEntityModel;
import com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0.util.PhloemPermissionUtil;
import com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0.util.ServiceContextUtil;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ExternalLinkResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ProductConsumptionResource;
import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.osb.koroneiki.taproot.service.ContactLocalService;
import com.liferay.osb.koroneiki.trunk.model.ProductField;
import com.liferay.osb.koroneiki.trunk.model.ProductPurchase;
import com.liferay.osb.koroneiki.trunk.service.ProductConsumptionLocalService;
import com.liferay.osb.koroneiki.trunk.service.ProductConsumptionService;
import com.liferay.osb.koroneiki.trunk.service.ProductFieldLocalService;
import com.liferay.osb.koroneiki.trunk.service.ProductPurchaseLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.vulcan.fields.NestedField;
import com.liferay.portal.vulcan.fields.NestedFieldId;
import com.liferay.portal.vulcan.fields.NestedFieldSupport;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.resource.EntityModelResource;
import com.liferay.portal.vulcan.util.SearchUtil;

import java.util.ArrayList;
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
	properties = "OSGI-INF/liferay/rest/v1_0/product-consumption.properties",
	scope = ServiceScope.PROTOTYPE,
	service = {NestedFieldSupport.class, ProductConsumptionResource.class}
)
public class ProductConsumptionResourceImpl
	extends BaseProductConsumptionResourceImpl
	implements EntityModelResource, NestedFieldSupport {

	@Override
	public void deleteProductConsumption(
			String agentName, String agentUID, String productConsumptionKey)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		_productConsumptionService.deleteProductConsumption(
			productConsumptionKey);
	}

	@Override
	public void deleteProductConsumptionProductConsumptionPermission(
			String agentName, String agentUID, String productConsumptionKey,
			ProductConsumptionPermission productConsumptionPermission)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		_updateProductConsumptionPermission(
			productConsumptionKey, "delete", productConsumptionPermission);
	}

	@Override
	public Page<ProductConsumption> getAccountAccountKeyProductConsumptionsPage(
			String accountKey, Pagination pagination)
		throws Exception {

		return Page.of(
			transform(
				_productConsumptionService.getAccountProductConsumptions(
					accountKey, pagination.getStartPosition(),
					pagination.getEndPosition()),
				ProductConsumptionUtil::toProductConsumption),
			pagination,
			_productConsumptionService.getAccountProductConsumptionsCount(
				accountKey));
	}

	@Override
	public Page<ProductConsumption>
			getContactByUuidContactUuidProductConsumptionsPage(
				String contactUuid, Pagination pagination)
		throws Exception {

		return _getContactProductConsumptionsPage(
			_contactLocalService.getContactByUuid(contactUuid), pagination);
	}

	@Override
	public EntityModel getEntityModel(MultivaluedMap multivaluedMap) {
		long classNameId = _classNameLocalService.getClassNameId(
			com.liferay.osb.koroneiki.trunk.model.ProductConsumption.class);

		return new ProductConsumptionEntityModel(
			_productFieldLocalService.getProductFieldNames(classNameId));
	}

	@NestedField("productConsumptions")
	public List<ProductConsumption> getNestedFieldProductConsumptions(
			@NestedFieldId("key") String productPurchaseKey)
		throws Exception {

		ProductPurchase productPurchase =
			_productPurchaseLocalService.getProductPurchase(productPurchaseKey);

		BooleanFilter booleanFilter = new BooleanFilter();

		booleanFilter.addRequiredTerm(
			"productPurchaseKey", StringUtil.toLowerCase(productPurchaseKey));

		Page<ProductConsumption> productConsumptionsPage = SearchUtil.search(
			booleanQuery -> {
			},
			booleanFilter,
			com.liferay.osb.koroneiki.trunk.model.ProductConsumption.class,
			StringPool.BLANK, null,
			queryConfig -> queryConfig.setSelectedFieldNames(
				Field.ENTRY_CLASS_PK),
			searchContext -> searchContext.setCompanyId(
				productPurchase.getCompanyId()),
			document -> ProductConsumptionUtil.toProductConsumption(
				_productConsumptionLocalService.getProductConsumption(
					GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)))),
			null);

		return new ArrayList<>(productConsumptionsPage.getItems());
	}

	@Override
	public ProductConsumption getProductConsumption(
			String productConsumptionKey)
		throws Exception {

		return ProductConsumptionUtil.toProductConsumption(
			_productConsumptionService.getProductConsumption(
				productConsumptionKey));
	}

	@Override
	public Page<ProductConsumption>
			getProductConsumptionByExternalLinkDomainEntityNameEntityPage(
				String domain, String entityName, String entityId,
				Pagination pagination)
		throws Exception {

		return Page.of(
			transform(
				_productConsumptionService.getProductConsumptions(
					domain, entityName, entityId, pagination.getStartPosition(),
					pagination.getEndPosition()),
				ProductConsumptionUtil::toProductConsumption),
			pagination,
			_productConsumptionService.getProductConsumptionsCount(
				domain, entityName, entityId));
	}

	@Override
	public Page<ProductConsumption> getProductConsumptionsPage(
			String search, Filter filter, Pagination pagination, Sort[] sorts)
		throws Exception {

		return SearchUtil.search(
			booleanQuery -> {
			},
			filter,
			com.liferay.osb.koroneiki.trunk.model.ProductConsumption.class,
			search, pagination,
			queryConfig -> queryConfig.setSelectedFieldNames(
				Field.ENTRY_CLASS_PK),
			searchContext -> searchContext.setCompanyId(
				contextCompany.getCompanyId()),
			document -> ProductConsumptionUtil.toProductConsumption(
				_productConsumptionLocalService.getProductConsumption(
					GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)))),
			sorts);
	}

	@Override
	public ProductConsumption postAccountAccountKeyProductConsumption(
			String agentName, String agentUID, String accountKey,
			ProductConsumption productConsumption)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		List<ProductField> productFields = _getProductFields(
			productConsumption.getProperties());

		ProductConsumption curProductConsumption =
			ProductConsumptionUtil.toProductConsumption(
				_productConsumptionService.addProductConsumption(
					accountKey, productConsumption.getProductKey(),
					productConsumption.getProductPurchaseKey(),
					productConsumption.getStartDate(),
					productConsumption.getEndDate(), productFields));

		if (!ArrayUtil.isEmpty(productConsumption.getExternalLinks())) {
			for (ExternalLink externalLink :
					productConsumption.getExternalLinks()) {

				_externalLinkResource.
					postProductConsumptionProductConsumptionKeyExternalLink(
						agentName, agentUID, curProductConsumption.getKey(),
						externalLink);
			}
		}

		return curProductConsumption;
	}

	@Override
	public void putProductConsumptionProductConsumptionPermission(
			String agentName, String agentUID, String productConsumptionKey,
			ProductConsumptionPermission productConsumptionPermission)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		_updateProductConsumptionPermission(
			productConsumptionKey, "add", productConsumptionPermission);
	}

	private Page<ProductConsumption> _getContactProductConsumptionsPage(
			Contact contact, Pagination pagination)
		throws PortalException {

		return Page.of(
			transform(
				_productConsumptionService.getContactProductConsumptions(
					contact.getContactId(), pagination.getStartPosition(),
					pagination.getEndPosition()),
				ProductConsumptionUtil::toProductConsumption),
			pagination,
			_productConsumptionService.getContactProductConsumptionsCount(
				contact.getContactId()));
	}

	private List<ProductField> _getProductFields(
		Map<String, String> properties) {

		List<ProductField> productFields = new ArrayList<>();

		if (properties == null) {
			return productFields;
		}

		for (Map.Entry<String, String> entry : properties.entrySet()) {
			ProductField productField =
				_productFieldLocalService.createProductField(0);

			productField.setName(entry.getKey());
			productField.setValue(entry.getValue());

			productFields.add(productField);
		}

		return productFields;
	}

	private void _updateProductConsumptionPermission(
			String productConsumptionKey, String operation,
			ProductConsumptionPermission productConsumptionPermission)
		throws Exception {

		com.liferay.osb.koroneiki.trunk.model.ProductConsumption
			productConsumption =
				_productConsumptionLocalService.getProductConsumption(
					productConsumptionKey);

		_productConsumptionPermission.check(
			PermissionThreadLocal.getPermissionChecker(), productConsumption,
			ActionKeys.PERMISSIONS);

		List<String> actionIds = new ArrayList<>();

		if (GetterUtil.getBoolean(productConsumptionPermission.getDelete())) {
			actionIds.add(ActionKeys.DELETE);
		}

		if (GetterUtil.getBoolean(
				productConsumptionPermission.getPermissions())) {

			actionIds.add(ActionKeys.PERMISSIONS);
		}

		if (GetterUtil.getBoolean(productConsumptionPermission.getUpdate())) {
			actionIds.add(ActionKeys.UPDATE);
		}

		if (GetterUtil.getBoolean(productConsumptionPermission.getView())) {
			actionIds.add(ActionKeys.VIEW);
		}

		if (actionIds.isEmpty()) {
			return;
		}

		_phloemPermissionUtil.persistModelPermission(
			operation, contextCompany.getCompanyId(),
			com.liferay.osb.koroneiki.trunk.model.ProductConsumption.class.
				getName(),
			productConsumption.getProductConsumptionId(),
			productConsumptionPermission.getRoleNames(), actionIds);
	}

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private ContactLocalService _contactLocalService;

	@Reference
	private ExternalLinkResource _externalLinkResource;

	@Reference
	private PhloemPermissionUtil _phloemPermissionUtil;

	@Reference
	private ProductConsumptionLocalService _productConsumptionLocalService;

	@Reference
	private
		com.liferay.osb.koroneiki.trunk.permission.ProductConsumptionPermission
			_productConsumptionPermission;

	@Reference
	private ProductConsumptionService _productConsumptionService;

	@Reference
	private ProductFieldLocalService _productFieldLocalService;

	@Reference
	private ProductPurchaseLocalService _productPurchaseLocalService;

}