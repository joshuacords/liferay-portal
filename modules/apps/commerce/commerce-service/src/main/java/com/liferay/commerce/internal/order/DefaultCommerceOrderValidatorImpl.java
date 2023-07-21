/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.internal.order;

import com.liferay.commerce.inventory.CPDefinitionInventoryEngine;
import com.liferay.commerce.inventory.CPDefinitionInventoryEngineRegistry;
import com.liferay.commerce.model.CPDefinitionInventory;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.order.CommerceOrderValidator;
import com.liferay.commerce.order.CommerceOrderValidatorResult;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.service.CPDefinitionInventoryLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = {
		"commerce.order.validator.key=" + DefaultCommerceOrderValidatorImpl.KEY,
		"commerce.order.validator.priority:Integer=10"
	},
	service = CommerceOrderValidator.class
)
public class DefaultCommerceOrderValidatorImpl
	implements CommerceOrderValidator {

	public static final String KEY = "default";

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public CommerceOrderValidatorResult validate(
			Locale locale, CommerceOrder commerceOrder, CPInstance cpInstance,
			int quantity)
		throws PortalException {

		if (cpInstance == null) {
			return new CommerceOrderValidatorResult(false);
		}

		CPDefinitionInventory cpDefinitionInventory =
			_cpDefinitionInventoryLocalService.
				fetchCPDefinitionInventoryByCPDefinitionId(
					cpInstance.getCPDefinitionId());

		CPDefinitionInventoryEngine cpDefinitionInventoryEngine =
			_cpDefinitionInventoryEngineRegistry.getCPDefinitionInventoryEngine(
				cpDefinitionInventory);

		int minOrderQuantity = cpDefinitionInventoryEngine.getMinOrderQuantity(
			cpInstance);
		int maxOrderQuantity = cpDefinitionInventoryEngine.getMaxOrderQuantity(
			cpInstance);
		String[] allowedOrderQuantities =
			cpDefinitionInventoryEngine.getAllowedOrderQuantities(cpInstance);

		if (cpDefinitionInventoryEngine.isBackOrderAllowed(cpInstance) &&
			(quantity >= minOrderQuantity) && (quantity <= maxOrderQuantity)) {

			return new CommerceOrderValidatorResult(true);
		}

		if (quantity < minOrderQuantity) {
			return new CommerceOrderValidatorResult(
				false,
				_getLocalizedMessage(
					locale, "the-minimum-quantity-is-x",
					new Object[] {minOrderQuantity}));
		}

		if ((maxOrderQuantity > 0) && (quantity > maxOrderQuantity)) {
			return new CommerceOrderValidatorResult(
				false,
				_getLocalizedMessage(
					locale, "the-maximum-quantity-is-x",
					new Object[] {maxOrderQuantity}));
		}

		if ((allowedOrderQuantities.length > 0) &&
			!ArrayUtil.contains(
				allowedOrderQuantities, String.valueOf(quantity))) {

			return new CommerceOrderValidatorResult(
				false,
				_getLocalizedMessage(
					locale, "that-quantity-is-not-allowed", null));
		}

		return new CommerceOrderValidatorResult(true);
	}

	@Override
	public CommerceOrderValidatorResult validate(
			Locale locale, CommerceOrderItem commerceOrderItem)
		throws PortalException {

		CPInstance cpInstance = commerceOrderItem.fetchCPInstance();

		if (cpInstance == null) {
			return new CommerceOrderValidatorResult(false);
		}

		CPDefinitionInventory cpDefinitionInventory =
			_cpDefinitionInventoryLocalService.
				fetchCPDefinitionInventoryByCPDefinitionId(
					cpInstance.getCPDefinitionId());

		CPDefinitionInventoryEngine cpDefinitionInventoryEngine =
			_cpDefinitionInventoryEngineRegistry.getCPDefinitionInventoryEngine(
				cpDefinitionInventory);

		if (cpDefinitionInventoryEngine.isBackOrderAllowed(cpInstance)) {
			return new CommerceOrderValidatorResult(true);
		}

		int minOrderQuantity = cpDefinitionInventoryEngine.getMinOrderQuantity(
			cpInstance);

		if (commerceOrderItem.getQuantity() < minOrderQuantity) {
			return new CommerceOrderValidatorResult(
				commerceOrderItem.getCommerceOrderItemId(), false,
				_getLocalizedMessage(
					locale, "the-minimum-quantity-is-x",
					new Object[] {minOrderQuantity}));
		}

		int maxOrderQuantity = cpDefinitionInventoryEngine.getMaxOrderQuantity(
			cpInstance);

		if ((maxOrderQuantity > 0) &&
			(commerceOrderItem.getQuantity() > maxOrderQuantity)) {

			return new CommerceOrderValidatorResult(
				commerceOrderItem.getCommerceOrderItemId(), false,
				_getLocalizedMessage(
					locale, "the-maximum-quantity-is-x",
					new Object[] {maxOrderQuantity}));
		}

		String[] allowedOrderQuantities =
			cpDefinitionInventoryEngine.getAllowedOrderQuantities(cpInstance);

		if ((allowedOrderQuantities.length > 0) &&
			!ArrayUtil.contains(
				allowedOrderQuantities,
				String.valueOf(commerceOrderItem.getQuantity()))) {

			return new CommerceOrderValidatorResult(
				commerceOrderItem.getCommerceOrderItemId(), false,
				_getLocalizedMessage(
					locale, "that-quantity-is-not-allowed", null));
		}

		return new CommerceOrderValidatorResult(true);
	}

	private String _getLocalizedMessage(
		Locale locale, String key, Object[] arguments) {

		if (locale == null) {
			return key;
		}

		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		if (arguments == null) {
			return LanguageUtil.get(resourceBundle, key);
		}

		return LanguageUtil.format(resourceBundle, key, arguments);
	}

	@Reference
	private CPDefinitionInventoryEngineRegistry
		_cpDefinitionInventoryEngineRegistry;

	@Reference
	private CPDefinitionInventoryLocalService
		_cpDefinitionInventoryLocalService;

}