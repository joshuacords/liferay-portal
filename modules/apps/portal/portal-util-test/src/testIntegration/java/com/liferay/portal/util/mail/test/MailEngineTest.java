/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.util.mail.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.petra.mail.MailEngine;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.test.mail.MailServiceTestUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.SynchronousMailTestRule;

import java.util.List;

import javax.mail.internet.InternetAddress;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Manuel de la Peña
 */
@RunWith(Arquillian.class)
public class MailEngineTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), SynchronousMailTestRule.INSTANCE);

	@Test
	public void testSendMail() throws Exception {
		MailEngine.send(
			new MailMessage(
				new InternetAddress("from@test.com"),
				new InternetAddress("to@test.com"), "Hello",
				"My name is Inigo Montoya.", true));

		Assert.assertEquals(1, MailServiceTestUtil.getInboxSize());

		List<com.liferay.portal.test.mail.MailMessage> mailMessages =
			MailServiceTestUtil.getMailMessages(
				"Body", "My name is Inigo Montoya.");

		Assert.assertEquals(mailMessages.toString(), 1, mailMessages.size());

		mailMessages = MailServiceTestUtil.getMailMessages("Subject", "Hello");

		Assert.assertEquals(mailMessages.toString(), 1, mailMessages.size());

		mailMessages = MailServiceTestUtil.getMailMessages("To", "to@test.com");

		Assert.assertEquals(mailMessages.toString(), 1, mailMessages.size());
	}

}