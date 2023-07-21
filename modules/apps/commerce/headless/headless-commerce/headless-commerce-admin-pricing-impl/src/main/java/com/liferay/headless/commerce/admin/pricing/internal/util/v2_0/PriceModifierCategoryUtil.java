/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.admin.pricing.internal.util.v2_0;

import com.liferay.asset.kernel.exception.NoSuchCategoryException;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.commerce.pricing.model.CommercePriceModifier;
import com.liferay.commerce.pricing.model.CommercePriceModifierRel;
import com.liferay.commerce.pricing.service.CommercePriceModifierRelService;
import com.liferay.headless.commerce.admin.pricing.dto.v2_0.PriceModifierCategory;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Riccardo Alberti
 */
public class PriceModifierCategoryUtil {

	public static CommercePriceModifierRel addCommercePriceModifierRel(
			AssetCategoryLocalService assetCategoryLocalService,
			CommercePriceModifierRelService commercePriceModifierRelService,
			PriceModifierCategory priceModifierCategory,
			CommercePriceModifier commercePriceModifier,
			ServiceContextHelper serviceContextHelper)
		throws PortalException {

		ServiceContext serviceContext =
			serviceContextHelper.getServiceContext();

		AssetCategory assetCategory;

		if (Validator.isNull(
				priceModifierCategory.getCategoryExternalReferenceCode())) {

			assetCategory = assetCategoryLocalService.getCategory(
				priceModifierCategory.getCategoryId());
		}
		else {
			assetCategory =
				assetCategoryLocalService.fetchAssetCategoryByReferenceCode(
					serviceContext.getCompanyId(),
					priceModifierCategory.getCategoryExternalReferenceCode());

			if (assetCategory == null) {
				throw new NoSuchCategoryException(
					"Unable to find Category with externalReferenceCode: " +
						priceModifierCategory.
							getCategoryExternalReferenceCode());
			}
		}

		return commercePriceModifierRelService.addCommercePriceModifierRel(
			commercePriceModifier.getCommercePriceModifierId(),
			AssetCategory.class.getName(), assetCategory.getCategoryId(),
			serviceContext);
	}

}