/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.service.persistence;

import aQute.bnd.annotation.ProviderType;

/**
 * @author Alessio Antonio Rendina
 * @generated
 */
@ProviderType
public interface CommerceCountryFinder {

	public java.util.List<com.liferay.commerce.model.CommerceCountry>
		findByCommerceInventoryWarehouses(long companyId, boolean all);

	public java.util.List<com.liferay.commerce.model.CommerceCountry>
		findByCommerceChannel(
			long commerceChannelId, boolean shippingAllowed,
			boolean billingAllowed, int start, int end);

}