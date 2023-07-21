/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.frontend.internal.clay.data.set;

import java.util.List;

/**
 * @author Marco Leo
 */
public class ClayDataSetResponse {

	public ClayDataSetResponse(List<ClayDataSetDataRow> items, int totalItems) {
		_items = items;
		_totalItems = totalItems;
	}

	public List<ClayDataSetDataRow> getItems() {
		return _items;
	}

	public int getTotalItems() {
		return _totalItems;
	}

	private final List<ClayDataSetDataRow> _items;
	private final int _totalItems;

}