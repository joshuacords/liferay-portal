/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React, {useContext} from 'react';

import DatasetDisplay from '../dataset_display/DatasetDisplay';
import MiniCartContext from './MiniCartContext';
import {ITEMS_LIST} from './util/constants';

function Wrapper() {
	const {CartViews, cartState, isOpen, spritemap} = useContext(
			MiniCartContext
		),
		{cartItems} = cartState;

	return (
		<div className={'mini-cart-wrapper'}>
			<CartViews.Header />

			<div className={'mini-cart-wrapper-items'}>
				{isOpen && (
					<DatasetDisplay
						id={'cart-items-list-dataset-display'}
						items={cartItems}
						overrideEmptyResultView={true}
						showManagementBar={false}
						showPagination={false}
						sidePanelId={'sidePanelDisabled'}
						spritemap={spritemap}
						views={[{component: CartViews[ITEMS_LIST]}]}
					/>
				)}
			</div>

			<CartViews.OrderButton />
		</div>
	);
}

export default Wrapper;
