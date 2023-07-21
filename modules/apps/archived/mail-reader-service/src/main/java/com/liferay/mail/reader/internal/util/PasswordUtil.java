/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mail.reader.internal.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.Validator;

import java.io.UnsupportedEncodingException;

/**
 * @author Deepak Gothe
 */
public class PasswordUtil {

	public static String decrypt(String encryptedPassword) {
		String unencryptedPassword = null;

		try {
			if (Validator.isNull(encryptedPassword)) {
				return StringPool.BLANK;
			}

			byte[] bytes = Base64.decode(encryptedPassword);

			unencryptedPassword = new String(bytes, StringPool.UTF8);
		}
		catch (UnsupportedEncodingException unsupportedEncodingException) {
			_log.error(
				"Unable to decrypt the password", unsupportedEncodingException);
		}

		return unencryptedPassword;
	}

	public static String encrypt(String unencryptedPassword) {
		String encryptedPassword = null;

		try {
			encryptedPassword = Base64.encode(
				unencryptedPassword.getBytes(StringPool.UTF8));
		}
		catch (UnsupportedEncodingException unsupportedEncodingException) {
			_log.error(
				"Unable to encrypt the password", unsupportedEncodingException);
		}

		return encryptedPassword;
	}

	private static final Log _log = LogFactoryUtil.getLog(PasswordUtil.class);

}