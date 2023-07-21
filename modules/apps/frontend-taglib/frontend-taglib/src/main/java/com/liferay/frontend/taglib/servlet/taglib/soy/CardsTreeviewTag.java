/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.taglib.servlet.taglib.soy;

import com.liferay.frontend.taglib.soy.servlet.taglib.ComponentRendererTag;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

/**
 * @author Chema Balsas
 */
public class CardsTreeviewTag extends ComponentRendererTag {

	@Override
	public int doStartTag() {
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		putValue("pathThemeImages", themeDisplay.getPathThemeImages());

		setTemplateNamespace("liferay.frontend.CardsTreeview.render");

		return super.doStartTag();
	}

	@Override
	public String getModule() {
		return "frontend-taglib/cards_treeview/CardsTreeview.es";
	}

	public void setNodes(Object nodes) {
		putValue("nodes", nodes);
	}

	public void setViewType(String viewType) {
		putValue("viewType", viewType);
	}

}