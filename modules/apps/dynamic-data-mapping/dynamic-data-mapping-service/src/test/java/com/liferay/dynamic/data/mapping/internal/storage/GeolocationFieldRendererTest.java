/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.internal.storage;

import com.liferay.dynamic.data.mapping.storage.Field;
import com.liferay.dynamic.data.mapping.storage.FieldRenderer;
import com.liferay.portal.json.JSONFactoryImpl;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.JavaDetector;
import com.liferay.portal.kernel.util.LocaleUtil;

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
 * @author Adolfo Pérez
 */
@PrepareForTest(LanguageUtil.class)
@RunWith(PowerMockRunner.class)
public class GeolocationFieldRendererTest extends PowerMockito {

	@Before
	public void setUp() {
		setUpLanguageUtil();
	}

	@Test
	public void testRenderedValuesFollowLocaleConventions() {
		FieldRenderer fieldRenderer = new GeolocationFieldRenderer(
			new JSONFactoryImpl(), _language);

		if (JavaDetector.isJDK8()) {
			Assert.assertEquals(
				"Latitud: 9,877, Longitud: 1,234",
				fieldRenderer.render(createField(), LocaleUtil.SPAIN));
		}
		else {
			Assert.assertEquals(
				"Latitud: 9,876, Longitud: 1,234",
				fieldRenderer.render(createField(), LocaleUtil.SPAIN));
		}
	}

	@Test
	public void testRenderedValuesShouldHave3DecimalPlaces() {
		FieldRenderer fieldRenderer = new GeolocationFieldRenderer(
			new JSONFactoryImpl(), _language);

		if (JavaDetector.isJDK8()) {
			Assert.assertEquals(
				"Latitude: 9.877, Longitude: 1.234",
				fieldRenderer.render(createField(), LocaleUtil.US));
		}
		else {
			Assert.assertEquals(
				"Latitude: 9.876, Longitude: 1.234",
				fieldRenderer.render(createField(), LocaleUtil.US));
		}
	}

	protected Field createField() {
		return new Field("field", "{latitude: 9.8765, longitude: 1.2345}");
	}

	protected void setUpLanguageUtil() {
		whenLanguageGet(LocaleUtil.SPAIN, "latitude", "Latitud");
		whenLanguageGet(LocaleUtil.SPAIN, "longitude", "Longitud");
		whenLanguageGet(LocaleUtil.US, "latitude", "Latitude");
		whenLanguageGet(LocaleUtil.US, "longitude", "Longitude");

		LanguageUtil languageUtil = new LanguageUtil();

		languageUtil.setLanguage(_language);
	}

	protected void whenLanguageGet(
		Locale locale, String key, String returnValue) {

		when(
			_language.get(Matchers.eq(locale), Matchers.eq(key))
		).thenReturn(
			returnValue
		);
	}

	@Mock
	private Language _language;

}