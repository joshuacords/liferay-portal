/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.portlets.web.internal.display.context;

import com.liferay.layout.portlets.web.internal.constants.LayoutsPortletsPortletKeys;
import com.liferay.layout.portlets.web.internal.search.PortletSearch;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.PortletCategory;
import com.liferay.portal.kernel.portlet.SearchDisplayStyleUtil;
import com.liferay.portal.kernel.service.PortletLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.util.WebAppPool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Jorge Ferrer
 */
public class LayoutPortletsDisplayContext {

	public LayoutPortletsDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse,
		HttpServletRequest httpServletRequest) {

		_renderRequest = renderRequest;
		_renderResponse = renderResponse;

		_httpServletRequest = httpServletRequest;

		ThemeDisplay themeDisplay =
			(ThemeDisplay)_httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		initPortlets(themeDisplay.getCompanyId());
	}

	public String getDisplayStyle() {
		if (Validator.isNotNull(_displayStyle)) {
			return _displayStyle;
		}

		_displayStyle = SearchDisplayStyleUtil.getDisplayStyle(
			_httpServletRequest, LayoutsPortletsPortletKeys.LAYOUT_PORTLETS,
			"list");

		return _displayStyle;
	}

	public String getOrderByCol() {
		if (Validator.isNotNull(_orderByCol)) {
			return _orderByCol;
		}

		_orderByCol = ParamUtil.getString(
			_httpServletRequest, "orderByCol", "name");

		return _orderByCol;
	}

	public String getOrderByType() {
		if (Validator.isNotNull(_orderByType)) {
			return _orderByType;
		}

		_orderByType = ParamUtil.getString(
			_httpServletRequest, "orderByType", "asc");

		return _orderByType;
	}

	public String getPortletCategoryLabels(String portletId) {
		String[] categories = _layoutPortletCategories.get(portletId);

		Stream<String> stream = Arrays.stream(categories);

		return stream.map(
			category -> LanguageUtil.get(_httpServletRequest, category)
		).collect(
			Collectors.joining(StringPool.COMMA_AND_SPACE)
		);
	}

	public PortletURL getPortletURL() {
		PortletURL portletURL = _renderResponse.createRenderURL();

		portletURL.setParameter("displayStyle", getDisplayStyle());

		return portletURL;
	}

	public SearchContainer getSearchContainer() {
		SearchContainer searchContainer = new PortletSearch(
			_renderRequest, getPortletURL());

		searchContainer.setEmptyResultsMessage("there-are-no-widgets");
		searchContainer.setId("layoutPortlets");
		searchContainer.setOrderByCol(getOrderByCol());
		searchContainer.setOrderByType(getOrderByType());
		searchContainer.setTotal(_layoutPortlets.size());

		List results = ListUtil.sort(
			_layoutPortlets, searchContainer.getOrderByComparator());

		results = ListUtil.subList(
			results, searchContainer.getStart(), searchContainer.getEnd());

		searchContainer.setResults(results);

		return searchContainer;
	}

	protected void initPortlets(long companyId) {
		PortletCategory portletCategory = (PortletCategory)WebAppPool.get(
			companyId, WebKeys.PORTLET_CATEGORY);

		Collection<PortletCategory> portletCategories =
			portletCategory.getCategories();

		for (PortletCategory curPortletCategory : portletCategories) {
			if (curPortletCategory.isHidden()) {
				continue;
			}

			for (String portletId : curPortletCategory.getPortletIds()) {
				Portlet portlet = PortletLocalServiceUtil.getPortletById(
					companyId, portletId);

				if (portlet.isSystem() || !portlet.isInclude()) {
					continue;
				}

				if (portlet != null) {
					_layoutPortlets.add(portlet);
				}

				String[] categories = _layoutPortletCategories.get(portletId);

				String curPortletCategoryName = curPortletCategory.getName();

				if (categories == null) {
					_layoutPortletCategories.put(
						portletId, new String[] {curPortletCategoryName});
				}
				else {
					_layoutPortletCategories.put(
						portletId,
						ArrayUtil.append(categories, curPortletCategoryName));
				}
			}
		}
	}

	private String _displayStyle;
	private final HttpServletRequest _httpServletRequest;
	private final Map<String, String[]> _layoutPortletCategories =
		new HashMap<>();
	private final ArrayList<Portlet> _layoutPortlets = new ArrayList<>();
	private String _orderByCol;
	private String _orderByType;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;

}