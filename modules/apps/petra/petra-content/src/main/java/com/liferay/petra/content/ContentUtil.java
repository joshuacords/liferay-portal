/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.petra.content;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 * @author Peter Fellwock
 * @see    com.liferay.util.ContentUtil
 */
public class ContentUtil {

	public static String get(ClassLoader classLoader, String location) {
		return _contentUtil._get(classLoader, location, false);
	}

	public static String get(
		ClassLoader classLoader, String location, boolean all) {

		return _contentUtil._get(classLoader, location, all);
	}

	private ContentUtil() {
		_contentPool = new HashMap<>();
	}

	private String _get(ClassLoader classLoader, String location, boolean all) {
		String content = _contentPool.get(location);

		if (content == null) {
			try {
				content = StringUtil.read(classLoader, location, all);

				_put(location, content);
			}
			catch (IOException ioException) {
				_log.error(ioException, ioException);
			}
		}

		return content;
	}

	private void _put(String location, String content) {
		_contentPool.put(location, content);
	}

	private static final Log _log = LogFactoryUtil.getLog(ContentUtil.class);

	private static final ContentUtil _contentUtil = new ContentUtil();

	private final Map<String, String> _contentPool;

}