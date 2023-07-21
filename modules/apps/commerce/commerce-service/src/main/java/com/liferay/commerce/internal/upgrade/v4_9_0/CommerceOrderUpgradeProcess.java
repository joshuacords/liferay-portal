/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.internal.upgrade.v4_9_0;

import com.liferay.commerce.internal.upgrade.base.BaseCommerceServiceUpgradeProcess;
import com.liferay.commerce.model.impl.CommerceOrderModelImpl;

/**
 * @author Riccardo Alberti
 */
public class CommerceOrderUpgradeProcess
	extends BaseCommerceServiceUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"subtotalWithTaxAmount", "DECIMAL(30,16)");

		addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"subtotalDiscountWithTaxAmount", "DECIMAL(30,16)");

		addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"subtotalDiscountPctLev1WithTax", "DECIMAL(30,16)");

		addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"subtotalDiscountPctLev2WithTax", "DECIMAL(30,16)");

		addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"subtotalDiscountPctLev3WithTax", "DECIMAL(30,16)");

		addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"subtotalDiscountPctLev4WithTax", "DECIMAL(30,16)");

		addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"shippingWithTaxAmount", "DECIMAL(30,16)");

		addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"shippingDiscountWithTaxAmount", "DECIMAL(30,16)");

		addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"shippingDiscountPctLev1WithTax", "DECIMAL(30,16)");

		addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"shippingDiscountPctLev2WithTax", "DECIMAL(30,16)");

		addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"shippingDiscountPctLev3WithTax", "DECIMAL(30,16)");

		addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"shippingDiscountPctLev4WithTax", "DECIMAL(30,16)");

		addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"totalWithTaxAmount", "DECIMAL(30,16)");

		addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"totalDiscountWithTaxAmount", "DECIMAL(30,16)");

		addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"totalDiscountPctLev1WithTax", "DECIMAL(30,16)");

		addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"totalDiscountPctLev2WithTax", "DECIMAL(30,16)");

		addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"totalDiscountPctLev3WithTax", "DECIMAL(30,16)");

		addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"totalDiscountPctLev4WithTax", "DECIMAL(30,16)");
	}

}