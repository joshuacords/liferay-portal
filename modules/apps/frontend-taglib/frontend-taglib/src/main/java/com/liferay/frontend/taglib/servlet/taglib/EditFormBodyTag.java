/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.taglib.servlet.taglib;

import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.taglib.util.IncludeTag;

import javax.servlet.jsp.JspWriter;

/**
 * @author Eudaldo Alonso
 */
public class EditFormBodyTag extends IncludeTag {

	@Override
	protected int processEndTag() throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		JspWriter jspWriter = pageContext.getOut();

		jspWriter.write("</div>");

		if (!themeDisplay.isStatePopUp()) {
			return EVAL_BODY_INCLUDE;
		}

		jspWriter.write("</div>");

		return EVAL_BODY_INCLUDE;
	}

	@Override
	protected int processStartTag() throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		JspWriter jspWriter = pageContext.getOut();

		jspWriter.write("<div class=\"lfr-form-content\">");

		if (!themeDisplay.isStatePopUp()) {
			return EVAL_BODY_INCLUDE;
		}

		EditFormTag editFormTag = (EditFormTag)findAncestorWithClass(
			this, EditFormTag.class);

		String cssClass = "sheet";

		if ((editFormTag != null) && !editFormTag.isFluid()) {
			cssClass = "sheet sheet-lg";
		}

		jspWriter.write("<div class=\"" + cssClass + "\">");

		return EVAL_BODY_INCLUDE;
	}

}