/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.empty.temp.dir;

import com.liferay.portal.kernel.util.OSDetector;

import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Tom Wang
 */
public class EmptyTempDirTest {

	@Test
	public void testEmptyTempDir() throws IOException {
		File tempDir = new File(System.getProperty("liferay.temp.dir"));

		List<String> fileNames = Arrays.asList(tempDir.list());

		try {
			Assert.assertTrue(
				"Unexpected files found in the application server's temp " +
					"directory: " + fileNames,
				fileNames.isEmpty());
		}
		catch (AssertionError ae) {
			if (OSDetector.isWindows()) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

				Calendar calendar = Calendar.getInstance();

				Date date = calendar.getTime();

				Path logFilePath = Paths.get(
					System.getProperty("liferay.log.dir"),
					"liferay." + dateFormat.format(date) + ".xml");

				if (Files.exists(logFilePath)) {
					Files.write(
						logFilePath,
						Arrays.asList(
							"<log4j:event level=\"ERROR\">\n<log4j:message>",
							ae.getMessage(),
							"</log4j:message>\n</log4j:event>\n\n"),
						StandardOpenOption.APPEND);
				}
			}

			throw ae;
		}
	}

}