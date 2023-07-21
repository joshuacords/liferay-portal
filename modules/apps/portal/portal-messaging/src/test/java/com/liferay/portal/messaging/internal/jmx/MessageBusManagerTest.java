/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.messaging.internal.jmx;

import com.liferay.portal.kernel.messaging.MessageBus;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;

import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Michael C. Han
 * @author Miguel Pastor
 */
@PowerMockIgnore("javax.management.*")
@RunWith(PowerMockRunner.class)
public class MessageBusManagerTest {

	@Before
	public void setUp() throws Exception {
		_mBeanServer = ManagementFactory.getPlatformMBeanServer();
	}

	@Test
	public void testRegisterMBean() throws Exception {
		MessageBusManager messageBusManager = new MessageBusManager();

		messageBusManager.setMessageBus(_messageBus);

		ObjectName objectName = new ObjectName(
			"com.liferay.portal.messaging:classification=message_bus," +
				"name=MessageBusManager");

		_mBeanServer.registerMBean(messageBusManager, objectName);

		Assert.assertTrue(_mBeanServer.isRegistered(objectName));
	}

	private MBeanServer _mBeanServer;

	@Mock
	private MessageBus _messageBus;

}