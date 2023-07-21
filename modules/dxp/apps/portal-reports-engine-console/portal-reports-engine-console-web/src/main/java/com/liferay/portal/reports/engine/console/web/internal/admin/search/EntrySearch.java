/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.reports.engine.console.web.internal.admin.search;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.reports.engine.console.constants.ReportsEngineConsolePortletKeys;
import com.liferay.portal.reports.engine.console.model.Entry;
import com.liferay.portal.reports.engine.console.util.comparator.EntryCreateDateComparator;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Rafael Praxedes
 */
public class EntrySearch extends SearchContainer<Entry> {

	public static final String EMPTY_RESULTS_MESSAGE = "there-are-no-reports";

	public static List<String> headerNames = new ArrayList<String>() {
		{
			add("definition-name");
			add("requested-by");
			add("create-date");
		}
	};

	public EntrySearch(PortletRequest portletRequest, PortletURL iteratorURL) {
		super(
			portletRequest, new EntryDisplayTerms(portletRequest),
			new EntrySearchTerms(portletRequest), DEFAULT_CUR_PARAM,
			DEFAULT_DELTA, iteratorURL, headerNames, EMPTY_RESULTS_MESSAGE);

		EntryDisplayTerms entryDisplayTerms =
			(EntryDisplayTerms)getDisplayTerms();

		iteratorURL.setParameter(
			EntryDisplayTerms.DEFINITION_NAME,
			entryDisplayTerms.getDefinitionName());
		iteratorURL.setParameter(
			EntryDisplayTerms.END_DATE_DAY,
			String.valueOf(entryDisplayTerms.getEndDateDay()));
		iteratorURL.setParameter(
			EntryDisplayTerms.END_DATE_MONTH,
			String.valueOf(entryDisplayTerms.getEndDateMonth()));
		iteratorURL.setParameter(
			EntryDisplayTerms.END_DATE_YEAR,
			String.valueOf(entryDisplayTerms.getEndDateYear()));
		iteratorURL.setParameter(
			EntryDisplayTerms.START_DATE_DAY,
			String.valueOf(entryDisplayTerms.getStartDateDay()));
		iteratorURL.setParameter(
			EntryDisplayTerms.START_DATE_MONTH,
			String.valueOf(entryDisplayTerms.getStartDateMonth()));
		iteratorURL.setParameter(
			EntryDisplayTerms.START_DATE_YEAR,
			String.valueOf(entryDisplayTerms.getStartDateYear()));
		iteratorURL.setParameter(
			EntryDisplayTerms.USERNAME, entryDisplayTerms.getUserName());

		PortalPreferences preferences =
			PortletPreferencesFactoryUtil.getPortalPreferences(portletRequest);

		String orderByCol = ParamUtil.getString(portletRequest, "orderByCol");

		if (Validator.isNotNull(orderByCol)) {
			preferences.setValue(
				ReportsEngineConsolePortletKeys.REPORTS_ADMIN, "order-by-col",
				orderByCol);
		}
		else {
			orderByCol = preferences.getValue(
				ReportsEngineConsolePortletKeys.REPORTS_ADMIN, "order-by-col",
				"create-date");
		}

		String orderByType = ParamUtil.getString(portletRequest, "orderByType");

		if (Validator.isNotNull(orderByType)) {
			preferences.setValue(
				ReportsEngineConsolePortletKeys.REPORTS_ADMIN, "order-by-type",
				orderByType);
		}
		else {
			orderByType = preferences.getValue(
				ReportsEngineConsolePortletKeys.REPORTS_ADMIN, "order-by-type",
				"asc");
		}

		setOrderByCol(orderByCol);

		OrderByComparator<Entry> orderByComparator = getEntryOrderByComparator(
			orderByCol, orderByType);

		setOrderByComparator(orderByComparator);

		setOrderByType(orderByType);
	}

	protected OrderByComparator<Entry> getEntryOrderByComparator(
		String orderByCol, String orderByType) {

		boolean orderByAsc = false;

		if (orderByType.equals("asc")) {
			orderByAsc = true;
		}

		return new EntryCreateDateComparator(orderByAsc);
	}

}