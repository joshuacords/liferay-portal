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

import {cleanup, fireEvent, render} from '@testing-library/react';
import React from 'react';

import GeneralInformation from '../../../src/main/resources/META-INF/resources/js/components/license_generation/GeneralInformation';
import {NewLicenseProvider} from '../../../src/main/resources/META-INF/resources/js/hooks/newLicense';
import {PermissionsProvider} from '../../../src/main/resources/META-INF/resources/js/hooks/permissions';

const licensableProducts = [
	{
		detached: {
			instanceSizes: [1, 2, 3, 4],
			licenseKeysGenerated: '0'
		},
		productKey: 'KEY-123',
		productName: 'Product A',
		productVersions: {
			6.1: [
				{
					licenseEntryDisplayName: 'Portal Backup (Production)',
					licenseEntryId: '98765',
					licenseEntryName: 'Portal Backup',
					licenseEntryType: 'production'
				},
				{
					licenseEntryDisplayName: 'Portal Backup (Development)',
					licenseEntryId: '87654',
					licenseEntryName: 'Portal Backup',
					licenseEntryType: 'development'
				}
			],
			6.2: [
				{
					licenseEntryDisplayName: 'Entry Name A (Oem)',
					licenseEntryId: '12345',
					licenseEntryName: 'Entry Name A',
					licenseEntryType: 'oem'
				},
				{
					licenseEntryDisplayName: 'Entry Name B (Limited)',
					licenseEntryId: '23456',
					licenseEntryName: 'Entry Name B',
					licenseEntryType: 'limited'
				}
			]
		}
	},
	{
		productKey: 'KEY-456',
		productName: 'Product B',
		productVersions: {
			'7.0': [
				{
					licenseEntryDisplayName: 'Test Entry (Developer)',
					licenseEntryId: '76543',
					licenseEntryName: 'Test Entry',
					licenseEntryType: 'developer'
				}
			]
		}
	}
];

const purchasedProducts = {
	'KEY-123': [
		{
			expirationDate: '2022-04-16',
			instanceSize: 1,
			licenseKeysGenerated: '0 / 1',
			perpetual: false,
			productPurchaseKey: 'PURCHKEY-123',
			startDate: '2021-03-17'
		},
		{
			expirationDate: '',
			instanceSize: 1,
			licenseKeysGenerated: '1 / 1',
			perpetual: true,
			productPurchaseKey: 'PURCHKEY-321',
			startDate: ''
		}
	],
	'KEY-456': [
		{
			expirationDate: '',
			instanceSize: 1,
			licenseKeysGenerated: '1 / 1',
			perpetual: true,
			productPurchaseKey: 'PURCHKEY-456',
			startDate: ''
		}
	]
};

function renderGeneralInformation({permission = true, props = {}} = {}) {
	return render(
		<NewLicenseProvider>
			<PermissionsProvider
				permissions={{updateDatePermission: permission}}
			>
				<GeneralInformation
					allowPermanentLicenses={true}
					redirect="/back/url"
					selectAccountActionURL="/action/url"
					selectAccountRenderURL="render/url"
					{...props}
				/>
			</PermissionsProvider>
		</NewLicenseProvider>
	);
}

