/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.builder.internal.converter.serializer;

import com.liferay.dynamic.data.mapping.form.builder.internal.converter.model.action.JumpToPageDDMFormRuleAction;
import com.liferay.petra.string.StringPool;

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
public class JumpToPageDDMFormRuleActionSerializerTest extends PowerMockito {

	@Test
	public void testSerialize() {
		when(
			_jumpToPageDDMFormRuleAction.getSource()
		).thenReturn(
			"1"
		);

		when(
			_jumpToPageDDMFormRuleAction.getTarget()
		).thenReturn(
			"3"
		);

		JumpToPageDDMFormRuleActionSerializer
			jumpToPageDDMFormRuleActionSerializer =
				new JumpToPageDDMFormRuleActionSerializer(
					_jumpToPageDDMFormRuleAction);

		String result = jumpToPageDDMFormRuleActionSerializer.serialize(
			_ddmFormRuleSerializerContext);

		Assert.assertEquals("jumpPage(1, 3)", result);
	}

	@Test
	public void testSerializeWithEmptyTarget() {
		when(
			_jumpToPageDDMFormRuleAction.getTarget()
		).thenReturn(
			StringPool.BLANK
		);

		JumpToPageDDMFormRuleActionSerializer
			jumpToPageDDMFormRuleActionSerializer =
				new JumpToPageDDMFormRuleActionSerializer(
					_jumpToPageDDMFormRuleAction);

		String result = jumpToPageDDMFormRuleActionSerializer.serialize(
			_ddmFormRuleSerializerContext);

		Assert.assertNull(result);
	}

	@Mock
	private DDMFormRuleSerializerContext _ddmFormRuleSerializerContext;

	@Mock
	private JumpToPageDDMFormRuleAction _jumpToPageDDMFormRuleAction;

}