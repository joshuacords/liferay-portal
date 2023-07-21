/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.messaging.sender;

import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusException;

/**
 * @author     Michael C. Han
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public interface SingleDestinationSynchronousMessageSender {

	public Object send(Message message) throws MessageBusException;

	public Object send(Message message, long timeout)
		throws MessageBusException;

	public Object send(Object payload) throws MessageBusException;

	public Object send(Object payload, long timeout) throws MessageBusException;

}