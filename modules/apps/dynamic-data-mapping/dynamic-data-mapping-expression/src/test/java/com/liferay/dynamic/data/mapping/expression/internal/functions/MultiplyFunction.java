/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.expression.internal.functions;

import com.liferay.dynamic.data.mapping.expression.DDMExpressionFunction;

import java.math.BigDecimal;

import java.util.stream.Stream;

/**
 * @author Rafael Praxedes
 */
public class MultiplyFunction
	implements DDMExpressionFunction.Function1<Object[], BigDecimal> {

	@Override
	public BigDecimal apply(Object[] numbers) {
		return Stream.of(
			numbers
		).map(
			number -> new BigDecimal(number.toString())
		).reduce(
			BigDecimal.ONE, (n1, n2) -> n1.multiply(n2)
		);
	}

	@Override
	public String getName() {
		return "multiply";
	}

}