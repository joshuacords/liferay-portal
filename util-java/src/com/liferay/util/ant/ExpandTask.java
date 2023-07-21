/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.util.ant;

import java.io.File;

import org.apache.tools.ant.taskdefs.Expand;

/**
 * @author Brian Wing Shun Chan
 */
public class ExpandTask {

	public static void expand(File source, File destination) {
		Expand expand = new Expand();

		expand.setDest(destination);
		expand.setProject(AntUtil.getProject());
		expand.setSrc(source);

		expand.execute();
	}

	public static void expand(String source, String destination) {
		expand(new File(source), new File(destination));
	}

}