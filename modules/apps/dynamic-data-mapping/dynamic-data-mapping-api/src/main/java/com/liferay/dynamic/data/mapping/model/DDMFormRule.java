/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.model;

import com.liferay.petra.lang.HashUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author Leonardo Barros
 */
public class DDMFormRule implements Serializable {

	public DDMFormRule() {
	}

	public DDMFormRule(DDMFormRule ddmFormRule) {
		_condition = ddmFormRule._condition;
		_actions = new ArrayList<>(ddmFormRule._actions);
		_enabled = ddmFormRule._enabled;
	}

	public DDMFormRule(String condition, List<String> actions) {
		_condition = condition;
		_actions = actions;
	}

	public DDMFormRule(String condition, String... actions) {
		this(condition, Arrays.asList(actions));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DDMFormRule)) {
			return false;
		}

		DDMFormRule ddmFormRule = (DDMFormRule)obj;

		if (Objects.equals(_actions, ddmFormRule._actions) &&
			Objects.equals(_condition, ddmFormRule._condition) &&
			Objects.equals(_enabled, ddmFormRule._enabled)) {

			return true;
		}

		return false;
	}

	public List<String> getActions() {
		return _actions;
	}

	public String getCondition() {
		return _condition;
	}

	@Override
	public int hashCode() {
		int hash = HashUtil.hash(0, _actions);

		hash = HashUtil.hash(hash, _condition);

		return HashUtil.hash(hash, _enabled);
	}

	public boolean isEnabled() {
		return _enabled;
	}

	public void setActions(List<String> actions) {
		_actions = actions;
	}

	public void setCondition(String condition) {
		_condition = condition;
	}

	public void setEnabled(boolean enabled) {
		_enabled = enabled;
	}

	private List<String> _actions = new ArrayList<>();
	private String _condition;
	private boolean _enabled = true;

}