/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.taglib.servlet.taglib.util;

import com.liferay.petra.string.StringPool;

/**
 * @author Eudaldo Alonso
 */
public class AddMenuKeys {

	public static final int MAX_ITEMS = Integer.MAX_VALUE;

	public static String getAddMenuTypeLabel(AddMenuType addMenuType) {
		if (addMenuType == AddMenuType.FAVORITE) {
			return "favorite";
		}
		else if (addMenuType == AddMenuType.PRIMARY) {
			return "primary";
		}
		else if (addMenuType == AddMenuType.RECENT) {
			return "recent";
		}
		else if (addMenuType == AddMenuType.DEFAULT) {
			return "default";
		}

		return StringPool.BLANK;
	}

	public enum AddMenuType {

		DEFAULT, FAVORITE, PRIMARY, RECENT

	}

}