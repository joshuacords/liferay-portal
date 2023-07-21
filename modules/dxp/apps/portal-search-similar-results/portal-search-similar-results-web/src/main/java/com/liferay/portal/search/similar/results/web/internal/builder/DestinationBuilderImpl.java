/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.similar.results.web.internal.builder;

import com.liferay.petra.string.CharPool;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.URLCodec;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.similar.results.web.spi.contributor.helper.DestinationBuilder;

/**
 * @author André de Oliveira
 */
public class DestinationBuilderImpl implements DestinationBuilder {

	public DestinationBuilderImpl(String urlString, Http http) {
		_urlString = StringUtil.replace(
			URLCodec.decodeURL(urlString), CharPool.PLUS, CharPool.SPACE);
		_http = http;
	}

	public String build() {
		return _urlString;
	}

	@Override
	public DestinationBuilder replace(String oldSub, String newSub) {
		if (Validator.isNotNull(oldSub) && Validator.isNotNull(newSub)) {
			_urlString = StringUtil.replace(_urlString, oldSub, newSub);
		}

		return this;
	}

	@Override
	public DestinationBuilder replaceParameter(
		String parameter, String newValue) {

		_urlString = _http.setParameter(_urlString, parameter, newValue);

		return this;
	}

	private final Http _http;
	private String _urlString;

}