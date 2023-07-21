/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.util.ant;

import java.io.File;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.War;

/**
 * @author Brian Wing Shun Chan
 */
public class WarTask {

	public static void war(
		File baseDir, File destination, String excludes, File webxml) {

		Project project = AntUtil.getProject();

		War war = new War();

		war.setBasedir(baseDir);
		war.setDestFile(destination);
		war.setExcludes(excludes);

		File manifestFile = new File(
			baseDir.getAbsolutePath() + "/META-INF/MANIFEST.MF");

		if (manifestFile.exists()) {
			war.setManifest(manifestFile);
		}

		war.setProject(project);
		war.setWebxml(webxml);

		war.execute();
	}

	public static void war(
		String baseDir, String destination, String excludes, String webxml) {

		war(
			new File(baseDir), new File(destination), excludes,
			new File(webxml));
	}

}