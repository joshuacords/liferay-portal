/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.reading.time.web.internal.message;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.reading.time.message.ReadingTimeMessageProvider;
import com.liferay.reading.time.model.ReadingTimeEntry;

import java.time.Duration;

import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;

/**
 * @author Alejandro Tardín
 */
@Component(
	immediate = true, property = "display.style=descriptive",
	service = ReadingTimeMessageProvider.class
)
public class DescriptiveReadingTimeMessageProviderImpl
	implements ReadingTimeMessageProvider {

	@Override
	public String provide(Duration readingTimeDuration, Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			locale, DescriptiveReadingTimeMessageProviderImpl.class);

		long readingTimeInMinutes = readingTimeDuration.toMinutes();

		if (readingTimeInMinutes == 0) {
			return LanguageUtil.get(resourceBundle, "less-than-a-minute-read");
		}
		else if (readingTimeInMinutes == 1) {
			return LanguageUtil.get(resourceBundle, "a-minute-read");
		}

		return LanguageUtil.format(
			resourceBundle, "x-minutes-read", readingTimeInMinutes);
	}

	@Override
	public String provide(ReadingTimeEntry readingTimeEntry, Locale locale) {
		return provide(
			Duration.ofMillis(readingTimeEntry.getReadingTime()), locale);
	}

}