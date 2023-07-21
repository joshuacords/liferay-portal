/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.portlet;

import javax.portlet.Portlet;
import javax.portlet.PortletConfig;
import javax.portlet.PortletContext;
import javax.portlet.PortletException;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Shuyang Zhou
 * @author Neil Griffin
 */
@ProviderType
public interface InvokerPortletFactory {

	public InvokerPortlet create(
			com.liferay.portal.kernel.model.Portlet portletModel,
			Portlet portlet, PortletConfig portletConfig,
			PortletContext portletContext,
			InvokerFilterContainer invokerFilterContainer,
			boolean checkAuthToken, boolean facesPortlet, boolean headerPortlet)
		throws PortletException;

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             #create(com.liferay.portal.kernel.model.Portlet, Portlet,
	 *             PortletConfig, PortletContext, InvokerFilterContainer,
	 *             boolean, boolean, boolean)}
	 */
	@Deprecated
	public InvokerPortlet create(
			com.liferay.portal.kernel.model.Portlet portletModel,
			Portlet portlet, PortletConfig portletConfig,
			PortletContext portletContext,
			InvokerFilterContainer invokerFilterContainer,
			boolean checkAuthToken, boolean facesPortlet, boolean strutsPortlet,
			boolean strutsBridgePortlet)
		throws PortletException;

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             #create(com.liferay.portal.kernel.model.Portlet, Portlet,
	 *             PortletConfig, PortletContext, InvokerFilterContainer,
	 *             boolean, boolean, boolean)}
	 */
	@Deprecated
	public InvokerPortlet create(
			com.liferay.portal.kernel.model.Portlet portletModel,
			Portlet portlet, PortletConfig portletConfig,
			PortletContext portletContext,
			InvokerFilterContainer invokerFilterContainer,
			boolean checkAuthToken, boolean facesPortlet, boolean headerPortlet,
			boolean strutsPortlet, boolean strutsBridgePortlet)
		throws PortletException;

	public InvokerPortlet create(
			com.liferay.portal.kernel.model.Portlet portletModel,
			Portlet portlet, PortletContext portletContext,
			InvokerFilterContainer invokerFilterContainer)
		throws PortletException;

}