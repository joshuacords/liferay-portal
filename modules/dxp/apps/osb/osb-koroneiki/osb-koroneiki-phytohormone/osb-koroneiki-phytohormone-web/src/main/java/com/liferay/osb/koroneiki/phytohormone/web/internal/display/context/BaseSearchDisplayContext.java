/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phytohormone.web.internal.display.context;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Amos Fong
 */
public abstract class BaseSearchDisplayContext {

	public BaseSearchDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse,
		HttpServletRequest httpServletRequest) {

		this.renderRequest = renderRequest;
		this.renderResponse = renderResponse;

		currentURLObj = PortletURLUtil.getCurrent(
			renderRequest, renderResponse);

		request = httpServletRequest;

		themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);
	}

	public String getKeywords() {
		if (Validator.isNull(_keywords)) {
			_keywords = ParamUtil.getString(request, "keywords");
		}

		return _keywords;
	}

	public String getOrderByCol() {
		if (Validator.isNull(_orderByCol)) {
			_orderByCol = ParamUtil.getString(
				request, "orderByCol", getDefaultOrderByCol());
		}

		return _orderByCol;
	}

	public String getOrderByType() {
		if (Validator.isNull(_orderByType)) {
			_orderByType = ParamUtil.getString(request, "orderByType", "asc");
		}

		return _orderByType;
	}

	public PortletURL getPortletURL() {
		PortletURL portletURL = null;

		try {
			portletURL = PortletURLUtil.clone(currentURLObj, renderResponse);
		}
		catch (PortletException portletException) {
			if (_log.isWarnEnabled()) {
				_log.warn(portletException, portletException);
			}

			portletURL = renderResponse.createRenderURL();
		}

		if (Validator.isNotNull(getKeywords())) {
			portletURL.setParameter("keywords", getKeywords());
		}

		portletURL.setParameter("orderByCol", getOrderByCol());
		portletURL.setParameter("orderByType", getOrderByType());

		return portletURL;
	}

	public SearchContainer getSearchContainer() throws Exception {
		if (searchContainer == null) {
			searchContainer = createSearchContainer();

			searchContainer.setOrderByCol(getOrderByCol());
			searchContainer.setOrderByType(getOrderByType());
		}

		return searchContainer;
	}

	protected abstract SearchContainer createSearchContainer() throws Exception;

	protected String getDefaultOrderByCol() {
		if (Validator.isNull(_keywords)) {
			return "name";
		}

		return StringPool.BLANK;
	}

	protected final PortletURL currentURLObj;
	protected final RenderRequest renderRequest;
	protected final RenderResponse renderResponse;
	protected final HttpServletRequest request;
	protected SearchContainer searchContainer;
	protected final ThemeDisplay themeDisplay;

	private static final Log _log = LogFactoryUtil.getLog(
		BaseSearchDisplayContext.class);

	private String _keywords;
	private String _orderByCol;
	private String _orderByType;

}