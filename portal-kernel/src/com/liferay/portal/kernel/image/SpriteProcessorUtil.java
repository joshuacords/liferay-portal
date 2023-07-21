/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.image;

import java.io.IOException;

import java.net.URL;

import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContext;

/**
 * @author Brian Wing Shun Chan
 */
public class SpriteProcessorUtil {

	public static Properties generate(
			ServletContext servletContext, List<URL> imageURLs,
			String spriteRootDirName, String spriteFileName,
			String spritePropertiesFileName, String rootPath, int maxHeight,
			int maxWidth, int maxSize)
		throws IOException {

		return getSpriteProcessor().generate(
			servletContext, imageURLs, spriteRootDirName, spriteFileName,
			spritePropertiesFileName, rootPath, maxHeight, maxWidth, maxSize);
	}

	public static SpriteProcessor getSpriteProcessor() {
		return _spriteProcessor;
	}

	public void setSpriteProcessor(SpriteProcessor spriteProcessor) {
		_spriteProcessor = spriteProcessor;
	}

	private static SpriteProcessor _spriteProcessor;

}