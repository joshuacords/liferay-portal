/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.service.impl;

import com.liferay.osb.koroneiki.root.service.ExternalLinkLocalService;
import com.liferay.osb.koroneiki.root.util.ModelKeyGenerator;
import com.liferay.osb.koroneiki.trunk.exception.ProductEntryNameException;
import com.liferay.osb.koroneiki.trunk.exception.RequiredProductEntryException;
import com.liferay.osb.koroneiki.trunk.model.ProductEntry;
import com.liferay.osb.koroneiki.trunk.model.ProductField;
import com.liferay.osb.koroneiki.trunk.model.ProductPurchase;
import com.liferay.osb.koroneiki.trunk.model.view.ProductPurchaseView;
import com.liferay.osb.koroneiki.trunk.model.view.impl.ProductPurchaseViewImpl;
import com.liferay.osb.koroneiki.trunk.service.ProductConsumptionLocalService;
import com.liferay.osb.koroneiki.trunk.service.ProductFieldLocalService;
import com.liferay.osb.koroneiki.trunk.service.ProductPurchaseLocalService;
import com.liferay.osb.koroneiki.trunk.service.base.ProductEntryLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistry;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.transaction.TransactionCommitCallbackUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	property = "model.class.name=com.liferay.osb.koroneiki.trunk.model.ProductEntry",
	service = AopService.class
)
public class ProductEntryLocalServiceImpl
	extends ProductEntryLocalServiceBaseImpl {

	@Indexable(type = IndexableType.REINDEX)
	public ProductEntry addProductEntry(
			long userId, String name, List<ProductField> productFields)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		validate(0, name);

		long productEntryId = counterLocalService.increment();

		ProductEntry productEntry = productEntryPersistence.create(
			productEntryId);

		productEntry.setCompanyId(user.getCompanyId());
		productEntry.setUserId(userId);
		productEntry.setProductEntryKey(
			ModelKeyGenerator.generate(productEntryId));
		productEntry.setName(name);

		productEntry = productEntryPersistence.update(productEntry);

		// Resources

		resourceLocalService.addResources(
			productEntry.getCompanyId(), 0, userId,
			ProductEntry.class.getName(), productEntry.getProductEntryId(),
			false, false, false);

		// Product fields

		for (ProductField productField : productFields) {
			_productFieldLocalService.addProductField(
				userId, ProductEntry.class.getName(), productEntryId,
				productField.getName(), productField.getValue());
		}

		return productEntry;
	}

	@Override
	public ProductEntry deleteProductEntry(long productEntryId)
		throws PortalException {

		return deleteProductEntry(
			productEntryLocalService.getProductEntry(productEntryId));
	}

	@Override
	public ProductEntry deleteProductEntry(ProductEntry productEntry)
		throws PortalException {

		int productConsumptionsCount =
			_productConsumptionLocalService.
				getProductEntryProductConsumptionsCount(
					productEntry.getProductEntryId());

		if (productConsumptionsCount > 0) {
			throw new RequiredProductEntryException.
				MustNotDeleteProductEntryReferencedByProductConsumption(
					productEntry.getProductEntryId());
		}

		int productPurchasesCount =
			_productPurchaseLocalService.getProductEntryProductPurchasesCount(
				productEntry.getProductEntryId());

		if (productPurchasesCount > 0) {
			throw new RequiredProductEntryException.
				MustNotDeleteProductEntryReferencedByProductPurchase(
					productEntry.getProductEntryId());
		}

		// External links

		long classNameId = classNameLocalService.getClassNameId(
			ProductEntry.class);

		_externalLinkLocalService.deleteExternalLinks(
			classNameId, productEntry.getProductEntryId());

		// Resources

		resourceLocalService.deleteResource(
			productEntry.getCompanyId(), ProductEntry.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL,
			productEntry.getProductEntryId());

		reindexProductPurchaseView(productEntry);

		return productEntryPersistence.remove(productEntry);
	}

	public ProductEntry fetchProductEntryByName(String name) {
		return productEntryPersistence.fetchByName(name);
	}

	public ProductEntry getProductEntry(String productEntryKey)
		throws PortalException {

		return productEntryPersistence.findByProductEntryKey(productEntryKey);
	}

	public ProductEntry getProductEntryByName(String name)
		throws PortalException {

		return productEntryPersistence.findByName(name);
	}

	@Indexable(type = IndexableType.REINDEX)
	public ProductEntry reindex(long productEntryId) throws PortalException {
		ProductEntry productEntry = productEntryPersistence.findByPrimaryKey(
			productEntryId);

		reindexProductPurchaseView(productEntry);

		return productEntry;
	}

	public Hits search(
			long companyId, String keywords, int start, int end, Sort sort)
		throws PortalException {

		try {
			Indexer<ProductEntry> indexer =
				IndexerRegistryUtil.nullSafeGetIndexer(ProductEntry.class);

			SearchContext searchContext = new SearchContext();

			searchContext.setAndSearch(false);

			Map<String, Serializable> attributes = new HashMap<>();

			attributes.put("externalLinkEntityIds", keywords);
			attributes.put("name", keywords);

			searchContext.setAttributes(attributes);

			searchContext.setCompanyId(companyId);
			searchContext.setEnd(end);

			if (sort != null) {
				searchContext.setSorts(sort);
			}

			searchContext.setStart(start);

			QueryConfig queryConfig = searchContext.getQueryConfig();

			queryConfig.setHighlightEnabled(false);
			queryConfig.setScoreEnabled(false);

			return indexer.search(searchContext);
		}
		catch (Exception exception) {
			throw new PortalException(exception);
		}
	}

	@Indexable(type = IndexableType.REINDEX)
	public ProductEntry updateProductEntry(
			long userId, long productEntryId, String name,
			List<ProductField> productFields)
		throws PortalException {

		validate(productEntryId, name);

		ProductEntry productEntry = productEntryPersistence.findByPrimaryKey(
			productEntryId);

		productEntry.setName(name);

		// Product Fields

		long classNameId = classNameLocalService.getClassNameId(
			ProductEntry.class);

		Map<String, ProductField> productFieldsMap = getProductFieldsMap(
			productEntryId);

		for (ProductField productField : productFields) {
			ProductField curProductField = productFieldsMap.remove(
				productField.getName());

			if (curProductField == null) {
				_productFieldLocalService.addProductField(
					userId, classNameId, productEntryId, productField.getName(),
					productField.getValue());
			}
			else {
				_productFieldLocalService.updateProductField(
					curProductField.getProductFieldId(),
					productField.getValue());
			}
		}

		for (ProductField productField : productFieldsMap.values()) {
			_productFieldLocalService.deleteProductField(
				productField.getProductFieldId());
		}

		reindexProductPurchaseView(productEntry);

		return productEntryPersistence.update(productEntry);
	}

	protected Map<String, ProductField> getProductFieldsMap(
		long productEntryId) {

		Map<String, ProductField> productFieldsMap = new HashMap<>();

		long classNameId = classNameLocalService.getClassNameId(
			ProductEntry.class);

		List<ProductField> productFields =
			_productFieldLocalService.getProductFields(
				classNameId, productEntryId);

		for (ProductField productField : productFields) {
			productFieldsMap.put(productField.getName(), productField);
		}

		return productFieldsMap;
	}

	protected void reindexProductPurchaseView(ProductEntry productEntry)
		throws PortalException {

		List<ProductPurchase> productPurchases =
			_productPurchaseLocalService.getProductEntryProductPurchases(
				productEntry.getProductEntryId());

		Stream<ProductPurchase> productPurchaseStream =
			productPurchases.stream();

		Set<Long> accountIds = productPurchaseStream.collect(
			Collectors.mapping(
				ProductPurchase::getAccountId, Collectors.toSet()));

		for (long accountId : accountIds) {
			ProductPurchaseView productPurchaseView =
				new ProductPurchaseViewImpl();

			productPurchaseView.setCompanyId(productEntry.getCompanyId());
			productPurchaseView.setAccountId(accountId);
			productPurchaseView.setProductEntryId(
				productEntry.getProductEntryId());

			TransactionCommitCallbackUtil.registerCallback(
				() -> {
					Indexer<ProductPurchaseView> indexer =
						_indexerRegistry.getIndexer(ProductPurchaseView.class);

					indexer.reindex(productPurchaseView);

					return null;
				});
		}
	}

	protected void validate(long productEntryId, String name)
		throws PortalException {

		if (Validator.isNull(name)) {
			throw new ProductEntryNameException();
		}

		ProductEntry productEntry = productEntryPersistence.fetchByName(name);

		if ((productEntry != null) &&
			(productEntry.getProductEntryId() != productEntryId)) {

			throw new ProductEntryNameException.MustNotBeDuplicate(name);
		}
	}

	@Reference
	private ExternalLinkLocalService _externalLinkLocalService;

	@Reference
	private IndexerRegistry _indexerRegistry;

	@Reference
	private ProductConsumptionLocalService _productConsumptionLocalService;

	@Reference
	private ProductFieldLocalService _productFieldLocalService;

	@Reference
	private ProductPurchaseLocalService _productPurchaseLocalService;

}