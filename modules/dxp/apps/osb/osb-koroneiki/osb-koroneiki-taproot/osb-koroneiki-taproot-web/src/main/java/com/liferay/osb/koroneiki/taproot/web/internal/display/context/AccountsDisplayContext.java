/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.web.internal.display.context;

import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalServiceUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Amos Fong
 */
public class AccountsDisplayContext extends BaseSearchDisplayContext {

	public AccountsDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse,
		HttpServletRequest httpServletRequest) {

		super(renderRequest, renderResponse, httpServletRequest);
	}

	@Override
	protected SearchContainer createSearchContainer() throws Exception {
		SearchContainer searchContainer = new SearchContainer(
			renderRequest, getPortletURL(), Collections.emptyList(),
			"no-accounts-were-found");

		Sort sort = SortFactoryUtil.getSort(
			Account.class, Sort.STRING_TYPE, getOrderByCol(), getOrderByType());

		Hits hits = AccountLocalServiceUtil.search(
			themeDisplay.getCompanyId(), getKeywords(),
			searchContainer.getStart(), searchContainer.getEnd(), sort);

		List<Account> results = new ArrayList<>();

		for (Document document : hits.getDocs()) {
			long accountId = GetterUtil.getLong(
				document.get(Field.ENTRY_CLASS_PK));

			results.add(AccountLocalServiceUtil.getAccount(accountId));
		}

		searchContainer.setResults(results);
		searchContainer.setTotal(hits.getLength());

		return searchContainer;
	}

}