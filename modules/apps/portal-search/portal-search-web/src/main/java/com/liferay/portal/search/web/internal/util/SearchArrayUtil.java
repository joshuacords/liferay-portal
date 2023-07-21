/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.web.internal.util;

import com.liferay.portal.kernel.util.ArrayUtil;

import java.util.Optional;

/**
 * @author André de Oliveira
 */
public class SearchArrayUtil {

	public static Optional<String[]> maybe(String[] texts) {
		if (ArrayUtil.isEmpty(texts)) {
			return Optional.empty();
		}

		return Optional.of(texts);
	}

}