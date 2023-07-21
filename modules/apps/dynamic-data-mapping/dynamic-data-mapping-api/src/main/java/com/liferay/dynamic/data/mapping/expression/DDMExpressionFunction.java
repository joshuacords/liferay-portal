/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.expression;

/**
 * @author Leonardo Barros
 */
public interface DDMExpressionFunction {

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link Function0}, {@link
	 *             Function1}, {@link Function2}, {@link Function3}, {@link
	 *             Function4}
	 */
	@Deprecated
	public default Object evaluate(Object... parameters) {
		if (parameters.length == 0) {
			DDMExpressionFunction.Function0 function0 =
				(DDMExpressionFunction.Function0)this;

			return function0.apply();
		}
		else if (parameters.length == 1) {
			DDMExpressionFunction.Function1 function1 =
				(DDMExpressionFunction.Function1)this;

			return function1.apply(parameters[0]);
		}
		else if (parameters.length == 2) {
			DDMExpressionFunction.Function2 function2 =
				(DDMExpressionFunction.Function2)this;

			return function2.apply(parameters[0], parameters[1]);
		}
		else if (parameters.length == 3) {
			DDMExpressionFunction.Function3 function3 =
				(DDMExpressionFunction.Function3)this;

			return function3.apply(parameters[0], parameters[1], parameters[2]);
		}
		else if (parameters.length == 4) {
			DDMExpressionFunction.Function4 function4 =
				(DDMExpressionFunction.Function4)this;

			return function4.apply(
				parameters[0], parameters[1], parameters[2], parameters[3]);
		}

		return null;
	}

	public String getName();

	public interface Function0<R> extends DDMExpressionFunction {

		public R apply();

	}

	public interface Function1<A, R> extends DDMExpressionFunction {

		public R apply(A a);

	}

	public interface Function2<A, B, R> extends DDMExpressionFunction {

		public R apply(A a, B b);

	}

	public interface Function3<A, B, C, R> extends DDMExpressionFunction {

		public R apply(A a, B b, C c);

	}

	public interface Function4<A, B, C, D, R> extends DDMExpressionFunction {

		public R apply(A a, B b, C c, D d);

	}

}