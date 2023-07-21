/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.lang;

import com.liferay.petra.lang.CentralizedThreadLocal;

/**
 * @author     Brian Wing Shun Chan
 * @author     Raymond Augé
 * @deprecated As of Newton (6.2.x)
 */
@Deprecated
public class PortalSecurityManagerThreadLocal {

	public static boolean isEnabled() {
		return _enabled.get();
	}

	public static void setEnabled(boolean enabled) {
		_enabled.set(enabled);
	}

	private static final ThreadLocal<Boolean> _enabled =
		new CentralizedThreadLocal<>(
			PortalSecurityManagerThreadLocal.class + "._enabled",
			() -> Boolean.TRUE);

}