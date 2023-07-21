/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.adaptive.media.image.internal.processor.util;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifIFD0Directory;

import com.liferay.adaptive.media.exception.AMRuntimeException;
import com.liferay.adaptive.media.image.internal.util.RenderedImageUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.image.ImageToolUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;

import java.awt.image.RenderedImage;

import java.io.IOException;
import java.io.InputStream;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author Sergio González
 */
public class TiffOrientationTransformer {

	public static RenderedImage transform(
			Supplier<InputStream> inputStreamSupplier)
		throws PortalException {

		try {
			Optional<Integer> tiffOrientationValueOptional =
				_getTiffOrientationValue(inputStreamSupplier);

			if (tiffOrientationValueOptional.isPresent()) {
				return _transform(
					inputStreamSupplier, tiffOrientationValueOptional.get());
			}

			return RenderedImageUtil.readImage(inputStreamSupplier.get());
		}
		catch (IOException ioException) {
			throw new AMRuntimeException.IOException(ioException);
		}
	}

	private static Optional<Integer> _getTiffOrientationValue(
		Supplier<InputStream> inputStreamSupplier) {

		if (!_isContentTypeSupported(inputStreamSupplier)) {
			return Optional.empty();
		}

		try (InputStream inputStream = inputStreamSupplier.get()) {
			Metadata metadata = ImageMetadataReader.readMetadata(inputStream);

			ExifIFD0Directory exifIFD0Directory =
				metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);

			if ((exifIFD0Directory == null) ||
				!exifIFD0Directory.containsTag(
					ExifIFD0Directory.TAG_ORIENTATION)) {

				return Optional.empty();
			}

			return Optional.of(
				exifIFD0Directory.getInt(ExifIFD0Directory.TAG_ORIENTATION));
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn(exception, exception);
			}
		}

		return Optional.empty();
	}

	private static boolean _isContentTypeSupported(
		Supplier<InputStream> inputStreamSupplier) {

		try (InputStream inputStream = inputStreamSupplier.get()) {
			if (ArrayUtil.contains(
					_UNSUPPORTED_MIME_TYPES,
					MimeTypesUtil.getContentType(
						inputStream, StringPool.BLANK))) {

				return false;
			}
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn(exception);
			}

			return false;
		}

		return true;
	}

	private static RenderedImage _transform(
			Supplier<InputStream> inputStreamSupplier, int tiffOrientationValue)
		throws IOException, PortalException {

		RenderedImage renderedImage = RenderedImageUtil.readImage(
			inputStreamSupplier.get());

		if (tiffOrientationValue == _ORIENTATION_VALUE_HORIZONTAL_NORMAL) {
			return renderedImage;
		}
		else if (tiffOrientationValue == _ORIENTATION_VALUE_MIRROR_HORIZONTAL) {
			return ImageToolUtil.flipHorizontal(renderedImage);
		}
		else if (tiffOrientationValue ==
					_ORIENTATION_VALUE_MIRROR_HORIZONTAL_ROTATE_90_CW) {

			return ImageToolUtil.flipVertical(
				ImageToolUtil.rotate(renderedImage, 90));
		}
		else if (tiffOrientationValue ==
					_ORIENTATION_VALUE_MIRROR_HORIZONTAL_ROTATE_270_CW) {

			return ImageToolUtil.flipVertical(
				ImageToolUtil.rotate(renderedImage, 270));
		}
		else if (tiffOrientationValue == _ORIENTATION_VALUE_MIRROR_VERTICAL) {
			return ImageToolUtil.flipVertical(renderedImage);
		}
		else if (tiffOrientationValue == _ORIENTATION_VALUE_ROTATE_90_CW) {
			return ImageToolUtil.rotate(renderedImage, 90);
		}
		else if (tiffOrientationValue == _ORIENTATION_VALUE_ROTATE_180) {
			return ImageToolUtil.rotate(renderedImage, 180);
		}
		else if (tiffOrientationValue == _ORIENTATION_VALUE_ROTATE_270_CW) {
			return ImageToolUtil.rotate(renderedImage, 270);
		}

		return renderedImage;
	}

	private static final int _ORIENTATION_VALUE_HORIZONTAL_NORMAL = 1;

	private static final int _ORIENTATION_VALUE_MIRROR_HORIZONTAL = 2;

	private static final int _ORIENTATION_VALUE_MIRROR_HORIZONTAL_ROTATE_90_CW =
		7;

	private static final int
		_ORIENTATION_VALUE_MIRROR_HORIZONTAL_ROTATE_270_CW = 5;

	private static final int _ORIENTATION_VALUE_MIRROR_VERTICAL = 4;

	private static final int _ORIENTATION_VALUE_ROTATE_90_CW = 6;

	private static final int _ORIENTATION_VALUE_ROTATE_180 = 3;

	private static final int _ORIENTATION_VALUE_ROTATE_270_CW = 8;

	private static final String[] _UNSUPPORTED_MIME_TYPES = {
		"image/jpeg", "image/pjpeg", "image/x-citrix-jpeg"
	};

	private static final Log _log = LogFactoryUtil.getLog(
		TiffOrientationTransformer.class);

}