/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.application.list;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.osb.provisioning.web.internal.application.list.constants.ProvisioningPanelCategoryKeys;
import com.liferay.portal.kernel.model.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Yuanyuan Huang
 */
@Component(
	immediate = true,
	property = {
		"panel.app.order:Integer=30",
		"panel.category.key=" + ProvisioningPanelCategoryKeys.CONTROL_PANEL_PROVISIONING
	},
	service = PanelApp.class
)
public class ProductBundlesPanelApp extends BasePanelApp {

	@Override
	public String getPortletId() {
		return ProvisioningPortletKeys.PRODUCT_BUNDLES;
	}

	@Override
	@Reference(
		target = "(javax.portlet.name=" + ProvisioningPortletKeys.PRODUCT_BUNDLES + ")",
		unbind = "-"
	)
	public void setPortlet(Portlet portlet) {
		super.setPortlet(portlet);
	}

}