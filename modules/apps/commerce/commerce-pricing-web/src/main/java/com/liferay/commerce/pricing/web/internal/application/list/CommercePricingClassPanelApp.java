/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.pricing.web.internal.application.list;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.commerce.application.list.constants.CommercePanelCategoryKeys;
import com.liferay.commerce.pricing.constants.CommercePricingPortletKeys;
import com.liferay.portal.kernel.model.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	immediate = true,
	property = {
		"panel.app.order:Integer=350",
		"panel.category.key=" + CommercePanelCategoryKeys.CONTROL_PANEL_COMMERCE
	},
	service = PanelApp.class
)
public class CommercePricingClassPanelApp extends BasePanelApp {

	@Override
	public String getPortletId() {
		return CommercePricingPortletKeys.COMMERCE_PRICING_CLASSES;
	}

	@Override
	@Reference(
		target = "(javax.portlet.name=" + CommercePricingPortletKeys.COMMERCE_PRICING_CLASSES + ")",
		unbind = "-"
	)
	public void setPortlet(Portlet portlet) {
		super.setPortlet(portlet);
	}

}