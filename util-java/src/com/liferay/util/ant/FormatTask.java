/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.util.ant;

import com.liferay.petra.string.StringPool;

import java.text.MessageFormat;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Echo;

/**
 * @author Miguel Pastor
 */
public class FormatTask extends Echo {

	@Override
	public void execute() throws BuildException {
		if (message == null) {
			throw new BuildException("The message attribute is mandatory");
		}

		Object[] arguments = _arguments.split(_separator);

		String value = MessageFormat.format(message, arguments);

		if (_property != null) {
			Project project = getProject();

			project.setProperty(_property, value);
		}
		else {
			setMessage(value);

			super.execute();
		}
	}

	public void setArguments(String arguments) {
		_arguments = arguments;
	}

	public void setProperty(String property) {
		_property = property;
	}

	public void setSeparator(String separator) {
		_separator = separator;
	}

	private String _arguments;
	private String _property;
	private String _separator = StringPool.COMMA;

}