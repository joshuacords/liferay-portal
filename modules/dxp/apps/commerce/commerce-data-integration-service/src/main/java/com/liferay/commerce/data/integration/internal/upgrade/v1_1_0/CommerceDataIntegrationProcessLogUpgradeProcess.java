/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.data.integration.internal.upgrade.v1_1_0;

import com.liferay.commerce.data.integration.internal.upgrade.base.BaseCommerceDataIntegrationServiceUpgradeProcess;
import com.liferay.commerce.data.integration.model.impl.CommerceDataIntegrationProcessLogModelImpl;

/**
 * @author Ethan Bustad
 */
public class CommerceDataIntegrationProcessLogUpgradeProcess
	extends BaseCommerceDataIntegrationServiceUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		renameColumn(
			CommerceDataIntegrationProcessLogModelImpl.class,
			CommerceDataIntegrationProcessLogModelImpl.TABLE_NAME, "output",
			"output_ TEXT null");
	}

}