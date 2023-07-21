/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.web.internal.display.context;

import com.liferay.frontend.taglib.clay.servlet.taglib.display.context.SearchContainerManagementToolbarDisplayContext;
import com.liferay.journal.model.JournalArticle;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Pavel Savinov
 */
public class JournalSelectArticleTranslationsManagementToolbarDisplayContext
	extends SearchContainerManagementToolbarDisplayContext {

	public JournalSelectArticleTranslationsManagementToolbarDisplayContext(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse,
			HttpServletRequest httpServletRequest,
			JournalDisplayContext journalDisplayContext)
		throws Exception {

		super(
			liferayPortletRequest, liferayPortletResponse, httpServletRequest,
			journalDisplayContext.getArticleTranslationsSearchContainer());

		_journalDisplayContext = journalDisplayContext;
	}

	@Override
	public String getClearResultsURL() {
		PortletURL clearResultsURL = getPortletURL();

		clearResultsURL.setParameter("keywords", StringPool.BLANK);

		return clearResultsURL.toString();
	}

	@Override
	public String getSearchActionURL() {
		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		portletURL.setParameter("mvcPath", "/select_article_translations.jsp");

		try {
			JournalArticle article = _journalDisplayContext.getArticle();

			portletURL.setParameter(
				"groupId", String.valueOf(article.getGroupId()));
			portletURL.setParameter("articleId", article.getArticleId());
			portletURL.setParameter(
				"status", String.valueOf(article.getStatus()));
		}
		catch (PortalException portalException) {
			_log.error("Unable to get the article", portalException);
		}

		return portletURL.toString();
	}

	@Override
	public String getSearchContainerId() {
		return "articleTranslations";
	}

	@Override
	protected String[] getDisplayViews() {
		return new String[] {"list"};
	}

	@Override
	protected String[] getNavigationKeys() {
		return new String[] {"all"};
	}

	private static final Log _log = LogFactoryUtil.getLog(
		JournalSelectArticleTranslationsManagementToolbarDisplayContext.class);

	private final JournalDisplayContext _journalDisplayContext;

}