/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.search.analysis;

import com.liferay.portal.kernel.search.SearchException;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceTracker;

import java.util.List;

/**
 * @author     David Mendez Gonzalez
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class TokenizerUtil {

	public static Tokenizer getTokenizer() {
		Tokenizer tokenizer = _tokenizerUtil._serviceTracker.getService();

		if (tokenizer == null) {
			tokenizer = _defaultTokenizer;
		}

		return tokenizer;
	}

	public static List<String> tokenize(
			String fieldName, String input, String languageId)
		throws SearchException {

		return getTokenizer().tokenize(fieldName, input, languageId);
	}

	private TokenizerUtil() {
		Registry registry = RegistryUtil.getRegistry();

		_serviceTracker = registry.trackServices(Tokenizer.class);
	}

	private static final Tokenizer _defaultTokenizer = new SimpleTokenizer();
	private static final TokenizerUtil _tokenizerUtil = new TokenizerUtil();

	private final ServiceTracker<Tokenizer, Tokenizer> _serviceTracker;

}