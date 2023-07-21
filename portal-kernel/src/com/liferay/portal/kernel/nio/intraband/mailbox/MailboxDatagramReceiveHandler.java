/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.nio.intraband.mailbox;

import com.liferay.portal.kernel.io.BigEndianCodec;
import com.liferay.portal.kernel.nio.intraband.BaseAsyncDatagramReceiveHandler;
import com.liferay.portal.kernel.nio.intraband.Datagram;
import com.liferay.portal.kernel.nio.intraband.Intraband;
import com.liferay.portal.kernel.nio.intraband.RegistrationReference;

import java.nio.ByteBuffer;

/**
 * @author Shuyang Zhou
 */
public class MailboxDatagramReceiveHandler
	extends BaseAsyncDatagramReceiveHandler {

	@Override
	protected void doReceive(
		RegistrationReference registrationReference, Datagram datagram) {

		long receipt = MailboxUtil.depositMail(datagram.getDataByteBuffer());

		byte[] data = new byte[8];

		BigEndianCodec.putLong(data, 0, receipt);

		Intraband intraband = registrationReference.getIntraband();

		intraband.sendDatagram(
			registrationReference,
			Datagram.createResponseDatagram(datagram, ByteBuffer.wrap(data)));
	}

}