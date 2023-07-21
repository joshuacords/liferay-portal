/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.definitions.web.internal.frontend;

import com.liferay.commerce.frontend.clay.data.set.ClayAutocompleteDataSetFilter;
import com.liferay.commerce.frontend.clay.data.set.ClayDataSetFilter;

import org.osgi.service.component.annotations.Component;

/**
 * @author Marco Leo
 */
@Component(
	immediate = true,
	property = "commerce.data.set.display.name=" + CommerceProductDataSetConstants.COMMERCE_DATA_SET_KEY_PRODUCT_DEFINITIONS,
	service = ClayDataSetFilter.class
)
public class AssetCategoryClayTableDataSetFilter
	extends ClayAutocompleteDataSetFilter {

	@Override
	public String getApiURL() {
		return "/o/headless-admin-taxonomy/v1.0/taxonomy-categories/0" +
			"/taxonomy-categories?sort=name:asc";
	}

	@Override
	public String getId() {
		return "categoryIds";
	}

	@Override
	public String getItemKey() {
		return "id";
	}

	@Override
	public String getItemLabel() {
		return "name";
	}

	@Override
	public String getLabel() {
		return "category";
	}

}