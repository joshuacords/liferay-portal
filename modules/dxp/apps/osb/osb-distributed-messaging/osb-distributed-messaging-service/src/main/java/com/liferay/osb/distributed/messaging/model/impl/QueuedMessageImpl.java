/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.distributed.messaging.model.impl;

import com.liferay.osb.distributed.messaging.Message;

import java.sql.Blob;

/**
 * @author Amos Fong
 */
public class QueuedMessageImpl extends QueuedMessageBaseImpl {

	public QueuedMessageImpl() {
	}

	public Message getMessage() throws Exception {
		Blob blob = getMessageObject();

		return Message.fromInputStream(blob.getBinaryStream());
	}

}