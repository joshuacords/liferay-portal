/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sharing.web.internal.portlet;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.portlet.BasePortletProvider;
import com.liferay.portal.kernel.portlet.PreviewPortletProvider;
import com.liferay.sharing.web.internal.constants.SharingPortletKeys;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

/**
 * @author Sergio González
 */
@Component(
	immediate = true,
	property = "model.class.name=com.liferay.sharing.model.SharingEntry",
	service = PreviewPortletProvider.class
)
public class SharedAssetsPreviewPortletProvider
	extends BasePortletProvider implements PreviewPortletProvider {

	@Override
	public String getPortletName() {
		return SharingPortletKeys.SHARED_ASSETS;
	}

	@Override
	public PortletURL getPortletURL(
			HttpServletRequest httpServletRequest, Group group)
		throws PortalException {

		PortletURL portletURL = super.getPortletURL(httpServletRequest, group);

		portletURL.setParameter(
			"mvcRenderCommandName", "/shared_assets/view_sharing_entry");

		return portletURL;
	}

}