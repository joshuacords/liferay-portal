/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.internal.upgrade.v4_9_0;

import com.liferay.commerce.internal.upgrade.base.BaseCommerceServiceUpgradeProcess;
import com.liferay.commerce.model.impl.CommerceOrderItemModelImpl;

/**
 * @author Igor Beslic
 * @author Riccardo Alberti
 */
public class CommerceOrderItemUpgradeProcess
	extends BaseCommerceServiceUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		addColumn(
			CommerceOrderItemModelImpl.class,
			CommerceOrderItemModelImpl.TABLE_NAME, "parentCommerceOrderItemId",
			"LONG");

		addColumn(
			CommerceOrderItemModelImpl.class,
			CommerceOrderItemModelImpl.TABLE_NAME, "unitPriceWithTaxAmount",
			"DECIMAL(30,16)");

		addColumn(
			CommerceOrderItemModelImpl.class,
			CommerceOrderItemModelImpl.TABLE_NAME, "promoPriceWithTaxAmount",
			"DECIMAL(30,16)");

		addColumn(
			CommerceOrderItemModelImpl.class,
			CommerceOrderItemModelImpl.TABLE_NAME, "discountWithTaxAmount",
			"DECIMAL(30,16)");

		addColumn(
			CommerceOrderItemModelImpl.class,
			CommerceOrderItemModelImpl.TABLE_NAME, "finalPriceWithTaxAmount",
			"DECIMAL(30,16)");

		addColumn(
			CommerceOrderItemModelImpl.class,
			CommerceOrderItemModelImpl.TABLE_NAME,
			"discountPctLevel1WithTaxAmount", "DECIMAL(30,16)");

		addColumn(
			CommerceOrderItemModelImpl.class,
			CommerceOrderItemModelImpl.TABLE_NAME,
			"discountPctLevel2WithTaxAmount", "DECIMAL(30,16)");

		addColumn(
			CommerceOrderItemModelImpl.class,
			CommerceOrderItemModelImpl.TABLE_NAME,
			"discountPctLevel3WithTaxAmount", "DECIMAL(30,16)");

		addColumn(
			CommerceOrderItemModelImpl.class,
			CommerceOrderItemModelImpl.TABLE_NAME,
			"discountPctLevel4WithTaxAmount", "DECIMAL(30,16)");

		addColumn(
			CommerceOrderItemModelImpl.class,
			CommerceOrderItemModelImpl.TABLE_NAME, "commercePriceListId",
			"LONG");
	}

}