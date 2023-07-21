/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.content.search.web.internal.portlet.action;

import com.liferay.commerce.currency.util.CommercePriceFormatter;
import com.liferay.commerce.product.constants.CPPortletKeys;
import com.liferay.commerce.product.content.search.web.internal.display.context.CPPriceRangeFacetsDisplayContext;
import com.liferay.commerce.product.display.context.util.CPRequestHelper;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.search.web.portlet.shared.search.PortletSharedSearchRequest;
import com.liferay.portal.search.web.portlet.shared.search.PortletSharedSearchResponse;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alec Sloan
 */
@Component(
	immediate = true,
	property = "javax.portlet.name=" + CPPortletKeys.CP_PRICE_RANGE_FACETS,
	service = ConfigurationAction.class
)
public class CPPriceRangeFacetsConfigurationAction
	extends DefaultConfigurationAction {

	@Override
	public String getJspPath(HttpServletRequest httpServletRequest) {
		CPRequestHelper cpRequestHelper = new CPRequestHelper(
			httpServletRequest);

		PortletSharedSearchResponse portletSharedSearchResponse =
			_portletSharedSearchRequest.search(
				cpRequestHelper.getRenderRequest());

		try {
			CPPriceRangeFacetsDisplayContext cpPriceRangeFacetsDisplayContext =
				new CPPriceRangeFacetsDisplayContext(
					_commercePriceFormatter, cpRequestHelper.getRenderRequest(),
					null, portletSharedSearchResponse);

			httpServletRequest.setAttribute(
				WebKeys.PORTLET_DISPLAY_CONTEXT,
				cpPriceRangeFacetsDisplayContext);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return "/price_range_facets/configuration.jsp";
	}

	@Override
	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.commerce.product.content.search.web)",
		unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		super.setServletContext(servletContext);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CPPriceRangeFacetsConfigurationAction.class);

	@Reference
	private CommercePriceFormatter _commercePriceFormatter;

	@Reference
	private PortletSharedSearchRequest _portletSharedSearchRequest;

}