describe('GeneralInformation', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderGeneralInformation();

		expect(container).toBeTruthy();
	});

	it('displays a sub heading showing the editing step', () => {
		const {getAllByText, getByText} = renderGeneralInformation();

		expect(getAllByText('general-information').length).toBe(2);
		expect(getByText('step-1-of-2')).toBeTruthy();
	});

	it('displays a Cancel button', () => {
		const {getByText} = renderGeneralInformation();

		expect(getByText('cancel')).toBeTruthy();
	});

	it('displays a disabled Product select if account has not been selected', () => {
		const {getByLabelText} = renderGeneralInformation();

		expect(getByLabelText('product').disabled).toBeTruthy();
	});

	it('displays a disabled Type select if account has not been selected', () => {
		const {getByLabelText} = renderGeneralInformation();

		expect(getByLabelText('type').disabled).toBeTruthy();
	});

	it('displays a disabled Version select if account has not been selected', () => {
		const {getByLabelText} = renderGeneralInformation();

		expect(getByLabelText('version').disabled).toBeTruthy();
	});

	it('displays the account name if one is provided', () => {
		const {getByDisplayValue} = renderGeneralInformation({
			props: {
				accountName: 'Test Account'
			}
		});

		getByDisplayValue('Test Account');
	});

	it('populates the Product select with options if a list of licensable products is provided', () => {
		const {getByLabelText, getByText} = renderGeneralInformation({
			props: {
				accountName: 'Test Account',
				licensableProducts
			}
		});

		getByText('Product A');
		expect(getByLabelText('product').disabled).toBeFalsy();
		expect(getByLabelText('type').disabled).toBeTruthy();
		expect(getByLabelText('version').disabled).toBeTruthy();
	});

	it('groups the purchased products inside the Product dropdown', () => {
		const {container, getByLabelText} = renderGeneralInformation({
			props: {
				accountName: 'Test Account',
				licensableProducts,
				purchasedProducts
			}
		});

		fireEvent.click(getByLabelText('product'));

		expect(
			container.querySelector('optgroup[label="purchased"]')
		).toBeTruthy();
	});

	it('groups the not purchased products inside the Product dropdown', () => {
		const {container, getByLabelText} = renderGeneralInformation({
			props: {
				accountName: 'Test Account',
				licensableProducts
			}
		});

		fireEvent.click(getByLabelText('product'));

		expect(
			container.querySelector('optgroup[label="not-purchased"]')
		).toBeTruthy();
	});

	it('populates the Version select with options based on the Product selected', () => {
		const {getByLabelText, getByText} = renderGeneralInformation({
			props: {
				accountName: 'Test Account',
				licensableProducts
			}
		});

		fireEvent.change(getByLabelText('product'), {
			target: {value: 'KEY-123'}
		});

		getByText('6.1');
		getByText('6.2');
		expect(getByLabelText('version').disabled).toBeFalsy();
		expect(getByLabelText('type').disabled).toBeTruthy();
	});

	it('populates the Type select with options based on the Product and Version selected', () => {
		const {getByLabelText, getByText} = renderGeneralInformation({
			props: {
				accountName: 'Test Account',
				licensableProducts
			}
		});

		fireEvent.change(getByLabelText('product'), {
			target: {value: 'KEY-123'}
		});
		fireEvent.change(getByLabelText('version'), {
			target: {value: '6.1'}
		});

		getByText('Portal Backup (Production)');
		getByText('Portal Backup (Development)');
		expect(getByLabelText('type').disabled).toBeFalsy();
	});

	it('displays the Choose Purchase section after Product, Version, and Type have been selected', () => {
		const {
			getByLabelText,
			getByText,
			queryByText
		} = renderGeneralInformation({
			props: {
				accountName: 'Test Account',
				licensableProducts
			}
		});

		expect(queryByText('choose-purchase')).toBeFalsy();

		fireEvent.change(getByLabelText('product'), {
			target: {value: 'KEY-123'}
		});
		fireEvent.change(getByLabelText('version'), {
			target: {value: '6.1'}
		});
		fireEvent.change(getByLabelText('type'), {
			target: {value: 98765}
		});

		getByText('choose-purchase');
	});

	it('displays the Non-detached section based on the Product Selected', () => {
		const {getAllByDisplayValue, getByLabelText} = renderGeneralInformation(
			{
				props: {
					accountName: 'Test Account',
					licensableProducts,
					purchasedProducts
				}
			}
		);

		fireEvent.change(getByLabelText('product'), {
			target: {value: 'KEY-123'}
		});
		fireEvent.change(getByLabelText('version'), {
			target: {value: '6.1'}
		});
		fireEvent.change(getByLabelText('type'), {
			target: {value: 98765}
		});

		// Clay Date Picker always displays two inputs for the same date

		expect(getAllByDisplayValue('2021-03-17').length).toBe(2);
	});

	it('repopulates the Version dropdown when the Product dropdown has been reselected', () => {
		const {getByLabelText, getByText} = renderGeneralInformation({
			props: {
				accountName: 'Test Account',
				licensableProducts
			}
		});

		fireEvent.change(getByLabelText('product'), {
			target: {value: 'KEY-123'}
		});

		getByText('6.1');
		getByText('6.2');

		fireEvent.change(getByLabelText('product'), {
			target: {value: 'KEY-456'}
		});

		getByText('7.0');
	});

	it('repopulates the Type dropdown when the Version dropdown has been reselected', () => {
		const {getByLabelText, getByText} = renderGeneralInformation({
			props: {
				accountName: 'Test Account',
				licensableProducts
			}
		});

		fireEvent.change(getByLabelText('product'), {
			target: {value: 'KEY-123'}
		});
		fireEvent.change(getByLabelText('version'), {
			target: {value: '6.1'}
		});

		getByText('Portal Backup (Production)');
		getByText('Portal Backup (Development)');

		fireEvent.change(getByLabelText('version'), {
			target: {value: '6.2'}
		});

		getByText('Entry Name A (Oem)');
		getByText('Entry Name B (Limited)');
	});

	it('disables the Type dropdown after the Product dropdown was reselected, until the Version dropdown is selected again', () => {
		const {getByLabelText} = renderGeneralInformation({
			props: {
				accountName: 'Test Account',
				licensableProducts
			}
		});

		fireEvent.change(getByLabelText('product'), {
			target: {value: 'KEY-123'}
		});
		fireEvent.change(getByLabelText('version'), {
			target: {value: '6.1'}
		});

		fireEvent.change(getByLabelText('product'), {
			target: {value: 'KEY-456'}
		});

		expect(getByLabelText('type').disabled).toBeTruthy();

		fireEvent.change(getByLabelText('version'), {
			target: {value: '7.0'}
		});

		expect(getByLabelText('type').disabled).toBeFalsy();
	});

	it('hides the Choose Purchase section when the Product dropdown was reselected after Product, Version, and Type have already been selected', () => {
		const {getByLabelText, queryByText} = renderGeneralInformation({
			props: {
				accountName: 'Test Account',
				licensableProducts
			}
		});

		fireEvent.change(getByLabelText('product'), {
			target: {value: 'KEY-123'}
		});
		fireEvent.change(getByLabelText('version'), {
			target: {value: '6.1'}
		});
		fireEvent.change(getByLabelText('type'), {
			target: {value: 98765}
		});

		fireEvent.change(getByLabelText('product'), {
			target: {value: 'KEY-456'}
		});

		expect(queryByText('choose-purchase')).toBeFalsy();
	});

	it('hides the Choose Purchase section when the Version dropdown was reselected after Product, Version, and Type have been previously selected', () => {
		const {getByLabelText, queryByText} = renderGeneralInformation({
			props: {
				accountName: 'Test Account',
				licensableProducts
			}
		});

		fireEvent.change(getByLabelText('product'), {
			target: {value: 'KEY-123'}
		});
		fireEvent.change(getByLabelText('version'), {
			target: {value: '6.1'}
		});
		fireEvent.change(getByLabelText('type'), {
			target: {value: 98765}
		});

		fireEvent.change(getByLabelText('version'), {
			target: {value: '6.2'}
		});

		expect(queryByText('choose-purchase')).toBeFalsy();
	});
});
