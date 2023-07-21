/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.documentlibrary.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author     Bruno Farache
 * @author     Alexander Chow
 * @deprecated As of Judson (7.1.x), moved to {@link
 *             com.liferay.document.library.kernel.document.conversion.DocumentConversionUtil}
 */
@Deprecated
public class DocumentConversionUtil {

	public static File convert(
			String id, InputStream inputStream, String sourceExtension,
			String targetExtension)
		throws IOException {

		return com.liferay.document.library.kernel.document.conversion.
			DocumentConversionUtil.convert(
				id, inputStream, sourceExtension, targetExtension);
	}

	public static void disconnect() {
		com.liferay.document.library.kernel.document.conversion.
			DocumentConversionUtil.disconnect();
	}

	public static String[] getConversions(String extension) {
		return com.liferay.document.library.kernel.document.conversion.
			DocumentConversionUtil.getConversions(extension);
	}

	public static String getFilePath(String id, String targetExtension) {
		return com.liferay.document.library.kernel.document.conversion.
			DocumentConversionUtil.getFilePath(id, targetExtension);
	}

	public static boolean isComparableVersion(String extension) {
		return com.liferay.document.library.kernel.document.conversion.
			DocumentConversionUtil.isComparableVersion(extension);
	}

	public static boolean isConvertBeforeCompare(String extension) {
		return com.liferay.document.library.kernel.document.conversion.
			DocumentConversionUtil.isConvertBeforeCompare(extension);
	}

	public static boolean isEnabled() {
		return com.liferay.document.library.kernel.document.conversion.
			DocumentConversionUtil.isEnabled();
	}

}