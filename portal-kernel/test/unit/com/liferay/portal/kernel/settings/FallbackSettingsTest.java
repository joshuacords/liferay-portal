/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.settings;

import org.junit.Assert;
import org.junit.Test;

import org.mockito.InOrder;
import org.mockito.Mockito;

import org.powermock.api.mockito.PowerMockito;

/**
 * @author Iván Zaera
 */
public class FallbackSettingsTest extends PowerMockito {

	public FallbackSettingsTest() {
		_settings = mock(Settings.class);

		_fallbackKeys = new FallbackKeys();

		_fallbackKeys.add("key1", "key2", "key3");
		_fallbackKeys.add("key2", "key7");
		_fallbackKeys.add("key3", "key5");

		_fallbackSettings = new FallbackSettings(_settings, _fallbackKeys);
	}

	@Test
	public void testGetValuesWhenConfigured() {
		String[] defaultValues = {"default"};

		String[] mockValues = {"value"};

		when(
			_settings.getValues("key2", null)
		).thenReturn(
			mockValues
		);

		String[] values = _fallbackSettings.getValues("key1", defaultValues);

		Assert.assertArrayEquals(mockValues, values);

		verifyGetValues("key1", "key2");
	}

	@Test
	public void testGetValuesWhenUnconfigured() {
		String[] defaultValues = {"default"};

		String[] values = _fallbackSettings.getValues("key1", defaultValues);

		Assert.assertArrayEquals(defaultValues, values);

		verifyGetValues("key1", "key2", "key3");
	}

	@Test
	public void testGetValueWhenConfigured() {
		when(
			_settings.getValue("key2", null)
		).thenReturn(
			"value"
		);

		String value = _fallbackSettings.getValue("key1", "default");

		Assert.assertEquals("value", value);

		verifyGetValue("key1", "key2");
	}

	@Test
	public void testGetValueWhenUnconfigured() {
		String value = _fallbackSettings.getValue("key1", "default");

		Assert.assertEquals("default", value);

		verifyGetValue("key1", "key2", "key3");
	}

	protected void verifyGetValue(String... keys) {
		InOrder inOrder = Mockito.inOrder(_settings);

		for (String key : keys) {
			inOrder.verify(_settings);

			_settings.getValue(key, null);
		}
	}

	protected void verifyGetValues(String... keys) {
		InOrder inOrder = Mockito.inOrder(_settings);

		for (String key : keys) {
			inOrder.verify(_settings);

			_settings.getValues(key, null);
		}
	}

	private final FallbackKeys _fallbackKeys;
	private final FallbackSettings _fallbackSettings;
	private final Settings _settings;

}