/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.definitions.web.internal.frontend;

import com.liferay.commerce.frontend.clay.data.set.ClayDataSetDisplayView;
import com.liferay.commerce.frontend.clay.table.ClayTableDataSetDisplayView;
import com.liferay.commerce.frontend.clay.table.ClayTableSchema;
import com.liferay.commerce.frontend.clay.table.ClayTableSchemaBuilder;
import com.liferay.commerce.frontend.clay.table.ClayTableSchemaBuilderFactory;
import com.liferay.commerce.frontend.clay.table.ClayTableSchemaField;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = "commerce.data.set.display.name=" + CommerceProductDataSetConstants.COMMERCE_DATA_SET_KEY_PRODUCT_DEFINITIONS,
	service = ClayDataSetDisplayView.class
)
public class CommerceProductDefinitionClayTableDataSetDisplayView
	extends ClayTableDataSetDisplayView {

	@Override
	public ClayTableSchema getClayTableSchema() {
		ClayTableSchemaBuilder clayTableSchemaBuilder =
			_clayTableSchemaBuilderFactory.clayTableSchemaBuilder();

		ClayTableSchemaField imageclayTableSchemaField =
			clayTableSchemaBuilder.addField("thumbnail", "");

		imageclayTableSchemaField.setContentRenderer("image");

		ClayTableSchemaField nameField = clayTableSchemaBuilder.addField(
			"name.LANG", "name");

		nameField.setSortable(true);
		nameField.setContentRenderer("actionLink");

		clayTableSchemaBuilder.addField("catalog.name", "catalog");
		clayTableSchemaBuilder.addField("productTypeI18n", "type");

		ClayTableSchemaField clayTableSchemaField =
			clayTableSchemaBuilder.addField("workflowStatusInfo", "status");

		clayTableSchemaField.setContentRenderer("status");

		ClayTableSchemaField dateclayTableSchemaField =
			clayTableSchemaBuilder.addField("modifiedDate", "modified-date");

		dateclayTableSchemaField.setContentRenderer("date");
		dateclayTableSchemaField.setSortable(true);

		return clayTableSchemaBuilder.build();
	}

	@Reference
	private ClayTableSchemaBuilderFactory _clayTableSchemaBuilderFactory;

}