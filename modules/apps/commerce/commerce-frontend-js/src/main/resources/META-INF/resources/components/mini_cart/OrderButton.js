/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayButton from '@clayui/button';
import React, {useContext} from 'react';

import {liferayNavigate} from '../../utilities/index';
import MiniCartContext from './MiniCartContext';
import {
	REVIEW_ORDER,
	SUBMIT_ORDER,
	WORKFLOW_STATUS_APPROVED
} from './util/constants';

function OrderButton() {
	const {actionURLs, cartState, labels} = useContext(MiniCartContext),
		{cartItems} = cartState,
		{length: numberOfItems = 0} = cartItems || {},
		{workflowStatusInfo} = cartState,
		{code: workflowStatus} = workflowStatusInfo || {},
		{checkoutURL} = actionURLs;

	return (
		<div className={'mini-cart-submit'}>
			<ClayButton
				block
				disabled={!numberOfItems}
				onClick={() => {
					liferayNavigate(checkoutURL);
				}}
			>
				{workflowStatus === WORKFLOW_STATUS_APPROVED
					? labels[SUBMIT_ORDER]
					: labels[REVIEW_ORDER]}
			</ClayButton>
		</div>
	);
}

export default OrderButton;
