/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.data.provider;

import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.dynamic.data.mapping.util.DDMFormInstanceFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Marcellus Tavares
 */
public class DDMDataProviderContext {

	public DDMDataProviderContext(DDMFormValues ddmFormValues) {
		_ddmFormValues = ddmFormValues;
	}

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             DDMDataProviderRequest#queryString(Map)}
	 */
	@Deprecated
	public void addParameters(Map<String, String> parameters) {
		_parameters.putAll(parameters);
	}

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             DDMDataProviderRequest#getParameters()}
	 */
	@Deprecated
	public Map<String, String> getParameters() {
		return _parameters;
	}

	public <T> T getSettingsInstance(Class<T> clazz) {
		return DDMFormInstanceFactory.create(clazz, _ddmFormValues);
	}

	private final DDMFormValues _ddmFormValues;
	private final Map<String, String> _parameters = new HashMap<>();

}