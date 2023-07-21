/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayIcon from '@clayui/icon';
import PropTypes from 'prop-types';
import React, {useContext} from 'react';

import Summary from '../summary/Summary';
import MiniCartContext from './MiniCartContext';
import {ADD_PRODUCT} from './util/constants';

function CartItemsList({items}) {
	const {
			CartViews,
			cartState,
			isUpdating,
			labels,
			spritemap,
			summaryDataMapper
		} = useContext(MiniCartContext),
		{summary = {}} = cartState,
		{itemsQuantity = 0} = summary,
		{length: numberOfItems = 0} = items || [];

	return (
		<div className={'mini-cart-items-list'}>
			<CartViews.ItemsListActions numberOfItems={numberOfItems} />

			{numberOfItems > 0 ? (
				<>
					<div className={'mini-cart-cart-items'}>
						{items.map(item => (
							<CartViews.Item item={item} key={item.id} />
						))}
					</div>

					{itemsQuantity > 0 && (
						<>
							<Summary
								dataMapper={summaryDataMapper}
								isLoading={isUpdating}
								summaryData={summary}
							/>
						</>
					)}
				</>
			) : (
				<div className="empty-cart">
					<div className="empty-cart-icon mb-3">
						<ClayIcon
							spritemap={spritemap}
							symbol={'shopping-cart'}
						/>
					</div>

					<p className="empty-cart-label">{labels[ADD_PRODUCT]}</p>
				</div>
			)}
		</div>
	);
}

CartItemsList.propTypes = {
	items: PropTypes.array
};

export default CartItemsList;
