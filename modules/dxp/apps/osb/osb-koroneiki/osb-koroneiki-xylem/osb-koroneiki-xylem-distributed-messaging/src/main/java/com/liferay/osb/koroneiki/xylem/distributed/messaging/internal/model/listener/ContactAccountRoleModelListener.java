/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.model.listener;

import com.liferay.osb.distributed.messaging.Message;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.osb.koroneiki.taproot.model.ContactAccountRole;
import com.liferay.osb.koroneiki.taproot.model.ContactRole;
import com.liferay.portal.kernel.model.ModelListener;

import java.util.concurrent.Callable;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = {
		"create.topic=koroneiki.account.contactrole.assigned",
		"remove.topic=koroneiki.account.contactrole.unassigned"
	},
	service = ModelListener.class
)
public class ContactAccountRoleModelListener
	extends BaseXylemModelListener<ContactAccountRole> {

	@Override
	protected Callable<Message> getCallable(
			ContactAccountRole contactAccountRole)
		throws Exception {

		Account account = contactAccountRole.getAccount();
		Contact contact = contactAccountRole.getContact();
		ContactRole contactRole = contactAccountRole.getContactRole();

		return () -> messageFactory.create(account, contact, contactRole);
	}

}