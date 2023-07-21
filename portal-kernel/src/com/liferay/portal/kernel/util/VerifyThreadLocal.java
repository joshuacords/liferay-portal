/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.util;

import com.liferay.petra.lang.CentralizedThreadLocal;

/**
 * @author Preston Crary
 */
public class VerifyThreadLocal {

	public static boolean isVerifyInProgress() {
		return _verifyInProgress.get();
	}

	public static void setVerifyInProgress(boolean enabled) {
		_verifyInProgress.set(enabled);
	}

	private static final ThreadLocal<Boolean> _verifyInProgress =
		new CentralizedThreadLocal<>(
			VerifyThreadLocal.class + "._verifyInProgress",
			() -> Boolean.FALSE);

}