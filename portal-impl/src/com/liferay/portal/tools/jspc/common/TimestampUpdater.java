/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.jspc.common;

import java.io.File;

import org.apache.tools.ant.DirectoryScanner;

/**
 * @author Minhchau Dang
 */
public class TimestampUpdater {

	public static void main(String[] args) {
		if (args.length == 1) {
			new TimestampUpdater(args[0]);
		}
		else {
			throw new IllegalArgumentException();
		}
	}

	public TimestampUpdater(String classDirName) {
		DirectoryScanner directoryScanner = new DirectoryScanner();

		directoryScanner.setBasedir(classDirName);
		directoryScanner.setIncludes(new String[] {"**\\*.java"});

		directoryScanner.scan();

		String[] fileNames = directoryScanner.getIncludedFiles();

		for (String fileName : fileNames) {
			File javaFile = new File(classDirName, fileName);

			String fileNameWithoutExtension = fileName.substring(
				0, fileName.length() - 5);

			String classFileName = fileNameWithoutExtension.concat(".class");

			File classFile = new File(classDirName, classFileName);

			classFile.setLastModified(javaFile.lastModified());
		}
	}

}