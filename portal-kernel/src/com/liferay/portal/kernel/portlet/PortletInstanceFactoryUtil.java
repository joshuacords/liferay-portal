/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.portlet;

import com.liferay.portal.kernel.model.Portlet;

import javax.portlet.PortletException;

import javax.servlet.ServletContext;

/**
 * @author Brian Wing Shun Chan
 */
public class PortletInstanceFactoryUtil {

	public static void clear(Portlet portlet) {
		getPortletInstanceFactory().clear(portlet);
	}

	public static void clear(Portlet portlet, boolean resetRemotePortletBag) {
		getPortletInstanceFactory().clear(portlet, resetRemotePortletBag);
	}

	public static InvokerPortlet create(
			Portlet portlet, ServletContext servletContext)
		throws PortletException {

		return getPortletInstanceFactory().create(portlet, servletContext);
	}

	public static InvokerPortlet create(
			Portlet portlet, ServletContext servletContext,
			boolean destroyPrevious)
		throws PortletException {

		return getPortletInstanceFactory().create(
			portlet, servletContext, destroyPrevious);
	}

	public static void delete(Portlet portlet) {
		getPortletInstanceFactory().delete(portlet);
	}

	public static void destroy(Portlet portlet) {
		getPortletInstanceFactory().destroy(portlet);
	}

	public static PortletInstanceFactory getPortletInstanceFactory() {
		return _portletInstanceFactory;
	}

	public void destroy() {

		// LPS-10473

	}

	public void setPortletInstanceFactory(
		PortletInstanceFactory portletInstanceFactory) {

		_portletInstanceFactory = portletInstanceFactory;
	}

	private static PortletInstanceFactory _portletInstanceFactory;

}