/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.security.pwd;

import com.liferay.portal.kernel.exception.PwdEncryptorException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author Brian Wing Shun Chan
 * @author Scott Lee
 * @author Tomas Polesovsky
 * @author Michael C. Han
 */
public class PasswordEncryptorUtil {

	public static final String TYPE_BCRYPT = "BCRYPT";

	public static final String TYPE_MD2 = "MD2";

	public static final String TYPE_MD5 = "MD5";

	public static final String TYPE_NONE = "NONE";

	public static final String TYPE_PBKDF2 = "PBKDF2";

	public static final String TYPE_SHA = "SHA";

	public static final String TYPE_SHA_256 = "SHA-256";

	public static final String TYPE_SHA_384 = "SHA-384";

	public static final String TYPE_SSHA = "SSHA";

	public static final String TYPE_UFC_CRYPT = "UFC-CRYPT";

	public static String encrypt(String plainTextPassword)
		throws PwdEncryptorException {

		return encrypt(plainTextPassword, null);
	}

	public static String encrypt(
			String plainTextPassword, String encryptedPassword)
		throws PwdEncryptorException {

		long startTime = 0;

		if (_log.isDebugEnabled()) {
			startTime = System.currentTimeMillis();
		}

		try {
			return _passwordEncryptor.encrypt(
				plainTextPassword, encryptedPassword);
		}
		finally {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Password encrypted in " +
						(System.currentTimeMillis() - startTime) + "ms");
			}
		}
	}

	public static String encrypt(
			String algorithm, String plainTextPassword,
			String encryptedPassword)
		throws PwdEncryptorException {

		return _passwordEncryptor.encrypt(
			algorithm, plainTextPassword, encryptedPassword);
	}

	public static String getDefaultPasswordAlgorithmType() {
		return _passwordEncryptor.getDefaultPasswordAlgorithmType();
	}

	public PasswordEncryptor getPasswordEncryptor() {
		return _passwordEncryptor;
	}

	public void setPasswordEncryptor(PasswordEncryptor passwordEncryptor) {
		_passwordEncryptor = passwordEncryptor;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PasswordEncryptorUtil.class);

	private static PasswordEncryptor _passwordEncryptor;

}