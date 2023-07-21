/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {cleanup, fireEvent, render, wait} from '@testing-library/react';
import React from 'react';

import ExtendAllSubscriptions from '../../../src/main/resources/META-INF/resources/js/components/subscriptions/ExtendAllSubscriptions';

function renderExtendAllSubscriptions() {
	return render(
		<div>
			<button
				onClick={() => {
					const event = new CustomEvent(
						'extendAllActiveSubscriptions',
						{
							detail: {
								modalVisible: true
							}
						}
					);

					window.dispatchEvent(event);
				}}
			>
				Extend
			</button>

			<ExtendAllSubscriptions
				extendActiveSubscriptionsURL="/extend/url"
				latestActiveSubscriptionEndDate="2021-10-21"
			/>
		</div>
	);
}

describe('ExtendAllSubscriptions', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderExtendAllSubscriptions();

		expect(container).toBeTruthy();
	});

	it('triggers the extension modal when the button is clicked', async () => {
		const {getByText} = renderExtendAllSubscriptions();

		fireEvent.click(getByText('Extend'));

		await wait(() => {
			getByText('extend-all-active-subscriptions');
			getByText('grace-period-end-date');
			getByText('cancel');
			getByText('extend');
		});
	});

	it("displays the default grace period end date as 30 days after the latest active subscription's grace period end date", async () => {
		const {
			getAllByPlaceholderText,
			getByText
		} = renderExtendAllSubscriptions();

		fireEvent.click(getByText('Extend'));

		await wait(() =>
			expect(getAllByPlaceholderText('YYYY-MM-DD')[0].value).toBe(
				'2021-11-20'
			)
		);
	});

	it('enables the Extend button initially', async () => {
		const {getByText} = renderExtendAllSubscriptions();

		fireEvent.click(getByText('Extend'));

		await wait(() => expect(getByText('extend').disabled).toBeFalsy());
	});

	it('disables the Extend button when an invalid date is entered', async () => {
		const {
			getAllByPlaceholderText,
			getByText
		} = renderExtendAllSubscriptions();

		fireEvent.click(getByText('Extend'));

		await wait(() => {
			fireEvent.change(getAllByPlaceholderText('YYYY-MM-DD')[0], {
				target: {value: '2021-13-13'}
			});

			expect(getByText('extend').disabled).toBeTruthy();
		});
	});

	it('disables the Extend button when the grace period end date is missing', async () => {
		const {
			getAllByPlaceholderText,
			getByText
		} = renderExtendAllSubscriptions();

		fireEvent.click(getByText('Extend'));

		await wait(() => {
			fireEvent.change(getAllByPlaceholderText('YYYY-MM-DD')[0], {
				target: {value: ''}
			});

			expect(getByText('extend').disabled).toBeTruthy();
		});
	});

	it("disables the Extend button when a date earlier than the latest active subscription's grace period end date was entered", async () => {
		const {
			getAllByPlaceholderText,
			getByText
		} = renderExtendAllSubscriptions();

		fireEvent.click(getByText('Extend'));

		await wait(() => {
			fireEvent.change(getAllByPlaceholderText('YYYY-MM-DD')[0], {
				target: {value: '2021-10-20'}
			});

			expect(getByText('extend').disabled).toBeTruthy();
		});
	});

	it("allows the user to extend all licenses using the latest active subscription's grace period end date", async () => {
		const {
			getAllByPlaceholderText,
			getByText
		} = renderExtendAllSubscriptions();

		fireEvent.click(getByText('Extend'));

		await wait(() => {
			fireEvent.change(getAllByPlaceholderText('YYYY-MM-DD')[0], {
				target: {value: '2021-10-21'}
			});

			expect(getByText('extend').disabled).toBeFalsy();
		});
	});
});
