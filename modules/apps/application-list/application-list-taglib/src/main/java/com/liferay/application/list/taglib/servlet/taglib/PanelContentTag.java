/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.application.list.taglib.servlet.taglib;

import com.liferay.application.list.PanelCategory;
import com.liferay.application.list.constants.ApplicationListWebKeys;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.taglib.servlet.PipingServletResponse;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

/**
 * @author Eudaldo Alonso
 */
public class PanelContentTag extends BasePanelTag {

	@Override
	public int doEndTag() throws JspException {
		request.setAttribute(
			ApplicationListWebKeys.PANEL_CATEGORY, _panelCategory);

		try {
			boolean include = _panelCategory.include(
				request,
				PipingServletResponse.createPipingServletResponse(pageContext));

			if (include) {
				doClearTag();

				return EVAL_PAGE;
			}
		}
		catch (IOException ioException) {
			_log.error("Unable to include panel category", ioException);
		}

		return super.doEndTag();
	}

	@Override
	public int doStartTag() throws JspException {
		return EVAL_BODY_INCLUDE;
	}

	public PanelCategory getPanelCategory() {
		return _panelCategory;
	}

	public void setPanelCategory(PanelCategory panelCategory) {
		_panelCategory = panelCategory;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_panelCategory = null;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest httpServletRequest) {
		httpServletRequest.setAttribute(
			"liferay-application-list:panel-content:panelCategory",
			_panelCategory);
	}

	private static final String _PAGE = "/panel_content/page.jsp";

	private static final Log _log = LogFactoryUtil.getLog(
		PanelContentTag.class);

	private PanelCategory _panelCategory;

}