/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.distributed.messaging.internal.security;

import com.liferay.osb.distributed.messaging.security.MessageEncryptor;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.SecureRandomUtil;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.security.spec.KeySpec;

import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author William Newbury
 * @author Amos Fong
 */
@Component(immediate = true, service = MessageEncryptor.class)
public class DefaultMessageEncryptor implements MessageEncryptor {

	public String decrypt(String message) {
		if (Validator.isNull(_encryptionKey)) {
			return message;
		}

		try {
			JSONObject jsonObject = _jsonFactory.createJSONObject(message);

			if (!jsonObject.has("salt") || !jsonObject.has("iv")) {
				return message;
			}

			Cipher cipher = Cipher.getInstance(_ALGORITHM);

			byte[] salt = Base64.decode(jsonObject.getString("salt"));

			SecretKey secretKey = _generateSecretKey(salt);

			byte[] initializationVector = Base64.decode(
				jsonObject.getString("iv"));

			IvParameterSpec ivParameterSpec = new IvParameterSpec(
				initializationVector);

			cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);

			byte[] cipherText = Base64.decode(jsonObject.getString("message"));

			byte[] plainText = cipher.doFinal(cipherText);

			return new String(plainText);
		}
		catch (JSONException jsonException) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Message is not in the proper format: " + message,
					jsonException);
			}
		}
		catch (Exception exception) {
			_log.error("Unable to decrypt message", exception);
		}

		return message;
	}

	public String encrypt(String message) {
		if (Validator.isNull(_encryptionKey)) {
			return message;
		}

		try {
			Cipher cipher = Cipher.getInstance(_ALGORITHM);

			byte[] salt = _generateRandomByteArray();

			SecretKey secretKey = _generateSecretKey(salt);

			byte[] initializationVector = _generateRandomByteArray();

			IvParameterSpec ivParameterSpec = new IvParameterSpec(
				initializationVector);

			cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);

			byte[] cipherText = cipher.doFinal(message.getBytes());

			JSONObject jsonObject = JSONUtil.put(
				"iv", Base64.encode(initializationVector)
			).put(
				"message", Base64.encode(cipherText)
			).put(
				"salt", Base64.encode(salt)
			);

			return jsonObject.toString();
		}
		catch (Exception exception) {
			_log.error("Unable to encrypt message", exception);

			if (_log.isDebugEnabled()) {
				_log.debug("Message: " + message);
			}

			return message;
		}
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_encryptionKey = GetterUtil.getString(properties.get("encryptionKey"));
	}

	private byte[] _generateRandomByteArray() {
		byte[] bytes = new byte[16];

		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = SecureRandomUtil.nextByte();
		}

		return bytes;
	}

	private SecretKey _generateSecretKey(byte[] salt) throws Exception {
		KeySpec keySpec = new PBEKeySpec(
			_encryptionKey.toCharArray(), salt, 65536, 256);

		SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(
			"PBKDF2WithHmacSHA256");

		SecretKey secretKey = secretKeyFactory.generateSecret(keySpec);

		return new SecretKeySpec(secretKey.getEncoded(), "AES");
	}

	private static final String _ALGORITHM = "AES/CBC/PKCS5Padding";

	private static final Log _log = LogFactoryUtil.getLog(
		DefaultMessageEncryptor.class);

	private String _encryptionKey;

	@Reference
	private JSONFactory _jsonFactory;

}