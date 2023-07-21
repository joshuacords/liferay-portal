/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.searcher;

import com.liferay.portal.search.spi.searcher.SearchRequestContributor;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * @author André de Oliveira
 */
public interface SearchRequestContributorsHolder {

	public Stream<SearchRequestContributor> stream();

	public Stream<SearchRequestContributor> stream(
		Collection<String> includeIds, Collection<String> excludeIds);

}