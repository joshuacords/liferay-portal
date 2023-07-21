/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.words;

import com.liferay.portal.kernel.jazzy.InvalidWord;

import java.util.List;
import java.util.Set;

/**
 * @author Shinn Lok
 */
public class WordsUtil {

	public static List<InvalidWord> checkSpelling(String text) {
		return getWords().checkSpelling(text);
	}

	public static List<String> getDictionaryList() {
		return getWords().getDictionaryList();
	}

	public static Set<String> getDictionarySet() {
		return getWords().getDictionarySet();
	}

	public static String getRandomWord() {
		return getWords().getRandomWord();
	}

	public static Words getWords() {
		return _words;
	}

	public static boolean isDictionaryWord(String word) {
		return getWords().isDictionaryWord(word);
	}

	public void setWords(Words words) {
		_words = words;
	}

	private static Words _words;

}