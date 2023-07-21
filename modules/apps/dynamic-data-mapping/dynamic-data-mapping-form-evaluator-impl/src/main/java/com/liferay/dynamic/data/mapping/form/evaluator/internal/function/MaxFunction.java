/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.evaluator.internal.function;

import com.liferay.dynamic.data.mapping.expression.DDMExpressionFunction;

import java.math.BigDecimal;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Leonardo Barros
 */
public class MaxFunction
	implements DDMExpressionFunction.Function1<Object[], BigDecimal> {

	public static final String NAME = "MAX";

	@Override
	public BigDecimal apply(Object[] values) {
		return Stream.of(
			values
		).map(
			value -> new BigDecimal(value.toString())
		).collect(
			Collectors.maxBy((num1, num2) -> num1.compareTo(num2))
		).orElse(
			BigDecimal.ZERO
		);
	}

	@Override
	public String getName() {
		return NAME;
	}

}