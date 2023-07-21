/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.messaging;

/**
 * @author Michael C. Han
 * @author Brian Wing Shun Chan
 */
public abstract class BaseMessageListener implements MessageListener {

	@Override
	public void receive(Message message) throws MessageListenerException {
		try {
			doReceive(message);
		}
		catch (MessageListenerException messageListenerException) {
			throw messageListenerException;
		}
		catch (Exception exception) {
			throw new MessageListenerException(exception);
		}
	}

	protected abstract void doReceive(Message message) throws Exception;

}