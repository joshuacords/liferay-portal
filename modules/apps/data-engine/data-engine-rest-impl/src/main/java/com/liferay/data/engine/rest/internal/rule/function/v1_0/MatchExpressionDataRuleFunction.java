/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.data.engine.rest.internal.rule.function.v1_0;

import com.liferay.data.engine.rest.internal.constants.DataRuleFunctionConstants;
import com.liferay.data.engine.rule.function.DataRuleFunction;
import com.liferay.data.engine.rule.function.DataRuleFunctionResult;
import com.liferay.data.engine.spi.dto.SPIDataDefinitionField;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.MapUtil;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.osgi.service.component.annotations.Component;

/**
 * @author Jeyvison Nascimento
 */
@Component(
	immediate = true,
	property = {
		"data.engine.rule.function.name=matchExpression",
		"data.engine.rule.function.type=" + DataRuleFunctionConstants.VALIDATION_RULE_TYPE
	},
	service = DataRuleFunction.class
)
public class MatchExpressionDataRuleFunction implements DataRuleFunction {

	@Override
	public DataRuleFunctionResult validate(
		Map<String, Object> dataDefinitionRuleParameters,
		SPIDataDefinitionField spiDataDefinitionField, Object value) {

		DataRuleFunctionResult dataRuleFunctionResult =
			DataRuleFunctionResult.of(
				spiDataDefinitionField, "value-must-match-expression");

		if (value == null) {
			return dataRuleFunctionResult;
		}

		try {
			Pattern pattern = Pattern.compile(
				MapUtil.getString(dataDefinitionRuleParameters, "expression"));

			Matcher matcher = pattern.matcher(value.toString());

			dataRuleFunctionResult.setValid(matcher.matches());
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception, exception);
			}
		}

		return dataRuleFunctionResult;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		MatchExpressionDataRuleFunction.class);

}