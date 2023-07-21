/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.frontend;

/**
 * @author Marco Leo
 */
public class ClayCreationMenuActionItem extends ClayMenuActionItem {

	public ClayCreationMenuActionItem(String href, String label) {
		super(href, "plus", label, CLAY_MENU_ACTION_ITEM_TARGET_LINK);
	}

	public ClayCreationMenuActionItem(
		String href, String label, String target) {

		super(href, "plus", label, target);
	}

}