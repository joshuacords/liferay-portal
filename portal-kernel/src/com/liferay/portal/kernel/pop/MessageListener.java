/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.pop;

import java.util.List;

import javax.mail.Message;

/**
 * @author Brian Wing Shun Chan
 */
public interface MessageListener {

	public default boolean accept(
		String from, List<String> recipients, Message message) {

		return accept(from, recipients.get(0), message);
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #accept(String, List, Message)}
	 */
	@Deprecated
	public boolean accept(String from, String recipient, Message message);

	public default void deliver(
			String from, List<String> recipients, Message message)
		throws MessageListenerException {

		deliver(from, recipients.get(0), message);
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #deliver(String, List, Message)}
	 */
	@Deprecated
	public void deliver(String from, String recipient, Message message)
		throws MessageListenerException;

	public String getId();

}