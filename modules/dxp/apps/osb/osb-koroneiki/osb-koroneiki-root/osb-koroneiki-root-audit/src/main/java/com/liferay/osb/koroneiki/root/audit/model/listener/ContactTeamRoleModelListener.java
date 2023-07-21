/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.root.audit.model.listener;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.AuditEntry;
import com.liferay.osb.koroneiki.root.audit.model.BaseAuditModelListener;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.osb.koroneiki.taproot.model.ContactRole;
import com.liferay.osb.koroneiki.taproot.model.ContactTeamRole;
import com.liferay.osb.koroneiki.taproot.model.Team;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ModelListener;

import org.osgi.service.component.annotations.Component;

/**
 * @author Kyle Bischof
 * @author Amos Fong
 */
@Component(immediate = true, service = ModelListener.class)
public class ContactTeamRoleModelListener
	extends BaseAuditModelListener<ContactTeamRole> {

	@Override
	public void onAfterCreate(ContactTeamRole contactTeamRole)
		throws ModelListenerException {

		if (!isEnabled()) {
			return;
		}

		try {
			Contact contact = contactTeamRole.getContact();
			ContactRole contactRole = contactTeamRole.getContactRole();
			Team team = contactTeamRole.getTeam();

			auditEntryLocalService.addAuditEntry(
				getUserId(),
				classNameLocalService.getClassNameId(Account.class),
				team.getAccountId(),
				classNameLocalService.getClassNameId(Team.class),
				team.getTeamId(), AuditEntry.Action.UPDATE.toString(),
				"Contact", StringPool.BLANK, StringPool.BLANK,
				contact.getFullName(), String.valueOf(contact.getContactId()),
				team.getName() + StringPool.SPACE + contactRole.getName(),
				getServiceContext(contactTeamRole));

			auditEntryLocalService.addAuditEntry(
				getUserId(),
				classNameLocalService.getClassNameId(Contact.class),
				contactTeamRole.getContactId(),
				classNameLocalService.getClassNameId(ContactRole.class),
				contactTeamRole.getContactRoleId(),
				AuditEntry.Action.ASSIGN.toString(), "Team", StringPool.BLANK,
				StringPool.BLANK, team.getName(),
				String.valueOf(team.getTeamId()), contactRole.getName(),
				getServiceContext(contactTeamRole));

			auditEntryLocalService.addAuditEntry(
				getUserId(), classNameLocalService.getClassNameId(Team.class),
				contactTeamRole.getTeamId(),
				classNameLocalService.getClassNameId(Contact.class),
				contactTeamRole.getContactId(),
				AuditEntry.Action.ASSIGN.toString(), "Contact Role",
				StringPool.BLANK, StringPool.BLANK, contactRole.getName(),
				String.valueOf(contactTeamRole.getContactRoleId()),
				contact.getFullName(), getServiceContext(contactTeamRole));
		}
		catch (PortalException portalException) {
			throw new ModelListenerException(portalException);
		}
	}

	@Override
	public void onBeforeRemove(ContactTeamRole contactTeamRole)
		throws ModelListenerException {

		if (!isEnabled()) {
			return;
		}

		try {
			Contact contact = contactTeamRole.getContact();
			ContactRole contactRole = contactTeamRole.getContactRole();
			Team team = contactTeamRole.getTeam();

			auditEntryLocalService.addAuditEntry(
				getUserId(),
				classNameLocalService.getClassNameId(Account.class),
				team.getAccountId(),
				classNameLocalService.getClassNameId(Team.class),
				team.getTeamId(), AuditEntry.Action.UPDATE.toString(),
				"Contact", contact.getFullName(),
				String.valueOf(contact.getContactId()), StringPool.BLANK,
				StringPool.BLANK,
				team.getName() + StringPool.SPACE + contactRole.getName(),
				getServiceContext(contactTeamRole));

			auditEntryLocalService.addAuditEntry(
				getUserId(),
				classNameLocalService.getClassNameId(Contact.class),
				contactTeamRole.getContactId(),
				classNameLocalService.getClassNameId(ContactRole.class),
				contactTeamRole.getContactRoleId(),
				AuditEntry.Action.UNASSIGN.toString(), "Team", team.getName(),
				String.valueOf(team.getTeamId()), StringPool.BLANK,
				StringPool.BLANK, contactRole.getName(),
				getServiceContext(contactTeamRole));

			auditEntryLocalService.addAuditEntry(
				getUserId(), classNameLocalService.getClassNameId(Team.class),
				contactTeamRole.getTeamId(),
				classNameLocalService.getClassNameId(Contact.class),
				contactTeamRole.getContactId(),
				AuditEntry.Action.UNASSIGN.toString(), "Contact Role",
				contactRole.getName(),
				String.valueOf(contactTeamRole.getContactRoleId()),
				StringPool.BLANK, StringPool.BLANK, contact.getFullName(),
				getServiceContext(contactTeamRole));
		}
		catch (PortalException portalException) {
			throw new ModelListenerException(portalException);
		}
	}

}