/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.staging.taglib.servlet.taglib.base;

import com.liferay.frontend.taglib.util.TagAccessor;
import com.liferay.frontend.taglib.util.TagResourceHandler;
import com.liferay.petra.string.StringBundler;
import com.liferay.taglib.util.IncludeTag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

/**
 * @author Péter Borkuti
 */
public abstract class BaseCssTag extends IncludeTag {

	@Override
	public int doStartTag() throws JspException {
		_outputStylesheetLink();

		return super.doStartTag();
	}

	public abstract String getTagNameForCssPath();

	private PageContext _getPageContext() {
		return pageContext;
	}

	private void _outputStylesheetLink() {
		StringBundler sb = new StringBundler(2);

		sb.append(getTagNameForCssPath());
		sb.append("/css/main.css");

		_tagResourceHandler.outputBundleStyleSheet(sb.toString());
	}

	private final TagResourceHandler _tagResourceHandler =
		new TagResourceHandler(
			BaseCssTag.class,
			new TagAccessor() {

				@Override
				public PageContext getPageContext() {
					return BaseCssTag.this._getPageContext();
				}

				@Override
				public HttpServletRequest getRequest() {
					return BaseCssTag.this.getRequest();
				}

			});

}