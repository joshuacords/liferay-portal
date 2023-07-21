/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.definitions.web.internal.frontend;

import com.liferay.commerce.frontend.clay.data.set.ClayDataSetFilter;
import com.liferay.commerce.frontend.clay.data.set.ClayRadioDataSetFilter;
import com.liferay.commerce.frontend.clay.data.set.ClayRadioDataSetFilterItem;
import com.liferay.commerce.product.type.CPType;
import com.liferay.commerce.product.type.CPTypeServicesTracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 */
@Component(
	immediate = true,
	property = "commerce.data.set.display.name=" + CommerceProductDataSetConstants.COMMERCE_DATA_SET_KEY_PRODUCT_DEFINITIONS,
	service = ClayDataSetFilter.class
)
public class ProductTypeClayTableDataSetFilter extends ClayRadioDataSetFilter {

	@Override
	public List<ClayRadioDataSetFilterItem> getClayRadioDataSetFilterItems(
		Locale locale) {

		List<ClayRadioDataSetFilterItem> clayRadioDataSetFilterItems =
			new ArrayList<>();

		for (CPType cpType : _cpTypeServicesTracker.getCPTypes()) {
			clayRadioDataSetFilterItems.add(
				new ClayRadioDataSetFilterItem(
					cpType.getLabel(locale), cpType.getName()));
		}

		return clayRadioDataSetFilterItems;
	}

	@Override
	public String getId() {
		return "productType";
	}

	@Override
	public String getLabel() {
		return "product-type";
	}

	@Reference
	private CPTypeServicesTracker _cpTypeServicesTracker;

}