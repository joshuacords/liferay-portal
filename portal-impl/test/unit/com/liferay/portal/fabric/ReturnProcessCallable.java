/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.fabric;

import com.liferay.petra.process.ProcessCallable;

import java.io.Serializable;

/**
 * @author Shuyang Zhou
 */
public class ReturnProcessCallable<T extends Serializable>
	implements ProcessCallable<T> {

	public ReturnProcessCallable(T returnValue) {
		_returnValue = returnValue;
	}

	@Override
	public T call() {
		return _returnValue;
	}

	private static final long serialVersionUID = 1L;

	private final T _returnValue;

}