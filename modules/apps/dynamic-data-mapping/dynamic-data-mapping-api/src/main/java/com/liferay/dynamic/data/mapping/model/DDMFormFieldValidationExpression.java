/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.model;

import com.liferay.petra.lang.HashUtil;

import java.util.Objects;

/**
 * @author Leonardo Barros
 */
public class DDMFormFieldValidationExpression {

	public DDMFormFieldValidationExpression() {
	}

	public DDMFormFieldValidationExpression(
		DDMFormFieldValidationExpression ddmFormFieldValidationExpression) {

		_name = ddmFormFieldValidationExpression._name;
		_value = ddmFormFieldValidationExpression._value;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DDMFormFieldValidationExpression)) {
			return false;
		}

		DDMFormFieldValidationExpression ddmFormFieldValidationExpression =
			(DDMFormFieldValidationExpression)obj;

		if (Objects.equals(_name, ddmFormFieldValidationExpression._name)) {
			return true;
		}

		return false;
	}

	public String getName() {
		return _name;
	}

	public String getValue() {
		return _value;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, _name);
	}

	public void setName(String name) {
		_name = name;
	}

	public void setValue(String value) {
		_value = value;
	}

	private String _name;
	private String _value;

}