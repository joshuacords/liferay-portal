/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.subscription.web.internal.frontend;

import com.liferay.commerce.frontend.clay.data.set.ClayDataSetDisplayView;
import com.liferay.commerce.frontend.clay.table.ClayTableDataSetDisplayView;
import com.liferay.commerce.frontend.clay.table.ClayTableSchema;
import com.liferay.commerce.frontend.clay.table.ClayTableSchemaBuilder;
import com.liferay.commerce.frontend.clay.table.ClayTableSchemaBuilderFactory;
import com.liferay.commerce.frontend.clay.table.ClayTableSchemaField;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Luca Pellizzon
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = "commerce.data.set.display.name=" + CommerceSubscriptionDataSetConstants.COMMERCE_DATA_SET_KEY_SUBSCRIPTION_ENTRIES,
	service = ClayDataSetDisplayView.class
)
public class CommerceSubscriptionEntryClayTableDataSetDisplayView
	extends ClayTableDataSetDisplayView {

	@Override
	public ClayTableSchema getClayTableSchema() {
		ClayTableSchemaBuilder clayTableSchemaBuilder =
			_clayTableSchemaBuilderFactory.clayTableSchemaBuilder();

		ClayTableSchemaField subscriptionIdField =
			clayTableSchemaBuilder.addField("subscriptionId", "id");

		subscriptionIdField.setContentRenderer("actionLink");

		ClayTableSchemaField subscriptionStatusField =
			clayTableSchemaBuilder.addField("subscriptionStatus", "status");

		subscriptionStatusField.setContentRenderer("label");

		ClayTableSchemaField orderIdField = clayTableSchemaBuilder.addField(
			"orderId", "order-id");

		orderIdField.setContentRenderer("link");

		ClayTableSchemaField accountIdField = clayTableSchemaBuilder.addField(
			"commerceAccountId", "account-id");

		accountIdField.setContentRenderer("link");

		clayTableSchemaBuilder.addField("commerceAccountName", "account-name");

		return clayTableSchemaBuilder.build();
	}

	@Reference
	private ClayTableSchemaBuilderFactory _clayTableSchemaBuilderFactory;

}