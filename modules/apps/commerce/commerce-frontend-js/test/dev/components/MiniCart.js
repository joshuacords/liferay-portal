/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import launcher from '../../../src/main/resources/META-INF/resources/components/mini_cart/entry';

import '../../../src/main/resources/META-INF/resources/styles/main.scss';

launcher('mini_cart', 'mini-cart-root-id', {
	cartActionURLs: {
		checkoutURL: 'http://localhost:8080',
		orderDetailURL: 'http://localhost:8080?commerceOrderUuid=12323'
	},
	spritemap: './assets/icons.svg'
});
