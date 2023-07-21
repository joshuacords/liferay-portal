/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.expression;

import com.liferay.dynamic.data.mapping.expression.model.Expression;

import java.util.Collections;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Miguel Angelo Caldas Gallindo
 */
@ProviderType
public interface DDMExpression<T> {

	public T evaluate() throws DDMExpressionException;

	public Expression getModel();

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public default Map<String, VariableDependencies>
			getVariableDependenciesMap()
		throws DDMExpressionException {

		return Collections.emptyMap();
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public default void setBooleanVariableValue(
		String variableName, Boolean variableValue) {

		setVariable(variableName, variableValue);
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public default void setDDMExpressionFunction(
		String functionName, DDMExpressionFunction ddmExpressionFunction) {
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public default void setDoubleVariableValue(
		String variableName, Double variableValue) {

		setVariable(variableName, variableValue);
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public default void setExpressionStringVariableValue(
		String variableName, String variableValue) {

		setVariable(variableName, variableValue);
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public default void setFloatVariableValue(
		String variableName, Float variableValue) {

		setVariable(variableName, variableValue);
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public default void setIntegerVariableValue(
		String variableName, Integer variableValue) {

		setVariable(variableName, variableValue);
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public default void setLongVariableValue(
		String variableName, Long variableValue) {

		setVariable(variableName, variableValue);
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public default void setNumberVariableValue(
		String variableName, Number variableValue) {

		setVariable(variableName, variableValue);
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public default void setObjectVariableValue(
		String variableName, Object variableValue) {

		setVariable(variableName, variableValue);
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public default void setStringVariableValue(
			String variableName, String variableValue)
		throws DDMExpressionException {

		setVariable(variableName, variableValue);
	}

	public void setVariable(String name, Object value);

}