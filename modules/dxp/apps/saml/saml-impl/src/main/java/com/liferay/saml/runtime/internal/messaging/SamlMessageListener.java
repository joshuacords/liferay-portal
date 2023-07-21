/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.saml.runtime.internal.messaging;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListenerException;

/**
 * @author Tomas Polesovsky
 */
public abstract class SamlMessageListener extends BaseMessageListener {

	@Override
	public void receive(Message message) throws MessageListenerException {
		Thread currentThread = Thread.currentThread();

		ClassLoader classLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(
				SamlMessageListener.class.getClassLoader());

			super.receive(message);
		}
		finally {
			currentThread.setContextClassLoader(classLoader);
		}
	}

}