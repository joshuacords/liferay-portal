/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.product.navigation.taglib.internal.servlet;

import com.liferay.product.navigation.control.menu.util.ProductNavigationControlMenuCategoryRegistry;
import com.liferay.product.navigation.control.menu.util.ProductNavigationControlMenuEntryRegistry;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Julio Camarero
 */
@Component(immediate = true, service = {})
public class ServletContextUtil {

	public static final String getContextPath() {
		return _servletContext.getContextPath();
	}

	public static final ProductNavigationControlMenuCategoryRegistry
		getProductNavigationControlMenuCategoryRegistry() {

		return _productNavigationControlMenuCategoryRegistry;
	}

	public static final ProductNavigationControlMenuEntryRegistry
		getProductNavigationControlMenuEntryRegistry() {

		return _productNavigationControlMenuEntryRegistry;
	}

	public static final ServletContext getServletContext() {
		return _servletContext;
	}

	@Reference(unbind = "-")
	protected void setProductNavigationControlMenuCategoryRegistry(
		ProductNavigationControlMenuCategoryRegistry
			productNavigationControlMenuCategoryRegistry) {

		_productNavigationControlMenuCategoryRegistry =
			productNavigationControlMenuCategoryRegistry;
	}

	@Reference(unbind = "-")
	protected void setProductNavigationControlMenuEntryRegistry(
		ProductNavigationControlMenuEntryRegistry
			productNavigationControlMenuEntryRegistry) {

		_productNavigationControlMenuEntryRegistry =
			productNavigationControlMenuEntryRegistry;
	}

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.product.navigation.taglib)",
		unbind = "-"
	)
	protected void setServletContext(ServletContext servletContext) {
		_servletContext = servletContext;
	}

	private static ProductNavigationControlMenuCategoryRegistry
		_productNavigationControlMenuCategoryRegistry;
	private static ProductNavigationControlMenuEntryRegistry
		_productNavigationControlMenuEntryRegistry;
	private static ServletContext _servletContext;

}