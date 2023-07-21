/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.rescore;

import com.liferay.portal.search.query.Query;
import com.liferay.portal.search.rescore.Rescore;
import com.liferay.portal.search.rescore.RescoreBuilder;

/**
 * @author Bryan Engler
 */
public class RescoreBuilderImpl implements RescoreBuilder {

	@Override
	public Rescore build() {
		return new RescoreImpl(_query, _windowSize);
	}

	@Override
	public RescoreBuilder query(Query query) {
		_query = query;

		return this;
	}

	@Override
	public RescoreBuilder windowSize(Integer windowSize) {
		_windowSize = windowSize;

		return this;
	}

	private Query _query;
	private Integer _windowSize;

}