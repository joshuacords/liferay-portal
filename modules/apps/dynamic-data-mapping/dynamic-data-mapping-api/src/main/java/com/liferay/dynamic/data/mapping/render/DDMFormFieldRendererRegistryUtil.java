/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.render;

/**
 * @author Pablo Carvalho
 */
public class DDMFormFieldRendererRegistryUtil {

	public static DDMFormFieldRenderer getDDMFormFieldRenderer(
		String ddmFormFieldType) {

		return getDDMFormFieldRendererRegistry().getDDMFormFieldRenderer(
			ddmFormFieldType);
	}

	public static DDMFormFieldRendererRegistry
		getDDMFormFieldRendererRegistry() {

		return _ddmFormFieldRendererRegistry;
	}

	public void setDDMFormFieldRendererRegistry(
		DDMFormFieldRendererRegistry ddmFormFieldRendererRegistry) {

		_ddmFormFieldRendererRegistry = ddmFormFieldRendererRegistry;
	}

	private static DDMFormFieldRendererRegistry _ddmFormFieldRendererRegistry;

}