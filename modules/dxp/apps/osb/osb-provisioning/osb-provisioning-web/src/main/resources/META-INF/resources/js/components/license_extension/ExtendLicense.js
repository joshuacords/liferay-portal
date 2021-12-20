/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 */

import ClayTable from '@clayui/table';
import PropTypes from 'prop-types';
import React from 'react';

import {
	ExtendLicensesProvider,
	useExtendLicenses
} from '../../hooks/extendLicenses';
import {PermissionsProvider} from '../../hooks/permissions';
import BulkExtension from './BulkExtension';
import SingleExtension from './SingleExtension';

export default function ExtendLicense({
	details,
	extensionURL,
	hasUpdateLicenseDatePermission
}) {
	return (
		<React.StrictMode>
			<ExtendLicensesProvider initialLicenses={details}>
				<PermissionsProvider
					permissions={{
						updateDatePermission: hasUpdateLicenseDatePermission
					}}
				>
					<div className="extend-licenses-container">
						<ExtendLicensesTable extensionURL={extensionURL} />
					</div>
				</PermissionsProvider>
			</ExtendLicensesProvider>
		</React.StrictMode>
	);
}

ExtendLicense.propTypes = {
	details: PropTypes.arrayOf(
		PropTypes.shape({
			accountName: PropTypes.string,
			expirationDate: PropTypes.string,
			indefinite: PropTypes.bool.isRequired,
			licenseKeyId: PropTypes.string.isRequired,
			licenseKeysGenerated: PropTypes.string,
			licenseType: PropTypes.string.isRequired,
			productName: PropTypes.string.isRequired,
			startDate: PropTypes.string,
			terms: PropTypes.arrayOf(
				PropTypes.shape({
					endDate: PropTypes.string,
					licenseKeysGenerated: PropTypes.string,
					perpetual: PropTypes.bool,
					productPurchaseKey: PropTypes.string,
					startDate: PropTypes.string,
					status: PropTypes.string
				})
			)
		})
	),
	extensionURL: PropTypes.string.isRequired,
	hasUpdateLicenseDatePermission: PropTypes.bool.isRequired
};

function ExtendLicensesTable({extensionURL}) {
	const [licenses] = useExtendLicenses();

	return (
		<ClayTable>
			<ClayTable.Head>
				<ClayTable.Row>
					<ClayTable.Cell headingCell>
						{Liferay.Language.get('account-name')}
					</ClayTable.Cell>
					<ClayTable.Cell headingCell>
						{Liferay.Language.get('products')}
					</ClayTable.Cell>
					<ClayTable.Cell expanded headingCell>
						{Liferay.Language.get('subscription-term')}
					</ClayTable.Cell>
					<ClayTable.Cell expanded headingCell>
						{Liferay.Language.get('start-date')}
					</ClayTable.Cell>
					<ClayTable.Cell expanded headingCell>
						{Liferay.Language.get('expiration-date')}
					</ClayTable.Cell>
					<ClayTable.Cell headingCell>
						{Liferay.Language.get('licenses-generated')}
					</ClayTable.Cell>
					<ClayTable.Cell headingCell></ClayTable.Cell>
					<ClayTable.Cell headingCell></ClayTable.Cell>
				</ClayTable.Row>
			</ClayTable.Head>

			{licenses.size === 1 && (
				<SingleExtension
					extensionURL={extensionURL}
					licenses={licenses.toList().toJS()}
				/>
			)}

			{licenses.size > 1 && (
				<BulkExtension
					extensionURL={extensionURL}
					licenses={licenses.toList().toJS()}
				/>
			)}
		</ClayTable>
	);
}
