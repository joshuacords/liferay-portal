/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayButton from '@clayui/button';
import classnames from 'classnames';
import PropTypes from 'prop-types';
import React, {useContext, useState} from 'react';

import {PRODUCT_REMOVED} from '../../utilities/eventsDefinitions';
import {liferayNavigate} from '../../utilities/index';
import MiniCartContext from './MiniCartContext';
import {REMOVE_ALL_ITEMS, VIEW_DETAILS} from './util/constants';

function CartItemsListActions({numberOfItems}) {
	const {
			AJAX,
			actionURLs,
			cartState,
			labels,
			setIsUpdating,
			updateCartModel
		} = useContext(MiniCartContext),
		{id: orderId} = cartState,
		{orderDetailURL} = actionURLs;

	const [isAsking, setIsAsking] = useState(false);

	const askConfirmation = () => setIsAsking(true),
		cancel = () => {
			setIsAsking(false);
		},
		flushCart = () => {
			setIsUpdating(true);

			AJAX.updateCartById(orderId, {...cartState, cartItems: []})
				.then(() => updateCartModel({orderId}))
				.then(() => {
					setIsAsking(false);
					setIsUpdating(false);

					Liferay.fire(PRODUCT_REMOVED, {
						skuId: 'all'
					});
				});
		};

	return (
		<div className={'mini-cart-header'}>
			<div className={'mini-cart-header-block'}>
				<div className={'mini-cart-header-resume'}>
					{numberOfItems > 0 && (
						<>
							<span className={'items'}>{numberOfItems}</span>
							{` ${
								numberOfItems > 1
									? Liferay.Language.get('products')
									: Liferay.Language.get('product')
							}`}
						</>
					)}
				</div>

				<div className={'mini-cart-header-actions'}>
					<span className={classnames('actions', isAsking && 'hide')}>
						<ClayButton
							className={'action'}
							disabled={!numberOfItems}
							displayType={'link'}
							onClick={() => {
								liferayNavigate(orderDetailURL);
							}}
							small
						>
							{labels[VIEW_DETAILS]}
						</ClayButton>

						<ClayButton
							className={'action'}
							disabled={!numberOfItems}
							displayType={'link'}
							onClick={askConfirmation}
							small
						>
							{labels[REMOVE_ALL_ITEMS]}
						</ClayButton>
					</span>

					<div
						className={classnames(
							'confirmation-prompt',
							!isAsking && 'hide'
						)}
					>
						<span>{Liferay.Language.get('are-you-sure')}</span>

						<span>
							<button
								className={'btn btn-outline-success btn-sm'}
								onClick={flushCart}
								type={'button'}
							>
								{Liferay.Language.get('yes')}
							</button>
							<button
								className={'btn btn-outline-danger btn-sm'}
								onClick={cancel}
								type={'button'}
							>
								{Liferay.Language.get('no')}
							</button>
						</span>
					</div>
				</div>
			</div>
		</div>
	);
}

CartItemsListActions.propTypes = {
	numberOfItems: PropTypes.number
};

export default CartItemsListActions;
