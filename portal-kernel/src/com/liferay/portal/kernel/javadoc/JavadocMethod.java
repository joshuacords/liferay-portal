/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.javadoc;

import java.lang.reflect.Method;

/**
 * @author Preston Crary
 */
public abstract class JavadocMethod extends BaseJavadoc {

	public JavadocMethod(String servletContextName, String comment) {
		super(servletContextName, comment);
	}

	public abstract Method getMethod();

	public abstract String getParameterComment(int index);

	public abstract String getReturnComment();

	public abstract String getThrowsComment(int index);

}