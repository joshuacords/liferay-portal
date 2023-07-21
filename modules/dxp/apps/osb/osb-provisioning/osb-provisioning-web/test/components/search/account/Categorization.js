/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {cleanup, fireEvent, render} from '@testing-library/react';
import React from 'react';

import Categorization from '../../../../src/main/resources/META-INF/resources/js/components/search/account/Categorization';

function renderCategorization() {
	return render(
		<Categorization
			activeSLANames={[
				'Gold Subscription',
				'Limited Subscription',
				'Platinum Subscription',
				'Premium Subscription',
				'Silver Subscription'
			]}
			regionNames={[
				'Australia',
				'Brazil',
				'China',
				'Global',
				'Hungary',
				'India',
				'Japan',
				'Spain',
				'United States'
			]}
			subscriptionStateNames={[
				'Active',
				'Cancelled',
				'Expired',
				'Unactivated',
				'N/A'
			]}
			tierNames={['OEM', 'Premier', 'Regular', 'Strategic']}
		/>
	);
}

describe('Account Search Categorization', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderCategorization();

		expect(container).toBeTruthy();
	});

	it('displays a Partner field', () => {
		const {getByText} = renderCategorization();

		getByText('partner');
	});

	it('displays a Provides FLS field', () => {
		const {getByText} = renderCategorization();

		getByText('provides-fls');
	});

	it('displays a Receives FLS field', () => {
		const {getByText} = renderCategorization();

		getByText('receives-fls');
	});

	it('displays an Internal field', () => {
		const {getByText} = renderCategorization();

		getByText('internal');
	});

	it('displays a Tier field', () => {
		const {getByText} = renderCategorization();

		getByText('tier');
		getByText('OEM');
		getByText('Premier');
		getByText('Regular');
		getByText('Strategic');
	});

	it('sets the input value as a comma deliminated list of clicked items when multiple checkboxes in a group are checked', () => {
		const {container, getByText} = renderCategorization();

		fireEvent.click(getByText('OEM'));
		fireEvent.click(getByText('Regular'));
		fireEvent.click(getByText('Strategic'));

		expect(
			container.querySelector(
				'input[name = "_com_liferay_osb_provisioning_web_portlet_AccountsPortlet_tiers"]'
			).value
		).toBe('OEM,Regular,Strategic');

		fireEvent.click(getByText('Strategic'));

		expect(
			container.querySelector(
				'input[name = "_com_liferay_osb_provisioning_web_portlet_AccountsPortlet_tiers"]'
			).value
		).toBe('OEM,Regular');
	});

	it('displays a Subscription State field', () => {
		const {getByText} = renderCategorization();

		getByText('subscription-state');
		getByText('Active');
		getByText('Cancelled');
		getByText('Expired');
		getByText('Unactivated');
		getByText('N/A');
	});

	it('displays all except for the N/A option of the Subscription State field as checked', () => {
		const {getByLabelText} = renderCategorization();

		expect(getByLabelText('Active').checked).toBeTruthy();
		expect(getByLabelText('Cancelled').checked).toBeTruthy();
		expect(getByLabelText('Expired').checked).toBeTruthy();
		expect(getByLabelText('Unactivated').checked).toBeTruthy();
		expect(getByLabelText('N/A').checked).toBeFalsy();
	});

	it('displays a Subscription Level field', () => {
		const {getByText} = renderCategorization();

		getByText('subscription-level');
		getByText('Gold');
		getByText('Limited');
		getByText('Platinum');
		getByText('Premium');
		getByText('Silver');
	});

	it('displays a Support Region field', () => {
		const {getByText} = renderCategorization();

		getByText('support-region');
		getByText('Australia');
		getByText('Brazil');
		getByText('China');
		getByText('Global');
		getByText('Hungary');
		getByText('India');
		getByText('Japan');
		getByText('Spain');
		getByText('United States');
	});
});
