/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.internal.highlight;

import org.elasticsearch.action.search.SearchRequestBuilder;

/**
 * @author Michael C. Han
 */
public interface HighlighterTranslator {

	public void translate(
		SearchRequestBuilder searchRequestBuilder, String[] highlightFieldNames,
		boolean highlightRequireFieldMatch, int highlightFragmentSize,
		int highlightSnippetSize, boolean luceneSyntax);

}