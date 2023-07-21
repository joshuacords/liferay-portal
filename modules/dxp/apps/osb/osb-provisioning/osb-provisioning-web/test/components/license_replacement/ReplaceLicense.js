/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {cleanup, fireEvent, render, wait} from '@testing-library/react';
import React from 'react';

import ReplaceLicense from '../../../src/main/resources/META-INF/resources/js/components/license_replacement/ReplaceLicense';
import {CURRENT_TIME} from '../../../src/main/resources/META-INF/resources/js/utilities/constants';
import {
	formatDate,
	generateNewDateByDay
} from '../../../src/main/resources/META-INF/resources/js/utilities/date';

function renderReplaceLicense() {
	return render(
		<ReplaceLicense
			expirationDate="2022-04-14"
			replacementURL="/replacement/url"
			startDate="2021-04-14"
		/>
	);
}

describe('ReplaceLicense', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderReplaceLicense();

		expect(container).toBeTruthy();
	});

	it('displays a Replace button initially', () => {
		const {getByText} = renderReplaceLicense();

		getByText('replace');
	});

	it('displays a replacement modal after the Replace button is clicked', async () => {
		const {getByText, queryAllByText} = renderReplaceLicense();

		fireEvent.click(getByText('replace'));

		await wait(() => expect(queryAllByText('replace').length).toBe(3));
	});

	// Clay Date Picker always displays two inputs for the same date

	it('displays a start date in the replacement modal if one is provided', async () => {
		const {getByText, queryAllByDisplayValue} = renderReplaceLicense();

		fireEvent.click(getByText('replace'));

		await wait(() => {
			getByText('start-date');

			expect(queryAllByDisplayValue('2021-04-14').length).toBe(2);
		});
	});

	it('displays an expiration date in the replacement modal if one is provided', async () => {
		const {getByText, queryAllByDisplayValue} = renderReplaceLicense();

		fireEvent.click(getByText('replace'));

		await wait(() => {
			getByText('expiration-date');

			expect(queryAllByDisplayValue('2022-04-14').length).toBe(2);
		});
	});

	it('displays the current date as the Start Date in the replacement modal if no start date is provided', async () => {
		const {getByText, queryAllByDisplayValue} = render(<ReplaceLicense />);

		fireEvent.click(getByText('replace'));

		await wait(() => {
			getByText('start-date');

			expect(
				queryAllByDisplayValue(formatDate(CURRENT_TIME)).length
			).toBe(2);
		});
	});

	it('displays 30 days after the current date as the Expiration Date in the replacement modal if no expiration date is provided', async () => {
		const {getByText, queryAllByDisplayValue} = render(<ReplaceLicense />);

		fireEvent.click(getByText('replace'));

		await wait(() => {
			getByText('expiration-date');

			expect(
				queryAllByDisplayValue(formatDate(generateNewDateByDay()))
					.length
			).toBe(2);
		});
	});

	it('disables the Replace button initially', async () => {
		const {getByText, queryAllByText} = renderReplaceLicense();

		fireEvent.click(getByText('replace'));

		await wait(() =>
			expect(queryAllByText('replace')[2].disabled).toBe(true)
		);
	});

	it('reenables the Replace button after a date has been updated', async () => {
		const {
			getAllByPlaceholderText,
			getByText,
			queryAllByText
		} = renderReplaceLicense();

		fireEvent.click(getByText('replace'));

		await wait(() => {
			fireEvent.change(getAllByPlaceholderText('YYYY-MM-DD')[0], {
				target: {value: '2020-04-15'}
			});

			expect(queryAllByText('replace')[2].disabled).toBe(false);
		});
	});

	it('keeps the Replace button disabled if a date is modified but left empty', async () => {
		const {
			getAllByPlaceholderText,
			getByText,
			queryAllByText
		} = renderReplaceLicense();

		fireEvent.click(getByText('replace'));

		await wait(() => {
			fireEvent.change(getAllByPlaceholderText('YYYY-MM-DD')[0], {
				target: {value: ''}
			});

			expect(queryAllByText('replace')[2].disabled).toBe(true);
		});
	});

	it('keeps the Replace button disabled if an invalid date is entered', async () => {
		const {
			getAllByPlaceholderText,
			getByText,
			queryAllByText
		} = renderReplaceLicense();

		fireEvent.click(getByText('replace'));

		await wait(() => {
			fireEvent.change(getAllByPlaceholderText('YYYY-MM-DD')[0], {
				target: {value: '2021-04-32'}
			});

			expect(queryAllByText('replace')[2].disabled).toBe(true);
		});
	});

	it('keeps the Replace button disabled until the input date matches the MDYDateFormat', async () => {
		const {
			getAllByPlaceholderText,
			getByText,
			queryAllByText
		} = renderReplaceLicense();

		fireEvent.click(getByText('replace'));

		await wait(() => {
			fireEvent.change(getAllByPlaceholderText('YYYY-MM-DD')[0], {
				target: {value: '2021-04'}
			});

			expect(queryAllByText('replace')[2].disabled).toBe(true);
		});
	});

	it('keeps the Replace button disabled until the input Start Date is later than the Expiration Date', async () => {
		const {
			getAllByPlaceholderText,
			getByText,
			queryAllByText
		} = renderReplaceLicense();

		fireEvent.click(getByText('replace'));

		await wait(() => {
			fireEvent.change(getAllByPlaceholderText('YYYY-MM-DD')[0], {
				target: {value: '2022-04-15'}
			});

			expect(queryAllByText('replace')[2].disabled).toBe(true);
		});
	});
});
