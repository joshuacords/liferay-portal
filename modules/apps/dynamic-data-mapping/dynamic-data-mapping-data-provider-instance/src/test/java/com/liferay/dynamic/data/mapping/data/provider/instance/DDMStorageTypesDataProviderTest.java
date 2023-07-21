/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.data.provider.instance;

import com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderRequest;
import com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderResponse;
import com.liferay.dynamic.data.mapping.storage.DDMStorageAdapterTracker;
import com.liferay.portal.kernel.util.KeyValuePair;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import org.powermock.api.mockito.PowerMockito;

/**
 * @author Leonardo Barros
 */
@RunWith(MockitoJUnitRunner.class)
public class DDMStorageTypesDataProviderTest extends PowerMockito {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() {
		_ddmStorageTypesDataProvider = new DDMStorageTypesDataProvider();

		_ddmStorageTypesDataProvider.ddmStorageAdapterTracker =
			_ddmStorageAdapterTracker;
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testGetSettings() {
		_ddmStorageTypesDataProvider.getSettings();
	}

	@Test
	public void testMultipleStorageAdapter() throws Exception {
		Set<String> expectedSet = new TreeSet<String>() {
			{
				add("json");
				add("txt");
				add("xml");
			}
		};

		_testStorageTypes(expectedSet);
	}

	@Test
	public void testSingleStorageAdapter() throws Exception {
		Set<String> expectedSet = new TreeSet<String>() {
			{
				add("json");
			}
		};

		_testStorageTypes(expectedSet);
	}

	private void _testStorageTypes(Set<String> expectedSet) throws Exception {
		when(
			_ddmStorageAdapterTracker.getDDMStorageAdapterTypes()
		).thenReturn(
			expectedSet
		);

		List<KeyValuePair> keyValuePairs = new ArrayList<>();

		Stream<String> stream = expectedSet.stream();

		stream.map(
			type -> new KeyValuePair(type, type)
		).forEach(
			keyValuePairs::add
		);

		DDMDataProviderRequest.Builder builder =
			DDMDataProviderRequest.Builder.newBuilder();

		DDMDataProviderResponse ddmDataProviderResponse =
			_ddmStorageTypesDataProvider.getData(builder.build());

		Assert.assertTrue(ddmDataProviderResponse.hasOutput("Default-Output"));

		Optional<List<KeyValuePair>> optional =
			ddmDataProviderResponse.getOutputOptional(
				"Default-Output", List.class);

		Assert.assertTrue(optional.isPresent());

		Assert.assertEquals(keyValuePairs, optional.get());
	}

	@Mock
	private DDMStorageAdapterTracker _ddmStorageAdapterTracker;

	private DDMStorageTypesDataProvider _ddmStorageTypesDataProvider;

}