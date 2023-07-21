/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.amazon.rankings.web.internal.portlet.action;

import com.liferay.amazon.rankings.web.internal.configuration.AmazonRankingsConfiguration;
import com.liferay.amazon.rankings.web.internal.constants.AmazonRankingsPortletKeys;
import com.liferay.petra.string.CharPool;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Arrays;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	configurationPid = "com.liferay.amazon.rankings.web.internal.configuration.AmazonRankingsConfiguration",
	immediate = true,
	property = "javax.portlet.name=" + AmazonRankingsPortletKeys.AMAZON_RANKINGS,
	service = ConfigurationAction.class
)
public class AmazonRankingsConfigurationAction
	extends DefaultConfigurationAction {

	@Override
	public String getJspPath(HttpServletRequest httpServletRequest) {
		return "/configuration.jsp";
	}

	@Override
	public void include(
			PortletConfig portletConfig, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception {

		httpServletRequest.setAttribute(
			AmazonRankingsConfiguration.class.getName(),
			_amazonRankingsConfiguration);

		super.include(portletConfig, httpServletRequest, httpServletResponse);
	}

	@Override
	public void processAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		String[] isbns = StringUtil.split(
			StringUtil.toUpperCase(getParameter(actionRequest, "isbns")),
			CharPool.SPACE);

		Arrays.sort(isbns);

		setPreference(actionRequest, "isbns", isbns);

		super.processAction(portletConfig, actionRequest, actionResponse);
	}

	@Override
	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.amazon.rankings.web)",
		unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		super.setServletContext(servletContext);
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_amazonRankingsConfiguration = ConfigurableUtil.createConfigurable(
			AmazonRankingsConfiguration.class, properties);
	}

	private volatile AmazonRankingsConfiguration _amazonRankingsConfiguration;

}