/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {cleanup, fireEvent, render, wait} from '@testing-library/react';
import React from 'react';

import SpecificDetails from '../../../src/main/resources/META-INF/resources/js/components/license_generation/SpecificDetails';
import {
	License,
	NewLicenseProvider
} from '../../../src/main/resources/META-INF/resources/js/hooks/newLicense';
import {CURRENT_TIME} from '../../../src/main/resources/META-INF/resources/js/utilities/constants';
import {
	displayInMDYDateFormat,
	getUTCAdjustedDate
} from '../../../src/main/resources/META-INF/resources/js/utilities/date';

const dummyLicense = new License({
	accountKey: 'KEY-ABC',
	accountName: 'Test Account',
	complimentary: true,
	expirationDate: CURRENT_TIME,
	licenseEntry: {
		licenseEntryId: 'ID-123',
		licenseEntryName: 'Test License Entry Name',
		licenseEntryType: 'developer'
	},
	licenseKeysAllowed: 1,
	licenseKeysGenerated: 0,
	name: 'Test Account',
	product: {productKey: 'PRODUCT-123', productName: 'Test Product'},
	productPurchaseKey: 'PPKEY-123',
	showSpecificDetails: true,
	sizing: '1',
	startDate: CURRENT_TIME,
	version: '1.0'
});

function renderSpecificDetails(props) {
	return render(
		<NewLicenseProvider initialLicense={dummyLicense}>
			<SpecificDetails
				addLicenseKeyURL="add/license/key/url"
				redirect="/redirect/url"
				{...props}
			/>
		</NewLicenseProvider>
	);
}

function renderServerIdFields(props) {
	return render(
		<NewLicenseProvider
			initialLicense={
				new License({
					accountName: 'Test Account',
					licenseEntry: {
						licenseEntryType: 'cluster'
					},
					name: 'Test Account',
					startDate: CURRENT_TIME,
					...props
				})
			}
		>
			<SpecificDetails
				addLicenseKeyURL="add/license/key/url"
				redirect={'/redirect/url'}
			/>
		</NewLicenseProvider>
	);
}

