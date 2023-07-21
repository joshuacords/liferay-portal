/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.internal.cluster;

import com.liferay.portal.kernel.process.ProcessCallable;
import com.liferay.portal.kernel.process.ProcessException;
import com.liferay.portal.kernel.util.MethodHandler;

import java.io.Serializable;

/**
 * @author Shuyang Zhou
 */
public class MethodHandlerProcessCallable<T extends Serializable>
	implements ProcessCallable<T> {

	public MethodHandlerProcessCallable(MethodHandler methodHandler) {
		_methodHandler = methodHandler;
	}

	@Override
	public T call() throws ProcessException {
		try {
			return (T)_methodHandler.invoke();
		}
		catch (Exception exception) {
			throw new ProcessException(exception);
		}
	}

	private static final long serialVersionUID = 1L;

	private final MethodHandler _methodHandler;

}