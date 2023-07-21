/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.distributed.messaging.service.impl;

import com.liferay.osb.distributed.messaging.Message;
import com.liferay.osb.distributed.messaging.model.QueuedMessage;
import com.liferay.osb.distributed.messaging.service.base.QueuedMessageLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.jdbc.OutputBlob;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(
	property = "model.class.name=com.liferay.osb.distributed.messaging.model.QueuedMessage",
	service = AopService.class
)
public class QueuedMessageLocalServiceImpl
	extends QueuedMessageLocalServiceBaseImpl {

	public QueuedMessage addQueuedMessage(
			String messageBrokerClassName, String topic, Message message)
		throws PortalException {

		long queuedMessageId = counterLocalService.increment();

		QueuedMessage queuedMessage = queuedMessagePersistence.create(
			queuedMessageId);

		queuedMessage.setMessageBrokerClassName(messageBrokerClassName);
		queuedMessage.setTopic(topic);

		byte[] bytes = message.toByteArray();

		UnsyncByteArrayInputStream unsyncByteArrayInputStream =
			new UnsyncByteArrayInputStream(bytes);

		OutputBlob outputBlob = new OutputBlob(
			unsyncByteArrayInputStream, bytes.length);

		queuedMessage.setMessageObject(outputBlob);

		return queuedMessagePersistence.update(queuedMessage);
	}

	public List<QueuedMessage> getQueuedMessages(
		String messageBrokerClassName) {

		return queuedMessagePersistence.findByMessageBrokerClassName(
			messageBrokerClassName);
	}

}