describe('SpecificDetails', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderSpecificDetails();

		expect(container).toBeTruthy();
	});

	it('displays a Previous Step button', () => {
		const {getByText} = renderSpecificDetails();

		getByText('previous-step');
	});

	it('displays a Cancel button', () => {
		const {getByText} = renderSpecificDetails();

		getByText('cancel');
	});

	it('displays the Account Name correctly', () => {
		const {getByDisplayValue} = renderSpecificDetails();

		getByDisplayValue('Test Account');
	});

	it('displays the Product Name correctly', () => {
		const {getByText} = renderSpecificDetails();

		getByText('Test Product');
	});

	it('displays the Version field correctly', () => {
		const {getByText} = renderSpecificDetails();

		getByText('1.0');
	});

	it('displays the Type field correctly', () => {
		const {getByText} = renderSpecificDetails();

		getByText('Developer');
	});

	it('displays the Start and Expiration date fields correctly', () => {
		const {getAllByText} = renderSpecificDetails();

		const utcAdjustedDate = displayInMDYDateFormat(
			getUTCAdjustedDate(CURRENT_TIME)
		);

		expect(getAllByText(utcAdjustedDate).length).toBe(2);
	});

	it('displays the Licenses Generated correctly', () => {
		const {getByText} = renderSpecificDetails();

		getByText('licenses-generated');
	});

	it('displays the Complimentary checkbox correctly', () => {
		const {getByLabelText} = renderSpecificDetails();

		expect(getByLabelText('complimentary').checked).toBeTruthy();
	});

	it('displays the Server Id Fields section if the selected Type is one of Backup, Cluster, Limited, Non-production, Per-user, or Production', () => {
		const {getByText} = render(
			<NewLicenseProvider
				initialLicense={
					new License({
						licenseEntry: {
							licenseEntryType: 'cluster'
						}
					})
				}
			>
				<SpecificDetails
					addLicenseKeyURL="add/license/key/url"
					redirect={'/redirect/url'}
				/>
			</NewLicenseProvider>
		);

		getByText('server-id-fields');
	});

	it('does not display the Server Id Fields section if the selected Type is one of Developer, Developer Cluster, Elastic, Enterprise, OEM, or Virtual Cluster (for DXP 7.4)', () => {
		const {queryByText} = renderSpecificDetails();

		expect(queryByText('server-id-fields')).toBeFalsy();
	});

	it('displays the Max Cluster Nodes input if the selected Type is Virtual Cluster', () => {
		const {getByLabelText, queryByText} = render(
			<NewLicenseProvider
				initialLicense={
					new License({
						licenseEntry: {
							licenseEntryType: 'virtual_cluster'
						}
					})
				}
			>
				<SpecificDetails
					addLicenseKeyURL="add/license/key/url"
					redirect={'/redirect/url'}
				/>
			</NewLicenseProvider>
		);

		getByLabelText('maximum-cluster-nodes');
		expect(queryByText('server-id-fields')).toBeFalsy();
	});

	it('does not display the Maximum Servers input if the selected Type is not Cluster', () => {
		const {queryByLabelText} = renderSpecificDetails();

		expect(queryByLabelText('maximum-servers')).toBeFalsy();
	});

	it('displays the Maximum Servers input if the selected Type is Cluster', () => {
		const {getByLabelText} = render(
			<NewLicenseProvider
				initialLicense={
					new License({
						licenseEntry: {
							licenseEntryType: 'cluster'
						}
					})
				}
			>
				<SpecificDetails
					addLicenseKeyURL="add/license/key/url"
					redirect={'/redirect/url'}
				/>
			</NewLicenseProvider>
		);

		getByLabelText('maximum-servers');
	});

	it('does not display the Maximum Connections input if the selected Type is not Developer or Developer Cluster', () => {
		const {queryByLabelText} = render(
			<NewLicenseProvider
				initialLicense={
					new License({
						licenseEntry: {
							licenseEntryType: 'oem'
						}
					})
				}
			>
				<SpecificDetails
					addLicenseKeyURL="add/license/key/url"
					redirect={'/redirect/url'}
				/>
			</NewLicenseProvider>
		);

		expect(queryByLabelText('maximum-connections')).toBeFalsy();
	});

	it('displays the Maximum Connections input if the selected Type is Developer', () => {
		const {getByLabelText} = renderSpecificDetails();

		getByLabelText('maximum-connections');
	});

	it('displays the Maximum Connections input if the selected Type is Developer Cluster', () => {
		const {getByLabelText} = render(
			<NewLicenseProvider
				initialLicense={
					new License({
						licenseEntry: {
							licenseEntryType: 'developer_cluster'
						}
					})
				}
			>
				<SpecificDetails
					addLicenseKeyURL="add/license/key/url"
					redirect={'/redirect/url'}
				/>
			</NewLicenseProvider>
		);

		getByLabelText('maximum-connections');
	});

	it('displays a warning that IPv6 addresses are ignore when one was entered', async () => {
		const {getByLabelText, getByText} = renderServerIdFields();

		await wait(() => {
			fireEvent.change(getByLabelText('ip-addresses'), {
				target: {value: '1762:0:0:0:0:B03:1:AF18'}
			});

			getByText(
				'ipv6-addresses-in-activation-keys-are-currently-ignored-please-enter-a-hostname-or-mac-address-instead'
			);
		});
	});

	it('displays no error message if license keys generated count is lower than the license keys allowed', async () => {
		const {getByText, queryByText} = renderSpecificDetails();

		getByText('0 / 1');

		expect(
			queryByText(
				'the-provisioned-keys-count-is-already-equal-to-or-higher-than-the-purchased-subscriptions'
			)
		).toBeFalsy();
	});

	it('displays an error message if license keys generated count will be higher than or equal to the license keys allowed', async () => {
		const {getByText} = render(
			<NewLicenseProvider
				initialLicense={
					new License({
						licenseEntry: {
							licenseEntryType: 'virtual_cluster'
						},
						licenseKeysAllowed: 1,
						licenseKeysGenerated: 1
					})
				}
			>
				<SpecificDetails
					addLicenseKeyURL="add/license/key/url"
					redirect={'/redirect/url'}
				/>
			</NewLicenseProvider>
		);

		getByText('1 / 1');

		getByText(
			'the-provisioned-keys-count-is-already-equal-to-or-higher-than-the-purchased-subscriptions'
		);
	});

	it('displays an error message if Max Cluster Nodes count will be higher than the license keys allowed', async () => {
		const {getByLabelText, getByText, queryByText} = render(
			<NewLicenseProvider
				initialLicense={
					new License({
						licenseEntry: {
							licenseEntryType: 'virtual_cluster'
						},
						licenseKeysAllowed: 1
					})
				}
			>
				<SpecificDetails
					addLicenseKeyURL="add/license/key/url"
					redirect={'/redirect/url'}
				/>
			</NewLicenseProvider>
		);

		expect(
			queryByText(
				'the-provisioned-keys-count-is-already-equal-to-or-higher-than-the-purchased-subscriptions'
			)
		).toBeFalsy();

		fireEvent.change(getByLabelText('maximum-cluster-nodes'), {
			target: {value: '2'}
		});

		getByText(
			'the-provisioned-keys-count-is-already-equal-to-or-higher-than-the-purchased-subscriptions'
		);
	});

	describe('Generate Button', () => {
		it('displays a Generate button', () => {
			const {getByText} = renderSpecificDetails();

			getByText('generate');
		});

		it('displays a disabled Generate button if the Owner field is empty', () => {
			const {getByText} = renderSpecificDetails();

			fireEvent.change(getByText('owner'), {taget: {value: ''}});

			expect(getByText('generate').disabled).toBeTruthy();
		});

		it('displays a disabled Generate button if the Description field is empty', () => {
			const {getByText} = renderSpecificDetails();

			fireEvent.change(getByText('description'), {taget: {value: ''}});

			expect(getByText('generate').disabled).toBeTruthy();
		});

		it('displays a disabled Generate button if the Server ID Fields are displayed but no value has been entered in any of its three fields', () => {
			const {getByText} = renderServerIdFields();

			expect(getByText('generate').disabled).toBeTruthy();
		});

		it('displays an enabled Generate button if a Host Name is entered', async () => {
			const {getByLabelText, getByText} = renderServerIdFields();

			await wait(() => {
				fireEvent.change(getByLabelText('host-name'), {
					target: {value: 'host name 1'}
				});

				expect(getByText('generate').disabled).toBeFalsy();
			});
		});

		it('displays a disabled Generate button if only a valid IPv6 address was entered', async () => {
			const {getByLabelText, getByText} = renderServerIdFields();

			await wait(() => {
				fireEvent.change(getByLabelText('ip-addresses'), {
					target: {value: '1762:0:0:0:0:B03:1:AF18'}
				});

				expect(getByText('generate').disabled).toBeTruthy();
			});
		});

		it('displays an enabled Generate button if a valid IPv4 address was entered', async () => {
			const {getByLabelText, getByText} = renderServerIdFields();

			await wait(() => {
				fireEvent.change(getByLabelText('ip-addresses'), {
					target: {value: '127.0.0.1'}
				});

				expect(getByText('generate').disabled).toBeFalsy();
			});
		});

		it('displays a disabled Generate button if an invalid IPv4 address was entered', async () => {
			const {getByLabelText, getByText} = renderServerIdFields();

			await wait(() => {
				fireEvent.change(getByLabelText('ip-addresses'), {
					target: {value: '127.0.0.267'}
				});

				expect(getByText('generate').disabled).toBeTruthy();
			});
		});

		it('displays an enabled Generate button if a valid MAC address was entered', async () => {
			const {getByLabelText, getByText} = renderServerIdFields();

			await wait(() => {
				fireEvent.change(getByLabelText('mac-addresses'), {
					target: {value: '00:00:0A:BB:28:FC'}
				});

				expect(getByText('generate').disabled).toBeFalsy();
			});
		});

		it('displays a disabled Generate button if an invalid MAC address was entered', async () => {
			const {getByLabelText, getByText} = renderServerIdFields();

			await wait(() => {
				fireEvent.change(getByLabelText('mac-addresses'), {
					target: {value: '00:00:0A:BB:28:FG'}
				});

				expect(getByText('generate').disabled).toBeTruthy();
			});
		});
	});
});
