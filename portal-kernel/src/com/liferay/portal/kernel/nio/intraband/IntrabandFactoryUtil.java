/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.nio.intraband;

import com.liferay.portal.kernel.nio.intraband.blocking.ExecutorIntraband;
import com.liferay.portal.kernel.nio.intraband.nonblocking.SelectorIntraband;
import com.liferay.portal.kernel.nio.intraband.welder.Welder;
import com.liferay.portal.kernel.nio.intraband.welder.WelderFactoryUtil;
import com.liferay.portal.kernel.nio.intraband.welder.socket.SocketWelder;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;

import java.lang.reflect.Constructor;

/**
 * @author Shuyang Zhou
 */
public class IntrabandFactoryUtil {

	public static Intraband createIntraband() throws IOException {
		if (Validator.isNotNull(_INTRABAND_IMPL)) {
			try {
				Class<? extends Intraband> intrabandClass =
					(Class<? extends Intraband>)Class.forName(_INTRABAND_IMPL);

				Constructor<? extends Intraband> constructor =
					intrabandClass.getConstructor(long.class);

				return constructor.newInstance(_INTRABAND_TIMEOUT_DEFAULT);
			}
			catch (Exception exception) {
				throw new RuntimeException(
					"Unable to instantiate " + _INTRABAND_IMPL, exception);
			}
		}
		else {
			Class<? extends Welder> welderClass =
				WelderFactoryUtil.getWelderClass();

			if (welderClass.equals(SocketWelder.class)) {
				return new SelectorIntraband(_INTRABAND_TIMEOUT_DEFAULT);
			}

			return new ExecutorIntraband(_INTRABAND_TIMEOUT_DEFAULT);
		}
	}

	private static final String _INTRABAND_IMPL = GetterUtil.getString(
		System.getProperty(PropsKeys.INTRABAND_IMPL));

	private static final long _INTRABAND_TIMEOUT_DEFAULT = GetterUtil.getLong(
		System.getProperty(PropsKeys.INTRABAND_TIMEOUT_DEFAULT));

}