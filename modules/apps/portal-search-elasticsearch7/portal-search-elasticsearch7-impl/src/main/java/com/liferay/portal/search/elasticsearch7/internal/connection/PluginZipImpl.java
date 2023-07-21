/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.connection;

import java.io.File;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * @author André de Oliveira
 */
public class PluginZipImpl implements PluginZip {

	public PluginZipImpl(File file) {
		_file = file;
	}

	@Override
	public void delete() {
		_file.delete();
	}

	@Override
	public URL getURL() {
		URI uri = _file.toURI();

		try {
			return uri.toURL();
		}
		catch (MalformedURLException malformedURLException) {
			throw new RuntimeException(
				"Invalid file " + _file, malformedURLException);
		}
	}

	private final File _file;

}