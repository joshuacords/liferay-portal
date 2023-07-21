/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.util;

import com.liferay.portal.kernel.util.MimeTypesUtil;

/**
 * @author     Alexander Chow
 * @deprecated As of Bunyan (6.0.x), replaced by {@link MimeTypesUtil}
 */
@Deprecated
public class ContentTypeUtil {

	public static String getContentType(String fileName) {
		return MimeTypesUtil.getContentType(fileName);
	}

}