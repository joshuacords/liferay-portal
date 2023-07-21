/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.osgi.web.portlet.container.upload.servlet.request.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.upload.UploadServletRequestConfigurationHelperUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.upload.UploadServletRequestImpl;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

/**
 * @author Manuel de la Peña
 */
@RunWith(Arquillian.class)
public class UploadServletRequestWhenGettingTempDirTest {

	@ClassRule
	@Rule
	public static final TestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Test
	public void testShouldNotReturnPreferencesValueWhenModified()
		throws IOException {

		File tempDir = UploadServletRequestImpl.getTempDir();

		try {
			TemporaryFolder temporaryFolder = new TemporaryFolder();

			temporaryFolder.create();

			File newTempDir = temporaryFolder.getRoot();

			UploadServletRequestImpl.setTempDir(newTempDir);

			File currentTempDir = UploadServletRequestImpl.getTempDir();

			Assert.assertEquals(temporaryFolder.getRoot(), currentTempDir);
		}
		finally {
			UploadServletRequestImpl.setTempDir(tempDir);
		}
	}

	@Test
	public void testShouldReturnPreferencesValue() {
		File tempDir = UploadServletRequestImpl.getTempDir();

		File expectedTempDir = new File(
			UploadServletRequestConfigurationHelperUtil.getTempDir());

		Assert.assertEquals(expectedTempDir, tempDir);
	}

}