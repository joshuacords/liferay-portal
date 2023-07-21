/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {cleanup, fireEvent, render} from '@testing-library/react';
import React from 'react';

import EditSubscriptions from '../../../src/main/resources/META-INF/resources/js/components/subscriptions/EditSubscriptions';
import {
	PRODUCT_PURCHASE_STATUS_APPROVED,
	PRODUCT_PURCHASE_STATUS_CANCELLED
} from '../../../src/main/resources/META-INF/resources/js/utilities/constants';

function renderAddSubscriptions(props) {
	return render(
		<EditSubscriptions
			accountName="Test Account"
			addSubscriptions={true}
			details={[
				{
					originalEndDate: '2021-12-08',
					productKey: 'KOR-35746',
					productName: 'Product A',
					startDate: '2020-12-08'
				}
			]}
			editProductPurchasesURL="/edit/product/purchases/url"
			redirect="/redirect/to/subscriptions/tab"
			selectProductsActionURL="/select/products/url"
			selectProductsRenderURL="/select/products/url"
			sizing={[1, 2, 3, 4]}
			status={[
				PRODUCT_PURCHASE_STATUS_APPROVED,
				PRODUCT_PURCHASE_STATUS_CANCELLED
			]}
			{...props}
		/>
	);
}

function renderEditSubscriptions(props) {
	return render(
		<EditSubscriptions
			accountName="Test Account"
			addSubscriptions={false}
			details={[
				{
					endDate: '2022-01-20',
					externalLinkKey: 'KOR-35727',
					key: 'KOR-38323',
					originalEndDate: '2021-12-21',
					perpetual: false,
					productName: 'Product B',
					quantity: 1,
					salesforceOpportunityKey: 'salesForceKey123',
					sizing: 1,
					startDate: '2020-12-21',
					status: 'Approved'
				}
			]}
			editProductPurchasesURL="/edit/product/purchases/url"
			redirect="/redirect/to/subscriptions/tab"
			selectProductsActionURL="/select/products/url"
			selectProductsRenderURL="/select/products/url"
			sizing={[1, 2, 3, 4]}
			status={[
				PRODUCT_PURCHASE_STATUS_APPROVED,
				PRODUCT_PURCHASE_STATUS_CANCELLED
			]}
			{...props}
		/>
	);
}

