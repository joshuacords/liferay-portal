/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.akismet.client.util;

import com.liferay.akismet.client.configuration.AkismetServiceConfiguration;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;

/**
 * @author Jamie Sammons
 */
@Component(
	configurationPid = "com.liferay.akismet.client.configuration.AkismetServiceConfiguration",
	configurationPolicy = ConfigurationPolicy.REQUIRE, immediate = true,
	service = {}
)
public class AkismetServiceConfigurationUtil {

	public static int getAkismetCheckThreshold() {
		return _akismetServiceConfiguration.akismetCheckThreshold();
	}

	public static AkismetServiceConfiguration getAkismetServiceConfiguration() {
		return _akismetServiceConfiguration;
	}

	public static String getAPIKey() {
		String apiKey = null;

		try {
			apiKey = _akismetServiceConfiguration.akismetApiKey();
		}
		catch (NullPointerException nullPointerException) {
		}

		return apiKey;
	}

	public static Date getReportableTime(long companyId) {
		int reportableTime =
			_akismetServiceConfiguration.akismetReportableTime();

		return new Date(
			System.currentTimeMillis() - (reportableTime * Time.DAY));
	}

	public static Date getRetainSpamTime() {
		long retainSpamTime =
			_akismetServiceConfiguration.akismetRetainSpamTime() * Time.DAY;

		return new Date(System.currentTimeMillis() - retainSpamTime);
	}

	public static boolean isMessageBoardsEnabled() {
		if (Validator.isNull(getAPIKey())) {
			return false;
		}

		return _akismetServiceConfiguration.messageBoardsEnabled();
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_akismetServiceConfiguration = ConfigurableUtil.createConfigurable(
			AkismetServiceConfiguration.class, properties);
	}

	private static volatile AkismetServiceConfiguration
		_akismetServiceConfiguration;

}