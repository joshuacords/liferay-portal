/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.util;

/**
 * @author     Brian Wing Shun Chan
 * @deprecated
 */
@Deprecated
public class Randomizer_IW {

	public static Randomizer_IW getInstance() {
		return _instance;
	}

	public com.liferay.portal.kernel.util.Randomizer getWrappedInstance() {
		return Randomizer.getInstance();
	}

	private Randomizer_IW() {
	}

	private static Randomizer_IW _instance = new Randomizer_IW();

}