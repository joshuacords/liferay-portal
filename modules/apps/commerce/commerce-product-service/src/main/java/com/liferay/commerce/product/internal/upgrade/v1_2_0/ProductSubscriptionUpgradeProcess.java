/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.internal.upgrade.v1_2_0;

import com.liferay.commerce.product.internal.upgrade.base.BaseCommerceProductServiceUpgradeProcess;
import com.liferay.commerce.product.model.impl.CPDefinitionImpl;
import com.liferay.commerce.product.model.impl.CPInstanceImpl;

/**
 * @author Marco Leo
 */
public class ProductSubscriptionUpgradeProcess
	extends BaseCommerceProductServiceUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		addColumn(
			CPDefinitionImpl.class, CPDefinitionImpl.TABLE_NAME,
			"subscriptionEnabled", "BOOLEAN");
		addColumn(
			CPDefinitionImpl.class, CPDefinitionImpl.TABLE_NAME,
			"subscriptionLength", "INTEGER");
		addColumn(
			CPDefinitionImpl.class, CPDefinitionImpl.TABLE_NAME,
			"subscriptionType", "VARCHAR(75)");
		addColumn(
			CPDefinitionImpl.class, CPDefinitionImpl.TABLE_NAME,
			"subscriptionTypeSettings", "TEXT");
		addColumn(
			CPDefinitionImpl.class, CPDefinitionImpl.TABLE_NAME,
			"maxSubscriptionCycles", "LONG");

		addColumn(
			CPInstanceImpl.class, CPInstanceImpl.TABLE_NAME,
			"overrideSubscriptionInfo", "BOOLEAN");
		addColumn(
			CPInstanceImpl.class, CPInstanceImpl.TABLE_NAME,
			"subscriptionEnabled", "BOOLEAN");
		addColumn(
			CPInstanceImpl.class, CPInstanceImpl.TABLE_NAME,
			"subscriptionLength", "INTEGER");
		addColumn(
			CPInstanceImpl.class, CPInstanceImpl.TABLE_NAME, "subscriptionType",
			"VARCHAR(75)");
		addColumn(
			CPInstanceImpl.class, CPInstanceImpl.TABLE_NAME,
			"subscriptionTypeSettings", "TEXT");
		addColumn(
			CPInstanceImpl.class, CPInstanceImpl.TABLE_NAME,
			"maxSubscriptionCycles", "LONG");
	}

}