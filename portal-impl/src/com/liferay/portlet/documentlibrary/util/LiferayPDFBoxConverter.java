/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.documentlibrary.util;

import com.liferay.portal.image.ImageToolImpl;
import com.liferay.portal.kernel.image.ImageTool;

import java.awt.image.RenderedImage;

import java.io.File;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

/**
 * @author Juan González
 */
public class LiferayPDFBoxConverter {

	public LiferayPDFBoxConverter(
		File inputFile, File thumbnailFile, File[] previewFiles,
		String extension, String thumbnailExtension, int dpi, int height,
		int width, boolean generatePreview, boolean generateThumbnail) {

		_inputFile = inputFile;
		_thumbnailFile = thumbnailFile;
		_previewFiles = previewFiles;
		_extension = extension;
		_thumbnailExtension = thumbnailExtension;
		_dpi = dpi;
		_height = height;
		_width = width;
		_generatePreview = generatePreview;
		_generateThumbnail = generateThumbnail;
	}

	public void generateImagesPB() throws Exception {
		try (PDDocument pdDocument = PDDocument.load(_inputFile)) {
			PDFRenderer pdfRenderer = new PDFRenderer(pdDocument);

			PDPageTree pdPageTree = pdDocument.getPages();

			int count = pdPageTree.getCount();

			for (int i = 0; i < count; i++) {
				if (_generateThumbnail && (i == 0)) {
					_generateImagesPB(
						pdfRenderer, i, _thumbnailFile, _thumbnailExtension);
				}

				if (!_generatePreview) {
					break;
				}

				_generateImagesPB(pdfRenderer, i, _previewFiles[i], _extension);
			}
		}
	}

	private void _generateImagesPB(
			PDFRenderer pdfRenderer, int pageIndex, File outputFile,
			String extension)
		throws Exception {

		RenderedImage renderedImage = pdfRenderer.renderImageWithDPI(
			pageIndex, _dpi, ImageType.RGB);

		ImageTool imageTool = ImageToolImpl.getInstance();

		if (_height != 0) {
			renderedImage = imageTool.scale(renderedImage, _width, _height);
		}
		else {
			renderedImage = imageTool.scale(renderedImage, _width);
		}

		outputFile.createNewFile();

		ImageIO.write(renderedImage, extension, outputFile);
	}

	private final int _dpi;
	private final String _extension;
	private final boolean _generatePreview;
	private final boolean _generateThumbnail;
	private final int _height;
	private final File _inputFile;
	private final File[] _previewFiles;
	private final String _thumbnailExtension;
	private final File _thumbnailFile;
	private final int _width;

}