/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.petra.json.web.service.client;

/**
 * @author Ivica Cardic
 */
public class JSONWebServiceException extends Exception {

	public JSONWebServiceException(String message) {
		super(message);
	}

	public JSONWebServiceException(String message, int status) {
		super(message);

		_status = status;
	}

	public JSONWebServiceException(
		String message, int status, Throwable cause) {

		super(message, cause);

		_status = status;
	}

	public JSONWebServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public JSONWebServiceException(Throwable cause) {
		super(cause);
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	@Override
	public String toString() {
		String message = super.getMessage();

		if ((message != null) && (message.length() > 0)) {
			return message;
		}

		return "Server returned status " + _status;
	}

	private int _status;

}