/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.price.list.internal.upgrade.v2_1_0;

import com.liferay.commerce.price.list.internal.upgrade.base.BaseCommercePriceListUpgradeProcess;
import com.liferay.commerce.price.list.model.impl.CommerceTierPriceEntryModelImpl;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

/**
 * @author Riccardo Alberti
 */
public class CommerceTierPriceEntryUpgradeProcess
	extends BaseCommercePriceListUpgradeProcess {

	@Override
	public void doUpgrade() throws Exception {
		addColumn(
			CommerceTierPriceEntryModelImpl.class,
			CommerceTierPriceEntryModelImpl.TABLE_NAME, "discountDiscovery",
			"BOOLEAN");

		addColumn(
			CommerceTierPriceEntryModelImpl.class,
			CommerceTierPriceEntryModelImpl.TABLE_NAME, "discountLevel1",
			"DECIMAL(30,16)");

		addColumn(
			CommerceTierPriceEntryModelImpl.class,
			CommerceTierPriceEntryModelImpl.TABLE_NAME, "discountLevel2",
			"DECIMAL(30,16)");

		addColumn(
			CommerceTierPriceEntryModelImpl.class,
			CommerceTierPriceEntryModelImpl.TABLE_NAME, "discountLevel3",
			"DECIMAL(30,16)");

		addColumn(
			CommerceTierPriceEntryModelImpl.class,
			CommerceTierPriceEntryModelImpl.TABLE_NAME, "discountLevel4",
			"DECIMAL(30,16)");

		addColumn(
			CommerceTierPriceEntryModelImpl.class,
			CommerceTierPriceEntryModelImpl.TABLE_NAME, "displayDate", "DATE");

		addColumn(
			CommerceTierPriceEntryModelImpl.class,
			CommerceTierPriceEntryModelImpl.TABLE_NAME, "expirationDate",
			"DATE");

		addColumn(
			CommerceTierPriceEntryModelImpl.class,
			CommerceTierPriceEntryModelImpl.TABLE_NAME, "status", "INTEGER");

		addColumn(
			CommerceTierPriceEntryModelImpl.class,
			CommerceTierPriceEntryModelImpl.TABLE_NAME, "statusByUserId",
			"LONG");

		addColumn(
			CommerceTierPriceEntryModelImpl.class,
			CommerceTierPriceEntryModelImpl.TABLE_NAME, "statusByUserName",
			"VARCHAR(75)");

		addColumn(
			CommerceTierPriceEntryModelImpl.class,
			CommerceTierPriceEntryModelImpl.TABLE_NAME, "statusDate", "DATE");

		runSQL(
			"UPDATE CommerceTierPriceEntry SET displayDate = lastPublishDate");
		runSQL(
			"UPDATE CommerceTierPriceEntry SET status = " +
				WorkflowConstants.STATUS_APPROVED);
		runSQL("UPDATE CommerceTierPriceEntry SET statusByUserId = userId");
		runSQL("UPDATE CommerceTierPriceEntry SET statusByUserName = userName");
		runSQL("UPDATE CommerceTierPriceEntry SET statusDate = modifiedDate");
	}

}