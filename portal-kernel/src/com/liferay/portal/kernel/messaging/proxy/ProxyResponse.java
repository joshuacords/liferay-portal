/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.messaging.proxy;

import com.liferay.petra.string.StringBundler;

import java.io.Serializable;

/**
 * @author Micha Kiener
 * @author Michael C. Han
 * @author Brian Wing Shun Chan
 */
public class ProxyResponse implements Serializable {

	public Exception getException() {
		return _exception;
	}

	public Object getResult() {
		return _result;
	}

	public boolean hasError() {
		if (_exception != null) {
			return true;
		}

		return false;
	}

	public void setException(Exception exception) {
		_exception = exception;
	}

	public void setResult(Object result) {
		_result = result;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{exception=");
		sb.append(_exception);
		sb.append(", result=");
		sb.append(_result);
		sb.append("}");

		return sb.toString();
	}

	private Exception _exception;
	private Object _result;

}