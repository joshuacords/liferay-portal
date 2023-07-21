/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.taglib.servlet.taglib;

import com.liferay.frontend.taglib.internal.servlet.ServletContextUtil;
import com.liferay.portal.kernel.diff.DiffVersionsInfo;
import com.liferay.taglib.util.IncludeTag;

import java.util.Locale;
import java.util.Set;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

/**
 * @author     Eudaldo Alonso
 * @deprecated As of Judson (7.1.x), in favor of
 *             com.liferay.frontend.taglib.servlet.taglib.soy.DiffVersionComparatorTag
 */
@Deprecated
public class DiffVersionComparatorTag extends IncludeTag {

	@Override
	public int doStartTag() {
		return EVAL_BODY_INCLUDE;
	}

	public void setAvailableLocales(Set<Locale> availableLocales) {
		_availableLocales = availableLocales;
	}

	public void setDiffHtmlResults(String diffHtmlResults) {
		_diffHtmlResults = diffHtmlResults;
	}

	public void setDiffVersionsInfo(DiffVersionsInfo diffVersionsInfo) {
		_diffVersionsInfo = diffVersionsInfo;
	}

	public void setLanguageId(String languageId) {
		_languageId = languageId;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		servletContext = ServletContextUtil.getServletContext();
	}

	public void setPortletURL(PortletURL portletURL) {
		_portletURL = portletURL;
	}

	public void setResourceURL(PortletURL resourceURL) {
		_resourceURL = resourceURL;
	}

	public void setSourceVersion(double sourceVersion) {
		_sourceVersion = sourceVersion;
	}

	public void setTargetVersion(double targetVersion) {
		_targetVersion = targetVersion;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_availableLocales = null;
		_diffHtmlResults = null;
		_diffVersionsInfo = null;
		_languageId = null;
		_portletURL = null;
		_resourceURL = null;
		_sourceVersion = 0;
		_targetVersion = 0;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected boolean isCleanUpSetAttributes() {
		return _CLEAN_UP_SET_ATTRIBUTES;
	}

	@Override
	protected void setAttributes(HttpServletRequest httpServletRequest) {
		httpServletRequest.setAttribute(
			"liferay-frontend:diff-version-comparator:availableLocales",
			_availableLocales);
		httpServletRequest.setAttribute(
			"liferay-frontend:diff-version-comparator:diffHtmlResults",
			_diffHtmlResults);
		httpServletRequest.setAttribute(
			"liferay-frontend:diff-version-comparator:diffVersionsInfo",
			_diffVersionsInfo);
		httpServletRequest.setAttribute(
			"liferay-frontend:diff-version-comparator:languageId", _languageId);
		httpServletRequest.setAttribute(
			"liferay-frontend:diff-version-comparator:portletURL", _portletURL);
		httpServletRequest.setAttribute(
			"liferay-frontend:diff-version-comparator:resourceURL",
			_resourceURL);
		httpServletRequest.setAttribute(
			"liferay-frontend:diff-version-comparator:sourceVersion",
			_sourceVersion);
		httpServletRequest.setAttribute(
			"liferay-frontend:diff-version-comparator:targetVersion",
			_targetVersion);
	}

	private static final boolean _CLEAN_UP_SET_ATTRIBUTES = true;

	private static final String _PAGE = "/diff_version_comparator/page.jsp";

	private Set<Locale> _availableLocales;
	private String _diffHtmlResults;
	private DiffVersionsInfo _diffVersionsInfo;
	private String _languageId;
	private PortletURL _portletURL;
	private PortletURL _resourceURL;
	private double _sourceVersion;
	private double _targetVersion;

}