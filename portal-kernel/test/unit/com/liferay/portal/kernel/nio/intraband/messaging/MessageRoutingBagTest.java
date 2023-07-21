/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.nio.intraband.messaging;

import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayOutputStream;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.CodeCoverageAssertor;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.List;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

/**
 * @author Shuyang Zhou
 */
public class MessageRoutingBagTest {

	@ClassRule
	public static final CodeCoverageAssertor codeCoverageAssertor =
		CodeCoverageAssertor.INSTANCE;

	@Test
	public void testAutomaticSerialization() throws Exception {
		Message message = new Message();

		String destinationName = "destinationName";

		message.setDestinationName(destinationName);

		boolean synchronizedBridge = true;

		MessageRoutingBag messageRoutingBag = new MessageRoutingBag(
			message, synchronizedBridge);

		String routingId1 = "routingId1";

		messageRoutingBag.appendRoutingId(routingId1);

		String routingId2 = "routingId2";

		messageRoutingBag.appendRoutingId(routingId2);

		boolean routingDowncast = true;

		messageRoutingBag.setRoutingDowncast(routingDowncast);

		UnsyncByteArrayOutputStream unsyncByteArrayOutputStream =
			new UnsyncByteArrayOutputStream();

		try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
				unsyncByteArrayOutputStream)) {

			objectOutputStream.writeObject(messageRoutingBag);
		}

		byte[] bytes = unsyncByteArrayOutputStream.toByteArray();

		ObjectInputStream objectInputStream = new ObjectInputStream(
			new UnsyncByteArrayInputStream(bytes));

		MessageRoutingBag newMessageRoutingBag =
			(MessageRoutingBag)objectInputStream.readObject();

		Assert.assertEquals(
			destinationName, newMessageRoutingBag.getDestinationName());
		Assert.assertNull(
			ReflectionTestUtil.getFieldValue(newMessageRoutingBag, "_message"));
		Assert.assertNotNull(newMessageRoutingBag.getMessageData());
		Assert.assertEquals(
			routingDowncast, newMessageRoutingBag.isRoutingDowncast());

		List<String> routingTrace = ReflectionTestUtil.getFieldValue(
			newMessageRoutingBag, "_routingTrace");

		Assert.assertEquals(routingTrace.toString(), 2, routingTrace.size());
		Assert.assertEquals(routingId1, routingTrace.get(0));
		Assert.assertEquals(routingId2, routingTrace.get(1));

		Assert.assertTrue(newMessageRoutingBag.isVisited(routingId1));
		Assert.assertTrue(newMessageRoutingBag.isVisited(routingId2));
		Assert.assertFalse(newMessageRoutingBag.isVisited("routingId3"));

		Assert.assertEquals(
			synchronizedBridge, newMessageRoutingBag.isSynchronizedBridge());

		Message newMessage = newMessageRoutingBag.getMessage();

		Assert.assertNotNull(newMessage);

		Assert.assertNull(
			ReflectionTestUtil.getFieldValue(
				newMessageRoutingBag, "_messageData"));
		Assert.assertSame(newMessage, newMessageRoutingBag.getMessage());
	}

	@Test
	public void testManualSerialization() throws ClassNotFoundException {
		Message message = new Message();

		String destinationName = "destinationName";

		message.setDestinationName(destinationName);

		boolean synchronizedBridge = false;

		MessageRoutingBag messageRoutingBag = new MessageRoutingBag(
			message, synchronizedBridge);

		String routingId1 = "routingId1";

		messageRoutingBag.appendRoutingId(routingId1);

		String routingId2 = "routingId2";

		messageRoutingBag.appendRoutingId(routingId2);

		boolean routingDowncast = true;

		messageRoutingBag.setRoutingDowncast(routingDowncast);

		byte[] bytes = messageRoutingBag.toByteArray();

		MessageRoutingBag newMessageRoutingBag =
			MessageRoutingBag.fromByteArray(bytes);

		Assert.assertEquals(
			destinationName, newMessageRoutingBag.getDestinationName());
		Assert.assertNull(
			ReflectionTestUtil.getFieldValue(newMessageRoutingBag, "_message"));
		Assert.assertNotNull(newMessageRoutingBag.getMessageData());
		Assert.assertEquals(
			routingDowncast, newMessageRoutingBag.isRoutingDowncast());

		List<String> routingTrace = ReflectionTestUtil.getFieldValue(
			newMessageRoutingBag, "_routingTrace");

		Assert.assertEquals(routingTrace.toString(), 2, routingTrace.size());
		Assert.assertEquals(routingId1, routingTrace.get(0));
		Assert.assertEquals(routingId2, routingTrace.get(1));

		Assert.assertTrue(newMessageRoutingBag.isVisited(routingId1));
		Assert.assertTrue(newMessageRoutingBag.isVisited(routingId2));
		Assert.assertFalse(newMessageRoutingBag.isVisited("routingId3"));

		Assert.assertEquals(
			synchronizedBridge, newMessageRoutingBag.isSynchronizedBridge());

		Message newMessage = newMessageRoutingBag.getMessage();

		Assert.assertNotNull(newMessage);

		Assert.assertNull(
			ReflectionTestUtil.getFieldValue(
				newMessageRoutingBag, "_messageData"));
		Assert.assertSame(newMessage, newMessageRoutingBag.getMessage());
	}

	@Test
	public void testMessageAssociation() {
		Message message = new Message();

		MessageRoutingBag messageRoutingBag = new MessageRoutingBag(
			message, true);

		Message newMessage = new Message();

		messageRoutingBag.setMessage(newMessage);

		Assert.assertSame(
			newMessage,
			ReflectionTestUtil.getFieldValue(messageRoutingBag, "_message"));
		Assert.assertSame(
			messageRoutingBag,
			newMessage.get(MessageRoutingBag.MESSAGE_ROUTING_BAG));
	}

	@Test
	public void testUnserializableMessage() {
		Message message = new Message();

		message.setPayload(new Object());

		MessageRoutingBag messageRoutingBag = new MessageRoutingBag(
			message, true);

		try {
			messageRoutingBag.getMessageData();

			Assert.fail();
		}
		catch (RuntimeException runtimeException) {
			message.remove(MessageRoutingBag.MESSAGE_ROUTING_BAG);

			Assert.assertEquals(
				"Unable to write ordinary serializable object " + message,
				runtimeException.getMessage());
		}
	}

}