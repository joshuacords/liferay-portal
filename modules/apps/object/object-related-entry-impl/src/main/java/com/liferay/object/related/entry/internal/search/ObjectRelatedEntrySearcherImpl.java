/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.related.entry.internal.search;

import com.liferay.object.related.entry.constants.ObjectRelatedEntryConstants;
import com.liferay.object.related.entry.search.ObjectRelatedEntrySearchResponse;
import com.liferay.object.related.entry.search.ObjectRelatedEntrySearchResult;
import com.liferay.object.related.entry.search.ObjectRelatedEntrySearcher;
import com.liferay.petra.string.CharPool;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.search.document.Document;
import com.liferay.portal.search.hits.SearchHit;
import com.liferay.portal.search.hits.SearchHits;
import com.liferay.portal.search.searcher.SearchRequest;
import com.liferay.portal.search.searcher.SearchRequestBuilder;
import com.liferay.portal.search.searcher.SearchRequestBuilderFactory;
import com.liferay.portal.search.searcher.SearchResponse;
import com.liferay.portal.search.searcher.Searcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Joshua Cords
 */
@Component(service = ObjectRelatedEntrySearcher.class)
public class ObjectRelatedEntrySearcherImpl
	implements ObjectRelatedEntrySearcher {

	@Override
	public ObjectRelatedEntrySearchResponse search(
		SearchRequest searchRequest) {

		SearchRequestBuilder searchRequestBuilder =
			_searchRequestBuilderFactory.builder(searchRequest);

		searchRequestBuilder.withSearchContext(
			searchContext -> {
				searchContext.setAttribute(
					ObjectRelatedEntryConstants.
						ATTRIBUTE_INCLUDE_OBJECT_RELATED_ENTRIES,
					Boolean.TRUE);

				QueryConfig queryConfig = searchContext.getQueryConfig();

				if (!queryConfig.isAllFieldsSelected()) {
					queryConfig.addSelectedFieldNames(_SELECTED_FIELD_NAMES);
				}
			});

		SearchResponse searchResponse = _searcher.search(
			searchRequestBuilder.build());

		return new ObjectRelatedEntrySearchResponse(
			_fold(
				_getHostClassNames(searchRequest, searchResponse),
				searchResponse),
			searchResponse);
	}

	private List<ObjectRelatedEntrySearchResult> _fold(
		List<String> hostClassNames, SearchResponse searchResponse) {

		Set<Long> hostClassNameIds = new HashSet<>();

		for (String hostClassName : hostClassNames) {
			hostClassNameIds.add(_portal.getClassNameId(hostClassName));
		}

		Map<ObjectRelatedEntrySearchResult, ObjectRelatedEntrySearchResult>
			searchResults = new LinkedHashMap<>();

		SearchHits searchHits = searchResponse.getSearchHits();

		for (SearchHit searchHit : searchHits.getSearchHits()) {
			Document document = searchHit.getDocument();

			String entryClassName = document.getString(Field.ENTRY_CLASS_NAME);

			if (hostClassNames.contains(entryClassName)) {
				ObjectRelatedEntrySearchResult searchResult =
					searchResults.computeIfAbsent(
						new ObjectRelatedEntrySearchResult(
							entryClassName,
							GetterUtil.getLong(
								document.getLong(Field.ENTRY_CLASS_PK))),
						key -> key);

				searchResult.setSearchHit(searchHit);

				continue;
			}

			ObjectRelatedEntrySearchResult parentSearchResult =
				_getParentSearchResult(document, hostClassNameIds);

			if (parentSearchResult == null) {
				continue;
			}

			ObjectRelatedEntrySearchResult searchResult =
				searchResults.computeIfAbsent(parentSearchResult, key -> key);

			searchResult.addRelatedEntrySearchHit(searchHit);
		}

		return new ArrayList<>(searchResults.values());
	}

	private List<String> _getHostClassNames(
		SearchRequest searchRequest, SearchResponse searchResponse) {

		String[] hostClassNames = searchResponse.withSearchContextGet(
			searchContext -> (String[])searchContext.getAttribute(
				ObjectRelatedEntryConstants.
					ATTRIBUTE_OBJECT_RELATED_ENTRY_HOST_CLASS_NAMES));

		if (hostClassNames != null) {
			return Arrays.asList(hostClassNames);
		}

		return searchRequest.getEntryClassNames();
	}

	private ObjectRelatedEntrySearchResult _getParentSearchResult(
		Document document, Set<Long> hostClassNameIds) {

		if (!GetterUtil.getBoolean(document.getBoolean(Field.RELATED_ENTRY))) {
			return null;
		}

		for (String ancestorKey :
				document.getStrings(
					ObjectRelatedEntryConstants.
						FIELD_RELATED_ENTRY_ANCESTOR_KEYS)) {

			String[] ancestorKeyParts = StringUtil.split(
				ancestorKey, CharPool.DASH);

			if (ancestorKeyParts.length != 2) {
				continue;
			}

			long classNameId = GetterUtil.getLong(ancestorKeyParts[0]);

			if (!hostClassNameIds.contains(classNameId)) {
				continue;
			}

			return new ObjectRelatedEntrySearchResult(
				_portal.getClassName(classNameId),
				GetterUtil.getLong(ancestorKeyParts[1]));
		}

		long classNameId = GetterUtil.getLong(
			document.getLong(Field.CLASS_NAME_ID));
		long classPK = GetterUtil.getLong(document.getLong(Field.CLASS_PK));

		if ((classPK <= 0) || !hostClassNameIds.contains(classNameId)) {
			return null;
		}

		return new ObjectRelatedEntrySearchResult(
			_portal.getClassName(classNameId), classPK);
	}

	private static final String[] _SELECTED_FIELD_NAMES = {
		Field.CLASS_NAME_ID, Field.CLASS_PK, Field.ENTRY_CLASS_NAME,
		Field.ENTRY_CLASS_PK, Field.RELATED_ENTRY,
		ObjectRelatedEntryConstants.FIELD_RELATED_ENTRY_ANCESTOR_KEYS
	};

	@Reference
	private Portal _portal;

	@Reference
	private Searcher _searcher;

	@Reference
	private SearchRequestBuilderFactory _searchRequestBuilderFactory;

}