/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.bom.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Luca Pellizzon
 */
public class NoSuchBOMFolderApplicationRelException
	extends NoSuchModelException {

	public NoSuchBOMFolderApplicationRelException() {
	}

	public NoSuchBOMFolderApplicationRelException(String msg) {
		super(msg);
	}

	public NoSuchBOMFolderApplicationRelException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchBOMFolderApplicationRelException(Throwable cause) {
		super(cause);
	}

}