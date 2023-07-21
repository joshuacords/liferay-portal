/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.nio.intraband;

import com.liferay.petra.executor.PortalExecutorManager;
import com.liferay.portal.kernel.test.CaptureHandler;
import com.liferay.portal.kernel.test.JDKLoggerTestUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.CodeCoverageAssertor;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.test.rule.LiferayUnitTestRule;
import com.liferay.registry.BasicRegistryImpl;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Shuyang Zhou
 */
public class BaseAsyncDatagramReceiveHandlerTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			CodeCoverageAssertor.INSTANCE, LiferayUnitTestRule.INSTANCE);

	@Before
	public void setUp() {
		RegistryUtil.setRegistry(new BasicRegistryImpl());

		Registry registry = RegistryUtil.getRegistry();

		registry.registerService(
			PortalExecutorManager.class,
			(PortalExecutorManager)ProxyUtil.newProxyInstance(
				BaseAsyncDatagramReceiveHandlerTest.class.getClassLoader(),
				new Class<?>[] {PortalExecutorManager.class},
				new PortalExecutorManagerInvocationHandler()));
	}

	@Test
	public void testErrorDispatch() {
		try (CaptureHandler captureHandler =
				JDKLoggerTestUtil.configureJDKLogger(
					BaseAsyncDatagramReceiveHandler.class.getName(),
					Level.SEVERE)) {

			List<LogRecord> logRecords = captureHandler.getLogRecords();

			ErrorAsyncDatagramReceiveHandler errorAsyncDatagramReceiveHandler =
				new ErrorAsyncDatagramReceiveHandler();

			errorAsyncDatagramReceiveHandler.receive(null, null);

			Assert.assertEquals(logRecords.toString(), 1, logRecords.size());

			LogRecord logRecord = logRecords.get(0);

			Assert.assertEquals("Unable to dispatch", logRecord.getMessage());

			Throwable throwable = logRecord.getThrown();

			Assert.assertEquals(Exception.class, throwable.getClass());
		}
	}

	@Test
	public void testNormalDispatch() {
		DummyAsyncDatagramReceiveHandler dummyAsyncDatagramReceiveHandler =
			new DummyAsyncDatagramReceiveHandler();

		dummyAsyncDatagramReceiveHandler.receive(null, null);

		Assert.assertTrue(dummyAsyncDatagramReceiveHandler._received);
	}

	private static class DummyAsyncDatagramReceiveHandler
		extends BaseAsyncDatagramReceiveHandler {

		@Override
		protected void doReceive(
			RegistrationReference registrationReference, Datagram datagram) {

			_received = true;
		}

		private boolean _received;

	}

	private static class ErrorAsyncDatagramReceiveHandler
		extends BaseAsyncDatagramReceiveHandler {

		@Override
		protected void doReceive(
				RegistrationReference registrationReference, Datagram datagram)
			throws Exception {

			throw new Exception();
		}

	}

}