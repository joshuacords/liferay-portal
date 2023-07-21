/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mail.reader.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Scott Lee
 */
public class MailException extends PortalException {

	public static final int ACCOUNT_ALREADY_EXISTS = 1;

	public static final int ACCOUNT_CONNECTIONS_FAILED = 2;

	public static final int ACCOUNT_INCOMING_CONNECTION_FAILED = 3;

	public static final int ACCOUNT_OUTGOING_CONNECTION_FAILED = 4;

	public static final int FOLDER_ALREADY_EXISTS = 5;

	public static final int FOLDER_CREATE_FAILED = 6;

	public static final int FOLDER_DELETE_FAILED = 7;

	public static final int FOLDER_DOES_NOT_EXIST = 8;

	public static final int FOLDER_INVALID_DESTINATION = 9;

	public static final int FOLDER_PAGE_DOES_NOT_EXIST = 10;

	public static final int FOLDER_RENAME_FAILED = 11;

	public static final int FOLDER_REQUIRED = 12;

	public static final int MESSAGE_HAS_NO_RECIPIENTS = 13;

	public static final int MESSAGE_INVALID_ADDRESS = 14;

	public static final int MESSAGE_INVALID_FLAG = 15;

	public static final int MESSAGE_NOT_FOUND_ON_SERVER = 16;

	public static final int MESSAGE_NOT_SELECTED = 17;

	public MailException() {
		this(0);
	}

	public MailException(int type) {
		this(type, (String)null);
	}

	public MailException(int type, String value) {
		_type = type;
		_value = value;
	}

	public MailException(int type, Throwable cause) {
		this(type, cause, null);
	}

	public MailException(int type, Throwable cause, String value) {
		super(cause);

		_type = type;
		_value = value;
	}

	public MailException(String msg) {
		super(msg);

		_type = 0;
		_value = null;
	}

	public MailException(String msg, Throwable cause) {
		super(msg, cause);

		_type = 0;
		_value = null;
	}

	public MailException(Throwable cause) {
		super(cause);

		_type = 0;
		_value = null;
	}

	public int getType() {
		return _type;
	}

	public String getValue() {
		return _value;
	}

	private final int _type;
	private final String _value;

}