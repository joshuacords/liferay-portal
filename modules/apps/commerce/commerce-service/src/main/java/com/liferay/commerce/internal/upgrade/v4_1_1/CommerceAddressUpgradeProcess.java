/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.internal.upgrade.v4_1_1;

import com.liferay.commerce.model.impl.CommerceAddressModelImpl;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Marco Leo
 */
public class CommerceAddressUpgradeProcess extends UpgradeProcess {

	protected void doUpgrade() throws Exception {
		alter(
			CommerceAddressModelImpl.class,
			new AlterColumnType("name", "VARCHAR(255) null"));
		alter(
			CommerceAddressModelImpl.class,
			new AlterColumnType("street1", "VARCHAR(255) null"));
		alter(
			CommerceAddressModelImpl.class,
			new AlterColumnType("street2", "VARCHAR(255) null"));
		alter(
			CommerceAddressModelImpl.class,
			new AlterColumnType("street3", "VARCHAR(255) null"));
	}

}