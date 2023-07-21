/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.application.service.impl;

import com.liferay.commerce.application.model.CommerceApplicationModelCProductRel;
import com.liferay.commerce.application.service.base.CommerceApplicationModelCProductRelLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;

import java.util.List;

/**
 * @author Luca Pellizzon
 * @author Alessio Antonio Rendina
 */
public class CommerceApplicationModelCProductRelLocalServiceImpl
	extends CommerceApplicationModelCProductRelLocalServiceBaseImpl {

	@Override
	public CommerceApplicationModelCProductRel
			addCommerceApplicationModelCProductRel(
				long userId, long commerceApplicationModelId, long cProductId)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		long caModelCProductRelId = counterLocalService.increment();

		CommerceApplicationModelCProductRel caModelCProductRel =
			commerceApplicationModelCProductRelPersistence.create(
				caModelCProductRelId);

		caModelCProductRel.setCompanyId(user.getCompanyId());
		caModelCProductRel.setUserId(user.getUserId());
		caModelCProductRel.setUserName(user.getFullName());
		caModelCProductRel.setCommerceApplicationModelId(
			commerceApplicationModelId);
		caModelCProductRel.setCProductId(cProductId);

		return commerceApplicationModelCProductRelPersistence.update(
			caModelCProductRel);
	}

	@Override
	public void deleteCommerceApplicationModelCProductRels(
		long commerceApplicationModelId) {

		commerceApplicationModelCProductRelPersistence.
			removeByCommerceApplicationModelId(commerceApplicationModelId);
	}

	@Override
	public void deleteCommerceApplicationModelCProductRelsByCProductId(
		long cProductId) {

		commerceApplicationModelCProductRelPersistence.removeByCProductId(
			cProductId);
	}

	@Override
	public List<CommerceApplicationModelCProductRel>
		getCommerceApplicationModelCProductRels(
			long commerceApplicationModelId, int start, int end) {

		return commerceApplicationModelCProductRelPersistence.
			findByCommerceApplicationModelId(
				commerceApplicationModelId, start, end);
	}

	@Override
	public int getCommerceApplicationModelCProductRelsCount(
		long commerceApplicationModelId) {

		return commerceApplicationModelCProductRelPersistence.
			countByCommerceApplicationModelId(commerceApplicationModelId);
	}

}