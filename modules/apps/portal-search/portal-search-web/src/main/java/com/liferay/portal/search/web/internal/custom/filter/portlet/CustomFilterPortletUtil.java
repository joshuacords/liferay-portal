/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.web.internal.custom.filter.portlet;

import com.liferay.portal.search.web.internal.util.SearchOptionalUtil;

import java.util.stream.Stream;

/**
 * @author André de Oliveira
 */
public class CustomFilterPortletUtil {

	public static String getParameterName(
		CustomFilterPortletPreferences customFilterPortletPreferences) {

		return SearchOptionalUtil.findFirstPresent(
			Stream.of(
				customFilterPortletPreferences.getParameterNameOptional(),
				customFilterPortletPreferences.getFilterFieldOptional()),
			"customfilter");
	}

}