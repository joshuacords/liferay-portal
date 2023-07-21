/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mail.reader.internal.mailbox;

import com.liferay.mail.reader.constants.MailPortletKeys;
import com.liferay.mail.reader.mailbox.Mailbox;
import com.liferay.mail.reader.mailbox.MailboxFactory;
import com.liferay.mail.reader.model.Account;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.User;

import org.osgi.service.component.annotations.Component;

/**
 * @author Scott Lee
 */
@Component(
	immediate = true, property = "javax.portlet.name=" + MailPortletKeys.MAIL,
	service = MailboxFactory.class
)
public class IMAPMailboxFactory implements MailboxFactory {

	@Override
	public Mailbox getMailbox(User user, Account account, String password) {
		return new IMAPMailbox(user, account, password);
	}

	@Override
	public Mailbox getMailbox(User user, String protocol) {
		return new IMAPMailbox(user, null, StringPool.BLANK);
	}

	@Override
	public String getMailboxFactoryName() {
		return "imap";
	}

	@Override
	public void initialize() {
	}

}