/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.pwd;

import com.liferay.portal.kernel.exception.PwdEncryptorException;
import com.liferay.portal.kernel.io.BigEndianCodec;
import com.liferay.portal.kernel.security.SecureRandomUtil;
import com.liferay.portal.kernel.security.pwd.PasswordEncryptor;
import com.liferay.portal.kernel.security.pwd.PasswordEncryptorUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.Digester;
import com.liferay.portal.kernel.util.Validator;

import java.io.UnsupportedEncodingException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Michael C. Han
 * @author Tomas Polesovsky
 */
public class SSHAPasswordEncryptor
	extends BasePasswordEncryptor implements PasswordEncryptor {

	@Override
	public String encrypt(
			String algorithm, String plainTextPassword,
			String encryptedPassword)
		throws PwdEncryptorException {

		byte[] saltBytes = getSaltBytes(encryptedPassword);

		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");

			byte[] plainTextPasswordBytes = plainTextPassword.getBytes(
				Digester.ENCODING);

			byte[] messageDigestBytes = messageDigest.digest(
				ArrayUtil.append(plainTextPasswordBytes, saltBytes));

			return Base64.encode(
				ArrayUtil.append(messageDigestBytes, saltBytes));
		}
		catch (NoSuchAlgorithmException noSuchAlgorithmException) {
			throw new PwdEncryptorException(
				noSuchAlgorithmException.getMessage(),
				noSuchAlgorithmException);
		}
		catch (UnsupportedEncodingException unsupportedEncodingException) {
			throw new PwdEncryptorException(
				unsupportedEncodingException.getMessage(),
				unsupportedEncodingException);
		}
	}

	@Override
	public String[] getSupportedAlgorithmTypes() {
		return new String[] {PasswordEncryptorUtil.TYPE_SSHA};
	}

	protected byte[] getSaltBytes(String encryptedPassword)
		throws PwdEncryptorException {

		byte[] saltBytes = new byte[8];

		if (Validator.isNull(encryptedPassword)) {
			BigEndianCodec.putLong(saltBytes, 0, SecureRandomUtil.nextLong());
		}
		else {
			try {
				byte[] encryptedPasswordBytes = Base64.decode(
					encryptedPassword);

				System.arraycopy(
					encryptedPasswordBytes, encryptedPasswordBytes.length - 8,
					saltBytes, 0, saltBytes.length);
			}
			catch (Exception exception) {
				throw new PwdEncryptorException(
					"Unable to extract salt from encrypted password " +
						exception.getMessage(),
					exception);
			}
		}

		return saltBytes;
	}

}