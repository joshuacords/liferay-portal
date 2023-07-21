/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.item.selector.taglib.internal.servlet.item.selector;

import com.liferay.item.selector.ItemSelector;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Roberto Díaz
 */
@Component(immediate = true, service = {})
public class ItemSelectorUtil {

	public static final ItemSelector getItemSelector() {
		return _itemSelectorUtil._getItemSelector();
	}

	@Activate
	protected void activate() {
		_itemSelectorUtil = this;
	}

	@Deactivate
	protected void deactivate() {
		_itemSelectorUtil = null;
	}

	@Reference(unbind = "-")
	protected void setItemSelector(ItemSelector itemSelector) {
		_itemSelector = itemSelector;
	}

	private ItemSelector _getItemSelector() {
		return _itemSelector;
	}

	private static ItemSelectorUtil _itemSelectorUtil;

	private ItemSelector _itemSelector;

}