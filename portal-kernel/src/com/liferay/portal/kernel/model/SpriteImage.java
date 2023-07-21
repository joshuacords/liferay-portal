/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.model;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 */
public class SpriteImage implements Serializable {

	public SpriteImage(
		String spriteFileName, String imageFileName, int offset, int height,
		int width) {

		_spriteFileName = spriteFileName;
		_imageFileName = imageFileName;
		_offset = offset;
		_height = height;
		_width = width;
	}

	public int getHeight() {
		return _height;
	}

	public String getImageFileName() {
		return _imageFileName;
	}

	public int getOffset() {
		return _offset;
	}

	public String getSpriteFileName() {
		return _spriteFileName;
	}

	public int getWidth() {
		return _width;
	}

	private final int _height;
	private final String _imageFileName;
	private final int _offset;
	private final String _spriteFileName;
	private final int _width;

}