/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.bom.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceBOMFolderService}.
 *
 * @author Luca Pellizzon
 * @see CommerceBOMFolderService
 * @generated
 */
public class CommerceBOMFolderServiceWrapper
	implements CommerceBOMFolderService,
			   ServiceWrapper<CommerceBOMFolderService> {

	public CommerceBOMFolderServiceWrapper(
		CommerceBOMFolderService commerceBOMFolderService) {

		_commerceBOMFolderService = commerceBOMFolderService;
	}

	@Override
	public com.liferay.commerce.bom.model.CommerceBOMFolder
			addCommerceBOMFolder(
				long userId, long parentCommerceBOMFolderId, String name,
				boolean logo, byte[] logoBytes)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceBOMFolderService.addCommerceBOMFolder(
			userId, parentCommerceBOMFolderId, name, logo, logoBytes);
	}

	@Override
	public void deleteCommerceBOMFolder(long commerceBOMFolderId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_commerceBOMFolderService.deleteCommerceBOMFolder(commerceBOMFolderId);
	}

	@Override
	public com.liferay.commerce.bom.model.CommerceBOMFolder
			getCommerceBOMFolder(long commerceBOMFolderId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceBOMFolderService.getCommerceBOMFolder(
			commerceBOMFolderId);
	}

	@Override
	public java.util.List<com.liferay.commerce.bom.model.CommerceBOMFolder>
		getCommerceBOMFolders(long companyId, int start, int end) {

		return _commerceBOMFolderService.getCommerceBOMFolders(
			companyId, start, end);
	}

	@Override
	public java.util.List<com.liferay.commerce.bom.model.CommerceBOMFolder>
		getCommerceBOMFolders(
			long companyId, long parentCommerceBOMFolderId, int start,
			int end) {

		return _commerceBOMFolderService.getCommerceBOMFolders(
			companyId, parentCommerceBOMFolderId, start, end);
	}

	@Override
	public int getCommerceBOMFoldersCount(long companyId) {
		return _commerceBOMFolderService.getCommerceBOMFoldersCount(companyId);
	}

	@Override
	public int getCommerceBOMFoldersCount(
		long companyId, long parentCommerceBOMFolderId) {

		return _commerceBOMFolderService.getCommerceBOMFoldersCount(
			companyId, parentCommerceBOMFolderId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceBOMFolderService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.commerce.bom.model.CommerceBOMFolder
			updateCommerceBOMFolder(
				long commerceBOMFolderId, String name, boolean logo,
				byte[] logoBytes)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceBOMFolderService.updateCommerceBOMFolder(
			commerceBOMFolderId, name, logo, logoBytes);
	}

	@Override
	public CommerceBOMFolderService getWrappedService() {
		return _commerceBOMFolderService;
	}

	@Override
	public void setWrappedService(
		CommerceBOMFolderService commerceBOMFolderService) {

		_commerceBOMFolderService = commerceBOMFolderService;
	}

	private CommerceBOMFolderService _commerceBOMFolderService;

}