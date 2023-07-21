/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.url.builder.internal;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.test.rule.LiferayUnitTestRule;
import com.liferay.portal.url.builder.AbsolutePortalURLBuilder;
import com.liferay.portal.url.builder.ModuleAbsolutePortalURLBuilder;

import java.util.Arrays;
import java.util.Collection;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import org.mockito.Mockito;

import org.osgi.framework.Bundle;

/**
 * @author Iván Zaera Avellón
 */
@RunWith(Parameterized.class)
public class ModuleAbsolutePortalURLBuilderTest
	extends BaseAbsolutePortalURLBuilderTestCase {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Parameterized.Parameters(name = "{0}: context={1}, proxy={2}, cdnHost={3}")
	public static Collection<Object[]> data() {
		return Arrays.asList(
			new Object[][] {
				{0, false, false, false}, {1, false, false, true},
				{2, true, false, false}, {3, true, true, false},
				{4, false, true, false}
			});
	}

	@Before
	public void setUp() throws Exception {
		_absolutePortalURLBuilder = new AbsolutePortalURLBuilderImpl(
			mockPortal(context, proxy, cdnHost),
			Mockito.mock(HttpServletRequest.class));

		Bundle bundle = Mockito.mock(Bundle.class);

		Dictionary<String, String> headers = new Hashtable<>();

		headers.put("Web-ContextPath", "/bundle");

		Mockito.when(
			bundle.getHeaders(StringPool.BLANK)
		).thenReturn(
			headers
		);

		_moduleAbsolutePortalURLBuilder = _absolutePortalURLBuilder.forModule(
			bundle, "path/to/resource");
	}

	@Test
	public void test() {
		String url = _moduleAbsolutePortalURLBuilder.build();

		Assert.assertEquals(_RESULTS[index], url);
	}

	@Test
	public void testIgnoreCDN() {
		_absolutePortalURLBuilder.ignoreCDNHost();

		String url = _moduleAbsolutePortalURLBuilder.build();

		Assert.assertEquals(_RESULTS_IGNORE_CDN[index], url);
	}

	@Test
	public void testIgnoreCDNAndProxy() {
		_absolutePortalURLBuilder.ignoreCDNHost();
		_absolutePortalURLBuilder.ignorePathProxy();

		String url = _moduleAbsolutePortalURLBuilder.build();

		Assert.assertEquals(_RESULTS_IGNORE_CDN_AND_PROXY[index], url);
	}

	@Test
	public void testIgnoreProxy() {
		_absolutePortalURLBuilder.ignorePathProxy();

		String url = _moduleAbsolutePortalURLBuilder.build();

		Assert.assertEquals(_RESULTS_IGNORE_PROXY[index], url);
	}

	@Parameterized.Parameter(3)
	public boolean cdnHost;

	@Parameterized.Parameter(1)
	public boolean context;

	@Parameterized.Parameter
	public int index;

	@Parameterized.Parameter(2)
	public boolean proxy;

	private static final String[] _RESULTS = {
		"/o/bundle/path/to/resource",
		"http://cdn-host/o/bundle/path/to/resource",
		"/context/o/bundle/path/to/resource",
		"/proxy/context/o/bundle/path/to/resource",
		"/proxy/o/bundle/path/to/resource"
	};

	private static final String[] _RESULTS_IGNORE_CDN = {
		"/o/bundle/path/to/resource", "/o/bundle/path/to/resource",
		"/context/o/bundle/path/to/resource",
		"/proxy/context/o/bundle/path/to/resource",
		"/proxy/o/bundle/path/to/resource"
	};

	private static final String[] _RESULTS_IGNORE_CDN_AND_PROXY = {
		"/o/bundle/path/to/resource", "/o/bundle/path/to/resource",
		"/context/o/bundle/path/to/resource",
		"/context/o/bundle/path/to/resource", "/o/bundle/path/to/resource"
	};

	private static final String[] _RESULTS_IGNORE_PROXY = {
		"/o/bundle/path/to/resource",
		"http://cdn-host/o/bundle/path/to/resource",
		"/context/o/bundle/path/to/resource",
		"/context/o/bundle/path/to/resource", "/o/bundle/path/to/resource"
	};

	private AbsolutePortalURLBuilder _absolutePortalURLBuilder;
	private ModuleAbsolutePortalURLBuilder _moduleAbsolutePortalURLBuilder;

}