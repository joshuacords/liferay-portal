/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {cleanup, fireEvent, render} from '@testing-library/react';
import React from 'react';

import Subscriptions from '../../../src/main/resources/META-INF/resources/js/components/subscriptions/Subscriptions';
import {SubscriptionsProvider} from '../../../src/main/resources/META-INF/resources/js/hooks/subscriptions';
import {
	ADD_SUBSCRIPTIONS,
	EDIT_SUBSCRIPTIONS,
	PRODUCT_PURCHASE_STATUS_APPROVED,
	PRODUCT_PURCHASE_STATUS_CANCELLED
} from '../../../src/main/resources/META-INF/resources/js/utilities/constants';

function mockAddSubscriptions() {
	return [
		{
			endDate: '2021-12-08',
			productKey: 'KOR-35735',
			productName: 'Product A',
			startDate: '2020-12-08'
		},
		{
			endDate: '2021-12-10',
			productKey: 'KOR-35803',
			productName: 'Product B',
			startDate: '2020-12-10'
		},
		{
			endDate: '2021-12-12',
			productKey: 'KOR-35746',
			productName: 'Product C',
			startDate: '2020-12-12'
		},
		{
			endDate: '2021-12-14',
			productKey: 'KOR-35757',
			productName: 'Product D',
			startDate: '2020-12-14'
		}
	];
}

function mockEditSubscriptions() {
	return [
		{
			endDate: '2022-01-20',
			externalLinkKey: 'KOR-35727',
			key: 'KOR-38323',
			originalEndDate: '2021-12-21',
			perpetual: true,
			productName: 'Product E',
			quantity: 1,
			salesforceOpportunityKey: 'salesForceKey123',
			sizing: 1,
			startDate: '2020-12-21',
			status: 'Approved'
		}
	];
}

function renderSubscriptions({
	subscriptions = mockAddSubscriptions(),
	...props
} = {}) {
	return render(
		<SubscriptionsProvider initialSubscriptions={subscriptions}>
			<Subscriptions
				accountName="Test Account"
				instanceSizes={[1, 2, 3, 4]}
				statusOptions={[
					PRODUCT_PURCHASE_STATUS_APPROVED,
					PRODUCT_PURCHASE_STATUS_CANCELLED
				]}
				subscriptionsType={ADD_SUBSCRIPTIONS}
				validateDateFormat={jest.fn()}
				{...props}
			/>
		</SubscriptionsProvider>
	);
}

