/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.opensocial.gadget.portlet;

import com.liferay.opensocial.gadget.action.ConfigurationActionImpl;
import com.liferay.opensocial.model.Gadget;
import com.liferay.opensocial.shindig.util.ShindigUtil;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;

import org.apache.shindig.gadgets.spec.GadgetSpec;

/**
 * @author Michael Young
 */
public class GadgetPortlet extends BaseGadgetPortlet {

	@Override
	protected Gadget getGadget(RenderRequest renderRequest) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PortletConfig portletConfig = getPortletConfig();

		return ShindigUtil.getGadget(
			portletConfig.getPortletName(), themeDisplay.getCompanyId());
	}

	@Override
	protected void overrideConfiguration(
			GadgetSpec gadgetSpec, Portlet portlet,
			PortletDisplay portletDisplay)
		throws Exception {

		String urlConfiguration = portletDisplay.getURLConfiguration();

		if (ShindigUtil.hasUserPrefs(gadgetSpec)) {
			portlet.setConfigurationActionClass(
				ConfigurationActionImpl.class.getName());

			urlConfiguration = urlConfiguration.replaceAll(
				"edit_permissions", "edit_configuration");
		}
		else {
			portlet.setConfigurationActionClass(null);

			urlConfiguration = urlConfiguration.replaceAll(
				"edit_configuration", "edit_permissions");
		}

		portletDisplay.setURLConfiguration(urlConfiguration);
	}

}