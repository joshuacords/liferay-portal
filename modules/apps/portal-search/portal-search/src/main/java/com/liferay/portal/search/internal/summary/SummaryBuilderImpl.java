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

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.search.highlight.HighlightUtil;
import com.liferay.portal.kernel.util.Html;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.summary.Summary;
import com.liferay.portal.search.summary.SummaryBuilder;
import com.liferay.portal.util.HtmlImpl;

import java.util.Locale;

/**
 * @author André de Oliveira
 * @author Bryan Engler
 * @author Ryan Park
 * @author Tibor Lipusz
 */
public class SummaryBuilderImpl implements SummaryBuilder {

	@Override
	public Summary build() {
		return new SummaryImpl(buildTitle(), buildContent(), _locale);
	}

	@Override
	public void setContent(String content) {
		_content = content;
	}

	@Override
	public void setEscape(boolean escape) {
		_escape = escape;
	}

	@Override
	public void setHighlight(boolean highlight) {
		_highlight = highlight;
	}

	@Override
	public void setLocale(Locale locale) {
		_locale = locale;
	}

	@Override
	public void setMaxContentLength(int maxContentLength) {
		_maxContentLength = maxContentLength;
	}

	@Override
	public void setTitle(String title) {
		_title = title;
	}

	protected String buildContent() {
		if (Validator.isNull(_content)) {
			return StringPool.BLANK;
		}

		if (_highlight) {
			return buildContentHighlighted();
		}

		return buildContentPlain();
	}

	protected String buildContentHighlighted() {
		int virtualMaxContentLength = _maxContentLength;

		if (_maxContentLength < _content.length()) {
			virtualMaxContentLength = _recalculateHighlightMaxLength();
		}

		_shortenHighlightedContent(virtualMaxContentLength);

		if (_content.lastIndexOf(HighlightUtil.HIGHLIGHT_TAG_CLOSE) <
				_content.lastIndexOf(HighlightUtil.HIGHLIGHT_TAG_OPEN)) {

			if (_content.endsWith("...")) {
				StringBuilder sb = new StringBuilder(
					_maxContentLength +
						HighlightUtil.HIGHLIGHT_TAG_CLOSE.length());

				sb.append(_content.substring(0, _content.lastIndexOf("...")));
				sb.append(HighlightUtil.HIGHLIGHT_TAG_CLOSE);
				sb.append("...");

				_content = sb.toString();
			}
			else {
				_content.concat(HighlightUtil.HIGHLIGHT_TAG_CLOSE);
			}
		}

		return _escapeAndHighlight(_content);
	}

	protected String buildContentPlain() {
		if ((_maxContentLength <= 0) ||
			(_content.length() <= _maxContentLength)) {

			return _content;
		}

		return StringUtil.shorten(_content, _maxContentLength);
	}

	protected String buildTitle() {
		if (Validator.isNull(_title)) {
			return StringPool.BLANK;
		}

		if (_highlight) {
			return buildTitleHighlighted();
		}

		return buildTitlePlain();
	}

	protected String buildTitleHighlighted() {
		return _escapeAndHighlight(_title);
	}

	protected String buildTitlePlain() {
		return _title;
	}

	private String _escapeAndHighlight(String text) {
		text = StringUtil.replace(
			text, _HIGHLIGHT_TAGS, _ESCAPE_SAFE_HIGHLIGHTS);

		if (_escape) {
			text = _html.escape(text);
		}

		text = StringUtil.replace(
			text, _ESCAPE_SAFE_HIGHLIGHTS, HighlightUtil.HIGHLIGHTS);

		return text;
	}

