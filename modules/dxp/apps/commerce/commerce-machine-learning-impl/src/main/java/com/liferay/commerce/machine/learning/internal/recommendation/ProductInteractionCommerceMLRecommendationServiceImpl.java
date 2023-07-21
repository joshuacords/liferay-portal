/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.machine.learning.internal.recommendation;

import com.liferay.commerce.machine.learning.internal.recommendation.constants.CommerceMLRecommendationField;
import com.liferay.commerce.machine.learning.internal.recommendation.model.ProductInteractionCommerceMLRecommendationImpl;
import com.liferay.commerce.machine.learning.internal.search.api.CommerceMLIndexer;
import com.liferay.commerce.machine.learning.recommendation.model.ProductInteractionCommerceMLRecommendation;
import com.liferay.commerce.machine.learning.recommendation.service.ProductInteractionCommerceMLRecommendationService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.search.engine.adapter.search.SearchSearchRequest;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Ferrari
 */
@Component(
	immediate = true,
	service = ProductInteractionCommerceMLRecommendationService.class
)
public class ProductInteractionCommerceMLRecommendationServiceImpl
	extends BaseCommerceMLRecommendationServiceImpl
		<ProductInteractionCommerceMLRecommendation>
	implements ProductInteractionCommerceMLRecommendationService {

	@Override
	public ProductInteractionCommerceMLRecommendation
			addProductInteractionCommerceMLRecommendation(
				ProductInteractionCommerceMLRecommendation
					productInteractionCommerceMLRecommendation)
		throws PortalException {

		return addCommerceMLRecommendation(
			productInteractionCommerceMLRecommendation,
			_commerceMLIndexer.getIndexName(
				productInteractionCommerceMLRecommendation.getCompanyId()),
			_commerceMLIndexer.getDocumentType());
	}

	@Override
	public ProductInteractionCommerceMLRecommendation create() {
		return new ProductInteractionCommerceMLRecommendationImpl();
	}

	@Override
	public List<ProductInteractionCommerceMLRecommendation>
			getProductInteractionCommerceMLRecommendations(
				long companyId, long cpDefinition)
		throws PortalException {

		SearchSearchRequest searchSearchRequest = getSearchSearchRequest(
			_commerceMLIndexer.getIndexName(companyId), companyId,
			cpDefinition);

		Sort rankSort = SortFactoryUtil.create(
			CommerceMLRecommendationField.RANK, Sort.INT_TYPE, false);

		searchSearchRequest.setSorts(new Sort[] {rankSort});

		return getSearchResults(searchSearchRequest);
	}

	@Override
	protected Document toDocument(
		ProductInteractionCommerceMLRecommendation model) {

		Document document = getBaseDocument(model);

		document.addNumber(CommerceMLRecommendationField.RANK, model.getRank());

		return document;
	}

	@Override
	protected ProductInteractionCommerceMLRecommendation toModel(
		Document document) {

		ProductInteractionCommerceMLRecommendation
			productInteractionCommerceMLRecommendationModel =
				getBaseCommerceMLRecommendationModel(
					new ProductInteractionCommerceMLRecommendationImpl(),
					document);

		productInteractionCommerceMLRecommendationModel.setRank(
			GetterUtil.getInteger(
				document.get(CommerceMLRecommendationField.RANK)));

		return productInteractionCommerceMLRecommendationModel;
	}

	@Reference(
		target = "(component.name=com.liferay.commerce.machine.learning.internal.recommendation.search.index.ProductInteractionCommerceMLRecommendationIndexer)"
	)
	private CommerceMLIndexer _commerceMLIndexer;

}