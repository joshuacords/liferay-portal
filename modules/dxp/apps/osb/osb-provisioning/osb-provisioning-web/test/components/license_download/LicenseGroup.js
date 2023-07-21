/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {cleanup, render} from '@testing-library/react';
import React from 'react';

import LicenseGroup from '../../../src/main/resources/META-INF/resources/js/components/license_download/LicenseGroup';
import {LicensesProvider} from '../../../src/main/resources/META-INF/resources/js/hooks/licenses';
import {CURRENT_TIME} from '../../../src/main/resources/META-INF/resources/js/utilities/constants';
import {
	formatDate,
	generateNewDateByYear
} from '../../../src/main/resources/META-INF/resources/js/utilities/date';

const multipleLicenses = [
	{
		active: true,
		description: 'Test Account description',
		expirationDate: 'April 16, 2122',
		hostName: 'Test Host Name 1',
		ipAddresses: '',
		licenseEntryDisplayName: 'Portal Backup (Production)',
		licenseEntryName: 'Portal Backup',
		licenseEntryType: 'production',
		licenseKeyId: '85602',
		licenseVersion: 3,
		macAddresses: '',
		name: 'License 1',
		productId: 'Portal',
		productName: 'Portal Backup',
		productVersion: '6.1 GA1',
		startDate: 'March 17, 2021'
	},
	{
		active: true,
		description: 'Test Account description',
		expirationDate: 'April 16, 2122',
		hostName: 'Test Host Name 2',
		ipAddresses: '',
		licenseEntryDisplayName: 'Portal Backup (Production)',
		licenseEntryName: 'Portal Backup',
		licenseEntryType: 'production',
		licenseKeyId: '85603',
		licenseVersion: 3,
		macAddresses: '',
		name: 'License 2',
		productId: 'Portal',
		productName: 'Portal Backup',
		productVersion: '6.2',
		startDate: 'March 17, 2021'
	}
];

const singleLicense = [
	{
		active: true,
		description: 'Test Account description',
		expirationDate: 'April 16, 2122',
		hostName: 'Test Host Name 1',
		ipAddresses: '0.0.0.0',
		licenseEntryDisplayName: 'Portal Backup (Production)',
		licenseEntryName: 'Portal Backup',
		licenseEntryType: 'production',
		licenseKeyId: '85602',
		licenseVersion: 3,
		macAddresses: '01-02-03-04-ab-cd',
		name: 'License 1',
		productId: 'Portal',
		productName: 'Portal Backup',
		productVersion: '6.1 GA1',
		startDate: 'March 17, 2021'
	}
];

function renderLicenseGroup(props) {
	return render(
		<LicensesProvider initialLicenses={singleLicense}>
			<table>
				<LicenseGroup
					downloadURL="/download/license/key/url"
					items={[singleLicense]}
					{...props}
				/>
			</table>
		</LicensesProvider>
	);
}

describe('LicenseGroup', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderLicenseGroup();

		expect(container).toBeTruthy();
	});

	it('displays a download button', () => {
		const {getByText} = renderLicenseGroup();

		getByText('download');
	});

	it('only displays one Download button per group no matter how many licenses are listed', () => {
		const {getAllByText} = renderLicenseGroup({items: [multipleLicenses]});

		expect(getAllByText('download').length).toBe(1);
	});

	it('displays the Name and Description correctly', () => {
		const {getByText} = renderLicenseGroup();

		getByText('License 1');
		getByText('Test Account description');
	});

	it('displays the Product Name correctly', () => {
		const {getByText} = renderLicenseGroup();

		getByText('Portal Backup');
	});

	it('displays the Product Version correctly', () => {
		const {getByText} = renderLicenseGroup();

		getByText('6.1 GA1');
	});

	it('displays the Product Type correctly', () => {
		const {getByText} = renderLicenseGroup();

		getByText('Portal Backup (Production)');
	});

	it('displays the Start Date correctly', () => {
		const {getByText} = renderLicenseGroup();

		getByText('March 17, 2021');
	});

	it('displays the Expiration Date correctly', () => {
		const {getByText} = renderLicenseGroup();

		getByText('April 16, 2122');
	});

	it('displays the Host Name correctly', () => {
		const {getByText} = renderLicenseGroup();

		getByText('Test Host Name 1');
	});

	it('displays the IP Addresses correctly', () => {
		const {getByText} = renderLicenseGroup();

		getByText('0.0.0.0');
	});

	it('displays the MAC Addresses correctly', () => {
		const {getByText} = renderLicenseGroup();

		getByText('01-02-03-04-ab-cd');
	});

	it('displays the Deactivated Status label correctly', () => {
		const {getByText} = renderLicenseGroup({
			items: [
				[
					{
						active: false,
						description: 'Test Account description',
						expirationDate: 'April 16, 2122',
						hostName: 'Test Host Name',
						ipAddresses: '',
						licenseEntryName: 'Portal Backup',
						licenseEntryType: 'production',
						licenseKeyId: '85602',
						licenseVersion: 3,
						macAddresses: '',
						name: 'License 1',
						productId: 'Portal',
						productName: 'Portal Backup',
						productVersion: '6.1 GA1',
						startDate: 'March 17, 2021'
					}
				]
			]
		});

		getByText('deactivated');
	});

	it('displays the Active Status label correctly', () => {
		const newExpirationDate = generateNewDateByYear(CURRENT_TIME, 2);

		const {getByText} = renderLicenseGroup({
			items: [
				[
					{
						active: true,
						description: 'Test Account description',
						expirationDate: formatDate(newExpirationDate),
						hostName: 'Test Host Name',
						ipAddresses: '',
						licenseEntryName: 'Portal Backup',
						licenseEntryType: 'production',
						licenseKeyId: '85602',
						licenseVersion: 3,
						macAddresses: '',
						name: 'License 1',
						productId: 'Portal',
						productName: 'Portal Backup',
						productVersion: '6.1 GA1',
						startDate: 'March 17, 2020'
					}
				]
			]
		});

		getByText('active');
	});

	it('displays the Expired Status label correctly', () => {
		const {getByText} = renderLicenseGroup({
			items: [
				[
					{
						active: true,
						description: 'Test Account description',
						expirationDate: 'April 10, 2020',
						hostName: 'Test Host Name',
						ipAddresses: '',
						licenseEntryName: 'Portal Backup',
						licenseEntryType: 'production',
						licenseKeyId: '85602',
						licenseVersion: 3,
						macAddresses: '',
						name: 'License 1',
						productId: 'Portal',
						productName: 'Portal Backup',
						productVersion: '6.1 GA1',
						startDate: 'March 17, 2020'
					}
				]
			]
		});

		getByText('expired');
	});

	it('displays a delete button correctly', () => {
		const {getByLabelText} = renderLicenseGroup();

		getByLabelText('delete-license-icon');
	});

	it('displays a disabled delete button when there is only one license', () => {
		const {getByLabelText} = renderLicenseGroup();

		expect(
			getByLabelText('delete-license-icon').parentElement.disabled
		).toBeTruthy();
	});

	it('displays enabled delete buttons when there is more than one license', () => {
		const {getAllByLabelText} = render(
			<LicensesProvider initialLicenses={multipleLicenses}>
				<table>
					<LicenseGroup
						downloadURL="/download/license/key/url"
						items={[multipleLicenses]}
					/>
				</table>
			</LicensesProvider>
		);

		const allDeleteIconSVGs = getAllByLabelText('delete-license-icon');

		allDeleteIconSVGs.forEach(icon => {
			expect(icon.parentElement.disabled).toBeFalsy();
		});
	});
});
