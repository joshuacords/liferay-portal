/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.data.integration.internal.upgrade.v2_0_0;

import com.liferay.commerce.data.integration.internal.upgrade.base.BaseCommerceDataIntegrationServiceUpgradeProcess;
import com.liferay.commerce.data.integration.internal.upgrade.v2_0_0.util.CommerceDataIntegrationProcessTable;

/**
 * @author Ethan Bustad
 * @author Alessio Antonio Rendina
 */
public class CommerceDataIntegrationProcessSystemUpgradeProcess
	extends BaseCommerceDataIntegrationServiceUpgradeProcess {

	protected void doUpgrade() throws Exception {
		renameColumn(
			CommerceDataIntegrationProcessTable.class,
			CommerceDataIntegrationProcessTable.TABLE_NAME, "system",
			"system_ BOOLEAN");
	}

}