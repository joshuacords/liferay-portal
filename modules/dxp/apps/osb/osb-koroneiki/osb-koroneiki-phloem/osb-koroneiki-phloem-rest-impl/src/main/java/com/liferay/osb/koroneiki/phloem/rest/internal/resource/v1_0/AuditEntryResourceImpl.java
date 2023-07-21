/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0;

import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.AuditEntry;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.util.AuditEntryUtil;
import com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0.util.ServiceContextUtil;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.AuditEntryResource;
import com.liferay.osb.koroneiki.root.identity.management.provider.ContactIdentityProvider;
import com.liferay.osb.koroneiki.root.service.AuditEntryService;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.osb.koroneiki.taproot.model.ContactRole;
import com.liferay.osb.koroneiki.taproot.model.Team;
import com.liferay.osb.koroneiki.taproot.model.TeamRole;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactRoleLocalService;
import com.liferay.osb.koroneiki.taproot.service.TeamLocalService;
import com.liferay.osb.koroneiki.taproot.service.TeamRoleLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Amos Fong
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/audit-entry.properties",
	scope = ServiceScope.PROTOTYPE, service = AuditEntryResource.class
)
public class AuditEntryResourceImpl extends BaseAuditEntryResourceImpl {

	@Override
	public Page<AuditEntry> getAccountAccountKeyAuditEntriesPage(
			String accountKey, Pagination pagination)
		throws Exception {

		Account account = _accountLocalService.getAccount(accountKey);

		return getAuditEntriesPage(
			Account.class, account.getAccountId(), pagination);
	}

	@Override
	public AuditEntry getAuditEntry(String auditEntryKey) throws Exception {
		return AuditEntryUtil.toAuditEntry(
			_auditEntryService.getAuditEntry(auditEntryKey));
	}

	@Override
	public Page<AuditEntry> getContactByUuidContactUuidAuditEntriesPage(
			String contactUuid, Pagination pagination)
		throws Exception {

		Contact contact = _contactLocalService.getContactByUuid(contactUuid);

		return getAuditEntriesPage(
			Contact.class, contact.getContactId(), pagination);
	}

	@Override
	public Page<AuditEntry> getContactRoleContactRoleKeyAuditEntriesPage(
			String contactRoleKey, Pagination pagination)
		throws Exception {

		ContactRole contactRole = _contactRoleLocalService.getContactRole(
			contactRoleKey);

		return getAuditEntriesPage(
			ContactRole.class, contactRole.getContactRoleId(), pagination);
	}

	@Override
	public Page<AuditEntry> getTeamRoleTeamRoleKeyAuditEntriesPage(
			String teamRoleKey, Pagination pagination)
		throws Exception {

		TeamRole teamRole = _teamRoleLocalService.getTeamRole(teamRoleKey);

		return getAuditEntriesPage(
			TeamRole.class, teamRole.getTeamRoleId(), pagination);
	}

	@Override
	public Page<AuditEntry> getTeamTeamKeyAuditEntriesPage(
			String teamKey, Pagination pagination)
		throws Exception {

		Team team = _teamLocalService.getTeam(teamKey);

		return getAuditEntriesPage(Team.class, team.getTeamId(), pagination);
	}

	@Override
	public Page<AuditEntry> postAccountAccountKeyAuditEntriesPage(
			String agentName, String agentUID, String accountKey,
			AuditEntry[] auditEntries)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);
		ServiceContextUtil.setAuditSetId(
			_counterLocalService.increment(
				com.liferay.osb.koroneiki.root.model.AuditEntry.class.
					getName()));

		Account account = _accountLocalService.getAccount(accountKey);

		List<AuditEntry> auditEntriesList = new ArrayList<>();

		for (AuditEntry auditEntry : auditEntries) {
			auditEntriesList.add(
				AuditEntryUtil.toAuditEntry(
					_auditEntryService.addAuditEntry(
						_classNameLocalService.getClassNameId(Account.class),
						account.getAccountId(),
						AuditEntryUtil.getDynamicClassNameId(
							auditEntry.getFieldClassLabel()),
						auditEntry.getFieldClassPK(),
						auditEntry.getActionAsString(), auditEntry.getField(),
						StringPool.BLANK, auditEntry.getOldValue(),
						StringPool.BLANK, auditEntry.getNewValue(),
						auditEntry.getDescription(),
						ServiceContextUtil.getServiceContext())));
		}

		return Page.of(auditEntriesList);
	}

	@Override
	public Page<AuditEntry> postContactByUuidContactUuidAuditEntriesPage(
			String agentName, String agentUID, String contactUuid,
			AuditEntry[] auditEntries)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);
		ServiceContextUtil.setAuditSetId(
			_counterLocalService.increment(
				com.liferay.osb.koroneiki.root.model.AuditEntry.class.
					getName()));

		Contact contact = _contactIdentityProvider.getContactByUuid(
			contactUuid);

		List<AuditEntry> auditEntriesList = new ArrayList<>();

		for (AuditEntry auditEntry : auditEntries) {
			auditEntriesList.add(
				AuditEntryUtil.toAuditEntry(
					_auditEntryService.addAuditEntry(
						_classNameLocalService.getClassNameId(Contact.class),
						contact.getContactId(),
						AuditEntryUtil.getDynamicClassNameId(
							auditEntry.getFieldClassLabel()),
						auditEntry.getFieldClassPK(),
						auditEntry.getActionAsString(), auditEntry.getField(),
						StringPool.BLANK, auditEntry.getOldValue(),
						StringPool.BLANK, auditEntry.getNewValue(),
						auditEntry.getDescription(),
						ServiceContextUtil.getServiceContext())));
		}

		return Page.of(auditEntriesList);
	}

	protected Page<AuditEntry> getAuditEntriesPage(
			Class<?> clazz, long classPK, Pagination pagination)
		throws Exception {

		long classNameId = _classNameLocalService.getClassNameId(clazz);

		return Page.of(
			transform(
				_auditEntryService.getAuditEntries(
					classNameId, classPK, pagination.getStartPosition(),
					pagination.getEndPosition(), null),
				AuditEntryUtil::toAuditEntry),
			pagination,
			_auditEntryService.getAuditEntriesCount(classNameId, classPK));
	}

	@Reference
	private AccountLocalService _accountLocalService;

	@Reference
	private AuditEntryService _auditEntryService;

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private ContactIdentityProvider _contactIdentityProvider;

	@Reference
	private ContactLocalService _contactLocalService;

	@Reference
	private ContactRoleLocalService _contactRoleLocalService;

	@Reference
	private CounterLocalService _counterLocalService;

	@Reference
	private TeamLocalService _teamLocalService;

	@Reference
	private TeamRoleLocalService _teamRoleLocalService;

}