/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.web.internal.portlet.action;

import com.liferay.journal.constants.JournalPortletKeys;
import com.liferay.journal.util.JournalHelper;
import com.liferay.portal.kernel.diff.CompareVersionsException;
import com.liferay.portal.kernel.portlet.PortletRequestModel;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.PortletContext;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.PortletSession;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + JournalPortletKeys.JOURNAL,
		"mvc.command.name=/journal/compare_versions"
	},
	service = MVCResourceCommand.class
)
public class CompareVersionsMVCResourceCommand extends BaseMVCResourceCommand {

	@Override
	protected void doServeResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long groupId = ParamUtil.getLong(resourceRequest, "groupId");
		String articleId = ParamUtil.getString(resourceRequest, "articleId");
		double sourceVersion = ParamUtil.getDouble(
			resourceRequest, "filterSourceVersion");
		double targetVersion = ParamUtil.getDouble(
			resourceRequest, "filterTargetVersion");
		String languageId = ParamUtil.getString(resourceRequest, "languageId");

		String diffHtmlResults = null;

		try {
			diffHtmlResults = _journalHelper.diffHtml(
				groupId, articleId, sourceVersion, targetVersion, languageId,
				new PortletRequestModel(resourceRequest, resourceResponse),
				themeDisplay);
		}
		catch (CompareVersionsException compareVersionsException) {
			resourceRequest.setAttribute(
				WebKeys.DIFF_VERSION, compareVersionsException.getVersion());
		}
		catch (Exception exception) {
			HttpServletRequest httpServletRequest =
				_portal.getHttpServletRequest(resourceRequest);

			HttpServletResponse httpServletResponse =
				_portal.getHttpServletResponse(resourceResponse);

			try {
				_portal.sendError(
					exception, httpServletRequest, httpServletResponse);
			}
			catch (ServletException servletException) {
			}
		}

		resourceRequest.setAttribute(
			WebKeys.DIFF_HTML_RESULTS, diffHtmlResults);

		PortletSession portletSession = resourceRequest.getPortletSession();

		PortletContext portletContext = portletSession.getPortletContext();

		PortletRequestDispatcher portletRequestDispatcher =
			portletContext.getRequestDispatcher(
				"/compare_versions_diff_html.jsp");

		portletRequestDispatcher.include(resourceRequest, resourceResponse);
	}

	@Reference
	private JournalHelper _journalHelper;

	@Reference
	private Portal _portal;

}