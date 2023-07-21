/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.internal.search.spi.model.index.contributor;

import com.liferay.osb.koroneiki.phytohormone.model.Entitlement;
import com.liferay.osb.koroneiki.root.model.ExternalLink;
import com.liferay.osb.koroneiki.root.service.ExternalLinkLocalService;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.osb.koroneiki.taproot.model.ContactRole;
import com.liferay.osb.koroneiki.taproot.model.ContactTeamRole;
import com.liferay.osb.koroneiki.taproot.model.Team;
import com.liferay.osb.koroneiki.taproot.model.TeamAccountRole;
import com.liferay.osb.koroneiki.taproot.model.TeamRole;
import com.liferay.osb.koroneiki.taproot.service.ContactLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactRoleLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactTeamRoleLocalService;
import com.liferay.osb.koroneiki.taproot.service.TeamAccountRoleLocalService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringPool;
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
	property = "indexer.class.name=com.liferay.osb.koroneiki.taproot.model.Team",
	service = ModelDocumentContributor.class
)
public class TeamModelDocumentContributor
	implements ModelDocumentContributor<Team> {

	@Override
	public void contribute(Document document, Team team) {
		try {
			_contribute(document, team);
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}
	}

	private void _contribute(Document document, Team team)
		throws PortalException {

		document.addKeyword(Field.COMPANY_ID, team.getCompanyId());
		document.addDate(Field.CREATE_DATE, team.getCreateDate());
		document.addDate(Field.MODIFIED_DATE, team.getModifiedDate());
		document.addText(Field.NAME, team.getName());

		Account account = team.getAccount();

		document.addKeyword("accountKey", account.getAccountKey());

		document.addKeyword("system", team.isSystem());
		document.addKeyword("teamKey", team.getTeamKey());

		document.addTextSortable(Field.NAME, team.getName());

		_contributeAccountData(document, account);
		_contributeAssignedAccounts(document, team.getTeamId());
		_contributeContacts(document, team.getTeamId());
		_contributeExternalLinks(document, team.getTeamId());
	}

	private void _contributeAccountData(Document document, Account account)
		throws PortalException {

		Set<String> entitlementNames = new HashSet<>();

		List<Entitlement> entitlements = account.getEntitlements();

		for (Entitlement entitlement : entitlements) {
			entitlementNames.add(entitlement.getName());
		}

		document.addKeyword(
			"accountEntitlements",
			ArrayUtil.toStringArray(entitlementNames.toArray()));
	}

	private void _contributeAssignedAccounts(Document document, long teamId)
		throws PortalException {

		Set<String> accountKeyTeamRoleKeys = new HashSet<>();

		List<TeamAccountRole> teamAccountRoles =
			_teamAccountRoleLocalService.getTeamAccountRoles(teamId);

		for (TeamAccountRole teamAccountRole : teamAccountRoles) {
			Account account = teamAccountRole.getAccount();
			TeamRole teamRole = teamAccountRole.getTeamRole();

			accountKeyTeamRoleKeys.add(
				account.getAccountKey() + StringPool.UNDERLINE +
					teamRole.getTeamRoleKey());
		}

		document.addKeyword(
			"accountKeyTeamRoleKeys",
			ArrayUtil.toStringArray(accountKeyTeamRoleKeys.toArray()));
	}

	private void _contributeContacts(Document document, long teamId)
		throws PortalException {

		Set<String> contactEmailAddresses = new HashSet<>();
		Set<String> contactUuidContactRoleKeys = new HashSet<>();
		Set<String> contactUuids = new HashSet<>();

		List<ContactTeamRole> contactTeamRoles =
			_contactTeamRoleLocalService.getContactTeamRolesByTeamId(teamId);

		for (ContactTeamRole contactTeamRole : contactTeamRoles) {
			Contact contact = _contactLocalService.getContact(
				contactTeamRole.getContactId());
			ContactRole contactRole = _contactRoleLocalService.getContactRole(
				contactTeamRole.getContactRoleId());

			contactEmailAddresses.add(contact.getEmailAddress());

			contactUuidContactRoleKeys.add(
				contact.getUuid() + StringPool.UNDERLINE +
					contactRole.getContactRoleKey());

			contactUuids.add(contact.getUuid());
		}

		document.addKeyword(
			"contactEmailAddresses",
			ArrayUtil.toStringArray(contactEmailAddresses.toArray()));
		document.addKeyword(
			"contactUuidContactRoleKeys",
			ArrayUtil.toStringArray(contactUuidContactRoleKeys.toArray()));
		document.addKeyword(
			"contactUuids", ArrayUtil.toStringArray(contactUuids.toArray()));
	}

	private void _contributeExternalLinks(Document document, long teamId)
		throws PortalException {

		Set<String> externalLinkDomains = new HashSet<>();
		Set<String> externalLinkEntityIds = new HashSet<>();
		Set<String> externalLinkEntityNames = new HashSet<>();

		List<ExternalLink> externalLinks =
			_externalLinkLocalService.getExternalLinks(
				Team.class.getName(), teamId, QueryUtil.ALL_POS,
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

	private static final Log _log = LogFactoryUtil.getLog(
		TeamModelDocumentContributor.class);

	@Reference
	private ContactLocalService _contactLocalService;

	@Reference
	private ContactRoleLocalService _contactRoleLocalService;

	@Reference
	private ContactTeamRoleLocalService _contactTeamRoleLocalService;

	@Reference
	private ExternalLinkLocalService _externalLinkLocalService;

	@Reference
	private TeamAccountRoleLocalService _teamAccountRoleLocalService;

}