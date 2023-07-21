/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.bom.permission;

import com.liferay.commerce.bom.model.CommerceBOMDefinition;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

/**
 * @author Alessio Antonio Rendina
 */
public interface CommerceBOMDefinitionPermission {

	public void check(
			PermissionChecker permissionChecker,
			CommerceBOMDefinition commerceBOMDefinition, String actionId)
		throws PortalException;

	public void check(
			PermissionChecker permissionChecker, long commerceBOMDefinitionId,
			String actionId)
		throws PortalException;

	public boolean contains(
			PermissionChecker permissionChecker,
			CommerceBOMDefinition commerceBOMDefinition, String actionId)
		throws PortalException;

	public boolean contains(
			PermissionChecker permissionChecker, long commerceBOMDefinitionId,
			String actionId)
		throws PortalException;

	public boolean contains(
			PermissionChecker permissionChecker,
			long[] commerceBOMDefinitionIds, String actionId)
		throws PortalException;

}