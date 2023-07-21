/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.service.impl;

import com.liferay.osb.koroneiki.root.service.ExternalLinkLocalService;
import com.liferay.osb.koroneiki.root.util.ModelKeyGenerator;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalService;
import com.liferay.osb.koroneiki.trunk.exception.NoSuchProductConsumptionException;
import com.liferay.osb.koroneiki.trunk.exception.ProductConsumptionProductEntryException;
import com.liferay.osb.koroneiki.trunk.model.ProductConsumption;
import com.liferay.osb.koroneiki.trunk.model.ProductField;
import com.liferay.osb.koroneiki.trunk.model.ProductPurchase;
import com.liferay.osb.koroneiki.trunk.model.view.ProductPurchaseView;
import com.liferay.osb.koroneiki.trunk.model.view.impl.ProductPurchaseViewImpl;
import com.liferay.osb.koroneiki.trunk.service.ProductFieldLocalService;
import com.liferay.osb.koroneiki.trunk.service.base.ProductConsumptionLocalServiceBaseImpl;
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

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	property = "model.class.name=com.liferay.osb.koroneiki.trunk.model.ProductConsumption",
	service = AopService.class
)
public class ProductConsumptionLocalServiceImpl
	extends ProductConsumptionLocalServiceBaseImpl {

	@Indexable(type = IndexableType.REINDEX)
	public ProductConsumption addProductConsumption(
			long userId, long accountId, long productEntryId,
			long productPurchaseId, Date startDate, Date endDate,
			List<ProductField> productFields)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		validate(accountId, productEntryId, productPurchaseId);

		long productConsumptionId = counterLocalService.increment();

		ProductConsumption productConsumption =
			productConsumptionPersistence.create(productConsumptionId);

		productConsumption.setCompanyId(user.getCompanyId());
		productConsumption.setUserId(userId);
		productConsumption.setProductConsumptionKey(
			ModelKeyGenerator.generate(productConsumptionId));
		productConsumption.setAccountId(accountId);
		productConsumption.setProductEntryId(productEntryId);
		productConsumption.setProductPurchaseId(productPurchaseId);
		productConsumption.setStartDate(startDate);
		productConsumption.setEndDate(endDate);

		productConsumption = productConsumptionPersistence.update(
			productConsumption);

		// Resources

		resourceLocalService.addResources(
			productConsumption.getCompanyId(), 0, userId,
			ProductConsumption.class.getName(),
			productConsumption.getProductConsumptionId(), false, false, false);

		// Product fields

		for (ProductField productField : productFields) {
			_productFieldLocalService.addProductField(
				userId, ProductConsumption.class.getName(),
				productConsumptionId, productField.getName(),
				productField.getValue());
		}

		reindexProductPurchaseView(productConsumption);

		return productConsumption;
	}

	@Override
	public ProductConsumption deleteProductConsumption(
			long productConsumptionId)
		throws PortalException {

		return deleteProductConsumption(
			getProductConsumption(productConsumptionId));
	}

	@Override
	public ProductConsumption deleteProductConsumption(
			long userId, long accountId, long productEntryId)
		throws PortalException {

		List<ProductConsumption> productConsumptions =
			productConsumptionPersistence.findByU_AI_PEI(
				userId, accountId, productEntryId);

		if (productConsumptions.isEmpty()) {
			throw new NoSuchProductConsumptionException();
		}

		ProductConsumption productConsumption = productConsumptions.get(0);

		return deleteProductConsumption(
			productConsumption.getProductConsumptionId());
	}

	@Override
	public ProductConsumption deleteProductConsumption(
			ProductConsumption productConsumption)
		throws PortalException {

		// External links

		long classNameId = classNameLocalService.getClassNameId(
			ProductConsumption.class);

		_externalLinkLocalService.deleteExternalLinks(
			classNameId, productConsumption.getProductConsumptionId());

		// Resources

		resourceLocalService.deleteResource(
			productConsumption.getCompanyId(),
			ProductConsumption.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL,
			productConsumption.getProductConsumptionId());

		// Product fields

		productFieldPersistence.removeByC_C(
			classNameId, productConsumption.getProductConsumptionId());

		reindexProductPurchaseView(productConsumption);

		return productConsumptionPersistence.remove(productConsumption);
	}

	@Override
	public List<ProductConsumption> getAccountProductConsumptions(
			long accountId, int start, int end)
		throws PortalException {

		return productConsumptionPersistence.findByAccountId(
			accountId, start, end);
	}

	@Override
	public int getAccountProductConsumptionsCount(long accountId)
		throws PortalException {

		return productConsumptionPersistence.countByAccountId(accountId);
	}

	public List<ProductConsumption> getAccountProductEntryProductConsumptions(
		long accountId, long productEntryId) {

		return productConsumptionPersistence.findByAI_PEI(
			accountId, productEntryId);
	}

	public int getAccountProductEntryProductConsumptionsCount(
		long accountId, long productEntryId) {

		return productConsumptionPersistence.countByAI_PEI(
			accountId, productEntryId);
	}

	public List<ProductConsumption> getContactProductConsumptions(
		long contactId, int start, int end) {

		return productConsumptionFinder.findByContact(contactId, start, end);
	}

	public int getContactProductConsumptionsCount(long contactId) {
		return productConsumptionFinder.countByContact(contactId);
	}

	public ProductConsumption getProductConsumption(
			String productConsumptionKey)
		throws PortalException {

		return productConsumptionPersistence.findByProductConsumptionKey(
			productConsumptionKey);
	}

	@Override
	public List<ProductConsumption> getProductConsumptions(
			long userId, long accountId, long productEntryId)
		throws PortalException {

		return productConsumptionPersistence.findByU_AI_PEI(
			userId, accountId, productEntryId);
	}

	public int getProductEntryProductConsumptionsCount(long productEntryId)
		throws PortalException {

		return productConsumptionPersistence.countByProductEntryId(
			productEntryId);
	}

	public int getProductPurchaseProductConsumptionsCount(
			long productPurchaseId)
		throws PortalException {

		return productConsumptionPersistence.countByProductPurchaseId(
			productPurchaseId);
	}

	@Indexable(type = IndexableType.REINDEX)
	public ProductConsumption reindex(long productConsumptionId)
		throws PortalException {

		ProductConsumption productConsumption =
			productConsumptionPersistence.findByPrimaryKey(
				productConsumptionId);

		reindexProductPurchaseView(productConsumption);

		return productConsumption;
	}

	public Hits search(
			long companyId, String keywords, int start, int end, Sort sort)
		throws PortalException {

		try {
			Indexer<ProductConsumption> indexer =
				IndexerRegistryUtil.nullSafeGetIndexer(
					ProductConsumption.class);

			SearchContext searchContext = new SearchContext();

			searchContext.setAndSearch(false);

			Map<String, Serializable> attributes = new HashMap<>();

			attributes.put("accountKey", keywords);
			attributes.put("externalLinkEntityIds", keywords);
			attributes.put("name", keywords);
			attributes.put("productEntryKey", keywords);

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

	protected void reindexProductPurchaseView(
			ProductConsumption productConsumption)
		throws PortalException {

		ProductPurchaseView productPurchaseView = new ProductPurchaseViewImpl();

		productPurchaseView.setCompanyId(productConsumption.getCompanyId());
		productPurchaseView.setAccountId(productConsumption.getAccountId());
		productPurchaseView.setProductEntryId(
			productConsumption.getProductEntryId());

		TransactionCommitCallbackUtil.registerCallback(
			() -> {
				Indexer<ProductPurchaseView> indexer =
					_indexerRegistry.getIndexer(ProductPurchaseView.class);

				indexer.reindex(productPurchaseView);

				return null;
			});
	}

	protected void validate(
			long accountId, long productEntryId, long productPurchaseId)
		throws PortalException {

		_accountLocalService.getAccount(accountId);

		productEntryPersistence.findByPrimaryKey(productEntryId);

		if (productPurchaseId > 0) {
			ProductPurchase productPurchase =
				productPurchasePersistence.findByPrimaryKey(productPurchaseId);

			if (productEntryId != productPurchase.getProductEntryId()) {
				throw new ProductConsumptionProductEntryException();
			}
		}
	}

	@Reference
	private AccountLocalService _accountLocalService;

	@Reference
	private ExternalLinkLocalService _externalLinkLocalService;

	@Reference
	private IndexerRegistry _indexerRegistry;

	@Reference
	private ProductFieldLocalService _productFieldLocalService;

}