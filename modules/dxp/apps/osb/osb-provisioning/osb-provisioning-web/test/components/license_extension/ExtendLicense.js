/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {cleanup, fireEvent, render} from '@testing-library/react';
import React from 'react';

import ExtendLicense from '../../../src/main/resources/META-INF/resources/js/components/license_extension/ExtendLicense';
import {CURRENT_TIME} from '../../../src/main/resources/META-INF/resources/js/utilities/constants';
import {
	formatDate,
	generateNewDateByYear
} from '../../../src/main/resources/META-INF/resources/js/utilities/date';

const singleAttachedLicense = [
	{
		accountName: 'Account 1',
		expirationDate: '2022-06-08',
		indefinite: false,
		licenseKeyId: 'licenseKeyID1',
		licenseKeysGenerated: 0,
		licenseType: 'development',
		productName: 'DXP 7.0',
		startDate: '2021-06-03',
		terms: [
			{
				endDate: '',
				licenseKeysAllowed: 1,
				licenseKeysGenerated: 2,
				perpetual: true,
				productPurchaseKey: 'productPurchaseKey1',
				startDate: '',
				status: 'Approved'
			},
			{
				endDate: '2022-07-02',
				licenseKeysAllowed: 1,
				licenseKeysGenerated: 1,
				perpetual: false,
				productPurchaseKey: 'productPurchaseKey2',
				startDate: '2021-06-02',
				status: 'Approved'
			}
		]
	}
];

const multipleAttachedLicenses = [
	{
		accountName: 'Account 1',
		expirationDate: '2022-06-08',
		indefinite: false,
		licenseKeyId: 'licenseKeyID1',
		licenseKeysGenerated: 0,
		licenseType: 'development',
		productName: 'DXP 7.0',
		startDate: '2021-06-03',
		terms: [
			{
				endDate: '',
				licenseKeysAllowed: 1,
				licenseKeysGenerated: 2,
				perpetual: true,
				productPurchaseKey: 'productPurchaseKey1',
				startDate: '',
				status: 'Approved'
			},
			{
				endDate: '2022-07-02',
				licenseKeysAllowed: 1,
				licenseKeysGenerated: 1,
				perpetual: false,
				productPurchaseKey: 'productPurchaseKey2',
				startDate: '2021-06-02',
				status: 'Approved'
			}
		]
	},
	{
		accountName: 'Account 1',
		expirationDate: '2022-06-08',
		indefinite: false,
		licenseKeyId: 'licenseKeyID2',
		licenseKeysGenerated: 0,
		licenseType: 'development',
		productName: 'DXP 7.0',
		startDate: '2021-06-03',
		terms: [
			{
				endDate: '',
				licenseKeysAllowed: 1,
				licenseKeysGenerated: 2,
				perpetual: true,
				productPurchaseKey: 'productPurchaseKey3',
				startDate: '',
				status: 'Approved'
			},
			{
				endDate: '2022-07-02',
				licenseKeysAllowed: 1,
				licenseKeysGenerated: 1,
				perpetual: false,
				productPurchaseKey: 'productPurchaseKey4',
				startDate: '2021-06-02',
				status: 'Approved'
			}
		]
	},
	{
		accountName: 'Account 1',
		expirationDate: '2022-06-08',
		indefinite: false,
		licenseKeyId: 'licenseKeyID3',
		licenseKeysGenerated: 0,
		licenseType: 'development',
		productName: 'DXP 7.0',
		startDate: '2021-06-03',
		terms: [
			{
				endDate: '2022-07-02',
				licenseKeysAllowed: 1,
				licenseKeysGenerated: 1,
				perpetual: false,
				productPurchaseKey: 'productPurchaseKey5',
				startDate: '2021-06-02',
				status: 'Approved'
			}
		]
	}
];

