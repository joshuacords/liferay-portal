/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {cleanup, fireEvent, render} from '@testing-library/react';
import React from 'react';

import {CustomTimeRangeForm} from '../../../../src/main/resources/META-INF/resources/js/components/process-metrics/filter/CustomTimeRangeForm.es';
import {TimeRangeContext} from '../../../../src/main/resources/META-INF/resources/js/components/process-metrics/filter/store/TimeRangeStore.es';
import {MockRouter} from '../../../mock/MockRouter.es';

describe('The custom time range form, when using a valid range, should', () => {
	const timeRange = {
		dateEnd: new Date('2019-01-31'),
		dateStart: new Date('2019-01-24'),
		key: 'custom',
		name: 'Custom Range'
	};

	const contextMock = {
		getSelectedTimeRange: () => timeRange,
		setShowCustomForm: jest.fn(),
		setTimeRanges: jest.fn(),
		timeRanges: [timeRange]
	};

	let renderResult;

	afterEach(cleanup);

	beforeEach(() => {
		renderResult = render(
			<MockRouter>
				<TimeRangeContext.Provider value={contextMock}>
					<CustomTimeRangeForm />
				</TimeRangeContext.Provider>
			</MockRouter>
		);
	});

	test('Call "setShowCustomForm" with "false" as param when apply button is clicked', () => {
		const {getByTestId} = renderResult;

		const applyButton = getByTestId('applyButton');

		fireEvent.click(applyButton);

		expect(contextMock.setShowCustomForm).toHaveBeenCalledWith(false);
	});

	test('Call "setShowCustomForm" with "false" as param when cancel button is clicked', () => {
		const {getByTestId} = renderResult;

		const cancelButton = getByTestId('cancelButton');

		fireEvent.mouseDown(cancelButton);

		expect(contextMock.setShowCustomForm).toHaveBeenCalledWith(false);
	});

	test('Display the formatted dates in the date inputs', () => {
		const {getByTestId} = renderResult;

		const dateEndInput = getByTestId('dateEndInput');
		const dateStartInput = getByTestId('dateStartInput');

		expect(dateEndInput.value).toBe('01/31/2019');
		expect(dateStartInput.value).toBe('01/24/2019');
	});
});

describe('The custom time range form, when using a invalid range, should', () => {
	const timeRange = {
		dateEnd: null,
		dateStart: null,
		key: 'custom',
		name: 'Custom Range'
	};

	const contextMock = {
		getSelectedTimeRange: () => timeRange,
		setShowCustomForm: jest.fn(),
		setTimeRanges: jest.fn(),
		timeRanges: [timeRange]
	};

	let renderResult;

	afterEach(cleanup);

	beforeEach(() => {
		renderResult = render(
			<MockRouter>
				<TimeRangeContext.Provider value={contextMock}>
					<CustomTimeRangeForm />
				</TimeRangeContext.Provider>
			</MockRouter>
		);
	});

	test('Display an error for each field', () => {
		const {getAllByTestId, getByTestId} = renderResult;

		const dateStartInput = getByTestId('dateStartInput');

		fireEvent.blur(dateStartInput);

		const errorSpans = getAllByTestId('errorSpan');

		expect(errorSpans.length).toBe(2);
	});

	test('Display "Invalid date" for each field', () => {
		const {getByTestId} = renderResult;

		const dateEndInput = getByTestId('dateEndInput');
		const dateStartInput = getByTestId('dateStartInput');

		expect(dateEndInput.value).toBe('Invalid date');
		expect(dateStartInput.value).toBe('Invalid date');
	});

	test('Hide the error if the valid is fixed', async () => {
		const {getAllByTestId, getByTestId} = renderResult;

		const dateEndInput = getByTestId('dateEndInput');
		const dateStartInput = getByTestId('dateStartInput');

		fireEvent.change(dateStartInput, {target: {value: '01/25/2019'}});
		fireEvent.blur(dateStartInput);

		fireEvent.change(dateEndInput, {target: {value: '01/31/2019'}});
		fireEvent.blur(dateEndInput);

		expect(() => getAllByTestId('errorSpan')).toThrow();
	});
});
