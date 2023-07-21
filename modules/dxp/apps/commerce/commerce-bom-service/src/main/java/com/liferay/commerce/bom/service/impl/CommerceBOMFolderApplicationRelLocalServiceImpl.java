/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.bom.service.impl;

import com.liferay.commerce.bom.model.CommerceBOMFolderApplicationRel;
import com.liferay.commerce.bom.service.base.CommerceBOMFolderApplicationRelLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;

import java.util.List;

/**
 * @author Luca Pellizzon
 * @author Alessio Antonio Rendina
 */
public class CommerceBOMFolderApplicationRelLocalServiceImpl
	extends CommerceBOMFolderApplicationRelLocalServiceBaseImpl {

	@Override
	public CommerceBOMFolderApplicationRel addCommerceBOMFolderApplicationRel(
			long userId, long commerceBOMFolderId,
			long commerceApplicationModelId)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		long commerceBOMFolderApplicationRelId =
			counterLocalService.increment();

		CommerceBOMFolderApplicationRel commerceBOMFolderApplicationRel =
			commerceBOMFolderApplicationRelPersistence.create(
				commerceBOMFolderApplicationRelId);

		commerceBOMFolderApplicationRel.setCompanyId(user.getCompanyId());
		commerceBOMFolderApplicationRel.setUserId(user.getUserId());
		commerceBOMFolderApplicationRel.setUserName(user.getFullName());
		commerceBOMFolderApplicationRel.setCommerceBOMFolderId(
			commerceBOMFolderId);
		commerceBOMFolderApplicationRel.setCommerceApplicationModelId(
			commerceApplicationModelId);

		return commerceBOMFolderApplicationRelPersistence.update(
			commerceBOMFolderApplicationRel);
	}

	@Override
	public void deleteCommerceBOMFolderApplicationRelsByCAMId(
		long commerceApplicationModelId) {

		commerceBOMFolderApplicationRelPersistence.
			removeByCommerceApplicationModelId(commerceApplicationModelId);
	}

	@Override
	public void deleteCommerceBOMFolderApplicationRelsByCommerceBOMFolderId(
		long commerceBOMFolderId) {

		commerceBOMFolderApplicationRelPersistence.removeByCommerceBOMFolderId(
			commerceBOMFolderId);
	}

	@Override
	public List<CommerceBOMFolderApplicationRel>
		getCommerceBOMFolderApplicationRelsByCAMId(
			long commerceApplicationModelId, int start, int end) {

		return commerceBOMFolderApplicationRelPersistence.
			findByCommerceApplicationModelId(
				commerceApplicationModelId, start, end);
	}

	@Override
	public List<CommerceBOMFolderApplicationRel>
		getCommerceBOMFolderApplicationRelsByCommerceBOMFolderId(
			long commerceBOMFolderId, int start, int end) {

		return commerceBOMFolderApplicationRelPersistence.
			findByCommerceBOMFolderId(commerceBOMFolderId, start, end);
	}

	@Override
	public int getCommerceBOMFolderApplicationRelsCountByCAMId(
		long commerceApplicationModelId) {

		return commerceBOMFolderApplicationRelPersistence.
			countByCommerceApplicationModelId(commerceApplicationModelId);
	}

	@Override
	public int getCommerceBOMFolderApplicationRelsCountByCommerceBOMFolderId(
		long commerceBOMFolderId) {

		return commerceBOMFolderApplicationRelPersistence.
			countByCommerceBOMFolderId(commerceBOMFolderId);
	}

}