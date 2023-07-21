/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.google.maps.web.internal.portlet.action;

import com.liferay.google.maps.web.internal.constants.GoogleMapsPortletKeys;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletSession;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Mark Wong
 * @author Peter Fellwock
 */
@Component(
	immediate = true,
	property = "javax.portlet.name=" + GoogleMapsPortletKeys.GOOGLE_MAPS,
	service = ConfigurationAction.class
)
public class GoogleMapsConfigurationAction extends DefaultConfigurationAction {

	@Override
	public void processAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		String portletResource = ParamUtil.getString(
			actionRequest, "portletResource");

		PortletSession portletSession = actionRequest.getPortletSession();

		portletSession.removeAttribute(
			_portal.getPortletNamespace(portletResource) + "mapAddress",
			PortletSession.APPLICATION_SCOPE);

		portletSession.removeAttribute(
			_portal.getPortletNamespace(portletResource) + "directionsAddress",
			PortletSession.APPLICATION_SCOPE);

		super.processAction(portletConfig, actionRequest, actionResponse);
	}

	@Reference
	private Portal _portal;

}