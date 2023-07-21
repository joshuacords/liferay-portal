/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.permission;

import com.liferay.osb.koroneiki.trunk.model.ProductConsumption;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

/**
 * @author Kyle Bischof
 */
public interface ProductConsumptionPermission {

	public void check(
			PermissionChecker permissionChecker, long productConsumptionId,
			String actionId)
		throws PortalException;

	public void check(
			PermissionChecker permissionChecker,
			ProductConsumption productConsumption, String actionId)
		throws PortalException;

	public boolean contains(
			PermissionChecker permissionChecker, long productConsumptionId,
			String actionId)
		throws PortalException;

	public boolean contains(
			PermissionChecker permissionChecker, long[] productConsumptionIds,
			String actionId)
		throws PortalException;

	public boolean contains(
			PermissionChecker permissionChecker,
			ProductConsumption productConsumption, String actionId)
		throws PortalException;

}