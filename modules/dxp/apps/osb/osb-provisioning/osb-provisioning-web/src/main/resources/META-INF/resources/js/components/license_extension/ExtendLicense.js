/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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
			licenseKeysGenerated: PropTypes.number,
			licenseType: PropTypes.string.isRequired,
			productName: PropTypes.string.isRequired,
			startDate: PropTypes.string,
			terms: PropTypes.arrayOf(
				PropTypes.shape({
					endDate: PropTypes.string,
					licenseKeysAllowed: PropTypes.number,
					licenseKeysGenerated: PropTypes.number,
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
