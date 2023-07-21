/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.nio.intraband;

import com.liferay.portal.kernel.nio.intraband.blocking.ExecutorIntraband;
import com.liferay.portal.kernel.nio.intraband.nonblocking.SelectorIntraband;
import com.liferay.portal.kernel.nio.intraband.welder.fifo.FIFOWelder;
import com.liferay.portal.kernel.nio.intraband.welder.socket.SocketWelder;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.CodeCoverageAssertor;
import com.liferay.portal.kernel.test.rule.NewEnv;
import com.liferay.portal.kernel.test.rule.NewEnvTestRule;
import com.liferay.portal.kernel.util.PropsKeys;

import java.io.IOException;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Shuyang Zhou
 */
@NewEnv(type = NewEnv.Type.CLASSLOADER)
public class IntrabandFactoryUtilTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			CodeCoverageAssertor.INSTANCE, NewEnvTestRule.INSTANCE);

	@Test
	public void testConstructor() {
		new IntrabandFactoryUtil();
	}

	@Test
	public void testCreateIntrabandClassNotFound() throws IOException {
		System.setProperty(PropsKeys.INTRABAND_IMPL, "NoSuchClass");

		try {
			IntrabandFactoryUtil.createIntraband();

			Assert.fail();
		}
		catch (RuntimeException runtimeException) {
			Assert.assertEquals(
				"Unable to instantiate NoSuchClass",
				runtimeException.getMessage());

			Throwable throwable = runtimeException.getCause();

			Assert.assertSame(
				ClassNotFoundException.class, throwable.getClass());
		}
		finally {
			System.clearProperty(PropsKeys.INTRABAND_IMPL);
		}
	}

	@Test
	public void testCreateIntrabandCustomizedImpl() throws Exception {
		System.setProperty(
			PropsKeys.INTRABAND_IMPL, SelectorIntraband.class.getName());

		Intraband intraband = null;

		try {
			intraband = IntrabandFactoryUtil.createIntraband();

			Assert.assertSame(SelectorIntraband.class, intraband.getClass());
		}
		finally {
			if (intraband != null) {
				intraband.close();
			}

			System.clearProperty(PropsKeys.INTRABAND_IMPL);
		}
	}

	@Test
	public void testCreateIntrabandDefaultToFIFO() throws Exception {
		System.setProperty(
			PropsKeys.INTRABAND_WELDER_IMPL, FIFOWelder.class.getName());

		Intraband intraband = null;

		try {
			intraband = IntrabandFactoryUtil.createIntraband();

			Assert.assertSame(ExecutorIntraband.class, intraband.getClass());
		}
		finally {
			if (intraband != null) {
				intraband.close();
			}

			System.clearProperty(PropsKeys.INTRABAND_WELDER_IMPL);
		}
	}

	@Test
	public void testCreateIntrabandDefaultToSocket() throws Exception {
		System.setProperty(
			PropsKeys.INTRABAND_WELDER_IMPL, SocketWelder.class.getName());

		Intraband intraband = null;

		try {
			intraband = IntrabandFactoryUtil.createIntraband();

			Assert.assertSame(SelectorIntraband.class, intraband.getClass());
		}
		finally {
			if (intraband != null) {
				intraband.close();
			}

			System.clearProperty(PropsKeys.INTRABAND_WELDER_IMPL);
		}
	}

}