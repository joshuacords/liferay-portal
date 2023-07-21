/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.indexer;

import com.liferay.portal.search.spi.model.query.contributor.KeywordQueryContributor;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author André de Oliveira
 */
public class ModelKeywordQueryContributorsHolderImpl
	implements ModelKeywordQueryContributorsHolder {

	public ModelKeywordQueryContributorsHolderImpl(
		Iterable<KeywordQueryContributor> keywordQueryContributors) {

		_keywordQueryContributors = keywordQueryContributors;
	}

	@Override
	public Stream<KeywordQueryContributor> getAll() {
		return StreamSupport.stream(
			_keywordQueryContributors.spliterator(), false);
	}

	private final Iterable<KeywordQueryContributor> _keywordQueryContributors;

}