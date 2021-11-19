/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ExternalLink;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ProductPurchasePermission;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.util.ProductPurchaseUtil;
import com.liferay.osb.koroneiki.phloem.rest.internal.odata.entity.v1_0.ProductPurchaseEntityModel;
import com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0.util.PhloemPermissionUtil;
import com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0.util.ServiceContextUtil;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ExternalLinkResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ProductPurchaseResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ProductResource;
import com.liferay.osb.koroneiki.taproot.constants.WorkflowConstants;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactLocalService;
import com.liferay.osb.koroneiki.trunk.model.ProductEntry;
import com.liferay.osb.koroneiki.trunk.model.ProductField;
import com.liferay.osb.koroneiki.trunk.service.ProductEntryLocalService;
import com.liferay.osb.koroneiki.trunk.service.ProductFieldLocalService;
import com.liferay.osb.koroneiki.trunk.service.ProductPurchaseLocalService;
import com.liferay.osb.koroneiki.trunk.service.ProductPurchaseService;
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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.vulcan.fields.NestedField;
import com.liferay.portal.vulcan.fields.NestedFieldId;
import com.liferay.portal.vulcan.fields.NestedFieldSupport;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.resource.EntityModelResource;
import com.liferay.portal.vulcan.util.SearchUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
	properties = "OSGI-INF/liferay/rest/v1_0/product-purchase.properties",
	scope = ServiceScope.PROTOTYPE,
	service = {NestedFieldSupport.class, ProductPurchaseResource.class}
)
public class ProductPurchaseResourceImpl
	extends BaseProductPurchaseResourceImpl
	implements EntityModelResource, NestedFieldSupport {

	@Override
	public void deleteProductPurchase(
			String agentName, String agentUID, String productPurchaseKey)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		_productPurchaseService.deleteProductPurchase(productPurchaseKey);
	}

	@Override
	public void deleteProductPurchaseProductPurchasePermission(
			String agentName, String agentUID, String productPurchaseKey,
			ProductPurchasePermission productPurchasePermission)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		_updateProductPurchasePermission(
			productPurchaseKey, "delete", productPurchasePermission);
	}

	@Override
	public Page<ProductPurchase> getAccountAccountKeyProductPurchasesPage(
			String accountKey, Pagination pagination)
		throws Exception {

		return Page.of(
			transform(
				_productPurchaseService.getAccountProductPurchases(
					accountKey, pagination.getStartPosition(),
					pagination.getEndPosition()),
				ProductPurchaseUtil::toProductPurchase),
			pagination,
			_productPurchaseService.getAccountProductPurchasesCount(
				accountKey));
	}

	@Override
	public Page<ProductPurchase>
			getContactByUuidContactUuidProductPurchasesPage(
				String contactUuid, Pagination pagination)
		throws Exception {

		return _getContactProductPurchasesPage(
			_contactLocalService.getContactByUuid(contactUuid), pagination);
	}

	@Override
	public EntityModel getEntityModel(MultivaluedMap multivaluedMap) {
		long classNameId = _classNameLocalService.getClassNameId(
			com.liferay.osb.koroneiki.trunk.model.ProductPurchase.class);

		return new ProductPurchaseEntityModel(
			_productFieldLocalService.getProductFieldNames(classNameId));
	}

	@NestedField("productPurchases")
	public List<ProductPurchase> getNestedFieldProductPurchases(
			@NestedFieldId("key") String accountKey)
		throws Exception {

		Account account = _accountLocalService.getAccount(accountKey);

		BooleanFilter booleanFilter = new BooleanFilter();

		booleanFilter.addRequiredTerm(
			"accountKey", StringUtil.toLowerCase(accountKey));

		Page<ProductPurchase> productPurchasesPage = SearchUtil.search(
			booleanQuery -> {
			},
			booleanFilter,
			com.liferay.osb.koroneiki.trunk.model.ProductPurchase.class,
			StringPool.BLANK, null,
			queryConfig -> queryConfig.setSelectedFieldNames(
				Field.ENTRY_CLASS_PK),
			searchContext -> searchContext.setCompanyId(account.getCompanyId()),
			document -> ProductPurchaseUtil.toProductPurchase(
				_productPurchaseLocalService.getProductPurchase(
					GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)))),
			null);

		return new ArrayList<>(productPurchasesPage.getItems());
	}

	@Override
	public ProductPurchase getProductPurchase(String productPurchaseKey)
		throws Exception {

		return ProductPurchaseUtil.toProductPurchase(
			_productPurchaseService.getProductPurchase(productPurchaseKey));
	}

	@Override
	public Page<ProductPurchase>
			getProductPurchaseByExternalLinkDomainEntityNameEntityPage(
				String domain, String entityName, String entityId,
				Pagination pagination)
		throws Exception {

		return Page.of(
			transform(
				_productPurchaseService.getProductPurchases(
					domain, entityName, entityId, pagination.getStartPosition(),
					pagination.getEndPosition()),
				ProductPurchaseUtil::toProductPurchase),
			pagination,
			_productPurchaseService.getProductPurchasesCount(
				domain, entityName, entityId));
	}

	@Override
	public Page<ProductPurchase> getProductPurchasesPage(
			String search, Filter filter, Pagination pagination, Sort[] sorts)
		throws Exception {

		return SearchUtil.search(
			booleanQuery -> {
			},
			filter, com.liferay.osb.koroneiki.trunk.model.ProductPurchase.class,
			search, pagination,
			queryConfig -> queryConfig.setSelectedFieldNames(
				Field.ENTRY_CLASS_PK),
			searchContext -> searchContext.setCompanyId(
				contextCompany.getCompanyId()),
			document -> ProductPurchaseUtil.toProductPurchase(
				_productPurchaseLocalService.getProductPurchase(
					GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)))),
			sorts);
	}

	@Override
	public ProductPurchase postAccountAccountKeyProductPurchase(
			String agentName, String agentUID, String accountKey,
			ProductPurchase productPurchase)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		String productKey = _getProductKey(
			agentName, agentUID, productPurchase);

		Date startDate = productPurchase.getStartDate();
		Date endDate = productPurchase.getEndDate();
		Date originalEndDate = productPurchase.getOriginalEndDate();

		if ((productPurchase.getPerpetual() != null) &&
			productPurchase.getPerpetual()) {

			startDate = null;
			endDate = null;
			originalEndDate = null;
		}

		int quantity = GetterUtil.getInteger(productPurchase.getQuantity(), 1);

		int status = WorkflowConstants.STATUS_APPROVED;

		ProductPurchase.Status productPurchaseStatus =
			productPurchase.getStatus();

		if (productPurchaseStatus != null) {
			status = WorkflowConstants.getLabelStatus(
				productPurchaseStatus.toString());
		}

		List<ProductField> productFields = getProductFields(
			productPurchase.getProperties(), Collections.emptyList());

		ProductPurchase curProductPurchase =
			ProductPurchaseUtil.toProductPurchase(
				_productPurchaseService.addProductPurchase(
					accountKey, productKey, startDate, endDate, originalEndDate,
					quantity, status, productFields));

		if (!ArrayUtil.isEmpty(productPurchase.getExternalLinks())) {
			for (ExternalLink externalLink :
					productPurchase.getExternalLinks()) {

				_externalLinkResource.
					postProductPurchaseProductPurchaseKeyExternalLink(
						agentName, agentUID, curProductPurchase.getKey(),
						externalLink);
			}
		}

		return curProductPurchase;
	}

	@Override
	public ProductPurchase putProductPurchase(
			String agentName, String agentUID, String productPurchaseKey,
			ProductPurchase productPurchase)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		com.liferay.osb.koroneiki.trunk.model.ProductPurchase
			curProductPurchase =
				_productPurchaseLocalService.getProductPurchase(
					productPurchaseKey);

		Date startDate = productPurchase.getStartDate();

		if (startDate == null) {
			startDate = curProductPurchase.getStartDate();
		}

		Date endDate = productPurchase.getEndDate();

		if (endDate == null) {
			endDate = curProductPurchase.getEndDate();
		}

		Date originalEndDate = productPurchase.getOriginalEndDate();

		if (originalEndDate == null) {
			originalEndDate = curProductPurchase.getOriginalEndDate();
		}

		if ((productPurchase.getPerpetual() != null) &&
			productPurchase.getPerpetual()) {

			startDate = null;
			endDate = null;
			originalEndDate = null;
		}

		int quantity = GetterUtil.getInteger(
			productPurchase.getQuantity(), curProductPurchase.getQuantity());

		int status = curProductPurchase.getStatus();

		ProductPurchase.Status productPurchaseStatus =
			productPurchase.getStatus();

		if (productPurchaseStatus != null) {
			status = WorkflowConstants.getLabelStatus(
				productPurchaseStatus.toString());
		}

		List<ProductField> productFields = getProductFields(
			productPurchase.getProperties(),
			curProductPurchase.getProductFields());

		if (!ArrayUtil.isEmpty(productPurchase.getExternalLinks())) {
			for (ExternalLink externalLink :
					productPurchase.getExternalLinks()) {

				if (Validator.isNull(externalLink.getKey())) {
					_externalLinkResource.
						postProductPurchaseProductPurchaseKeyExternalLink(
							agentName, agentUID, productPurchaseKey,
							externalLink);
				}
				else {
					_externalLinkResource.putExternalLink(
						agentName, agentUID, externalLink.getKey(),
						externalLink);
				}
			}
		}

		return ProductPurchaseUtil.toProductPurchase(
			_productPurchaseService.updateProductPurchase(
				curProductPurchase.getProductPurchaseId(), startDate, endDate,
				originalEndDate, quantity, status, productFields));
	}

	@Override
	public void putProductPurchaseProductPurchasePermission(
			String agentName, String agentUID, String productPurchaseKey,
			ProductPurchasePermission productPurchasePermission)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		_updateProductPurchasePermission(
			productPurchaseKey, "add", productPurchasePermission);
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

	private Page<ProductPurchase> _getContactProductPurchasesPage(
			Contact contact, Pagination pagination)
		throws Exception {

		return Page.of(
			transform(
				_productPurchaseService.getContactProductPurchases(
					contact.getContactId(), pagination.getStartPosition(),
					pagination.getEndPosition()),
				ProductPurchaseUtil::toProductPurchase),
			pagination,
			_productPurchaseService.getContactProductPurchasesCount(
				contact.getContactId()));
	}

	private String _getProductKey(
			String agentName, String agentUID, ProductPurchase productPurchase)
		throws Exception {

		Product product = productPurchase.getProduct();

		if (product != null) {
			if (Validator.isNotNull(product.getKey())) {
				return product.getKey();
			}

			ProductEntry productEntry =
				_productEntryLocalService.fetchProductEntryByName(
					product.getName());

			if (productEntry != null) {
				return productEntry.getProductEntryKey();
			}

			product = _productResource.postProduct(
				agentName, agentUID, product);

			return product.getKey();
		}

		return productPurchase.getProductKey();
	}

	private void _updateProductPurchasePermission(
			String productPurchaseKey, String operation,
			ProductPurchasePermission productPurchasePermission)
		throws Exception {

		com.liferay.osb.koroneiki.trunk.model.ProductPurchase productPurchase =
			_productPurchaseLocalService.getProductPurchase(productPurchaseKey);

		_productPurchasePermission.check(
			PermissionThreadLocal.getPermissionChecker(), productPurchase,
			ActionKeys.PERMISSIONS);

		List<String> actionIds = new ArrayList<>();

		if (GetterUtil.getBoolean(productPurchasePermission.getDelete())) {
			actionIds.add(ActionKeys.DELETE);
		}

		if (GetterUtil.getBoolean(productPurchasePermission.getPermissions())) {
			actionIds.add(ActionKeys.PERMISSIONS);
		}

		if (GetterUtil.getBoolean(productPurchasePermission.getUpdate())) {
			actionIds.add(ActionKeys.UPDATE);
		}

		if (GetterUtil.getBoolean(productPurchasePermission.getView())) {
			actionIds.add(ActionKeys.VIEW);
		}

		if (actionIds.isEmpty()) {
			return;
		}

		_phloemPermissionUtil.persistModelPermission(
			operation, contextCompany.getCompanyId(),
			com.liferay.osb.koroneiki.trunk.model.ProductPurchase.class.
				getName(),
			productPurchase.getProductPurchaseId(),
			productPurchasePermission.getRoleNames(), actionIds);
	}

	@Reference
	private AccountLocalService _accountLocalService;

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private ContactLocalService _contactLocalService;

	@Reference
	private ExternalLinkResource _externalLinkResource;

	@Reference
	private PhloemPermissionUtil _phloemPermissionUtil;

	@Reference
	private ProductEntryLocalService _productEntryLocalService;

	@Reference
	private ProductFieldLocalService _productFieldLocalService;

	@Reference
	private ProductPurchaseLocalService _productPurchaseLocalService;

	@Reference
	private com.liferay.osb.koroneiki.trunk.permission.ProductPurchasePermission
		_productPurchasePermission;

	@Reference
	private ProductPurchaseService _productPurchaseService;

	@Reference
	private ProductResource _productResource;

}