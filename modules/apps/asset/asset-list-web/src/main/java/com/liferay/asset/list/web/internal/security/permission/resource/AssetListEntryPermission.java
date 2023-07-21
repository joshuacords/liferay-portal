/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.list.web.internal.security.permission.resource;

import com.liferay.asset.list.model.AssetListEntry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jürgen Kappler
 */
@Component(immediate = true, service = {})
public class AssetListEntryPermission {

	public static boolean contains(
			PermissionChecker permissionChecker, AssetListEntry assetListEntry,
			String actionId)
		throws PortalException {

		return _assetListEntryModelResourcePermission.contains(
			permissionChecker, assetListEntry, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long assetListEntryId,
			String actionId)
		throws PortalException {

		return _assetListEntryModelResourcePermission.contains(
			permissionChecker, assetListEntryId, actionId);
	}

	@Reference(
		target = "(model.class.name=com.liferay.asset.list.model.AssetListEntry)",
		unbind = "-"
	)
	protected void setModelResourcePermission(
		ModelResourcePermission<AssetListEntry> modelResourcePermission) {

		_assetListEntryModelResourcePermission = modelResourcePermission;
	}

	private static ModelResourcePermission<AssetListEntry>
		_assetListEntryModelResourcePermission;

}