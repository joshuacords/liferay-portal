/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.bom.service.impl;

import com.liferay.commerce.bom.model.CommerceBOMDefinition;
import com.liferay.commerce.bom.service.base.CommerceBOMDefinitionLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil;

import java.util.List;

/**
 * @author Luca Pellizzon
 * @author Alessio Antonio Rendina
 */
public class CommerceBOMDefinitionLocalServiceImpl
	extends CommerceBOMDefinitionLocalServiceBaseImpl {

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceBOMDefinition addCommerceBOMDefinition(
			long userId, long commerceBOMFolderId, long cpAttachmentFileEntryId,
			String name, String friendlyUrl)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		long commerceBOMDefinitionId = counterLocalService.increment();

		CommerceBOMDefinition commerceBOMDefinition =
			commerceBOMDefinitionPersistence.create(commerceBOMDefinitionId);

		commerceBOMDefinition.setCompanyId(user.getCompanyId());
		commerceBOMDefinition.setUserId(user.getUserId());
		commerceBOMDefinition.setUserName(user.getFullName());
		commerceBOMDefinition.setCommerceBOMFolderId(commerceBOMFolderId);
		commerceBOMDefinition.setCPAttachmentFileEntryId(
			cpAttachmentFileEntryId);
		commerceBOMDefinition.setName(name);
		commerceBOMDefinition.setFriendlyUrl(
			FriendlyURLNormalizerUtil.normalizeWithPeriodsAndSlashes(
				friendlyUrl));

		commerceBOMDefinition = commerceBOMDefinitionPersistence.update(
			commerceBOMDefinition);

		// Resources

		resourceLocalService.addResources(
			user.getCompanyId(), GroupConstants.DEFAULT_LIVE_GROUP_ID,
			user.getUserId(), CommerceBOMDefinition.class.getName(),
			commerceBOMDefinition.getCommerceBOMDefinitionId(), false, false,
			false);

		return commerceBOMDefinition;
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public CommerceBOMDefinition deleteCommerceBOMDefinition(
			CommerceBOMDefinition commerceBOMDefinition)
		throws PortalException {

		// Resources

		resourceLocalService.deleteResource(
			commerceBOMDefinition, ResourceConstants.SCOPE_INDIVIDUAL);

		// Commerce BOM definition

		return commerceBOMDefinitionPersistence.remove(commerceBOMDefinition);
	}

	@Override
	public CommerceBOMDefinition deleteCommerceBOMDefinition(
			long commerceBOMDefinitionId)
		throws PortalException {

		CommerceBOMDefinition commerceBOMDefinition =
			commerceBOMDefinitionPersistence.findByPrimaryKey(
				commerceBOMDefinitionId);

		return commerceBOMDefinitionLocalService.deleteCommerceBOMDefinition(
			commerceBOMDefinition);
	}

	@Override
	public void deleteCommerceBOMDefinitions(long commerceBOMFolderId)
		throws PortalException {

		List<CommerceBOMDefinition> commerceBOMDefinitions =
			commerceBOMDefinitionPersistence.findByCommerceBOMFolderId(
				commerceBOMFolderId);

		for (CommerceBOMDefinition commerceBOMDefinition :
				commerceBOMDefinitions) {

			commerceBOMDefinitionLocalService.deleteCommerceBOMDefinition(
				commerceBOMDefinition);
		}
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceBOMDefinition updateCommerceBOMDefinition(
			long commerceBOMDefinitionId, long cpAttachmentFileEntryId,
			String name)
		throws PortalException {

		CommerceBOMDefinition commerceBOMDefinition =
			commerceBOMDefinitionLocalService.getCommerceBOMDefinition(
				commerceBOMDefinitionId);

		commerceBOMDefinition.setCPAttachmentFileEntryId(
			cpAttachmentFileEntryId);
		commerceBOMDefinition.setName(name);

		return commerceBOMDefinitionPersistence.update(commerceBOMDefinition);
	}

}