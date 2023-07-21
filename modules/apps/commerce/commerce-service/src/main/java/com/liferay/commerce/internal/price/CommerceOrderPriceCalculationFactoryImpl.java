/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.internal.price;

import com.liferay.commerce.currency.model.CommerceMoneyFactory;
import com.liferay.commerce.discount.CommerceDiscountCalculation;
import com.liferay.commerce.price.CommerceOrderPriceCalculation;
import com.liferay.commerce.price.CommerceOrderPriceCalculationFactory;
import com.liferay.commerce.pricing.configuration.CommercePricingConfiguration;
import com.liferay.commerce.pricing.constants.CommercePricingConstants;
import com.liferay.commerce.product.service.CommerceChannelLocalService;
import com.liferay.commerce.tax.CommerceTaxCalculation;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;

import java.util.Map;
import java.util.Objects;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	configurationPid = "com.liferay.commerce.pricing.configuration.CommercePricingConfiguration",
	immediate = true, service = CommerceOrderPriceCalculationFactory.class
)
public class CommerceOrderPriceCalculationFactoryImpl
	implements CommerceOrderPriceCalculationFactory {

	@Activate
	@Modified
	public void activate(Map<String, Object> properties) {
		_commercePricingConfiguration = ConfigurableUtil.createConfigurable(
			CommercePricingConfiguration.class, properties);
	}

	@Override
	public CommerceOrderPriceCalculation getCommerceOrderPriceCalculation() {
		if ((_commercePricingConfiguration == null) ||
			Objects.equals(
				_commercePricingConfiguration.commercePricingCalculationKey(),
				CommercePricingConstants.VERSION_2_0)) {

			return new CommerceOrderPriceCalculationV2Impl(
				_commerceChannelLocalService, _commerceDiscountCalculationV2,
				_commerceMoneyFactory, _commerceTaxCalculation);
		}

		return new CommerceOrderPriceCalculationImpl(
			_commerceChannelLocalService, _commerceDiscountCalculation,
			_commerceMoneyFactory, _commerceTaxCalculation);
	}

	@Reference
	private CommerceChannelLocalService _commerceChannelLocalService;

	@Reference(target = "(commerce.discount.calculation.key=v1.0)")
	private CommerceDiscountCalculation _commerceDiscountCalculation;

	@Reference(target = "(commerce.discount.calculation.key=v2.0)")
	private CommerceDiscountCalculation _commerceDiscountCalculationV2;

	@Reference
	private CommerceMoneyFactory _commerceMoneyFactory;

	private volatile CommercePricingConfiguration _commercePricingConfiguration;

	@Reference
	private CommerceTaxCalculation _commerceTaxCalculation;

}