describe('Subscriptions', () => {
	afterEach(cleanup);

	describe('New Subscriptions', () => {
		it('renders', () => {
			const {container} = renderSubscriptions();

			expect(container).toBeTruthy();
		});

		it('displays a delete subscription icon for each of the subscriptions', () => {
			const {getAllByLabelText} = renderSubscriptions();

			const allDeleteIcons = getAllByLabelText(
				'delete-subscription-icon'
			);

			expect(allDeleteIcons[0]).toBeTruthy();
			expect(allDeleteIcons.length).toBe(4);
		});

		it('removes subscriptions when the delete button is clicked', () => {
			const {getAllByLabelText, getByText} = renderSubscriptions();

			const allDeleteIcons = getAllByLabelText(
				'delete-subscription-icon'
			);

			allDeleteIcons.forEach(icon => {
				fireEvent.click(icon);
			});

			expect(getByText('Product D'));
		});

		it('displays a disabled delete button when there is only one subscription', () => {
			const {getAllByLabelText, getByLabelText} = renderSubscriptions();

			const allDeleteIcons = getAllByLabelText(
				'delete-subscription-icon'
			);

			allDeleteIcons.forEach(icon => {
				fireEvent.click(icon);
			});

			expect(
				getByLabelText('delete-subscription-icon').parentElement
					.disabled
			).toBeTruthy();
		});

		it('displays the product name for each of the selected products', () => {
			const {getByText} = renderSubscriptions();

			expect(getByText('Product A')).toBeTruthy();
			expect(getByText('Product B')).toBeTruthy();
			expect(getByText('Product C')).toBeTruthy();
			expect(getByText('Product D')).toBeTruthy();
		});

		it('displays the account name for each of the subscriptions and the Bulk Input', () => {
			const {getAllByText} = renderSubscriptions();

			const allAccountNames = getAllByText('Test Account');

			expect(allAccountNames.length).toBe(5);
		});

		it('removes a subscription when the delete icon for that subscription is clicked', () => {
			const {
				getAllByLabelText,
				getByText,
				queryByText
			} = renderSubscriptions();

			fireEvent.click(getAllByLabelText('delete-subscription-icon')[0]);

			expect(queryByText('Product A')).toBeFalsy();
			expect(getByText('Product B')).toBeTruthy();
			expect(getByText('Product C')).toBeTruthy();
			expect(getByText('Product D')).toBeTruthy();
		});

		it('disables date fields after checking the perpetual checkbox', () => {
			const {getAllByPlaceholderText, getAllByRole} = renderSubscriptions(
				{
					subscriptions: [
						{
							endDate: '2021-12-08',
							productKey: 'KOR-35735',
							productName: 'Product A',
							startDate: '2020-12-08'
						}
					]
				}
			);

			const dateFields = getAllByPlaceholderText('YYYY-MM-DD');
			const perpetualCheckboxes = getAllByRole('checkbox');

			expect(dateFields[0].disabled).toBeFalsy();
			expect(dateFields[1].disabled).toBeFalsy();

			fireEvent.click(perpetualCheckboxes[0]);

			expect(dateFields[0].disabled).toBeTruthy();
			expect(dateFields[1].disabled).toBeTruthy();
		});

		it('displays a warning to the user when an invalid date is entered in the date input', () => {
			const {getAllByPlaceholderText} = renderSubscriptions({
				subscriptions: [
					{
						endDate: '2021-12-08',
						productKey: 'KOR-35735',
						productName: 'Product A',
						startDate: '2020-12-08'
					}
				]
			});

			const dateFields = getAllByPlaceholderText('YYYY-MM-DD');

			fireEvent.change(dateFields[0], {
				target: {value: '2021-02-29'}
			});

			expect(dateFields[1].value).toBe('Invalid Date');
		});
	});

	describe('Existing Subscriptions', () => {
		it('displays the start date correctly in UTC', () => {
			const {getAllByPlaceholderText} = renderSubscriptions({
				subscriptions: mockEditSubscriptions(),
				subscriptionsType: EDIT_SUBSCRIPTIONS
			});

			const dateFields = getAllByPlaceholderText('YYYY-MM-DD');

			expect(dateFields[0].value).toBe('2020-12-21');
		});

		it('displays the date fields as disabled if perpetual checkbox is checked', () => {
			const {
				getAllByPlaceholderText,
				getByLabelText
			} = renderSubscriptions({
				subscriptions: mockEditSubscriptions(),
				subscriptionsType: EDIT_SUBSCRIPTIONS
			});

			const dateFields = getAllByPlaceholderText('YYYY-MM-DD');

			expect(
				getByLabelText('perpetual-subscription').checked
			).toBeTruthy();
			expect(dateFields[0].disabled).toBeTruthy();
			expect(dateFields[1].disabled).toBeTruthy();
			expect(getByLabelText('grace-period').disabled).toBeTruthy();
		});

		it('displays the account name for each of the subscriptions and the Bulk Input', () => {
			const {getAllByText} = renderSubscriptions({
				subscriptions: [
					{
						endDate: '2022-01-20',
						key: 'KOR-38323',
						originalEndDate: '2021-12-21',
						perpetual: true,
						productName: 'Product E',
						quantity: 1,
						salesforceOpportunityKey: 'salesForceKey123',
						sizing: 1,
						startDate: '2020-12-21',
						status: 'Approved'
					},
					{
						endDate: '2022-01-20',
						key: 'KOR-38323',
						originalEndDate: '2021-12-21',
						perpetual: true,
						productName: 'Product E',
						quantity: 1,
						salesforceOpportunityKey: 'salesForceKey456',
						sizing: 1,
						startDate: '2020-12-21',
						status: 'Approved'
					}
				],
				subscriptionsType: EDIT_SUBSCRIPTIONS
			});

			const allAccountNames = getAllByText('Test Account');

			expect(allAccountNames.length).toBe(3);
		});
	});

	describe('Subscriptions with Bulk Edit', () => {
		it('does not display Bulk Input when adding only one subscription', () => {
			const {getAllByText} = renderSubscriptions({
				subscriptions: [
					{
						endDate: '2021-12-08',
						productKey: 'KOR-35735',
						productName: 'Product A',
						startDate: '2020-12-08'
					}
				]
			});

			const allAccountNames = getAllByText('Test Account');

			expect(allAccountNames.length).toBe(1);
		});

		it('does not display Bulk Input when editing only one subscription', () => {
			const {getAllByText} = renderSubscriptions({
				subscriptions: mockEditSubscriptions(),
				subscriptionsType: EDIT_SUBSCRIPTIONS
			});

			const allAccountNames = getAllByText('Test Account');

			expect(allAccountNames.length).toBe(1);
		});

		it('updates all subscriptions values for a given field when its Bulk Edit field is modified', () => {
			const {
				getAllByDisplayValue,
				getByLabelText,
				getByText,
				queryByDisplayValue
			} = renderSubscriptions({
				subscriptions: [
					{
						endDate: '2022-01-20',
						key: 'KOR-38323',
						originalEndDate: '2021-12-21',
						perpetual: true,
						productName: 'Product E',
						quantity: 1,
						salesforceOpportunityKey: 'salesForceKey123',
						sizing: 1,
						startDate: '2020-12-21',
						status: 'Approved'
					},
					{
						endDate: '2022-01-20',
						key: 'KOR-38323',
						originalEndDate: '2021-12-21',
						perpetual: true,
						productName: 'Product E',
						quantity: 1,
						salesforceOpportunityKey: 'salesForceKey456',
						sizing: 1,
						startDate: '2020-12-21',
						status: 'Approved'
					}
				],
				subscriptionsType: EDIT_SUBSCRIPTIONS
			});

			expect(queryByDisplayValue('test')).toBeFalsy();

			fireEvent.click(getByText('varied-data'));
			fireEvent.change(
				getByLabelText('salesforce-opportunity-key-bulk-input'),
				{
					target: {value: 'test'}
				}
			);

			expect(getAllByDisplayValue('test').length).toBe(3);
		});

		it('shows changes in the Bulk Input field when the corresponding values for subscriptions change', () => {
			const {
				getAllByDisplayValue,
				getAllByLabelText,
				getByText,
				queryByText
			} = renderSubscriptions({
				subscriptions: [
					{
						endDate: '2022-01-20',
						key: 'KOR-38323',
						originalEndDate: '2021-12-21',
						perpetual: true,
						productName: 'Product E',
						quantity: 1,
						salesforceOpportunityKey: 'salesForceKey123',
						sizing: 3,
						startDate: '2020-12-21',
						status: 'Approved'
					},
					{
						endDate: '2022-01-20',
						key: 'KOR-38323',
						originalEndDate: '2021-12-21',
						perpetual: true,
						productName: 'Product E',
						quantity: 1,
						salesforceOpportunityKey: 'salesForceKey123',
						sizing: 3,
						startDate: '2020-12-21',
						status: 'Approved'
					}
				],
				subscriptionsType: EDIT_SUBSCRIPTIONS
			});

			expect(getAllByDisplayValue('1').length).toBe(3);

			fireEvent.change(getAllByLabelText('purchased')[0], {
				target: {value: '2'}
			});

			expect(getByText('varied-data')).toBeTruthy();
			expect(getAllByDisplayValue('1').length).toBe(1);

			fireEvent.change(getAllByLabelText('purchased')[1], {
				target: {value: '2'}
			});

			expect(queryByText('varied-data')).toBeFalsy();
			expect(getAllByDisplayValue('2').length).toBe(3);
		});
	});
});
