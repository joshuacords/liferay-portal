/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.nio.intraband.test;

import com.liferay.portal.kernel.nio.intraband.BaseIntraband;
import com.liferay.portal.kernel.nio.intraband.CompletionHandler;
import com.liferay.portal.kernel.nio.intraband.Datagram;
import com.liferay.portal.kernel.nio.intraband.DatagramHelper;
import com.liferay.portal.kernel.nio.intraband.RegistrationReference;

import java.io.IOException;

import java.nio.channels.Channel;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;

/**
 * @author Shuyang Zhou
 */
public class MockIntraband extends BaseIntraband {

	public MockIntraband() {
		this(10000);
	}

	public MockIntraband(long defaultTimeout) {
		super(defaultTimeout);
	}

	public Datagram getDatagram() {
		return _datagram;
	}

	public RegistrationReference getRegistrationReference() {
		return _registrationReference;
	}

	@Override
	public RegistrationReference registerChannel(Channel duplexChannel) {
		return new MockRegistrationReference(
			(ScatteringByteChannel)duplexChannel,
			(GatheringByteChannel)duplexChannel);
	}

	@Override
	public RegistrationReference registerChannel(
		ScatteringByteChannel scatteringByteChannel,
		GatheringByteChannel gatheringByteChannel) {

		return new MockRegistrationReference(
			scatteringByteChannel, gatheringByteChannel);
	}

	public void setIOException(IOException ioException) {
		_ioException = ioException;
	}

	@Override
	protected void doSendDatagram(
		RegistrationReference registrationReference, Datagram datagram) {

		_registrationReference = registrationReference;
		_datagram = datagram;

		CompletionHandler<?> completionHandler =
			DatagramHelper.getCompletionHandler(datagram);

		if (completionHandler == null) {
			return;
		}

		if (_ioException == null) {
			Datagram responseDatagram = processDatagram(datagram);

			if (responseDatagram != null) {
				completionHandler.replied(null, responseDatagram);
			}
		}
		else {
			completionHandler.failed(null, _ioException);
		}
	}

	protected Datagram processDatagram(Datagram datagram) {
		return null;
	}

	private Datagram _datagram;
	private IOException _ioException;
	private RegistrationReference _registrationReference;

}