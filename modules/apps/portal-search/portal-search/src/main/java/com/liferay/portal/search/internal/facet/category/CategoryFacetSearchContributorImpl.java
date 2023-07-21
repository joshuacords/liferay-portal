/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.facet.category;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.facet.config.FacetConfiguration;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.search.facet.Facet;
import com.liferay.portal.search.facet.category.CategoryFacetFactory;
import com.liferay.portal.search.facet.category.CategoryFacetSearchContributor;
import com.liferay.portal.search.searcher.SearchRequestBuilder;

import java.util.function.Consumer;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author André de Oliveira
 */
@Component(service = CategoryFacetSearchContributor.class)
public class CategoryFacetSearchContributorImpl
	implements CategoryFacetSearchContributor {

	@Override
	public void contribute(
		SearchRequestBuilder searchRequestBuilder,
		Consumer<CategoryFacetBuilder> categoryFacetBuilderConsumer) {

		Facet facet = searchRequestBuilder.withSearchContextGet(
			searchContext -> {
				CategoryFacetBuilderImpl categoryFacetBuilderImpl =
					new CategoryFacetBuilderImpl(searchContext);

				categoryFacetBuilderConsumer.accept(categoryFacetBuilderImpl);

				return categoryFacetBuilderImpl.build();
			});

		searchRequestBuilder.withFacetContext(
			facetContext -> facetContext.addFacet(facet));
	}

	@Reference(unbind = "-")
	protected void setCategoryFacetFactory(
		CategoryFacetFactory categoryFacetFactory) {

		_categoryFacetFactory = categoryFacetFactory;
	}

	private CategoryFacetFactory _categoryFacetFactory;

	private class CategoryFacetBuilderImpl implements CategoryFacetBuilder {

		public CategoryFacetBuilderImpl(SearchContext searchContext) {
			_searchContext = searchContext;
		}

		@Override
		public CategoryFacetBuilder aggregationName(String aggregationName) {
			_aggregationName = aggregationName;

			return this;
		}

		public Facet build() {
			Facet facet = _categoryFacetFactory.newInstance(_searchContext);

			facet.setAggregationName(_aggregationName);
			facet.setFacetConfiguration(buildFacetConfiguration(facet));

			if (_selectedCategoryIds != null) {
				facet.select(ArrayUtil.toStringArray(_selectedCategoryIds));
			}

			return facet;
		}

		@Override
		public CategoryFacetBuilder frequencyThreshold(int frequencyThreshold) {
			_frequencyThreshold = frequencyThreshold;

			return this;
		}

		@Override
		public CategoryFacetBuilder maxTerms(int maxTerms) {
			_maxTerms = maxTerms;

			return this;
		}

		@Override
		public CategoryFacetBuilder selectedCategoryIds(
			long... selectedCategoryIds) {

			_selectedCategoryIds = selectedCategoryIds;

			return this;
		}

		protected FacetConfiguration buildFacetConfiguration(Facet facet) {
			FacetConfiguration facetConfiguration = new FacetConfiguration();

			facetConfiguration.setFieldName(facet.getFieldName());
			facetConfiguration.setLabel("any-category");
			facetConfiguration.setOrder("OrderHitsDesc");
			facetConfiguration.setStatic(false);
			facetConfiguration.setWeight(1.6);

			JSONObject jsonObject = facetConfiguration.getData();

			jsonObject.put(
				"frequencyThreshold", _frequencyThreshold
			).put(
				"maxTerms", _maxTerms
			);

			return facetConfiguration;
		}

		private String _aggregationName;
		private int _frequencyThreshold;
		private int _maxTerms;
		private final SearchContext _searchContext;
		private long[] _selectedCategoryIds;

	}

}