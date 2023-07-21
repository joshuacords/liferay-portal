/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.messaging;

import java.util.Collection;

/**
 * @author Michael C. Han
 */
public interface MessageBus {

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public void addDestination(Destination destination);

	public boolean addMessageBusEventListener(
		MessageBusEventListener messageBusEventListener);

	public Destination getDestination(String destinationName);

	public int getDestinationCount();

	public Collection<String> getDestinationNames();

	public Collection<Destination> getDestinations();

	public boolean hasDestination(String destinationName);

	public boolean hasMessageListener(String destinationName);

	public boolean registerMessageListener(
		String destinationName, MessageListener messageListener);

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public Destination removeDestination(String destinationName);

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public Destination removeDestination(
		String destinationName, boolean closeOnRemove);

	public boolean removeMessageBusEventListener(
		MessageBusEventListener messageBusEventListener);

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public void replace(Destination destination);

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public void replace(Destination destination, boolean closeOnReplace);

	public void sendMessage(String destinationName, Message message);

	public void shutdown();

	public void shutdown(boolean force);

	public boolean unregisterMessageListener(
		String destinationName, MessageListener messageListener);

}