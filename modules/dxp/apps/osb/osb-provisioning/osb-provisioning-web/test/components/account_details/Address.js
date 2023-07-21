/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {cleanup, fireEvent, render} from '@testing-library/react';
import React from 'react';

import Address from '../../../src/main/resources/META-INF/resources/js/components/account_details/Address';
import {PermissionsProvider} from '../../../src/main/resources/META-INF/resources/js/hooks/permissions';
import {DASH} from '../../../src/main/resources/META-INF/resources/js/utilities/constants';

const countryOptions = [
	{
		active: true,
		countryRegions: [
			{
				active: true,
				name: 'Shanghai'
			},
			{
				active: true,
				name: 'Sichuan'
			}
		],
		name: 'China',
		zipRequired: true
	},
	{
		active: true,
		countryRegions: [],
		name: 'United Arab Emirates',
		zipRequired: false
	},
	{
		active: true,
		countryRegions: [
			{
				active: true,
				name: 'California'
			}
		],
		name: 'United States',
		zipRequired: true
	}
];

function renderAddress(permission = true) {
	return render(
		<PermissionsProvider permissions={{updatePermission: permission}}>
			<Address
				accountKey="key123"
				addFn={jest.fn()}
				address={{
					addressCountry: 'United States',
					addressLocality: 'Diamond Bar',
					addressRegion: 'California',
					deletePostalAddressURL: '/',
					editPostalAddressURL: '/',
					id: '123',
					postalCode: '91765',
					primary: true,
					streetAddressLine1: '1400 Montefino Ave',
					streetAddressLine2: DASH,
					streetAddressLine3: DASH
				}}
				count={1}
				countryOptions={countryOptions}
			/>
		</PermissionsProvider>
	);
}

describe('Address', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderAddress();

		expect(container).toBeTruthy();
	});

	it('displays all address fields as editable when any one of the address fields is clicked for a user with full editing privilege', () => {
		const {container, getByText} = renderAddress();

		fireEvent.click(getByText('Diamond Bar'));

		expect(container.querySelectorAll('select').length).toBe(2);
		expect(container.querySelectorAll('input[type=text]').length).toBe(5);

		getByText('save');
		getByText('cancel');
	});

	it('displays all address fields as non editable when any of the fields is clicked for a user with limited editing privilege', () => {
		const {container, getByText, queryByText} = renderAddress(false);

		fireEvent.click(getByText('Diamond Bar'));

		expect(container.querySelectorAll('select').length).toBe(0);
		expect(container.querySelectorAll('input[type=text]').length).toBe(0);

		expect(queryByText('save')).toBeFalsy();
		expect(queryByText('cancel')).toBeFalsy();
	});

	it('displays PRC, UAE, and USA as country options when the user clicks on a Country field', () => {
		const {getByText} = renderAddress();

		fireEvent.click(getByText('United States'));

		getByText('China');
		getByText('United Arab Emirates');
		getByText('United States');
	});

	it('displays Shanghai as a region option when the user selects PRC as the country', () => {
		const {getByDisplayValue, getByText} = renderAddress();

		fireEvent.click(getByText('United States'));
		fireEvent.change(getByDisplayValue('United States'), {
			target: {value: 'China'}
		});

		getByText('Shanghai');
	});

	it('displays Primary field as toggled on', () => {
		const {container, getByLabelText} = renderAddress();

		fireEvent.click(getByLabelText('primary'));

		expect(container.querySelector('input[type=checkbox]').checked).toBe(
			true
		);
	});

	it('displays the Save button as disabled until Country is filled out', () => {
		const {
			getAllByDisplayValue,
			getAllByText,
			getByLabelText,
			getByText
		} = render(
			<PermissionsProvider permissions={{updatePermission: true}}>
				<Address
					accountKey="key123"
					addFn={jest.fn()}
					address={{
						addressCountry: DASH,
						addressLocality: DASH,
						addressRegion: DASH,
						deletePostalAddressURL: '/',
						editPostalAddressURL: '/',
						id: '123',
						postalCode: DASH,
						primary: false,
						streetAddressLine1: DASH,
						streetAddressLine2: DASH,
						streetAddressLine3: DASH
					}}
					count={1}
					countryOptions={countryOptions}
				/>
			</PermissionsProvider>
		);

		fireEvent.click(getAllByText(DASH)[0]);

		expect(getByText('save').disabled).toBeTruthy();

		fireEvent.click(getByLabelText('addressPrimary'));

		expect(getByText('save').disabled).toBeTruthy();

		fireEvent.change(getAllByDisplayValue(DASH)[1], {
			target: {value: 'China'}
		});

		expect(getByText('save').disabled).toBeFalsy();
	});
});
