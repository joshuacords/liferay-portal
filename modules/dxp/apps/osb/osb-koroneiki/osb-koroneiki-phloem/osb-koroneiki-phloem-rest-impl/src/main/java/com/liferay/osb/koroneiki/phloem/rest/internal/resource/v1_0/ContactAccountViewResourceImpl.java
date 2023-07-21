/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactAccountView;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactRole;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.util.ContactAccountViewUtil;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ContactAccountViewResource;
import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.osb.koroneiki.taproot.service.AccountService;
import com.liferay.osb.koroneiki.taproot.service.ContactRoleService;
import com.liferay.osb.koroneiki.taproot.service.ContactService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.Collections;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Kyle Bischof
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/contact-account-view.properties",
	scope = ServiceScope.PROTOTYPE, service = ContactAccountViewResource.class
)
public class ContactAccountViewResourceImpl
	extends BaseContactAccountViewResourceImpl {

	@Override
	public Page<ContactAccountView>
			getContactByUuidContactUuidContactAccountViewsPage(
				String contactUuid, Pagination pagination)
		throws Exception {

		Contact contact = _contactService.fetchContactByUuid(contactUuid);

		if (contact == null) {
			return Page.of(Collections.emptyList(), pagination, 0);
		}

		return _getContactAccountViewPage(contact, pagination);
	}

	private Page<ContactAccountView> _getContactAccountViewPage(
			Contact contact, Pagination pagination)
		throws Exception {

		return Page.of(
			transform(
				_accountService.getContactAccounts(
					contact.getContactId(), pagination.getStartPosition(),
					pagination.getEndPosition()),
				account -> ContactAccountViewUtil.toContactAccountView(
					account,
					_contactRoleService.getContactAccountContactRoles(
						account.getAccountId(), contact.getContactId(),
						new String[] {
							ContactRole.Type.ACCOUNT_CUSTOMER.toString()
						},
						QueryUtil.ALL_POS, QueryUtil.ALL_POS),
					_contactRoleService.getContactAccountContactRoles(
						account.getAccountId(), contact.getContactId(),
						new String[] {
							ContactRole.Type.ACCOUNT_WORKER.toString()
						},
						QueryUtil.ALL_POS, QueryUtil.ALL_POS))),
			pagination,
			_accountService.getContactAccountsCount(contact.getContactId()));
	}

	@Reference
	private AccountService _accountService;

	@Reference
	private ContactRoleService _contactRoleService;

	@Reference
	private ContactService _contactService;

}