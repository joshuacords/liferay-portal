/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.solr8.internal.facet;

import com.liferay.portal.json.JSONFactoryImpl;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.search.solr8.internal.SolrIndexingFixture;
import com.liferay.portal.search.test.util.facet.BaseAggregationFilteringTestCase;
import com.liferay.portal.search.test.util.indexing.IndexingFixture;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.Collections;

import org.apache.solr.client.solrj.SolrQuery;

import org.junit.ClassRule;
import org.junit.Rule;

/**
 * @author André de Oliveira
 */
public class AggregationFilteringTest extends BaseAggregationFilteringTestCase {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	protected void addFacetProcessor(
		String className, FacetProcessor<SolrQuery> facetProcessor,
		CompositeFacetProcessor compositeFacetProcessor) {

		compositeFacetProcessor.setFacetProcessor(
			facetProcessor, Collections.singletonMap("class.name", className));
	}

	protected FacetProcessor createFacetProcessor() {
		CompositeFacetProcessor compositeFacetProcessor =
			new CompositeFacetProcessor();

		compositeFacetProcessor.setDefaultFacetProcessor(
			new DefaultFacetProcessor() {
				{
					setJSONFactory(_jsonFactory);
				}
			});

		addFacetProcessor(
			"com.liferay.portal.search.internal.facet.ModifiedFacetImpl",
			new ModifiedFacetProcessor() {
				{
					jsonFactory = _jsonFactory;
				}
			},
			compositeFacetProcessor);

		return compositeFacetProcessor;
	}

	@Override
	protected IndexingFixture createIndexingFixture() throws Exception {
		SolrIndexingFixture solrIndexingFixture = new SolrIndexingFixture();

		solrIndexingFixture.setFacetProcessor(createFacetProcessor());

		return solrIndexingFixture;
	}

	private final JSONFactory _jsonFactory = new JSONFactoryImpl();

}