/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2025-06
 */

package com.liferay.portal.search.ml.embedding.text.util;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.feature.flag.FeatureFlagManagerUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.search.ml.embedding.text.TextEmbeddingDocumentContributor;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author Joshua Cords
 * @author Rodrigo Guedes de Souza
 */
public class TextEmbeddingContentHelper<T extends BaseModel<T>> {

	public TextEmbeddingContentHelper(
		long companyId, String defaultLanguageId, String delimiter,
		String[] languageIds, boolean localizationEnabled, T model, int size,
		TextEmbeddingDocumentContributor textEmbeddingDocumentContributor) {

		_companyId = companyId;
		_defaultLanguageId = defaultLanguageId;
		_delimiter = delimiter;

		_contentSB = new StringBundler((size * 2) - 1);

		if (localizationEnabled) {
			for (String languageId : languageIds) {
				_languageIdToContentSBMap.put(
					languageId, new StringBundler(size));
			}
		}

		_localizationEnabled = localizationEnabled;
		_model = model;
		_textEmbeddingDocumentContributor = textEmbeddingDocumentContributor;
	}

	public void appendToAll(String value) {
		_append(_contentSB, value);

		for (StringBundler localizedContentSB :
				_languageIdToContentSBMap.values()) {

			_append(localizedContentSB, value);
		}
	}

	public void appendToLanguageId(String languageId, String value) {
		_append(_languageIdToContentSBMap.get(languageId), value);
	}

	public void appendToLanguageIdAndNonlocalized(
		String languageId, String value) {

		_append(_contentSB, value);
		_append(_languageIdToContentSBMap.get(languageId), value);
	}

	public void appendToNonlocalized(String value) {
		_append(_contentSB, value);
	}

	public void contribute(Document document) {
		if (!FeatureFlagManagerUtil.isEnabled(_companyId, "LPS-122920")) {
			return;
		}

		if (_localizationEnabled) {
			for (String languageId :
					_textEmbeddingDocumentContributor.getLanguageIds(_model)) {

				StringBundler contentSB = _languageIdToContentSBMap.get(
					languageId);

				if (contentSB.length() <= 0) {
					contentSB = _languageIdToContentSBMap.get(
						_defaultLanguageId);
				}

				_textEmbeddingDocumentContributor.contribute(
					document, languageId, _model, contentSB.toString());
			}
		}
		else {
			_textEmbeddingDocumentContributor.contribute(
				document, _model, _contentSB.toString());
		}
	}

	public String getContent() {
		return _contentSB.toString();
	}

	public Map<String, String> getLanguageIdToContentMap() {
		Map<String, String> languageToContentMap = new TreeMap<>();

		if (_languageIdToContentSBMap.isEmpty() || !_localizationEnabled) {
			return languageToContentMap;
		}

		for (Map.Entry<String, StringBundler> entry :
				_languageIdToContentSBMap.entrySet()) {

			StringBundler languageContentSB = entry.getValue();

			if (languageContentSB.length() <= 0) {
				languageContentSB = _languageIdToContentSBMap.get(
					_defaultLanguageId);
			}

			languageToContentMap.put(
				entry.getKey(), languageContentSB.toString());
		}

		return languageToContentMap;
	}

	private void _append(StringBundler sb, String value) {
		if (sb == null) {
			return;
		}

		if (sb.length() > 0) {
			sb.append(_delimiter);
		}

		sb.append(value);
	}

	private final long _companyId;
	private final StringBundler _contentSB;
	private final String _defaultLanguageId;
	private final String _delimiter;
	private final Map<String, StringBundler> _languageIdToContentSBMap =
		new TreeMap<>();
	private final boolean _localizationEnabled;
	private final T _model;
	private final TextEmbeddingDocumentContributor
		_textEmbeddingDocumentContributor;

}