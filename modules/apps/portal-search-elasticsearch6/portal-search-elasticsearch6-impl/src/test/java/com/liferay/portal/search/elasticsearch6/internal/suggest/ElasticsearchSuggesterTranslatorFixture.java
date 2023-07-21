/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.internal.suggest;

/**
 * @author Michael C. Han
 */
public class ElasticsearchSuggesterTranslatorFixture {

	public ElasticsearchSuggesterTranslatorFixture() {
		_elasticsearchSuggesterTranslator =
			new ElasticsearchSuggesterTranslator() {
				{
					setCompletionSuggesterTranslator(
						new CompletionSuggesterTranslatorImpl());

					setPhraseSuggesterTranslator(
						new PhraseSuggesterTranslatorImpl());

					setTermSuggesterTranslator(
						new TermSuggesterTranslatorImpl());
				}
			};
	}

	public ElasticsearchSuggesterTranslator
		getElasticsearchSuggesterTranslator() {

		return _elasticsearchSuggesterTranslator;
	}

	private final ElasticsearchSuggesterTranslator
		_elasticsearchSuggesterTranslator;

}