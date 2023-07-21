/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.internal.upgrade.v4_1_0;

import com.liferay.commerce.internal.upgrade.base.BaseCommerceServiceUpgradeProcess;
import com.liferay.commerce.model.impl.CommerceCountryModelImpl;

/**
 * @author Marco Leo
 */
public class CommerceCountryUpgradeProcess
	extends BaseCommerceServiceUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		if (!hasColumn(
				CommerceCountryModelImpl.TABLE_NAME, "channelFilterEnabled")) {

			addColumn(
				CommerceCountryModelImpl.class,
				CommerceCountryModelImpl.TABLE_NAME, "channelFilterEnabled",
				"BOOLEAN");

			runSQL(
				"update CommerceCountry set channelFilterEnabled = [$FALSE$]");
		}
	}

}