/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.template.soy.util;

import java.util.Map;

/**
 * @author Máté Thurzó
 */
public class SoyContextFactoryUtil {

	public static SoyContext createSoyContext() {
		return _soyContextFactory.createSoyContext();
	}

	public static SoyContext createSoyContext(Map<String, Object> context) {
		return _soyContextFactory.createSoyContext(context);
	}

	public static SoyContextFactory getSoyContextFactory() {
		if (_soyContextFactory != null) {
			return _soyContextFactory;
		}

		throw new NullPointerException("Soy context factory is null");
	}

	public static void setSoyContextFactory(
		SoyContextFactory soyContextFactory) {

		if (_soyContextFactory != null) {
			return;
		}

		_soyContextFactory = soyContextFactory;
	}

	private static SoyContextFactory _soyContextFactory;

}