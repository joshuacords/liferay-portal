/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.nio.intraband.welder;

import com.liferay.portal.kernel.nio.intraband.Intraband;
import com.liferay.portal.kernel.nio.intraband.RegistrationReference;
import com.liferay.portal.kernel.nio.intraband.welder.fifo.FIFOWelder;
import com.liferay.portal.kernel.nio.intraband.welder.socket.SocketWelder;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.CodeCoverageAssertor;
import com.liferay.portal.kernel.test.rule.NewEnv;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.test.rule.AdviseWith;
import com.liferay.portal.test.rule.AspectJNewEnvTestRule;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Shuyang Zhou
 */
@NewEnv(type = NewEnv.Type.CLASSLOADER)
public class WelderFactoryUtilTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			AspectJNewEnvTestRule.INSTANCE, CodeCoverageAssertor.INSTANCE,
			LiferayUnitTestRule.INSTANCE);

	@NewEnv(type = NewEnv.Type.NONE)
	@Test
	public void testConstructor() {
		new WelderFactoryUtil();
	}

	@Test
	public void testCreateWelder() {
		System.setProperty(
			PropsKeys.INTRABAND_WELDER_IMPL, MockWelder.class.getName());

		try {
			Welder welder = WelderFactoryUtil.createWelder();

			Assert.assertNotNull(welder);
			Assert.assertSame(MockWelder.class, welder.getClass());
		}
		finally {
			System.clearProperty(PropsKeys.INTRABAND_WELDER_IMPL);
		}
	}

	@Test
	public void testCreateWelderFailed() {
		System.setProperty(
			PropsKeys.INTRABAND_WELDER_IMPL, PrivateMockWelder.class.getName());

		try {
			WelderFactoryUtil.createWelder();

			Assert.fail();
		}
		catch (RuntimeException runtimeException) {
			Assert.assertTrue(
				runtimeException.getCause() instanceof IllegalAccessException);
		}
		finally {
			System.clearProperty(PropsKeys.INTRABAND_WELDER_IMPL);
		}
	}

	@Test
	public void testGetWelderClassClassNotFound() {
		System.setProperty(PropsKeys.INTRABAND_WELDER_IMPL, "NoSuchClass");

		try {
			WelderFactoryUtil.getWelderClass();

			Assert.fail();
		}
		catch (RuntimeException runtimeException) {
			Assert.assertTrue(
				runtimeException.getCause() instanceof ClassNotFoundException);
		}
		finally {
			System.clearProperty(PropsKeys.INTRABAND_WELDER_IMPL);
		}
	}

	@Test
	public void testGetWelderClassCustomizedImpl() {
		System.setProperty(
			PropsKeys.INTRABAND_WELDER_IMPL, MockWelder.class.getName());

		try {
			Assert.assertSame(
				MockWelder.class, WelderFactoryUtil.getWelderClass());
		}
		finally {
			System.clearProperty(PropsKeys.INTRABAND_WELDER_IMPL);
		}
	}

	@AdviseWith(adviceClasses = {FIFOUtilAdvice.class, OSDetectorAdvice.class})
	@Test
	public void testGetWelderClassOnNonwindowsWithFIFO() {
		FIFOUtilAdvice._fifoSupported = true;
		OSDetectorAdvice._windows = false;

		Assert.assertSame(FIFOWelder.class, WelderFactoryUtil.getWelderClass());
	}

	@AdviseWith(adviceClasses = {FIFOUtilAdvice.class, OSDetectorAdvice.class})
	@Test
	public void testGetWelderClassOnnonWindowsWithoutFIFO() {
		FIFOUtilAdvice._fifoSupported = false;
		OSDetectorAdvice._windows = false;

		Assert.assertSame(
			SocketWelder.class, WelderFactoryUtil.getWelderClass());
	}

	@AdviseWith(adviceClasses = OSDetectorAdvice.class)
	@Test
	public void testGetWelderClassOnWindows() {
		OSDetectorAdvice._windows = true;

		Assert.assertSame(
			SocketWelder.class, WelderFactoryUtil.getWelderClass());
	}

	@Aspect
	public static class FIFOUtilAdvice {

		@Around(
			"execution(public static boolean com.liferay.portal.kernel.nio." +
				"intraband.welder.fifo.FIFOUtil.isFIFOSupported())"
		)
		public boolean isFIFOSupported() {
			return _fifoSupported;
		}

		private static boolean _fifoSupported;

	}

	@Aspect
	public static class OSDetectorAdvice {

		@Around(
			"execution(public static boolean com.liferay.portal.kernel.util." +
				"OSDetector.isWindows())"
		)
		public boolean isWindows() {
			return _windows;
		}

		private static boolean _windows;

	}

	protected static class MockWelder implements Welder {

		@Override
		public void destroy() {
		}

		@Override
		public RegistrationReference weld(Intraband intraband) {
			return null;
		}

	}

	private static class PrivateMockWelder implements Welder {

		@Override
		public void destroy() {
		}

		@Override
		public RegistrationReference weld(Intraband intraband) {
			return null;
		}

	}

}