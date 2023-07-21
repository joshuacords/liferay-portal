/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.price;

import aQute.bnd.annotation.ProviderType;

/**
 * @author Riccardo Alberti
 */
@ProviderType
public interface CommerceProductPriceCalculationFactory {

	public CommerceProductPriceCalculation getCommerceProductPriceCalculation();

}