/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.kernel.util;

import com.liferay.portal.kernel.util.ServiceProxyFactory;

import java.util.Locale;

/**
 * @author     Marcellus Tavares
 * @author     Bruno Basto
 * @author     Leonardo Barros
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class JournalConverterManagerUtil {

	public static String getDDMXSD(String journalXSD, Locale defaultLocale)
		throws Exception {

		return _journalConverterManager.getDDMXSD(journalXSD, defaultLocale);
	}

	private static volatile JournalConverterManager _journalConverterManager =
		ServiceProxyFactory.newServiceTrackedInstance(
			JournalConverterManager.class, JournalConverterManagerUtil.class,
			"_journalConverterManager", false);

}