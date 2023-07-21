/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.web.internal.display.context;

import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.osb.koroneiki.taproot.service.ContactLocalServiceUtil;
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
public class ContactsDisplayContext extends BaseSearchDisplayContext {

	public ContactsDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse,
		HttpServletRequest httpServletRequest) {

		super(renderRequest, renderResponse, httpServletRequest);
	}

	@Override
	protected SearchContainer createSearchContainer() throws Exception {
		SearchContainer searchContainer = new SearchContainer(
			renderRequest, getPortletURL(), Collections.emptyList(),
			"no-contacts-were-found");

		Sort sort = SortFactoryUtil.getSort(
			Contact.class, Sort.STRING_TYPE, getOrderByCol(), getOrderByType());

		Hits hits = ContactLocalServiceUtil.search(
			themeDisplay.getCompanyId(), getKeywords(),
			searchContainer.getStart(), searchContainer.getEnd(), sort);

		List<Contact> results = new ArrayList<>();

		for (Document document : hits.getDocs()) {
			long contactId = GetterUtil.getLong(
				document.get(Field.ENTRY_CLASS_PK));

			results.add(ContactLocalServiceUtil.getContact(contactId));
		}

		searchContainer.setResults(results);
		searchContainer.setTotal(hits.getLength());

		return searchContainer;
	}

}