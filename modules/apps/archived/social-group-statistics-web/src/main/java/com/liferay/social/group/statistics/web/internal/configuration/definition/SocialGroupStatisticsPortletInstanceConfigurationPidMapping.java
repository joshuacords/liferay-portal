/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.social.group.statistics.web.internal.configuration.definition;

import com.liferay.portal.kernel.settings.definition.ConfigurationPidMapping;
import com.liferay.social.group.statistics.web.internal.configuration.SocialGroupStatisticsPortletInstanceConfiguration;
import com.liferay.social.group.statistics.web.internal.constants.SocialGroupStatisticsPortletKeys;

import org.osgi.service.component.annotations.Component;

/**
 * @author Kailash Yadav
 */
@Component(service = ConfigurationPidMapping.class)
public class SocialGroupStatisticsPortletInstanceConfigurationPidMapping
	implements ConfigurationPidMapping {

	@Override
	public Class<?> getConfigurationBeanClass() {
		return SocialGroupStatisticsPortletInstanceConfiguration.class;
	}

	@Override
	public String getConfigurationPid() {
		return SocialGroupStatisticsPortletKeys.SOCIAL_GROUP_STATISTICS;
	}

}