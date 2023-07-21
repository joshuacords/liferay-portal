/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.oauth.helper;

import com.liferay.portal.kernel.service.ServiceContext;

/**
 * @author Shinn Lok
 */
public interface SyncOAuthHelper {

	public void enableOAuth(long companyId, ServiceContext serviceContext)
		throws Exception;

	public boolean isDeployed();

	public boolean isOAuthApplicationAvailable(long oAuthApplicationId);

}