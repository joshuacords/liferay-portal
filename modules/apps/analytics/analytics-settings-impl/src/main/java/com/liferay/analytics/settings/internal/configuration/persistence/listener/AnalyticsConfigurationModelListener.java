/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.analytics.settings.internal.configuration.persistence.listener;

import com.liferay.analytics.settings.configuration.AnalyticsConfiguration;
import com.liferay.analytics.settings.configuration.AnalyticsConfigurationTracker;
import com.liferay.portal.configuration.persistence.listener.ConfigurationModelListener;
import com.liferay.portal.kernel.util.ArrayUtil;

import java.util.Dictionary;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Shinn Lok
 */
@Component(
	immediate = true,
	property = "model.class.name=com.liferay.analytics.settings.configuration.AnalyticsConfiguration.scoped",
	service = ConfigurationModelListener.class
)
public class AnalyticsConfigurationModelListener
	implements ConfigurationModelListener {

	@Override
	public void onBeforeSave(
		String pid, Dictionary<String, Object> properties) {

		AnalyticsConfiguration analyticsConfiguration =
			_analyticsConfigurationTracker.getAnalyticsConfiguration(pid);

		properties.put(
			"previousSyncAllContacts",
			analyticsConfiguration.syncAllContacts());

		String[] syncedContactFieldNames =
			analyticsConfiguration.syncedContactFieldNames();

		if (!ArrayUtil.isEmpty(syncedContactFieldNames)) {
			properties.put(
				"previousSyncedContactFieldNames", syncedContactFieldNames);
		}

		String[] syncedUserFieldNames =
			analyticsConfiguration.syncedUserFieldNames();

		if (!ArrayUtil.isEmpty(syncedUserFieldNames)) {
			properties.put(
				"previousSyncedUserFieldNames", syncedUserFieldNames);
		}

		String token = analyticsConfiguration.token();

		if (token != null) {
			properties.put("previousToken", token);
		}
	}

	@Reference
	private AnalyticsConfigurationTracker _analyticsConfigurationTracker;

}