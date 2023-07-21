/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.taglib.ui;

import com.liferay.taglib.util.IncludeTag;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Julio Camarero
 */
public class DiffHtmlTag extends IncludeTag {

	public String getDiffHtmlResults() {
		return _diffHtmlResults;
	}

	public String getInfoMessage() {
		return _infoMessage;
	}

	public void setDiffHtmlResults(String diffHtmlResults) {
		_diffHtmlResults = diffHtmlResults;
	}

	public void setInfoMessage(String infoMessage) {
		_infoMessage = infoMessage;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_diffHtmlResults = null;
		_infoMessage = null;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest httpServletRequest) {
		httpServletRequest.setAttribute(
			"liferay-ui:diff-html:diffHtmlResults", _diffHtmlResults);
		httpServletRequest.setAttribute(
			"liferay-ui:diff-html:infoMessage", _infoMessage);
	}

	private static final String _PAGE = "/html/taglib/ui/diff_html/page.jsp";

	private String _diffHtmlResults;
	private String _infoMessage;

}