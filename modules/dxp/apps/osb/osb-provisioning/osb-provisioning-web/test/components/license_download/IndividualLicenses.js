/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {cleanup, render, within} from '@testing-library/react';
import React from 'react';

import IndividualLicenses from '../../../src/main/resources/META-INF/resources/js/components/license_download/IndividualLicenses';
import {LicensesProvider} from '../../../src/main/resources/META-INF/resources/js/hooks/licenses';

const licenseKeys = [
	{
		active: true,
		description: 'Test Account description',
		expirationDate: 'April 16, 2122',
		hostName: 'Test Host Name 1',
		ipAddresses: '',
		licenseEntryName: 'Portal Backup',
		licenseEntryType: 'production',
		licenseKeyId: '85602',
		licenseVersion: 4,
		macAddresses: '',
		name: 'License 1',
		productId: 'Portal',
		productName: 'Portal Backup',
		productVersion: '6.2',
		sizing: 1,
		startDate: 'March 17, 2021'
	},
	// groupable with above license
	{
		active: true,
		description: 'Test Account description',
		expirationDate: 'April 16, 2122',
		hostName: 'Test Host Name 2',
		ipAddresses: '',
		licenseEntryName: 'Portal Backup',
		licenseEntryType: 'production',
		licenseKeyId: '85603',
		licenseVersion: 4,
		macAddresses: '',
		name: 'License 2',
		productId: 'Portal',
		productName: 'Portal Backup',
		productVersion: '6.2',
		sizing: 1,
		startDate: 'March 17, 2021'
	},
	// Inactive license
	{
		active: false,
		description: 'Test Account description',
		expirationDate: 'April 16, 2122',
		hostName: 'Test Host Name 3',
		ipAddresses: '',
		licenseEntryName: 'Portal Backup',
		licenseEntryType: 'production',
		licenseKeyId: '85604',
		licenseVersion: 4,
		macAddresses: '',
		name: 'License 3',
		productId: 'Portal',
		productName: 'Portal Backup',
		productVersion: '6.2',
		sizing: 1,
		startDate: 'March 17, 2021'
	},
	// Different expirationDate
	{
		active: true,
		description: 'Test Account description',
		expirationDate: 'April 17, 2122',
		hostName: 'Test Host Name 4',
		ipAddresses: '',
		licenseEntryName: 'Portal Backup',
		licenseEntryType: 'production',
		licenseKeyId: '85605',
		licenseVersion: 4,
		macAddresses: '',
		name: 'License 4',
		productId: 'Portal',
		productName: 'Portal Backup',
		productVersion: '6.2',
		sizing: 1,
		startDate: 'March 17, 2021'
	},
	// Different startDate
	{
		active: true,
		description: 'Test Account description',
		expirationDate: 'April 16, 2122',
		hostName: 'Test Host Name 5',
		ipAddresses: '',
		licenseEntryName: 'Portal Backup',
		licenseEntryType: 'production',
		licenseKeyId: '85606',
		licenseVersion: 4,
		macAddresses: '',
		name: 'License 5',
		productId: 'Portal',
		productName: 'Portal Backup',
		productVersion: '6.2',
		sizing: 1,
		startDate: 'March 16, 2021'
	},
	// Different license version & product version
	{
		active: true,
		description: 'Test Account description',
		expirationDate: 'April 16, 2122',
		hostName: 'Test Host Name 6',
		ipAddresses: '',
		licenseEntryName: 'DXP Development',
		licenseEntryType: 'production',
		licenseKeyId: '85607',
		licenseVersion: 6,
		macAddresses: '',
		name: 'License 6',
		productId: 'Portal',
		productName: 'DXP Development',
		productVersion: '7.4',
		sizing: 1,
		startDate: 'March 17, 2021'
	},
	// Different license version & product version
	{
		active: true,
		description: 'Test Account description',
		expirationDate: 'April 16, 2122',
		hostName: 'Test Host Name 7',
		ipAddresses: '',
		licenseEntryName: 'DXP Development',
		licenseEntryType: 'production',
		licenseKeyId: '85608',
		licenseVersion: 6,
		macAddresses: '',
		name: 'License 7',
		productId: 'Portal',
		productName: 'DXP Development',
		productVersion: '7.1',
		sizing: 1,
		startDate: 'March 17, 2021'
	},
	// licenseEntryType is not Production, same start date as License 5
	{
		active: true,
		description: 'Test Account description',
		expirationDate: 'April 16, 2122',
		hostName: 'Test Host Name 8',
		ipAddresses: '',
		licenseEntryName: 'Portal Backup',
		licenseEntryType: 'cluster',
		licenseKeyId: '85609',
		licenseVersion: 4,
		macAddresses: '',
		name: 'License 8',
		productId: 'Portal',
		productName: 'Portal Backup',
		productVersion: '6.2',
		sizing: 1,
		startDate: 'March 16, 2021'
	},
	// Old licenseVersion
	{
		active: true,
		description: 'Test Account description',
		expirationDate: 'April 16, 2122',
		hostName: 'Test Host Name 9',
		ipAddresses: '',
		licenseEntryName: 'Portal Backup',
		licenseEntryType: 'production',
		licenseKeyId: '85610',
		licenseVersion: 2,
		macAddresses: '',
		name: 'License 9',
		productId: 'Portal',
		productName: 'Portal Backup',
		productVersion: '6.0',
		sizing: 1,
		startDate: 'March 17, 2021'
	}
];

