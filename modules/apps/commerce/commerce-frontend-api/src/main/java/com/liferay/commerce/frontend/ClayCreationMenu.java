/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.frontend;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marco Leo
 */
public class ClayCreationMenu {

	public ClayCreationMenu() {
		_clayCreationMenuActionItems = new ArrayList<>();
	}

	public void addClayCreationMenuActionItem(
		ClayCreationMenuActionItem clayCreationMenuActionItem) {

		_clayCreationMenuActionItems.add(clayCreationMenuActionItem);
	}

	public void addClayCreationMenuActionItem(String url, String label) {
		addClayCreationMenuActionItem(
			url, label, ClayMenuActionItem.CLAY_MENU_ACTION_ITEM_TARGET_LINK);
	}

	public void addClayCreationMenuActionItem(
		String url, String label, String target) {

		_clayCreationMenuActionItems.add(
			new ClayCreationMenuActionItem(url, label, target));
	}

	public List<ClayCreationMenuActionItem> getClayCreationMenuActionItems() {
		return _clayCreationMenuActionItems;
	}

	private final List<ClayCreationMenuActionItem> _clayCreationMenuActionItems;

}