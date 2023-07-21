/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.fabric;

import com.liferay.petra.process.ProcessCallable;
import com.liferay.petra.process.ProcessException;

/**
 * @author Shuyang Zhou
 */
public class ExceptionProcessCallable
	implements ProcessCallable<ProcessException> {

	public ExceptionProcessCallable(ProcessException processException) {
		_processException = processException;
	}

	@Override
	public ProcessException call() throws ProcessException {
		throw _processException;
	}

	private static final long serialVersionUID = 1L;

	private final ProcessException _processException;

}