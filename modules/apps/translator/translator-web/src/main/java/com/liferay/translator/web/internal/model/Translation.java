/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.translator.web.internal.model;

import com.liferay.petra.reflect.ReflectionUtil;
import com.liferay.petra.string.StringPool;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

/**
 * @author Brian Wing Shun Chan
 */
public class Translation implements Serializable {

	public Translation(
		String fromLanguageId, String toLanguageId, String fromText) {

		_fromLanguageId = fromLanguageId;
		_toLanguageId = toLanguageId;

		setFromText(fromText);
	}

	public Translation(
		String fromLanguageId, String toLanguageId, String fromText,
		String toText) {

		_fromLanguageId = fromLanguageId;
		_toLanguageId = toLanguageId;

		setFromText(fromText);
		setToText(toText);
	}

	public String getFromLanguageId() {
		return _fromLanguageId;
	}

	public String getFromText() {
		return _fromText;
	}

	public String getToLanguageId() {
		return _toLanguageId;
	}

	public String getToText() {
		return _toText;
	}

	public void setFromText(String fromText) {
		try {
			_fromText = new String(fromText.getBytes(), StringPool.UTF8);
		}
		catch (UnsupportedEncodingException unsupportedEncodingException) {
			ReflectionUtil.throwException(unsupportedEncodingException);
		}
	}

	public void setToText(String toText) {
		try {
			_toText = new String(toText.getBytes(), StringPool.UTF8);
		}
		catch (UnsupportedEncodingException unsupportedEncodingException) {
			ReflectionUtil.throwException(unsupportedEncodingException);
		}
	}

	private final String _fromLanguageId;
	private String _fromText;
	private final String _toLanguageId;
	private String _toText;

}