/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.nio.intraband.rpc;

import com.liferay.portal.kernel.io.Deserializer;
import com.liferay.portal.kernel.io.Serializer;
import com.liferay.portal.kernel.nio.intraband.Datagram;
import com.liferay.portal.kernel.nio.intraband.DatagramReceiveHandler;
import com.liferay.portal.kernel.nio.intraband.Intraband;
import com.liferay.portal.kernel.nio.intraband.RegistrationReference;
import com.liferay.portal.kernel.process.ProcessCallable;

import java.io.Serializable;

/**
 * @author Shuyang Zhou
 */
public class BootstrapRPCDatagramReceiveHandler
	implements DatagramReceiveHandler {

	@Override
	public void receive(
		RegistrationReference registrationReference, Datagram datagram) {

		Deserializer deserializer = new Deserializer(
			datagram.getDataByteBuffer());

		Serializer serializer = new Serializer();

		try {
			ProcessCallable<? extends Serializable> processCallable =
				deserializer.readObject();

			serializer.writeObject(new RPCResponse(processCallable.call()));
		}
		catch (Exception exception) {
			serializer.writeObject(new RPCResponse(exception));
		}

		Intraband intraband = registrationReference.getIntraband();

		intraband.sendDatagram(
			registrationReference,
			Datagram.createResponseDatagram(
				datagram, serializer.toByteBuffer()));
	}

}