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
System.out.println(StringBundler.concat(
	"AAA<strong>BBB</strong>CCC", HighlightUtil.HIGHLIGHT_TAG_OPEN,
	"DDD<strong>EEE</strong>FFF", HighlightUtil.HIGHLIGHT_TAG_CLOSE,
	"GGG<strong>HHH</strong>III"));
		_summaryBuilder.setHighlight(true);

		Summary summary = _summaryBuilder.build();

		Assert.assertEquals(
			StringBundler.concat(
				"AAA&lt;strong&gt;BBB&lt;/strong&gt;CCC",
				HighlightUtil.HIGHLIGHTS[0],
				"DDD&lt;strong&gt;EEE&lt;/strong&gt;FFF",
				HighlightUtil.HIGHLIGHTS[1],
				"GGG&lt;strong&gt;HHH&lt;/strong&gt;III"),
			summary.getContent());
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

		Summary summary = _summaryBuilder.build();

		Assert.assertEquals(
			StringBundler.concat(
				"AAA<strong>BBB</strong>CCC", HighlightUtil.HIGHLIGHTS[0],
				"DDD<strong>EEE</strong>FFF", HighlightUtil.HIGHLIGHTS[1],
				"GGG<strong>HHH</strong>III"),
			summary.getContent());
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
	public void testContentHighlightCuttingNoSpaces() {

		StringBundler sb = new StringBundler();
		String content = "";
		String expectedContent = "";
		String actualContent = "";
		String highlightedContent = RandomTestUtil.randomString(9);
		String normalContent = RandomTestUtil.randomString(10);

		sb.append(HighlightUtil.HIGHLIGHT_TAG_OPEN);
		sb.append(highlightedContent);
		sb.append(HighlightUtil.HIGHLIGHT_TAG_CLOSE);
		sb.append(normalContent);

		_summaryBuilder.setHighlight(true);

		content = sb.toString();

		System.out.println("Input Content: " + content);

		for (int i = 0; i < 21; i++) {
			_summaryBuilder.setContent(content);
			_summaryBuilder.setMaxContentLength(i);
			Summary summary = _summaryBuilder.build();
			actualContent = summary.getContent();

			System.out.println("Length = " + i);
			System.out.println("Content = " + actualContent + "\n");

			switch (i) {
				case 0: assertHighlightEquals("", actualContent);
				break;
				case 1: assertHighlightEquals("&lt;", actualContent);
				break;
				case 2: assertHighlightEquals("&lt;l", actualContent);
					break;
				case 3: assertHighlightEquals("...", actualContent);
					break;
				case 20: assertHighlightEquals("[[" + highlightedContent + "]]" + normalContent, actualContent);
				break;
				default: _assertNoSpaceResult(highlightedContent, normalContent, i, actualContent);
			}
		}
	}

	@Test
	public void testContentHighlightCuttingSpaces() {

		StringBundler sb = new StringBundler();
		String content = "";
		String expectedContent = "";
		String actualContent = "";
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

		System.out.println("Input Content: " + content);

		for (int i = 0; i < 20; i++) {
			_summaryBuilder.setContent(content);
			_summaryBuilder.setMaxContentLength(i);
			Summary summary = _summaryBuilder.build();
			actualContent = summary.getContent();

			System.out.println("Length = " + i);
			System.out.println("Content = " + actualContent + "\n");

			switch (i) {
				case 0: assertHighlightEquals("", actualContent);
					break;
				case 1: assertHighlightEquals("&lt;", actualContent);
					break;
				case 2: assertHighlightEquals("&lt;l", actualContent);
					break;
				case 3: assertHighlightEquals("...", actualContent);
					break;
				case 19: sb = new StringBundler(23);
					sb.append("[[");
					sb.append(highlightedContent);
					sb.append(" ");
					sb.append(highlightedContent);
					sb.append("]]");
					sb.append(" ");
					sb.append(normalContent);
					sb.append(" ");
					sb.append(normalContent);
					assertHighlightEquals(sb.toString(), actualContent);
					break;
				default: _assertSpaceResult(highlightedContent, normalContent, i, actualContent);
			}
		}
	}

	@Test
	public void testHighlightTagUncutOnShorten() {
		int hloLength = HighlightUtil.HIGHLIGHT_TAG_OPEN.length();
		int hlcLength = HighlightUtil.HIGHLIGHT_TAG_CLOSE.length();
		int expectedLength = 0;

		StringBundler sb = new StringBundler();
		String content = "";
		String expectedContent = "";
		String highlightedContent = RandomTestUtil.randomString(3);
		String normalContent = RandomTestUtil.randomString(11);

		sb.append(HighlightUtil.HIGHLIGHT_TAG_OPEN);
		sb.append(highlightedContent);
		sb.append(HighlightUtil.HIGHLIGHT_TAG_CLOSE);
		sb.append(normalContent);

		content = sb.toString();

		System.out.println("Content:" + content);

		//Test 1 - cut close tag:
		//Content = "<liferay-hl>Red</liferay-hl> Other Text"
		//MaxLength = 20
		_summaryBuilder.setContent(content);
		_summaryBuilder.setMaxContentLength(20);
		_summaryBuilder.setHighlight(true);
		Summary summary = _summaryBuilder.build();

		//Expected Result = "<liferay-hl>Red</liferay-hl>  Other Text"
		expectedContent = "[[" + highlightedContent + "]]" + normalContent;
		assertHighlightEquals(expectedContent, summary.getContent());

		//Test 2 - add missing close tag:
		//Content = "<liferay-hl>Red</liferay-hl> Other Text"
		//MaxLength = 18
		_summaryBuilder.setContent(content);
		_summaryBuilder.setMaxContentLength(18);
		summary = _summaryBuilder.build();

		//Expected Result = "<liferay-hl>Red</liferay-hl> Other Text"
		assertHighlightEquals(expectedContent, summary.getContent());

		//Test 3 - cut open tag:
		//Content = "<liferay-hl>Red</liferay-hl> Other Text"
		//MaxLength = 10
		_summaryBuilder.setContent(content);
		_summaryBuilder.setMaxContentLength(10);
		summary = _summaryBuilder.build();

		//Expected Result = "..."expectedContent = "...";
		expectedContent = expectedContent.substring(0, 11) + "...";
		System.out.println("Summary.getContent: " + summary.getContent());
		System.out.println("expectedContent: " + expectedContent);

		assertHighlightEquals(expectedContent, summary.getContent());

		//Test 4 - cut open tag with short content:
		//Content = "Text-<liferay-hl>Red</liferay-hl>"
		//MaxLength = 9
		sb = new StringBundler();

		highlightedContent = RandomTestUtil.randomString(3);
		normalContent = RandomTestUtil.randomString(5);

		sb.append(normalContent);
		sb.append(HighlightUtil.HIGHLIGHT_TAG_OPEN);
		sb.append(highlightedContent);
		sb.append(HighlightUtil.HIGHLIGHT_TAG_CLOSE);

		content = sb.toString();

		_summaryBuilder.setContent(content);
		_summaryBuilder.setMaxContentLength(9);
		summary = _summaryBuilder.build();

		//Expected Result = "Text-[[Red]]"
		expectedContent = normalContent + "[[" + highlightedContent + "]]";
		assertHighlightEquals(expectedContent, summary.getContent());

		//Test 5 - content with "<" and no tags
		//content = "Text-<Other Chars"
		//Max Length = 12
		sb = new StringBundler();

		sb.append(normalContent);
		sb.append("<");
		sb.append(normalContent);

		content = sb.toString();

		System.out.println("Last Content:" + content);

		_summaryBuilder.setContent(content);
		_summaryBuilder.setMaxContentLength(8);
		summary = _summaryBuilder.build();
		//Expected Result = "Text-<Oth..."
		expectedContent = content.substring(0, 5) + "...";
		assertHighlightEquals(expectedContent, summary.getContent());
	}

	@Test
	public void testMaxHighlightedContentLength() {
		StringBundler sb = new StringBundler();

		String expectedResult = "";
		String normalContent = RandomTestUtil.randomString(8);
		String highlightedContent = RandomTestUtil.randomString(8);

		sb.append(normalContent);
		sb.append(" ");
		sb.append(HighlightUtil.HIGHLIGHT_TAG_OPEN);
		sb.append(highlightedContent);
		sb.append(HighlightUtil.HIGHLIGHT_TAG_CLOSE);
		sb.append(" ");
		sb.append(normalContent);

		String content = sb.toString();

		//System.out.println("Content:" + content);

		//Test before highlight tag
		_summaryBuilder.setContent(content);

		_summaryBuilder.setHighlight(true);

		_summaryBuilder.setMaxContentLength(8);

		Summary summary = _summaryBuilder.build();

		//Should only return word before test
		expectedResult = content.substring(0, 5) + "...";
		assertHighlightEquals(expectedResult, summary.getContent());

		//Test split on opening highlight tag
		_summaryBuilder.setContent(content);

		_summaryBuilder.setMaxContentLength(12);

		summary = _summaryBuilder.build();

		//Should only return word before highlighted portion
		expectedResult = normalContent + "...";
		assertHighlightEquals(expectedResult, summary.getContent());

		//Test split on highlighted portion
		_summaryBuilder.setContent(content);

		_summaryBuilder.setMaxContentLength(15);

		summary = _summaryBuilder.build();

		//Should still only return word before highlighted portion
		assertHighlightEquals(expectedResult, summary.getContent());

		//Test split at the end of the highlighted portion before closing highlight tag
		_summaryBuilder.setContent(content);

		_summaryBuilder.setMaxContentLength(20);

		summary = _summaryBuilder.build();

		//Should return word before highlighted portion and highlighted portion with an ending tag
		expectedResult = normalContent + " [[" + highlightedContent + "]]" + "...";
		assertHighlightEquals(expectedResult, summary.getContent());

		//Test split on closing highlight tag
//		_summaryBuilder.setContent(content);
//
//		_summaryBuilder.setMaxContentLength(18);
//
//		summary = _summaryBuilder.build();
//
//		//Should return word before highlighted portion and highlighted portion and the first space character
//		expectedResult = normalContent + "[[" + highlightedContent + "]]" + " ";
//		assertHighlightEquals(expectedResult, summary.getContent());

		//Test where content fits in max content
		_summaryBuilder.setContent(content);

		_summaryBuilder.setMaxContentLength(200);

		summary = _summaryBuilder.build();

		//Should return word before highlighted portion and highlighted portion
		expectedResult = normalContent + " [[" + highlightedContent + "]] " + normalContent;
		assertHighlightEquals(expectedResult, summary.getContent());

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

		Assert.assertEquals(
			StringBundler.concat(
				"AAA&lt;strong&gt;BBB&lt;/strong&gt;CCC",
				HighlightUtil.HIGHLIGHTS[0],
				"DDD&lt;strong&gt;EEE&lt;/strong&gt;FFF",
				HighlightUtil.HIGHLIGHTS[1],
				"GGG&lt;strong&gt;HHH&lt;/strong&gt;III"),
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

		Assert.assertEquals(
			StringBundler.concat(
				"AAA<strong>BBB</strong>CCC", HighlightUtil.HIGHLIGHTS[0],
				"DDD<strong>EEE</strong>FFF", HighlightUtil.HIGHLIGHTS[1],
				"GGG<strong>HHH</strong>III"),
			summary.getTitle());
	}

	protected void testMaxContentLength(
		String content, int maxContentLength, String expected) {

		SummaryBuilder summaryBuilder = new SummaryBuilderImpl();

		summaryBuilder.setContent(content);
		summaryBuilder.setMaxContentLength(maxContentLength);

		Summary summary = summaryBuilder.build();

		Assert.assertEquals(expected, summary.getContent());
	}

	protected void assertHighlightEquals(
		String expected, String s) {

		expected = StringUtil.replace(
			expected, new String[] {"[[", "]]"}, HighlightUtil.HIGHLIGHTS);

		Assert.assertEquals(expected, s);
	}

	private void _assertNoSpaceResult(String highlightedContent, String normalContent, int maxLength, String result) {
		StringBundler sb = new StringBundler();

		sb.append("[[");

		if (maxLength < (highlightedContent.length() + 3)) {
			sb.append(highlightedContent.substring(0, maxLength - 3));
		} else {
			sb.append(highlightedContent);
		}

		sb.append("]]");

		if (maxLength > (highlightedContent.length() + 3)) {
			sb.append(normalContent.substring(0, maxLength - highlightedContent.length() - 3));
		}

		sb.append("...");

		System.out.println("Expected Content:" + sb.toString());

		assertHighlightEquals(sb.toString(), result);
	}

	private void _assertSpaceResult(String highlightedContent, String normalContent, int maxLength, String result) {
		StringBundler sb = new StringBundler();

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

		if (maxLength > (highlightedContent.length() * 2 + normalContent.length() + 4)) {
			sb.append(" ");
			sb.append(normalContent);
		}
		sb.append("...");

		System.out.println("Expected Content:" + sb.toString());

		assertHighlightEquals(sb.toString(), result);
	}

	private final SummaryBuilder _summaryBuilder = new SummaryBuilderImpl();

}