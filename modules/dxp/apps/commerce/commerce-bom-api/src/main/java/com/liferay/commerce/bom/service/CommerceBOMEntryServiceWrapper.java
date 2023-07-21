/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.bom.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceBOMEntryService}.
 *
 * @author Luca Pellizzon
 * @see CommerceBOMEntryService
 * @generated
 */
public class CommerceBOMEntryServiceWrapper
	implements CommerceBOMEntryService,
			   ServiceWrapper<CommerceBOMEntryService> {

	public CommerceBOMEntryServiceWrapper(
		CommerceBOMEntryService commerceBOMEntryService) {

		_commerceBOMEntryService = commerceBOMEntryService;
	}

	@Override
	public com.liferay.commerce.bom.model.CommerceBOMEntry addCommerceBOMEntry(
			long userId, int number, String cpInstanceUuid, long cProductId,
			long commerceBOMDefinitionId, double positionX, double positionY,
			double radius)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceBOMEntryService.addCommerceBOMEntry(
			userId, number, cpInstanceUuid, cProductId, commerceBOMDefinitionId,
			positionX, positionY, radius);
	}

	@Override
	public void deleteCommerceBOMEntry(long commerceBOMEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_commerceBOMEntryService.deleteCommerceBOMEntry(commerceBOMEntryId);
	}

	@Override
	public java.util.List<com.liferay.commerce.bom.model.CommerceBOMEntry>
			getCommerceBOMEntries(
				long commerceBOMDefinitionId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceBOMEntryService.getCommerceBOMEntries(
			commerceBOMDefinitionId, start, end);
	}

	@Override
	public int getCommerceBOMEntriesCount(long commerceBOMDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceBOMEntryService.getCommerceBOMEntriesCount(
			commerceBOMDefinitionId);
	}

	@Override
	public com.liferay.commerce.bom.model.CommerceBOMEntry getCommerceBOMEntry(
			long commerceBOMEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceBOMEntryService.getCommerceBOMEntry(commerceBOMEntryId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceBOMEntryService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.commerce.bom.model.CommerceBOMEntry
			updateCommerceBOMEntry(
				long commerceBOMEntryId, int number, String cpInstanceUuid,
				long cProductId, double positionX, double positionY,
				double radius)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceBOMEntryService.updateCommerceBOMEntry(
			commerceBOMEntryId, number, cpInstanceUuid, cProductId, positionX,
			positionY, radius);
	}

	@Override
	public CommerceBOMEntryService getWrappedService() {
		return _commerceBOMEntryService;
	}

	@Override
	public void setWrappedService(
		CommerceBOMEntryService commerceBOMEntryService) {

		_commerceBOMEntryService = commerceBOMEntryService;
	}

	private CommerceBOMEntryService _commerceBOMEntryService;

}