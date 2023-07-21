/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.bom.service.impl;

import com.liferay.commerce.bom.model.CommerceBOMDefinition;
import com.liferay.commerce.bom.model.CommerceBOMEntry;
import com.liferay.commerce.bom.service.base.CommerceBOMEntryServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;

import java.util.List;

/**
 * @author Luca Pellizzon
 * @author Alessio Antonio Rendina
 */
public class CommerceBOMEntryServiceImpl
	extends CommerceBOMEntryServiceBaseImpl {

	@Override
	public CommerceBOMEntry addCommerceBOMEntry(
			long userId, int number, String cpInstanceUuid, long cProductId,
			long commerceBOMDefinitionId, double positionX, double positionY,
			double radius)
		throws PortalException {

		_commerceBOMDefinitionModelResourcePermission.check(
			getPermissionChecker(), commerceBOMDefinitionId, ActionKeys.UPDATE);

		return commerceBOMEntryLocalService.addCommerceBOMEntry(
			userId, number, cpInstanceUuid, cProductId, commerceBOMDefinitionId,
			positionX, positionY, radius);
	}

	@Override
	public void deleteCommerceBOMEntry(long commerceBOMEntryId)
		throws PortalException {

		CommerceBOMEntry commerceBOMEntry =
			commerceBOMEntryLocalService.getCommerceBOMEntry(
				commerceBOMEntryId);

		_commerceBOMDefinitionModelResourcePermission.check(
			getPermissionChecker(),
			commerceBOMEntry.getCommerceBOMDefinitionId(), ActionKeys.UPDATE);

		commerceBOMEntryLocalService.deleteCommerceBOMEntry(commerceBOMEntry);
	}

	@Override
	public List<CommerceBOMEntry> getCommerceBOMEntries(
			long commerceBOMDefinitionId, int start, int end)
		throws PortalException {

		_commerceBOMDefinitionModelResourcePermission.check(
			getPermissionChecker(), commerceBOMDefinitionId, ActionKeys.VIEW);

		return commerceBOMEntryLocalService.getCommerceBOMEntries(
			commerceBOMDefinitionId, start, end);
	}

	@Override
	public int getCommerceBOMEntriesCount(long commerceBOMDefinitionId)
		throws PortalException {

		_commerceBOMDefinitionModelResourcePermission.check(
			getPermissionChecker(), commerceBOMDefinitionId, ActionKeys.VIEW);

		return commerceBOMEntryLocalService.getCommerceBOMEntriesCount(
			commerceBOMDefinitionId);
	}

	@Override
	public CommerceBOMEntry getCommerceBOMEntry(long commerceBOMEntryId)
		throws PortalException {

		CommerceBOMEntry commerceBOMEntry =
			commerceBOMEntryLocalService.getCommerceBOMEntry(
				commerceBOMEntryId);

		_commerceBOMDefinitionModelResourcePermission.check(
			getPermissionChecker(),
			commerceBOMEntry.getCommerceBOMDefinitionId(), ActionKeys.VIEW);

		return commerceBOMEntry;
	}

	@Override
	public CommerceBOMEntry updateCommerceBOMEntry(
			long commerceBOMEntryId, int number, String cpInstanceUuid,
			long cProductId, double positionX, double positionY, double radius)
		throws PortalException {

		CommerceBOMEntry commerceBOMEntry =
			commerceBOMEntryLocalService.getCommerceBOMEntry(
				commerceBOMEntryId);

		_commerceBOMDefinitionModelResourcePermission.check(
			getPermissionChecker(),
			commerceBOMEntry.getCommerceBOMDefinitionId(), ActionKeys.UPDATE);

		return commerceBOMEntryLocalService.updateCommerceBOMEntry(
			commerceBOMEntryId, number, cpInstanceUuid, cProductId, positionX,
			positionY, radius);
	}

	private static volatile ModelResourcePermission<CommerceBOMDefinition>
		_commerceBOMDefinitionModelResourcePermission =
			ModelResourcePermissionFactory.getInstance(
				CommerceBOMEntryServiceImpl.class,
				"_commerceBOMDefinitionModelResourcePermission",
				CommerceBOMDefinition.class);

}