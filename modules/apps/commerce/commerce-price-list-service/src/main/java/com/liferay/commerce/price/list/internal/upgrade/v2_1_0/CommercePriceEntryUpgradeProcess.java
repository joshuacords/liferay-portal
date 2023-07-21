/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.price.list.internal.upgrade.v2_1_0;

import com.liferay.commerce.price.list.internal.upgrade.base.BaseCommercePriceListUpgradeProcess;
import com.liferay.commerce.price.list.model.impl.CommercePriceEntryModelImpl;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

/**
 * @author Riccardo Alberti
 */
public class CommercePriceEntryUpgradeProcess
	extends BaseCommercePriceListUpgradeProcess {

	@Override
	public void doUpgrade() throws Exception {
		addColumn(
			CommercePriceEntryModelImpl.class,
			CommercePriceEntryModelImpl.TABLE_NAME, "discountDiscovery",
			"BOOLEAN");

		addColumn(
			CommercePriceEntryModelImpl.class,
			CommercePriceEntryModelImpl.TABLE_NAME, "discountLevel1",
			"DECIMAL(30,16)");

		addColumn(
			CommercePriceEntryModelImpl.class,
			CommercePriceEntryModelImpl.TABLE_NAME, "discountLevel2",
			"DECIMAL(30,16)");

		addColumn(
			CommercePriceEntryModelImpl.class,
			CommercePriceEntryModelImpl.TABLE_NAME, "discountLevel3",
			"DECIMAL(30,16)");

		addColumn(
			CommercePriceEntryModelImpl.class,
			CommercePriceEntryModelImpl.TABLE_NAME, "discountLevel4",
			"DECIMAL(30,16)");

		addColumn(
			CommercePriceEntryModelImpl.class,
			CommercePriceEntryModelImpl.TABLE_NAME, "bulkPricing", "BOOLEAN");

		addColumn(
			CommercePriceEntryModelImpl.class,
			CommercePriceEntryModelImpl.TABLE_NAME, "displayDate", "DATE");

		addColumn(
			CommercePriceEntryModelImpl.class,
			CommercePriceEntryModelImpl.TABLE_NAME, "expirationDate", "DATE");

		addColumn(
			CommercePriceEntryModelImpl.class,
			CommercePriceEntryModelImpl.TABLE_NAME, "status", "INTEGER");

		addColumn(
			CommercePriceEntryModelImpl.class,
			CommercePriceEntryModelImpl.TABLE_NAME, "statusByUserId", "LONG");

		addColumn(
			CommercePriceEntryModelImpl.class,
			CommercePriceEntryModelImpl.TABLE_NAME, "statusByUserName",
			"VARCHAR(75)");

		addColumn(
			CommercePriceEntryModelImpl.class,
			CommercePriceEntryModelImpl.TABLE_NAME, "statusDate", "DATE");

		runSQL("UPDATE CommercePriceEntry SET bulkPricing = [$TRUE$]");
		runSQL("UPDATE CommercePriceEntry SET displayDate = lastPublishDate");
		runSQL(
			"UPDATE CommercePriceEntry SET status = " +
				WorkflowConstants.STATUS_APPROVED);
		runSQL("UPDATE CommercePriceEntry SET statusByUserId = userId");
		runSQL("UPDATE CommercePriceEntry SET statusByUserName = userName");
		runSQL("UPDATE CommercePriceEntry SET statusDate = modifiedDate");
	}

}