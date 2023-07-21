/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mobile.device.rules.web.internal.rule.group.action;

import com.liferay.mobile.device.rules.action.ActionHandler;
import com.liferay.mobile.device.rules.model.MDRAction;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutTypePortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Edward Han
 */
@Component(immediate = true, service = ActionHandler.class)
public class LayoutTemplateModificationActionHandler implements ActionHandler {

	public static String getHandlerType() {
		return LayoutTemplateModificationActionHandler.class.getName();
	}

	@Override
	public void applyAction(
		MDRAction mdrAction, HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		Layout layout = themeDisplay.getLayout();

		if (layout.isTypePortlet()) {
			LayoutTypePortlet layoutTypePortlet =
				(LayoutTypePortlet)layout.getLayoutType();

			UnicodeProperties mdrActionTypeSettingsProperties =
				mdrAction.getTypeSettingsProperties();

			String layoutTemplateId = GetterUtil.getString(
				mdrActionTypeSettingsProperties.getProperty(
					"layoutTemplateId"));

			layoutTypePortlet.setLayoutTemplateId(0, layoutTemplateId, false);
		}
	}

	@Override
	public String getEditorJSP() {
		return "/action/layout_tpl.jsp";
	}

	@Override
	public Collection<String> getPropertyNames() {
		return _propertyNames;
	}

	@Override
	public String getType() {
		return getHandlerType();
	}

	private static final Collection<String> _propertyNames =
		Collections.unmodifiableCollection(Arrays.asList("layoutTemplateId"));

}