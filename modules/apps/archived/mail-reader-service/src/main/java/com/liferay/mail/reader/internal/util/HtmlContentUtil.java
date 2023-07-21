/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mail.reader.internal.util;

import com.liferay.petra.string.StringPool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Scott Lee
 * @author Minhchau Dang
 * @author Michael C. Han
 */
public class HtmlContentUtil {

	public static String getInlineHtml(String html) {

		// Lines

		Matcher matcher = _bodyTagPattern.matcher(html);

		html = matcher.replaceAll(StringPool.BLANK);

		matcher = _doctypeTagPattern.matcher(html);

		html = matcher.replaceAll(StringPool.BLANK);

		matcher = _htmlTagPattern.matcher(html);

		html = matcher.replaceAll(StringPool.BLANK);

		matcher = _linkTagPattern.matcher(html);

		html = matcher.replaceAll(StringPool.BLANK);

		// Blocks

		matcher = _headTagPattern.matcher(html);

		html = matcher.replaceAll(StringPool.BLANK);

		matcher = _scriptTagPattern.matcher(html);

		html = matcher.replaceAll(StringPool.BLANK);

		matcher = _styleTagPattern.matcher(html);

		return matcher.replaceAll(StringPool.BLANK);
	}

	public static String getPlainText(String html) {
		Matcher matcher = _lineBreakPattern.matcher(html);

		html = matcher.replaceAll(StringPool.BLANK);

		matcher = _tagPattern.matcher(html);

		return matcher.replaceAll(StringPool.BLANK);
	}

	private static final Pattern _bodyTagPattern = Pattern.compile(
		"</?body[^>]+>", Pattern.CASE_INSENSITIVE);
	private static final Pattern _doctypeTagPattern = Pattern.compile(
		"<!doctype[^>]+>", Pattern.CASE_INSENSITIVE);
	private static final Pattern _headTagPattern = Pattern.compile(
		"<head.*?</head>", Pattern.CASE_INSENSITIVE + Pattern.DOTALL);
	private static final Pattern _htmlTagPattern = Pattern.compile(
		"</?html[^>]+>", Pattern.CASE_INSENSITIVE);
	private static final Pattern _lineBreakPattern = Pattern.compile("[\r\n]+");
	private static final Pattern _linkTagPattern = Pattern.compile(
		"</?link[^>]+>", Pattern.CASE_INSENSITIVE);
	private static final Pattern _scriptTagPattern = Pattern.compile(
		"<script.*?</script>", Pattern.CASE_INSENSITIVE + Pattern.DOTALL);
	private static final Pattern _styleTagPattern = Pattern.compile(
		"<style.*?</style>", Pattern.CASE_INSENSITIVE + Pattern.DOTALL);
	private static final Pattern _tagPattern = Pattern.compile("<[^>]+>");

}