/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.util.comparator;

import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.PortletCategory;
import com.liferay.portal.kernel.test.util.PropsTestUtil;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.Collections;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Matchers;
import org.mockito.Mock;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Eduardo García
 */
@PrepareForTest(LanguageUtil.class)
@RunWith(PowerMockRunner.class)
public class PortletCategoryComparatorTest extends PowerMockito {

	@Before
	public void setUp() {
		PropsTestUtil.setProps(Collections.emptyMap());

		setUpLanguageUtil();
	}

	@Test
	public void testCompareLocalized() {
		PortletCategory portletCategory1 = new PortletCategory("area");
		PortletCategory portletCategory2 = new PortletCategory("zone");

		PortletCategoryComparator portletCategoryComparator =
			new PortletCategoryComparator(LocaleUtil.SPAIN);

		int value = portletCategoryComparator.compare(
			portletCategory1, portletCategory2);

		Assert.assertTrue(value < 0);
	}

	protected void setUpLanguageUtil() {
		LanguageUtil languageUtil = new LanguageUtil();

		languageUtil.setLanguage(_language);

		whenLanguageGet(LocaleUtil.SPAIN, "area", "Área");
		whenLanguageGet(LocaleUtil.SPAIN, "zone", "Zona");
	}

	protected void whenLanguageGet(Locale locale, String key, String value) {
		when(
			_language.get(Matchers.eq(locale), Matchers.eq(key))
		).thenReturn(
			value
		);
	}

	@Mock
	private Language _language;

}