/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.bean;

/**
 * @author     Brian Wing Shun Chan
 * @deprecated As of Paton (6.1.x)
 */
@Deprecated
public class ContextClassLoaderBeanHandler extends ClassLoaderBeanHandler {

	public ContextClassLoaderBeanHandler(Object bean, ClassLoader classLoader) {
		super(bean, classLoader);
	}

}