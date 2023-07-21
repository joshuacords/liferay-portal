/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.util.ant;

import java.io.File;

import org.apache.tools.ant.taskdefs.Mkdir;

/**
 * @author Brian Wing Shun Chan
 */
public class MkdirTask {

	public static void mkdir(File dir) {
		Mkdir mkdir = new Mkdir();

		mkdir.setProject(AntUtil.getProject());
		mkdir.setDir(dir);

		mkdir.execute();
	}

	public static void mkdir(String dir) {
		mkdir(new File(dir));
	}

}