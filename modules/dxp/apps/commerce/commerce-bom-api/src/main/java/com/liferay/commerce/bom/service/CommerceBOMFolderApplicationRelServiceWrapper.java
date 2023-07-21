/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.bom.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceBOMFolderApplicationRelService}.
 *
 * @author Luca Pellizzon
 * @see CommerceBOMFolderApplicationRelService
 * @generated
 */
public class CommerceBOMFolderApplicationRelServiceWrapper
	implements CommerceBOMFolderApplicationRelService,
			   ServiceWrapper<CommerceBOMFolderApplicationRelService> {

	public CommerceBOMFolderApplicationRelServiceWrapper(
		CommerceBOMFolderApplicationRelService
			commerceBOMFolderApplicationRelService) {

		_commerceBOMFolderApplicationRelService =
			commerceBOMFolderApplicationRelService;
	}

	@Override
	public com.liferay.commerce.bom.model.CommerceBOMFolderApplicationRel
			addCommerceBOMFolderApplicationRel(
				long userId, long commerceBOMFolderId,
				long commerceApplicationModelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceBOMFolderApplicationRelService.
			addCommerceBOMFolderApplicationRel(
				userId, commerceBOMFolderId, commerceApplicationModelId);
	}

	@Override
	public void deleteCommerceBOMFolderApplicationRel(
			long commerceBOMFolderApplicationRelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_commerceBOMFolderApplicationRelService.
			deleteCommerceBOMFolderApplicationRel(
				commerceBOMFolderApplicationRelId);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.bom.model.CommerceBOMFolderApplicationRel>
				getCommerceBOMFolderApplicationRelsByCAMId(
					long commerceApplicationModelId, int start, int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceBOMFolderApplicationRelService.
			getCommerceBOMFolderApplicationRelsByCAMId(
				commerceApplicationModelId, start, end);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.bom.model.CommerceBOMFolderApplicationRel>
				getCommerceBOMFolderApplicationRelsByCommerceBOMFolderId(
					long commerceBOMFolderId, int start, int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceBOMFolderApplicationRelService.
			getCommerceBOMFolderApplicationRelsByCommerceBOMFolderId(
				commerceBOMFolderId, start, end);
	}

	@Override
	public int getCommerceBOMFolderApplicationRelsCountByCAMId(
			long commerceApplicationModelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceBOMFolderApplicationRelService.
			getCommerceBOMFolderApplicationRelsCountByCAMId(
				commerceApplicationModelId);
	}

	@Override
	public int getCommerceBOMFolderApplicationRelsCountByCommerceBOMFolderId(
			long commerceBOMFolderId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceBOMFolderApplicationRelService.
			getCommerceBOMFolderApplicationRelsCountByCommerceBOMFolderId(
				commerceBOMFolderId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceBOMFolderApplicationRelService.
			getOSGiServiceIdentifier();
	}

	@Override
	public CommerceBOMFolderApplicationRelService getWrappedService() {
		return _commerceBOMFolderApplicationRelService;
	}

	@Override
	public void setWrappedService(
		CommerceBOMFolderApplicationRelService
			commerceBOMFolderApplicationRelService) {

		_commerceBOMFolderApplicationRelService =
			commerceBOMFolderApplicationRelService;
	}

	private CommerceBOMFolderApplicationRelService
		_commerceBOMFolderApplicationRelService;

}