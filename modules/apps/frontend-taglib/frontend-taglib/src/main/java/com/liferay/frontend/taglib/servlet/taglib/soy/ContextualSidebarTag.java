/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.taglib.servlet.taglib.soy;

import com.liferay.frontend.taglib.soy.servlet.taglib.ComponentRendererTag;

import java.util.Map;

/**
 * @author Chema Balsas
 */
public class ContextualSidebarTag extends ComponentRendererTag {

	@Override
	public int doStartTag() {
		Map<String, Object> context = getContext();

		if (context.get("visible") == null) {
			putValue("visible", true);
		}

		setTemplateNamespace("liferay.frontend.ContextualSidebar.render");

		return super.doStartTag();
	}

	@Override
	public String getModule() {
		return "frontend-taglib/contextual_sidebar/ContextualSidebar.es";
	}

	public void setBody(String body) {
		putHTMLValue("body", body);
	}

	public void setBodyClasses(String bodyClasses) {
		putValue("bodyClasses", bodyClasses);
	}

	public void setElementClasses(String elementClasses) {
		putValue("elementClasses", elementClasses);
	}

	public void setHeader(String header) {
		putHTMLValue("header", header);
	}

	public void setHeaderClasses(String headerClasses) {
		putValue("headerClasses", headerClasses);
	}

	public void setId(String id) {
		putValue("id", id);
	}

	public void setNamespace(String namespace) {
		putValue("namespace", namespace);
	}

	public void setVisible(Boolean visible) {
		putValue("visible", visible);
	}

}