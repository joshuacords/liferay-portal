/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wiki.engine.input.editor.common;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.io.unsync.UnsyncStringWriter;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.taglib.servlet.PipingServletResponse;
import com.liferay.wiki.engine.BaseWikiEngine;
import com.liferay.wiki.engine.input.editor.common.internal.util.WikiEngineInputEditorCommonComponentProvider;
import com.liferay.wiki.model.WikiNode;
import com.liferay.wiki.model.WikiPage;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

/**
 * @author     Iván Zaera
 * @deprecated As of Judson (7.1.x), replaced by {@link BaseWikiEngine}
 */
@Deprecated
public abstract class BaseInputEditorWikiEngine extends BaseWikiEngine {

	public static BaseInputEditorWikiEngine getBaseInputEditorWikiEngine(
		ServletRequest servletRequest) {

		return (BaseInputEditorWikiEngine)servletRequest.getAttribute(
			_BASE_INPUT_EDITOR_WIKI_ENGINE);
	}

	@Override
	public abstract String getEditorName();

	@Override
	public String getHelpPageHTML(PageContext pageContext)
		throws IOException, ServletException {

		if (!isHelpPageDefined()) {
			return StringPool.BLANK;
		}

		HttpServletResponse httpServletResponse =
			(HttpServletResponse)pageContext.getResponse();

		UnsyncStringWriter unsyncStringWriter = new UnsyncStringWriter();

		PipingServletResponse pipingServletResponse = new PipingServletResponse(
			httpServletResponse, unsyncStringWriter);

		ServletContext servletContext = getHelpPageServletContext();

		RequestDispatcher requestDispatcher =
			servletContext.getRequestDispatcher(getHelpPageJSP());

		requestDispatcher.include(
			pageContext.getRequest(), pipingServletResponse);

		StringBundler sb = unsyncStringWriter.getStringBundler();

		return sb.toString();
	}

	@Override
	public String getHelpPageTitle(HttpServletRequest httpServletRequest) {
		return LanguageUtil.format(
			httpServletRequest, "x-syntax-help",
			getFormatLabel(httpServletRequest.getLocale()), false);
	}

	@Override
	public abstract String getHelpURL();

	@Override
	public boolean isHelpPageDefined() {
		if ((getHelpPageServletContext() == null) ||
			Validator.isNull(getHelpPageJSP())) {

			return false;
		}

		return true;
	}

	@Override
	public void renderEditPage(
			ServletRequest servletRequest, ServletResponse servletResponse,
			WikiNode node, WikiPage page)
		throws IOException, ServletException {

		servletRequest.setAttribute(_BASE_INPUT_EDITOR_WIKI_ENGINE, this);

		super.renderEditPage(servletRequest, servletResponse, node, page);
	}

	@Override
	protected ServletContext getEditPageServletContext() {
		WikiEngineInputEditorCommonComponentProvider
			wikiEngineInputEditorCommonComponentProvider =
				WikiEngineInputEditorCommonComponentProvider.
					getWikiEngineInputEditorCommonComponentProvider();

		return wikiEngineInputEditorCommonComponentProvider.getServletContext();
	}

	@Override
	protected String getHelpPageJSP() {
		return "/help_page.jsp";
	}

	@Override
	protected abstract ServletContext getHelpPageServletContext();

	private static final String _BASE_INPUT_EDITOR_WIKI_ENGINE =
		BaseInputEditorWikiEngine.class.getName() +
			"#BASE_INPUT_EDITOR_WIKI_ENGINE";

}