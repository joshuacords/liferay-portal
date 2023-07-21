/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.util.ant;

import java.io.File;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.UpToDate;

/**
 * @author Brian Wing Shun Chan
 */
public class UpToDateTask {

	public static boolean isUpToDate(File source, File target) {
		if (!source.exists() || !target.exists()) {
			return false;
		}

		Project project = AntUtil.getProject();

		UpToDate upToDate = new UpToDate();

		upToDate.setProject(project);
		upToDate.setProperty("uptodate");
		upToDate.setSrcfile(source);
		upToDate.setTargetFile(target);

		upToDate.execute();

		if (project.getProperty("uptodate") != null) {
			return true;
		}

		return false;
	}

	public static boolean isUpToDate(String source, String target) {
		return isUpToDate(new File(source), new File(target));
	}

}