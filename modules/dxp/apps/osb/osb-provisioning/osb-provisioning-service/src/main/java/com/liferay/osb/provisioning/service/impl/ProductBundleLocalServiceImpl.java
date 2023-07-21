/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.service.impl;

import com.liferay.osb.provisioning.exception.ProductBundleNameException;
import com.liferay.osb.provisioning.model.ProductBundle;
import com.liferay.osb.provisioning.service.base.ProductBundleLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

/**
 * @author Yuanyuan Huang
 */
@Component(
	property = "model.class.name=com.liferay.osb.provisioning.model.ProductBundle",
	service = AopService.class
)
public class ProductBundleLocalServiceImpl
	extends ProductBundleLocalServiceBaseImpl {

	@Indexable(type = IndexableType.REINDEX)
	public ProductBundle addProductBundle(long userId, String name)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		validate(0, name);

		long productBundleId = counterLocalService.increment();

		ProductBundle productBundle = productBundlePersistence.create(
			productBundleId);

		productBundle.setCompanyId(user.getCompanyId());
		productBundle.setUserId(userId);
		productBundle.setName(name);

		return productBundlePersistence.update(productBundle);
	}

	@Override
	public ProductBundle deleteProductBundle(long productBundleId)
		throws PortalException {

		productBundleProductsPersistence.removeByProductBundleId(
			productBundleId);

		return productBundlePersistence.remove(productBundleId);
	}

	public Hits search(
			long companyId, String keywords, int start, int end, Sort sort)
		throws PortalException {

		try {
			Indexer<ProductBundle> indexer =
				IndexerRegistryUtil.nullSafeGetIndexer(ProductBundle.class);

			SearchContext searchContext = new SearchContext();

			searchContext.setAndSearch(false);

			Map<String, Serializable> attributes = new HashMap<>();

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
	public ProductBundle updateProductBundle(long productBundleId, String name)
		throws PortalException {

		validate(productBundleId, name);

		ProductBundle productBundle = productBundlePersistence.findByPrimaryKey(
			productBundleId);

		productBundle.setName(name);

		return productBundlePersistence.update(productBundle);
	}

	protected void validate(long productBundleId, String name)
		throws PortalException {

		if (Validator.isNull(name)) {
			throw new ProductBundleNameException();
		}

		ProductBundle productBundle = productBundlePersistence.fetchByName(
			name);

		if ((productBundle != null) &&
			(productBundle.getProductBundleId() != productBundleId)) {

			throw new ProductBundleNameException.MustNotBeDuplicate(name);
		}
	}

}