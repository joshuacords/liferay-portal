/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.info.display.contributor.util;

/**
 * @author Pavel Savinov
 */
public class ContentAccessorUtil {

	public static ContentAccessorUtil getInstance() {
		return _contentAccessorUtil;
	}

	public static boolean isContentAccessor(Object object) {
		return object instanceof ContentAccessor;
	}

	private ContentAccessorUtil() {
	}

	private static final ContentAccessorUtil _contentAccessorUtil =
		new ContentAccessorUtil();

}