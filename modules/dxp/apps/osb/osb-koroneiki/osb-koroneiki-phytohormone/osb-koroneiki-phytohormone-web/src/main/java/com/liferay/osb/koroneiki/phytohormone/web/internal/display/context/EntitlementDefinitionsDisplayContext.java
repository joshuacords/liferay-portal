/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phytohormone.web.internal.display.context;

import com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition;
import com.liferay.osb.koroneiki.phytohormone.service.EntitlementDefinitionLocalServiceUtil;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Collections;
import java.util.List;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Amos Fong
 */
public class EntitlementDefinitionsDisplayContext
	extends BaseSearchDisplayContext {

	public EntitlementDefinitionsDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse,
		HttpServletRequest httpServletRequest) {

		super(renderRequest, renderResponse, httpServletRequest);
	}

	@Override
	protected SearchContainer createSearchContainer() throws Exception {
		SearchContainer searchContainer = new SearchContainer(
			renderRequest, getPortletURL(), Collections.emptyList(),
			"no-entitlement-definitions-were-found");

		long classNameId = 0;

		String tabs1 = ParamUtil.getString(renderRequest, "tabs1", "account");

		if (tabs1.equals("account")) {
			classNameId = PortalUtil.getClassNameId(Account.class);
		}
		else {
			classNameId = PortalUtil.getClassNameId(Contact.class);
		}

		String keywords = StringUtil.quote(getKeywords(), StringPool.PERCENT);

		List<EntitlementDefinition> entitlementDefinitions =
			EntitlementDefinitionLocalServiceUtil.search(
				classNameId, keywords, searchContainer.getStart(),
				searchContainer.getEnd());

		searchContainer.setResults(entitlementDefinitions);

		int total = EntitlementDefinitionLocalServiceUtil.searchCount(
			classNameId, keywords);

		searchContainer.setTotal(total);

		return searchContainer;
	}

}