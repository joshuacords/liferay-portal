/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.image;

import com.liferay.portal.kernel.image.Hook;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.util.PropsValues;

/**
 * @author Jorge Ferrer
 */
public class HookFactory {

	public static Hook getInstance() {
		if (_hook == null) {
			if (_log.isDebugEnabled()) {
				_log.debug("Instantiate " + PropsValues.IMAGE_HOOK_IMPL);
			}

			_hook = new DLHook();
		}

		if (_log.isDebugEnabled()) {
			Class<?> clazz = _hook.getClass();

			_log.debug("Return " + clazz.getName());
		}

		return _hook;
	}

	public static void setInstance(Hook hook) {
		if (_log.isDebugEnabled()) {
			Class<?> clazz = hook.getClass();

			_log.debug("Set " + clazz.getName());
		}

		_hook = hook;
	}

	private static final Log _log = LogFactoryUtil.getLog(HookFactory.class);

	private static Hook _hook;

}