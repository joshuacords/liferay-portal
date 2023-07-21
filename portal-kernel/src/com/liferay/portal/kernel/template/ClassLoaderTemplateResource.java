/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.template;

/**
 * @author     Raymond Augé
 * @deprecated As of Mueller (7.2.x), with no direct replacement
 */
@Deprecated
public class ClassLoaderTemplateResource extends URLTemplateResource {

	public ClassLoaderTemplateResource(
		ClassLoader classLoader, String templateId) {

		super(templateId, classLoader.getResource(templateId));

		_classLoader = classLoader;
	}

	public ClassLoader getClassLoader() {
		return _classLoader;
	}

	private final ClassLoader _classLoader;

}