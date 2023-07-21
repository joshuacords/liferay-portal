/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.messaging.internal.jmx;

import com.liferay.portal.kernel.messaging.Destination;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Michael C. Han
 */
@PowerMockIgnore("javax.management.*")
@RunWith(PowerMockRunner.class)
public class DestinationStatisticsManagerTest {

	@Before
	public void setUp() throws Exception {
		_mBeanServer = ManagementFactory.getPlatformMBeanServer();
	}

	@Test
	public void testRegisterMBean() throws Exception {
		PowerMockito.when(
			_destination.getName()
		).thenReturn(
			"test"
		);

		ObjectName objectName = new ObjectName(
			"com.liferay.portal.messaging:classification=" +
				"messaging_destination,name=MessagingDestinationStatistics-" +
					_destination.getName());

		_mBeanServer.registerMBean(
			new DestinationStatisticsManager(_destination), objectName);

		Assert.assertTrue(_mBeanServer.isRegistered(objectName));
	}

	@Mock
	private Destination _destination;

	private MBeanServer _mBeanServer;

}