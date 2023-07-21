/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.util;

import java.io.InputStream;

import java.nio.ByteBuffer;

/**
 * @author Brian Wing Shun Chan
 * @author Alexander Chow
 * @author Connor McKay
 */
public class DigesterUtil {

	public static String digest(ByteBuffer byteBuffer) {
		return getDigester().digest(byteBuffer);
	}

	public static String digest(InputStream inputStream) {
		return getDigester().digest(inputStream);
	}

	public static String digest(String text) {
		return getDigester().digest(text);
	}

	public static String digest(String algorithm, ByteBuffer byteBuffer) {
		return getDigester().digest(algorithm, byteBuffer);
	}

	public static String digest(String algorithm, InputStream inputStream) {
		return getDigester().digest(algorithm, inputStream);
	}

	public static String digest(String algorithm, String... text) {
		return getDigester().digest(algorithm, text);
	}

	public static String digestBase64(ByteBuffer byteBuffer) {
		return getDigester().digestBase64(byteBuffer);
	}

	public static String digestBase64(InputStream inputStream) {
		return getDigester().digestBase64(inputStream);
	}

	public static String digestBase64(String text) {
		return getDigester().digestBase64(text);
	}

	public static String digestBase64(String algorithm, ByteBuffer byteBuffer) {
		return getDigester().digestBase64(algorithm, byteBuffer);
	}

	public static String digestBase64(
		String algorithm, InputStream inputStream) {

		return getDigester().digestBase64(algorithm, inputStream);
	}

	public static String digestBase64(String algorithm, String... text) {
		return getDigester().digestBase64(algorithm, text);
	}

	public static String digestHex(ByteBuffer byteBuffer) {
		return getDigester().digestHex(byteBuffer);
	}

	public static String digestHex(InputStream inputStream) {
		return getDigester().digestHex(inputStream);
	}

	public static String digestHex(String text) {
		return getDigester().digestHex(text);
	}

	public static String digestHex(String algorithm, ByteBuffer byteBuffer) {
		return getDigester().digestHex(algorithm, byteBuffer);
	}

	public static String digestHex(String algorithm, InputStream inputStream) {
		return getDigester().digestHex(algorithm, inputStream);
	}

	public static String digestHex(String algorithm, String... text) {
		return getDigester().digestHex(algorithm, text);
	}

	public static byte[] digestRaw(ByteBuffer byteBuffer) {
		return getDigester().digestRaw(byteBuffer);
	}

	public static byte[] digestRaw(String text) {
		return getDigester().digestRaw(text);
	}

	public static byte[] digestRaw(String algorithm, ByteBuffer byteBuffer) {
		return getDigester().digestRaw(algorithm, byteBuffer);
	}

	public static byte[] digestRaw(String algorithm, InputStream inputStream) {
		return getDigester().digestRaw(algorithm, inputStream);
	}

	public static byte[] digestRaw(String algorithm, String... text) {
		return getDigester().digestRaw(algorithm, text);
	}

	public static Digester getDigester() {
		return _digester;
	}

	public void setDigester(Digester digester) {
		_digester = digester;
	}

	private static Digester _digester;

}