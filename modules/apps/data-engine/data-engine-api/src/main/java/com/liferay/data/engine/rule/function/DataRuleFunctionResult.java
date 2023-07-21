/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.data.engine.rule.function;

import com.liferay.data.engine.spi.dto.SPIDataDefinitionField;

/**
 * @author Jeyvison Nascimento
 */
public class DataRuleFunctionResult {

	public static DataRuleFunctionResult of(
		SPIDataDefinitionField spiDataDefinitionField, String errorCode) {

		return new DataRuleFunctionResult(spiDataDefinitionField, errorCode);
	}

	public String getErrorCode() {
		return _errorCode;
	}

	public SPIDataDefinitionField getSPIDataDefinitionField() {
		return _spiDataDefinitionField;
	}

	public boolean isValid() {
		return _valid;
	}

	public void setValid(boolean valid) {
		_valid = valid;

		if (valid) {
			_errorCode = null;
		}
	}

	private DataRuleFunctionResult(
		SPIDataDefinitionField spiDataDefinitionField, String errorCode) {

		_spiDataDefinitionField = spiDataDefinitionField;
		_errorCode = errorCode;
	}

	private String _errorCode;
	private final SPIDataDefinitionField _spiDataDefinitionField;
	private boolean _valid;

}