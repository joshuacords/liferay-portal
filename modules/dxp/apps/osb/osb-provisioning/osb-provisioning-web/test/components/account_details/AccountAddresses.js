/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {cleanup, fireEvent, render} from '@testing-library/react';
import React from 'react';

import AccountAddresses from '../../../src/main/resources/META-INF/resources/js/components/account_details/AccountAddresses';
import {PermissionsProvider} from '../../../src/main/resources/META-INF/resources/js/hooks/permissions';
import {DASH} from '../../../src/main/resources/META-INF/resources/js/utilities/constants';

const sampleAddresses = [
	{
		addressCountry: 'United States',
		addressLocality: 'Diamond Bar',
		addressRegion: 'California',
		deletePostalAddressURL: '/',
		editPostalAddressURL: '/',
		id: '123',
		postalCode: '91765',
		primary: false,
		streetAddressLine1: '1400 Montefino Ave',
		streetAddressLine2: '-',
		streetAddressLine3: '-'
	},
	{
		addressCountry: 'United Arab Emirates',
		addressLocality: 'Dubai Media City',
		addressRegion: '-',
		deletePostalAddressURL: '/',
		editPostalAddressURL: '/',
		id: '456',
		postalCode: '-',
		primary: false,
		streetAddressLine1: 'Building 8',
		streetAddressLine2: 'Office 207',
		streetAddressLine3: '-'
	},
	{
		addressCountry: 'P.R. China',
		addressLocality: 'Dalian',
		addressRegion: 'Liaoning',
		deletePostalAddressURL: '/',
		editPostalAddressURL: '/',
		id: '789',
		postalCode: '116023',
		primary: false,
		streetAddressLine1: '537 Huangpu Road Taide Building',
		streetAddressLine2: '1005 High-Tech Zone',
		streetAddressLine3: '-'
	}
];

function renderAccountAddress(addresses = [], permission = true) {
	return render(
		<PermissionsProvider permissions={{updatePermission: permission}}>
			<AccountAddresses
				accountKey="key123"
				addAddressURL="/add/address/url"
				addresses={addresses}
				countryOptions={[
					{
						active: true,
						countryRegions: [],
						name: 'afghanistan',
						zipRequired: true
					},
					{
						active: true,
						countryRegions: [
							{
								active: true,
								code: 'NSW',
								countryName: 'australia',
								name: 'New South Wales'
							},
							{
								active: true,
								code: 'QLD',
								countryName: 'australia',
								name: 'Queensland'
							},
							{
								active: true,
								code: 'VIC',
								countryName: 'australia',
								name: 'Victoria'
							}
						],
						name: 'australia',
						zipRequired: true
					}
				]}
			/>
		</PermissionsProvider>
	);
}

describe('AccountAddresses', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderAccountAddress(sampleAddresses);

		expect(container).toBeTruthy();
	});

	it('displays an addresses List Group with dashes for each field when no address was provided', () => {
		const {getAllByText} = renderAccountAddress();

		expect(getAllByText('-').length).toBe(7);
	});

	it('allows a new address to be added when no address exists yet', () => {
		const {getAllByText, getByText} = renderAccountAddress();

		fireEvent.click(getAllByText('-')[0]);

		getByText('save');
		getByText('cancel');
	});

	it('displays no Add or Delete button when no address was provided', () => {
		const {queryByLabelText} = renderAccountAddress();

		expect(queryByLabelText('add')).toBeFalsy();
		expect(queryByLabelText('delete')).toBeFalsy();
	});

	it('displays multiple address List Groups when multiple addresses are provided', () => {
		const {getByText} = renderAccountAddress(sampleAddresses);

		getByText('address 1');
		getByText('address 2');
		getByText('address 3');
	});

	describe('Full Editing Privilege', () => {
		it('displays an Add button for each provided addresses', () => {
			const {getAllByLabelText} = renderAccountAddress(sampleAddresses);

			expect(getAllByLabelText('add').length).toBe(3);
		});

		it('displays a Delete button for each provided addresses', () => {
			const {getAllByLabelText} = renderAccountAddress(sampleAddresses);

			expect(getAllByLabelText('delete').length).toBe(3);
		});

		it('allows address fields to be edited when at least one address was provided', () => {
			const {getByText} = renderAccountAddress(sampleAddresses);

			fireEvent.click(getByText('Diamond Bar'));

			getByText('save');
			getByText('cancel');
		});

		it('allows an address to be added when no addresses exist yet', () => {
			const {getAllByText, getByText} = renderAccountAddress();

			fireEvent.click(getAllByText(DASH)[0]);

			getByText('save');
			getByText('cancel');
		});
	});

	describe('Limited Editing Privilege', () => {
		it('does not display an add button for each provided addresses', () => {
			const {queryByLabelText} = renderAccountAddress(
				sampleAddresses,
				false
			);

			expect(queryByLabelText('add')).toBeFalsy();
		});

		it('does not display a delete button for each provided addresses', () => {
			const {queryByLabelText} = renderAccountAddress(
				sampleAddresses,
				false
			);

			expect(queryByLabelText('delete')).toBeFalsy();
		});

		it('does not allow address fields to be edited when at least one address was provided', () => {
			const {getByText, queryByText} = renderAccountAddress(
				sampleAddresses,
				false
			);

			fireEvent.click(getByText('Diamond Bar'));

			expect(queryByText('save')).toBeFalsy();
			expect(queryByText('cancel')).toBeFalsy();
		});

		it('does not allow an address to be added when none exist yet', () => {
			const {getAllByText, queryByText} = renderAccountAddress([], false);

			fireEvent.click(getAllByText(DASH)[0]);

			expect(queryByText('save')).toBeFalsy();
			expect(queryByText('cancel')).toBeFalsy();
		});
	});
});
