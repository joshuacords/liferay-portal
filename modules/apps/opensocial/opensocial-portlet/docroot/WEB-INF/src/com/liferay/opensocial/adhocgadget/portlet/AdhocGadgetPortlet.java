/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.opensocial.adhocgadget.portlet;

import com.liferay.opensocial.gadget.portlet.BaseGadgetPortlet;
import com.liferay.opensocial.model.Gadget;
import com.liferay.opensocial.shindig.util.ShindigUtil;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;

/**
 * @author Michael Young
 */
public class AdhocGadgetPortlet extends BaseGadgetPortlet {

	@Override
	protected Gadget getGadget(RenderRequest renderRequest) throws Exception {
		Portlet portlet = (Portlet)renderRequest.getAttribute(
			WebKeys.RENDER_PORTLET);

		PortletPreferences portletPreferences =
			PortletPreferencesFactoryUtil.getPortletSetup(
				renderRequest, portlet.getPortletId());

		return ShindigUtil.getGadget(portletPreferences);
	}

}