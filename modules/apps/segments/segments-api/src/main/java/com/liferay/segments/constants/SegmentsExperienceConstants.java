/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.segments.constants;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Sarai Díaz
 */
public class SegmentsExperienceConstants {

	public static final long ID_DEFAULT = 0;

	public static final String ID_PREFIX = "segments-experience-id-";

	public static final String KEY_DEFAULT = "DEFAULT";

	public static final int PRIORITY_DEFAULT = -1;

	public static String getDefaultSegmentsExperienceName(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, SegmentsExperienceConstants.class);

		return LanguageUtil.get(resourceBundle, "default-experience-name");
	}

}