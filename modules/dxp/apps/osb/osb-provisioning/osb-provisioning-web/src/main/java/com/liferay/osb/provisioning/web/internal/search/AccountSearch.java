/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.search;

import com.liferay.osb.provisioning.web.internal.display.context.AccountDisplay;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.util.ArrayUtil;

import java.util.Collections;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Amos Fong
 */
public class AccountSearch extends SearchContainer<AccountDisplay> {

	public static final String EMPTY_RESULTS_MESSAGE = "no-accounts-were-found";

	public AccountSearch(
		PortletRequest portletRequest, PortletURL iteratorURL) {

		super(
			portletRequest, new AccountDisplayTerms(portletRequest),
			new AccountSearchTerms(portletRequest), DEFAULT_CUR_PARAM,
			DEFAULT_DELTA, iteratorURL, Collections.emptyList(),
			EMPTY_RESULTS_MESSAGE);

		AccountDisplayTerms displayTerms =
			(AccountDisplayTerms)getDisplayTerms();

		iteratorURL.setParameter(
			AccountDisplayTerms.ACTIVE_SLAS, displayTerms.getActiveSLAs());
		iteratorURL.setParameter(
			AccountDisplayTerms.CODE, displayTerms.getCode());
		iteratorURL.setParameter(
			AccountDisplayTerms.COUNTRY_NAME, displayTerms.getCountryName());
		iteratorURL.setParameter(
			AccountDisplayTerms.CREATE_DATE_GT, displayTerms.getCreateDateGT());
		iteratorURL.setParameter(
			AccountDisplayTerms.CREATE_DATE_LT, displayTerms.getCreateDateLT());
		iteratorURL.setParameter(
			AccountDisplayTerms.CREATED_BY_EMAIL_ADDRESS,
			displayTerms.getCreatedByEmailAddress());
		iteratorURL.setParameter(
			AccountDisplayTerms.EXTERNAL_ACCOUNT_KEY,
			displayTerms.getExternalAccountKey());
		iteratorURL.setParameter(
			AccountDisplayTerms.FLS_TEAM_KEY, displayTerms.getFLSTeamKey());
		iteratorURL.setParameter(
			AccountDisplayTerms.INTERNALS,
			ArrayUtil.toStringArray(displayTerms.internals));
		iteratorURL.setParameter(
			AccountDisplayTerms.MODIFIED_DATE_GT,
			displayTerms.getModifiedDateGT());
		iteratorURL.setParameter(
			AccountDisplayTerms.MODIFIED_DATE_LT,
			displayTerms.getModifiedDateLT());
		iteratorURL.setParameter(
			AccountDisplayTerms.NAME, displayTerms.getName());
		iteratorURL.setParameter(
			AccountDisplayTerms.NOTES, displayTerms.getNotes());
		iteratorURL.setParameter(
			AccountDisplayTerms.PARENT_ACCOUNT_KEY,
			displayTerms.getParentAccountKey());
		iteratorURL.setParameter(
			AccountDisplayTerms.PARTNERS,
			ArrayUtil.toStringArray(displayTerms.partners));
		iteratorURL.setParameter(
			AccountDisplayTerms.PARTNER_TEAM_KEY,
			displayTerms.getPartnerTeamKey());
		iteratorURL.setParameter(
			AccountDisplayTerms.PROVIDES_FLS,
			ArrayUtil.toStringArray(displayTerms.providesFLS));
		iteratorURL.setParameter(
			AccountDisplayTerms.RECEIVES_FLS,
			ArrayUtil.toStringArray(displayTerms.receivesFLS));
		iteratorURL.setParameter(
			AccountDisplayTerms.REGIONS, displayTerms.getRegions());
		iteratorURL.setParameter(
			AccountDisplayTerms.SALES_INFO, displayTerms.getSalesInfo());
		iteratorURL.setParameter(
			AccountDisplayTerms.SUBSCRIPTION_STATES,
			displayTerms.getSubscriptionStates());
		iteratorURL.setParameter(
			AccountDisplayTerms.TIERS, displayTerms.getTiers());
		iteratorURL.setParameter(
			AccountDisplayTerms.WORKER_CONTACT_EMAIL_ADDRESS,
			displayTerms.getWorkerContactEmailAddress());
	}

}