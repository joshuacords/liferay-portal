/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.settings;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.powermock.api.mockito.PowerMockito;

/**
 * @author Iván Zaera
 */
public class LocationVariableResolverTest extends PowerMockito {

	@Before
	public void setUp() throws Exception {
		_mockResourceManager = new MockResourceManager(
			"En un lugar de la Mancha...");

		_mockSettingsLocatorHelper = mock(SettingsLocatorHelper.class);

		_locationVariableResolver = new LocationVariableResolver(
			_mockResourceManager, _mockSettingsLocatorHelper);
	}

	@Test
	public void testIsLocationVariableWithNonvariable() {
		Assert.assertFalse(
			_locationVariableResolver.isLocationVariable(
				"this is obviously not a location variable"));
	}

	@Test
	public void testIsLocationVariableWithVariable() {
		Assert.assertTrue(
			_locationVariableResolver.isLocationVariable(
				"${protocol:location}"));
	}

	@Test
	public void testResolveVariableWithFile() throws IOException {
		String expectedValue = "En un lugar de la Mancha...";

		File file = File.createTempFile("testResolveVariableForFile", "txt");

		try (OutputStream outputStream = new FileOutputStream(file)) {
			outputStream.write(expectedValue.getBytes());

			outputStream.flush();

			String value = _locationVariableResolver.resolve(
				"${file://" + file.getAbsolutePath() + "}");

			Assert.assertEquals(expectedValue, value);
		}
		finally {
			file.delete();
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testResolveVariableWithInvalidFile() {
		_locationVariableResolver.resolve(
			"${file:bad_file_uri_without_slashes.txt}");
	}

	@Test
	public void testResolveVariableWithResource() {
		String value = _locationVariableResolver.resolve(
			"${resource:template.ftl}");

		Assert.assertEquals(_mockResourceManager.getContent(), value);

		List<String> requestedLocations =
			_mockResourceManager.getRequestedLocations();

		Assert.assertEquals(
			requestedLocations.toString(), 1, requestedLocations.size());
		Assert.assertEquals("template.ftl", requestedLocations.get(0));
	}

	@Test
	public void testResolveVariableWithServerProperty() {
		final String expectedValue = "test@liferay.com";

		MemorySettings memorySettings = new MemorySettings();

		memorySettings.setValue("admin.email.from.address", expectedValue);

		when(
			_mockSettingsLocatorHelper.getServerSettings("com.liferay.portal")
		).thenReturn(
			memorySettings
		);

		Assert.assertEquals(
			expectedValue,
			_locationVariableResolver.resolve(
				"${server-property://com.liferay.portal" +
					"/admin.email.from.address}"));
	}

	private LocationVariableResolver _locationVariableResolver;
	private MockResourceManager _mockResourceManager;
	private SettingsLocatorHelper _mockSettingsLocatorHelper;

}