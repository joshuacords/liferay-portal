/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mail.reader.model;

import java.io.File;

/**
 * @author Scott Lee
 */
public class MailFile {

	public MailFile(File file, String fileName, long size) {
		_file = file;
		_fileName = fileName;
		_size = size;

		_contentPath = null;
	}

	public MailFile(String contentPath, String fileName, long size) {
		_contentPath = contentPath;
		_fileName = fileName;
		_size = size;

		_file = null;
	}

	public void cleanUp() {
		if ((_file != null) && _file.exists()) {
			_file.delete();
		}
	}

	public String getContentPath() {
		return _contentPath;
	}

	public File getFile() {
		return _file;
	}

	public String getFileName() {
		return _fileName;
	}

	public long getSize() {
		return _size;
	}

	private final String _contentPath;
	private final File _file;
	private final String _fileName;
	private final long _size;

}