/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.admin.pricing.internal.util.v2_0;

import com.liferay.asset.kernel.exception.NoSuchCategoryException;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.commerce.discount.model.CommerceDiscount;
import com.liferay.commerce.discount.model.CommerceDiscountRel;
import com.liferay.commerce.discount.service.CommerceDiscountRelService;
import com.liferay.headless.commerce.admin.pricing.dto.v2_0.DiscountCategory;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Riccardo Alberti
 */
public class DiscountCategoryUtil {

	public static CommerceDiscountRel addCommerceDiscountRel(
			AssetCategoryLocalService assetCategoryLocalService,
			CommerceDiscountRelService commerceDiscountRelService,
			DiscountCategory discountCategory,
			CommerceDiscount commerceDiscount,
			ServiceContextHelper serviceContextHelper)
		throws PortalException {

		ServiceContext serviceContext =
			serviceContextHelper.getServiceContext();

		AssetCategory assetCategory;

		if (Validator.isNull(
				discountCategory.getCategoryExternalReferenceCode())) {

			assetCategory = assetCategoryLocalService.getCategory(
				discountCategory.getCategoryId());
		}
		else {
			assetCategory =
				assetCategoryLocalService.fetchAssetCategoryByReferenceCode(
					serviceContext.getCompanyId(),
					discountCategory.getCategoryExternalReferenceCode());

			if (assetCategory == null) {
				throw new NoSuchCategoryException(
					"Unable to find Category with externalReferenceCode: " +
						discountCategory.getCategoryExternalReferenceCode());
			}
		}

		return commerceDiscountRelService.addCommerceDiscountRel(
			commerceDiscount.getCommerceDiscountId(),
			AssetCategory.class.getName(), assetCategory.getCategoryId(),
			serviceContext);
	}

}