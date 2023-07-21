/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import AdminCatalogAPI from './commerce-admin-catalog/index';
import AdminPricingAPI from './commerce-admin-pricing/index';
import DeliveryCartAPI from './commerce-delivery-cart/index';

const ServiceProvider = {
	AdminCatalogAPI,
	AdminPricingAPI,
	DeliveryCartAPI
};

export default ServiceProvider;
