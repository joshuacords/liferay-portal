/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.builder.internal.converter.model.action;

import com.liferay.dynamic.data.mapping.form.builder.internal.converter.serializer.CalculateDDMFormRuleActionSerializer;
import com.liferay.dynamic.data.mapping.form.builder.internal.converter.serializer.DDMFormRuleActionSerializer;
import com.liferay.dynamic.data.mapping.form.builder.internal.converter.serializer.DDMFormRuleSerializerContext;
import com.liferay.petra.lang.HashUtil;

import java.util.Objects;

/**
 * @author Leonardo Barros
 */
public class CalculateDDMFormRuleAction extends DefaultDDMFormRuleAction {

	public CalculateDDMFormRuleAction() {
	}

	public CalculateDDMFormRuleAction(String target, String expression) {
		super("calculate", target);

		_expression = expression;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CalculateDDMFormRuleAction)) {
			return false;
		}

		CalculateDDMFormRuleAction calculateDDMFormRuleAction =
			(CalculateDDMFormRuleAction)obj;

		if (super.equals(obj) &&
			Objects.equals(
				_expression, calculateDDMFormRuleAction._expression)) {

			return true;
		}

		return false;
	}

	public String getExpression() {
		return _expression;
	}

	@Override
	public int hashCode() {
		int hash = super.hashCode();

		return HashUtil.hash(hash, _expression);
	}

	@Override
	public String serialize(
		DDMFormRuleSerializerContext ddmFormRuleSerializerContext) {

		DDMFormRuleActionSerializer ddmFormRuleActionSerializer =
			new CalculateDDMFormRuleActionSerializer(this);

		return ddmFormRuleActionSerializer.serialize(
			ddmFormRuleSerializerContext);
	}

	public void setExpression(String expression) {
		_expression = expression;
	}

	private String _expression;

}