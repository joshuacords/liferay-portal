/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.javadoc;

/**
 * @author Igor Spasic
 */
public abstract class BaseJavadoc {

	public BaseJavadoc(String servletContextName, String comment) {
		_servletContextName = servletContextName;
		_comment = comment;
	}

	public String getComment() {
		return _comment;
	}

	public String getServletContextName() {
		return _servletContextName;
	}

	private final String _comment;
	private final String _servletContextName;

}