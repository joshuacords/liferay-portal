/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.kernel.antivirus;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Michael C. Han
 * @author Hugo Huijser
 */
public class AntivirusScannerException extends PortalException {

	public static final int PROCESS_FAILURE = 1;

	public static final int VIRUS_DETECTED = 2;

	/**
	 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
	 *             #AntivirusScannerException(int)}
	 */
	@Deprecated
	public AntivirusScannerException() {
	}

	public AntivirusScannerException(int type) {
		_type = type;
	}

	public AntivirusScannerException(int type, Throwable cause) {
		super(cause);

		_type = type;
	}

	/**
	 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
	 *             #AntivirusScannerException(String, int)}
	 */
	@Deprecated
	public AntivirusScannerException(String msg) {
		super(msg);
	}

	public AntivirusScannerException(String msg, int type) {
		super(msg);

		_type = type;
	}

	public AntivirusScannerException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public AntivirusScannerException(Throwable cause) {
		super(cause);
	}

	public String getMessageKey() {
		if (_type == PROCESS_FAILURE) {
			return "unable-to-scan-file-for-viruses";
		}
		else if (_type == VIRUS_DETECTED) {
			return "a-virus-was-detected-in-the-file";
		}

		return "an-unexpected-error-occurred-while-scanning-for-viruses";
	}

	private int _type;

}