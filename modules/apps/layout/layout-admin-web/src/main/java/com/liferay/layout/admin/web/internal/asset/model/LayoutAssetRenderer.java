/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.admin.web.internal.asset.model;

import com.liferay.asset.kernel.model.BaseJSPAssetRenderer;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Eduardo García
 */
public class LayoutAssetRenderer extends BaseJSPAssetRenderer<Layout> {

	public LayoutAssetRenderer(Layout layout) {
		_layout = layout;
	}

	@Override
	public Layout getAssetObject() {
		return _layout;
	}

	@Override
	public String getClassName() {
		return Layout.class.getName();
	}

	@Override
	public long getClassPK() {
		return _layout.getLayoutId();
	}

	@Override
	public long getGroupId() {
		return _layout.getGroupId();
	}

	@Override
	public String getJspPath(
		HttpServletRequest httpServletRequest, String template) {

		if (template.equals(TEMPLATE_FULL_CONTENT)) {
			return "/asset/" + template + ".jsp";
		}

		return null;
	}

	@Override
	public String getSummary(
		PortletRequest portletRequest, PortletResponse portletResponse) {

		Locale locale = getLocale(portletRequest);

		StringBundler sb = new StringBundler(4);

		sb.append("<strong>");
		sb.append(LanguageUtil.get(locale, "page"));
		sb.append(":</strong> ");
		sb.append(_layout.getHTMLTitle(locale));

		return sb.toString();
	}

	@Override
	public String getTitle(Locale locale) {
		return _layout.getHTMLTitle(locale);
	}

	@Override
	public String getURLViewInContext(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse,
		String noSuchEntryRedirect) {

		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)liferayPortletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			return PortalUtil.getLayoutFriendlyURL(
				LayoutLocalServiceUtil.getLayout(_layout.getPlid()),
				themeDisplay);
		}
		catch (Exception exception) {
			return StringPool.BLANK;
		}
	}

	@Override
	public long getUserId() {
		return _layout.getUserId();
	}

	@Override
	public String getUserName() {
		return _layout.getUserName();
	}

	@Override
	public String getUuid() {
		return null;
	}

	@Override
	public boolean include(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, String template)
		throws Exception {

		httpServletRequest.setAttribute(WebKeys.LAYOUT, _layout);

		return super.include(httpServletRequest, httpServletResponse, template);
	}

	private final Layout _layout;

}