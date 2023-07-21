/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.reading.time.calculator;

import com.liferay.portal.kernel.model.GroupedModel;

import java.time.Duration;

import java.util.Locale;
import java.util.Optional;

/**
 * @author Alejandro Tardín
 */
public interface ReadingTimeCalculator {

	public Optional<Duration> calculate(GroupedModel groupedModel);

	public Optional<Duration> calculate(
		String content, String contentType, Locale locale);

}