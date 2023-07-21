/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.javadoc;

import java.lang.reflect.Method;

/**
 * @author Igor Spasic
 */
public class JavadocMethodImpl extends JavadocMethod {

	public JavadocMethodImpl(
		String servletContextName, String comment, Method method,
		String[] parameterComments, String returnComment,
		String[] throwsComments) {

		super(servletContextName, comment);

		_method = method;
		_parameterComments = parameterComments;
		_returnComment = returnComment;
		_throwsComments = throwsComments;
	}

	@Override
	public Method getMethod() {
		return _method;
	}

	@Override
	public String getParameterComment(int index) {
		if (_parameterComments == null) {
			return null;
		}

		return _parameterComments[index];
	}

	@Override
	public String getReturnComment() {
		return _returnComment;
	}

	@Override
	public String getThrowsComment(int index) {
		if (_throwsComments == null) {
			return null;
		}

		return _throwsComments[index];
	}

	private final Method _method;
	private final String[] _parameterComments;
	private final String _returnComment;
	private final String[] _throwsComments;

}