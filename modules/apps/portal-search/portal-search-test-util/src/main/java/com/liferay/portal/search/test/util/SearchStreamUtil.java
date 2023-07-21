/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.test.util;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * @author André de Oliveira
 */
public class SearchStreamUtil {

	public static <T> Stream<T> stream(Collection<T> collection) {
		return collection.stream();
	}

}