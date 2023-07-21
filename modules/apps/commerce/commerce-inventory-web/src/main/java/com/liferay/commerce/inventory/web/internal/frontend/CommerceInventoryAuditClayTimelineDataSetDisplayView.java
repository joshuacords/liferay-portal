/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.inventory.web.internal.frontend;

import com.liferay.commerce.frontend.clay.data.set.ClayDataSetDisplayView;
import com.liferay.commerce.frontend.clay.timeline.ClayTimelineDataSetDisplayView;

import org.osgi.service.component.annotations.Component;

/**
 * @author Luca Pellizzon
 */
@Component(
	immediate = true,
	property = "commerce.data.set.display.name=" + CommerceInventoryDataSetConstants.COMMERCE_DATA_SET_KEY_INVENTORY_AUDIT,
	service = ClayDataSetDisplayView.class
)
public class CommerceInventoryAuditClayTimelineDataSetDisplayView
	extends ClayTimelineDataSetDisplayView {

	@Override
	public String getDate() {
		return "date";
	}

	@Override
	public String getDescription() {
		return "description";
	}

	@Override
	public String getTitle() {
		return "title";
	}

}