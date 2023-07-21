/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.template.soy.context.contributor.internal;

import com.liferay.portal.kernel.portlet.ThemeDisplayModel;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.template.TemplateContextContributor;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

/**
 * @author Bruno Basto
 */
@Component(
	immediate = true,
	property = {
		"lang.type=" + TemplateConstants.LANG_TYPE_SOY,
		"type=" + TemplateContextContributor.TYPE_GLOBAL
	},
	service = TemplateContextContributor.class
)
public class ThemeDisplaySoyTemplateContextContributor
	implements TemplateContextContributor {

	@Override
	public void prepare(
		Map<String, Object> contextObjects,
		HttpServletRequest httpServletRequest) {

		ThemeDisplayModel themeDisplayModel = null;

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		if (themeDisplay != null) {
			themeDisplayModel = new ThemeDisplayModel(themeDisplay);
		}

		contextObjects.put("themeDisplayModel", themeDisplayModel);
	}

}