/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.process.local;

import com.liferay.portal.kernel.process.ProcessCallable;
import com.liferay.portal.kernel.process.ProcessException;

import java.io.Serializable;

/**
 * @author Shuyang Zhou
 */
class ResultProcessCallable<T extends Serializable>
	implements ProcessCallable<T> {

	ResultProcessCallable(T returnValue, ProcessException processException) {
		_returnValue = returnValue;
		_processException = processException;
	}

	@Override
	public T call() throws ProcessException {
		if (_processException != null) {
			throw _processException;
		}

		return _returnValue;
	}

	private static final long serialVersionUID = 1L;

	private final ProcessException _processException;
	private final T _returnValue;

}