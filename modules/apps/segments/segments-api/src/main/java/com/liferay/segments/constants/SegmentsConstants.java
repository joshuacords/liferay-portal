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
 * @author Eduardo García
 */
public class SegmentsConstants {

	public static final String RESOURCE_NAME = "com.liferay.segments";

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	public static final long SEGMENTS_ENTRY_ID_DEFAULT = 0;

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	public static final long SEGMENTS_EXPERIENCE_ID_DEFAULT = 0;

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	public static final String SEGMENTS_EXPERIENCE_ID_PREFIX =
		"segments-experience-id-";

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	public static final int SEGMENTS_EXPERIENCE_PRIORITY_DEFAULT = -1;

	public static final String SERVICE_NAME = "com.liferay.segments";

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	public static final String SOURCE_ASAH_FARO_BACKEND = "ASAH_FARO_BACKEND";

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	public static final String SOURCE_DEFAULT = "DEFAULT";

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	public static String getDefaultSegmentsEntryName(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, SegmentsConstants.class);

		return LanguageUtil.get(resourceBundle, "default-segment-name");
	}

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	public static String getDefaultSegmentsExperienceName(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, SegmentsConstants.class);

		return LanguageUtil.get(resourceBundle, "default-experience-name");
	}

}