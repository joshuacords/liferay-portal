/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.product.navigation.control.menu.web.internal.portlet;

import com.liferay.admin.kernel.util.PortalProductNavigationControlMenuApplicationType;
import com.liferay.portal.kernel.portlet.BasePortletProvider;
import com.liferay.portal.kernel.portlet.ViewPortletProvider;
import com.liferay.product.navigation.control.menu.web.internal.constants.ProductNavigationControlMenuPortletKeys;

import org.osgi.service.component.annotations.Component;

/**
 * @author     Eudaldo Alonso
 * @deprecated As of Judson (7.1.x), with a replacement. Theme developers must
 *             eventually switch from using Velocity  templates that leverage
 *             this taglib wrapper mechanism, to using FreeMarker templates that
 *             leverage the <code>liferay-product-navigation:control-menu</code>
 *             tag.
 */
@Component(
	immediate = true,
	property = "model.class.name=" + PortalProductNavigationControlMenuApplicationType.ProductNavigationControlMenu.CLASS_NAME,
	service = ViewPortletProvider.class
)
@Deprecated
public class ProductNavigationControlMenuViewPortletProvider
	extends BasePortletProvider implements ViewPortletProvider {

	@Override
	public String getPortletName() {
		return ProductNavigationControlMenuPortletKeys.
			PRODUCT_NAVIGATION_CONTROL_MENU;
	}

}