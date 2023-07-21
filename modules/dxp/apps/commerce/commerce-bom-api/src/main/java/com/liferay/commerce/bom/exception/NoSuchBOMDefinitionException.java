/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.bom.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Luca Pellizzon
 */
public class NoSuchBOMDefinitionException extends NoSuchModelException {

	public NoSuchBOMDefinitionException() {
	}

	public NoSuchBOMDefinitionException(String msg) {
		super(msg);
	}

	public NoSuchBOMDefinitionException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchBOMDefinitionException(Throwable cause) {
		super(cause);
	}

}