/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.nio.intraband.messaging;

import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.nio.intraband.Datagram;
import com.liferay.portal.kernel.nio.intraband.test.MockIntraband;
import com.liferay.portal.kernel.nio.intraband.test.MockRegistrationReference;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.CodeCoverageAssertor;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;

import java.nio.ByteBuffer;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

/**
 * @author Shuyang Zhou
 */
public class IntrabandBridgeMessageListenerTest {

	@ClassRule
	public static final CodeCoverageAssertor codeCoverageAssertor =
		CodeCoverageAssertor.INSTANCE;

	@Test
	public void testConstructor() {
		IntrabandBridgeMessageListener intrabandBridgeMessageListener =
			new IntrabandBridgeMessageListener(_mockRegistrationReference);

		Assert.assertSame(
			_mockIntraband,
			ReflectionTestUtil.getFieldValue(
				intrabandBridgeMessageListener, "_intraband"));
		Assert.assertSame(
			_mockRegistrationReference,
			ReflectionTestUtil.getFieldValue(
				intrabandBridgeMessageListener, "_registrationReference"));
	}

	@Test
	public void testReceive() throws ClassNotFoundException {
		PortalClassLoaderUtil.setClassLoader(
			IntrabandBridgeMessageListenerTest.class.getClassLoader());

		IntrabandBridgeMessageListener intrabandBridgeMessageListener =
			new IntrabandBridgeMessageListener(_mockRegistrationReference);

		Message message = new Message();

		message.setDestinationName(
			IntrabandBridgeMessageListenerTest.class.getName());

		String payload = "payload";

		message.setPayload(payload);

		intrabandBridgeMessageListener.receive(message);

		Datagram datagram = _mockIntraband.getDatagram();

		ByteBuffer byteBuffer = datagram.getDataByteBuffer();

		MessageRoutingBag receivedMessageRoutingBag =
			MessageRoutingBag.fromByteArray(byteBuffer.array());

		Assert.assertNotNull(receivedMessageRoutingBag);

		Message receivedMessage = receivedMessageRoutingBag.getMessage();

		Assert.assertEquals(payload, receivedMessage.getPayload());
	}

	private final MockIntraband _mockIntraband = new MockIntraband();
	private final MockRegistrationReference _mockRegistrationReference =
		new MockRegistrationReference(_mockIntraband);

}