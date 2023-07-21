/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.model.impl;

import com.liferay.osb.koroneiki.phytohormone.model.Entitlement;
import com.liferay.osb.koroneiki.phytohormone.service.EntitlementLocalServiceUtil;
import com.liferay.osb.koroneiki.root.model.ExternalLink;
import com.liferay.osb.koroneiki.root.service.ExternalLinkLocalServiceUtil;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.model.AccountField;
import com.liferay.osb.koroneiki.taproot.model.Team;
import com.liferay.osb.koroneiki.taproot.service.AccountFieldLocalServiceUtil;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalServiceUtil;
import com.liferay.osb.koroneiki.taproot.service.TeamLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.service.AddressLocalServiceUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Kyle Bischof
 */
public class AccountImpl extends AccountBaseImpl {

	public AccountImpl() {
	}

	public List<AccountField> getAccountFields() {
		return AccountFieldLocalServiceUtil.getAccountFields(getAccountId());
	}

	public Map<String, String> getAccountFieldsMap() {
		Map<String, String> accountFieldsMap = new HashMap<>();

		List<AccountField> accountFields = getAccountFields();

		for (AccountField accountField : accountFields) {
			accountFieldsMap.put(
				accountField.getName(), accountField.getValue());
		}

		return accountFieldsMap;
	}

	public List<Address> getAddresses() {
		return AddressLocalServiceUtil.getAddresses(
			getCompanyId(), Account.class.getName(), getAccountId());
	}

	public List<Team> getAssignedTeams() {
		return TeamLocalServiceUtil.getAccountAssignedTeams(
			getAccountId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	public List<Account> getChildAccounts() throws PortalException {
		return AccountLocalServiceUtil.getAccounts(
			getAccountId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	public List<Entitlement> getEntitlements() {
		return EntitlementLocalServiceUtil.getEntitlements(
			Account.class.getName(), getAccountId(), QueryUtil.ALL_POS,
			QueryUtil.ALL_POS);
	}

	public List<ExternalLink> getExternalLinks() {
		return ExternalLinkLocalServiceUtil.getExternalLinks(
			Account.class.getName(), getAccountId(), QueryUtil.ALL_POS,
			QueryUtil.ALL_POS);
	}

	public Account getParentAccount() throws PortalException {
		if (getParentAccountId() <= 0) {
			return null;
		}

		return AccountLocalServiceUtil.getAccount(getParentAccountId());
	}

}