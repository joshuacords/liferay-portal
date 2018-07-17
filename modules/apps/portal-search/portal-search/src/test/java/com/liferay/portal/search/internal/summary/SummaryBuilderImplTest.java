/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.search.internal.summary;

import com.liferay.portal.kernel.search.highlight.HighlightUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.search.summary.Summary;
import com.liferay.portal.search.summary.SummaryBuilder;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author André de Oliveira
 */
public class SummaryBuilderImplTest {

	@Test
	public void testContent() {
		String content = RandomTestUtil.randomString();

		_summaryBuilder.setContent(content);

		Summary summary = _summaryBuilder.build();

		Assert.assertEquals(content, summary.getContent());
	}

	@Test
	public void testContentHighlight() {
		_summaryBuilder.setContent(
			StringBundler.concat(
				"AAA<strong>BBB</strong>CCC", HighlightUtil.HIGHLIGHT_TAG_OPEN,
				"DDD<strong>EEE</strong>FFF", HighlightUtil.HIGHLIGHT_TAG_CLOSE,
				"GGG<strong>HHH</strong>III"));

		_summaryBuilder.setHighlight(true);
		_summaryBuilder.setMaxContentLength(200);

		Summary summary = _summaryBuilder.build();

		assertHighlightEquals(
			"AAA&lt;strong&gt;BBB&lt;/strong&gt;CCC[[DDD&lt;strong&gt;EEE" +
				"&lt;/strong&gt;FFF]]GGG&lt;strong&gt;HHH&lt;/strong&gt;III",
			summary.getContent());
	}

	@Test
	public void testContentHighlightCuttingNoSpaces() {
		StringBundler sb = new StringBundler(4);
		String content = "";
		String actualContent = "";
		String highlightedContent = RandomTestUtil.randomString(9);
		String normalContent = RandomTestUtil.randomString(10);

		sb.append(HighlightUtil.HIGHLIGHT_TAG_OPEN);
		sb.append(highlightedContent);
		sb.append(HighlightUtil.HIGHLIGHT_TAG_CLOSE);
		sb.append(normalContent);

		_summaryBuilder.setHighlight(true);

		content = sb.toString();

		for (int i = 0; i < 21; i++) {
			_summaryBuilder.setContent(content);
			_summaryBuilder.setMaxContentLength(i);

			Summary summary = _summaryBuilder.build();

			actualContent = summary.getContent();

			if (i == 0) {
				assertHighlightEquals("", actualContent);
			}
			else if (i == 1) {
				assertHighlightEquals("&lt;", actualContent);
			}
			else if (i == 2) {
				assertHighlightEquals("&lt;l", actualContent);
			}
			else if (i == 3) {
				assertHighlightEquals("...", actualContent);
			}
			else if (i == 20) {
				assertHighlightEquals(
					"[[" + highlightedContent + "]]" + normalContent,
					actualContent);
			}
			else {
				_assertNoSpaceResult(
					highlightedContent, normalContent, i, actualContent);
			}
		}
	}

	@Test
	public void testContentHighlightCuttingSpaces() {
		StringBundler sb = new StringBundler();
		String content;
		String actualContent;
		String highlightedContent = RandomTestUtil.randomString(4);
		String normalContent = RandomTestUtil.randomString(4);

		sb.append(HighlightUtil.HIGHLIGHT_TAG_OPEN);
		sb.append(highlightedContent);
		sb.append(" ");
		sb.append(highlightedContent);
		sb.append(HighlightUtil.HIGHLIGHT_TAG_CLOSE);
		sb.append(" ");
		sb.append(normalContent);
		sb.append(" ");
		sb.append(normalContent);

		_summaryBuilder.setHighlight(true);

		content = sb.toString();

		for (int i = 0; i < 20; i++) {
			_summaryBuilder.setContent(content);
			_summaryBuilder.setMaxContentLength(i);

			Summary summary = _summaryBuilder.build();

			actualContent = summary.getContent();

			if (i == 0) {
				assertHighlightEquals("", actualContent);
			}
			else if (i == 1) {
				assertHighlightEquals("&lt;", actualContent);
			}
			else if (i == 2) {
				assertHighlightEquals("&lt;l", actualContent);
			}
			else if (i == 3) {
				assertHighlightEquals("...", actualContent);
			}
			else if (i == 19) {
				sb = new StringBundler(23);

				sb.append("[[");
				sb.append(highlightedContent);
				sb.append(" ");
				sb.append(highlightedContent);
				sb.append("]] ");
				sb.append(normalContent);
				sb.append(" ");
				sb.append(normalContent);

				assertHighlightEquals(sb.toString(), actualContent);
			}
			else {
				_assertSpaceResult(
					highlightedContent, normalContent, i, actualContent);
			}
		}
	}

	@Test
	public void testContentHighlightUnescaped() {
		_summaryBuilder.setContent(
			StringBundler.concat(
				"AAA<strong>BBB</strong>CCC", HighlightUtil.HIGHLIGHT_TAG_OPEN,
				"DDD<strong>EEE</strong>FFF", HighlightUtil.HIGHLIGHT_TAG_CLOSE,
				"GGG<strong>HHH</strong>III"));

		_summaryBuilder.setEscape(false);
		_summaryBuilder.setHighlight(true);
		_summaryBuilder.setMaxContentLength(200);

		Summary summary = _summaryBuilder.build();

		assertHighlightEquals(
			"AAA<strong>BBB</strong>CCC[[DDD<strong>EEE</strong>FFF" +
				"]]GGG<strong>HHH</strong>III",
			summary.getContent());
	}

