/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.data.engine.rest.client.dto.v1_0;

import com.liferay.data.engine.rest.client.function.UnsafeSupplier;
import com.liferay.data.engine.rest.client.serdes.v1_0.DataDefinitionRuleSerDes;

import java.io.Serializable;

import java.util.Map;
import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Jeyvison Nascimento
 * @generated
 */
@Generated("")
public class DataDefinitionRule implements Cloneable, Serializable {

	public static DataDefinitionRule toDTO(String json) {
		return DataDefinitionRuleSerDes.toDTO(json);
	}

	public String[] getDataDefinitionFieldNames() {
		return dataDefinitionFieldNames;
	}

	public void setDataDefinitionFieldNames(String[] dataDefinitionFieldNames) {
		this.dataDefinitionFieldNames = dataDefinitionFieldNames;
	}

	public void setDataDefinitionFieldNames(
		UnsafeSupplier<String[], Exception>
			dataDefinitionFieldNamesUnsafeSupplier) {

		try {
			dataDefinitionFieldNames =
				dataDefinitionFieldNamesUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String[] dataDefinitionFieldNames;

	public Map<String, Object> getDataDefinitionRuleParameters() {
		return dataDefinitionRuleParameters;
	}

	public void setDataDefinitionRuleParameters(
		Map<String, Object> dataDefinitionRuleParameters) {

		this.dataDefinitionRuleParameters = dataDefinitionRuleParameters;
	}

	public void setDataDefinitionRuleParameters(
		UnsafeSupplier<Map<String, Object>, Exception>
			dataDefinitionRuleParametersUnsafeSupplier) {

		try {
			dataDefinitionRuleParameters =
				dataDefinitionRuleParametersUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Map<String, Object> dataDefinitionRuleParameters;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setName(UnsafeSupplier<String, Exception> nameUnsafeSupplier) {
		try {
			name = nameUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String name;

	public String getRuleType() {
		return ruleType;
	}

	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}

	public void setRuleType(
		UnsafeSupplier<String, Exception> ruleTypeUnsafeSupplier) {

		try {
			ruleType = ruleTypeUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String ruleType;

	@Override
	public DataDefinitionRule clone() throws CloneNotSupportedException {
		return (DataDefinitionRule)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof DataDefinitionRule)) {
			return false;
		}

		DataDefinitionRule dataDefinitionRule = (DataDefinitionRule)object;

		return Objects.equals(toString(), dataDefinitionRule.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return DataDefinitionRuleSerDes.toJSON(this);
	}

}