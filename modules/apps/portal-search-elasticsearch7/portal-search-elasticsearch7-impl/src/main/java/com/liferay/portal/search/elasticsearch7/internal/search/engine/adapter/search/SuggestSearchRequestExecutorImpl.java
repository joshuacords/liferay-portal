/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.search.engine.adapter.search;

import com.liferay.portal.kernel.search.suggest.Suggester;
import com.liferay.portal.kernel.search.suggest.SuggesterTranslator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.elasticsearch7.internal.connection.ElasticsearchClientResolver;
import com.liferay.portal.search.engine.adapter.search.SuggestSearchRequest;
import com.liferay.portal.search.engine.adapter.search.SuggestSearchResponse;
import com.liferay.portal.search.engine.adapter.search.SuggestSearchResult;

import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchAction;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestionBuilder;
import org.elasticsearch.search.suggest.term.TermSuggestion;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(service = SuggestSearchRequestExecutor.class)
public class SuggestSearchRequestExecutorImpl
	implements SuggestSearchRequestExecutor {

	@Override
	public SuggestSearchResponse execute(
		SuggestSearchRequest suggestSearchRequest) {

		SearchRequestBuilder searchRequestBuilder = createSearchRequestBuilder(
			suggestSearchRequest);

		SearchResponse searchResponse = searchRequestBuilder.get();

		Suggest suggest = searchResponse.getSuggest();

		SuggestSearchResponse suggestSearchResponse =
			new SuggestSearchResponse();

		if (suggest == null) {
			return suggestSearchResponse;
		}

		for (Suggest.Suggestion
				<? extends Suggest.Suggestion.Entry
					<? extends Suggest.Suggestion.Entry.Option>> suggestion :
						suggest) {

			SuggestSearchResult suggestSearchResult = translate(suggestion);

			suggestSearchResponse.addSuggestSearchResult(suggestSearchResult);
		}

		return suggestSearchResponse;
	}

	protected SearchRequestBuilder createSearchRequestBuilder(
		SuggestSearchRequest suggestSearchRequest) {

		SearchRequestBuilder searchRequestBuilder = new SearchRequestBuilder(
			_elasticsearchClientResolver.getClient(true),
			SearchAction.INSTANCE);

		searchRequestBuilder.setIndices(suggestSearchRequest.getIndexNames());

		Map<String, Suggester> suggesterMap =
			suggestSearchRequest.getSuggesterMap();

		SuggestBuilder suggestBuilder = new SuggestBuilder();

		if (!Validator.isBlank(suggestSearchRequest.getGlobalText())) {
			suggestBuilder.setGlobalText(suggestSearchRequest.getGlobalText());
		}

		for (Map.Entry<String, Suggester> entry : suggesterMap.entrySet()) {
			Suggester suggester = entry.getValue();
			String suggesterName = entry.getKey();

			SuggestionBuilder suggestionBuilder =
				_suggesterTranslator.translate(suggester, null);

			suggestBuilder.addSuggestion(suggesterName, suggestionBuilder);
		}

		searchRequestBuilder.suggest(suggestBuilder);

		return searchRequestBuilder;
	}

	@Reference(unbind = "-")
	protected void setElasticsearchClientResolver(
		ElasticsearchClientResolver elasticsearchClientResolver) {

		_elasticsearchClientResolver = elasticsearchClientResolver;
	}

	@Reference(target = "(search.engine.impl=Elasticsearch)", unbind = "-")
	protected void setSuggesterTranslator(
		SuggesterTranslator<SuggestionBuilder> suggesterTranslator) {

		_suggesterTranslator = suggesterTranslator;
	}

	protected SuggestSearchResult.Entry.Option translate(
		Suggest.Suggestion.Entry.Option suggestionEntryOption) {

		Text text = suggestionEntryOption.getText();

		SuggestSearchResult.Entry.Option suggesterResultEntryOption =
			new SuggestSearchResult.Entry.Option(
				text.string(), suggestionEntryOption.getScore());

		if (suggestionEntryOption.getHighlighted() != null) {
			Text highlightedText = suggestionEntryOption.getHighlighted();

			suggesterResultEntryOption.setHighlightedText(
				highlightedText.string());
		}

		if (suggestionEntryOption instanceof TermSuggestion.Entry.Option) {
			TermSuggestion.Entry.Option termSuggestionEntryOption =
				(TermSuggestion.Entry.Option)suggestionEntryOption;

			suggesterResultEntryOption.setFrequency(
				termSuggestionEntryOption.getFreq());
		}

		return suggesterResultEntryOption;
	}

	protected SuggestSearchResult.Entry translate(
		Suggest.Suggestion.Entry<? extends Suggest.Suggestion.Entry.Option>
			suggestionEntry) {

		Text text = suggestionEntry.getText();

		SuggestSearchResult.Entry suggesterResultEntry =
			new SuggestSearchResult.Entry(text.string());

		List<? extends Suggest.Suggestion.Entry.Option> suggestionEntryOptions =
			suggestionEntry.getOptions();

		for (Suggest.Suggestion.Entry.Option suggestionEntryOption :
				suggestionEntryOptions) {

			SuggestSearchResult.Entry.Option suggesterResultEntryOption =
				translate(suggestionEntryOption);

			suggesterResultEntry.addOption(suggesterResultEntryOption);
		}

		return suggesterResultEntry;
	}

	protected SuggestSearchResult translate(
		Suggest.Suggestion
			<? extends Suggest.Suggestion.Entry
				<? extends Suggest.Suggestion.Entry.Option>> suggestion) {

		SuggestSearchResult suggestSearchResult = new SuggestSearchResult(
			suggestion.getName());

		for (Suggest.Suggestion.Entry<? extends Suggest.Suggestion.Entry.Option>
				suggestionEntry : suggestion) {

			SuggestSearchResult.Entry suggesterResultEntry = translate(
				suggestionEntry);

			suggestSearchResult.addEntry(suggesterResultEntry);
		}

		return suggestSearchResult;
	}

	private ElasticsearchClientResolver _elasticsearchClientResolver;
	private SuggesterTranslator<SuggestionBuilder> _suggesterTranslator;

}