/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.external.reference.service.impl;

import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.external.reference.service.base.ERAssetVocabularyLocalServiceBaseImpl;
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
	property = "model.class.name=com.liferay.asset.kernel.model.AssetVocabulary",
	service = AopService.class
)
public class ERAssetVocabularyLocalServiceImpl
	extends ERAssetVocabularyLocalServiceBaseImpl {

	@Override
	public AssetVocabulary addOrUpdateVocabulary(
			String externalReferenceCode, long userId, long groupId,
			String title, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, String settings,
			ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		AssetVocabulary assetVocabulary =
			assetVocabularyLocalService.fetchAssetVocabularyByReferenceCode(
				user.getCompanyId(), externalReferenceCode);

		if (assetVocabulary == null) {
			assetVocabulary = assetVocabularyLocalService.addVocabulary(
				userId, groupId, title, titleMap, descriptionMap, settings,
				serviceContext);

			assetVocabulary.setExternalReferenceCode(externalReferenceCode);

			assetVocabularyLocalService.updateAssetVocabulary(assetVocabulary);
		}
		else {
			assetVocabulary = assetVocabularyLocalService.updateVocabulary(
				assetVocabulary.getVocabularyId(), title, titleMap,
				descriptionMap, settings, serviceContext);
		}

		return assetVocabulary;
	}

}