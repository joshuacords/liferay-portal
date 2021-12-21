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

import {LicensesProvider} from '../../hooks/licenses';
import CombinedLicenses from './CombinedLicenses';
import IndividualLicenses from './IndividualLicenses';

const SIZING_WITH_NO_VALIDATION = '4';

function DownloadLicenses({downloadLicenseKeysURL, licenseKeys}) {
	const processedLicenseKeys = licenseKeys.map(license => {
		const result = /(?<size>[0-4])/.exec(license.sizing);

		return {
			...license,
			sizing: result ? result.groups.size : SIZING_WITH_NO_VALIDATION
		};
	});

	return (
		<LicensesProvider initialLicenses={processedLicenseKeys}>
			<div className="download-licenses-container">
				<React.StrictMode>
					<ClayTable>
						<ClayTable.Head>
							<ClayTable.Row>
								<ClayTable.Cell expanded headingCell>
									{Liferay.Language.get('name-description')}
								</ClayTable.Cell>
								<ClayTable.Cell expanded headingCell>
									{Liferay.Language.get('product')}
								</ClayTable.Cell>
								<ClayTable.Cell headingCell>
									{Liferay.Language.get('version')}
								</ClayTable.Cell>
								<ClayTable.Cell expanded headingCell>
									{Liferay.Language.get('type')}
								</ClayTable.Cell>
								<ClayTable.Cell expanded headingCell>
									{Liferay.Language.get('start-date')}
								</ClayTable.Cell>
								<ClayTable.Cell expanded headingCell>
									{Liferay.Language.get('expiration-date')}
								</ClayTable.Cell>
								<ClayTable.Cell expanded headingCell>
									{Liferay.Language.get('host-name')}
								</ClayTable.Cell>
								<ClayTable.Cell expanded headingCell>
									{Liferay.Language.get('ip-addresses')}
								</ClayTable.Cell>
								<ClayTable.Cell expanded headingCell>
									{Liferay.Language.get('mac-addresses')}
								</ClayTable.Cell>
								<ClayTable.Cell headingCell>
									{Liferay.Language.get('status')}
								</ClayTable.Cell>
								<ClayTable.Cell headingCell></ClayTable.Cell>
							</ClayTable.Row>
						</ClayTable.Head>

						<IndividualLicenses
							downloadURL={downloadLicenseKeysURL}
						/>

						<CombinedLicenses
							downloadURL={downloadLicenseKeysURL}
						/>
					</ClayTable>
				</React.StrictMode>
			</div>
		</LicensesProvider>
	);
}

DownloadLicenses.propTypes = {
	downloadLicenseKeysURL: PropTypes.string.isRequired,
	licenseKeys: PropTypes.arrayOf(
		PropTypes.shape({
			active: PropTypes.bool,
			description: PropTypes.string,
			expirationDate: PropTypes.string,
			hostName: PropTypes.string,
			ipAddresses: PropTypes.string,
			licenseEntryDisplayName: PropTypes.string,
			licenseEntryName: PropTypes.string,
			licenseEntryType: PropTypes.string,
			licenseKeyId: PropTypes.string,
			licenseVersion: PropTypes.number,
			macAddresses: PropTypes.string,
			name: PropTypes.string,
			productName: PropTypes.string,
			productVersion: PropTypes.string,
			sizing: PropTypes.string,
			startDate: PropTypes.string
		})
	).isRequired
};

export default DownloadLicenses;
