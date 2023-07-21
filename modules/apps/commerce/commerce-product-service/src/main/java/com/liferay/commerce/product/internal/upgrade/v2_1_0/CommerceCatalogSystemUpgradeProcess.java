/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.internal.upgrade.v2_1_0;

import com.liferay.commerce.product.internal.upgrade.base.BaseCommerceProductServiceUpgradeProcess;
import com.liferay.commerce.product.model.impl.CommerceCatalogImpl;

/**
 * @author Ethan Bustad
 */
public class CommerceCatalogSystemUpgradeProcess
	extends BaseCommerceProductServiceUpgradeProcess {

	protected void doUpgrade() throws Exception {
		renameColumn(
			CommerceCatalogImpl.class, CommerceCatalogImpl.TABLE_NAME, "system",
			"system_ BOOLEAN");
	}

}