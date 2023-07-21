/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.cart.taglib.servlet.taglib.internal.servlet;

import com.liferay.commerce.service.CommerceOrderItemService;
import com.liferay.frontend.js.loader.modules.extender.npm.NPMResolver;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = ServletContextUtil.class)
public class ServletContextUtil {

	public static final CommerceOrderItemService getCommerceOrderItemService() {
		return _servletContextUtil._getCommerceOrderItemService();
	}

	public static final NPMResolver getNPMResolver() {
		return _servletContextUtil._getNPMResolver();
	}

	public static final ServletContext getServletContext() {
		return _servletContextUtil._getServletContext();
	}

	@Activate
	protected void activate() {
		_servletContextUtil = this;
	}

	@Deactivate
	protected void deactivate() {
		_servletContextUtil = null;
	}

	@Reference(unbind = "-")
	protected void setCommerceOrderItemService(
		CommerceOrderItemService commerceOrderItemService) {

		_commerceOrderItemService = commerceOrderItemService;
	}

	@Reference(unbind = "-")
	protected void setNPMResolver(NPMResolver npmResolver) {
		_npmResolver = npmResolver;
	}

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.commerce.cart.taglib)",
		unbind = "-"
	)
	protected void setServletContext(ServletContext servletContext) {
		_servletContext = servletContext;
	}

	private CommerceOrderItemService _getCommerceOrderItemService() {
		return _commerceOrderItemService;
	}

	private NPMResolver _getNPMResolver() {
		return _npmResolver;
	}

	private ServletContext _getServletContext() {
		return _servletContext;
	}

	private static ServletContextUtil _servletContextUtil;

	private CommerceOrderItemService _commerceOrderItemService;
	private NPMResolver _npmResolver;
	private ServletContext _servletContext;

}