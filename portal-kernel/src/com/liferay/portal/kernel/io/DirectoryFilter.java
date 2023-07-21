/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.io;

import java.io.File;
import java.io.FileFilter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author     Brian Wing Shun Chan
 * @author     Alexander Chow
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class DirectoryFilter implements FileFilter {

	public DirectoryFilter() {
	}

	public DirectoryFilter(String regex) {
		_pattern = Pattern.compile(regex);
	}

	@Override
	public boolean accept(File file) {
		if (file.isDirectory()) {
			if (_pattern == null) {
				return true;
			}

			Matcher matcher = _pattern.matcher(file.getName());

			return matcher.matches();
		}

		return false;
	}

	private Pattern _pattern;

}