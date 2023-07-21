/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.root.audit.model.listener;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.AuditEntry;
import com.liferay.osb.koroneiki.root.audit.model.BaseAuditModelListener;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.osb.koroneiki.taproot.model.ContactAccountRole;
import com.liferay.osb.koroneiki.taproot.model.ContactRole;
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
public class ContactAccountRoleModelListener
	extends BaseAuditModelListener<ContactAccountRole> {

	@Override
	public void onAfterCreate(ContactAccountRole contactAccountRole)
		throws ModelListenerException {

		if (!isEnabled()) {
			return;
		}

		try {
			Account account = contactAccountRole.getAccount();

			Contact contact = contactAccountRole.getContact();
			ContactRole contactRole = contactAccountRole.getContactRole();

			auditEntryLocalService.addAuditEntry(
				getUserId(),
				classNameLocalService.getClassNameId(Account.class),
				contactAccountRole.getAccountId(),
				classNameLocalService.getClassNameId(Contact.class),
				contactAccountRole.getContactId(),
				AuditEntry.Action.ASSIGN.toString(), "Contact Role",
				StringPool.BLANK, StringPool.BLANK, contactRole.getName(),
				String.valueOf(contactAccountRole.getContactRoleId()),
				contact.getFullName(), getServiceContext(contactAccountRole));

			auditEntryLocalService.addAuditEntry(
				getUserId(),
				classNameLocalService.getClassNameId(Contact.class),
				contactAccountRole.getContactId(),
				classNameLocalService.getClassNameId(ContactRole.class),
				contactAccountRole.getContactRoleId(),
				AuditEntry.Action.ASSIGN.toString(), "Account",
				StringPool.BLANK, StringPool.BLANK, account.getName(),
				String.valueOf(account.getAccountId()), contactRole.getName(),
				getServiceContext(contactAccountRole));
		}
		catch (PortalException portalException) {
			throw new ModelListenerException(portalException);
		}
	}

	@Override
	public void onBeforeRemove(ContactAccountRole contactAccountRole)
		throws ModelListenerException {

		if (!isEnabled()) {
			return;
		}

		try {
			Account account = contactAccountRole.getAccount();

			Contact contact = contactAccountRole.getContact();
			ContactRole contactRole = contactAccountRole.getContactRole();

			auditEntryLocalService.addAuditEntry(
				getUserId(),
				classNameLocalService.getClassNameId(Account.class),
				contactAccountRole.getAccountId(),
				classNameLocalService.getClassNameId(Contact.class),
				contactAccountRole.getContactId(),
				AuditEntry.Action.UNASSIGN.toString(), "Contact Role",
				contactRole.getName(),
				String.valueOf(contactAccountRole.getContactRoleId()),
				StringPool.BLANK, StringPool.BLANK, contact.getFullName(),
				getServiceContext(contactAccountRole));

			auditEntryLocalService.addAuditEntry(
				getUserId(),
				classNameLocalService.getClassNameId(Contact.class),
				contactAccountRole.getContactId(),
				classNameLocalService.getClassNameId(ContactRole.class),
				contactAccountRole.getContactRoleId(),
				AuditEntry.Action.UNASSIGN.toString(), "Account",
				account.getName(), String.valueOf(account.getAccountId()),
				StringPool.BLANK, StringPool.BLANK, contactRole.getName(),
				getServiceContext(contactAccountRole));
		}
		catch (PortalException portalException) {
			throw new ModelListenerException(portalException);
		}
	}

}