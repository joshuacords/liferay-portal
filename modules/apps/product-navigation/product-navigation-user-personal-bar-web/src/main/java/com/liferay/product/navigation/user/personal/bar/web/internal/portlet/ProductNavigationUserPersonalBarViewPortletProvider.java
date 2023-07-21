/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.product.navigation.user.personal.bar.web.internal.portlet;

import com.liferay.admin.kernel.util.PortalUserPersonalBarApplicationType;
import com.liferay.portal.kernel.portlet.BasePortletProvider;
import com.liferay.portal.kernel.portlet.ViewPortletProvider;
import com.liferay.product.navigation.user.personal.bar.web.internal.contants.ProductNavigationUserPersonalBarPortletKeys;

import org.osgi.service.component.annotations.Component;

/**
 * @author Eudaldo Alonso
 */
@Component(
	immediate = true,
	property = "model.class.name=" + PortalUserPersonalBarApplicationType.UserPersonalBar.CLASS_NAME,
	service = ViewPortletProvider.class
)
public class ProductNavigationUserPersonalBarViewPortletProvider
	extends BasePortletProvider implements ViewPortletProvider {

	@Override
	public String getPortletName() {
		return ProductNavigationUserPersonalBarPortletKeys.
			PRODUCT_NAVIGATION_USER_PERSONAL_BAR;
	}

}