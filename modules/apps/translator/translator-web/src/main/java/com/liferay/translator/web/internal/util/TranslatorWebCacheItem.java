/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.translator.web.internal.util;

import com.liferay.portal.kernel.microsofttranslator.MicrosoftTranslator;
import com.liferay.portal.kernel.microsofttranslator.MicrosoftTranslatorFactoryUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.webcache.WebCacheException;
import com.liferay.portal.kernel.webcache.WebCacheItem;
import com.liferay.translator.web.internal.model.Translation;

/**
 * @author Brian Wing Shun Chan
 * @author Hugo Huijser
 */
public class TranslatorWebCacheItem implements WebCacheItem {

	public TranslatorWebCacheItem(
		String fromLanguageId, String toLanguageId, String fromText) {

		_fromLanguageId = fromLanguageId;
		_toLanguageId = toLanguageId;
		_fromText = fromText;
	}

	@Override
	public Object convert(String key) throws WebCacheException {
		Translation translation = new Translation(
			_fromLanguageId, _toLanguageId, _fromText);

		try {
			MicrosoftTranslator microsoftTranslator =
				MicrosoftTranslatorFactoryUtil.getMicrosoftTranslator();

			String toText = microsoftTranslator.translate(
				_fromLanguageId, _toLanguageId, _fromText);

			translation.setToText(toText);
		}
		catch (Exception exception) {
			throw new WebCacheException(exception);
		}

		return translation;
	}

	@Override
	public long getRefreshTime() {
		return _REFRESH_TIME;
	}

	private static final long _REFRESH_TIME = Time.DAY * 90;

	private final String _fromLanguageId;
	private final String _fromText;
	private final String _toLanguageId;

}