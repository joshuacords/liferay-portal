/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.nio.intraband.welder;

import com.liferay.portal.kernel.nio.intraband.Intraband;
import com.liferay.portal.kernel.nio.intraband.RegistrationReference;
import com.liferay.portal.kernel.nio.intraband.test.MockRegistrationReference;
import com.liferay.portal.kernel.nio.intraband.welder.test.WelderTestUtil;
import com.liferay.portal.kernel.test.rule.CodeCoverageAssertor;

import java.io.IOException;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

/**
 * @author Shuyang Zhou
 */
public class BaseWelderTest {

	@ClassRule
	public static final CodeCoverageAssertor codeCoverageAssertor =
		CodeCoverageAssertor.INSTANCE;

	@Test
	public void testConstructor() {
		TestWelder testWelder = new TestWelder();

		Assert.assertTrue(testWelder.server);
		Assert.assertEquals(BaseWelder.State.CREATED, testWelder.state);
		Assert.assertNull(testWelder.registrationReference);
	}

	@Test
	public void testDestroty() throws IOException {
		TestWelder testWelder = new TestWelder();

		try {
			testWelder.destroy();

			Assert.fail();
		}
		catch (IllegalStateException illegalStateException) {
			Assert.assertEquals(
				"Unable to destroy a welder with state CREATED",
				illegalStateException.getMessage());
		}

		RegistrationReference registrationReference =
			new MockRegistrationReference(null);

		testWelder.registrationReference = registrationReference;

		testWelder.state = BaseWelder.State.WELDED;

		testWelder.destroy();

		Assert.assertFalse(registrationReference.isValid());
		Assert.assertTrue(testWelder._destroyed);
		Assert.assertEquals(BaseWelder.State.DESTROYED, testWelder.state);
	}

	@Test
	public void testSerialization() throws Exception {
		TestWelder testWelder = new TestWelder();

		testWelder.registrationReference = new MockRegistrationReference(null);

		TestWelder newTestWelder = WelderTestUtil.transform(testWelder);

		Assert.assertNull(newTestWelder.registrationReference);
		Assert.assertFalse(newTestWelder.server);
		Assert.assertEquals(BaseWelder.State.CREATED, newTestWelder.state);
	}

	@Test
	public void testStateEnum() {
		BaseWelder.State[] states = BaseWelder.State.values();

		Assert.assertEquals(Arrays.toString(states), 3, states.length);
		Assert.assertEquals(BaseWelder.State.CREATED, states[0]);
		Assert.assertEquals(BaseWelder.State.DESTROYED, states[1]);
		Assert.assertEquals(BaseWelder.State.WELDED, states[2]);
	}

	@Test
	public void testWeld() throws Exception {
		TestWelder testWelder = new TestWelder();

		testWelder.state = BaseWelder.State.DESTROYED;

		try {
			testWelder.weld(null);

			Assert.fail();
		}
		catch (IllegalStateException illegalStateException) {
			Assert.assertEquals(
				"Unable to weld a welder with state DESTROYED",
				illegalStateException.getMessage());
		}

		testWelder.state = BaseWelder.State.CREATED;

		RegistrationReference registrationReference = testWelder.weld(null);

		Assert.assertNull(testWelder._clientRegistrationReference);
		Assert.assertEquals(
			testWelder._serverRegistrationReference, registrationReference);
		Assert.assertEquals(BaseWelder.State.WELDED, testWelder.state);

		TestWelder newTestWelder = WelderTestUtil.transform(testWelder);

		registrationReference = newTestWelder.weld(null);

		Assert.assertEquals(
			newTestWelder._clientRegistrationReference, registrationReference);

		Assert.assertNull(newTestWelder._serverRegistrationReference);
		Assert.assertEquals(BaseWelder.State.WELDED, newTestWelder.state);
	}

	private static class TestWelder extends BaseWelder {

		@Override
		protected void doDestroy() {
			_destroyed = true;
		}

		@Override
		protected RegistrationReference weldClient(Intraband intraband) {
			_clientRegistrationReference = new MockRegistrationReference(
				intraband);

			return _clientRegistrationReference;
		}

		@Override
		protected RegistrationReference weldServer(Intraband intraband) {
			_serverRegistrationReference = new MockRegistrationReference(
				intraband);

			return _serverRegistrationReference;
		}

		private transient RegistrationReference _clientRegistrationReference;
		private transient boolean _destroyed;
		private transient RegistrationReference _serverRegistrationReference;

	}

}