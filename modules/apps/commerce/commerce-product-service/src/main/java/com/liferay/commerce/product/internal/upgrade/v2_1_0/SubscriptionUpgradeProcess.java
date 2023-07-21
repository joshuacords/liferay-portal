/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.internal.upgrade.v2_1_0;

import com.liferay.commerce.product.internal.upgrade.base.BaseCommerceProductServiceUpgradeProcess;
import com.liferay.commerce.product.model.impl.CPDefinitionImpl;
import com.liferay.commerce.product.model.impl.CPInstanceImpl;

/**
 * @author Luca Pellizzon
 */
public class SubscriptionUpgradeProcess
	extends BaseCommerceProductServiceUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		addColumn(
			CPDefinitionImpl.class, CPDefinitionImpl.TABLE_NAME,
			"deliverySubscriptionEnabled", "BOOLEAN");
		addColumn(
			CPDefinitionImpl.class, CPDefinitionImpl.TABLE_NAME,
			"deliverySubscriptionLength", "INTEGER");
		addColumn(
			CPDefinitionImpl.class, CPDefinitionImpl.TABLE_NAME,
			"deliverySubscriptionType", "VARCHAR(75)");
		addColumn(
			CPDefinitionImpl.class, CPDefinitionImpl.TABLE_NAME,
			"deliverySubTypeSettings", "TEXT");
		addColumn(
			CPDefinitionImpl.class, CPDefinitionImpl.TABLE_NAME,
			"deliveryMaxSubscriptionCycles", "LONG");
		addColumn(
			CPInstanceImpl.class, CPInstanceImpl.TABLE_NAME,
			"deliverySubscriptionEnabled", "BOOLEAN");
		addColumn(
			CPInstanceImpl.class, CPInstanceImpl.TABLE_NAME,
			"deliverySubscriptionLength", "INTEGER");
		addColumn(
			CPInstanceImpl.class, CPInstanceImpl.TABLE_NAME,
			"deliverySubscriptionType", "VARCHAR(75)");
		addColumn(
			CPInstanceImpl.class, CPInstanceImpl.TABLE_NAME,
			"deliverySubTypeSettings", "TEXT");
		addColumn(
			CPInstanceImpl.class, CPInstanceImpl.TABLE_NAME,
			"deliveryMaxSubscriptionCycles", "LONG");
	}

}