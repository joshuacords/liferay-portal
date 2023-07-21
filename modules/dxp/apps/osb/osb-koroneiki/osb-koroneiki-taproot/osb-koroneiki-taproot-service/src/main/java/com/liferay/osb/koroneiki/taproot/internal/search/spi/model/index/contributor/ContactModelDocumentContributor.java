/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.internal.search.spi.model.index.contributor;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactRole.Type;
import com.liferay.osb.koroneiki.phytohormone.model.Entitlement;
import com.liferay.osb.koroneiki.phytohormone.service.EntitlementLocalService;
import com.liferay.osb.koroneiki.root.model.ExternalLink;
import com.liferay.osb.koroneiki.root.service.ExternalLinkLocalService;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.osb.koroneiki.taproot.model.ContactRole;
import com.liferay.osb.koroneiki.taproot.model.ContactTeamRole;
import com.liferay.osb.koroneiki.taproot.model.Team;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactRoleLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactTeamRoleLocalService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.search.spi.model.index.contributor.ModelDocumentContributor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = "indexer.class.name=com.liferay.osb.koroneiki.taproot.model.Contact",
	service = ModelDocumentContributor.class
)
public class ContactModelDocumentContributor
	implements ModelDocumentContributor<Contact> {

	@Override
	public void contribute(Document document, Contact contact) {
		try {
			_contribute(document, contact);
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}
	}

	private void _contribute(Document document, Contact contact)
		throws PortalException {

		document.addKeyword(Field.COMPANY_ID, contact.getCompanyId());
		document.addDate(Field.CREATE_DATE, contact.getCreateDate());
		document.addDate(Field.MODIFIED_DATE, contact.getModifiedDate());

		document.addKeyword("contactKey", contact.getContactKey());
		document.addText(
			"emailAddress", StringUtil.toLowerCase(contact.getEmailAddress()));
		document.addText("firstName", contact.getFirstName());
		document.addText("lastName", contact.getLastName());
		document.addText("middleName", contact.getMiddleName());
		document.addKeyword("uuid", contact.getUuid());

		document.addTextSortable(
			"emailAddress", StringUtil.toLowerCase(contact.getEmailAddress()));
		document.addTextSortable("firstName", contact.getFirstName());
		document.addTextSortable("lastName", contact.getLastName());

		_contributeAccounts(document, contact.getContactId());
		_contributeEntitlements(document, contact.getContactId());
		_contributeExternalLinks(document, contact.getContactId());
		_contributeTeams(document, contact.getContactId());
	}

	private void _contributeAccounts(Document document, long contactId)
		throws PortalException {

		Set<String> accountKeys = new HashSet<>();
		Set<String> accountKeysContactRoleKeys = new HashSet<>();
		Set<String> customerAccountKeys = new HashSet<>();
		Set<String> workerAccountKeys = new HashSet<>();

		List<Account> accounts = _accountLocalService.getContactAccounts(
			contactId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (Account account : accounts) {
			accountKeys.add(account.getAccountKey());

			List<ContactRole> customerContactRoles =
				_contactRoleLocalService.getContactAccountContactRoles(
					account.getAccountId(), contactId,
					new String[] {Type.ACCOUNT_CUSTOMER.toString()},
					QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			if (!customerContactRoles.isEmpty()) {
				customerAccountKeys.add(account.getAccountKey());
			}

			for (ContactRole contactRole : customerContactRoles) {
				accountKeysContactRoleKeys.add(
					account.getAccountKey() + StringPool.UNDERLINE +
						contactRole.getContactRoleKey());
			}

			List<ContactRole> workerContactRoles =
				_contactRoleLocalService.getContactAccountContactRoles(
					account.getAccountId(), contactId,
					new String[] {Type.ACCOUNT_WORKER.toString()},
					QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			if (!workerContactRoles.isEmpty()) {
				workerAccountKeys.add(account.getAccountKey());
			}

			for (ContactRole contactRole : workerContactRoles) {
				accountKeysContactRoleKeys.add(
					account.getAccountKey() + StringPool.UNDERLINE +
						contactRole.getContactRoleKey());
			}
		}

		document.addKeyword(
			"accountKeys", ArrayUtil.toStringArray(accountKeys.toArray()));
		document.addKeyword(
			"accountKeysContactRoleKeys",
			ArrayUtil.toStringArray(accountKeysContactRoleKeys.toArray()));
		document.addKeyword(
			"customerAccountKeys",
			ArrayUtil.toStringArray(customerAccountKeys.toArray()));
		document.addKeyword(
			"workerAccountKeys",
			ArrayUtil.toStringArray(workerAccountKeys.toArray()));
	}

	private void _contributeEntitlements(Document document, long contactId)
		throws PortalException {

		Set<String> entitlementNames = new HashSet<>();

		List<Entitlement> entitlements =
			_entitlementLocalService.getEntitlements(
				Contact.class.getName(), contactId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);

		for (Entitlement entitlement : entitlements) {
			entitlementNames.add(entitlement.getName());
		}

		document.addKeyword(
			"entitlements",
			ArrayUtil.toStringArray(entitlementNames.toArray()));
	}

	private void _contributeExternalLinks(Document document, long contactId)
		throws PortalException {

		Set<String> externalLinkDomains = new HashSet<>();
		Set<String> externalLinkEntityIds = new HashSet<>();
		Set<String> externalLinkEntityNames = new HashSet<>();

		List<ExternalLink> externalLinks =
			_externalLinkLocalService.getExternalLinks(
				Contact.class.getName(), contactId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);

		for (ExternalLink externalLink : externalLinks) {
			String entityName =
				externalLink.getDomain() + StringPool.UNDERLINE +
					externalLink.getEntityName();

			String entityId =
				entityName + StringPool.UNDERLINE + externalLink.getEntityId();

			externalLinkDomains.add(externalLink.getDomain());
			externalLinkEntityIds.add(entityId);
			externalLinkEntityNames.add(entityName);
		}

		document.addKeyword(
			"externalLinkDomains",
			ArrayUtil.toStringArray(externalLinkDomains.toArray()));
		document.addKeyword(
			"externalLinkEntityIds",
			ArrayUtil.toStringArray(externalLinkEntityIds.toArray()));
		document.addKeyword(
			"externalLinkEntityNames",
			ArrayUtil.toStringArray(externalLinkEntityNames.toArray()));
	}

	private void _contributeTeams(Document document, long contactId)
		throws PortalException {

		Set<String> teamKeys = new HashSet<>();
		Set<String> teamKeysContactRoleKeys = new HashSet<>();

		List<ContactTeamRole> contactTeamRoles =
			_contactTeamRoleLocalService.getContactTeamRoles(contactId);

		for (ContactTeamRole contactTeamRole : contactTeamRoles) {
			Team team = contactTeamRole.getTeam();
			ContactRole contactRole = contactTeamRole.getContactRole();

			teamKeys.add(team.getTeamKey());
			teamKeysContactRoleKeys.add(
				team.getTeamKey() + StringPool.UNDERLINE +
					contactRole.getContactRoleKey());
		}

		document.addKeyword(
			"teamKeys", ArrayUtil.toStringArray(teamKeys.toArray()));
		document.addKeyword(
			"teamKeysContactRoleKeys",
			ArrayUtil.toStringArray(teamKeysContactRoleKeys.toArray()));
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ContactModelDocumentContributor.class);

	@Reference
	private AccountLocalService _accountLocalService;

	@Reference
	private ContactRoleLocalService _contactRoleLocalService;

	@Reference
	private ContactTeamRoleLocalService _contactTeamRoleLocalService;

	@Reference
	private EntitlementLocalService _entitlementLocalService;

	@Reference
	private ExternalLinkLocalService _externalLinkLocalService;

}