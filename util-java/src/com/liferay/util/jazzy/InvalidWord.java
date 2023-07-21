/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.util.jazzy;

import java.util.List;

/**
 * @author     Brian Wing Shun Chan
 * @deprecated As of Wilberforce (7.0.x), moved to {@link
 *             com.liferay.portal.kernel.jazzy.InvalidWord}
 */
@Deprecated
public class InvalidWord extends com.liferay.portal.kernel.jazzy.InvalidWord {

	public InvalidWord(
		String invalidWord, List<String> suggestions, String wordContext,
		int wordContextPosition) {

		super(invalidWord, suggestions, wordContext, wordContextPosition);
	}

}