function renderIndividualLicenses(licenseKeys) {
	return render(
		<LicensesProvider initialLicenses={licenseKeys}>
			<table>
				<IndividualLicenses downloadURL="/download/license/key/url" />
			</table>
		</LicensesProvider>
	);
}

describe('IndividualLicenses', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderIndividualLicenses(licenseKeys);

		expect(container).toBeTruthy();
	});

	it('groups licenses that are active, has a license version that is at least 3, has a license type that is Production, and shares the same start date, expiration date, product version, and license version', () => {
		const {container, getAllByText} = renderIndividualLicenses(licenseKeys);

		const groups = container.querySelectorAll('tbody');

		let grouped;

		groups.forEach(group => {
			if (group.children.length > 2) {
				grouped = group;
			}
		});

		// only two licenses are groupable
		within(grouped).getByText('License 1');
		within(grouped).getByText('License 2');

		expect(getAllByText('download').length).toBe(8);
	});

	it('does not group licenses whose version is below 3', () => {
		const {getAllByText} = renderIndividualLicenses([
			{
				active: true,
				description: 'Test Account description',
				expirationDate: 'April 16, 2122',
				hostName: 'Test Host Name 1',
				ipAddresses: '',
				licenseEntryName: 'Portal Enterprise',
				licenseEntryType: 'enterprise',
				licenseKeyId: '85602',
				licenseVersion: 2,
				macAddresses: '',
				name: 'License 1',
				productId: 'Portal',
				productName: 'Portal Enterprise',
				productVersion: '6.0 SP1',
				sizing: 1,
				startDate: 'March 17, 2021'
			},
			{
				active: true,
				description: 'Test Account description',
				expirationDate: 'April 16, 2122',
				hostName: 'Test Host Name 2',
				ipAddresses: '',
				licenseEntryName: 'Portal Enterprise',
				licenseEntryType: 'enterprise',
				licenseKeyId: '85603',
				licenseVersion: 2,
				macAddresses: '',
				name: 'License 2',
				productId: 'Portal',
				productName: 'Portal Enterprise',
				productVersion: '6.0 SP1',
				sizing: 1,
				startDate: 'March 17, 2021'
			}
		]);

		expect(getAllByText('download').length).toBe(2);
	});

	it('does not group marketplace app licenses, even if license version is 3', () => {
		const {getAllByText} = renderIndividualLicenses([
			{
				active: true,
				description: 'Test Account description',
				expirationDate: 'April 16, 2122',
				hostName: 'Test Host Name 1',
				ipAddresses: '',
				licenseEntryName: 'Liferay Commerce Subscription Production',
				licenseEntryType: 'production',
				licenseKeyId: '85602',
				licenseVersion: 3,
				macAddresses: '',
				name: 'License 1',
				productId: 'commerce-id',
				productName: 'Commerce Subscription Production',
				productVersion: '1',
				sizing: 1,
				startDate: 'March 17, 2021'
			},
			{
				active: true,
				description: 'Test Account description',
				expirationDate: 'April 16, 2122',
				hostName: 'Test Host Name 2',
				ipAddresses: '',
				licenseEntryName: 'Liferay Commerce Subscription Production',
				licenseEntryType: 'production',
				licenseKeyId: '85603',
				licenseVersion: 2,
				macAddresses: '',
				name: 'License 2',
				productId: 'commerce-id',
				productName: 'Commerce Subscription Production',
				productVersion: '1',
				sizing: 1,
				startDate: 'March 17, 2021'
			}
		]);

		expect(getAllByText('download').length).toBe(2);
	});

	it('does not group licenses whose Type is not Production', () => {
		const {getAllByText} = renderIndividualLicenses([
			{
				active: true,
				description: 'Test Account description',
				expirationDate: 'April 16, 2122',
				hostName: 'Test Host Name 1',
				ipAddresses: '',
				licenseEntryName: 'Portal Backup',
				licenseEntryType: 'elastic',
				licenseKeyId: '85602',
				licenseVersion: 4,
				macAddresses: '',
				name: 'License 1',
				productId: 'Portal',
				productName: 'Portal Backup',
				productVersion: '6.2',
				sizing: 1,
				startDate: 'March 17, 2021'
			},
			{
				active: true,
				description: 'Test Account description',
				expirationDate: 'April 16, 2122',
				hostName: 'Test Host Name 2',
				ipAddresses: '',
				licenseEntryName: 'Portal Backup',
				licenseEntryType: 'elastic',
				licenseKeyId: '85603',
				licenseVersion: 4,
				macAddresses: '',
				name: 'License 2',
				productId: 'Portal',
				productName: 'Portal Backup',
				productVersion: '6.2',
				sizing: 1,
				startDate: 'March 17, 2021'
			}
		]);

		expect(getAllByText('download').length).toBe(2);
	});

	it('does not group licenses whose start date is not the same', () => {
		const {getAllByText} = renderIndividualLicenses([
			{
				active: true,
				description: 'Test Account description',
				expirationDate: 'April 16, 2122',
				hostName: 'Test Host Name 1',
				ipAddresses: '',
				licenseEntryName: 'Portal Backup',
				licenseEntryType: 'production',
				licenseKeyId: '85602',
				licenseVersion: 4,
				macAddresses: '',
				name: 'License 1',
				productId: 'Portal',
				productName: 'Portal Backup',
				productVersion: '6.2',
				sizing: 1,
				startDate: 'March 17, 2021'
			},
			{
				active: true,
				description: 'Test Account description',
				expirationDate: 'April 16, 2122',
				hostName: 'Test Host Name 2',
				ipAddresses: '',
				licenseEntryName: 'Portal Backup',
				licenseEntryType: 'production',
				licenseKeyId: '85603',
				licenseVersion: 4,
				macAddresses: '',
				name: 'License 2',
				productId: 'Portal',
				productName: 'Portal Backup',
				productVersion: '6.2',
				sizing: 1,
				startDate: 'March 13, 2021'
			}
		]);

		expect(getAllByText('download').length).toBe(2);
	});

	it('does not group licenses whose expiration date is not the same', () => {
		const {getAllByText} = renderIndividualLicenses([
			{
				active: true,
				description: 'Test Account description',
				expirationDate: 'April 14, 2122',
				hostName: 'Test Host Name 1',
				ipAddresses: '',
				licenseEntryName: 'Portal Backup',
				licenseEntryType: 'production',
				licenseKeyId: '85602',
				licenseVersion: 4,
				macAddresses: '',
				name: 'License 1',
				productId: 'Portal',
				productName: 'Portal Backup',
				productVersion: '6.2',
				sizing: 1,
				startDate: 'March 17, 2021'
			},
			{
				active: true,
				description: 'Test Account description',
				expirationDate: 'April 16, 2122',
				hostName: 'Test Host Name 2',
				ipAddresses: '',
				licenseEntryName: 'Portal Backup',
				licenseEntryType: 'production',
				licenseKeyId: '85603',
				licenseVersion: 4,
				macAddresses: '',
				name: 'License 2',
				productId: 'Portal',
				productName: 'Portal Backup',
				productVersion: '6.2',
				sizing: 1,
				startDate: 'March 17, 2021'
			}
		]);

		expect(getAllByText('download').length).toBe(2);
	});

	it('does not group licenses whose license version is not the same', () => {
		// In real life, licenses wouldn't have different license versions without having different product and product versions

		const {getAllByText} = renderIndividualLicenses([
			{
				active: true,
				description: 'Test Account description',
				expirationDate: 'April 16, 2122',
				hostName: 'Test Host Name 1',
				ipAddresses: '',
				licenseEntryName: 'Portal Backup',
				licenseEntryType: 'production',
				licenseKeyId: '85602',
				licenseVersion: 4,
				macAddresses: '',
				name: 'License 1',
				productId: 'Portal',
				productName: 'Portal Backup',
				productVersion: '6.2',
				sizing: 1,
				startDate: 'March 17, 2021'
			},
			{
				active: true,
				description: 'Test Account description',
				expirationDate: 'April 16, 2122',
				hostName: 'Test Host Name 2',
				ipAddresses: '',
				licenseEntryName: 'Portal Backup',
				licenseEntryType: 'production',
				licenseKeyId: '85603',
				licenseVersion: 5,
				macAddresses: '',
				name: 'License 2',
				productId: 'Portal',
				productName: 'Portal Backup',
				productVersion: '6.2',
				sizing: 1,
				startDate: 'March 17, 2021'
			}
		]);

		expect(getAllByText('download').length).toBe(2);
	});

	it('does not group licenses whose product version is not the same', () => {
		const {getAllByText} = renderIndividualLicenses([
			{
				active: true,
				description: 'Test Account description',
				expirationDate: 'April 16, 2122',
				hostName: 'Test Host Name 1',
				ipAddresses: '',
				licenseEntryName: 'DXP Development',
				licenseEntryType: 'production',
				licenseKeyId: '85602',
				licenseVersion: 6,
				macAddresses: '',
				name: 'License 1',
				productId: 'Portal',
				productName: 'DXP Development',
				productVersion: '7.1',
				sizing: 1,
				startDate: 'March 17, 2021'
			},
			{
				active: true,
				description: 'Test Account description',
				expirationDate: 'April 16, 2122',
				hostName: 'Test Host Name 2',
				ipAddresses: '',
				licenseEntryName: 'DXP Development',
				licenseEntryType: 'production',
				licenseKeyId: '85603',
				licenseVersion: 6,
				macAddresses: '',
				name: 'License 2',
				productId: 'Portal',
				productName: 'DXP Development',
				productVersion: '7.4',
				sizing: 1,
				startDate: 'March 17, 2021'
			}
		]);

		expect(getAllByText('download').length).toBe(2);
	});
});
