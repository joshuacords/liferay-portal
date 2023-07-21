/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.internal.upgrade.v2_2_0;

import com.liferay.commerce.product.constants.CPConstants;
import com.liferay.commerce.product.internal.upgrade.base.BaseCommerceProductServiceUpgradeProcess;
import com.liferay.commerce.product.model.impl.CPDefinitionOptionRelImpl;
import com.liferay.commerce.product.model.impl.CPDefinitionOptionValueRelImpl;

/**
 * @author Marco Leo
 */
public class CPDefinitionOptionValueRelUpgradeProcess
	extends BaseCommerceProductServiceUpgradeProcess {

	protected void doUpgrade() throws Exception {
		addColumn(
			CPDefinitionOptionValueRelImpl.class,
			CPDefinitionOptionValueRelImpl.TABLE_NAME, "CPInstanceUuid",
			"VARCHAR(75)");

		addColumn(
			CPDefinitionOptionValueRelImpl.class,
			CPDefinitionOptionValueRelImpl.TABLE_NAME, "CProductId", "LONG");

		addColumn(
			CPDefinitionOptionValueRelImpl.class,
			CPDefinitionOptionValueRelImpl.TABLE_NAME, "quantity", "INTEGER");

		addColumn(
			CPDefinitionOptionValueRelImpl.class,
			CPDefinitionOptionValueRelImpl.TABLE_NAME, "price",
			"DECIMAL(30, 16)");

		addColumn(
			CPDefinitionOptionRelImpl.class,
			CPDefinitionOptionRelImpl.TABLE_NAME, "priceType", "VARCHAR(75)");

		runSQL(
			String.format(
				"update %s set priceType = '%s'",
				CPDefinitionOptionRelImpl.TABLE_NAME,
				CPConstants.PRODUCT_OPTION_PRICE_TYPE_STATIC));

		runSQL(
			String.format(
				"update %s set price = 0",
				CPDefinitionOptionValueRelImpl.TABLE_NAME));
	}

}