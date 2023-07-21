/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.taglib.ui;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.SessionClicks;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @author Brian Wing Shun Chan
 */
public class ToggleValueTag extends TagSupport {

	public static void doTag(
			String id, String defaultValue, PageContext pageContext)
		throws Exception {

		HttpServletRequest httpServletRequest =
			(HttpServletRequest)pageContext.getRequest();

		String value = SessionClicks.get(
			httpServletRequest, id, StringPool.BLANK);

		if (value.equals(StringPool.BLANK)) {
			value = defaultValue;
		}

		JspWriter jspWriter = pageContext.getOut();

		jspWriter.write(HtmlUtil.escapeAttribute(value));
	}

	@Override
	public int doEndTag() throws JspException {
		try {
			doTag(_id, _defaultValue, pageContext);

			return EVAL_PAGE;
		}
		catch (Exception exception) {
			throw new JspException(exception);
		}
	}

	public void setDefaultValue(String defaultValue) {
		_defaultValue = defaultValue;
	}

	@Override
	public void setId(String id) {
		_id = id;
	}

	private String _defaultValue = "block";
	private String _id;

}