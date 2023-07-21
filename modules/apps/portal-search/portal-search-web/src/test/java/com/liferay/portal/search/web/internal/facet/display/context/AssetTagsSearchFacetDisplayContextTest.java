/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.web.internal.facet.display.context;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.search.facet.Facet;
import com.liferay.portal.kernel.search.facet.collector.FacetCollector;
import com.liferay.portal.kernel.search.facet.collector.TermCollector;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.search.web.internal.facet.display.builder.AssetTagsSearchFacetDisplayBuilder;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * @author André de Oliveira
 */
public class AssetTagsSearchFacetDisplayContextTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		Mockito.doReturn(
			_facetCollector
		).when(
			_facet
		).getFacetCollector();
	}

	@Test
	public void testEmptySearchResults() throws Exception {
		String facetParam = "";

		AssetTagsSearchFacetDisplayContext assetTagsSearchFacetDisplayContext =
			createDisplayContext(facetParam);

		List<AssetTagsSearchFacetTermDisplayContext>
			assetTagsSearchFacetTermDisplayContexts =
				assetTagsSearchFacetDisplayContext.getTermDisplayContexts();

		Assert.assertEquals(
			assetTagsSearchFacetTermDisplayContexts.toString(), 0,
			assetTagsSearchFacetTermDisplayContexts.size());

		Assert.assertEquals(
			facetParam, assetTagsSearchFacetDisplayContext.getParameterValue());
		Assert.assertTrue(
			assetTagsSearchFacetDisplayContext.isNothingSelected());
		Assert.assertTrue(assetTagsSearchFacetDisplayContext.isRenderNothing());
	}

	@Test
	public void testEmptySearchResultsWithPreviousSelection() throws Exception {
		String term = RandomTestUtil.randomString();

		String facetParam = term;

		AssetTagsSearchFacetDisplayContext assetTagsSearchFacetDisplayContext =
			createDisplayContext(facetParam);

		List<AssetTagsSearchFacetTermDisplayContext>
			assetTagsSearchFacetTermDisplayContexts =
				assetTagsSearchFacetDisplayContext.getTermDisplayContexts();

		Assert.assertEquals(
			assetTagsSearchFacetTermDisplayContexts.toString(), 1,
			assetTagsSearchFacetTermDisplayContexts.size());

		AssetTagsSearchFacetTermDisplayContext
			assetTagsSearchFacetTermDisplayContext =
				assetTagsSearchFacetTermDisplayContexts.get(0);

		Assert.assertEquals(
			term, assetTagsSearchFacetTermDisplayContext.getDisplayName());
		Assert.assertEquals(
			0, assetTagsSearchFacetTermDisplayContext.getFrequency());
		Assert.assertEquals(
			term, assetTagsSearchFacetTermDisplayContext.getValue());
		Assert.assertTrue(assetTagsSearchFacetTermDisplayContext.isSelected());
		Assert.assertTrue(
			assetTagsSearchFacetTermDisplayContext.isFrequencyVisible());

		Assert.assertEquals(
			facetParam, assetTagsSearchFacetDisplayContext.getParameterValue());
		Assert.assertFalse(
			assetTagsSearchFacetDisplayContext.isNothingSelected());
		Assert.assertFalse(
			assetTagsSearchFacetDisplayContext.isRenderNothing());
	}

	@Test
	public void testOneTerm() throws Exception {
		String term = RandomTestUtil.randomString();
		int frequency = RandomTestUtil.randomInt();

		setUpOneTermCollector(term, frequency);

		String facetParam = StringPool.BLANK;

		AssetTagsSearchFacetDisplayContext assetTagsSearchFacetDisplayContext =
			createDisplayContext(facetParam);

		List<AssetTagsSearchFacetTermDisplayContext>
			assetTagsSearchFacetTermDisplayContexts =
				assetTagsSearchFacetDisplayContext.getTermDisplayContexts();

		Assert.assertEquals(
			assetTagsSearchFacetTermDisplayContexts.toString(), 1,
			assetTagsSearchFacetTermDisplayContexts.size());

		AssetTagsSearchFacetTermDisplayContext
			assetTagsSearchFacetTermDisplayContext =
				assetTagsSearchFacetTermDisplayContexts.get(0);

		Assert.assertEquals(
			term, assetTagsSearchFacetTermDisplayContext.getDisplayName());
		Assert.assertEquals(
			frequency, assetTagsSearchFacetTermDisplayContext.getFrequency());
		Assert.assertEquals(
			term, assetTagsSearchFacetTermDisplayContext.getValue());
		Assert.assertFalse(assetTagsSearchFacetTermDisplayContext.isSelected());
		Assert.assertTrue(
			assetTagsSearchFacetTermDisplayContext.isFrequencyVisible());

		Assert.assertEquals(
			facetParam, assetTagsSearchFacetDisplayContext.getParameterValue());
		Assert.assertTrue(
			assetTagsSearchFacetDisplayContext.isNothingSelected());
		Assert.assertFalse(
			assetTagsSearchFacetDisplayContext.isRenderNothing());
	}

	@Test
	public void testOneTermWithPreviousSelection() throws Exception {
		String term = RandomTestUtil.randomString();
		int frequency = RandomTestUtil.randomInt();

		setUpOneTermCollector(term, frequency);

		String facetParam = term;

		AssetTagsSearchFacetDisplayContext assetTagsSearchFacetDisplayContext =
			createDisplayContext(facetParam);

		List<AssetTagsSearchFacetTermDisplayContext>
			assetTagsSearchFacetTermDisplayContexts =
				assetTagsSearchFacetDisplayContext.getTermDisplayContexts();

		Assert.assertEquals(
			assetTagsSearchFacetTermDisplayContexts.toString(), 1,
			assetTagsSearchFacetTermDisplayContexts.size());

		AssetTagsSearchFacetTermDisplayContext
			assetTagsSearchFacetTermDisplayContext =
				assetTagsSearchFacetTermDisplayContexts.get(0);

		Assert.assertEquals(
			term, assetTagsSearchFacetTermDisplayContext.getDisplayName());
		Assert.assertEquals(
			frequency, assetTagsSearchFacetTermDisplayContext.getFrequency());
		Assert.assertEquals(
			term, assetTagsSearchFacetTermDisplayContext.getValue());
		Assert.assertTrue(assetTagsSearchFacetTermDisplayContext.isSelected());
		Assert.assertTrue(
			assetTagsSearchFacetTermDisplayContext.isFrequencyVisible());

		Assert.assertEquals(
			facetParam, assetTagsSearchFacetDisplayContext.getParameterValue());
		Assert.assertFalse(
			assetTagsSearchFacetDisplayContext.isNothingSelected());
		Assert.assertFalse(
			assetTagsSearchFacetDisplayContext.isRenderNothing());
	}

	protected AssetTagsSearchFacetDisplayContext createDisplayContext(
		String facetParam) {

		AssetTagsSearchFacetDisplayBuilder assetTagsSearchFacetDisplayBuilder =
			new AssetTagsSearchFacetDisplayBuilder();

		assetTagsSearchFacetDisplayBuilder.setDisplayStyle("cloud");
		assetTagsSearchFacetDisplayBuilder.setFacet(_facet);
		assetTagsSearchFacetDisplayBuilder.setFrequenciesVisible(true);
		assetTagsSearchFacetDisplayBuilder.setFrequencyThreshold(0);
		assetTagsSearchFacetDisplayBuilder.setMaxTerms(0);
		assetTagsSearchFacetDisplayBuilder.setParameterName(
			_facet.getFieldId());
		assetTagsSearchFacetDisplayBuilder.setParameterValue(facetParam);

		return assetTagsSearchFacetDisplayBuilder.build();
	}

	protected TermCollector createTermCollector(String term, int frequency) {
		TermCollector termCollector = Mockito.mock(TermCollector.class);

		Mockito.doReturn(
			frequency
		).when(
			termCollector
		).getFrequency();

		Mockito.doReturn(
			term
		).when(
			termCollector
		).getTerm();

		return termCollector;
	}

	protected void setUpOneTermCollector(String facetParam, int frequency) {
		Mockito.doReturn(
			Collections.singletonList(
				createTermCollector(facetParam, frequency))
		).when(
			_facetCollector
		).getTermCollectors();
	}

	@Mock
	private Facet _facet;

	@Mock
	private FacetCollector _facetCollector;

}