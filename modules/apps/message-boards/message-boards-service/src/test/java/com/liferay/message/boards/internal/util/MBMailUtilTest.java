/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.message.boards.internal.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.util.PropsValues;

import javax.mail.Message;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;

import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author John Zhao
 */
@PrepareForTest({MBMailUtil.class, StringPool.class, PropsValues.class})
@RunWith(PowerMockRunner.class)
public class MBMailUtilTest {

	@Test
	public void testGetCategoryId() {
		Assert.assertEquals(
			10640,
			MBMailUtil.getCategoryId(
				"<mb_message.10640.20646.1425017183884@gmail.com>"));
	}

	@Test
	public void testGetCategoryIdWithNoSurroundingChevrons() {
		Assert.assertEquals(
			10640,
			MBMailUtil.getCategoryId(
				"mb_message.10640.20646.1425017183884@events.gmail.com"));
	}

	@Test
	public void testGetMessageId() {
		Assert.assertEquals(
			20646,
			MBMailUtil.getMessageId(
				"<mb_message.10640.20646.1425017183884@gmail.com>"));
	}

	@Test
	public void testGetMessageIdWithNoSurroundingChevrons() {
		Assert.assertEquals(
			20646,
			MBMailUtil.getMessageId(
				"mb_message.10640.20646.1425017183884@events.gmail.com"));
	}

	@Test
	public void testGetParentMessageIdWithTheInReplyToHeader()
		throws Exception {

		Message message = Mockito.mock(Message.class);

		Mockito.when(
			message.getHeader("In-Reply-To")
		).thenReturn(
			new String[] {"<mb_message.10640.20646.1425017183884@gmail.com>"}
		);

		Assert.assertEquals(20646, MBMailUtil.getParentMessageId(message));
	}

	@Test
	public void testGetParentMessageIdWithTheReferencesHeader()
		throws Exception {

		Message message = Mockito.mock(Message.class);

		Mockito.when(
			message.getHeader("References")
		).thenReturn(
			new String[] {"<mb_message.10640.20646.1425017183884@gmail.com>"}
		);

		Assert.assertEquals(20646, MBMailUtil.getParentMessageId(message));
	}

	@Test
	public void testGetParentMessageWithTheInReplyToHeader() throws Exception {
		Message message = Mockito.mock(Message.class);

		Mockito.when(
			message.getHeader("In-Reply-To")
		).thenReturn(
			new String[] {"<mb_message.10640.20646.1425017183884@gmail.com>"}
		);

		Assert.assertEquals(20646, MBMailUtil.getParentMessageId(message));
	}

}