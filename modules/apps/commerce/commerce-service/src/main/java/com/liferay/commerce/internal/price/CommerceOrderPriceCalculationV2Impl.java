/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.internal.price;

import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.model.CommerceMoney;
import com.liferay.commerce.currency.model.CommerceMoneyFactory;
import com.liferay.commerce.discount.CommerceDiscountCalculation;
import com.liferay.commerce.discount.CommerceDiscountValue;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.price.CommerceOrderPrice;
import com.liferay.commerce.price.CommerceOrderPriceImpl;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.service.CommerceChannelLocalService;
import com.liferay.commerce.tax.CommerceTaxCalculation;
import com.liferay.portal.kernel.exception.PortalException;

import java.math.BigDecimal;

/**
 * @author Alessio Antonio Rendina
 * @author Marco Leo
 */
public class CommerceOrderPriceCalculationV2Impl
	extends BaseCommerceOrderPriceCalculation {

	public CommerceOrderPriceCalculationV2Impl(
		CommerceChannelLocalService commerceChannelLocalService,
		CommerceDiscountCalculation commerceDiscountCalculation,
		CommerceMoneyFactory commerceMoneyFactory,
		CommerceTaxCalculation commerceTaxCalculation) {

		super(commerceChannelLocalService, commerceMoneyFactory);

		_commerceDiscountCalculation = commerceDiscountCalculation;
		_commerceTaxCalculation = commerceTaxCalculation;
	}

	@Override
	public CommerceOrderPrice getCommerceOrderPrice(
			CommerceOrder commerceOrder, boolean secure,
			CommerceContext commerceContext)
		throws PortalException {

		if (commerceOrder == null) {
			return getEmptyCommerceOrderPrice(
				commerceContext.getCommerceCurrency());
		}

		if (!commerceOrder.isOpen()) {
			return getCommerceOrderPriceFromOrder(commerceOrder);
		}

		CommerceDiscountValue orderShippingCommerceDiscountValue;
		CommerceDiscountValue orderSubtotalCommerceDiscountValue;
		CommerceDiscountValue orderTotalCommerceDiscountValue;

		BigDecimal shippingAmount = commerceOrder.getShippingAmount();

		CommerceCurrency commerceCurrency = commerceOrder.getCommerceCurrency();

		CommerceMoney shippingTaxAmount =
			_commerceTaxCalculation.getShippingTaxValue(
				commerceOrder, commerceCurrency);

		BigDecimal shippingWithTaxAmount = shippingAmount.add(
			shippingTaxAmount.getPrice());

		CommerceMoney subtotalMoney = getSubtotal(
			commerceOrder, secure, commerceContext);

		BigDecimal subtotalAmount = subtotalMoney.getPrice();

		BigDecimal totalAmount = subtotalAmount;

		CommerceMoney taxValue = getTaxValue(
			commerceOrder, secure, commerceContext);

		BigDecimal subtotalWithTaxAmount = subtotalAmount.add(
			taxValue.getPrice());

		BigDecimal totalTaxValue = taxValue.getPrice();

		totalTaxValue = totalTaxValue.add(shippingTaxAmount.getPrice());

		BigDecimal totalWithTaxAmount = totalAmount.add(totalTaxValue);

		BigDecimal shippingDiscounted = shippingAmount;
		BigDecimal shippingDiscountedWithTaxAmount = shippingWithTaxAmount;
		BigDecimal subtotalDiscounted = subtotalAmount;
		BigDecimal subtotalDiscountedWithTaxAmount = subtotalWithTaxAmount;
		BigDecimal totalDiscounted = totalAmount;
		BigDecimal totalDiscountedWithTaxAmount = totalWithTaxAmount;

		boolean discountsTargetNetPrice = true;

		CommerceChannel commerceChannel =
			commerceChannelLocalService.fetchCommerceChannel(
				commerceContext.getCommerceChannelId());

		if (commerceChannel != null) {
			discountsTargetNetPrice =
				commerceChannel.isDiscountsTargetNetPrice();
		}

		if (discountsTargetNetPrice) {
			orderShippingCommerceDiscountValue =
				_commerceDiscountCalculation.
					getOrderShippingCommerceDiscountValue(
						commerceOrder, shippingAmount, commerceContext);

			orderSubtotalCommerceDiscountValue =
				_commerceDiscountCalculation.
					getOrderSubtotalCommerceDiscountValue(
						commerceOrder, subtotalAmount, commerceContext);

			if (orderSubtotalCommerceDiscountValue != null) {
				CommerceMoney discountAmount =
					orderSubtotalCommerceDiscountValue.getDiscountAmount();

				totalAmount = totalAmount.subtract(discountAmount.getPrice());

				subtotalDiscounted = subtotalDiscounted.subtract(
					discountAmount.getPrice());
			}

			totalAmount = totalAmount.add(shippingAmount);

			if (orderShippingCommerceDiscountValue != null) {
				CommerceMoney discountAmount =
					orderShippingCommerceDiscountValue.getDiscountAmount();

				totalAmount = totalAmount.subtract(discountAmount.getPrice());

				shippingDiscounted = shippingDiscounted.subtract(
					discountAmount.getPrice());
			}

			totalDiscounted = totalAmount;

			orderTotalCommerceDiscountValue =
				_commerceDiscountCalculation.getOrderTotalCommerceDiscountValue(
					commerceOrder, totalAmount, commerceContext);

			if (orderTotalCommerceDiscountValue != null) {
				CommerceMoney discountAmount =
					orderTotalCommerceDiscountValue.getDiscountAmount();

				totalDiscounted = totalDiscounted.subtract(
					discountAmount.getPrice());
			}

			subtotalDiscountedWithTaxAmount = subtotalDiscounted.add(
				taxValue.getPrice());
			shippingDiscountedWithTaxAmount = shippingDiscounted.add(
				shippingTaxAmount.getPrice());
			totalDiscountedWithTaxAmount = totalDiscounted.add(totalTaxValue);

			totalWithTaxAmount = totalAmount.add(totalTaxValue);
		}
		else {
			orderShippingCommerceDiscountValue =
				_commerceDiscountCalculation.
					getOrderShippingCommerceDiscountValue(
						commerceOrder, shippingWithTaxAmount, commerceContext);

			orderSubtotalCommerceDiscountValue =
				_commerceDiscountCalculation.
					getOrderSubtotalCommerceDiscountValue(
						commerceOrder, subtotalWithTaxAmount, commerceContext);

			if (orderSubtotalCommerceDiscountValue != null) {
				CommerceMoney discountAmount =
					orderSubtotalCommerceDiscountValue.getDiscountAmount();

				totalWithTaxAmount = totalWithTaxAmount.subtract(
					discountAmount.getPrice());

				subtotalDiscountedWithTaxAmount =
					subtotalDiscountedWithTaxAmount.subtract(
						discountAmount.getPrice());
			}

			totalWithTaxAmount = totalWithTaxAmount.add(shippingAmount);

			if (orderShippingCommerceDiscountValue != null) {
				CommerceMoney discountAmount =
					orderShippingCommerceDiscountValue.getDiscountAmount();

				totalWithTaxAmount = totalWithTaxAmount.subtract(
					discountAmount.getPrice());

				shippingDiscountedWithTaxAmount =
					shippingDiscountedWithTaxAmount.subtract(
						discountAmount.getPrice());
			}

			totalDiscountedWithTaxAmount = totalWithTaxAmount;

			orderTotalCommerceDiscountValue =
				_commerceDiscountCalculation.getOrderTotalCommerceDiscountValue(
					commerceOrder, totalWithTaxAmount, commerceContext);

			if (orderTotalCommerceDiscountValue != null) {
				CommerceMoney discountAmount =
					orderTotalCommerceDiscountValue.getDiscountAmount();

				totalDiscountedWithTaxAmount =
					totalDiscountedWithTaxAmount.subtract(
						discountAmount.getPrice());
			}

			shippingDiscounted = shippingDiscountedWithTaxAmount.subtract(
				shippingTaxAmount.getPrice());
			subtotalDiscounted = subtotalDiscountedWithTaxAmount.subtract(
				taxValue.getPrice());
			totalDiscounted = totalDiscountedWithTaxAmount.subtract(
				totalTaxValue);

			totalAmount = totalWithTaxAmount.subtract(totalTaxValue);
		}

		// fill data

		CommerceOrderPriceImpl commerceOrderPriceImpl =
			new CommerceOrderPriceImpl();

		setDiscountValues(
			discountsTargetNetPrice, shippingAmount, shippingDiscounted,
			orderShippingCommerceDiscountValue, subtotalAmount,
			subtotalDiscounted, orderSubtotalCommerceDiscountValue, totalAmount,
			totalDiscounted, orderTotalCommerceDiscountValue,
			commerceOrderPriceImpl, commerceOrder);

		commerceOrderPriceImpl.setShippingValue(
			commerceMoneyFactory.create(
				commerceOrder.getCommerceCurrency(), shippingAmount));
		commerceOrderPriceImpl.setShippingValueWithTaxAmount(
			commerceMoneyFactory.create(
				commerceOrder.getCommerceCurrency(), shippingWithTaxAmount));
		commerceOrderPriceImpl.setSubtotal(subtotalMoney);
		commerceOrderPriceImpl.setSubtotalWithTaxAmount(
			commerceMoneyFactory.create(
				commerceOrder.getCommerceCurrency(), subtotalWithTaxAmount));
		commerceOrderPriceImpl.setTaxValue(
			commerceMoneyFactory.create(
				commerceOrder.getCommerceCurrency(), totalTaxValue));
		commerceOrderPriceImpl.setTotal(
			commerceMoneyFactory.create(
				commerceOrder.getCommerceCurrency(),
				totalDiscounted.add(totalTaxValue)));
		commerceOrderPriceImpl.setTotalWithTaxAmount(
			commerceMoneyFactory.create(
				commerceOrder.getCommerceCurrency(),
				totalDiscountedWithTaxAmount));

		setDiscountValuesWithTaxAmount(
			discountsTargetNetPrice, shippingWithTaxAmount,
			shippingDiscountedWithTaxAmount, orderShippingCommerceDiscountValue,
			subtotalWithTaxAmount, subtotalDiscountedWithTaxAmount,
			orderSubtotalCommerceDiscountValue, totalWithTaxAmount,
			totalDiscountedWithTaxAmount, orderTotalCommerceDiscountValue,
			commerceOrderPriceImpl, commerceOrder);

		return commerceOrderPriceImpl;
	}

	@Override
	public CommerceOrderPrice getCommerceOrderPrice(
			CommerceOrder commerceOrder, CommerceContext commerceContext)
		throws PortalException {

		return getCommerceOrderPrice(commerceOrder, true, commerceContext);
	}

	@Override
	public CommerceMoney getSubtotal(
			CommerceOrder commerceOrder, boolean secure,
			CommerceContext commerceContext)
		throws PortalException {

		BigDecimal subtotal = BigDecimal.ZERO;

		if (commerceOrder == null) {
			return commerceMoneyFactory.create(
				commerceContext.getCommerceCurrency(), subtotal);
		}

		if (!commerceOrder.isOpen()) {
			return commerceMoneyFactory.create(
				commerceContext.getCommerceCurrency(),
				commerceOrder.getSubtotal());
		}

		for (CommerceOrderItem commerceOrderItem :
				commerceOrder.getCommerceOrderItems()) {

			subtotal = subtotal.add(commerceOrderItem.getFinalPrice());
		}

		return commerceMoneyFactory.create(
			commerceContext.getCommerceCurrency(), subtotal);
	}

	@Override
	public CommerceMoney getSubtotal(
			CommerceOrder commerceOrder, CommerceContext commerceContext)
		throws PortalException {

		return getSubtotal(commerceOrder, true, commerceContext);
	}

	@Override
	public CommerceMoney getTaxValue(
			CommerceOrder commerceOrder, boolean secure,
			CommerceContext commerceContext)
		throws PortalException {

		if (commerceOrder == null) {
			return commerceMoneyFactory.create(
				commerceContext.getCommerceCurrency(), BigDecimal.ZERO);
		}

		if (!commerceOrder.isOpen()) {
			return commerceMoneyFactory.create(
				commerceContext.getCommerceCurrency(),
				commerceOrder.getTaxAmount());
		}

		return _commerceTaxCalculation.getTaxAmount(
			commerceOrder, commerceContext.getCommerceCurrency());
	}

	@Override
	public CommerceMoney getTaxValue(
			CommerceOrder commerceOrder, CommerceContext commerceContext)
		throws PortalException {

		return getTaxValue(commerceOrder, true, commerceContext);
	}

	@Override
	public CommerceMoney getTotal(
			CommerceOrder commerceOrder, boolean secure,
			CommerceContext commerceContext)
		throws PortalException {

		if (!commerceOrder.isOpen()) {
			return commerceMoneyFactory.create(
				commerceContext.getCommerceCurrency(),
				commerceOrder.getTotal());
		}

		CommerceOrderPrice commerceOrderPrice = getCommerceOrderPrice(
			commerceOrder, commerceContext);

		return commerceOrderPrice.getTotal();
	}

	@Override
	public CommerceMoney getTotal(
			CommerceOrder commerceOrder, CommerceContext commerceContext)
		throws PortalException {

		return getTotal(commerceOrder, true, commerceContext);
	}

	private final CommerceDiscountCalculation _commerceDiscountCalculation;
	private final CommerceTaxCalculation _commerceTaxCalculation;

}