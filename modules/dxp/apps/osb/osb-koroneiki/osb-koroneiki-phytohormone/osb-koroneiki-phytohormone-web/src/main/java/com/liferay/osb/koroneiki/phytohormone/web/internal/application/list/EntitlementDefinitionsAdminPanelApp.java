/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phytohormone.web.internal.application.list;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.osb.koroneiki.application.list.constants.KoroneikiPanelCategoryKeys;
import com.liferay.osb.koroneiki.phytohormone.constants.PhytohormonePortletKeys;
import com.liferay.portal.kernel.model.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = {
		"panel.app.order:Integer=6",
		"panel.category.key=" + KoroneikiPanelCategoryKeys.CONTROL_PANEL_KORONEIKI
	},
	service = PanelApp.class
)
public class EntitlementDefinitionsAdminPanelApp extends BasePanelApp {

	@Override
	public String getPortletId() {
		return PhytohormonePortletKeys.ENTITLEMENT_DEFINITIONS_ADMIN;
	}

	@Override
	@Reference(
		target = "(javax.portlet.name=" + PhytohormonePortletKeys.ENTITLEMENT_DEFINITIONS_ADMIN + ")",
		unbind = "-"
	)
	public void setPortlet(Portlet portlet) {
		super.setPortlet(portlet);
	}

}