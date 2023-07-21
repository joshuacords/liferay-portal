/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.expression;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Marcellus Tavares
 */
@ProviderType
public interface DDMExpressionFactory {

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             DDMExpressionFactory#createExpression(
	 *             CreateExpressionRequest)}
	 */
	@Deprecated
	public default DDMExpression<Boolean> createBooleanDDMExpression(
			String ddmExpressionString)
		throws DDMExpressionException {

		CreateExpressionRequest createExpressionRequest =
			CreateExpressionRequest.Builder.newBuilder(
				ddmExpressionString
			).build();

		return createExpression(createExpressionRequest);
	}

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             DDMExpressionFactory#createExpression(
	 *             CreateExpressionRequest)}
	 */
	@Deprecated
	public default DDMExpression<Double> createDoubleDDMExpression(
			String ddmExpressionString)
		throws DDMExpressionException {

		CreateExpressionRequest createExpressionRequest =
			CreateExpressionRequest.Builder.newBuilder(
				ddmExpressionString
			).build();

		return createExpression(createExpressionRequest);
	}

	public <T> DDMExpression<T> createExpression(
			CreateExpressionRequest createExpressionRequest)
		throws DDMExpressionException;

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             DDMExpressionFactory#createExpression(
	 *             CreateExpressionRequest)}
	 */
	@Deprecated
	public default DDMExpression<Float> createFloatDDMExpression(
			String ddmExpressionString)
		throws DDMExpressionException {

		CreateExpressionRequest createExpressionRequest =
			CreateExpressionRequest.Builder.newBuilder(
				ddmExpressionString
			).build();

		return createExpression(createExpressionRequest);
	}

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             DDMExpressionFactory#createExpression(
	 *             CreateExpressionRequest)}
	 */
	@Deprecated
	public default DDMExpression<Integer> createIntegerDDMExpression(
			String ddmExpressionString)
		throws DDMExpressionException {

		CreateExpressionRequest createExpressionRequest =
			CreateExpressionRequest.Builder.newBuilder(
				ddmExpressionString
			).build();

		return createExpression(createExpressionRequest);
	}

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             DDMExpressionFactory#createExpression(
	 *             CreateExpressionRequest)}
	 */
	@Deprecated
	public default DDMExpression<Long> createLongDDMExpression(
			String ddmExpressionString)
		throws DDMExpressionException {

		CreateExpressionRequest createExpressionRequest =
			CreateExpressionRequest.Builder.newBuilder(
				ddmExpressionString
			).build();

		return createExpression(createExpressionRequest);
	}

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             DDMExpressionFactory#createExpression(
	 *             CreateExpressionRequest)}
	 */
	@Deprecated
	public default DDMExpression<Number> createNumberDDMExpression(
			String ddmExpressionString)
		throws DDMExpressionException {

		CreateExpressionRequest createExpressionRequest =
			CreateExpressionRequest.Builder.newBuilder(
				ddmExpressionString
			).build();

		return createExpression(createExpressionRequest);
	}

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             DDMExpressionFactory#createExpression(
	 *             CreateExpressionRequest)}
	 */
	@Deprecated
	public default DDMExpression<String> createStringDDMExpression(
			String ddmExpressionString)
		throws DDMExpressionException {

		CreateExpressionRequest createExpressionRequest =
			CreateExpressionRequest.Builder.newBuilder(
				ddmExpressionString
			).build();

		return createExpression(createExpressionRequest);
	}

}