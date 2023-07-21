/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.social.group.statistics.web.internal.portlet.action;

import com.liferay.portal.kernel.portlet.BaseJSPSettingsConfigurationAction;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.social.group.statistics.web.internal.constants.SocialGroupStatisticsPortletKeys;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Iván Zaera
 */
@Component(
	immediate = true,
	property = "javax.portlet.name=" + SocialGroupStatisticsPortletKeys.SOCIAL_GROUP_STATISTICS,
	service = ConfigurationAction.class
)
public class SocialGroupStatisticsConfigurationAction
	extends BaseJSPSettingsConfigurationAction {

	@Override
	public String getJspPath(HttpServletRequest httpServletRequest) {
		return "/configuration.jsp";
	}

	@Override
	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.social.group.statistics.web)",
		unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		super.setServletContext(servletContext);
	}

	@Override
	protected void updateMultiValuedKeys(ActionRequest actionRequest) {
		super.updateMultiValuedKeys(actionRequest);

		_setPreference(actionRequest, "chartType");
		_setPreference(actionRequest, "chartWidth");
		_setPreference(actionRequest, "dataRange");
		_setPreference(actionRequest, "displayActivityCounterName");
	}

	private void _setPreference(ActionRequest actionRequest, String key) {
		String[] displayActivityCounterNameIndexes = ParamUtil.getStringValues(
			actionRequest, "displayActivityCounterNameIndexes");

		List<String> values = new ArrayList<>(
			displayActivityCounterNameIndexes.length);

		for (String index : displayActivityCounterNameIndexes) {
			String value = ParamUtil.getString(actionRequest, key + index);

			if (Validator.isNotNull(value)) {
				values.add(value);
			}
		}

		setPreference(actionRequest, key, values.toArray(new String[0]));
	}

}