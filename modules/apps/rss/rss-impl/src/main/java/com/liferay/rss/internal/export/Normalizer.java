/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.rss.internal.export;

import com.ibm.icu.text.Transliterator;

import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 * @see    com.liferay.util.Normalizer
 */
public class Normalizer {

	public static String normalizeToAscii(String s) {
		if (!_hasNonasciiCode(s)) {
			return s;
		}

		String normalizedText = _transliterator.transform(s);

		return StringUtil.replace(
			normalizedText, _UNICODE_TEXT, _NORMALIZED_TEXT);
	}

	private static boolean _hasNonasciiCode(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) > 127) {
				return true;
			}
		}

		return false;
	}

	private static final String[] _NORMALIZED_TEXT = {"l", "'", "\""};

	private static final String[] _UNICODE_TEXT = {
		"\u0142", "\u02B9", "\u02BA"
	};

	private static final Transliterator _transliterator =
		Transliterator.getInstance(
			"Greek-Latin; Cyrillic-Latin; NFD; [:Nonspacing Mark:] Remove; " +
				"NFC");

}