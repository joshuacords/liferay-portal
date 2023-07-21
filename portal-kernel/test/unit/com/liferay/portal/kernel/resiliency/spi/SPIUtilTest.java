/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.resiliency.spi;

import com.liferay.portal.kernel.process.local.LocalProcessLauncher;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.CodeCoverageAssertor;
import com.liferay.portal.kernel.test.rule.NewEnv;
import com.liferay.portal.kernel.test.rule.NewEnvTestRule;

import java.util.concurrent.ConcurrentMap;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Shuyang Zhou
 */
public class SPIUtilTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			CodeCoverageAssertor.INSTANCE, NewEnvTestRule.INSTANCE);

	@Test
	public void testConstructor() {
		new SPIUtil();
	}

	@Test
	public void testIsNotSPI() {
		Assert.assertFalse(SPIUtil.isSPI());

		try {
			SPIUtil.getSPI();

			Assert.fail();
		}
		catch (IllegalStateException illegalStateException) {
			Assert.assertEquals(
				"Current process is not an SPI instance",
				illegalStateException.getMessage());
		}
	}

	@NewEnv(type = NewEnv.Type.CLASSLOADER)
	@Test
	public void testIsSPI() {
		MockSPI mockSPI = new MockSPI();

		ConcurrentMap<String, Object> attributes =
			LocalProcessLauncher.ProcessContext.getAttributes();

		attributes.put(SPI.SPI_INSTANCE_PUBLICATION_KEY, mockSPI);

		Assert.assertTrue(SPIUtil.isSPI());
		Assert.assertSame(mockSPI, SPIUtil.getSPI());
	}

}