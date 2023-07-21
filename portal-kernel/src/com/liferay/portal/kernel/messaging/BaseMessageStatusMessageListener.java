/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.messaging;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.sender.SingleDestinationMessageSender;

/**
 * @author Michael C. Han
 * @author Brian Wing Shun Chan
 */
public abstract class BaseMessageStatusMessageListener
	implements MessageListener {

	@Override
	public void receive(Message message) {
		MessageStatus messageStatus = new MessageStatus();

		messageStatus.startTimer();

		try {
			doReceive(message, messageStatus);
		}
		catch (Exception exception) {
			_log.error(
				"Unable to process request " + message.getDestinationName(),
				exception);

			messageStatus.setException(exception);
		}
		finally {
			messageStatus.stopTimer();

			message = new Message();

			message.setPayload(messageStatus);

			Destination destination = getDestination();

			destination.send(message);
		}
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public void setStatusSender(SingleDestinationMessageSender statusSender) {
	}

	protected abstract void doReceive(
			Message message, MessageStatus messageStatus)
		throws Exception;

	protected abstract Destination getDestination();

	private static final Log _log = LogFactoryUtil.getLog(
		BaseMessageStatusMessageListener.class);

}