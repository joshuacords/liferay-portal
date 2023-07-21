/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.order.web.internal.application.list;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.commerce.application.list.constants.CommercePanelCategoryKeys;
import com.liferay.commerce.constants.CommercePortletKeys;
import com.liferay.portal.kernel.model.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = {
		"panel.app.order:Integer=700",
		"panel.category.key=" + CommercePanelCategoryKeys.CONTROL_PANEL_COMMERCE
	},
	service = PanelApp.class
)
public class CommerceOrderPanelApp extends BasePanelApp {

	@Override
	public String getPortletId() {
		return CommercePortletKeys.COMMERCE_ORDER;
	}

	@Override
	@Reference(
		target = "(javax.portlet.name=" + CommercePortletKeys.COMMERCE_ORDER + ")",
		unbind = "-"
	)
	public void setPortlet(Portlet portlet) {
		super.setPortlet(portlet);
	}

}