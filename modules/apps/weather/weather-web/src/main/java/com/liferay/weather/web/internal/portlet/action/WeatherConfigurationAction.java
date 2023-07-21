/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.weather.web.internal.portlet.action;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.weather.web.internal.constants.WeatherPortletKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.ValidatorException;

import org.osgi.service.component.annotations.Component;

/**
 * @author Samuel Kong
 * @author Peter Fellwock
 */
@Component(
	immediate = true,
	property = "javax.portlet.name=" + WeatherPortletKeys.WEATHER,
	service = ConfigurationAction.class
)
public class WeatherConfigurationAction extends DefaultConfigurationAction {

	@Override
	public void processAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		String apiKey = getParameter(actionRequest, "apiKey");

		setPreference(actionRequest, "apiKey", apiKey);

		String[] zips = StringUtil.split(
			getParameter(actionRequest, "zips"), StringPool.NEW_LINE);

		setPreference(actionRequest, "zips", zips);

		try {
			super.processAction(portletConfig, actionRequest, actionResponse);
		}
		catch (ValidatorException validatorException) {
			SessionErrors.add(
				actionRequest, ValidatorException.class.getName(),
				validatorException);
		}
	}

}