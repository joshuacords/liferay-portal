/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.expression;

import java.util.ArrayList;
import java.util.List;

/**
 * @author     Miguel Angelo Caldas Gallindo
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class VariableDependencies {

	public VariableDependencies(String variableName) {
		_variableName = variableName;
	}

	public void addAffectedVariable(String variableName) {
		_affectedVariableNames.add(variableName);
	}

	public void addRequiredVariable(String variableName) {
		_requiredVariableNames.add(variableName);
	}

	public List<String> getAffectedVariableNames() {
		return _affectedVariableNames;
	}

	public List<String> getRequiredVariableNames() {
		return _requiredVariableNames;
	}

	public String getVariableName() {
		return _variableName;
	}

	private final List<String> _affectedVariableNames = new ArrayList<>();
	private final List<String> _requiredVariableNames = new ArrayList<>();
	private final String _variableName;

}