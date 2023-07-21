/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.pricing.web.internal.frontend;

import com.liferay.commerce.frontend.clay.data.set.ClayDataSetDisplayView;
import com.liferay.commerce.frontend.clay.table.ClayTableDataSetDisplayView;
import com.liferay.commerce.frontend.clay.table.ClayTableSchema;
import com.liferay.commerce.frontend.clay.table.ClayTableSchemaBuilder;
import com.liferay.commerce.frontend.clay.table.ClayTableSchemaBuilderFactory;
import com.liferay.commerce.frontend.clay.table.ClayTableSchemaField;
import com.liferay.commerce.pricing.web.internal.frontend.constants.CommercePricingDataSetConstants;
import com.liferay.petra.string.StringPool;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = "commerce.data.set.display.name=" + CommercePricingDataSetConstants.COMMERCE_DATA_SET_KEY_PRICE_LIST_ENTRIES,
	service = ClayDataSetDisplayView.class
)
public class CommercePriceEntryClayTableDataSetDisplayView
	extends ClayTableDataSetDisplayView {

	@Override
	public ClayTableSchema getClayTableSchema() {
		ClayTableSchemaBuilder clayTableSchemaBuilder =
			_clayTableSchemaBuilderFactory.clayTableSchemaBuilder();

		ClayTableSchemaField imageField = clayTableSchemaBuilder.addField(
			"product.thumbnail", StringPool.BLANK);

		imageField.setContentRenderer("image");

		ClayTableSchemaField skuField = clayTableSchemaBuilder.addField(
			"sku.name", "sku");

		skuField.setContentRenderer("actionLink");

		clayTableSchemaBuilder.addField("product.name.LANG", "name");

		clayTableSchemaBuilder.addField("sku.basePriceFormatted", "base-price");

		clayTableSchemaBuilder.addField("priceFormatted", "price-list-price");

		clayTableSchemaBuilder.addField(
			"discountLevelsFormatted", "unit-discount");

		ClayTableSchemaField tieredPrice = clayTableSchemaBuilder.addField(
			"hasTierPrice", "tiered-price");

		tieredPrice.setContentRenderer("boolean");

		return clayTableSchemaBuilder.build();
	}

	@Reference
	private ClayTableSchemaBuilderFactory _clayTableSchemaBuilderFactory;

}