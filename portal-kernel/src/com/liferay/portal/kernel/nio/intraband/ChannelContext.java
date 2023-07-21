/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.nio.intraband;

import java.util.Queue;

/**
 * @author Shuyang Zhou
 */
public class ChannelContext {

	public ChannelContext(Queue<Datagram> sendingQueue) {
		_sendingQueue = sendingQueue;
	}

	public Datagram getReadingDatagram() {
		return _readingDatagram;
	}

	public RegistrationReference getRegistrationReference() {
		return _registrationReference;
	}

	public Queue<Datagram> getSendingQueue() {
		return _sendingQueue;
	}

	public Datagram getWritingDatagram() {
		return _writingDatagram;
	}

	public void setReadingDatagram(Datagram readingDatagram) {
		_readingDatagram = readingDatagram;
	}

	public void setRegistrationReference(
		RegistrationReference registrationReference) {

		_registrationReference = registrationReference;
	}

	public void setWritingDatagram(Datagram writingDatagram) {
		_writingDatagram = writingDatagram;
	}

	// All nonfinal fields are not thread safe. They depend on external logic to
	// do thread safe publication and must be accessed solely by polling threads
	// to remain thread safety.

	private Datagram _readingDatagram;
	private RegistrationReference _registrationReference;
	private final Queue<Datagram> _sendingQueue;
	private Datagram _writingDatagram;

}