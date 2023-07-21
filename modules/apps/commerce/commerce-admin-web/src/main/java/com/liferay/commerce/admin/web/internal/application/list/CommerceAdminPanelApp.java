/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.admin.web.internal.application.list;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.commerce.admin.CommerceAdminModule;
import com.liferay.commerce.admin.constants.CommerceAdminPortletKeys;
import com.liferay.commerce.admin.web.internal.util.CommerceAdminModuleRegistry;
import com.liferay.commerce.application.list.constants.CommercePanelCategoryKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Andrea Di Giorgi
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = {
		"panel.app.order:Integer=900",
		"panel.category.key=" + CommercePanelCategoryKeys.CONTROL_PANEL_COMMERCE
	},
	service = PanelApp.class
)
public class CommerceAdminPanelApp extends BasePanelApp {

	@Override
	public String getPortletId() {
		return CommerceAdminPortletKeys.COMMERCE_ADMIN;
	}

	@Override
	public boolean isShow(PermissionChecker permissionChecker, Group group)
		throws PortalException {

		boolean show = super.isShow(permissionChecker, group);

		if (show) {
			Map<String, CommerceAdminModule> commerceAdminModules =
				_commerceAdminModuleRegistry.getCommerceAdminModules();

			if (commerceAdminModules.isEmpty()) {
				show = false;
			}
		}

		return show;
	}

	@Override
	@Reference(
		target = "(javax.portlet.name=" + CommerceAdminPortletKeys.COMMERCE_ADMIN + ")",
		unbind = "-"
	)
	public void setPortlet(Portlet portlet) {
		super.setPortlet(portlet);
	}

	@Reference
	private CommerceAdminModuleRegistry _commerceAdminModuleRegistry;

}