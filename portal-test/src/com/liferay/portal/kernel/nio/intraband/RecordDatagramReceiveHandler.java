/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.nio.intraband;

/**
 * @author Shuyang Zhou
 */
public class RecordDatagramReceiveHandler implements DatagramReceiveHandler {

	public RecordDatagramReceiveHandler() {
		this(true);
	}

	public RecordDatagramReceiveHandler(boolean throwException) {
		_throwException = throwException;
	}

	public Datagram getReceiveDatagram() {
		return _datagram;
	}

	@Override
	public void receive(
		RegistrationReference registrationReference, Datagram datagram) {

		_datagram = datagram;

		if (_throwException) {
			throw new RuntimeException("RecordDatagramReceiveHandler");
		}
	}

	private volatile Datagram _datagram;
	private final boolean _throwException;

}