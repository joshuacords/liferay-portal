/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.web.internal.portlet;

import com.liferay.dynamic.data.mapping.constants.DDMPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.BasePortletProvider;
import com.liferay.portal.kernel.portlet.BrowsePortletProvider;

import javax.portlet.Portlet;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jürgen Kappler
 */
@Component(
	immediate = true,
	property = {
		"model.class.name=com.liferay.dynamic.data.mapping.model.DDMFormInstance",
		"service.ranking:Integer=" + (Integer.MAX_VALUE - 1000)
	},
	service = BrowsePortletProvider.class
)
public class DDMFormBrowsePortletProvider
	extends BasePortletProvider implements BrowsePortletProvider {

	@Override
	public String getPortletName() {
		return DDMPortletKeys.DYNAMIC_DATA_MAPPING_FORM_BROWSER;
	}

	@Override
	public PortletURL getPortletURL(HttpServletRequest httpServletRequest)
		throws PortalException {

		PortletURL portletURL = super.getPortletURL(httpServletRequest);

		portletURL.setParameter("mvcPath", "/browser/view.jsp");

		return portletURL;
	}

	@Reference(
		target = "(javax.portlet.name=" + DDMPortletKeys.DYNAMIC_DATA_MAPPING_FORM_BROWSER + ")",
		unbind = "-"
	)
	protected void setPortlet(Portlet portlet) {
	}

}