	@Test
	public void testHighlightWithCarrot() {
		StringBundler sb = new StringBundler(3);
		String normalContent = RandomTestUtil.randomString(11);

		sb.append(normalContent);

		sb.append("<");
		sb.append(normalContent);

		String content = sb.toString();

		_summaryBuilder.setContent(content);

		_summaryBuilder.setMaxContentLength(8);

		Summary summary = _summaryBuilder.build();

		String expectedContent = content.substring(0, 5) + "...";

		assertHighlightEquals(expectedContent, summary.getContent());
	}

	@Test
	public void testMaxContentLength() {
		String content = "12345678";

		testMaxContentLength(content, -99, content);
		testMaxContentLength(content, 0, content);
		testMaxContentLength(content, 2, "12");
		testMaxContentLength(content, 3, "...");
		testMaxContentLength(content, 4, "1...");
		testMaxContentLength(content, 7, "1234...");
		testMaxContentLength(content, 8, content);
		testMaxContentLength(content, 99, content);
	}

	@Test
	public void testMaxContentLengthIgnoredForTitle() {
		String title = RandomTestUtil.randomString(8);

		_summaryBuilder.setTitle(title);

		_summaryBuilder.setMaxContentLength(1);

		Summary summary = _summaryBuilder.build();

		Assert.assertEquals(title, summary.getTitle());
	}

	@Test
	public void testTitle() {
		String title = RandomTestUtil.randomString();

		_summaryBuilder.setTitle(title);

		Summary summary = _summaryBuilder.build();

		Assert.assertEquals(title, summary.getTitle());
	}

	@Test
	public void testTitleHighlight() {
		_summaryBuilder.setTitle(
			StringBundler.concat(
				"AAA<strong>BBB</strong>CCC", HighlightUtil.HIGHLIGHT_TAG_OPEN,
				"DDD<strong>EEE</strong>FFF", HighlightUtil.HIGHLIGHT_TAG_CLOSE,
				"GGG<strong>HHH</strong>III"));

		_summaryBuilder.setHighlight(true);

		Summary summary = _summaryBuilder.build();

		assertHighlightEquals(
			"AAA&lt;strong&gt;BBB&lt;/strong&gt;CCC[[DDD&lt;strong" +
				"&gt;EEE&lt;/strong&gt;FFF]]GGG&lt;strong&gt;HHH&lt;" +
					"/strong&gt;III",
			summary.getTitle());
	}

	@Test
	public void testTitleHighlightUnescaped() {
		_summaryBuilder.setTitle(
			StringBundler.concat(
				"AAA<strong>BBB</strong>CCC", HighlightUtil.HIGHLIGHT_TAG_OPEN,
				"DDD<strong>EEE</strong>FFF", HighlightUtil.HIGHLIGHT_TAG_CLOSE,
				"GGG<strong>HHH</strong>III"));

		_summaryBuilder.setEscape(false);
		_summaryBuilder.setHighlight(true);

		Summary summary = _summaryBuilder.build();

		assertHighlightEquals(
			"AAA<strong>BBB</strong>CCC[[DDD<strong>EEE</strong>FFF" +
				"]]GGG<strong>HHH</strong>III",
			summary.getTitle());
	}

	protected void assertHighlightEquals(String expected, String s) {
		expected = StringUtil.replace(
			expected, new String[] {"[[", "]]"}, HighlightUtil.HIGHLIGHTS);

		Assert.assertEquals(expected, s);
	}

	protected void testMaxContentLength(
		String content, int maxContentLength, String expected) {

		SummaryBuilder summaryBuilder = new SummaryBuilderImpl();

		summaryBuilder.setContent(content);
		summaryBuilder.setMaxContentLength(maxContentLength);

		Summary summary = summaryBuilder.build();

		Assert.assertEquals(expected, summary.getContent());
	}

	private void _assertNoSpaceResult(
		String highlightedContent, String normalContent, int maxLength,
		String result) {

		StringBundler sb = new StringBundler(5);

		sb.append("[[");

		if (maxLength < (highlightedContent.length() + 3)) {
			sb.append(highlightedContent.substring(0, maxLength - 3));
		}
		else {
			sb.append(highlightedContent);
		}

		sb.append("]]");

		if (maxLength > (highlightedContent.length() + 3)) {
			sb.append(
				normalContent.substring(
					0, maxLength - highlightedContent.length() - 3));
		}

		sb.append("...");

		assertHighlightEquals(sb.toString(), result);
	}

	private void _assertSpaceResult(
		String highlightedContent, String normalContent, int maxLength,
		String result) {

		StringBundler sb = new StringBundler(8);

		sb.append("[[");

		if (maxLength < (highlightedContent.length() + 3)) {
			sb.append(highlightedContent.substring(0, maxLength - 3));
		}
		else {
			sb.append(highlightedContent);
		}

		if (maxLength > (highlightedContent.length() * 2 + 3)) {
			sb.append(" ");
			sb.append(highlightedContent);
		}

		sb.append("]]");

		if (maxLength >
				(highlightedContent.
					length() * 2 + normalContent.length() + 4)) {

			sb.append(" ");
			sb.append(normalContent);
		}

		sb.append("...");

		assertHighlightEquals(sb.toString(), result);
	}

	private final SummaryBuilder _summaryBuilder = new SummaryBuilderImpl();

}