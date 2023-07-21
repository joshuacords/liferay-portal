/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.discount.internal.application.strategy;

import com.liferay.commerce.discount.application.strategy.CommerceDiscountApplicationStrategy;
import com.liferay.commerce.pricing.constants.CommercePricingConstants;
import com.liferay.portal.kernel.exception.PortalException;

import java.math.BigDecimal;

import org.osgi.service.component.annotations.Component;

/**
 * @author Riccardo Alberti
 */
@Component(
	property = "commerce.discount.application.strategy.key=" + CommercePricingConstants.DISCOUNT_ADDITION_METHOD,
	service = CommerceDiscountApplicationStrategy.class
)
public class AdditionCommerceDiscountApplicationStrategyImpl
	implements CommerceDiscountApplicationStrategy {

	@Override
	public BigDecimal applyCommerceDiscounts(
			BigDecimal commercePrice, BigDecimal[] commerceDiscountLevels)
		throws PortalException {

		BigDecimal discountedAmount = commercePrice;
		BigDecimal totalDiscount = BigDecimal.ZERO;

		for (BigDecimal commerceDiscountLevel : commerceDiscountLevels) {
			if ((commerceDiscountLevel == null) ||
				(commerceDiscountLevel.compareTo(BigDecimal.ZERO) == 0)) {

				continue;
			}

			totalDiscount = totalDiscount.add(commerceDiscountLevel);

			BigDecimal currentDiscountAmount = commercePrice.multiply(
				totalDiscount);

			currentDiscountAmount = currentDiscountAmount.divide(_ONE_HUNDRED);

			discountedAmount = commercePrice.subtract(currentDiscountAmount);
		}

		return discountedAmount;
	}

	private static final BigDecimal _ONE_HUNDRED = BigDecimal.valueOf(100);

}