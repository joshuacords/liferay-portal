/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mail.reader.model;

import java.util.List;

/**
 * @author Scott Lee
 */
public class MessageDisplay {

	public MessageDisplay(
		Message message, List<Attachment> attachments, int messageCount) {

		_message = message;
		_attachments = attachments;
		_messageCount = messageCount;
	}

	public List<Attachment> getAttachments() {
		return _attachments;
	}

	public Message getMessage() {
		return _message;
	}

	public int getMessageCount() {
		return _messageCount;
	}

	private final List<Attachment> _attachments;
	private final Message _message;
	private final int _messageCount;

}