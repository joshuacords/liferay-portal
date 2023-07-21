/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.price;

import aQute.bnd.annotation.ProviderType;

import java.util.Map;

/**
 * @author Riccardo Alberti
 */
@ProviderType
public interface CommercePriceCalculationRegistry {

	public CommerceOrderPriceCalculation getCommerceOrderPriceCalculation(
		String key);

	public Map<String, CommerceOrderPriceCalculation>
		getCommerceOrderPriceCalculations();

	public CommerceProductPriceCalculation getCommerceProductPriceCalculation(
		String key);

	public Map<String, CommerceProductPriceCalculation>
		getCommerceProductPriceCalculations();

}