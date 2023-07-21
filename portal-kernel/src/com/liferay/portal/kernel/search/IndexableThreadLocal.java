/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.search;

import com.liferay.petra.lang.CentralizedThreadLocal;
import com.liferay.petra.lang.SafeClosable;

/**
 * @author Shuyang Zhou
 * @deprecated As of Cavanaugh (7.4.x), with no direct replacement
 */
@Deprecated
public class IndexableThreadLocal {

	public static boolean isForceSync() {
		return _forceSync.get();
	}

	public static SafeClosable setWithSafeClosable(boolean forceSync) {
		return _forceSync.setWithSafeClosable(forceSync);
	}

	private static final CentralizedThreadLocal<Boolean> _forceSync =
		new CentralizedThreadLocal<>(
			IndexableThreadLocal.class + "_forceSync", () -> Boolean.TRUE);

}