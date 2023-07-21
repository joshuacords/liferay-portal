/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.nio.intraband;

import java.io.IOException;

import java.nio.channels.Channel;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;

import java.util.EnumSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author Shuyang Zhou
 */
public interface Intraband {

	public void close() throws InterruptedException, IOException;

	public DatagramReceiveHandler[] getDatagramReceiveHandlers();

	public boolean isOpen();

	public RegistrationReference registerChannel(Channel channel)
		throws IOException;

	public RegistrationReference registerChannel(
			ScatteringByteChannel scatteringByteChannel,
			GatheringByteChannel gatheringByteChannel)
		throws IOException;

	public DatagramReceiveHandler registerDatagramReceiveHandler(
		byte type, DatagramReceiveHandler datagramReceiveHandler);

	public void sendDatagram(
		RegistrationReference registrationReference, Datagram datagram);

	public <A> void sendDatagram(
		RegistrationReference registrationReference, Datagram datagram,
		A attachment, EnumSet<CompletionHandler.CompletionType> completionTypes,
		CompletionHandler<A> completionHandler);

	public <A> void sendDatagram(
		RegistrationReference registrationReference, Datagram datagram,
		A attachment, EnumSet<CompletionHandler.CompletionType> completionTypes,
		CompletionHandler<A> completionHandler, long timeout,
		TimeUnit timeUnit);

	public Datagram sendSyncDatagram(
			RegistrationReference registrationReference, Datagram datagram)
		throws InterruptedException, IOException, TimeoutException;

	public Datagram sendSyncDatagram(
			RegistrationReference registrationReference, Datagram datagram,
			long timeout, TimeUnit timeUnit)
		throws InterruptedException, IOException, TimeoutException;

	public DatagramReceiveHandler unregisterDatagramReceiveHandler(byte type);

}