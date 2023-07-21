/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import AJAX from '../../../utilities/AJAX/index';

const CARTS_PATH = '/carts',
	CHANNELS_PATH = '/channels';

const VERSION = 'v1.0';

function resolveCartsPath(basePath = '', cartId) {
	return `${basePath}${VERSION}${CARTS_PATH}/${cartId}`;
}

function resolveChannelsPath(basePath = '', channelId) {
	return `${basePath}${VERSION}${CHANNELS_PATH}/${channelId}${CARTS_PATH}`;
}

export default basePath => ({
	createCartByChannelId: (channelId, json) =>
		AJAX.POST(resolveChannelsPath(basePath, channelId), json),

	createCouponCodeByCartId: (cartId, json) =>
		AJAX.POST(`${resolveCartsPath(basePath, cartId)}/coupon-code`, json),

	deleteCartById: cartId => AJAX.DELETE(resolveCartsPath(basePath, cartId)),

	getCartById: cartId => AJAX.GET(resolveCartsPath(basePath, cartId)),

	getCartByIdWithItems: cartId =>
		AJAX.GET(
			resolveCartsPath(basePath, cartId) + '?nestedFields=cartItems'
		),

	getCartsByChannelId: channelId =>
		AJAX.GET(resolveChannelsPath(basePath, channelId)),

	replaceCartById: (cartId, json) =>
		AJAX.PUT(resolveCartsPath(basePath, cartId), json),

	updateCartById: (cartId, jsonProps) =>
		AJAX.PATCH(resolveCartsPath(basePath, cartId), jsonProps)
});
