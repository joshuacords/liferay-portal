/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.internal.upgrade.v2_0_0;

import com.liferay.commerce.internal.upgrade.base.BaseCommerceServiceUpgradeProcess;
import com.liferay.commerce.model.impl.CommerceOrderImpl;
import com.liferay.commerce.model.impl.CommerceOrderPaymentImpl;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Luca Pellizzon
 */
public class CommercePaymentMethodUpgradeProcess
	extends BaseCommerceServiceUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		if (!hasColumn(CommerceOrderImpl.TABLE_NAME, "transactionId")) {
			addColumn(
				CommerceOrderImpl.class, CommerceOrderImpl.TABLE_NAME,
				"transactionId", "VARCHAR(75)");
		}

		if (hasColumn(
				CommerceOrderImpl.TABLE_NAME, "commercePaymentMethodId")) {

			addColumn(
				CommerceOrderImpl.class, CommerceOrderImpl.TABLE_NAME,
				"commercePaymentMethodKey", "VARCHAR(75)");

			String template = StringUtil.read(
				CommercePaymentMethodUpgradeProcess.class.getResourceAsStream(
					"dependencies/CommerceOrderUpgradeProcess.sql"));

			runSQLTemplateString(template, false, false);

			alter(
				CommerceOrderImpl.class,
				new AlterTableDropColumn("commercePaymentMethodId"));
		}

		if (hasColumn(
				CommerceOrderPaymentImpl.TABLE_NAME,
				"commercePaymentMethodId")) {

			addColumn(
				CommerceOrderPaymentImpl.class,
				CommerceOrderPaymentImpl.TABLE_NAME, "commercePaymentMethodKey",
				"VARCHAR(75)");

			String template = StringUtil.read(
				CommercePaymentMethodUpgradeProcess.class.getResourceAsStream(
					"dependencies/CommerceOrderPaymentUpgradeProcess.sql"));

			runSQLTemplateString(template, false, false);

			alter(
				CommerceOrderPaymentImpl.class,
				new AlterTableDropColumn("commercePaymentMethodId"));
		}

		if (hasTable("CommercePaymentMethod")) {
			String template = StringUtil.read(
				CommercePaymentMethodUpgradeProcess.class.getResourceAsStream(
					"dependencies/CommercePaymentMethodUpgradeProcess.sql"));

			runSQLTemplateString(template, false, false);
		}
	}

}