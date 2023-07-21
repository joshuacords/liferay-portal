/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.portlet.configuration.icon;

import java.io.IOException;

import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Eudaldo Alonso
 */
public interface PortletConfigurationIcon {

	public String getAlt();

	public String getAriaRole();

	public String getCssClass();

	public Map<String, Object> getData();

	public String getIconCssClass();

	public String getId();

	public String getImage();

	public String getImageHover();

	public String getLang();

	public String getLinkCssClass();

	public String getMessage(PortletRequest portletRequest);

	public String getMethod();

	public String getOnClick(
		PortletRequest portletRequest, PortletResponse portletResponse);

	public String getSrc();

	public String getSrcHover();

	public String getTarget();

	public String getURL(
		PortletRequest portletRequest, PortletResponse portletResponse);

	public double getWeight();

	public boolean include(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException;

	public boolean isLabel();

	public boolean isShow(PortletRequest portletRequest);

	public default boolean isShowInEditMode(PortletRequest portletRequest) {
		return false;
	}

	public boolean isToolTip();

	public boolean isUseDialog();

}