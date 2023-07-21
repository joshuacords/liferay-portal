/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.admin.web.internal.servlet.taglib.clay;

import com.liferay.frontend.taglib.clay.servlet.taglib.soy.VerticalCard;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.LayoutTypeController;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.util.LayoutTypeControllerTracker;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eudaldo Alonso
 */
public class SelectBasicPagesVerticalCard implements VerticalCard {

	public SelectBasicPagesVerticalCard(
		String type, RenderRequest renderRequest,
		RenderResponse renderResponse) {

		_type = type;
		_renderResponse = renderResponse;

		_layoutTypeController =
			LayoutTypeControllerTracker.getLayoutTypeController(type);
		_httpServletRequest = PortalUtil.getHttpServletRequest(renderRequest);
		_themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);
	}

	public Map<String, Object> getDataLink() {
		Map<String, Object> data = new HashMap<>();

		String redirect = ParamUtil.getString(_httpServletRequest, "redirect");

		try {
			PortletURL addLayoutURL = _renderResponse.createRenderURL();

			addLayoutURL.setParameter(
				"mvcRenderCommandName", "/layout/add_layout");
			addLayoutURL.setParameter("backURL", redirect);

			long selPlid = ParamUtil.getLong(_httpServletRequest, "selPlid");

			addLayoutURL.setParameter("selPlid", String.valueOf(selPlid));

			boolean privateLayout = ParamUtil.getBoolean(
				_httpServletRequest, "privateLayout");

			addLayoutURL.setParameter(
				"privateLayout", String.valueOf(privateLayout));

			addLayoutURL.setParameter("type", _type);
			addLayoutURL.setWindowState(LiferayWindowState.POP_UP);

			data.put("add-layout-url", addLayoutURL.toString());
		}
		catch (Exception exception) {
		}

		return data;
	}

	@Override
	public String getElementClasses() {
		return "add-layout-action-option card-interactive " +
			"card-interactive-primary";
	}

	@Override
	public String getImageSrc() {
		StringBundler sb = new StringBundler(4);

		sb.append(PortalUtil.getPathContext(_httpServletRequest));
		sb.append("/images/");
		sb.append(_type);
		sb.append(".svg");

		return sb.toString();
	}

	@Override
	public String getSubtitle() {
		ResourceBundle layoutTypeResourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", _themeDisplay.getLocale(),
			_layoutTypeController.getClass());

		return LanguageUtil.get(
			_httpServletRequest, layoutTypeResourceBundle,
			"layout.types." + _type + ".description");
	}

	@Override
	public String getTitle() {
		ResourceBundle layoutTypeResourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", _themeDisplay.getLocale(),
			_layoutTypeController.getClass());

		return LanguageUtil.get(
			_httpServletRequest, layoutTypeResourceBundle,
			"layout.types." + _type);
	}

	@Override
	public boolean isSelectable() {
		return false;
	}

	private final HttpServletRequest _httpServletRequest;
	private final LayoutTypeController _layoutTypeController;
	private final RenderResponse _renderResponse;
	private final ThemeDisplay _themeDisplay;
	private final String _type;

}