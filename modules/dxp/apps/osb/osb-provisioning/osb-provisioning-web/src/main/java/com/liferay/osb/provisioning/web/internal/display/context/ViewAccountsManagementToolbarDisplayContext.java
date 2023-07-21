/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.display.context;

import com.liferay.frontend.taglib.clay.servlet.taglib.display.context.SearchContainerManagementToolbarDisplayContext;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.LabelItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.LabelItemList;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Team;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.TeamWebService;
import com.liferay.osb.provisioning.web.internal.search.AccountDisplayTerms;
import com.liferay.osb.provisioning.web.internal.search.DisplayTerm;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.List;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Kyle Bischof
 */
public class ViewAccountsManagementToolbarDisplayContext
	extends SearchContainerManagementToolbarDisplayContext {

	public ViewAccountsManagementToolbarDisplayContext(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse,
		HttpServletRequest httpServletRequest, SearchContainer searchContainer,
		AccountWebService accountWebService, TeamWebService teamWebService) {

		super(
			liferayPortletRequest, liferayPortletResponse, httpServletRequest,
			searchContainer);

		_accountWebService = accountWebService;
		_teamWebService = teamWebService;
	}

	@Override
	public String getClearResultsURL() {
		PortletURL clearResultsURL = liferayPortletResponse.createRenderURL();

		AccountDisplayTerms accountDisplayTerms =
			(AccountDisplayTerms)searchContainer.getDisplayTerms();

		if (!accountDisplayTerms.isAdvancedSearch()) {
			clearResultsURL.setParameter(
				"accountSearchKeywords", accountDisplayTerms.getKeywords());
			clearResultsURL.setParameter(
				AccountDisplayTerms.SUBSCRIPTION_STATES, StringPool.BLANK);
		}

		return clearResultsURL.toString();
	}

	@Override
	public List<LabelItem> getFilterLabelItems() {
		AccountDisplayTerms accountDisplayTerms =
			(AccountDisplayTerms)searchContainer.getDisplayTerms();

		return new LabelItemList() {
			{
				List<DisplayTerm> displayTermsList =
					accountDisplayTerms.getDisplayTermsList();

				for (DisplayTerm displayTerm : displayTermsList) {
					String[] values = StringUtil.split(displayTerm.getValue());

					for (String value : values) {
						add(
							labelItem -> {
								labelItem.putData(
									"removeLabelURL",
									_getRemoveLabelURL(
										displayTerm.getName(), values, value));

								labelItem.setCloseable(true);

								labelItem.setLabel(
									_getLabel(displayTerm.getLabel(), value));
							});
					}
				}
			}
		};
	}

	@Override
	public String getSearchActionURL() {
		PortletURL searchActionURL = getPortletURL();

		return searchActionURL.toString();
	}

	@Override
	public String getSearchContainerId() {
		return "accountSearch";
	}

	@Override
	public Boolean isSelectable() {
		return false;
	}

	private String _getAccountName(String accountKey) {
		try {
			Account account = _accountWebService.getAccount(accountKey);

			return account.getName();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			return accountKey;
		}
	}

	private String _getLabel(String key, String value) {
		if (key.equals("first-line-support") ||
			key.equals("partner-reseller-si")) {

			value = _getTeamName(value);
		}
		else if (key.equals("parent-account")) {
			value = _getAccountName(value);
		}

		if (value.equals(StringPool.TRUE)) {
			value = LanguageUtil.get(request, "yes");
		}
		else if (value.equals(StringPool.FALSE)) {
			value = LanguageUtil.get(request, "no");
		}

		return String.format("%s: %s", LanguageUtil.get(request, key), value);
	}

	private String _getRemoveLabelURL(
		String displayTermName, String[] values, String value) {

		PortletURL removeLabelURL = getPortletURL();

		String[] removeKeywords = ArrayUtil.remove(values, value);

		removeLabelURL.setParameter(
			displayTermName, StringUtil.merge(removeKeywords));

		if (displayTermName.equals(AccountDisplayTerms.FLS_TEAM_KEY)) {
			removeLabelURL.setParameter("flsTeamName", StringPool.BLANK);
		}
		else if (displayTermName.equals(
					AccountDisplayTerms.PARENT_ACCOUNT_KEY)) {

			removeLabelURL.setParameter("parentAccountName", StringPool.BLANK);
		}
		else if (displayTermName.equals(AccountDisplayTerms.PARTNER_TEAM_KEY)) {
			removeLabelURL.setParameter("partnerTeamName", StringPool.BLANK);
		}

		return removeLabelURL.toString();
	}

	private String _getTeamName(String teamKey) {
		try {
			Team team = _teamWebService.getTeam(teamKey);

			return team.getName();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			return teamKey;
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ViewAccountsManagementToolbarDisplayContext.class);

	private final AccountWebService _accountWebService;
	private final TeamWebService _teamWebService;

}