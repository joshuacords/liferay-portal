/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.data.partitioning.sql.builder.exporter.exception;

/**
 * @author Miguel Pastor
 */
public class DBProviderNotAvailableException extends RuntimeException {

	public DBProviderNotAvailableException() {
	}

	public DBProviderNotAvailableException(String message) {
		super(message);
	}

	public DBProviderNotAvailableException(String message, Throwable cause) {
		super(message, cause);
	}

	public DBProviderNotAvailableException(
		String message, Throwable cause, boolean enableSuppression,
		boolean writableStackTrace) {

		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DBProviderNotAvailableException(Throwable cause) {
		super(cause);
	}

}