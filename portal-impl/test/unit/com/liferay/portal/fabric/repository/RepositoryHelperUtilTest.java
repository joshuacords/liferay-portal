/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.fabric.repository;

import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.CodeCoverageAssertor;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Shuyang Zhou
 */
public class RepositoryHelperUtilTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			CodeCoverageAssertor.INSTANCE, LiferayUnitTestRule.INSTANCE);

	@Test
	public void testConstructor() {
		new RepositoryHelperUtil();
	}

	@Test
	public void testGetRepositoryFilePath() {
		AtomicLong idGenerator = RepositoryHelperUtil.idGenerator;

		long currentId = idGenerator.get();

		Path repositoryFilePath = RepositoryHelperUtil.getRepositoryFilePath(
			Paths.get("repository"), Paths.get("parent/child/remoteFile"));

		Pattern pattern = Pattern.compile(
			"repository/remoteFile-\\d+-" + currentId);

		Matcher matcher = pattern.matcher(repositoryFilePath.toString());

		Assert.assertTrue(matcher.find());

		repositoryFilePath = RepositoryHelperUtil.getRepositoryFilePath(
			Paths.get("repository"), Paths.get("parent/child/remoteFile.xyz"));

		pattern = Pattern.compile(
			"repository/remoteFile-\\d+-" + (currentId + 1) + ".xyz");

		matcher = pattern.matcher(repositoryFilePath.toString());

		Assert.assertTrue(matcher.find());
	}

}