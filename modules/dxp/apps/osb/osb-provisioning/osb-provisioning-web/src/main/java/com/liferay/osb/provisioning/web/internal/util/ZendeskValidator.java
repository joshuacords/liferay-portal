/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.provisioning.web.internal.util;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.TeamRole;
import com.liferay.osb.provisioning.customer.model.AccountEntry;
import com.liferay.osb.provisioning.customer.web.service.AccountEntryWebService;
import com.liferay.osb.provisioning.exception.ContactRequiredException;
import com.liferay.osb.provisioning.koroneiki.constants.ContactRoleConstants;
import com.liferay.osb.provisioning.koroneiki.constants.TeamRoleConstants;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactRoleWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.TeamRoleWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.TeamWebService;
import com.liferay.osb.provisioning.search.FilterQuery;
import com.liferay.osb.provisioning.zendesk.model.ZendeskOrganization;
import com.liferay.osb.provisioning.zendesk.model.ZendeskTicket;
import com.liferay.osb.provisioning.zendesk.model.ZendeskUser;
import com.liferay.osb.provisioning.zendesk.web.service.ZendeskOrganizationWebService;
import com.liferay.osb.provisioning.zendesk.web.service.ZendeskTicketWebService;
import com.liferay.osb.provisioning.zendesk.web.service.ZendeskUserWebService;
import com.liferay.petra.string.StringPool;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = ZendeskValidator.class)
public class ZendeskValidator {

	public void validateCustomerZendeskTickets(
			String accountKey, String emailAddress)
		throws Exception {

		if (!_isValidateCustomerZendeskTickets(accountKey, emailAddress)) {
			return;
		}

		ZendeskUser zendeskUser =
			_zendeskUserWebService.getZendeskUserByEmailAddress(emailAddress);

		if (zendeskUser == null) {
			return;
		}

		List<Long> zendeskOrganizationIds = new ArrayList<>();

		AccountEntry accountEntry = _accountEntryWebService.fetchAccountEntry(
			accountKey);

		if (accountEntry != null) {
			ZendeskOrganization zendeskOrganization =
				_zendeskOrganizationWebService.getZendeskOrganization(
					String.valueOf(accountEntry.getAccountEntryId()));

			if (zendeskOrganization != null) {
				zendeskOrganizationIds.add(
					zendeskOrganization.getZendeskOrganizationId());
			}
		}

		if (_hasZendeskTickets(zendeskOrganizationIds, zendeskUser)) {
			throw new ContactRequiredException();
		}
	}

	public void validateFLSPartnerZendeskTickets(
			String teamKey, String emailAddress)
		throws Exception {

		if (!_isValidateFLSPartnerZendeskTickets(teamKey)) {
			return;
		}

		ZendeskUser zendeskUser =
			_zendeskUserWebService.getZendeskUserByEmailAddress(emailAddress);

		if (zendeskUser == null) {
			return;
		}

		List<Long> zendeskOrganizationIds = new ArrayList<>();

		TeamRole flsTeamRole = _teamRoleWebService.getTeamRole(
			TeamRole.Type.ACCOUNT.toString(),
			TeamRoleConstants.NAME_FIRST_LINE_SUPPORT);

		FilterQuery filterQuery = new FilterQuery();

		filterQuery.addLambdaEquals(
			true, "assignedTeamKeyTeamRoleKeys",
			teamKey + "_" + flsTeamRole.getKey());

		List<Account> accounts = _accountWebService.search(
			StringPool.BLANK, filterQuery, 1, 1000, StringPool.BLANK);

		for (Account account : accounts) {
			AccountEntry accountEntry =
				_accountEntryWebService.fetchAccountEntry(account.getKey());

			if (accountEntry == null) {
				continue;
			}

			ZendeskOrganization zendeskOrganization =
				_zendeskOrganizationWebService.getZendeskOrganization(
					String.valueOf(accountEntry.getAccountEntryId()));

			zendeskOrganizationIds.add(
				zendeskOrganization.getZendeskOrganizationId());
		}

		if (_hasZendeskTickets(zendeskOrganizationIds, zendeskUser)) {
			throw new ContactRequiredException();
		}
	}

	private boolean _hasZendeskTickets(
			List<Long> zendeskOrganizationIds, ZendeskUser zendeskUser)
		throws Exception {

		if (zendeskOrganizationIds.isEmpty()) {
			return false;
		}

		Set<String> criteria = new HashSet<>();

		for (long zendeskOrganizationId : zendeskOrganizationIds) {
			criteria.add("organization:" + zendeskOrganizationId);
		}

		criteria.add("requester:" + zendeskUser.getZendeskUserId());
		criteria.add("status<closed");

		List<ZendeskTicket> zendeskTickets =
			_zendeskTicketWebService.getZendeskTickets(criteria);

		if (!zendeskTickets.isEmpty()) {
			return true;
		}

		return false;
	}

	private boolean _isValidateCustomerZendeskTickets(
			String accountKey, String emailAddress)
		throws Exception {

		boolean supportRequester = false;

		FilterQuery filterQuery = new FilterQuery();

		ContactRole supportAdministratorContactRole =
			_contactRoleWebService.getContactRole(
				ContactRole.Type.ACCOUNT_CUSTOMER.toString(),
				ContactRoleConstants.NAME_SUPPORT_ADMINISTRATOR);

		filterQuery.addLambdaEquals(
			false, "accountKeysContactRoleKeys",
			accountKey + "_" + supportAdministratorContactRole.getKey());

		ContactRole supportRequesterContactRole =
			_contactRoleWebService.getContactRole(
				ContactRole.Type.ACCOUNT_CUSTOMER.toString(),
				ContactRoleConstants.NAME_SUPPORT_REQUESTER);

		filterQuery.addLambdaEquals(
			false, "accountKeysContactRoleKeys",
			accountKey + "_" + supportRequesterContactRole.getKey());

		List<Contact> contacts = _contactWebService.search(
			StringPool.BLANK, filterQuery, 1, 1000, StringPool.BLANK);

		for (Contact contact : contacts) {
			String curEmailAddress = contact.getEmailAddress();

			if (curEmailAddress.equals(emailAddress)) {
				supportRequester = true;

				break;
			}
		}

		if (supportRequester && (contacts.size() <= 1)) {
			return true;
		}

		return false;
	}

	private boolean _isValidateFLSPartnerZendeskTickets(String teamKey)
		throws Exception {

		FilterQuery filterQuery = new FilterQuery();

		filterQuery.addLambdaEquals(true, "teamKeys", teamKey);

		List<Contact> contacts = _contactWebService.search(
			StringPool.BLANK, filterQuery, 1, 1000, StringPool.BLANK);

		if (contacts.size() <= 1) {
			return true;
		}

		return false;
	}

	@Reference
	private AccountEntryWebService _accountEntryWebService;

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private ContactRoleWebService _contactRoleWebService;

	@Reference
	private ContactWebService _contactWebService;

	@Reference
	private TeamRoleWebService _teamRoleWebService;

	@Reference
	private TeamWebService _teamWebService;

	@Reference
	private ZendeskOrganizationWebService _zendeskOrganizationWebService;

	@Reference
	private ZendeskTicketWebService _zendeskTicketWebService;

	@Reference
	private ZendeskUserWebService _zendeskUserWebService;

}