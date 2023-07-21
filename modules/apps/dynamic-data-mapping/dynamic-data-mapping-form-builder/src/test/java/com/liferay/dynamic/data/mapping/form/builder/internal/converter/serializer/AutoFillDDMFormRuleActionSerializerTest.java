/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.builder.internal.converter.serializer;

import com.liferay.dynamic.data.mapping.form.builder.internal.converter.model.action.AutoFillDDMFormRuleAction;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.HashMapBuilder;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Leonardo Barros
 */
@RunWith(PowerMockRunner.class)
public class AutoFillDDMFormRuleActionSerializerTest extends PowerMockito {

	@Test
	public void testSerialize() {
		_mockGetDDMDataProviderInstanceUUID();

		_mockGetInputParametersMapper("field1");

		_mockGetOutputParametersMapper("field2", "field3");

		AutoFillDDMFormRuleActionSerializer
			autoFillDDMFormRuleActionSerializer =
				new AutoFillDDMFormRuleActionSerializer(
					_autoFillDDMFormRuleAction);

		String result = autoFillDDMFormRuleActionSerializer.serialize(
			_ddmFormRuleSerializerContext);

		Assert.assertEquals(
			"call('0', 'key1=field1', 'field2=key1;field3=key2')", result);
	}

	@Test
	public void testSerializeWithEmptyInputParameter() {
		_mockGetInputParametersMapper(StringPool.BLANK);

		_mockGetOutputParametersMapper("field2", "field3");

		AutoFillDDMFormRuleActionSerializer
			autoFillDDMFormRuleActionSerializer =
				new AutoFillDDMFormRuleActionSerializer(
					_autoFillDDMFormRuleAction);

		String result = autoFillDDMFormRuleActionSerializer.serialize(
			_ddmFormRuleSerializerContext);

		Assert.assertNull(result);
	}

	@Test
	public void testSerializeWithEmptyOutputParameter() {
		_mockGetInputParametersMapper("field1");

		_mockGetOutputParametersMapper("field2", StringPool.BLANK);

		AutoFillDDMFormRuleActionSerializer
			autoFillDDMFormRuleActionSerializer =
				new AutoFillDDMFormRuleActionSerializer(
					_autoFillDDMFormRuleAction);

		String result = autoFillDDMFormRuleActionSerializer.serialize(
			_ddmFormRuleSerializerContext);

		Assert.assertNull(result);
	}

	private void _mockGetDDMDataProviderInstanceUUID() {
		when(
			_autoFillDDMFormRuleAction.getDDMDataProviderInstanceUUID()
		).thenReturn(
			"0"
		);
	}

	private void _mockGetInputParametersMapper(String fieldName) {
		when(
			_autoFillDDMFormRuleAction.getInputParametersMapper()
		).thenReturn(
			HashMapBuilder.put(
				"key1", fieldName
			).build()
		);
	}

	private void _mockGetOutputParametersMapper(
		String fieldName1, String fieldName2) {

		when(
			_autoFillDDMFormRuleAction.getOutputParametersMapper()
		).thenReturn(
			HashMapBuilder.put(
				"key1", fieldName1
			).put(
				"key2", fieldName2
			).build()
		);
	}

	@Mock
	private AutoFillDDMFormRuleAction _autoFillDDMFormRuleAction;

	@Mock
	private DDMFormRuleSerializerContext _ddmFormRuleSerializerContext;

}