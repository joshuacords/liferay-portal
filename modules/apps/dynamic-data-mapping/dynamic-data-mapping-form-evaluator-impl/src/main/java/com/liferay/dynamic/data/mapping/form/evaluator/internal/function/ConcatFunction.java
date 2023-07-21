/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.evaluator.internal.function;

import com.liferay.dynamic.data.mapping.expression.DDMExpressionFunction;
import com.liferay.portal.kernel.util.Validator;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Leonardo Barros
 */
public class ConcatFunction
	implements DDMExpressionFunction.Function1<Object[], String> {

	public static final String NAME = "concat";

	@Override
	public String apply(Object[] values) {
		return Stream.of(
			values
		).filter(
			Validator::isNotNull
		).map(
			Object::toString
		).collect(
			Collectors.joining()
		);
	}

	@Override
	public String getName() {
		return NAME;
	}

}