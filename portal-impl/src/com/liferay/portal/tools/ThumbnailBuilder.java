/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools;

import com.liferay.portal.image.ImageToolImpl;
import com.liferay.portal.kernel.image.ImageBag;
import com.liferay.portal.kernel.image.ImageTool;
import com.liferay.portal.kernel.util.GetterUtil;

import java.awt.image.RenderedImage;

import java.io.File;

import java.util.Map;

import javax.imageio.ImageIO;

/**
 * @author Brian Wing Shun Chan
 */
public class ThumbnailBuilder {

	public static void main(String[] args) throws Exception {
		Map<String, String> arguments = ArgumentsUtil.parseArguments(args);

		File originalFile = new File(arguments.get("thumbnail.original.file"));
		File thumbnailFile = new File(
			arguments.get("thumbnail.thumbnail.file"));
		int height = GetterUtil.getInteger(arguments.get("thumbnail.height"));
		int width = GetterUtil.getInteger(arguments.get("thumbnail.width"));
		boolean overwrite = GetterUtil.getBoolean(
			arguments.get("thumbnail.overwrite"));

		try {
			new ThumbnailBuilder(
				originalFile, thumbnailFile, height, width, overwrite);
		}
		catch (Exception exception) {
			ArgumentsUtil.processMainException(arguments, exception);
		}
	}

	public ThumbnailBuilder(
			File originalFile, File thumbnailFile, int height, int width,
			boolean overwrite)
		throws Exception {

		if (!originalFile.exists()) {
			return;
		}

		if (!overwrite &&
			(thumbnailFile.lastModified() > originalFile.lastModified())) {

			return;
		}

		ImageBag imageBag = _imageToolUtil.read(originalFile);

		RenderedImage renderedImage = _imageToolUtil.scale(
			imageBag.getRenderedImage(), height, width);

		ImageIO.write(renderedImage, imageBag.getType(), thumbnailFile);
	}

	private static final ImageTool _imageToolUtil = ImageToolImpl.getInstance();

}