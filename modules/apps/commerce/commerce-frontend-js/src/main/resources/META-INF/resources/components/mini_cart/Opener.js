/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayIcon from '@clayui/icon';
import classnames from 'classnames';
import PropTypes from 'prop-types';
import React, {useContext} from 'react';

import MiniCartContext from './MiniCartContext';

function Opener({openCart}) {
	const {cartState, displayTotalItemsQuantity, spritemap} = useContext(
		MiniCartContext
	);

	let numberOfItems = 0;

	if (displayTotalItemsQuantity) {
		const {summary = {}} = cartState,
			{itemsQuantity = 0} = summary;

		numberOfItems = itemsQuantity;
	}
	else {
		const {cartItems = []} = cartState;

		numberOfItems = cartItems.length;
	}

	return (
		<button
			className={classnames(
				'mini-cart-opener',
				!!numberOfItems && 'has-badge'
			)}
			data-badge-count={numberOfItems}
			onClick={openCart}
		>
			<ClayIcon spritemap={spritemap} symbol={'shopping-cart'} />
		</button>
	);
}

Opener.propTypes = {
	openCart: PropTypes.func
};

export default Opener;
