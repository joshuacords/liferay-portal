/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {cleanup, render} from '@testing-library/react';
import React from 'react';

import CombinedLicenses from '../../../src/main/resources/META-INF/resources/js/components/license_download/CombinedLicenses';
import {LicensesProvider} from '../../../src/main/resources/META-INF/resources/js/hooks/licenses';

const multipleLicenseKeys = [
	{
		active: true,
		description: 'Test Account description',
		expirationDate: 'Jun 04, 2022',
		hostName: 'Test Host Name 1',
		ipAddresses: '',
		licenseEntryName: 'Commerce Subscription Backup',
		licenseEntryType: 'production',
		licenseKeyId: '85602',
		licenseVersion: 3,
		macAddresses: '',
		name: 'Commerce Subscription Backup',
		productId: 'commerce-id',
		productName: 'Commerce Subscription Backup',
		productVersion: '1',
		sizing: 1,
		startDate: 'Jun 04, 2021'
	},
	// Inactive license
	{
		active: false,
		description: 'Test Account description',
		expirationDate: 'Jun 04, 2022',
		hostName: 'Test Host Name 2',
		ipAddresses: '',
		licenseEntryName: 'Commerce Subscription Backup',
		licenseEntryType: 'production',
		licenseKeyId: '85603',
		licenseVersion: 3,
		macAddresses: '',
		name: 'Commerce Subscription Backup',
		productId: 'commerce-id',
		productName: 'Commerce Subscription Backup',
		productVersion: '1',
		sizing: 1,
		startDate: 'Jun 04, 2021'
	},
	{
		active: true,
		description: '365-Day License',
		expirationDate: 'Jun 04, 2022',
		hostName: 'Test Host Name 3',
		ipAddresses: '',
		licenseEntryName: 'DXP Backup',
		licenseEntryType: 'production',
		licenseKeyId: '85604',
		licenseVersion: 5,
		macAddresses: '',
		name: 'DXP Backup',
		productId: 'Portal',
		productName: 'DXP Backup',
		productVersion: '7.0',
		sizing: 1,
		startDate: 'Jun 04, 2021'
	},
	// Not DXP or Commerce product
	{
		active: true,
		description: 'Test Account description',
		expirationDate: 'April 16, 2122',
		hostName: 'Test Host Name 4',
		ipAddresses: '',
		licenseEntryName: 'Portal Backup',
		licenseEntryType: 'elastic',
		licenseKeyId: '85605',
		licenseVersion: 4,
		macAddresses: '',
		name: 'License 1',
		productId: 'Portal',
		productName: 'Portal Backup',
		productVersion: '6.2',
		sizing: 1,
		startDate: 'March 17, 2021'
	}
];

const singleLicenseKey = [
	{
		active: true,
		description: 'Test Account description',
		expirationDate: 'Jun 04, 2022',
		hostName: 'Test Host Name 1',
		ipAddresses: '',
		licenseEntryName: 'Commerce Subscription Backup',
		licenseEntryType: 'production',
		licenseKeyId: '85602',
		licenseVersion: 3,
		macAddresses: '',
		name: 'Commerce Subscription Backup',
		productId: 'commerce-id',
		productName: 'Commerce Subscription Backup',
		productVersion: '1',
		sizing: 1,
		startDate: 'Jun 04, 2021'
	}
];

function renderCombinedLicenses(licenseKeys) {
	return render(
		<LicensesProvider initialLicenses={licenseKeys}>
			<table>
				<CombinedLicenses downloadURL="/download/license/key/url" />
			</table>
		</LicensesProvider>
	);
}

