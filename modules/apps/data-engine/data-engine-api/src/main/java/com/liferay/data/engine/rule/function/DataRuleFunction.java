/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.data.engine.rule.function;

import com.liferay.data.engine.spi.dto.SPIDataDefinitionField;

import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Jeyvison Nascimento
 */
@ProviderType
public interface DataRuleFunction {

	public DataRuleFunctionResult validate(
		Map<String, Object> dataDefinitionRuleParameters,
		SPIDataDefinitionField spiDataDefinitionField, Object value);

}