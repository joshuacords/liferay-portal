/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.configuration.metatype.util;

import com.liferay.portal.kernel.module.configuration.ConfigurationException;

import java.util.Map;

/**
 * @author Jorge Ferrer
 */
public class ParameterMapUtil {

	public static <T> T setParameterMap(
			Class<T> clazz, T configurationBean,
			Map<String, String[]> parameterMap)
		throws ConfigurationException {

		ParameterMapInvocationHandler<T> parameterMapInvocationHandler =
			new ParameterMapInvocationHandler<>(
				clazz, configurationBean, parameterMap);

		return parameterMapInvocationHandler.createProxy();
	}

	public static <T> T setParameterMap(
			Class<T> clazz, T configurationBean,
			Map<String, String[]> parameterMap, String parameterPrefix,
			String parameterSuffix)
		throws ConfigurationException {

		ParameterMapInvocationHandler<T> parameterMapInvocationHandler =
			new ParameterMapInvocationHandler<>(
				clazz, configurationBean, parameterMap, parameterPrefix,
				parameterSuffix);

		return parameterMapInvocationHandler.createProxy();
	}

}