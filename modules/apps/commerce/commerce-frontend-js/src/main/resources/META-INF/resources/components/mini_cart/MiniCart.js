/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import classnames from 'classnames';
import PropTypes from 'prop-types';
import React, {useCallback, useEffect, useState} from 'react';

import ServiceProvider from '../../ServiceProvider/index';
import {
	ADD_TO_ORDER,
	CHANGE_ACCOUNT,
	CHANGE_ORDER
} from '../../utilities/eventsDefinitions';
import {showErrorNotification} from '../../utilities/notifications';
import MiniCartContext from './MiniCartContext';
import {
	ADD_PRODUCT,
	CART,
	HEADER,
	ITEM,
	ITEMS_LIST,
	ITEMS_LIST_ACTIONS,
	OPENER,
	ORDER_BUTTON,
	ORDER_IS_EMPTY,
	REMOVE_ALL_ITEMS,
	REVIEW_ORDER,
	SUBMIT_ORDER,
	VIEW_DETAILS,
	YOUR_ORDER
} from './util/constants';
import {
	normalizePartialObject,
	regenerateOrderDetailURL,
	summaryDataMapper
} from './util/index';
import {DEFAULT_LABELS} from './util/labels';
import {DEFAULT_VIEWS, resolveCartViews} from './util/views';

function MiniCart({
	cartActionURLs,
	cartViews,
	displayDiscountLevels,
	displayTotalItemsQuantity,
	labels,
	onAddToCart,
	orderId,
	spritemap,
	summaryDataMapper,
	toggleable
}) {
	const AJAX = ServiceProvider.DeliveryCartAPI('v1');

	const [isOpen, setIsOpen] = useState(!toggleable || false),
		[isUpdating, setIsUpdating] = useState(false),
		[cartState, updateCartState] = useState({}),
		[actionURLs, setActionURLs] = useState(cartActionURLs),
		[CartViews, setCartViews] = useState({});

	const closeCart = () => setIsOpen(false),
		openCart = () => setIsOpen(true),
		resetCartState = useCallback(() => updateCartState({}), [
			updateCartState
		]);

	// eslint-disable-next-line react-hooks/exhaustive-deps
	const updateCartModel = ({orderId: cartId}) =>
		AJAX.getCartByIdWithItems(cartId)
			.then(model => {
				if (model.id !== cartId) {
					const {orderUUID} = model,
						{checkoutURL, orderDetailURL} = actionURLs;

					setActionURLs({
						checkoutURL,
						orderDetailURL: regenerateOrderDetailURL(
							orderDetailURL,
							orderUUID
						)
					});
				}

				updateCartState({...cartState, ...model});
				onAddToCart(actionURLs, cartState);
			})
			.catch(showErrorNotification);

	useEffect(() => {
		resolveCartViews(
			normalizePartialObject(DEFAULT_VIEWS, cartViews)
		).then(views => setCartViews(views));
	}, [cartViews]);

	useEffect(() => {
		Liferay.on(ADD_TO_ORDER, updateCartModel);
		Liferay.on(CHANGE_ORDER, updateCartModel);

		return () => {
			Liferay.detach(ADD_TO_ORDER, updateCartModel);
			Liferay.detach(CHANGE_ORDER, updateCartModel);
		};
	}, [updateCartModel]);

	useEffect(() => {
		if (orderId && orderId !== 0) {
			updateCartModel({orderId});
		}
		// eslint-disable-next-line react-hooks/exhaustive-deps
	}, [orderId]);

	useEffect(() => {
		Liferay.on(CHANGE_ACCOUNT, resetCartState);

		return () => {
			Liferay.detach(CHANGE_ACCOUNT, resetCartState);
		};
	}, [resetCartState]);

	return (
		<MiniCartContext.Provider
			value={{
				AJAX,
				CartViews,
				actionURLs,
				cartState,
				closeCart,
				displayDiscountLevels,
				displayTotalItemsQuantity,
				isOpen,
				isUpdating,
				labels: normalizePartialObject(DEFAULT_LABELS, labels),
				setIsUpdating,
				spritemap,
				summaryDataMapper,
				toggleable,
				updateCartModel,
				updateCartState
			}}
		>
			{!!CartViews[CART] && (
				<div
					className={classnames(
						'mini-cart',
						(!toggleable || isOpen) && 'is-open'
					)}
				>
					{toggleable && (
						<>
							<div
								className={'mini-cart-overlay'}
								onClick={() => setIsOpen(false)}
							/>

							<CartViews.Opener openCart={openCart} />
						</>
					)}

					<CartViews.Cart />
				</div>
			)}
		</MiniCartContext.Provider>
	);
}

MiniCart.defaultProps = {
	cartViews: DEFAULT_VIEWS,
	displayDiscountLevels: false,
	displayTotalItemsQuantity: false,
	labels: DEFAULT_LABELS,
	onAddToCart: () => {},
	summaryDataMapper,
	toggleable: true
};

MiniCart.propTypes = {
	cartActionURLs: PropTypes.shape({
		checkoutURL: PropTypes.string,
		orderDetailURL: PropTypes.string
	}).isRequired,
	cartViews: PropTypes.shape({
		[CART]: PropTypes.oneOfType([
			PropTypes.shape({
				component: PropTypes.func
			}),
			PropTypes.shape({
				contentRendererModuleUrl: PropTypes.string
			})
		]),
		[HEADER]: PropTypes.oneOfType([
			PropTypes.shape({
				component: PropTypes.func
			}),
			PropTypes.shape({
				contentRendererModuleUrl: PropTypes.string
			})
		]),
		[ITEM]: PropTypes.oneOfType([
			PropTypes.shape({
				component: PropTypes.func
			}),
			PropTypes.shape({
				contentRendererModuleUrl: PropTypes.string
			})
		]),
		[ITEMS_LIST]: PropTypes.oneOfType([
			PropTypes.shape({
				component: PropTypes.func
			}),
			PropTypes.shape({
				contentRendererModuleUrl: PropTypes.string
			})
		]),
		[ITEMS_LIST_ACTIONS]: PropTypes.oneOfType([
			PropTypes.shape({
				component: PropTypes.func
			}),
			PropTypes.shape({
				contentRendererModuleUrl: PropTypes.string
			})
		]),
		[OPENER]: PropTypes.oneOfType([
			PropTypes.shape({
				component: PropTypes.func
			}),
			PropTypes.shape({
				contentRendererModuleUrl: PropTypes.string
			})
		]),
		[ORDER_BUTTON]: PropTypes.oneOfType([
			PropTypes.shape({
				component: PropTypes.func
			}),
			PropTypes.shape({
				contentRendererModuleUrl: PropTypes.string
			})
		])
	}),
	displayDiscountLevels: PropTypes.bool,
	displayTotalItemsQuantity: PropTypes.bool,
	labels: PropTypes.shape({
		[ADD_PRODUCT]: PropTypes.string,
		[ORDER_IS_EMPTY]: PropTypes.string,
		[REMOVE_ALL_ITEMS]: PropTypes.string,
		[REVIEW_ORDER]: PropTypes.string,
		[SUBMIT_ORDER]: PropTypes.string,
		[VIEW_DETAILS]: PropTypes.string,
		[YOUR_ORDER]: PropTypes.string
	}),
	onAddToCart: PropTypes.func,
	orderId: PropTypes.number,
	spritemap: PropTypes.string,
	summaryDataMapper: PropTypes.func,
	toggleable: PropTypes.bool
};

export default MiniCart;
