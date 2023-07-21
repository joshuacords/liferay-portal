/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.servlet.filters.aggregate;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ServiceProxyFactory;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Cleydyr de Albuquerque
 */
@PrepareForTest({PortalUtil.class, ServiceProxyFactory.class})
@RunWith(PowerMockRunner.class)
public class AggregateFilterTest extends PowerMockito {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		_setUpPortalUtil();
		_setUpServiceProxyFactory();
	}

	@Test
	public void testAggregateWithImports() throws Exception {
		String fileName = "./my-styles.css";
		String css = "body {color: black;}";

		ServletPaths servletPaths = _createMockServletPaths(fileName, css);

		_testAggregateWithImports(
			servletPaths, css, _wrap(fileName, StringPool.QUOTE));

		String url = "https://example.com";

		_testAggregateWithImports(
			servletPaths, _wrap(url, StringPool.APOSTROPHE));
		_testAggregateWithImports(servletPaths, _wrap(url, StringPool.BLANK));
		_testAggregateWithImports(servletPaths, _wrap(url, StringPool.QUOTE));
	}

	private ServletPaths _createMockServletPaths(String fileName, String css) {
		ServletPaths servletPaths = mock(ServletPaths.class);

		when(
			servletPaths.down(Mockito.anyString())
		).thenReturn(
			servletPaths
		);

		ServletPaths cssServletPaths = mock(ServletPaths.class);

		when(
			cssServletPaths.getContent()
		).thenReturn(
			css
		);

		when(
			cssServletPaths.getResourcePath()
		).thenReturn(
			StringPool.BLANK
		);

		when(
			servletPaths.down(StringPool.QUOTE + fileName + StringPool.QUOTE)
		).thenReturn(
			cssServletPaths
		);

		when(
			servletPaths.getContent()
		).thenReturn(
			null
		);

		when(
			servletPaths.getResourcePath()
		).thenReturn(
			StringPool.BLANK
		);

		return servletPaths;
	}

	private void _setUpPortalUtil() {
		mockStatic(PortalUtil.class);

		PowerMockito.when(
			PortalUtil.getPathModule()
		).thenReturn(
			StringPool.BLANK
		);
	}

	private void _setUpServiceProxyFactory() {
		mockStatic(ServiceProxyFactory.class);

		when(
			ServiceProxyFactory.newServiceTrackedInstance(
				Matchers.any(), Matchers.any(), Matchers.anyString(),
				Matchers.anyBoolean())
		).thenReturn(
			null
		);
	}

	private void _testAggregateWithImports(
			ServletPaths servletPaths, String expected)
		throws Exception {

		_testAggregateWithImports(servletPaths, expected, expected);
	}

	private void _testAggregateWithImports(
			ServletPaths servletPaths, String expected, String content)
		throws Exception {

		Assert.assertEquals(
			expected, AggregateFilter.aggregateCss(servletPaths, content));
	}

	private String _wrap(String url, String delimiter) {
		return StringBundler.concat(
			"@import url(", delimiter, url, delimiter, ");");
	}

}