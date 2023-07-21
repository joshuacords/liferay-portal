/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.diff;

import java.io.Reader;

/**
 * <p>
 * This class can compare two different versions of HTML code. It detects
 * changes to an entire HTML page such as removal or addition of characters or
 * images.
 * </p>
 *
 * @author Julio Camarero
 */
public class DiffHtmlUtil {

	public static String diff(Reader source, Reader target) throws Exception {
		return getDiffHtml().diff(source, target);
	}

	public static DiffHtml getDiffHtml() {
		return _diffHtml;
	}

	public static String replaceStyles(String html) {
		return getDiffHtml().replaceStyles(html);
	}

	public void setDiffHtml(DiffHtml diffHtml) {
		_diffHtml = diffHtml;
	}

	private static DiffHtml _diffHtml;

}