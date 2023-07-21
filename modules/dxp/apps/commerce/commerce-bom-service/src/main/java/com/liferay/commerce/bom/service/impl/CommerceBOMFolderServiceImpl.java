/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.bom.service.impl;

import com.liferay.commerce.bom.constants.CommerceBOMActionKeys;
import com.liferay.commerce.bom.model.CommerceBOMFolder;
import com.liferay.commerce.bom.service.base.CommerceBOMFolderServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.service.permission.PortalPermissionUtil;

import java.util.List;

/**
 * @author Luca Pellizzon
 * @author Alessio Antonio Rendina
 */
public class CommerceBOMFolderServiceImpl
	extends CommerceBOMFolderServiceBaseImpl {

	@Override
	public CommerceBOMFolder addCommerceBOMFolder(
			long userId, long parentCommerceBOMFolderId, String name,
			boolean logo, byte[] logoBytes)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CommerceBOMActionKeys.ADD_COMMERCE_BOM_FOLDER);

		return commerceBOMFolderLocalService.addCommerceBOMFolder(
			userId, parentCommerceBOMFolderId, name, logo, logoBytes);
	}

	@Override
	public void deleteCommerceBOMFolder(long commerceBOMFolderId)
		throws PortalException {

		_commerceBOMFolderModelResourcePermission.check(
			getPermissionChecker(), commerceBOMFolderId, ActionKeys.DELETE);

		commerceBOMFolderLocalService.deleteCommerceBOMFolder(
			commerceBOMFolderId);
	}

	@Override
	public CommerceBOMFolder getCommerceBOMFolder(long commerceBOMFolderId)
		throws PortalException {

		_commerceBOMFolderModelResourcePermission.check(
			getPermissionChecker(), commerceBOMFolderId, ActionKeys.VIEW);

		return commerceBOMFolderLocalService.getCommerceBOMFolder(
			commerceBOMFolderId);
	}

	@Override
	public List<CommerceBOMFolder> getCommerceBOMFolders(
		long companyId, int start, int end) {

		return commerceBOMFolderPersistence.filterFindByCompanyId(
			companyId, start, end);
	}

	@Override
	public List<CommerceBOMFolder> getCommerceBOMFolders(
		long companyId, long parentCommerceBOMFolderId, int start, int end) {

		return commerceBOMFolderPersistence.filterFindByC_P(
			companyId, parentCommerceBOMFolderId, start, end);
	}

	@Override
	public int getCommerceBOMFoldersCount(long companyId) {
		return commerceBOMFolderPersistence.filterCountByCompanyId(companyId);
	}

	@Override
	public int getCommerceBOMFoldersCount(
		long companyId, long parentCommerceBOMFolderId) {

		return commerceBOMFolderPersistence.filterCountByC_P(
			companyId, parentCommerceBOMFolderId);
	}

	@Override
	public CommerceBOMFolder updateCommerceBOMFolder(
			long commerceBOMFolderId, String name, boolean logo,
			byte[] logoBytes)
		throws PortalException {

		_commerceBOMFolderModelResourcePermission.check(
			getPermissionChecker(), commerceBOMFolderId, ActionKeys.UPDATE);

		return commerceBOMFolderLocalService.updateCommerceBOMFolder(
			commerceBOMFolderId, name, logo, logoBytes);
	}

	private static volatile ModelResourcePermission<CommerceBOMFolder>
		_commerceBOMFolderModelResourcePermission =
			ModelResourcePermissionFactory.getInstance(
				CommerceBOMFolderServiceImpl.class,
				"_commerceBOMFolderModelResourcePermission",
				CommerceBOMFolder.class);

}