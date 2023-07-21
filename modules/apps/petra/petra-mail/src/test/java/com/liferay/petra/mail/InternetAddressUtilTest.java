/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.petra.mail;

import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.Arrays;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Miguel Pastor
 * @see    com.liferay.util.mail.InternetAddressUtilTest
 */
public class InternetAddressUtilTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() throws AddressException {
		_internetAddresses = buildInternetAddresses(11);
	}

	@Test
	public void testContainsNullEmailAddress() {
		Assert.assertFalse(
			InternetAddressUtil.contains(_internetAddresses, null));
	}

	@Test
	public void testContainsValidEmailAddress() {
		Assert.assertTrue(
			InternetAddressUtil.contains(_internetAddresses, "1@liferay.com"));
	}

	@Test
	public void testInvalidEmailAddress() {
		Assert.assertFalse(InternetAddressUtil.isValid("miguel.pastor"));
	}

	@Test
	public void testInvalidEmailAddressWithAt() {
		Assert.assertFalse(InternetAddressUtil.isValid("miguel.pastor@"));
	}

	@Test
	public void testNotContainsValidEmailAddress() {
		Assert.assertFalse(
			InternetAddressUtil.contains(_internetAddresses, "12@liferay.com"));
	}

	@Test
	public void testRemoveExistingEmailAddress() {
		InternetAddress[] internetAddresses = InternetAddressUtil.removeEntry(
			_internetAddresses, "1@liferay.com");

		Assert.assertEquals(
			Arrays.toString(internetAddresses), 10, internetAddresses.length);
	}

	@Test
	public void testRemoveNonexistentEmailAddress() {
		InternetAddress[] restOfInternetAddresses =
			InternetAddressUtil.removeEntry(
				_internetAddresses, "12@liferay.com");

		Assert.assertEquals(
			Arrays.toString(restOfInternetAddresses), 11,
			restOfInternetAddresses.length);
	}

	@Test
	public void testValidEmailAddress() {
		Assert.assertTrue(
			InternetAddressUtil.isValid("miguel.pastor@liferay.com"));
	}

	protected InternetAddress[] buildInternetAddresses(int size)
		throws AddressException {

		InternetAddress[] internetAddresses = new InternetAddress[size];

		for (int i = 0; i < size; i++) {
			internetAddresses[i] = new InternetAddress(
				String.valueOf(i) + _INTERNET_ADDRESS_SUFFIX);
		}

		return internetAddresses;
	}

	private static final String _INTERNET_ADDRESS_SUFFIX = "@liferay.com";

	private InternetAddress[] _internetAddresses;

}