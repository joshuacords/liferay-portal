/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wiki.web.internal.translator;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.DigesterUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Jorge Ferrer
 */
public abstract class BaseTranslator {

	public String translate(String content) {
		_protectedMap.clear();

		content = preProcess(content);
		content = runRegexps(content);
		content = postProcess(content);

		return content;
	}

	protected String postProcess(String content) {
		return unprotectNowikiText(content);
	}

	protected String preProcess(String content) {
		content = _normalizeLineBreaks(content);

		for (String regexp : nowikiRegexps) {
			content = protectText(content, regexp);
		}

		return content;
	}

	protected String protectText(String content, String markupRegex) {
		Pattern pattern = Pattern.compile(
			markupRegex, Pattern.MULTILINE | Pattern.DOTALL);

		Matcher matcher = pattern.matcher(content);

		StringBuffer sb = new StringBuffer();

		while (matcher.find()) {
			String protectedText = matcher.group();

			String hash = DigesterUtil.digest(protectedText);

			matcher.appendReplacement(sb, "$1" + hash + "$3");

			_protectedMap.put(hash, matcher.group(2));
		}

		matcher.appendTail(sb);

		return sb.toString();
	}

	protected String runRegexp(
		String content, String regexp, String replacement) {

		Pattern pattern = Pattern.compile(regexp, Pattern.MULTILINE);

		Matcher matcher = pattern.matcher(content);

		StringBuffer sb = new StringBuffer();

		while (matcher.find()) {
			matcher.appendReplacement(sb, replacement);
		}

		matcher.appendTail(sb);

		return sb.toString();
	}

	protected String runRegexps(String content) {
		for (Map.Entry<String, String> entry : regexps.entrySet()) {
			String regexp = entry.getKey();
			String replacement = entry.getValue();

			content = runRegexp(content, regexp, replacement);
		}

		return content;
	}

	protected String unprotectNowikiText(String content) {
		for (Map.Entry<String, String> entry : _protectedMap.entrySet()) {
			String hash = entry.getKey();
			String protectedMarkup = entry.getValue();

			content = StringUtil.replace(content, hash, protectedMarkup);
		}

		return content;
	}

	protected List<String> nowikiRegexps = new LinkedList<>();
	protected Map<String, String> regexps = new LinkedHashMap<>();

	private String _normalizeLineBreaks(String content) {
		return StringUtil.replace(
			content,
			new String[] {StringPool.RETURN_NEW_LINE, StringPool.RETURN},
			new String[] {StringPool.NEW_LINE, StringPool.NEW_LINE});
	}

	private final Map<String, String> _protectedMap = new LinkedHashMap<>();

}