/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.petra.mail;

import com.liferay.portal.kernel.util.ArrayUtil;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

/**
 * @author Jorge Ferrer
 * @see    com.liferay.util.mail.LiferayMimeMessage
 */
public class LiferayMimeMessage extends MimeMessage {

	public LiferayMimeMessage(Session session) {
		super(session);
	}

	@Override
	protected void updateMessageID() throws MessagingException {
		String[] messageIds = getHeader("Message-ID");

		if (ArrayUtil.isNotEmpty(messageIds)) {

			// Keep current value

			return;
		}

		super.updateMessageID();
	}

}