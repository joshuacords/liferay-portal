/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.admin.pricing.internal.util.v1_0;

import com.liferay.commerce.account.exception.NoSuchAccountGroupException;
import com.liferay.commerce.account.model.CommerceAccountGroup;
import com.liferay.commerce.account.service.CommerceAccountGroupService;
import com.liferay.commerce.discount.model.CommerceDiscount;
import com.liferay.commerce.discount.model.CommerceDiscountCommerceAccountGroupRel;
import com.liferay.commerce.discount.service.CommerceDiscountCommerceAccountGroupRelService;
import com.liferay.headless.commerce.admin.pricing.dto.v1_0.DiscountAccountGroup;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Alessio Antonio Rendina
 */
public class DiscountAccountGroupUtil {

	public static CommerceDiscountCommerceAccountGroupRel
			addCommerceDiscountCommerceAccountGroupRel(
				CommerceAccountGroupService commerceAccountGroupService,
				CommerceDiscountCommerceAccountGroupRelService
					commerceDiscountCommerceAccountGroupRelService,
				DiscountAccountGroup discountAccountGroup,
				CommerceDiscount commerceDiscount,
				ServiceContext serviceContext)
		throws PortalException {

		CommerceAccountGroup commerceAccountGroup;

		if (Validator.isNull(
				discountAccountGroup.getAccountGroupExternalReferenceCode())) {

			commerceAccountGroup =
				commerceAccountGroupService.getCommerceAccountGroup(
					discountAccountGroup.getAccountGroupId());
		}
		else {
			commerceAccountGroup =
				commerceAccountGroupService.fetchByExternalReferenceCode(
					serviceContext.getCompanyId(),
					discountAccountGroup.
						getAccountGroupExternalReferenceCode());

			if (commerceAccountGroup == null) {
				throw new NoSuchAccountGroupException(
					"Unable to find AccountGroup with externalReferenceCode: " +
						discountAccountGroup.
							getAccountGroupExternalReferenceCode());
			}
		}

		return commerceDiscountCommerceAccountGroupRelService.
			addCommerceDiscountCommerceAccountGroupRel(
				commerceDiscount.getCommerceDiscountId(),
				commerceAccountGroup.getCommerceAccountGroupId(),
				serviceContext);
	}

}