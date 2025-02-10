/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.facet.category;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.facet.config.FacetConfiguration;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.search.facet.Facet;
import com.liferay.portal.search.facet.category.CategoryFacetFactory;
import com.liferay.portal.search.facet.category.CategoryFacetSearchContributor;
import com.liferay.portal.search.searcher.SearchRequestBuilder;

import java.util.ArrayList;
import java.util.List;
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

	@Reference
	private AssetCategoryLocalService _assetCategoryLocalService;

	@Reference
	private CategoryFacetFactory _categoryFacetFactory;

	@Reference
	private GroupLocalService _groupLocalService;

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
			facet.setFacetConfiguration(
				buildFacetConfiguration(facet.getFieldName()));

			if (_selectedGroupCategoryExternalReferenceCodes != null) {
				String fieldName = facet.getFieldName();

				if (fieldName.equals(
						"groupAssetVocabularyCategoryExternalReferenceCodes")) {

					facet.select(
						_getSelections(
							_selectedGroupCategoryExternalReferenceCodes));
				}
				else {
					facet.select(
						ArrayUtil.toStringArray(
							_selectedGroupCategoryExternalReferenceCodes));
				}
			}

			return facet;
		}

		@Override
		public CategoryFacetBuilder frequencyThreshold(int frequencyThreshold) {
			_frequencyThreshold = frequencyThreshold;

			return this;
		}

		@Override
		public CategoryFacetBuilder groupVocabularyExternalReferenceCodes(
			String[] groupVocabularyExternalReferenceCodes) {

			_groupVocabularyExternalReferenceCodes =
				groupVocabularyExternalReferenceCodes;

			return this;
		}

		@Override
		public CategoryFacetBuilder maxTerms(int maxTerms) {
			_maxTerms = maxTerms;

			return this;
		}

		@Override
		public CategoryFacetBuilder selectedGroupCategoryExternalReferenceCodes(
			String... selectedGroupCategoryExternalReferenceCodes) {

			_selectedGroupCategoryExternalReferenceCodes =
				selectedGroupCategoryExternalReferenceCodes;

			return this;
		}

		protected FacetConfiguration buildFacetConfiguration(String fieldName) {
			FacetConfiguration facetConfiguration = new FacetConfiguration();

			facetConfiguration.setFieldName(fieldName);
			facetConfiguration.setLabel("any-category");
			facetConfiguration.setOrder("OrderHitsDesc");
			facetConfiguration.setStatic(false);
			facetConfiguration.setWeight(1.6);

			JSONObject jsonObject = facetConfiguration.getData();

			jsonObject.put(
				"frequencyThreshold", _frequencyThreshold
			).put(
				"include", _getIncludeRegexString(fieldName)
			).put(
				"maxTerms", _maxTerms
			);

			return facetConfiguration;
		}

		private String _getIncludeRegexString(String fieldName) {
			if (ArrayUtil.isEmpty(_groupVocabularyExternalReferenceCodes) ||
				fieldName.equals("assetCategoryIds")) {

				return null;
			}

			StringBundler sb = new StringBundler(
				_groupVocabularyExternalReferenceCodes.length * 8);

			for (String groupVocabularyExternalReferenceCode :
					_groupVocabularyExternalReferenceCodes) {

				sb.append(
					StringUtil.replace(
						groupVocabularyExternalReferenceCode,
						CharPool.AMPERSAND,
						StringPool.BACK_SLASH + StringPool.AMPERSAND));
				sb.append(StringPool.BACK_SLASH);
				sb.append(StringPool.AMPERSAND);
				sb.append(StringPool.BACK_SLASH);
				sb.append(StringPool.AMPERSAND);
				sb.append(StringPool.PERIOD);
				sb.append(StringPool.STAR);
				sb.append(StringPool.PIPE);
			}

			if (sb.index() == 0) {
				return null;
			}

			sb.setIndex(sb.index() - 1);

			return sb.toString();
		}

		private String[] _getSelections(long[] selectedCategoryIds) {
			List<String> selections = new ArrayList<>();

			for (long selectedCategoryId : selectedCategoryIds) {
				AssetCategory assetCategory =
					_assetCategoryLocalService.fetchAssetCategory(
						selectedCategoryId);

				if (assetCategory != null) {
					selections.add(
						assetCategory.getVocabularyId() + StringPool.DASH +
							assetCategory.getCategoryId());
				}
				else {
					selections.add(String.valueOf(selectedCategoryId));
				}
			}

			return ArrayUtil.toStringArray(selections);
		}

		private String[] _getSelections(
			String[] selectedGroupCategoryExternalReferenceCodes) {

			List<String> selections = new ArrayList<>();

			for (String selectedGroupCategoryExternalReferenceCode :
					selectedGroupCategoryExternalReferenceCodes) {

				String[] groupCategoryExternalReferenceCodes = StringUtil.split(
					selectedGroupCategoryExternalReferenceCode,
					StringPool.AMPERSAND + StringPool.AMPERSAND);

				Group group =
					_groupLocalService.fetchGroupByExternalReferenceCode(
						groupCategoryExternalReferenceCodes[0],
						CompanyThreadLocal.getCompanyId());

				AssetCategory assetCategory =
					_assetCategoryLocalService.
						fetchAssetCategoryByExternalReferenceCode(
							groupCategoryExternalReferenceCodes[2],
							group.getGroupId());

				// do we even need all this logic??

				if (assetCategory != null) {
					selections.add(selectedGroupCategoryExternalReferenceCode);
				}
				//				else {
				//					selections.add(
				//					String.valueOf(selectedCategoryId));
				//				}
			}

			return ArrayUtil.toStringArray(selections);
		}

		private String _aggregationName;
		private int _frequencyThreshold;
		private String[] _groupVocabularyExternalReferenceCodes;
		private int _maxTerms;
		private final SearchContext _searchContext;
		private String[] _selectedGroupCategoryExternalReferenceCodes;

	}

}