/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.server.manager.internal.executor;

import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Marcellus Tavares
 */
public class ExecutorPathResolverTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() {
		setUpExecutorPathResolver();
	}

	@Test
	public void testGetExactMatchExecutor() {
		Assert.assertEquals("/", _executorPathResolver.getExecutorPath("/"));
		Assert.assertEquals(
			"/server", _executorPathResolver.getExecutorPath("/server"));
		Assert.assertEquals(
			"/plugins", _executorPathResolver.getExecutorPath("/plugins"));
	}

	@Test
	public void testGetNextExecutorsPaths() {
		Assert.assertEquals(
			Arrays.asList("/plugins", "/server"),
			_executorPathResolver.getNextExecutorsPaths(null));
		Assert.assertEquals(
			Arrays.asList("/server/log"),
			_executorPathResolver.getNextExecutorsPaths("/server"));
		Assert.assertEquals(
			Arrays.asList("/server/log/error", "/server/log/output"),
			_executorPathResolver.getNextExecutorsPaths("/server/log/xyz"));
	}

	@Test
	public void testGetNullPathMatchExecutor() {
		Assert.assertEquals("/", _executorPathResolver.getExecutorPath(null));
		Assert.assertEquals("/", _executorPathResolver.getExecutorPath(""));
	}

	@Test
	public void testGetPartialMatchExecutor() {
		Assert.assertEquals("/", _executorPathResolver.getExecutorPath("/xyz"));
		Assert.assertEquals(
			"/server/log",
			_executorPathResolver.getExecutorPath("/server/log/xyz"));
		Assert.assertEquals(
			"/plugins", _executorPathResolver.getExecutorPath("/plugins/xyz"));
	}

	protected void setUpExecutorPathResolver() {
		Set<String> availableExecutorPaths = new HashSet<>();

		availableExecutorPaths.add("/");
		availableExecutorPaths.add("/plugins");
		availableExecutorPaths.add("/server");
		availableExecutorPaths.add("/server/freemarker/debug-port");
		availableExecutorPaths.add("/server/log");
		availableExecutorPaths.add("/server/log/error");
		availableExecutorPaths.add("/server/log/output");

		_executorPathResolver = new ExecutorPathResolver(
			availableExecutorPaths);
	}

	private ExecutorPathResolver _executorPathResolver;

}