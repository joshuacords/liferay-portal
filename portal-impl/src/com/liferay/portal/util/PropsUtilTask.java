/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.util;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;

/**
 * @author Brian Wing Shun Chan
 */
public class PropsUtilTask extends Task {

	@Override
	public void execute() throws BuildException {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			Class<?> clazz = getClass();

			currentThread.setContextClassLoader(clazz.getClassLoader());

			Project project = getProject();

			project.setUserProperty(_result, PropsUtil.get(_key));
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}
	}

	public void setKey(String key) {
		_key = key;
	}

	public void setResult(String result) {
		_result = result;
	}

	private String _key;
	private String _result;

}