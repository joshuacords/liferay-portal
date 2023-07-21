/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.display.context;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.osb.provisioning.search.FilterQuery;
import com.liferay.osb.provisioning.web.internal.dao.search.AssignTeamContactRowChecker;
import com.liferay.portal.kernel.dao.search.RowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.util.Collections;
import java.util.List;

import javax.portlet.PortletURL;

/**
 * @author Amos Fong
 */
public class AssignTeamContactsDisplayContext extends ViewTeamDisplayContext {

	public AssignTeamContactsDisplayContext() {
	}

	public String getClearResultsURL() {
		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter(
			"mvcRenderCommandName", "/accounts/assign_team_contacts");
		portletURL.setParameter("teamKey", team.getKey());

		return portletURL.toString();
	}

	public SearchContainer getSearchContainer() throws Exception {
		String keywords = ParamUtil.getString(renderRequest, "keywords");

		SearchContainer searchContainer = new SearchContainer(
			renderRequest, currentURLObj, Collections.emptyList(),
			"no-contacts-were-found");

		FilterQuery filterQuery = new FilterQuery();

		String[] contactRoleKeys = ParamUtil.getStringValues(
			renderRequest, "contactRoleKeys");

		if (!ArrayUtil.isEmpty(contactRoleKeys)) {
			filterQuery.addLambdaEquals(
				true, "accountKeysContactRoleKeys", contactRoleKeys);
		}

		filterQuery.addLambdaEquals(
			true, "customerAccountKeys", account.getKey());

		List<Contact> contacts = contactWebService.search(
			keywords, filterQuery, searchContainer.getCur(),
			searchContainer.getEnd() - searchContainer.getStart(), "firstName");

		searchContainer.setResults(
			TransformUtil.transform(
				contacts,
				contact -> {
					List<ContactRole> contactRoles =
						contactRoleWebService.getAccountCustomerContactRoles(
							account.getKey(), contact.getEmailAddress(), 1,
							1000);

					return new ContactDisplay(
						httpServletRequest, contact, contactRoles);
				}));

		searchContainer.setRowChecker(_getRowChecker());

		int count = (int)contactWebService.searchCount(keywords, filterQuery);

		searchContainer.setTotal(count);

		return searchContainer;
	}

	private RowChecker _getRowChecker() throws Exception {
		FilterQuery filterQuery = new FilterQuery();

		filterQuery.addLambdaEquals(true, "teamKeys", team.getKey());

		List<Contact> contacts = contactWebService.search(
			StringPool.BLANK, filterQuery, 1, 1000, StringPool.BLANK);

		return new AssignTeamContactRowChecker(
			renderResponse, ListUtil.toList(contacts, Contact::getKey));
	}

}