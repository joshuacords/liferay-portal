/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {cleanup, fireEvent, render} from '@testing-library/react';
import React from 'react';

import DatePicker from '../../src/main/resources/META-INF/resources/js/components/DatePicker';

function renderDatePicker(props) {
	return render(<DatePicker id="test" inputName="test" {...props} />);
}

describe('DatePicker', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderDatePicker();

		expect(container).toBeTruthy();
	});

	it('displays the default date value correctly', () => {
		const {getByPlaceholderText} = renderDatePicker({
			defaultValue: '2021-03-09'
		});

		expect(getByPlaceholderText('YYYY-MM-DD').value).toBe('2021-03-09');
	});

	it('displays the date entered in the input field correctly', () => {
		const {getByPlaceholderText} = renderDatePicker();

		expect(getByPlaceholderText('YYYY-MM-DD').value).toBe('');

		fireEvent.change(getByPlaceholderText('YYYY-MM-DD'), {
			target: {value: '2021-03-09'}
		});

		expect(getByPlaceholderText('YYYY-MM-DD').value).toBe('2021-03-09');
	});
});
