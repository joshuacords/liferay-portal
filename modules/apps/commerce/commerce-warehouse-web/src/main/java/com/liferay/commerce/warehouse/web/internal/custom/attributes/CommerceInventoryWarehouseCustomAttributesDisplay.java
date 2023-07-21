/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.warehouse.web.internal.custom.attributes;

import com.liferay.commerce.admin.constants.CommerceAdminPortletKeys;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouse;
import com.liferay.expando.kernel.model.BaseCustomAttributesDisplay;
import com.liferay.expando.kernel.model.CustomAttributesDisplay;

import org.osgi.service.component.annotations.Component;

/**
 * @author Ethan Bustad
 */
@Component(
	immediate = true,
	property = "javax.portlet.name=" + CommerceAdminPortletKeys.COMMERCE_ADMIN,
	service = CustomAttributesDisplay.class
)
public class CommerceInventoryWarehouseCustomAttributesDisplay
	extends BaseCustomAttributesDisplay {

	@Override
	public String getClassName() {
		return CommerceInventoryWarehouse.class.getName();
	}

	@Override
	public String getIconCssClass() {
		return "tag";
	}

}