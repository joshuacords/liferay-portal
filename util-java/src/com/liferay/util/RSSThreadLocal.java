/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.util;

import com.liferay.petra.lang.CentralizedThreadLocal;

/**
 * @author     Shuyang Zhou
 * @see        com.liferay.rss.util.RSSThreadLocal
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class RSSThreadLocal {

	public static boolean isExportRSS() {
		return _exportRSS.get();
	}

	public static void setExportRSS(boolean exportRSS) {
		_exportRSS.set(exportRSS);
	}

	private static final ThreadLocal<Boolean> _exportRSS =
		new CentralizedThreadLocal<>(
			RSSThreadLocal.class + "._exportRSS", () -> Boolean.FALSE);

}