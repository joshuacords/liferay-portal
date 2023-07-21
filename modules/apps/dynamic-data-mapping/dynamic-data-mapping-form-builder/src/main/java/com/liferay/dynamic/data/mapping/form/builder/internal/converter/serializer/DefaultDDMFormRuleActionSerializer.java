/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.builder.internal.converter.serializer;

import com.liferay.dynamic.data.mapping.form.builder.internal.converter.model.action.DefaultDDMFormRuleAction;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Leonardo Barros
 */
public class DefaultDDMFormRuleActionSerializer
	implements DDMFormRuleActionSerializer {

	public DefaultDDMFormRuleActionSerializer(
		DefaultDDMFormRuleAction defaultDDMFormRuleAction) {

		_defaultDefaultDDMFormRuleAction = defaultDDMFormRuleAction;
	}

	@Override
	public String serialize(
		DDMFormRuleSerializerContext ddmFormRuleSerializerContext) {

		if (Validator.isNull(_defaultDefaultDDMFormRuleAction.getTarget())) {
			return null;
		}

		String functionName = _actionBooleanFunctionNameMap.get(
			_defaultDefaultDDMFormRuleAction.getAction());

		return String.format(
			_SET_BOOLEAN_PROPERTY_FORMAT, functionName,
			_defaultDefaultDDMFormRuleAction.getTarget());
	}

	private static final String _SET_BOOLEAN_PROPERTY_FORMAT = "%s('%s', true)";

	private static final Map<String, String> _actionBooleanFunctionNameMap =
		new HashMap<String, String>() {
			{
				put("enable", "setEnabled");
				put("invalidate", "setInvalid");
				put("require", "setRequired");
				put("show", "setVisible");
			}
		};

	private final DefaultDDMFormRuleAction _defaultDefaultDDMFormRuleAction;

}