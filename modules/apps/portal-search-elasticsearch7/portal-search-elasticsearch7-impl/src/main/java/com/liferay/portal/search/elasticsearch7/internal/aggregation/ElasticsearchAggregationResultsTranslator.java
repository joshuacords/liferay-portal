/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.aggregation;

import com.liferay.portal.search.aggregation.Aggregation;
import com.liferay.portal.search.aggregation.AggregationResult;
import com.liferay.portal.search.aggregation.pipeline.PipelineAggregation;

import java.util.List;
import java.util.stream.Stream;

import org.elasticsearch.search.aggregations.Aggregations;

/**
 * @author André de Oliveira
 */
public class ElasticsearchAggregationResultsTranslator {

	public ElasticsearchAggregationResultsTranslator(
		AggregationResultTranslatorFactory aggregationResultTranslatorFactory,
		PipelineAggregationResultTranslatorFactory
			pipelineAggregationResultTranslatorFactory,
		AggregationLookup aggregationLookup,
		PipelineAggregationLookup pipelineAggregationLookup) {

		_aggregationResultTranslatorFactory =
			aggregationResultTranslatorFactory;
		_pipelineAggregationResultTranslatorFactory =
			pipelineAggregationResultTranslatorFactory;
		_aggregationLookup = aggregationLookup;
		_pipelineAggregationLookup = pipelineAggregationLookup;
	}

	public Stream<AggregationResult> translate(
		Aggregations elasticsearchAggregations) {

		Stream<org.elasticsearch.search.aggregations.Aggregation> stream =
			getElasticsearchAggregations(elasticsearchAggregations);

		return stream.map(
			this::translate
		).filter(
			aggregationResult -> aggregationResult != null
		);
	}

	public interface AggregationLookup {

		public Aggregation lookup(String name);

	}

	public interface PipelineAggregationLookup {

		public PipelineAggregation lookup(String name);

	}

	protected Stream<org.elasticsearch.search.aggregations.Aggregation>
		getElasticsearchAggregations(Aggregations aggregations) {

		List<org.elasticsearch.search.aggregations.Aggregation> list =
			aggregations.asList();

		return list.stream();
	}

	protected AggregationResult translate(
		org.elasticsearch.search.aggregations.Aggregation
			elasticsearchAggregation) {

		String name = elasticsearchAggregation.getName();

		Aggregation aggregation = _aggregationLookup.lookup(name);

		if (aggregation != null) {
			return aggregation.accept(
				_aggregationResultTranslatorFactory.
					createAggregationResultTranslator(
						elasticsearchAggregation));
		}

		PipelineAggregation pipelineAggregation =
			_pipelineAggregationLookup.lookup(name);

		if (pipelineAggregation != null) {
			return pipelineAggregation.accept(
				_pipelineAggregationResultTranslatorFactory.
					createPipelineAggregationResultTranslator(
						elasticsearchAggregation));
		}

		return null;
	}

	private final AggregationLookup _aggregationLookup;
	private final AggregationResultTranslatorFactory
		_aggregationResultTranslatorFactory;
	private final PipelineAggregationLookup _pipelineAggregationLookup;
	private final PipelineAggregationResultTranslatorFactory
		_pipelineAggregationResultTranslatorFactory;

}