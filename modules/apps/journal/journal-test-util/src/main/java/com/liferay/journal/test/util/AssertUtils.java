/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.test.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

/**
 * @author     André de Oliveira
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class AssertUtils {

	public static void assertEquals(
		String message, Map<?, ?> expectedMap, Map<?, ?> actualMap) {

		Assert.assertEquals(
			message, _toString(expectedMap), _toString(actualMap));
	}

	private static String _toString(Map<?, ?> map) {
		List<String> list = new ArrayList<>(map.size());

		for (Map.Entry<?, ?> entry : map.entrySet()) {
			list.add(entry.toString());
		}

		Collections.sort(list);

		return list.toString();
	}

}