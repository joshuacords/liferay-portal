/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.search.suggest;

import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.ServiceProxyFactory;

import java.util.List;
import java.util.Map;

/**
 * @author     Michael C. Han
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class CollatorUtil {

	public static String collate(
			Map<String, List<String>> suggestions, List<String> keywords)
		throws SearchException {

		return _collator.collate(suggestions, keywords);
	}

	private static volatile Collator _collator =
		ServiceProxyFactory.newServiceTrackedInstance(
			Collator.class, CollatorUtil.class, "_collator", false);

}