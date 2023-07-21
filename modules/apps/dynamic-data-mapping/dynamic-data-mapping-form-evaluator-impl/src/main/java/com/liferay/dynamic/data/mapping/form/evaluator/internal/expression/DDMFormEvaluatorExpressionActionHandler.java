/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.evaluator.internal.expression;

import com.liferay.dynamic.data.mapping.expression.DDMExpressionActionHandler;
import com.liferay.dynamic.data.mapping.expression.ExecuteActionRequest;
import com.liferay.dynamic.data.mapping.expression.ExecuteActionResponse;

import java.util.Map;
import java.util.Optional;

/**
 * @author Leonardo Barros
 * @author Rafael Praxedes
 */
public class DDMFormEvaluatorExpressionActionHandler
	implements DDMExpressionActionHandler {

	public DDMFormEvaluatorExpressionActionHandler(
		Map<Integer, Integer> pageFlow) {

		_pageFlow = pageFlow;
	}

	@Override
	public ExecuteActionResponse executeAction(
		ExecuteActionRequest executeActionRequest) {

		String action = executeActionRequest.getAction();

		if (action.equals("jumpPage")) {
			return _jumpPage(executeActionRequest);
		}

		ExecuteActionResponse.Builder builder =
			ExecuteActionResponse.Builder.newBuilder(false);

		return builder.build();
	}

	private boolean _hasIntervalOnPageFlow(
		Integer fromPageIndex, Integer toPageIndex) {

		for (Map.Entry<Integer, Integer> entry : _pageFlow.entrySet()) {
			int fromPageFlowIndex = entry.getKey();
			int toPageFlowIndex = entry.getValue();

			if ((toPageIndex < fromPageFlowIndex) ||
				(fromPageIndex > toPageFlowIndex)) {

				continue;
			}

			return true;
		}

		return false;
	}

	private ExecuteActionResponse _jumpPage(
		ExecuteActionRequest executeActionRequest) {

		Optional<Integer> fromOptional =
			executeActionRequest.getParameterOptional("from");
		Optional<Integer> toOptional =
			executeActionRequest.getParameterOptional("to");

		if (fromOptional.isPresent() && toOptional.isPresent() &&
			!_hasIntervalOnPageFlow(fromOptional.get(), toOptional.get())) {

			_pageFlow.put(fromOptional.get(), toOptional.get());
		}

		ExecuteActionResponse.Builder builder =
			ExecuteActionResponse.Builder.newBuilder(true);

		return builder.build();
	}

	private final Map<Integer, Integer> _pageFlow;

}