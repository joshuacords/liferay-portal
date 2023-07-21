/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.taglib.util;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;

import java.util.Map;

/**
 * @author Shuyang Zhou
 */
public class InlineUtil {

	public static String buildDynamicAttributes(
		Map<String, Object> dynamicAttributes) {

		if ((dynamicAttributes == null) || dynamicAttributes.isEmpty()) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(dynamicAttributes.size() * 4);

		for (Map.Entry<String, Object> entry : dynamicAttributes.entrySet()) {
			String key = entry.getKey();

			if (!key.equals("class")) {
				sb.append(key);
				sb.append("=\"");
				sb.append(String.valueOf(entry.getValue()));
				sb.append("\" ");
			}
		}

		return sb.toString();
	}

}