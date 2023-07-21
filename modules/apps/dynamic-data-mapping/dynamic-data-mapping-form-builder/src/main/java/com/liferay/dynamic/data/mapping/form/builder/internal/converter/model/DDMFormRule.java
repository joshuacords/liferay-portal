/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.builder.internal.converter.model;

import com.liferay.petra.lang.HashUtil;
import com.liferay.portal.kernel.json.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Marcellus Tavares
 */
public class DDMFormRule {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DDMFormRule)) {
			return false;
		}

		DDMFormRule ddmFormRule = (DDMFormRule)obj;

		if (Objects.equals(
				_ddmFormRuleActions, ddmFormRule._ddmFormRuleActions) &&
			Objects.equals(
				_ddmFormRuleConditions, ddmFormRule._ddmFormRuleConditions) &&
			Objects.equals(_logicalOperator, ddmFormRule._logicalOperator)) {

			return true;
		}

		return false;
	}

	@JSON(name = "actions")
	public List<DDMFormRuleAction> getDDMFormRuleActions() {
		return _ddmFormRuleActions;
	}

	@JSON(name = "conditions")
	public List<DDMFormRuleCondition> getDDMFormRuleConditions() {
		return _ddmFormRuleConditions;
	}

	@JSON(name = "logical-operator")
	public String getLogicalOperator() {
		return _logicalOperator;
	}

	@Override
	public int hashCode() {
		int hash = HashUtil.hash(0, _ddmFormRuleActions);

		hash = HashUtil.hash(hash, _ddmFormRuleConditions);

		return HashUtil.hash(hash, _logicalOperator);
	}

	public void setDDMFormRuleActions(
		List<DDMFormRuleAction> ddmFormRuleActions) {

		_ddmFormRuleActions = ddmFormRuleActions;
	}

	public void setDDMFormRuleConditions(
		List<DDMFormRuleCondition> ddmFormRuleConditions) {

		_ddmFormRuleConditions = ddmFormRuleConditions;
	}

	public void setLogicalOperator(String logicalOperator) {
		_logicalOperator = logicalOperator;
	}

	private List<DDMFormRuleAction> _ddmFormRuleActions = new ArrayList<>();
	private List<DDMFormRuleCondition> _ddmFormRuleConditions =
		new ArrayList<>();
	private String _logicalOperator = "AND";

}