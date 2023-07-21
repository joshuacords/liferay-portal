/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.rescore;

import com.liferay.portal.search.query.Query;
import com.liferay.portal.search.rescore.Rescore;

/**
 * @author Bryan Engler
 */
public class RescoreImpl implements Rescore {

	public RescoreImpl(Query query, Integer windowSize) {
		_query = query;
		_windowSize = windowSize;
	}

	public Query getQuery() {
		return _query;
	}

	public Integer getWindowSize() {
		return _windowSize;
	}

	private final Query _query;
	private final Integer _windowSize;

}