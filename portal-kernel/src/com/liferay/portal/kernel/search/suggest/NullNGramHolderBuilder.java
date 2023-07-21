/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.search.suggest;

/**
 * @author     Michael C. Han
 * @deprecated As of Judson (7.1.x), moved to {@link
 *             com.liferay.portal.search.suggest.NullNGramHolderBuilder}
 */
@Deprecated
public class NullNGramHolderBuilder implements NGramHolderBuilder {

	@Override
	public NGramHolder buildNGramHolder(String input) {
		return new NGramHolder();
	}

	@Override
	public NGramHolder buildNGramHolder(String input, int nGramMaxLength) {
		return new NGramHolder();
	}

	@Override
	public NGramHolder buildNGramHolder(
		String input, int nGramMinLength, int nGramMaxLength) {

		return new NGramHolder();
	}

}