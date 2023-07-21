/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.oauth2.provider.internal.upgrade.v1_1_0;

import com.liferay.oauth2.provider.internal.upgrade.v1_1_0.util.OAuth2ScopeGrantTable;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Carlos Sierra Andrés
 */
public class UpgradeOAuth2ScopeGrant extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		alter(
			OAuth2ScopeGrantTable.class,
			new AlterColumnType("scope", "VARCHAR(240) null"));
	}

}