/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.scion.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Brian Wing Shun Chan
 */
public class NoSuchServiceProducerException extends NoSuchModelException {

	public NoSuchServiceProducerException() {
	}

	public NoSuchServiceProducerException(String msg) {
		super(msg);
	}

	public NoSuchServiceProducerException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchServiceProducerException(Throwable cause) {
		super(cause);
	}

}