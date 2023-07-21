/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.bean;

/**
 * @author Shuyang Zhou
 */
public class ConstantsBeanFactoryUtil {

	public static Object getConstantsBean(Class<?> constantsClass) {
		return _constantsBeanFactory.getConstantsBean(constantsClass);
	}

	public void setConstantsBeanFactory(
		ConstantsBeanFactory constantsBeanFactory) {

		_constantsBeanFactory = constantsBeanFactory;
	}

	private static ConstantsBeanFactory _constantsBeanFactory;

}