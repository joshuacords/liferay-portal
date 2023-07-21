/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.process.local;

import com.liferay.portal.kernel.process.ProcessCallable;
import com.liferay.portal.kernel.process.ProcessException;

import java.io.IOException;
import java.io.Serializable;

/**
 * @author Shuyang Zhou
 */
public class RequestProcessCallable<T extends Serializable>
	implements ProcessCallable<T> {

	public RequestProcessCallable(long id, ProcessCallable<T> processCallable) {
		_id = id;
		_processCallable = processCallable;
	}

	@Override
	public T call() throws ProcessException {
		T result = null;
		Throwable throwable = null;

		try {
			result = _processCallable.call();

			return result;
		}
		catch (Throwable t) {
			throwable = t;

			throw t;
		}
		finally {
			try {
				LocalProcessLauncher.ProcessContext.writeProcessCallable(
					new ResponseProcessCallable<>(_id, result, throwable));
			}
			catch (IOException ioException) {
				throw new ProcessException(ioException);
			}
		}
	}

	private static final long serialVersionUID = 1L;

	private final long _id;
	private final ProcessCallable<T> _processCallable;

}