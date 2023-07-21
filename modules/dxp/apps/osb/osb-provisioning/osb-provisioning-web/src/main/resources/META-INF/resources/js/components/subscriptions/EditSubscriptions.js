/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import PropTypes from 'prop-types';
import React from 'react';

import {SubscriptionsProvider} from '../../hooks/subscriptions';
import {AddView, EditView} from './Views';

function EditSubscriptions({
	accountName,
	addSubscriptions,
	backURL,
	details,
	editProductPurchasesURL,
	redirect,
	selectProductsActionURL,
	selectProductsRenderURL,
	sizing,
	status
}) {
	return (
		<SubscriptionsProvider initialSubscriptions={details}>
			{addSubscriptions && (
				<AddView
					accountName={accountName}
					editProductPurchasesURL={editProductPurchasesURL}
					redirect={redirect}
					selectProductsActionURL={selectProductsActionURL}
					selectProductsRenderURL={selectProductsRenderURL}
					sizing={sizing}
				/>
			)}

			{!addSubscriptions && (
				<EditView
					accountName={accountName}
					backURL={backURL}
					editProductPurchasesURL={editProductPurchasesURL}
					redirect={redirect}
					sizing={sizing}
					status={status}
				/>
			)}
		</SubscriptionsProvider>
	);
}

EditSubscriptions.propTypes = {
	accountName: PropTypes.string.isRequired,
	addSubscriptions: PropTypes.bool.isRequired,
	backURL: PropTypes.string,
	details: PropTypes.arrayOf(
		PropTypes.shape({
			endDate: PropTypes.string,
			externalLinkKey: PropTypes.string,
			key: PropTypes.string,
			originalEndDate: PropTypes.string,
			perpetual: PropTypes.bool,
			productKey: PropTypes.string,
			productName: PropTypes.string,
			quantity: PropTypes.number,
			salesforceOpportunityKey: PropTypes.string,
			sizing: PropTypes.number,
			startDate: PropTypes.string,
			status: PropTypes.string
		})
	),
	editProductPurchasesURL: PropTypes.string.isRequired,
	redirect: PropTypes.string.isRequired,
	selectProductsActionURL: PropTypes.string.isRequired,
	selectProductsRenderURL: PropTypes.string,
	sizing: PropTypes.arrayOf(PropTypes.number),
	status: PropTypes.arrayOf(PropTypes.string)
};

export default EditSubscriptions;
