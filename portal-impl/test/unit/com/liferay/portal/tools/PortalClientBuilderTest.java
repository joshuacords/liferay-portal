/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools;

import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayOutputStream;
import com.liferay.portal.kernel.test.ConsoleTestUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Shuyang Zhou
 */
public class PortalClientBuilderTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testPortalClientBuilder() throws Exception {
		Path outputPath = Files.createTempDirectory(null);

		UnsyncByteArrayOutputStream unsyncByteArrayOutputStream =
			ConsoleTestUtil.hijackStdOut();

		try {
			PortalClientBuilder.main(
				new String[] {
					"portal-web/docroot/WEB-INF/server-config.wsdd",
					outputPath.toString(),
					"portal-client/namespace-mapping.properties",
					"http://localhost:8080"
				});
		}
		finally {
			FileUtil.deltree(outputPath.toFile());

			String output = ConsoleTestUtil.restoreStdOut(
				unsyncByteArrayOutputStream);

			for (String line : StringUtil.splitLines(output)) {
				line = line.trim();

				Assert.assertTrue(
					"Unexpected output " + output,
					line.startsWith("Loading ") ||
					line.startsWith("WSDL2Java "));
			}
		}
	}

}