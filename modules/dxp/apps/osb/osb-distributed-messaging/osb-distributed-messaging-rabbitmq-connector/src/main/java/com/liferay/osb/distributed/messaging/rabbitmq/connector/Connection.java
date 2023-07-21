/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.distributed.messaging.rabbitmq.connector;

import com.rabbitmq.client.Channel;

import java.io.IOException;

/**
 * @author Amos Fong
 */
public interface Connection {

	public void connect() throws IOException;

	public Channel createChannel() throws IOException;

	public Channel createChannel(int prefetchCount) throws IOException;

	public void disconnect();

}