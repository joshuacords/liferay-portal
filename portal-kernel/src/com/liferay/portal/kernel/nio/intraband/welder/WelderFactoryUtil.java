/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.nio.intraband.welder;

import com.liferay.portal.kernel.nio.intraband.welder.fifo.FIFOUtil;
import com.liferay.portal.kernel.nio.intraband.welder.fifo.FIFOWelder;
import com.liferay.portal.kernel.nio.intraband.welder.socket.SocketWelder;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OSDetector;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Shuyang Zhou
 */
public class WelderFactoryUtil {

	public static Welder createWelder() {
		Class<? extends Welder> welderClass = getWelderClass();

		try {
			return welderClass.newInstance();
		}
		catch (Exception exception) {
			throw new RuntimeException(
				"Unable to create Welder instance for class " + welderClass,
				exception);
		}
	}

	public static Class<? extends Welder> getWelderClass() {
		if (Validator.isNotNull(_INTRABAND_WELDER_IMPL)) {
			try {
				return (Class<? extends Welder>)Class.forName(
					_INTRABAND_WELDER_IMPL);
			}
			catch (ClassNotFoundException classNotFoundException) {
				throw new RuntimeException(
					"Unable to load class with name " + _INTRABAND_WELDER_IMPL,
					classNotFoundException);
			}
		}
		else {
			if (!OSDetector.isWindows() && FIFOUtil.isFIFOSupported()) {
				return FIFOWelder.class;
			}

			return SocketWelder.class;
		}
	}

	private static final String _INTRABAND_WELDER_IMPL = GetterUtil.getString(
		System.getProperty(PropsKeys.INTRABAND_WELDER_IMPL));

}