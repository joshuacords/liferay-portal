/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.taglib.aui;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.taglib.aui.base.BaseFieldsetGroupTag;

import javax.servlet.jsp.JspWriter;

/**
 * @author Eduardo Lundgren
 * @author Bruno Basto
 * @author Nathan Cavanaugh
 * @author Julio Camarero
 */
public class FieldsetGroupTag extends BaseFieldsetGroupTag {

	@Override
	protected String getEndPage() {
		if (Validator.isNotNull(getMarkupView())) {
			return "/html/taglib/aui/fieldset_group/" + getMarkupView() +
				"/end.jsp";
		}

		return "/html/taglib/aui/fieldset_group/end.jsp";
	}

	@Override
	protected String getStartPage() {
		if (Validator.isNotNull(getMarkupView())) {
			return "/html/taglib/aui/fieldset_group/" + getMarkupView() +
				"/start.jsp";
		}

		return "/html/taglib/aui/fieldset_group/start.jsp";
	}

	@Override
	protected int processEndTag() throws Exception {
		JspWriter jspWriter = pageContext.getOut();

		jspWriter.write("</div></div>");

		return EVAL_PAGE;
	}

}