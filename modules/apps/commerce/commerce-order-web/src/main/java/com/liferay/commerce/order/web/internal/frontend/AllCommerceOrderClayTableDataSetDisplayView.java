/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.order.web.internal.frontend;

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
	property = "commerce.data.set.display.name=" + CommerceOrderDataSetConstants.COMMERCE_DATA_SET_KEY_ALL_ORDERS,
	service = ClayDataSetDisplayView.class
)
public class AllCommerceOrderClayTableDataSetDisplayView
	extends ClayTableDataSetDisplayView {

	@Override
	public ClayTableSchema getClayTableSchema() {
		ClayTableSchemaBuilder clayTableSchemaBuilder =
			_clayTableSchemaBuilderFactory.clayTableSchemaBuilder();

		ClayTableSchemaField orderIdField = clayTableSchemaBuilder.addField(
			"id", "order-id");

		orderIdField.setContentRenderer("actionLink");

		clayTableSchemaBuilder.addField("account.name", "account");

		clayTableSchemaBuilder.addField("accountId", "account-number");

		clayTableSchemaBuilder.addField("channel.name", "channel");

		clayTableSchemaBuilder.addField("totalFormatted", "amount");

		ClayTableSchemaField dateClayTableSchemaField =
			clayTableSchemaBuilder.addField("createDate", "create-date");

		dateClayTableSchemaField.setContentRenderer("date");
		dateClayTableSchemaField.setSortable(true);

		ClayTableSchemaField orderStatusField = clayTableSchemaBuilder.addField(
			"orderStatusInfo", "order-status");

		orderStatusField.setContentRenderer("status");

		ClayTableSchemaField fulfillmentWorkflowField =
			clayTableSchemaBuilder.addField(
				"workflowStatusInfo", "acceptance-workflow-status");

		fulfillmentWorkflowField.setContentRenderer("status");

		return clayTableSchemaBuilder.build();
	}

	@Reference
	private ClayTableSchemaBuilderFactory _clayTableSchemaBuilderFactory;

}