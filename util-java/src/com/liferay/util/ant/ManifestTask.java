/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.util.ant;

import java.io.File;

import org.apache.tools.ant.taskdefs.Manifest;
import org.apache.tools.ant.taskdefs.ManifestException;

/**
 * @author Brian Wing Shun Chan
 */
public class ManifestTask {

	public static void manifest(File file, Manifest.Attribute[] attributes)
		throws ManifestException {

		String parentFile = file.getParent();

		if (parentFile != null) {
			MkdirTask.mkdir(parentFile);
		}

		org.apache.tools.ant.taskdefs.ManifestTask manifest =
			new org.apache.tools.ant.taskdefs.ManifestTask();

		manifest.setProject(AntUtil.getProject());
		manifest.setFile(file);

		for (Manifest.Attribute attribute : attributes) {
			manifest.addConfiguredAttribute(attribute);
		}

		manifest.execute();
	}

	public static void manifest(String file, Manifest.Attribute[] attributes)
		throws ManifestException {

		manifest(new File(file), attributes);
	}

}