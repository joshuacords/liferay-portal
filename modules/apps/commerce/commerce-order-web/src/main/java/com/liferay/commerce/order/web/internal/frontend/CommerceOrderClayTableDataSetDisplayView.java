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
	property = {
		"commerce.data.set.display.name=" + CommerceOrderDataSetConstants.COMMERCE_DATA_SET_KEY_COMPLETED_ORDERS,
		"commerce.data.set.display.name=" + CommerceOrderDataSetConstants.COMMERCE_DATA_SET_KEY_PENDING_ORDERS,
		"commerce.data.set.display.name=" + CommerceOrderDataSetConstants.COMMERCE_DATA_SET_KEY_PROCESSING_ORDERS
	},
	service = ClayDataSetDisplayView.class
)
public class CommerceOrderClayTableDataSetDisplayView
	extends ClayTableDataSetDisplayView {

	@Override
	public ClayTableSchema getClayTableSchema() {
		ClayTableSchemaBuilder clayTableSchemaBuilder =
			_clayTableSchemaBuilderFactory.clayTableSchemaBuilder();

		ClayTableSchemaField orderIdField = clayTableSchemaBuilder.addField(
			"orderId", "order-id");

		orderIdField.setContentRenderer("actionLink");

		clayTableSchemaBuilder.addField("account", "account");

		clayTableSchemaBuilder.addField("accountCode", "account-number");

		clayTableSchemaBuilder.addField("channel", "channel");

		clayTableSchemaBuilder.addField("amount", "amount");

		clayTableSchemaBuilder.addField("createDate", "order-date");

		ClayTableSchemaField orderStatusField = clayTableSchemaBuilder.addField(
			"orderStatus", "order-status");

		orderStatusField.setContentRenderer("label");

		ClayTableSchemaField fulfillmentWorkflowField =
			clayTableSchemaBuilder.addField(
				"fulfillmentWorkflow", "acceptance-workflow-status");

		fulfillmentWorkflowField.setContentRenderer("label");

		return clayTableSchemaBuilder.build();
	}

	@Reference
	private ClayTableSchemaBuilderFactory _clayTableSchemaBuilderFactory;

}