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
 * @author Rodrigo Guedes de Souza
 * @author Joshua Cords
 */
public class TextEmbeddingContentHelper<T extends BaseModel<T>> {

	public TextEmbeddingContentHelper(
		long companyId, String delimiter, boolean localizationEnabled, T model,
		boolean nonlocalizedEnabled, int size,
		TextEmbeddingDocumentContributor textEmbeddingDocumentContributor) {

		if (!localizationEnabled && !nonlocalizedEnabled) {
			throw new RuntimeException();
		}

		_companyId = companyId;
		_delimiter = delimiter;
		_localizationEnabled = localizationEnabled;
		_model = model;
		_textEmbeddingDocumentContributor = textEmbeddingDocumentContributor;

		_nonlocalizedContentSB = new StringBundler((size * 2) - 1);

		if (!localizationEnabled) {
			return;
		}

		for (String languageId :
				textEmbeddingDocumentContributor.getLanguageIds(model)) {

			_localizedContentSBMap.put(languageId, new StringBundler(size));
		}
	}

	public void appendToAll(String value) {
		_append(_nonlocalizedContentSB, value);

		for (StringBundler localizedContentSB :
				_localizedContentSBMap.values()) {

			_append(localizedContentSB, value);
		}
	}

	public void appendToLocale(String languageId, String value) {
		_append(_localizedContentSBMap.get(languageId), value);
	}

	public void appendToLocalizedAndNonlocalized(
		String languageId, String value) {

		_append(_nonlocalizedContentSB, value);
		_append(_localizedContentSBMap.get(languageId), value);
	}

	public void contribute(Document document) {
		if (!FeatureFlagManagerUtil.isEnabled(_companyId, "LPS-122920")) {
			return;
		}

		if (_localizationEnabled) {
			for (Map.Entry<String, StringBundler> localizedContent :
					_localizedContentSBMap.entrySet()) {

				StringBundler localizedSB = localizedContent.getValue();

				_textEmbeddingDocumentContributor.contribute(
					document, localizedContent.getKey(), _model,
					localizedSB.toString());
			}
		}
		else {
			_textEmbeddingDocumentContributor.contribute(
				document, _model, _nonlocalizedContentSB.toString());
		}
	}

	public Map<String, String> getLocalizedContentMap() {
		Map<String, String> localizedContentMap = new TreeMap<>();

		if (_localizedContentSBMap.isEmpty()) {
			return localizedContentMap;
		}

		for (Map.Entry<String, StringBundler> entry :
				_localizedContentSBMap.entrySet()) {

			StringBundler localizedContentSB = entry.getValue();

			if ((localizedContentSB != null) &&
				(localizedContentSB.length() != 0)) {

				localizedContentMap.put(
					entry.getKey(), localizedContentSB.toString());
			}
		}

		return localizedContentMap;
	}

	public String getNonlocalizedContent() {
		return _nonlocalizedContentSB.toString();
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
	private final String _delimiter;
	private final boolean _localizationEnabled;
	private final Map<String, StringBundler> _localizedContentSBMap =
		new TreeMap<>();
	private final T _model;
	private final StringBundler _nonlocalizedContentSB;
	private final TextEmbeddingDocumentContributor
		_textEmbeddingDocumentContributor;

}