/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.internal.search.spi.model.result.contributor;

import com.liferay.object.constants.ObjectEntrySearchConstants;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.spi.model.result.contributor.ModelSummaryContributor;

import java.util.Locale;

/**
 * @author Bryan Engler
 * @author Joshua Cords
 */
public class ObjectEntryModelSummaryContributor
	implements ModelSummaryContributor {

	@Override
	public Summary getSummary(
		Document document, Locale locale, String snippet) {

		Locale defaultLocale = LocaleUtil.fromLanguageId(
			document.get(ObjectEntrySearchConstants.DEFAULT_LANGUAGE_ID));

		Locale snippetLocale = _getSnippetLocale(document, locale);

		if (snippetLocale == null) {
			snippetLocale = defaultLocale;
		}

		Summary summary = new Summary(
			_getTitle(defaultLocale, document, snippetLocale),
			_getContent(defaultLocale, document, snippetLocale));

		summary.setMaxContentLength(200);

		return summary;
	}

	private String _getContent(
		Locale defaultLocale, Document document, Locale snippetLocale) {

		String content = _getLocalizedContent(document, snippetLocale);

		if (Validator.isBlank(content) && (defaultLocale != null) &&
			!defaultLocale.equals(snippetLocale)) {

			content = _getLocalizedContent(document, defaultLocale);
		}

		if (Validator.isBlank(content)) {
			content = document.get(
				StringBundler.concat(
					Field.SNIPPET, StringPool.UNDERLINE,
					ObjectEntrySearchConstants.OBJECT_ENTRY_CONTENT));
		}

		if (Validator.isBlank(content)) {
			content = document.get(
				ObjectEntrySearchConstants.OBJECT_ENTRY_CONTENT);
		}

		return content;
	}

	private String _getLocalizedContent(Document document, Locale locale) {
		if (locale == null) {
			return StringPool.BLANK;
		}

		String localizedNestedValueSnippetName = StringBundler.concat(
			Field.SNIPPET, StringPool.UNDERLINE,
			Field.getLocalizedName(
				locale, ObjectEntrySearchConstants.NESTED_FIELD_ARRAY_VALUE));

		String content = document.get(localizedNestedValueSnippetName);

		if (!Validator.isBlank(content)) {
			return content;
		}

		String localizedContentSnippetName = StringBundler.concat(
			Field.SNIPPET, StringPool.UNDERLINE,
			Field.getLocalizedName(
				locale, ObjectEntrySearchConstants.OBJECT_ENTRY_CONTENT));

		content = document.get(localizedContentSnippetName);

		if (!Validator.isBlank(content)) {
			return content;
		}

		return document.get(
			Field.getLocalizedName(
				locale, ObjectEntrySearchConstants.OBJECT_ENTRY_CONTENT));
	}

	private Locale _getSnippetLocale(Document document, Locale locale) {
		if (locale == null) {
			return null;
		}

		String localizedContentSnippetName = StringBundler.concat(
			Field.SNIPPET, StringPool.UNDERLINE,
			Field.getLocalizedName(
				locale, ObjectEntrySearchConstants.OBJECT_ENTRY_CONTENT));
		String localizedNestedValueSnippetName = StringBundler.concat(
			Field.SNIPPET, StringPool.UNDERLINE,
			Field.getLocalizedName(
				locale, ObjectEntrySearchConstants.NESTED_FIELD_ARRAY_VALUE));
		String localizedTitleSnippetName = StringBundler.concat(
			Field.SNIPPET, StringPool.UNDERLINE,
			Field.getLocalizedName(
				locale, ObjectEntrySearchConstants.OBJECT_ENTRY_TITLE));

		if ((document.getField(localizedContentSnippetName) != null) ||
			(document.getField(localizedNestedValueSnippetName) != null) ||
			(document.getField(localizedTitleSnippetName) != null)) {

			return locale;
		}

		return null;
	}

	private String _getTitle(
		Locale defaultLocale, Document document, Locale snippetLocale) {

		String title = document.get(
			snippetLocale,
			StringBundler.concat(
				Field.SNIPPET, StringPool.UNDERLINE,
				ObjectEntrySearchConstants.OBJECT_ENTRY_TITLE),
			ObjectEntrySearchConstants.OBJECT_ENTRY_TITLE);

		if (Validator.isBlank(title) && (defaultLocale != null) &&
			!defaultLocale.equals(snippetLocale)) {

			title = document.get(
				defaultLocale,
				StringBundler.concat(
					Field.SNIPPET, StringPool.UNDERLINE,
					ObjectEntrySearchConstants.OBJECT_ENTRY_TITLE),
				ObjectEntrySearchConstants.OBJECT_ENTRY_TITLE);
		}

		if (Validator.isBlank(title)) {
			title = document.get(
				StringBundler.concat(
					Field.SNIPPET, StringPool.UNDERLINE,
					ObjectEntrySearchConstants.OBJECT_ENTRY_TITLE));
		}

		if (Validator.isBlank(title)) {
			title = document.get(ObjectEntrySearchConstants.OBJECT_ENTRY_TITLE);
		}

		if (Validator.isBlank(title)) {
			title = document.get(
				StringBundler.concat(
					Field.SNIPPET, StringPool.UNDERLINE, Field.ENTRY_CLASS_PK));
		}

		if (Validator.isBlank(title)) {
			title = document.get(Field.ENTRY_CLASS_PK);
		}

		return title;
	}

}