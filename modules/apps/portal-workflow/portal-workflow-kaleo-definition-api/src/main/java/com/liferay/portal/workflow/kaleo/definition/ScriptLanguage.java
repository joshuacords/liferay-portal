/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.definition;

import java.util.Objects;

/**
 * @author Michael C. Han
 */
public enum ScriptLanguage {

	BEANSHELL("beanshell"), DRL("drl"), GROOVY("groovy"), JAVA("java"),
	JAVASCRIPT("javascript"), PYTHON("python"), RUBY("ruby");

	public static ScriptLanguage parse(String value) {
		if (Objects.equals(BEANSHELL.getValue(), value)) {
			return BEANSHELL;
		}
		else if (Objects.equals(DRL.getValue(), value)) {
			return DRL;
		}
		else if (Objects.equals(GROOVY.getValue(), value)) {
			return GROOVY;
		}
		else if (Objects.equals(JAVA.getValue(), value)) {
			return JAVA;
		}
		else if (Objects.equals(JAVASCRIPT.getValue(), value)) {
			return JAVASCRIPT;
		}
		else if (Objects.equals(PYTHON.getValue(), value)) {
			return PYTHON;
		}
		else if (Objects.equals(RUBY.getValue(), value)) {
			return RUBY;
		}

		throw new IllegalArgumentException("Invalid value " + value);
	}

	public String getValue() {
		return _value;
	}

	@Override
	public String toString() {
		return _value;
	}

	private ScriptLanguage(String value) {
		_value = value;
	}

	private final String _value;

}