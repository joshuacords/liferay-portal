/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.browser.web.internal.search;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.portal.kernel.dao.search.SearchContainer;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Brian Wing Shun Chan
 * @author Julio Camarero
 */
public class AssetBrowserSearch extends SearchContainer<AssetEntry> {

	public static final String EMPTY_RESULTS_MESSAGE = "there-are-no-results";

	public static List<String> headerNames = new ArrayList<String>() {
		{
			add("title");
			add("description");
			add("user-name");
			add("modified-date");
			add("scope");
		}
	};

	public AssetBrowserSearch(
		PortletRequest portletRequest, int delta, PortletURL iteratorURL) {

		super(
			portletRequest, new AssetBrowserDisplayTerms(portletRequest),
			new AssetBrowserSearchTerms(portletRequest), DEFAULT_CUR_PARAM,
			delta, iteratorURL, headerNames, EMPTY_RESULTS_MESSAGE);

		AssetBrowserDisplayTerms displayTerms =
			(AssetBrowserDisplayTerms)getDisplayTerms();

		iteratorURL.setParameter(
			AssetBrowserDisplayTerms.DESCRIPTION,
			displayTerms.getDescription());
		iteratorURL.setParameter(
			AssetBrowserDisplayTerms.GROUP_ID,
			String.valueOf(displayTerms.getGroupId()));
		iteratorURL.setParameter(
			AssetBrowserDisplayTerms.TITLE, displayTerms.getTitle());
		iteratorURL.setParameter(
			AssetBrowserDisplayTerms.USER_NAME, displayTerms.getUserName());
	}

	public AssetBrowserSearch(
		PortletRequest portletRequest, PortletURL iteratorURL) {

		this(portletRequest, DEFAULT_DELTA, iteratorURL);
	}

}