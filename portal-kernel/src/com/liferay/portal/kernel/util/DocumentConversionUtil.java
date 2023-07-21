/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.util;

import java.io.File;
import java.io.InputStream;

/**
 * @author     Bruno Farache
 * @deprecated As of Judson (7.1.x), replaced by {@link
 *             com.liferay.document.library.kernel.document.conversion.DocumentConversionUtil}
 */
@Deprecated
public class DocumentConversionUtil {

	public static File convert(
			String id, InputStream inputStream, String sourceExtension,
			String targetExtension)
		throws Exception {

		return com.liferay.document.library.kernel.document.conversion.
			DocumentConversionUtil.convert(
				id, inputStream, sourceExtension, targetExtension);
	}

	public static String[] getConversions(String extension) throws Exception {
		return com.liferay.document.library.kernel.document.conversion.
			DocumentConversionUtil.getConversions(extension);
	}

}