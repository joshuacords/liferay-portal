/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.pwd;

import com.liferay.portal.kernel.security.pwd.PasswordEncryptor;
import com.liferay.portal.kernel.security.pwd.PasswordEncryptorUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jodd.util.BCrypt;

/**
 * @author Michael C. Han
 * @author Tomas Polesovsky
 */
public class BCryptPasswordEncryptor
	extends BasePasswordEncryptor implements PasswordEncryptor {

	@Override
	public String encrypt(
		String algorithm, String plainTextPassword, String encryptedPassword) {

		String salt = null;

		if (Validator.isNull(encryptedPassword)) {
			int rounds = _ROUNDS;

			Matcher matcher = _pattern.matcher(algorithm);

			if (matcher.matches()) {
				rounds = GetterUtil.getInteger(matcher.group(1), rounds);
			}

			salt = BCrypt.gensalt(rounds);
		}
		else {
			salt = encryptedPassword.substring(0, 29);
		}

		return BCrypt.hashpw(plainTextPassword, salt);
	}

	@Override
	public String[] getSupportedAlgorithmTypes() {
		return new String[] {PasswordEncryptorUtil.TYPE_BCRYPT};
	}

	private static final int _ROUNDS = 10;

	private static final Pattern _pattern = Pattern.compile(
		"^BCrypt/([0-9]+)$", Pattern.CASE_INSENSITIVE);

}