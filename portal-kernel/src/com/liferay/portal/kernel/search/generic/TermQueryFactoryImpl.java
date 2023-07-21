/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.search.generic;

import com.liferay.portal.kernel.search.TermQuery;
import com.liferay.portal.kernel.search.TermQueryFactory;

/**
 * @author     Michael C. Han
 * @deprecated As of Wilberforce (7.0.x)
 */
@Deprecated
public class TermQueryFactoryImpl implements TermQueryFactory {

	@Override
	public TermQuery create(String field, long value) {
		return create(field, String.valueOf(value));
	}

	@Override
	public TermQuery create(String field, String value) {
		return new TermQueryImpl(new QueryTermImpl(field, value));
	}

}