describe('EditSubscriptions', () => {
	afterEach(cleanup);

	describe('Add Subscriptions', () => {
		it('renders', () => {
			const {container} = renderAddSubscriptions();

			expect(container).toBeTruthy();
		});

		it('renders a subtext to describe the action', () => {
			const {getByText} = renderAddSubscriptions();

			expect(getByText('configure-subscriptions')).toBeTruthy();
		});

		it('renders a Select button', () => {
			const {getByText} = renderAddSubscriptions();

			expect(getByText('select')).toBeTruthy();
		});

		it('renders a Cancel button', () => {
			const {getByText} = renderAddSubscriptions();

			expect(getByText('cancel')).toBeTruthy();
		});

		it('displays an error message if the user selects a Start Date that is later than the End Date', () => {
			const {
				getAllByPlaceholderText,
				getByText
			} = renderAddSubscriptions();

			const firstStartDate = getAllByPlaceholderText('YYYY-MM-DD')[0];

			fireEvent.change(firstStartDate, {
				target: {value: '2022-12-08'}
			});

			getByText('invalid-date');
			getByText('please-make-sure-the-start-date-is-before-the-end-date');
		});

		it('allows the user to add a duplicate subscription', () => {
			const {getAllByText} = renderAddSubscriptions({
				details: [
					{
						originalEndDate: '2021-01-07',
						productKey: 'KOR-11111',
						productName: 'Product B',
						startDate: '2020-01-07'
					},
					{
						originalEndDate: '2021-01-07',
						productKey: 'KOR-11111',
						productName: 'Product B',
						startDate: '2020-01-07'
					}
				]
			});

			expect(getAllByText('Product B').length).toBe(2);
		});

		describe('Save Button Interaction', () => {
			it('renders a disabled Save button by default', () => {
				const {getByText} = renderAddSubscriptions();

				expect(getByText('save').disabled).toBeTruthy();
			});

			describe('Single Subscription', () => {
				it('enables the Save button once a Salesforce Opportunity Key is entered', () => {
					const {
						getByLabelText,
						getByText
					} = renderAddSubscriptions();

					fireEvent.change(
						getByLabelText('salesforce-opportunity-key'),
						{
							target: {value: 'test'}
						}
					);

					expect(getByText('save').disabled).toBeFalsy();
				});

				it('disables the Save button if a date field is invalid', () => {
					const {
						getAllByPlaceholderText,
						getByText
					} = renderAddSubscriptions();

					const firstStartDate = getAllByPlaceholderText(
						'YYYY-MM-DD'
					)[0];

					fireEvent.change(firstStartDate, {
						target: {value: 'invalid'}
					});

					expect(getByText('save').disabled).toBeTruthy();
				});

				it('disables the Save button if a date field is left empty', () => {
					const {
						getAllByPlaceholderText,
						getByText
					} = renderAddSubscriptions();

					const firstStartDate = getAllByPlaceholderText(
						'YYYY-MM-DD'
					)[0];

					fireEvent.change(firstStartDate, {
						target: {value: ''}
					});

					expect(getByText('save').disabled).toBeTruthy();
				});

				it('disables the Save button if a start date is set to be after the End Date', () => {
					const {
						getAllByPlaceholderText,
						getByText
					} = renderAddSubscriptions();

					const firstStartDate = getAllByPlaceholderText(
						'YYYY-MM-DD'
					)[0];

					fireEvent.change(firstStartDate, {
						target: {value: '2022-12-08'}
					});

					expect(getByText('save').disabled).toBeTruthy();
				});
			});

			describe('Multiple Subscriptions', () => {
				it('enables the Save button once a Salesforce Opportunity Key is entered in the bulk input', () => {
					const {getByLabelText, getByText} = renderAddSubscriptions({
						details: [
							{
								originalEndDate: '2021-12-08',
								productKey: 'KOR-35746',
								productName: 'Product A',
								startDate: '2020-12-08'
							},
							{
								originalEndDate: '2021-12-09',
								productKey: 'KOR-35747',
								productName: 'Product B',
								startDate: '2020-12-09'
							}
						]
					});

					fireEvent.change(
						getByLabelText('salesforce-opportunity-key-bulk-input'),
						{
							target: {value: 'test'}
						}
					);

					expect(getByText('save').disabled).toBeFalsy();
				});

				it('disables the Save button if date entered in one of the bulk input date fields is invalid', () => {
					const {
						getAllByPlaceholderText,
						getByText
					} = renderAddSubscriptions({
						details: [
							{
								originalEndDate: '2021-12-08',
								productKey: 'KOR-35746',
								productName: 'Product A',
								startDate: '2020-12-08'
							},
							{
								originalEndDate: '2021-12-09',
								productKey: 'KOR-35747',
								productName: 'Product B',
								startDate: '2020-12-09'
							}
						]
					});

					const bulkStartDate = getAllByPlaceholderText(
						'YYYY-MM-DD'
					)[0];

					fireEvent.change(bulkStartDate, {
						target: {value: 'invalid'}
					});

					expect(getByText('save').disabled).toBeTruthy();
				});

				it('disables the Save button if a bulk input date field is left empty', () => {
					const {
						getAllByPlaceholderText,
						getByText
					} = renderAddSubscriptions();

					const bulkStartDate = getAllByPlaceholderText(
						'YYYY-MM-DD'
					)[0];

					fireEvent.change(bulkStartDate, {
						target: {value: ''}
					});

					expect(getByText('save').disabled).toBeTruthy();
				});
			});
		});
	});

	describe('Edit Subscriptions', () => {
		it('renders', () => {
			const {container} = renderEditSubscriptions();

			expect(container).toBeTruthy();
		});

		it('renders sub heading showing the editing step', () => {
			const {getByText} = renderEditSubscriptions();

			expect(getByText('edit-details')).toBeTruthy();
			expect(getByText('step-2-of-2')).toBeTruthy();
		});

		it('renders a Previous button if a backURL is provided', () => {
			const {getByText} = renderEditSubscriptions({
				backURL: '/back/to/previous/page'
			});

			expect(getByText('previous'));
		});

		describe('Save Button Interaction', () => {
			it('renders an enabled Save button by default', () => {
				const {getByText} = renderEditSubscriptions();

				expect(getByText('save').disabled).toBeFalsy();
			});

			describe('Single Subscription', () => {
				it('disables the Save button if a Salesforce Opportunity key is removed', () => {
					const {
						getByLabelText,
						getByText
					} = renderEditSubscriptions();

					expect(getByText('save').disabled).toBeFalsy();

					fireEvent.change(
						getByLabelText('salesforce-opportunity-key'),
						{
							target: {value: ''}
						}
					);

					expect(getByText('save').disabled).toBeTruthy();
				});

				it('disables the Save button if a date field is invalid', () => {
					const {
						getAllByPlaceholderText,
						getByText
					} = renderEditSubscriptions();

					const firstStartDate = getAllByPlaceholderText(
						'YYYY-MM-DD'
					)[0];

					fireEvent.change(firstStartDate, {
						target: {value: 'invalid'}
					});

					expect(getByText('save').disabled).toBeTruthy();
				});

				it('disables the Save button if a date field is left empty', () => {
					const {
						getAllByPlaceholderText,
						getByText
					} = renderEditSubscriptions();

					const firstStartDate = getAllByPlaceholderText(
						'YYYY-MM-DD'
					)[0];

					fireEvent.change(firstStartDate, {
						target: {value: ''}
					});

					expect(getByText('save').disabled).toBeTruthy();
				});

				it('disables the Save button if a start date is set to be after the End Date', () => {
					const {
						getAllByPlaceholderText,
						getByText
					} = renderEditSubscriptions();

					const firstStartDate = getAllByPlaceholderText(
						'YYYY-MM-DD'
					)[0];

					fireEvent.change(firstStartDate, {
						target: {value: '2024-12-08'}
					});

					expect(getByText('save').disabled).toBeTruthy();
				});

				it('disables the Save button if Grace Period field is empty', () => {
					const {
						getByLabelText,
						getByText
					} = renderEditSubscriptions();

					fireEvent.change(getByLabelText('grace-period'), {
						target: {value: ''}
					});

					expect(getByText('save').disabled).toBeTruthy();
				});

				it('disables the Save button if Grace Period entered is not a number', () => {
					const {
						getByLabelText,
						getByText
					} = renderEditSubscriptions();

					fireEvent.change(getByLabelText('grace-period'), {
						target: {value: 'test'}
					});

					expect(getByText('save').disabled).toBeTruthy();
				});

				it('disables the Save button and shows the Invalid Date warning if Grace Period entered is negative', () => {
					const {
						getByLabelText,
						getByText
					} = renderEditSubscriptions();

					fireEvent.change(getByLabelText('grace-period'), {
						target: {value: '-1'}
					});

					getByText('invalid-date');
					expect(getByText('save').disabled).toBeTruthy();
				});
			});

			describe('Multiple Subscriptions', () => {
				it('disables the Save button if date entered in one of the bulk input date fields is invalid', () => {
					const {
						getAllByPlaceholderText,
						getByText
					} = renderEditSubscriptions({
						details: [
							{
								endDate: '2022-01-20',
								externalLinkKey: 'KOR-35727',
								key: 'KOR-38323',
								originalEndDate: '2021-12-21',
								perpetual: false,
								productName: 'Product B',
								quantity: 1,
								salesforceOpportunityKey: 'salesForceKey123',
								sizing: 1,
								startDate: '2020-12-21',
								status: 'Approved'
							},
							{
								endDate: '2022-01-20',
								externalLinkKey: 'KOR-35728',
								key: 'KOR-38324',
								originalEndDate: '2021-12-21',
								perpetual: false,
								productName: 'Product C',
								quantity: 1,
								salesforceOpportunityKey: 'salesForceKey124',
								sizing: 1,
								startDate: '2020-12-21',
								status: 'Approved'
							}
						]
					});

					const bulkStartDate = getAllByPlaceholderText(
						'YYYY-MM-DD'
					)[0];

					fireEvent.change(bulkStartDate, {
						target: {value: 'invalid'}
					});

					expect(getByText('save').disabled).toBeTruthy();
				});

				it('disables the Save button if a bulk input date field is left empty', () => {
					const {
						getAllByPlaceholderText,
						getByText
					} = renderEditSubscriptions({
						details: [
							{
								endDate: '2022-01-20',
								externalLinkKey: 'KOR-35727',
								key: 'KOR-38323',
								originalEndDate: '2021-12-21',
								perpetual: false,
								productName: 'Product B',
								quantity: 1,
								salesforceOpportunityKey: 'salesForceKey123',
								sizing: 1,
								startDate: '2020-12-21',
								status: 'Approved'
							},
							{
								endDate: '2022-01-20',
								externalLinkKey: 'KOR-35728',
								key: 'KOR-38324',
								originalEndDate: '2021-12-21',
								perpetual: false,
								productName: 'Product C',
								quantity: 1,
								salesforceOpportunityKey: 'salesForceKey124',
								sizing: 1,
								startDate: '2020-12-21',
								status: 'Approved'
							}
						]
					});

					const bulkStartDate = getAllByPlaceholderText(
						'YYYY-MM-DD'
					)[0];

					fireEvent.change(bulkStartDate, {
						target: {value: ''}
					});

					expect(getByText('save').disabled).toBeTruthy();
				});
			});
		});
	});
});
