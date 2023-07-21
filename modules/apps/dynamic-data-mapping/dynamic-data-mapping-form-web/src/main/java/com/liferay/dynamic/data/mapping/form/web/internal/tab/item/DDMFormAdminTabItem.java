/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.web.internal.tab.item;

import com.liferay.dynamic.data.mapping.constants.DDMPortletKeys;
import com.liferay.dynamic.data.mapping.util.DDMDisplayTabItem;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;

/**
 * @author Lino Alves
 */
@Component(
	property = "javax.portlet.name=" + DDMPortletKeys.DYNAMIC_DATA_MAPPING_FORM_ADMIN,
	service = {DDMDisplayTabItem.class, DDMFormAdminTabItem.class}
)
public class DDMFormAdminTabItem implements DDMDisplayTabItem {

	@Override
	public String getTitle(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse) {

		return LanguageUtil.get(
			liferayPortletRequest.getHttpServletRequest(), "forms");
	}

	@Override
	public String getURL(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse)
		throws Exception {

		PortletURL portletURL = getPortletURL(
			liferayPortletRequest, liferayPortletResponse);

		portletURL.setParameter("currentTab", "forms");

		return portletURL.toString();
	}

	protected PortletURL getPortletURL(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse) {

		PortletURL portletURL = PortletURLFactoryUtil.create(
			liferayPortletRequest,
			DDMPortletKeys.DYNAMIC_DATA_MAPPING_FORM_ADMIN,
			PortletRequest.RENDER_PHASE);

		ThemeDisplay themeDisplay =
			(ThemeDisplay)liferayPortletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		portletURL.setParameter("backURL", themeDisplay.getURLCurrent());

		portletURL.setParameter(
			"refererPortletName",
			DDMPortletKeys.DYNAMIC_DATA_MAPPING_FORM_ADMIN);
		portletURL.setParameter(
			"groupId", String.valueOf(themeDisplay.getScopeGroupId()));

		return portletURL;
	}

}