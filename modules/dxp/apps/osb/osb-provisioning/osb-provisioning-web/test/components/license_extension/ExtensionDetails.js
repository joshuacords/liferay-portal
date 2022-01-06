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

import {cleanup, render} from '@testing-library/react';
import React from 'react';

import ExtensionDetails from '../../../src/main/resources/META-INF/resources/js/components/license_extension/ExtensionDetails';
import {ExtendLicensesProvider} from '../../../src/main/resources/META-INF/resources/js/hooks/extendLicenses';
import {PermissionsProvider} from '../../../src/main/resources/META-INF/resources/js/hooks/permissions';
import {DASH} from '../../../src/main/resources/META-INF/resources/js/utilities/constants';

const multipleDetachedLicenses = [
	{
		accountName: 'Account 1',
		expirationDate: '2022-06-04',
		indefinite: false,
		licenseKeyId: 'licenseKeyID1',
		licenseKeysGenerated: '0',
		licenseType: 'production',
		productName: 'Commerce Subscription Backup',
		startDate: '2021-06-04'
	},
	{
		accountName: 'Account 1',
		expirationDate: '2027-12-14',
		indefinite: false,
		licenseKeyId: 'licenseKeyID2',
		licenseKeysGenerated: '0',
		licenseType: 'developer',
		productName: 'DXP Development',
		startDate: '2021-07-26'
	}
];

const singleDetachedLicense = [
	{
		accountName: 'Account 1',
		expirationDate: '2022-06-04',
		indefinite: false,
		licenseKeyId: 'licenseKeyID1',
		licenseKeysGenerated: '0',
		licenseType: 'production',
		productName: 'Commerce Subscription Backup',
		startDate: '2021-06-04'
	}
];

const singleAttachedLicense = [
	{
		accountName: 'Account 1',
		expirationDate: '2122-06-08',
		indefinite: false,
		licenseKeyId: 'licenseKeyID1',
		licenseKeysGenerated: '0',
		licenseType: 'development',
		productName: 'DXP 7.0',
		startDate: '2021-06-03',
		terms: [
			{
				endDate: '',
				licenseKeysGenerated: '2 / 1',
				perpetual: true,
				productPurchaseKey: 'productPurchaseKey1',
				startDate: '',
				status: 'Approved'
			},
			{
				endDate: '2022-07-02',
				licenseKeysGenerated: '1 / 1',
				perpetual: false,
				productPurchaseKey: 'productPurchaseKey2',
				startDate: '2021-06-02',
				status: 'Approved'
			}
		]
	}
];

function renderExtensionDetails(initialLicenses, permission = true) {
	return render(
		<table>
			<ExtendLicensesProvider initialLicenses={initialLicenses}>
				<PermissionsProvider
					permissions={{updateDatePermission: permission}}
				>
					<ExtensionDetails
						extensionURL="/extension/url"
						licenses={initialLicenses}
					/>
				</PermissionsProvider>
			</ExtendLicensesProvider>
		</table>
	);
}

describe('ExtensionDetails', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderExtensionDetails(singleDetachedLicense);

		expect(container).toBeTruthy();
	});

	it('renders the Terms of a Detached license as a dash', () => {
		const {getByText} = renderExtensionDetails(singleDetachedLicense);

		getByText(DASH);
	});

	it('renders the Terms in the dropdown of a Non Detached license', () => {
		const {getByText} = renderExtensionDetails(singleAttachedLicense);

		getByText('perpetual');
		getByText('June 2, 2021 - July 2, 2022');
	});

	it('renders a disabled X icon for a single license extension', () => {
		const {getByLabelText, getByTitle} = renderExtensionDetails(
			singleDetachedLicense
		);

		getByLabelText('delete-license-icon');
		expect(getByTitle('delete').disabled).toBeTruthy();
	});

	it('renders an enabled X icon if there are multiple license extensions', () => {
		const {getAllByTitle} = renderExtensionDetails(
			multipleDetachedLicenses
		);

		expect(getAllByTitle('delete').disabled).toBeFalsy();
	});
});
