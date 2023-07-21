/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upload;

import java.io.File;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;

/**
 * @author Brian Wing Shun Chan
 */
public class LiferayFileItemFactory extends DiskFileItemFactory {

	public static final int DEFAULT_SIZE = 1024;

	public LiferayFileItemFactory(File tempDir) {
		this(tempDir, DEFAULT_SIZE);
	}

	public LiferayFileItemFactory(File tempDir, int sizeThreshold) {
		_tempDir = tempDir;
		_sizeThreshold = sizeThreshold;
	}

	@Override
	public LiferayFileItem createItem(
		String fieldName, String contentType, boolean formField,
		String fileName) {

		return new LiferayFileItem(
			fieldName, contentType, formField, fileName, _sizeThreshold,
			_tempDir);
	}

	private final int _sizeThreshold;
	private final File _tempDir;

}