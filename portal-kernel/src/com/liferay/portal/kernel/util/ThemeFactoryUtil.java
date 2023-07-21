/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.util;

import com.liferay.portal.kernel.model.Theme;

/**
 * @author Harrison Schueler
 */
public class ThemeFactoryUtil {

	public static Theme getDefaultRegularTheme(long companyId) {
		return getThemeFactory().getDefaultRegularTheme(companyId);
	}

	public static String getDefaultRegularThemeId(long companyId) {
		return getThemeFactory().getDefaultRegularThemeId(companyId);
	}

	public static Theme getTheme() {
		return getThemeFactory().getTheme();
	}

	public static Theme getTheme(String themeId) {
		return getThemeFactory().getTheme(themeId);
	}

	public static Theme getTheme(String themeId, String name) {
		return getThemeFactory().getTheme(themeId, name);
	}

	public static ThemeFactory getThemeFactory() {
		return _themeFactory;
	}

	public void setThemeFactory(ThemeFactory themeFactory) {
		_themeFactory = themeFactory;
	}

	private static ThemeFactory _themeFactory;

}