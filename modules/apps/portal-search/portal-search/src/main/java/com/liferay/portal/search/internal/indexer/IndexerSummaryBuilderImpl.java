/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.indexer;

import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.search.indexer.IndexerSummaryBuilder;
import com.liferay.portal.search.spi.model.result.contributor.ModelSummaryContributor;

import java.util.Locale;

/**
 * @author Michael C. Han
 */
public class IndexerSummaryBuilderImpl implements IndexerSummaryBuilder {

	public IndexerSummaryBuilderImpl(
		ModelSummaryContributor modelSummaryContributor,
		IndexerPostProcessorsHolder indexerPostProcessorsHolder) {

		_modelSummaryContributor = modelSummaryContributor;
		_indexerPostProcessorsHolder = indexerPostProcessorsHolder;
	}

	@Override
	public Summary getSummary(
		Document document, String snippet, Locale locale) {

		if (_modelSummaryContributor == null) {
			return null;
		}

		Summary summary = _modelSummaryContributor.getSummary(
			document, locale, snippet);

		_indexerPostProcessorsHolder.forEach(
			indexerPostProcessor -> indexerPostProcessor.postProcessSummary(
				summary, document, locale, snippet));

		return summary;
	}

	private final IndexerPostProcessorsHolder _indexerPostProcessorsHolder;
	private final ModelSummaryContributor _modelSummaryContributor;

}