/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.exception;

import com.liferay.portal.kernel.util.ClassUtil;
import com.liferay.portal.kernel.util.StackTraceUtil;

import java.util.Collection;

/**
 * @author     Michael C. Han
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class BulkException extends Exception {

	public BulkException(Collection<Throwable> causes) {
		_causes = causes;
	}

	public BulkException(String message, Collection<Throwable> causes) {
		super(message);

		_causes = causes;
	}

	public Collection<Throwable> getCauses() {
		return _causes;
	}

	@Override
	public String getMessage() {
		StringBuilder sb = new StringBuilder((7 * _causes.size()) + 4);

		sb.append("{message = ");
		sb.append(super.getMessage());
		sb.append("\n");

		for (Throwable cause : _causes) {
			sb.append("{");
			sb.append(ClassUtil.getClassName(cause));
			sb.append(":");
			sb.append(cause.getMessage());
			sb.append(", ");
			sb.append(StackTraceUtil.getStackTrace(cause));
			sb.append("}\n");
		}

		sb.append("}");

		return sb.toString();
	}

	private final Collection<Throwable> _causes;

}