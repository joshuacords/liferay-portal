/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.flash;

import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Tomas Polesovsky
 */
public class FlashMagicBytesUtilTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testCheckGZIPFlash() throws IOException {
		test(_CWS, true);
	}

	@Test
	public void testCheckLZMAFlash() throws IOException {
		test(_ZWS, true);
	}

	@Test
	public void testCheckUncompressedFlash() throws IOException {
		test(_FWS, true);
	}

	@Test
	public void testNoFlash() throws IOException {
		for (int i = 0; i < 5; i++) {
			test(new byte[i], false);
		}
	}

	protected void test(byte[] bytes, boolean expectFlash) throws IOException {
		ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);

		FlashMagicBytesUtil.Result result = FlashMagicBytesUtil.check(
			inputStream);

		Assert.assertEquals(expectFlash, result.isFlash());

		InputStream returnedInputStream = result.getInputStream();

		byte[] buffer = new byte[4096];

		int length = returnedInputStream.read(buffer);

		if (length == _EOF) {
			Assert.assertEquals(Arrays.toString(bytes), 0, bytes.length);

			return;
		}

		Assert.assertEquals(bytes.length, length);
	}

	private static final byte[] _CWS = {0x43, 0x57, 0x53};

	private static final int _EOF = -1;

	private static final byte[] _FWS = {0x46, 0x57, 0x53};

	private static final byte[] _ZWS = {0x5a, 0x57, 0x53};

}