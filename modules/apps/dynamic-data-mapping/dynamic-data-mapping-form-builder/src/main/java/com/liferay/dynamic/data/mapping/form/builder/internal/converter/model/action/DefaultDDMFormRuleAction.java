/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.builder.internal.converter.model.action;

import com.liferay.dynamic.data.mapping.form.builder.internal.converter.model.DDMFormRuleAction;
import com.liferay.dynamic.data.mapping.form.builder.internal.converter.serializer.DDMFormRuleActionSerializer;
import com.liferay.dynamic.data.mapping.form.builder.internal.converter.serializer.DDMFormRuleSerializerContext;
import com.liferay.dynamic.data.mapping.form.builder.internal.converter.serializer.DefaultDDMFormRuleActionSerializer;
import com.liferay.petra.lang.HashUtil;

import java.util.Objects;

/**
 * @author Rafael Praxedes
 */
public class DefaultDDMFormRuleAction implements DDMFormRuleAction {

	public DefaultDDMFormRuleAction() {
	}

	public DefaultDDMFormRuleAction(String action, String target) {
		_action = action;
		_target = target;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DefaultDDMFormRuleAction)) {
			return false;
		}

		DefaultDDMFormRuleAction ddmFormRuleAction =
			(DefaultDDMFormRuleAction)obj;

		if (Objects.equals(_action, ddmFormRuleAction._action) &&
			Objects.equals(_target, ddmFormRuleAction._target)) {

			return true;
		}

		return false;
	}

	@Override
	public String getAction() {
		return _action;
	}

	public String getTarget() {
		return _target;
	}

	@Override
	public int hashCode() {
		int hash = HashUtil.hash(0, _action);

		return HashUtil.hash(hash, _target);
	}

	@Override
	public String serialize(
		DDMFormRuleSerializerContext ddmFormRuleSerializerContext) {

		DDMFormRuleActionSerializer ddmFormRuleActionSerializer =
			new DefaultDDMFormRuleActionSerializer(this);

		return ddmFormRuleActionSerializer.serialize(
			ddmFormRuleSerializerContext);
	}

	public void setAction(String action) {
		_action = action;
	}

	public void setTarget(String target) {
		_target = target;
	}

	private String _action;
	private String _target;

}