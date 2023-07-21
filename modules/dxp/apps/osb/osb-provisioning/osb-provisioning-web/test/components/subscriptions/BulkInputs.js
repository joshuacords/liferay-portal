/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {cleanup, fireEvent, render} from '@testing-library/react';
import React from 'react';

import BulkInputs from '../../../src/main/resources/META-INF/resources/js/components/subscriptions/BulkInputs';
import {SubscriptionsProvider} from '../../../src/main/resources/META-INF/resources/js/hooks/subscriptions';
import {
	EDIT_SUBSCRIPTIONS,
	PRODUCT_PURCHASE_STATUS_APPROVED,
	PRODUCT_PURCHASE_STATUS_CANCELLED
} from '../../../src/main/resources/META-INF/resources/js/utilities/constants';

function mockEditSubscriptions() {
	return [
		{
			endDate: '2022-01-20',
			externalLinkKey: 'KOR-35727',
			key: 'KOR-38322',
			originalEndDate: '2021-12-20',
			perpetual: true,
			productName: 'Product A',
			quantity: 1,
			salesforceOpportunityKey: 'salesForceKey123',
			sizing: 1,
			startDate: '2020-12-20',
			status: 'Cancelled'
		},
		{
			endDate: '2022-01-21',
			externalLinkKey: 'KOR-35727',
			key: 'KOR-38323',
			originalEndDate: '2021-12-21',
			perpetual: true,
			productName: 'Product B',
			quantity: 2,
			salesforceOpportunityKey: 'salesForceKey456',
			sizing: 2,
			startDate: '2020-12-21',
			status: 'Approved'
		}
	];
}

function renderBulkInputs({
	subscriptions = mockEditSubscriptions(),
	...props
} = {}) {
	return render(
		<table>
			<tbody>
				<SubscriptionsProvider initialSubscriptions={subscriptions}>
					<BulkInputs
						accountName="Test Account"
						instanceSizes={[1, 2, 3, 4]}
						statusOptions={[
							PRODUCT_PURCHASE_STATUS_APPROVED,
							PRODUCT_PURCHASE_STATUS_CANCELLED
						]}
						subscriptionsType={EDIT_SUBSCRIPTIONS}
						updateBulkGracePeriod={jest.fn()}
						{...props}
					/>
				</SubscriptionsProvider>
			</tbody>
		</table>
	);
}