	private int _recalculateHighlightMaxLength() {
		String extractedContent = _html.extractText(_content);

		if (extractedContent.length() <= _maxContentLength) {
			_maxContentLength = _content.length();

			return _maxContentLength;
		}

		int currentLength = 0;
		int highlightLength = 0;
		int indexClose = _content.indexOf(
			HighlightUtil.HIGHLIGHT_TAG_CLOSE, currentLength);
		int indexOpen = _content.indexOf(
			HighlightUtil.HIGHLIGHT_TAG_OPEN, currentLength);

		while ((indexClose > 0) || (indexOpen > 0)) {
			if ((indexOpen > -1) &&
				((indexOpen < indexClose) || (indexClose < 0))) {

				currentLength = indexOpen - highlightLength;

				if (currentLength >= _maxContentLength) {
					break;
				}

				highlightLength += HighlightUtil.HIGHLIGHT_TAG_OPEN.length();
				indexOpen = _content.indexOf(
					HighlightUtil.HIGHLIGHT_TAG_OPEN,
					currentLength + highlightLength);
			}
			else if ((indexClose > -1) &&
					 ((indexClose < indexOpen) || (indexOpen < 0))) {

				currentLength = indexClose - highlightLength;

				if (currentLength >= _maxContentLength) {
					break;
				}

				highlightLength += HighlightUtil.HIGHLIGHT_TAG_CLOSE.length();
				indexClose = _content.indexOf(
					HighlightUtil.HIGHLIGHT_TAG_CLOSE,
					currentLength + highlightLength);
			}
		}

		return _maxContentLength + highlightLength;
	}

	private void _shortenHighlightedContent(int virtualMaxContentLength) {
		if (_content.length() > _maxContentLength) {
			if (_maxContentLength < 4) {
				if (_maxContentLength < 0) {
					_maxContentLength = 0;
				}

				if (_maxContentLength == 3) {
					_content = "...";
				}
				else {
					_content = _content.substring(0, _maxContentLength);
				}

				return;
			}

			String shortenedContent = StringUtil.shorten(
				_content, virtualMaxContentLength - 3, "");

			int indexLastTagOpen = shortenedContent.lastIndexOf("<");

			if ((indexLastTagOpen > -1) &&
				(indexLastTagOpen >
					(virtualMaxContentLength - HighlightUtil.
						HIGHLIGHT_TAG_CLOSE.length() - 3))) {

				int endIndex =
					indexLastTagOpen +
						HighlightUtil.HIGHLIGHT_TAG_CLOSE.length();

				if (endIndex > _content.length()) {
					endIndex = _content.length();
				}

				String ending = _content.substring(indexLastTagOpen, endIndex);
				int cutLength = 3;

				if (ending.contains(HighlightUtil.HIGHLIGHT_TAG_CLOSE)) {
					cutLength -= virtualMaxContentLength - indexLastTagOpen -
						HighlightUtil.HIGHLIGHT_TAG_CLOSE.length();
					shortenedContent = _content.substring(
						0, indexLastTagOpen - cutLength);

					shortenedContent += HighlightUtil.HIGHLIGHT_TAG_CLOSE;
				}
				else if (ending.contains(HighlightUtil.HIGHLIGHT_TAG_OPEN)) {
					cutLength -= virtualMaxContentLength - indexLastTagOpen -
						HighlightUtil.HIGHLIGHT_TAG_OPEN.length();
					shortenedContent = _content.substring(
						0, indexLastTagOpen - cutLength);

					shortenedContent += HighlightUtil.HIGHLIGHT_TAG_OPEN;
				}
			}

			shortenedContent = shortenedContent.concat("...");

			_content = shortenedContent;
		}
	}

	private static final String[] _ESCAPE_SAFE_HIGHLIGHTS =
		{"[@HIGHLIGHT1@]", "[@HIGHLIGHT2@]"};

	private static final String[] _HIGHLIGHT_TAGS =
		{HighlightUtil.HIGHLIGHT_TAG_OPEN, HighlightUtil.HIGHLIGHT_TAG_CLOSE};

	private String _content;
	private boolean _escape = true;
	private boolean _highlight;
	private final Html _html = new HtmlImpl();
	private Locale _locale;
	private int _maxContentLength;
	private String _title;

}