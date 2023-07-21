/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.repository.search;

import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceTracker;

/**
 * @author Mika Koivisto
 */
public class RepositorySearchQueryBuilderUtil {

	public static BooleanQuery getFullQuery(SearchContext searchContext)
		throws SearchException {

		return getRepositorySearchQueryBuilder().getFullQuery(searchContext);
	}

	public static RepositorySearchQueryBuilder
		getRepositorySearchQueryBuilder() {

		return _repositorySearchQueryBuilderUtil._serviceTracker.getService();
	}

	public RepositorySearchQueryBuilderUtil() {
		Registry registry = RegistryUtil.getRegistry();

		_serviceTracker = registry.trackServices(
			RepositorySearchQueryBuilder.class);

		_serviceTracker.open();
	}

	private static final RepositorySearchQueryBuilderUtil
		_repositorySearchQueryBuilderUtil =
			new RepositorySearchQueryBuilderUtil();

	private final ServiceTracker
		<RepositorySearchQueryBuilder, RepositorySearchQueryBuilder>
			_serviceTracker;

}