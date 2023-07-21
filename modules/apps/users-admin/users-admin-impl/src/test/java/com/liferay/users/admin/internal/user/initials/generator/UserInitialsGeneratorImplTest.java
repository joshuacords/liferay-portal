/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.users.admin.internal.user.initials.generator;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.test.rule.LiferayUnitTestRule;
import com.liferay.users.admin.kernel.util.UserInitialsGenerator;

import java.util.Locale;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.Matchers;
import org.mockito.Mockito;

/**
 * @author Drew Brokke
 */
public class UserInitialsGeneratorImplTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testFirstLast() throws Exception {
		_setUpLanguageUtil("first-name,last-name");

		Assert.assertEquals(
			"FL",
			_userInitialsGenerator.getInitials(
				_LOCALE, _FIRST_NAME, _MIDDLE_NAME, _LAST_NAME));
		Assert.assertEquals(
			"F",
			_userInitialsGenerator.getInitials(
				_LOCALE, _FIRST_NAME, _MIDDLE_NAME, null));
		Assert.assertEquals(
			"L",
			_userInitialsGenerator.getInitials(
				_LOCALE, null, _MIDDLE_NAME, _LAST_NAME));
		Assert.assertEquals(
			StringPool.BLANK,
			_userInitialsGenerator.getInitials(
				_LOCALE, null, _MIDDLE_NAME, null));
	}

	@Test
	public void testFirstMiddleLast() throws Exception {
		_setUpLanguageUtil("first-name,middle-name,last-name");

		Assert.assertEquals(
			"FM",
			_userInitialsGenerator.getInitials(
				_LOCALE, _FIRST_NAME, _MIDDLE_NAME, _LAST_NAME));
		Assert.assertEquals(
			"FM",
			_userInitialsGenerator.getInitials(
				_LOCALE, _FIRST_NAME, _MIDDLE_NAME, null));
		Assert.assertEquals(
			"ML",
			_userInitialsGenerator.getInitials(
				_LOCALE, null, _MIDDLE_NAME, _LAST_NAME));
		Assert.assertEquals(
			"FL",
			_userInitialsGenerator.getInitials(
				_LOCALE, _FIRST_NAME, null, _LAST_NAME));
		Assert.assertEquals(
			"F",
			_userInitialsGenerator.getInitials(
				_LOCALE, _FIRST_NAME, null, null));
		Assert.assertEquals(
			"M",
			_userInitialsGenerator.getInitials(
				_LOCALE, null, _MIDDLE_NAME, null));
		Assert.assertEquals(
			"L",
			_userInitialsGenerator.getInitials(
				_LOCALE, null, null, _LAST_NAME));
	}

	@Test
	public void testLastFirst() throws Exception {
		_setUpLanguageUtil("last-name,first-name");

		Assert.assertEquals(
			"LF",
			_userInitialsGenerator.getInitials(
				_LOCALE, _FIRST_NAME, _MIDDLE_NAME, _LAST_NAME));
		Assert.assertEquals(
			"F",
			_userInitialsGenerator.getInitials(
				_LOCALE, _FIRST_NAME, _MIDDLE_NAME, null));
		Assert.assertEquals(
			"L",
			_userInitialsGenerator.getInitials(
				_LOCALE, null, _MIDDLE_NAME, _LAST_NAME));
		Assert.assertEquals(
			StringPool.BLANK,
			_userInitialsGenerator.getInitials(
				_LOCALE, null, _MIDDLE_NAME, null));
	}

	@Test
	public void testLastFirstMiddle() throws Exception {
		_setUpLanguageUtil("last-name,first-name,middle-name");

		Assert.assertEquals(
			"LF",
			_userInitialsGenerator.getInitials(
				_LOCALE, _FIRST_NAME, _MIDDLE_NAME, _LAST_NAME));
		Assert.assertEquals(
			"FM",
			_userInitialsGenerator.getInitials(
				_LOCALE, _FIRST_NAME, _MIDDLE_NAME, null));
		Assert.assertEquals(
			"LM",
			_userInitialsGenerator.getInitials(
				_LOCALE, null, _MIDDLE_NAME, _LAST_NAME));
		Assert.assertEquals(
			"LF",
			_userInitialsGenerator.getInitials(
				_LOCALE, _FIRST_NAME, null, _LAST_NAME));
		Assert.assertEquals(
			"F",
			_userInitialsGenerator.getInitials(
				_LOCALE, _FIRST_NAME, null, null));
		Assert.assertEquals(
			"M",
			_userInitialsGenerator.getInitials(
				_LOCALE, null, _MIDDLE_NAME, null));
		Assert.assertEquals(
			"L",
			_userInitialsGenerator.getInitials(
				_LOCALE, null, null, _LAST_NAME));
	}

	@Test
	public void testNoPropertiesReturnedUsesDefaultValues() throws Exception {
		_setUpLanguageUtil(StringPool.BLANK);

		Assert.assertEquals(
			"FL",
			_userInitialsGenerator.getInitials(
				_LOCALE, _FIRST_NAME, _MIDDLE_NAME, _LAST_NAME));
		Assert.assertEquals(
			"F",
			_userInitialsGenerator.getInitials(
				_LOCALE, _FIRST_NAME, _MIDDLE_NAME, null));
		Assert.assertEquals(
			"L",
			_userInitialsGenerator.getInitials(
				_LOCALE, null, _MIDDLE_NAME, _LAST_NAME));
		Assert.assertEquals(
			StringPool.BLANK,
			_userInitialsGenerator.getInitials(
				_LOCALE, null, _MIDDLE_NAME, null));
	}

	private void _setUpLanguageUtil(String returnValue) throws Exception {
		Language language = Mockito.mock(Language.class);

		Mockito.doReturn(
			returnValue
		).when(
			language
		).get(
			Matchers.any(Locale.class), Matchers.anyString(),
			Matchers.anyString()
		);

		LanguageUtil languageUtil = new LanguageUtil();

		languageUtil.setLanguage(language);
	}

	private static final String _FIRST_NAME = "First";

	private static final String _LAST_NAME = "Last";

	private static final Locale _LOCALE = LocaleUtil.ENGLISH;

	private static final String _MIDDLE_NAME = "Middle";

	private final UserInitialsGenerator _userInitialsGenerator =
		new UserInitialsGeneratorImpl();

}