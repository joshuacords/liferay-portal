/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.frontend.internal.clay.data.set;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import com.liferay.commerce.frontend.clay.data.set.ClayDataSetAction;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marco Leo
 */
public class ClayDataSetDataRow {

	public ClayDataSetDataRow(Object item) {
		_item = item;

		_actionItems = new ArrayList<>();
	}

	public void addActionItems(List<ClayDataSetAction> actionItems) {
		_actionItems.addAll(actionItems);
	}

	public List<ClayDataSetAction> getActionItems() {
		return _actionItems;
	}

	@JsonUnwrapped
	public Object getItem() {
		return _item;
	}

	private final List<ClayDataSetAction> _actionItems;
	private final Object _item;

}