const multipleDetachedLicenses = [
	{
		accountName: 'Account 1',
		expirationDate: '2022-06-04',
		indefinite: false,
		licenseKeyId: 'licenseKeyID1',
		licenseKeysGenerated: 0,
		licenseType: 'production',
		productName: 'DXP Development',
		startDate: '2021-06-04'
	},
	{
		accountName: 'Account 2',
		expirationDate: '2027-12-14',
		indefinite: false,
		licenseKeyId: 'licenseKeyID2',
		licenseKeysGenerated: 0,
		licenseType: 'developer',
		productName: 'DXP Development',
		startDate: '2021-07-26'
	}
];

function renderExtendLicense(props) {
	return render(
		<ExtendLicense
			details={[
				{
					accountName: 'Account 1',
					expirationDate: '2021-08-21',
					indefinite: false,
					licenseKeyId: 'licenseKeyID1',
					licenseKeysGenerated: 0,
					licenseType: 'development',
					productName: 'DXP 7.0',
					startDate: '2021-07-21'
				}
			]}
			extensionURL="/extension/url"
			hasUpdateLicenseDatePermission={true}
			{...props}
		/>
	);
}

describe('ExtendLicense', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderExtendLicense();

		expect(container).toBeTruthy();
	});

	describe('general display', () => {
		it('displays an Account Name table heading', () => {
			const {getByText} = renderExtendLicense();

			getByText('account-name');
		});

		it('displays a Product table heading', () => {
			const {getByText} = renderExtendLicense();

			getByText('products');
		});

		it('displays a Subscription Terms table heading', () => {
			const {getByText} = renderExtendLicense();

			getByText('subscription-term');
		});

		it('displays a Start Date table heading', () => {
			const {getByText} = renderExtendLicense();

			getByText('start-date');
		});

		it('displays an Expiration Date table heading', () => {
			const {getByText} = renderExtendLicense();

			getByText('expiration-date');
		});

		it('displays a Licenses Generated table heading', () => {
			const {getByText} = renderExtendLicense();

			getByText('licenses-generated');
		});
	});

	describe('Bulk Input', () => {
		it('displays Bulk Input when more than one temporary licenses of the same product are available', () => {
			const {getByText} = renderExtendLicense({
				details: multipleAttachedLicenses
			});

			getByText('bulk-input');
		});

		it('does not display Bulk Input when only one temporary license of a given product is available', () => {
			const {queryByText} = renderExtendLicense({
				details: singleAttachedLicense
			});

			expect(queryByText('bulk-input')).toBeFalsy();
		});

		it('populates the start/expiration bulk date input when all licenses in the group have the same start/expiration dates', () => {
			const {container} = renderExtendLicense({
				details: multipleAttachedLicenses
			});

			const bulkStartDate = container.querySelector(
				'input[name="startDateBulkInput-DXP 7.0"]'
			);

			const bulkExpirationDate = container.querySelector(
				'input[name="expirationDateBulkInput-DXP 7.0"]'
			);

			expect(bulkStartDate.value).toBe('2021-06-03');
			expect(bulkExpirationDate.value).toBe('2022-06-08');
		});

		it('displays the start/expiration bulk date input as Varied Data when dates vary for the licenses in the group', () => {
			const {container} = renderExtendLicense({
				details: [
					{
						accountName: 'Account 1',
						expirationDate: '2022-01-08',
						indefinite: false,
						licenseKeyId: 'licenseKeyID1',
						licenseKeysGenerated: 0,
						licenseType: 'development',
						productName: 'DXP 7.0',
						startDate: '2021-01-03',
						terms: [
							{
								endDate: '2022-07-02',
								licenseKeysAllowed: 1,
								licenseKeysGenerated: 1,
								perpetual: false,
								productPurchaseKey: 'productPurchaseKey2',
								startDate: '2021-06-02',
								status: 'Approved'
							}
						]
					},
					{
						accountName: 'Account 1',
						expirationDate: '2022-06-08',
						indefinite: false,
						licenseKeyId: 'licenseKeyID2',
						licenseKeysGenerated: 0,
						licenseType: 'development',
						productName: 'DXP 7.0',
						startDate: '2021-06-03',
						terms: [
							{
								endDate: '2022-07-02',
								licenseKeysAllowed: 1,
								licenseKeysGenerated: 1,
								perpetual: false,
								productPurchaseKey: 'productPurchaseKey4',
								startDate: '2021-06-02',
								status: 'Approved'
							}
						]
					}
				]
			});

			const bulkStartDate = container.querySelector(
				'input[name="startDateBulkInput-DXP 7.0"]'
			);

			const bulkExpirationDate = container.querySelector(
				'input[name="expirationDateBulkInput-DXP 7.0"]'
			);

			expect(bulkStartDate.value).toBe('');
			expect(bulkExpirationDate.value).toBe('');
		});

		it("display Varied Data when one of the licenses' date changes", () => {
			const {container, getAllByDisplayValue} = renderExtendLicense({
				details: multipleAttachedLicenses
			});

			const bulkStartDate = container.querySelector(
				'input[name="startDateBulkInput-DXP 7.0"]'
			);

			expect(bulkStartDate.value).toBe('2021-06-03');

			// Clay Date Picker always displays two inputs for the same date

			fireEvent.change(getAllByDisplayValue('2021-06-03')[3], {
				target: {value: '2021-03-06'}
			});

			expect(bulkStartDate.value).toBe('');
		});

		it('updates all licenses dates in the group when the Bulk Input is updated', () => {
			const {container, getAllByDisplayValue} = renderExtendLicense({
				details: multipleAttachedLicenses
			});

			const bulkStartDate = container.querySelector(
				'input[name="startDateBulkInput-DXP 7.0"]'
			);

			expect(bulkStartDate.value).toBe('2021-06-03');

			// Clay Date Picker always displays two inputs for the same date

			fireEvent.change(getAllByDisplayValue('2021-06-03')[1], {
				target: {value: '2021-03-06'}
			});

			expect(bulkStartDate.value).toBe('2021-03-06');
		});
	});

	describe('Extend Button', () => {
		describe('Single License', () => {
			it('does not render an Extend button for a permanent license', () => {
				const {queryByText} = renderExtendLicense({
					details: [
						{
							accountName: 'Account 1',
							expirationDate: '2022-06-04',
							indefinite: true,
							licenseKeyId: 'licenseKeyID1',
							licenseType: 'production',
							productName: 'Commerce Subscription Backup',
							startDate: '2021-06-04'
						}
					]
				});

				expect(queryByText('extend')).toBeFalsy();
			});

			it('renders an enabled Extend button for a Detached temporary license by default', () => {
				const {getByText} = renderExtendLicense({
					details: [
						{
							accountName: 'Account 1',
							expirationDate: '2022-06-04',
							indefinite: false,
							licenseKeyId: 'licenseKeyID1',
							licenseKeysGenerated: 0,
							licenseType: 'production',
							productName: 'DXP Development',
							startDate: '2021-06-04'
						}
					]
				});

				expect(getByText('extend').disabled).toBeFalsy();
			});

			it('renders a disabled Extend button for an Attached temporary license by default', () => {
				const {getByText} = renderExtendLicense({
					details: singleAttachedLicense
				});

				expect(getByText('extend').disabled).toBeTruthy();
			});

			it('renders an enabled Extend button for an Attached temporary license after a term has been selected', () => {
				const {getByLabelText, getByText} = renderExtendLicense({
					details: singleAttachedLicense
				});

				fireEvent.change(getByLabelText('subscription-term'), {
					target: {value: 'productPurchaseKey2'}
				});

				expect(getByText('extend').disabled).toBeFalsy();
			});

			it('renders a disabled Extend button if any of the dates are empty ', () => {
				const {
					getAllByDisplayValue,
					getByLabelText,
					getByText
				} = renderExtendLicense({
					details: singleAttachedLicense
				});

				fireEvent.change(getByLabelText('subscription-term'), {
					target: {value: 'productPurchaseKey2'}
				});

				expect(getByText('extend').disabled).toBeFalsy();

				// Clay Date Picker always displays two inputs for the same date

				fireEvent.change(getAllByDisplayValue('2021-06-03')[1], {
					target: {value: ''}
				});

				expect(getByText('extend').disabled).toBeTruthy();
			});

			it('always renders an enabled Extend button after a new Subscription Term is selected', () => {
				const {getByLabelText, getByText} = renderExtendLicense({
					details: singleAttachedLicense
				});

				fireEvent.change(getByLabelText('subscription-term'), {
					target: {value: 'productPurchaseKey2'}
				});

				expect(getByText('extend').disabled).toBeFalsy();

				fireEvent.change(getByLabelText('subscription-term'), {
					target: {value: 'productPurchaseKey1'}
				});

				expect(getByText('extend').disabled).toBeFalsy();
			});
		});

		describe('Multiple Licenses', () => {
			it('renders one Extend button for a group of temporary licenses for the same product', () => {
				const {getAllByText} = renderExtendLicense({
					details: multipleDetachedLicenses
				});

				expect(getAllByText('extend').length).toBe(1);
			});

			it('renders a disabled Extend button for a group of Attached temporary licenses by default', () => {
				const {getByText} = renderExtendLicense({
					details: multipleAttachedLicenses
				});

				expect(getByText('extend').disabled).toBeTruthy();
			});

			it('renders an enabled Extend button when all of the temporary licenses in a group has a term selected', () => {
				const {getAllByLabelText, getByText} = renderExtendLicense({
					details: multipleAttachedLicenses
				});

				const subscriptionTerms = getAllByLabelText(
					'subscription-term'
				);

				fireEvent.change(subscriptionTerms[0], {
					target: {value: 'productPurchaseKey2'}
				});

				fireEvent.change(subscriptionTerms[1], {
					target: {value: 'productPurchaseKey3'}
				});

				expect(getByText('extend').disabled).toBeFalsy();
			});

			it('renders an enabled Extend button correctly after user removes a license and selects a term for the remaining licenses in a group', () => {
				const {
					getAllByLabelText,
					getAllByTitle,
					getByText
				} = renderExtendLicense({
					details: multipleAttachedLicenses
				});

				const deleteBtns = getAllByTitle('delete');

				fireEvent.click(deleteBtns[2]);

				const subscriptionTerms = getAllByLabelText(
					'subscription-term'
				);
				expect(subscriptionTerms.length).toBe(2);

				fireEvent.change(subscriptionTerms[0], {
					target: {value: 'productPurchaseKey2'}
				});

				fireEvent.change(subscriptionTerms[1], {
					target: {value: 'productPurchaseKey3'}
				});

				expect(getByText('extend').disabled).toBeFalsy();
			});

			it('renders a disabled Extend button if any of the dates are invalid', () => {
				const {getAllByDisplayValue, getByText} = renderExtendLicense({
					details: multipleDetachedLicenses
				});

				expect(getByText('extend').disabled).toBeFalsy();

				// Clay Date Picker always displays two inputs for the same date

				fireEvent.change(
					getAllByDisplayValue(formatDate(CURRENT_TIME))[3],
					{
						target: {value: 'invalid'}
					}
				);

				expect(getByText('extend').disabled).toBeTruthy();
			});

			it('renders a disabled Extend button if any of the start dates occurs on a latter date than the corresponding expiration date', () => {
				const {getAllByDisplayValue, getByText} = renderExtendLicense({
					details: multipleDetachedLicenses
				});

				expect(getByText('extend').disabled).toBeFalsy();

				// Clay Date Picker always displays two inputs for the same date

				fireEvent.change(
					getAllByDisplayValue(formatDate(CURRENT_TIME))[1],
					{
						target: {
							value: formatDate(
								generateNewDateByYear(CURRENT_TIME, 100)
							)
						}
					}
				);

				expect(getByText('extend').disabled).toBeTruthy();
			});
		});
	});

	describe('Default Dates', () => {
		it('renders the default start and expiration dates of the existing license for an Attached license entry', () => {
			const {getAllByDisplayValue} = renderExtendLicense({
				details: singleAttachedLicense
			});

			// Clay Date Picker always displays two inputs for the same date
			// The date occurs one more time in the hidden form

			expect(getAllByDisplayValue('2021-06-03').length).toBe(3);
			expect(getAllByDisplayValue('2022-06-08').length).toBe(3);
		});

		it('renders Today as the default start date for a Detached license', () => {
			const {getAllByDisplayValue} = renderExtendLicense({
				details: [
					{
						accountName: 'Account 1',
						expirationDate: '2022-06-04',
						indefinite: false,
						licenseKeyId: 'licenseKeyID1',
						licenseKeysGenerated: 0,
						licenseType: 'production',
						productName: 'Commerce Subscription Backup',
						startDate: '2021-06-04'
					}
				]
			});

			// Clay Date Picker always displays two inputs for the same date
			// The date occurs one more time in the hidden form

			expect(getAllByDisplayValue(formatDate(CURRENT_TIME)).length).toBe(
				3
			);
		});
	});

	it('renders the Permanent License table heading for a single permanent license', () => {
		const {getByText} = renderExtendLicense({
			details: [
				{
					accountName: 'Account 1',
					expirationDate: '2022-06-04',
					indefinite: true,
					licenseKeyId: 'licenseKeyID1',
					licenseType: 'production',
					productName: 'Commerce Subscription Backup',
					startDate: '2021-06-04'
				}
			]
		});

		getByText('permanent-licenses');
	});

	it('updates the Licenses Generated value when terms change', () => {
		const {getByLabelText, getByText, queryByText} = renderExtendLicense({
			details: singleAttachedLicense
		});

		getByText('0 / 0');

		fireEvent.change(getByLabelText('subscription-term'), {
			target: {value: 'productPurchaseKey2'}
		});

		expect(queryByText('0 / 0')).toBeFalsy();
		getByText('1 / 1');
	});

	it('does not render any terms whose status is cancelled', () => {
		const {getByText, queryByText} = renderExtendLicense({
			details: [
				{
					accountName: 'Account 1',
					expirationDate: '2122-06-08',
					indefinite: false,
					licenseKeyId: 'licenseKeyID1',
					licenseKeysGenerated: 0,
					licenseType: 'development',
					productName: 'DXP 7.0',
					startDate: '2021-06-03',
					terms: [
						{
							endDate: '',
							licenseKeysAllowed: 1,
							licenseKeysGenerated: 2,
							perpetual: true,
							productPurchaseKey: 'productPurchaseKey1',
							startDate: '',
							status: 'Approved'
						},
						{
							endDate: '2022-07-02',
							licenseKeysAllowed: 1,
							licenseKeysGenerated: 1,
							perpetual: false,
							productPurchaseKey: 'productPurchaseKey2',
							startDate: '2021-06-02',
							status: 'Cancelled'
						}
					]
				}
			]
		});

		getByText('perpetual');
		expect(queryByText('June 02, 2021 - July 02, 2022')).toBeFalsy();
	});

	it('auto fills the terms if only one is Approved', () => {
		const {getByLabelText, getByText} = renderExtendLicense({
			details: [
				{
					accountName: 'Account 1',
					expirationDate: '2122-06-08',
					indefinite: false,
					licenseKeyId: 'licenseKeyID1',
					licenseKeysGenerated: 0,
					licenseType: 'development',
					productName: 'DXP 7.0',
					startDate: '2021-06-03',
					terms: [
						{
							endDate: '',
							licenseKeysAllowed: 1,
							licenseKeysGenerated: 2,
							perpetual: true,
							productPurchaseKey: 'productPurchaseKey1',
							startDate: '',
							status: 'Approved'
						},
						{
							endDate: '2022-07-02',
							licenseKeysAllowed: 1,
							licenseKeysGenerated: 1,
							perpetual: false,
							productPurchaseKey: 'productPurchaseKey2',
							startDate: '2021-06-02',
							status: 'Cancelled'
						}
					]
				}
			]
		});

		expect(getByLabelText('subscription-term').value).toBe(
			'productPurchaseKey1'
		);
		expect(getByText('extend').disabled).toBeFalsy();
	});
});
