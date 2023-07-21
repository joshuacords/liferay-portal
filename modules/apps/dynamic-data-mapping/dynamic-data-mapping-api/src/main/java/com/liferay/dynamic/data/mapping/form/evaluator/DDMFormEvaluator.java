/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.evaluator;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Pablo Carvalho
 * @author Leonardo Barros
 */
@ProviderType
public interface DDMFormEvaluator {

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             DDMFormEvaluator#evaluate(DDMFormEvaluatorEvaluateRequest)}
	 */
	@Deprecated
	public DDMFormEvaluationResult evaluate(
			DDMFormEvaluatorContext ddmFormEvaluatorContext)
		throws DDMFormEvaluationException;

	public DDMFormEvaluatorEvaluateResponse evaluate(
		DDMFormEvaluatorEvaluateRequest ddmFormEvaluatorEvaluateRequest);

}