describe('CombinedLicenses', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderCombinedLicenses(singleLicenseKey);

		expect(container).toBeTruthy();
	});

	it('displays no combined licenses if only one license was provided', () => {
		const {queryByText} = renderCombinedLicenses(singleLicenseKey);

		expect(queryByText('Test Host Name 1')).toBeFalsy();
		expect(queryByText('download')).toBeFalsy();
	});

	it('does not display an inactive license in the Combined Licenses section', () => {
		const {getByText, queryByText} = renderCombinedLicenses(
			multipleLicenseKeys
		);

		expect(queryByText('Invalid Commerce Subscription Backup')).toBeFalsy();
		getByText('download');
	});

	it('does not display a license whose product is neither Commerce nor DXP in the Combined Licenses section', () => {
		const {getByText, queryByText} = renderCombinedLicenses(
			multipleLicenseKeys
		);

		expect(queryByText('Portal Backup')).toBeFalsy();
		getByText('download');
	});

	describe('Invalid Grouping', () => {
		it('does not group if dates are different', () => {
			const {queryByText} = renderCombinedLicenses([
				{
					active: true,
					description: 'Test Account description',
					expirationDate: 'Jun 04, 2022',
					hostName: 'Test Host Name 1',
					ipAddresses: '',
					licenseEntryName: 'Commerce Subscription Backup',
					licenseEntryType: 'production',
					licenseKeyId: '85602',
					licenseVersion: 3,
					macAddresses: '',
					name: 'Commerce Subscription Backup',
					productId: 'commerce-id',
					productName: 'Commerce Subscription Backup',
					productVersion: '1',
					sizing: 1,
					startDate: 'Jun 04, 2021'
				},
				{
					active: true,
					description: '365-Day License',
					expirationDate: 'Jun 05, 2022',
					hostName: 'Test Host Name 2',
					ipAddresses: '',
					licenseEntryName: 'DXP Backup',
					licenseEntryType: 'production',
					licenseKeyId: '89402',
					licenseVersion: 5,
					macAddresses: '',
					name: 'DXP Backup',
					productId: 'Portal',
					productName: 'DXP Backup',
					productVersion: '7.0',
					sizing: 1,
					startDate: 'Jun 04, 2021'
				}
			]);

			expect(queryByText('Test Host Name 1')).toBeFalsy();
			expect(queryByText('Test Host Name 2')).toBeFalsy();
			expect(queryByText('download')).toBeFalsy();
		});

		it('does not group if Sizing are different', () => {
			const {queryByText} = renderCombinedLicenses([
				{
					active: true,
					description: 'Test Account description',
					expirationDate: 'Jun 04, 2022',
					hostName: 'Test Host Name 1',
					ipAddresses: '',
					licenseEntryName: 'Commerce Subscription Backup',
					licenseEntryType: 'production',
					licenseKeyId: '85602',
					licenseVersion: 3,
					macAddresses: '',
					name: 'Commerce Subscription Backup',
					productId: 'commerce-id',
					productName: 'Commerce Subscription Backup',
					productVersion: '1',
					sizing: 1,
					startDate: 'Jun 04, 2021'
				},
				{
					active: true,
					description: '365-Day License',
					expirationDate: 'Jun 04, 2022',
					hostName: 'Test Host Name 2',
					ipAddresses: '',
					licenseEntryName: 'DXP Backup',
					licenseEntryType: 'production',
					licenseKeyId: '89402',
					licenseVersion: 5,
					macAddresses: '',
					name: 'DXP Backup',
					productId: 'Portal',
					productName: 'DXP Backup',
					productVersion: '7.0',
					sizing: 2,
					startDate: 'Jun 04, 2021'
				}
			]);

			expect(queryByText('Test Host Name 1')).toBeFalsy();
			expect(queryByText('Test Host Name 2')).toBeFalsy();
			expect(queryByText('download')).toBeFalsy();
		});

		it('does not group if license types are different for the same product', () => {
			const {queryByText} = renderCombinedLicenses([
				{
					active: true,
					description: '365-Day License',
					expirationDate: 'Jun 04, 2022',
					hostName: 'Test Host Name 1',
					ipAddresses: '',
					licenseEntryName: 'DXP Backup',
					licenseEntryType: 'virtual-cluster',
					licenseKeyId: '89401',
					licenseVersion: 5,
					macAddresses: '',
					name: 'DXP Backup',
					productId: 'Portal',
					productName: 'DXP Backup',
					productVersion: '7.0',
					sizing: 1,
					startDate: 'Jun 04, 2021'
				},
				{
					active: true,
					description: '365-Day License',
					expirationDate: 'Jun 04, 2022',
					hostName: 'Test Host Name 2',
					ipAddresses: '',
					licenseEntryName: 'DXP Backup',
					licenseEntryType: 'production',
					licenseKeyId: '89402',
					licenseVersion: 5,
					macAddresses: '',
					name: 'DXP Backup',
					productId: 'Portal',
					productName: 'DXP Backup',
					productVersion: '7.0',
					sizing: 1,
					startDate: 'Jun 04, 2021'
				},
				{
					active: true,
					description: 'Test Account description',
					expirationDate: 'Jun 04, 2022',
					hostName: 'Test Host Name 3',
					ipAddresses: '',
					licenseEntryName: 'Commerce Subscription Backup',
					licenseEntryType: 'production',
					licenseKeyId: '85601',
					licenseVersion: 3,
					macAddresses: '',
					name: 'Commerce Subscription Backup',
					productId: 'commerce-id',
					productName: 'Commerce Subscription Backup',
					productVersion: '1',
					sizing: 1,
					startDate: 'Jun 04, 2021'
				},
				{
					active: true,
					description: 'Test Account description',
					expirationDate: 'Jun 04, 2022',
					hostName: 'Test Host Name 4',
					ipAddresses: '',
					licenseEntryName: 'Commerce Subscription Backup',
					licenseEntryType: 'production',
					licenseKeyId: '85602',
					licenseVersion: 3,
					macAddresses: '',
					name: 'Commerce Subscription Backup',
					productId: 'commerce-id',
					productName: 'Commerce Subscription Backup',
					productVersion: '1',
					sizing: 1,
					startDate: 'Jun 04, 2021'
				}
			]);

			expect(queryByText('Test Host Name 1')).toBeFalsy();
			expect(queryByText('Test Host Name 2')).toBeFalsy();
			expect(queryByText('Test Host Name 3')).toBeFalsy();
			expect(queryByText('Test Host Name 4')).toBeFalsy();
			expect(queryByText('download')).toBeFalsy();
		});

		it('does not group if product versions are different for DXP licenses', () => {
			const {queryByText} = renderCombinedLicenses([
				{
					active: true,
					description: '365-Day License',
					expirationDate: 'Jun 04, 2022',
					hostName: 'Test Host Name 1',
					ipAddresses: '',
					licenseEntryName: 'DXP Backup',
					licenseEntryType: 'production',
					licenseKeyId: '89401',
					licenseVersion: 5,
					macAddresses: '',
					name: 'DXP Backup',
					productId: 'Portal',
					productName: 'DXP Backup',
					productVersion: '7.0',
					sizing: 1,
					startDate: 'Jun 04, 2021'
				},
				{
					active: true,
					description: '365-Day License',
					expirationDate: 'Jun 04, 2022',
					hostName: 'Test Host Name 2',
					ipAddresses: '',
					licenseEntryName: 'DXP Backup',
					licenseEntryType: 'production',
					licenseKeyId: '89402',
					licenseVersion: 5,
					macAddresses: '',
					name: 'DXP Backup',
					productId: 'Portal',
					productName: 'DXP Backup',
					productVersion: '7.1',
					sizing: 1,
					startDate: 'Jun 04, 2021'
				},
				{
					active: true,
					description: 'Test Account description',
					expirationDate: 'Jun 04, 2022',
					hostName: 'Test Host Name 1',
					ipAddresses: '',
					licenseEntryName: 'Commerce Subscription Backup',
					licenseEntryType: 'production',
					licenseKeyId: '85601',
					licenseVersion: 3,
					macAddresses: '',
					name: 'Commerce Subscription Backup',
					productId: 'commerce-id',
					productName: 'Commerce Subscription Backup',
					productVersion: '1',
					sizing: 1,
					startDate: 'Jun 04, 2021'
				},
				{
					active: true,
					description: 'Test Account description',
					expirationDate: 'Jun 04, 2022',
					hostName: 'Test Host Name 1',
					ipAddresses: '',
					licenseEntryName: 'Commerce Subscription Backup',
					licenseEntryType: 'production',
					licenseKeyId: '85602',
					licenseVersion: 3,
					macAddresses: '',
					name: 'Commerce Subscription Backup',
					productId: 'commerce-id',
					productName: 'Commerce Subscription Backup',
					productVersion: '1',
					sizing: 1,
					startDate: 'Jun 04, 2021'
				}
			]);

			expect(queryByText('download')).toBeFalsy();
		});

		it('does not group if number of combinable Commerce and DXP licenses do not match', () => {
			const {queryByText} = renderCombinedLicenses([
				{
					active: true,
					description: 'Test Account description',
					expirationDate: 'Jun 04, 2022',
					hostName: 'Test Host Name 1',
					ipAddresses: '',
					licenseEntryName: 'Commerce Subscription Backup',
					licenseEntryType: 'production',
					licenseKeyId: '85602',
					licenseVersion: 3,
					macAddresses: '',
					name: 'Commerce Subscription Backup',
					productId: 'commerce-id',
					productName: 'Commerce Subscription Backup',
					productVersion: '1',
					sizing: 1,
					startDate: 'Jun 04, 2021'
				},
				{
					active: true,
					description: 'Test Account description',
					expirationDate: 'Jun 04, 2022',
					hostName: 'Test Host Name 2',
					ipAddresses: '',
					licenseEntryName: 'Commerce Subscription Backup',
					licenseEntryType: 'production',
					licenseKeyId: '85603',
					licenseVersion: 3,
					macAddresses: '',
					name: 'Commerce Subscription Backup',
					productId: 'commerce-id',
					productName: 'Commerce Subscription Backup',
					productVersion: '1',
					sizing: 1,
					startDate: 'Jun 04, 2021'
				},
				{
					active: true,
					description: '365-Day License',
					expirationDate: 'Jun 04, 2022',
					hostName: 'Test Host Name 3',
					ipAddresses: '',
					licenseEntryName: 'DXP Backup',
					licenseEntryType: 'production',
					licenseKeyId: '85604',
					licenseVersion: 5,
					macAddresses: '',
					name: 'DXP Backup',
					productId: 'Portal',
					productName: 'DXP Backup',
					productVersion: '7.0',
					sizing: 1,
					startDate: 'Jun 04, 2021'
				}
			]);

			expect(queryByText('download')).toBeFalsy();
		});
	});

	describe('Valid Grouping', () => {
		it('displays the combined DXP Commerce licenses if the licenses are Active, share the same Start Date and Expiration Date, and are equal in quantity (same number of Commerce licenses as DXP licenses)', () => {
			const {getByText} = renderCombinedLicenses([
				{
					active: true,
					description: 'Test Account description',
					expirationDate: 'Jun 04, 2022',
					hostName: 'Test Host Name 1',
					ipAddresses: '',
					licenseEntryName: 'Commerce Subscription Backup',
					licenseEntryType: 'production',
					licenseKeyId: '85602',
					licenseVersion: 3,
					macAddresses: '',
					name: 'Commerce Subscription Backup',
					productId: 'commerce-id',
					productName: 'Commerce Subscription Backup',
					productVersion: '1',
					sizing: 1,
					startDate: 'Jun 04, 2021'
				},
				{
					active: true,
					description: '365-Day License',
					expirationDate: 'Jun 04, 2022',
					hostName: 'Test Host Name 2',
					ipAddresses: '',
					licenseEntryName: 'DXP Backup',
					licenseEntryType: 'production',
					licenseKeyId: '89402',
					licenseVersion: 5,
					macAddresses: '',
					name: 'DXP Backup',
					productId: 'Portal',
					productName: 'DXP Backup',
					productVersion: '7.0',
					sizing: 1,
					startDate: 'Jun 04, 2021'
				}
			]);

			getByText('Test Host Name 1');
			getByText('Test Host Name 2');
			getByText('download');
		});

		it('displays the combined DXP Commerce licenses if the licenses are Active, share the same Start Date and Expiration Date, are equal in quantity (same number of Commerce licenses as DXP licenses), contain the same license types for Commerce and for DXP, and finally, contain the same product version for DXP licenses (so far, product version is always one for Commerce)', () => {
			const {getByText} = renderCombinedLicenses([
				{
					active: true,
					description: 'Test Account description',
					expirationDate: 'Jun 04, 2022',
					hostName: 'Test Host Name 1',
					ipAddresses: '',
					licenseEntryName: 'Commerce Subscription Backup',
					licenseEntryType: 'production',
					licenseKeyId: '85602',
					licenseVersion: 3,
					macAddresses: '',
					name: 'Commerce Subscription Backup',
					productId: 'commerce-id',
					productName: 'Commerce Subscription Backup',
					productVersion: '1',
					sizing: 1,
					startDate: 'Jun 04, 2021'
				},
				{
					active: true,
					description: 'Test Account description',
					expirationDate: 'Jun 04, 2022',
					hostName: 'Test Host Name 2',
					ipAddresses: '',
					licenseEntryName: 'Commerce Subscription Backup',
					licenseEntryType: 'production',
					licenseKeyId: '85603',
					licenseVersion: 3,
					macAddresses: '',
					name: 'Commerce Subscription Backup',
					productId: 'commerce-id',
					productName: 'Commerce Subscription Backup',
					productVersion: '1',
					sizing: 1,
					startDate: 'Jun 04, 2021'
				},
				{
					active: true,
					description: '365-Day License',
					expirationDate: 'Jun 04, 2022',
					hostName: 'Test Host Name 3',
					ipAddresses: '',
					licenseEntryName: 'DXP Backup',
					licenseEntryType: 'production',
					licenseKeyId: '89404',
					licenseVersion: 5,
					macAddresses: '',
					name: 'DXP Backup',
					productId: 'Portal',
					productName: 'DXP Backup',
					productVersion: '7.0',
					sizing: 1,
					startDate: 'Jun 04, 2021'
				},
				{
					active: true,
					description: '365-Day License',
					expirationDate: 'Jun 04, 2022',
					hostName: 'Test Host Name 4',
					ipAddresses: '',
					licenseEntryName: 'DXP Backup',
					licenseEntryType: 'production',
					licenseKeyId: '89405',
					licenseVersion: 5,
					macAddresses: '',
					name: 'DXP Backup',
					productId: 'Portal',
					productName: 'DXP Backup',
					productVersion: '7.0',
					sizing: 1,
					startDate: 'Jun 04, 2021'
				}
			]);

			getByText('Test Host Name 1');
			getByText('Test Host Name 2');
			getByText('Test Host Name 3');
			getByText('Test Host Name 4');
			getByText('download');
		});

		it('displays multiple sets of combined licenses correctly', () => {
			const {getAllByText} = renderCombinedLicenses([
				{
					active: true,
					description: 'Test Account description',
					expirationDate: 'Jun 04, 2022',
					hostName: 'Test Host Name 1',
					ipAddresses: '',
					licenseEntryName: 'Commerce Subscription Backup',
					licenseEntryType: 'production',
					licenseKeyId: '85602',
					licenseVersion: 3,
					macAddresses: '',
					name: 'Commerce Subscription Backup',
					productId: 'commerce-id',
					productName: 'Commerce Subscription Backup',
					productVersion: '1',
					sizing: 1,
					startDate: 'Jun 03, 2021'
				},
				{
					active: true,
					description: 'Test Account description',
					expirationDate: 'Jun 04, 2022',
					hostName: 'Test Host Name 2',
					ipAddresses: '',
					licenseEntryName: 'Commerce Subscription Backup',
					licenseEntryType: 'production',
					licenseKeyId: '85603',
					licenseVersion: 3,
					macAddresses: '',
					name: 'Commerce Subscription Backup',
					productId: 'commerce-id',
					productName: 'Commerce Subscription Backup',
					productVersion: '1',
					sizing: 1,
					startDate: 'Jun 04, 2021'
				},
				{
					active: true,
					description: '365-Day License',
					expirationDate: 'Jun 04, 2022',
					hostName: 'Test Host Name 3',
					ipAddresses: '',
					licenseEntryName: 'DXP Backup',
					licenseEntryType: 'production',
					licenseKeyId: '89404',
					licenseVersion: 5,
					macAddresses: '',
					name: 'DXP Backup',
					productId: 'Portal',
					productName: 'DXP Backup',
					productVersion: '7.0',
					sizing: 1,
					startDate: 'Jun 03, 2021'
				},
				{
					active: true,
					description: '365-Day License',
					expirationDate: 'Jun 04, 2022',
					hostName: 'Test Host Name 4',
					ipAddresses: '',
					licenseEntryName: 'DXP Backup',
					licenseEntryType: 'production',
					licenseKeyId: '89405',
					licenseVersion: 5,
					macAddresses: '',
					name: 'DXP Backup',
					productId: 'Portal',
					productName: 'DXP Backup',
					productVersion: '7.1',
					sizing: 1,
					startDate: 'Jun 04, 2021'
				}
			]);

			expect(getAllByText('download').length).toBe(2);
		});
	});
});
