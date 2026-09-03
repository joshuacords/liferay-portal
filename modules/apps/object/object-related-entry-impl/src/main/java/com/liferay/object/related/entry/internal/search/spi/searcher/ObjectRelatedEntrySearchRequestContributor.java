/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.related.entry.internal.search.spi.searcher;

import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.related.entry.constants.ObjectRelatedEntryConstants;
import com.liferay.object.related.entry.internal.helper.ObjectRelatedEntryHelper;
import com.liferay.object.service.ObjectDefinitionLocalService;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.search.searcher.SearchRequest;
import com.liferay.portal.search.searcher.SearchRequestBuilder;
import com.liferay.portal.search.searcher.SearchRequestBuilderFactory;
import com.liferay.portal.search.spi.searcher.SearchRequestContributor;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Joshua Cords
 */
@Component(
	property = "search.request.contributor.id=com.liferay.object.related.entry",
	service = SearchRequestContributor.class
)
public class ObjectRelatedEntrySearchRequestContributor
	implements SearchRequestContributor {

	@Override
	public SearchRequest contribute(SearchRequest searchRequest) {
		SearchRequestBuilder searchRequestBuilder =
			_searchRequestBuilderFactory.builder(searchRequest);

		if (!searchRequestBuilder.withSearchContextGet(
				searchContext -> GetterUtil.getBoolean(
					searchContext.getAttribute(
						ObjectRelatedEntryConstants.
							ATTRIBUTE_INCLUDE_OBJECT_RELATED_ENTRIES)))) {

			return searchRequest;
		}

		String[] hostClassNames = _getHostClassNames(
			searchRequest, searchRequestBuilder);

		Set<String> entryClassNames = new LinkedHashSet<>(
			Arrays.asList(hostClassNames));

		long companyId = searchRequestBuilder.withSearchContextGet(
			SearchContext::getCompanyId);

		for (String hostClassName : hostClassNames) {
			ObjectDefinition objectDefinition =
				_objectDefinitionLocalService.fetchObjectDefinitionByClassName(
					companyId, hostClassName);

			if (!_objectRelatedEntryHelper.isRelatedEntryObjectDefinition(
					objectDefinition)) {

				continue;
			}

			for (ObjectDefinition descendantObjectDefinition :
					_objectRelatedEntryHelper.getDescendantObjectDefinitions(
						objectDefinition)) {

				entryClassNames.add(descendantObjectDefinition.getClassName());
			}
		}

		if (entryClassNames.size() == hostClassNames.length) {
			return searchRequest;
		}

		String[] entryClassNamesArray = entryClassNames.toArray(new String[0]);

		searchRequestBuilder.entryClassNames(entryClassNamesArray);

		if (ListUtil.isNotEmpty(searchRequest.getModelIndexerClassNames())) {
			searchRequestBuilder.modelIndexerClassNames(entryClassNamesArray);
		}

		return searchRequestBuilder.build();
	}

	private String[] _getHostClassNames(
		SearchRequest searchRequest,
		SearchRequestBuilder searchRequestBuilder) {

		String[] hostClassNames = searchRequestBuilder.withSearchContextGet(
			searchContext -> (String[])searchContext.getAttribute(
				ObjectRelatedEntryConstants.
					ATTRIBUTE_OBJECT_RELATED_ENTRY_HOST_CLASS_NAMES));

		if (hostClassNames != null) {
			return hostClassNames;
		}

		List<String> entryClassNames = searchRequest.getEntryClassNames();

		if (entryClassNames.isEmpty()) {
			entryClassNames = searchRequest.getModelIndexerClassNames();
		}

		String[] entryClassNamesArray = entryClassNames.toArray(new String[0]);

		searchRequestBuilder.withSearchContext(
			searchContext -> searchContext.setAttribute(
				ObjectRelatedEntryConstants.
					ATTRIBUTE_OBJECT_RELATED_ENTRY_HOST_CLASS_NAMES,
				entryClassNamesArray));

		return entryClassNamesArray;
	}

	@Reference
	private ObjectDefinitionLocalService _objectDefinitionLocalService;

	@Reference
	private ObjectRelatedEntryHelper _objectRelatedEntryHelper;

	@Reference
	private SearchRequestBuilderFactory _searchRequestBuilderFactory;

}