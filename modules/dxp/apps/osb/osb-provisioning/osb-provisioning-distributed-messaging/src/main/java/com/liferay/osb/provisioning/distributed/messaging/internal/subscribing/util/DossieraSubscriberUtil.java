/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.distributed.messaging.internal.subscribing.util;

import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkDomain;
import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkEntityName;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.provisioning.customer.model.AccountEntry;
import com.liferay.osb.provisioning.customer.web.service.AccountEntryWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.zendesk.constants.ZendeskTagConstants;
import com.liferay.osb.provisioning.zendesk.constants.ZendeskTicketConstants;
import com.liferay.osb.provisioning.zendesk.model.ZendeskOrganization;
import com.liferay.osb.provisioning.zendesk.model.ZendeskTicket;
import com.liferay.osb.provisioning.zendesk.web.service.ZendeskOrganizationWebService;
import com.liferay.osb.provisioning.zendesk.web.service.ZendeskTicketWebService;
import com.liferay.petra.string.CharPool;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = DossieraSubscriberUtil.class)
public class DossieraSubscriberUtil {

	public Account fetchAccount(String dossieraAccountKey) throws Exception {
		List<Account> accounts = _accountWebService.getAccounts(
			ExternalLinkDomain.DOSSIERA,
			ExternalLinkEntityName.DOSSIERA_ACCOUNT, dossieraAccountKey, 1, 1);

		if (!accounts.isEmpty()) {
			return accounts.get(0);
		}

		return null;
	}

	public String getAccountKey(JSONObject jsonObject) throws Exception {
		JSONObject projectJSONObject = jsonObject.getJSONObject("_project");

		if (projectJSONObject != null) {
			String dossieraProjectKey = projectJSONObject.getString(
				"_dossieraProjectKey");

			List<Account> accounts = _accountWebService.getAccounts(
				ExternalLinkDomain.DOSSIERA,
				ExternalLinkEntityName.DOSSIERA_PROJECT, dossieraProjectKey, 1,
				1);

			if (!accounts.isEmpty()) {
				Account account = accounts.get(0);

				return account.getKey();
			}
		}
		else {
			JSONObject accountJSONObject = jsonObject.getJSONObject("_account");

			String dossieraAccountKey = accountJSONObject.getString(
				"_dossieraAccountKey");

			Account account = fetchAccount(dossieraAccountKey);

			if (account != null) {
				return account.getKey();
			}
		}

		return null;
	}

	public String getAccountKey(String salesforceProjectKey) throws Exception {
		List<Account> accounts = _accountWebService.getAccounts(
			ExternalLinkDomain.SALESFORCE,
			ExternalLinkEntityName.SALESFORCE_PROJECT, salesforceProjectKey, 1,
			1);

		if (!accounts.isEmpty()) {
			Account account = accounts.get(0);

			return account.getKey();
		}

		return null;
	}

	public Map<String, String> getAccountProperties(
		Account account, JSONObject jsonObject) {

		Map<String, String> properties = new HashMap<>();

		if ((account != null) && (account.getProperties() != null)) {
			properties = account.getProperties();
		}

		JSONObject projectJSONObject = jsonObject.getJSONObject("_project");

		String liferayVersion = null;
		String projectSolution = null;

		if (projectJSONObject != null) {
			if (projectJSONObject.getBoolean("_gsInvolved")) {
				properties.put("gsOpportunity", "true");
			}
			else {
				properties.remove("gsOpportunity");
			}

			liferayVersion = projectJSONObject.getString("_liferayVersion");

			if (Validator.isNotNull(liferayVersion)) {
				properties.put("liferayVersion", liferayVersion);
			}

			projectSolution = projectJSONObject.getString("_projectSolution");
		}
		else {
			liferayVersion = jsonObject.getString("currentLiferayVersion");
			projectSolution = jsonObject.getString("projectSolution");
		}

		if (Validator.isNotNull(liferayVersion)) {
			properties.put("liferayVersion", liferayVersion);
		}
		else {
			properties.remove("liferayVersion");
		}

		if (Validator.isNotNull(projectSolution)) {
			properties.put("projectSolution", projectSolution);
		}
		else {
			properties.remove("projectSolution");
		}

		return properties;
	}

	public void updateTickets(Account account, Map<String, String> properties)
		throws Exception {

		AccountEntry accountEntry = _accountEntryWebService.fetchAccountEntry(
			account.getKey());

		if (accountEntry == null) {
			return;
		}

		ZendeskOrganization zendeskOrganization =
			_zendeskOrganizationWebService.getZendeskOrganization(
				String.valueOf(accountEntry.getAccountEntryId()));

		if (zendeskOrganization == null) {
			return;
		}

		String gsOpportunity = properties.get("gsOpportunity");
		String projectSolution = properties.get("projectSolution");

		Set<String> criteria = new HashSet<>();

		criteria.add(
			"organization:" + zendeskOrganization.getZendeskOrganizationId());
		criteria.add("status<" + ZendeskTicketConstants.STATUS_CLOSED);

		List<ZendeskTicket> zendeskTickets =
			_zendeskTicketWebService.getZendeskTickets(criteria);

		for (ZendeskTicket zendeskTicket : zendeskTickets) {
			Set<String> tags = zendeskTicket.getTags();

			tags.remove(ZendeskTagConstants.GS_OPPORTUNITY);
			tags.remove(ZendeskTagConstants.COMMERCE_SOLUTION);
			tags.remove(ZendeskTagConstants.SERVICE_SOLUTION);

			if (Validator.isNotNull(gsOpportunity)) {
				tags.add(ZendeskTagConstants.GS_OPPORTUNITY);
			}

			if (Validator.isNotNull(projectSolution)) {
				tags.add(_toZendeskTag(projectSolution));
			}
		}

		_zendeskTicketWebService.updateZendeskTickets(zendeskTickets);
	}

	private String _toZendeskTag(String tag) {
		return StringUtil.replace(
			StringUtil.toLowerCase(tag), CharPool.SPACE, CharPool.UNDERLINE);
	}

	@Reference
	private AccountEntryWebService _accountEntryWebService;

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private ZendeskOrganizationWebService _zendeskOrganizationWebService;

	@Reference
	private ZendeskTicketWebService _zendeskTicketWebService;

}