/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.internal.mappings;

import com.liferay.portal.search.elasticsearch6.internal.LiferayElasticsearchIndexingFixtureFactory;
import com.liferay.portal.search.engine.SearchEngineInformation;
import com.liferay.portal.search.test.util.indexing.IndexingFixture;
import com.liferay.portal.search.test.util.mappings.BaseLiferayFieldQueryFactoryTestCase;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;

import org.mockito.Mockito;

/**
 * @author André de Oliveira
 */
public class LiferayFieldQueryFactoryTest
	extends BaseLiferayFieldQueryFactoryTestCase {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() throws Exception {
		super.setUp();

		searchEngineInformation = Mockito.mock(SearchEngineInformation.class);

		Mockito.when(
			searchEngineInformation.getVendorString()
		).thenReturn(
			"Elasticsearch"
		);

		Mockito.when(
			searchEngineInformation.getClientVersionString()
		).thenReturn(
			"6"
		);
	}

	@Override
	protected IndexingFixture createIndexingFixture() {
		return LiferayElasticsearchIndexingFixtureFactory.getInstance();
	}

}