describe('Bulk Input', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderBulkInputs();

		expect(container).toBeTruthy();
	});

	it('renders Varied Data for Salesforce Opportunity Key, Purchased, Instant Size, and Status when the subscriptions to be edited contain different values for these fields', () => {
		const {getAllByText} = renderBulkInputs();

		expect(getAllByText('varied-data').length).toBe(4);
	});

	it('renders Varied Data for the two date fields when the subscriptions to be edited contain different values for these fields', () => {
		const {getAllByPlaceholderText} = renderBulkInputs();

		expect(getAllByPlaceholderText('varied-data').length).toBe(2);
	});

	it("renders the field's value in the Bulk Input when the subscriptions contain identical values for the same field name", () => {
		const {
			getAllByDisplayValue,
			getByDisplayValue,
			getByLabelText
		} = renderBulkInputs({
			subscriptions: [
				{
					endDate: '2021-12-24',
					externalLinkKey: 'KOR-35727',
					key: 'KOR-38323',
					originalEndDate: '2021-12-21',
					perpetual: true,
					productName: 'Product B',
					quantity: 2,
					salesforceOpportunityKey: 'salesForceKey456',
					sizing: 1,
					startDate: '2020-12-21',
					status: 'Approved'
				},
				{
					endDate: '2021-12-24',
					externalLinkKey: 'KOR-35727',
					key: 'KOR-38323',
					originalEndDate: '2021-12-21',
					perpetual: true,
					productName: 'Product B',
					quantity: 2,
					salesforceOpportunityKey: 'salesForceKey456',
					sizing: 1,
					startDate: '2020-12-21',
					status: 'Approved'
				}
			]
		});

		// Clay Date Picker always displays two inputs for the same date

		getByDisplayValue('salesForceKey456');
		getByDisplayValue('2'); // quantity
		expect(getAllByDisplayValue('2020-12-21').length).toBe(2);
		expect(getByLabelText('grace-period-bulk-input').value).toBe('3'); // grace period
		getByDisplayValue('1'); // sizing
		expect(getAllByDisplayValue('2021-12-21').length).toBe(2);
		getByDisplayValue('Approved');
	});

	it('renders Varied Data for the two date fields when subscriptions do not have identical perpetual values even when the dates are identical', () => {
		const {
			getAllByPlaceholderText,
			queryAllByDisplayValue
		} = renderBulkInputs({
			subscriptions: [
				{
					endDate: '2022-01-21',
					externalLinkKey: 'KOR-35727',
					key: 'KOR-38323',
					originalEndDate: '2021-12-21',
					perpetual: false,
					productName: 'Product B',
					quantity: 1,
					salesforceOpportunityKey: 'salesForceKey456',
					sizing: 2,
					startDate: '2020-12-21',
					status: 'Approved'
				},
				{
					endDate: '2022-01-21',
					externalLinkKey: 'KOR-35727',
					key: 'KOR-38323',
					originalEndDate: '2021-12-21',
					perpetual: true,
					productName: 'Product B',
					quantity: 2,
					salesforceOpportunityKey: 'salesForceKey456',
					sizing: 1,
					startDate: '2020-12-21',
					status: 'Approved'
				}
			]
		});

		expect(queryAllByDisplayValue('2020-12-21').length).toBeFalsy();
		expect(queryAllByDisplayValue('2021-12-21').length).toBeFalsy();
		expect(getAllByPlaceholderText('varied-data').length).toBe(2);
	});

	it('reveals an input for user to enter data when Varied Data is clicked', () => {
		const {getAllByText, getByLabelText} = renderBulkInputs();

		fireEvent.click(getAllByText('varied-data')[0]);

		getByLabelText('salesforce-opportunity-key-bulk-input');
		expect(getAllByText('varied-data').length).toBe(3);
	});

	it('displays date bulk inputs and Grace Period bulk input as disabled when Perpetual Subscription is checked', () => {
		const {getAllByPlaceholderText, getByLabelText} = renderBulkInputs();

		const dateFields = getAllByPlaceholderText('varied-data');

		expect(
			getByLabelText('perpetual-subscription-bulk-input').checked
		).toBeTruthy();

		expect(dateFields[0].disabled).toBeTruthy();
		expect(dateFields[1].disabled).toBeTruthy();
		expect(getByLabelText('grace-period-bulk-input').disabled).toBeTruthy();
	});

	it('displays date bulk inputs and Grace Period bulk input as enabled when Perpetual Subscription is unchecked', () => {
		const {getAllByPlaceholderText, getByLabelText} = renderBulkInputs({
			subscriptions: [
				{
					endDate: '2022-01-20',
					externalLinkKey: 'KOR-35727',
					key: 'KOR-38322',
					originalEndDate: '2021-12-20',
					perpetual: false,
					productName: 'Product A',
					quantity: 1,
					salesforceOpportunityKey: 'salesForceKey123',
					sizing: 1,
					startDate: '2020-12-20',
					status: 'Cancelled'
				},
				{
					endDate: '2022-01-21',
					externalLinkKey: 'KOR-35727',
					key: 'KOR-38323',
					originalEndDate: '2021-12-21',
					perpetual: false,
					productName: 'Product B',
					quantity: 2,
					salesforceOpportunityKey: 'salesForceKey456',
					sizing: 1,
					startDate: '2020-12-21',
					status: 'Approved'
				}
			]
		});

		const dateFields = getAllByPlaceholderText('varied-data');

		expect(
			getByLabelText('perpetual-subscription-bulk-input').checked
		).toBeFalsy();

		expect(dateFields[0].disabled).toBeFalsy();
		expect(dateFields[1].disabled).toBeFalsy();
		expect(getByLabelText('grace-period-bulk-input').disabled).toBeFalsy();
	});

	it('displays the indeterminate state of Perpetual Subscription when subscriptions contain varying values', () => {
		const {getByLabelText} = renderBulkInputs({
			subscriptions: [
				{
					endDate: '2022-01-21',
					externalLinkKey: 'KOR-35727',
					key: 'KOR-38323',
					originalEndDate: '2021-12-21',
					perpetual: false,
					productName: 'Product B',
					quantity: 1,
					salesforceOpportunityKey: 'salesForceKey456',
					sizing: 2,
					startDate: '2020-12-21',
					status: 'Approved'
				},
				{
					endDate: '2022-01-21',
					externalLinkKey: 'KOR-35727',
					key: 'KOR-38323',
					originalEndDate: '2021-12-21',
					perpetual: true,
					productName: 'Product B',
					quantity: 2,
					salesforceOpportunityKey: 'salesForceKey456',
					sizing: 1,
					startDate: '2020-12-21',
					status: 'Approved'
				}
			]
		});

		expect(
			getByLabelText('perpetual-subscription-bulk-input').indeterminate
		).toBeTruthy();
	});
});
