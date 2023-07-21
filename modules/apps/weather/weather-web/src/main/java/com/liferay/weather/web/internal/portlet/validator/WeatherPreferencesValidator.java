/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.weather.web.internal.portlet.validator;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.weather.web.internal.constants.WeatherPortletKeys;
import com.liferay.weather.web.internal.model.Weather;
import com.liferay.weather.web.internal.util.WeatherUtil;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletPreferences;
import javax.portlet.PreferencesValidator;
import javax.portlet.ValidatorException;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	immediate = true,
	property = "javax.portlet.name=" + WeatherPortletKeys.WEATHER,
	service = PreferencesValidator.class
)
public class WeatherPreferencesValidator implements PreferencesValidator {

	@Override
	public void validate(PortletPreferences preferences)
		throws ValidatorException {

		String apiKey = preferences.getValue("apiKey", StringPool.BLANK);

		if (Validator.isNull(apiKey)) {
			return;
		}

		List<String> badZips = new ArrayList<>();

		String[] zips = preferences.getValues("zips", new String[0]);

		for (String zip : zips) {
			Weather weather = WeatherUtil.getWeather(zip, apiKey);

			if (weather == null) {
				badZips.add(zip);
			}
		}

		if (!badZips.isEmpty()) {
			throw new ValidatorException("Failed to retrieve zips", badZips);
		}
	}

}