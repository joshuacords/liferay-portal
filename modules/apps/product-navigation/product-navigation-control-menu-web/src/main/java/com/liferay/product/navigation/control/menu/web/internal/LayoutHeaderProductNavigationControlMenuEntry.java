/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.product.navigation.control.menu.web.internal;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.util.PropsValues;
import com.liferay.product.navigation.control.menu.BaseProductNavigationControlMenuEntry;
import com.liferay.product.navigation.control.menu.ProductNavigationControlMenuEntry;
import com.liferay.product.navigation.control.menu.constants.ProductNavigationControlMenuCategoryKeys;

import java.io.IOException;
import java.io.Writer;

import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(
	immediate = true,
	property = {
		"product.navigation.control.menu.category.key=" + ProductNavigationControlMenuCategoryKeys.TOOLS,
		"product.navigation.control.menu.entry.order:Integer=100"
	},
	service = ProductNavigationControlMenuEntry.class
)
public class LayoutHeaderProductNavigationControlMenuEntry
	extends BaseProductNavigationControlMenuEntry {

	@Override
	public String getLabel(Locale locale) {
		return null;
	}

	@Override
	public String getURL(HttpServletRequest httpServletRequest) {
		return null;
	}

	@Override
	public boolean includeIcon(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		Writer writer = httpServletResponse.getWriter();

		StringBundler sb = new StringBundler(15);

		sb.append("<li class=\"control-menu-nav-item ");
		sb.append("control-menu-nav-item-content \">");
		sb.append("<span class=\"align-items-center ");
		sb.append("control-menu-level-1-heading d-flex mr-1\" ");
		sb.append("data-qa-id=\"headerTitle\"><span class=\"text-truncate\">");
		sb.append(_getHeaderTitle(httpServletRequest));
		sb.append("</span>");

		if (_hasDraftLayout(httpServletRequest)) {
			sb.append("<sup class=\"flex-shrink-0 small\">*</sup>");
		}

		sb.append("</span>");

		if (_isDraftLayout(httpServletRequest)) {
			sb.append("<span class=\"bg-transparent flex-shrink-0 label ");
			sb.append("label-inverse-secondary ml-2 mr-0\">");
			sb.append("<span class=\"label-item label-item-expand\">");
			sb.append(LanguageUtil.get(httpServletRequest, "draft"));
			sb.append("</span>");
			sb.append("</span>");
		}

		writer.write(sb.toString());

		return true;
	}

	@Override
	public boolean isShow(HttpServletRequest httpServletRequest)
		throws PortalException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		Layout layout = themeDisplay.getLayout();

		if (layout.isTypeControlPanel()) {
			return false;
		}

		if (!(themeDisplay.isShowLayoutTemplatesIcon() ||
			  themeDisplay.isShowPageSettingsIcon())) {

			return false;
		}

		return super.isShow(httpServletRequest);
	}

	private String _getHeaderTitle(HttpServletRequest httpServletRequest) {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		String portletId = ParamUtil.getString(httpServletRequest, "p_p_id");

		Layout layout = themeDisplay.getLayout();

		if (Validator.isNotNull(portletId) && layout.isSystem() &&
			!layout.isTypeControlPanel() &&
			Objects.equals(
				layout.getFriendlyURL(),
				PropsValues.CONTROL_PANEL_LAYOUT_FRIENDLY_URL)) {

			return _portal.getPortletTitle(portletId, themeDisplay.getLocale());
		}

		return HtmlUtil.escape(layout.getName(themeDisplay.getLocale()));
	}

	private boolean _hasDraftLayout(HttpServletRequest httpServletRequest) {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		Layout layout = themeDisplay.getLayout();

		if (!Objects.equals(layout.getType(), LayoutConstants.TYPE_CONTENT)) {
			return false;
		}

		Layout draftLayout = _layoutLocalService.fetchLayout(
			_portal.getClassNameId(Layout.class), layout.getPlid());

		if (draftLayout != null) {
			Date modifiedDate = draftLayout.getModifiedDate();

			Date publishDate = layout.getPublishDate();

			if (publishDate == null) {
				publishDate = modifiedDate;
			}

			if ((modifiedDate.getTime() <= publishDate.getTime()) &&
				_isLayoutPublished(layout)) {

				return false;
			}
		}
		else if (_isLayoutPublished(layout)) {
			return false;
		}

		return true;
	}

	private boolean _isDraftLayout(HttpServletRequest httpServletRequest) {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		Layout layout = themeDisplay.getLayout();

		if (!Objects.equals(layout.getType(), LayoutConstants.TYPE_CONTENT)) {
			return false;
		}

		Layout draftLayout = _layoutLocalService.fetchLayout(
			_portal.getClassNameId(Layout.class), layout.getPlid());

		if ((draftLayout != null) || _isLayoutPublished(layout)) {
			return false;
		}

		return true;
	}

	private boolean _isLayoutPublished(Layout layout) {
		boolean published = GetterUtil.getBoolean(
			layout.getTypeSettingsProperty("published"));

		if (published) {
			return true;
		}

		return false;
	}

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private Portal _portal;

}