/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.web.internal.facet.display.context;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.facet.Facet;
import com.liferay.portal.kernel.search.facet.collector.FacetCollector;
import com.liferay.portal.kernel.search.facet.collector.TermCollector;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.search.web.internal.facet.display.builder.UserSearchFacetDisplayBuilder;
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
 * @author Lino Alves
 */
public class UserSearchFacetDisplayContextTest {

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
		String paramValue = "";

		UserSearchFacetDisplayContext userSearchFacetDisplayContext =
			createDisplayContext(paramValue);

		List<UserSearchFacetTermDisplayContext>
			userSearchFacetTermDisplayContexts =
				userSearchFacetDisplayContext.getTermDisplayContexts();

		Assert.assertEquals(
			userSearchFacetTermDisplayContexts.toString(), 0,
			userSearchFacetTermDisplayContexts.size());

		Assert.assertEquals(
			paramValue, userSearchFacetDisplayContext.getParamValue());
		Assert.assertTrue(userSearchFacetDisplayContext.isNothingSelected());
		Assert.assertTrue(userSearchFacetDisplayContext.isRenderNothing());
	}

	@Test
	public void testEmptySearchResultsWithPreviousSelection() throws Exception {
		String userName = RandomTestUtil.randomString();

		String paramValue = userName;

		UserSearchFacetDisplayContext userSearchFacetDisplayContext =
			createDisplayContext(paramValue);

		List<UserSearchFacetTermDisplayContext>
			userSearchFacetTermDisplayContexts =
				userSearchFacetDisplayContext.getTermDisplayContexts();

		Assert.assertEquals(
			userSearchFacetTermDisplayContexts.toString(), 1,
			userSearchFacetTermDisplayContexts.size());

		UserSearchFacetTermDisplayContext userSearchFacetTermDisplayContext =
			userSearchFacetTermDisplayContexts.get(0);

		Assert.assertEquals(
			0, userSearchFacetTermDisplayContext.getFrequency());
		Assert.assertEquals(
			userName, userSearchFacetTermDisplayContext.getUserName());
		Assert.assertTrue(userSearchFacetTermDisplayContext.isSelected());
		Assert.assertTrue(
			userSearchFacetTermDisplayContext.isFrequencyVisible());

		Assert.assertEquals(
			paramValue, userSearchFacetDisplayContext.getParamValue());
		Assert.assertFalse(userSearchFacetDisplayContext.isNothingSelected());
		Assert.assertFalse(userSearchFacetDisplayContext.isRenderNothing());
	}

	@Test
	public void testOneTerm() throws Exception {
		String userName = RandomTestUtil.randomString();

		int count = RandomTestUtil.randomInt();

		setUpOneTermCollector(userName, count);

		String paramValue = "";

		UserSearchFacetDisplayContext userSearchFacetDisplayContext =
			createDisplayContext(paramValue);

		List<UserSearchFacetTermDisplayContext>
			userSearchFacetTermDisplayContexts =
				userSearchFacetDisplayContext.getTermDisplayContexts();

		Assert.assertEquals(
			userSearchFacetTermDisplayContexts.toString(), 1,
			userSearchFacetTermDisplayContexts.size());

		UserSearchFacetTermDisplayContext userSearchFacetTermDisplayContext =
			userSearchFacetTermDisplayContexts.get(0);

		Assert.assertEquals(
			count, userSearchFacetTermDisplayContext.getFrequency());
		Assert.assertEquals(
			userName, userSearchFacetTermDisplayContext.getUserName());
		Assert.assertFalse(userSearchFacetTermDisplayContext.isSelected());
		Assert.assertTrue(
			userSearchFacetTermDisplayContext.isFrequencyVisible());

		Assert.assertEquals(
			paramValue, userSearchFacetDisplayContext.getParamValue());
		Assert.assertTrue(userSearchFacetDisplayContext.isNothingSelected());
		Assert.assertFalse(userSearchFacetDisplayContext.isRenderNothing());
	}

	@Test
	public void testOneTermWithPreviousSelection() throws Exception {
		String userName = RandomTestUtil.randomString();

		int count = RandomTestUtil.randomInt();

		setUpOneTermCollector(userName, count);

		String paramValue = userName;

		UserSearchFacetDisplayContext userSearchFacetDisplayContext =
			createDisplayContext(paramValue);

		List<UserSearchFacetTermDisplayContext>
			userSearchFacetTermDisplayContexts =
				userSearchFacetDisplayContext.getTermDisplayContexts();

		Assert.assertEquals(
			userSearchFacetTermDisplayContexts.toString(), 1,
			userSearchFacetTermDisplayContexts.size());

		UserSearchFacetTermDisplayContext userSearchFacetTermDisplayContext =
			userSearchFacetTermDisplayContexts.get(0);

		Assert.assertEquals(
			count, userSearchFacetTermDisplayContext.getFrequency());
		Assert.assertEquals(
			userName, userSearchFacetTermDisplayContext.getUserName());
		Assert.assertTrue(userSearchFacetTermDisplayContext.isSelected());
		Assert.assertTrue(
			userSearchFacetTermDisplayContext.isFrequencyVisible());

		Assert.assertEquals(
			paramValue, userSearchFacetDisplayContext.getParamValue());
		Assert.assertFalse(userSearchFacetDisplayContext.isNothingSelected());
		Assert.assertFalse(userSearchFacetDisplayContext.isRenderNothing());
	}

	protected UserSearchFacetDisplayContext createDisplayContext(
			String paramValue)
		throws Exception {

		UserSearchFacetDisplayBuilder userSearchFacetDisplayBuilder =
			new UserSearchFacetDisplayBuilder();

		userSearchFacetDisplayBuilder.setFacet(_facet);
		userSearchFacetDisplayBuilder.setParamValue(paramValue);
		userSearchFacetDisplayBuilder.setFrequenciesVisible(true);
		userSearchFacetDisplayBuilder.setFrequencyThreshold(0);
		userSearchFacetDisplayBuilder.setMaxTerms(0);

		return userSearchFacetDisplayBuilder.build();
	}

	protected TermCollector createTermCollector(String userName, int count) {
		TermCollector termCollector = Mockito.mock(TermCollector.class);

		Mockito.doReturn(
			count
		).when(
			termCollector
		).getFrequency();

		Mockito.doReturn(
			userName
		).when(
			termCollector
		).getTerm();

		return termCollector;
	}

	protected User createUser(String userName) throws Exception {
		User user = Mockito.mock(User.class);

		Mockito.doReturn(
			userName
		).when(
			user
		).getFullName();

		return user;
	}

	protected void setUpOneTermCollector(String userName, int count) {
		Mockito.doReturn(
			Collections.singletonList(createTermCollector(userName, count))
		).when(
			_facetCollector
		).getTermCollectors();
	}

	@Mock
	private Facet _facet;

	@Mock
	private FacetCollector _facetCollector;

}