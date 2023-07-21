/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.internal.upgrade.v4_6_0;

import com.liferay.commerce.internal.upgrade.base.BaseCommerceServiceUpgradeProcess;
import com.liferay.commerce.model.impl.CommerceSubscriptionEntryImpl;

/**
 * @author Luca Pellizzon
 */
public class SubscriptionUpgradeProcess
	extends BaseCommerceServiceUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		if (!hasColumn(
				CommerceSubscriptionEntryImpl.TABLE_NAME,
				"deliverySubscriptionLength")) {

			addColumn(
				CommerceSubscriptionEntryImpl.class,
				CommerceSubscriptionEntryImpl.TABLE_NAME,
				"deliverySubscriptionLength", "INTEGER");
		}

		if (!hasColumn(
				CommerceSubscriptionEntryImpl.TABLE_NAME,
				"deliverySubscriptionType")) {

			addColumn(
				CommerceSubscriptionEntryImpl.class,
				CommerceSubscriptionEntryImpl.TABLE_NAME,
				"deliverySubscriptionType", "VARCHAR(75)");
		}

		if (!hasColumn(
				CommerceSubscriptionEntryImpl.TABLE_NAME,
				"deliverySubTypeSettings")) {

			addColumn(
				CommerceSubscriptionEntryImpl.class,
				CommerceSubscriptionEntryImpl.TABLE_NAME,
				"deliverySubTypeSettings", "TEXT");
		}

		if (!hasColumn(
				CommerceSubscriptionEntryImpl.TABLE_NAME,
				"deliveryCurrentCycle")) {

			addColumn(
				CommerceSubscriptionEntryImpl.class,
				CommerceSubscriptionEntryImpl.TABLE_NAME,
				"deliveryCurrentCycle", "LONG");
		}

		if (!hasColumn(
				CommerceSubscriptionEntryImpl.TABLE_NAME,
				"deliveryMaxSubscriptionCycles")) {

			addColumn(
				CommerceSubscriptionEntryImpl.class,
				CommerceSubscriptionEntryImpl.TABLE_NAME,
				"deliveryMaxSubscriptionCycles", "LONG");
		}

		if (!hasColumn(
				CommerceSubscriptionEntryImpl.TABLE_NAME,
				"deliverySubscriptionStatus")) {

			addColumn(
				CommerceSubscriptionEntryImpl.class,
				CommerceSubscriptionEntryImpl.TABLE_NAME,
				"deliverySubscriptionStatus", "INTEGER");
		}

		if (!hasColumn(
				CommerceSubscriptionEntryImpl.TABLE_NAME,
				"deliveryLastIterationDate")) {

			addColumn(
				CommerceSubscriptionEntryImpl.class,
				CommerceSubscriptionEntryImpl.TABLE_NAME,
				"deliveryLastIterationDate", "DATE");
		}

		if (!hasColumn(
				CommerceSubscriptionEntryImpl.TABLE_NAME,
				"deliveryNextIterationDate")) {

			addColumn(
				CommerceSubscriptionEntryImpl.class,
				CommerceSubscriptionEntryImpl.TABLE_NAME,
				"deliveryNextIterationDate", "DATE");
		}

		if (!hasColumn(
				CommerceSubscriptionEntryImpl.TABLE_NAME,
				"deliveryStartDate")) {

			addColumn(
				CommerceSubscriptionEntryImpl.class,
				CommerceSubscriptionEntryImpl.TABLE_NAME, "deliveryStartDate",
				"DATE");
		}
	}

}