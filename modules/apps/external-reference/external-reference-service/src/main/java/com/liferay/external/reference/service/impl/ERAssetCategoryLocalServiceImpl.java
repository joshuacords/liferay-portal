/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.external.reference.service.impl;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.external.reference.service.base.ERAssetCategoryLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

/**
 * @author Dylan Rebelak
 */
@Component(
	property = "model.class.name=com.liferay.asset.kernel.model.AssetCategory",
	service = AopService.class
)
public class ERAssetCategoryLocalServiceImpl
	extends ERAssetCategoryLocalServiceBaseImpl {

	@Override
	public AssetCategory addOrUpdateCategory(
			String externalReferenceCode, long userId, long groupId,
			long parentCategoryId, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, long vocabularyId,
			String[] categoryProperties, ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		AssetCategory assetCategory =
			assetCategoryLocalService.fetchAssetCategoryByReferenceCode(
				user.getCompanyId(), externalReferenceCode);

		if (assetCategory == null) {
			assetCategory = assetCategoryLocalService.addCategory(
				userId, groupId, parentCategoryId, titleMap, descriptionMap,
				vocabularyId, categoryProperties, serviceContext);

			assetCategory.setExternalReferenceCode(externalReferenceCode);

			assetCategory = assetCategoryLocalService.updateAssetCategory(
				assetCategory);
		}
		else {
			assetCategory = assetCategoryLocalService.updateCategory(
				userId, assetCategory.getCategoryId(), parentCategoryId,
				titleMap, descriptionMap, vocabularyId, categoryProperties,
				serviceContext);
		}

		return assetCategory;
	}

}