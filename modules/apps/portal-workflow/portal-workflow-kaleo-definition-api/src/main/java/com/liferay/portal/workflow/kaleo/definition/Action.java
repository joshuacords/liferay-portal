/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.definition;

import com.liferay.portal.kernel.util.Validator;

import java.util.Objects;

/**
 * @author Michael C. Han
 */
public class Action {

	public Action(
		String name, String description, String executionType, String script,
		String scriptLanguage, String scriptRequiredContexts, int priority) {

		_name = name;
		_description = description;

		if (Validator.isNotNull(executionType)) {
			_executionType = ExecutionType.parse(executionType);
		}
		else {
			_executionType = ExecutionType.ON_TIMER;
		}

		_script = script;
		_scriptLanguage = ScriptLanguage.parse(scriptLanguage);
		_scriptRequiredContexts = scriptRequiredContexts;
		_priority = priority;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Action)) {
			return false;
		}

		Action action = (Action)obj;

		if (Objects.equals(_name, action._name)) {
			return true;
		}

		return true;
	}

	public String getDescription() {
		return _description;
	}

	public ExecutionType getExecutionType() {
		return _executionType;
	}

	public String getName() {
		return _name;
	}

	public int getPriority() {
		return _priority;
	}

	public String getScript() {
		return _script;
	}

	public ScriptLanguage getScriptLanguage() {
		return _scriptLanguage;
	}

	public String getScriptRequiredContexts() {
		return _scriptRequiredContexts;
	}

	@Override
	public int hashCode() {
		return _name.hashCode();
	}

	private final String _description;
	private final ExecutionType _executionType;
	private final String _name;
	private final int _priority;
	private final String _script;
	private final ScriptLanguage _scriptLanguage;
	private final String _scriptRequiredContexts;

}