/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {cleanup, render} from '@testing-library/react';
import React from 'react';

import Dates from '../../../../src/main/resources/META-INF/resources/js/components/search/account/Dates';

function renderDates() {
	return render(<Dates />);
}

describe('Account Search Dates', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderDates();

		expect(container).toBeTruthy();
	});

	it('displays a Created By email field', () => {
		const {getByLabelText} = renderDates();

		expect(getByLabelText('created-by').type).toBe('email');
	});

	it('displays a Created After field', () => {
		const {getByLabelText} = renderDates();

		getByLabelText('created-after');
	});

	it('displays a Created Before field', () => {
		const {getByLabelText} = renderDates();

		getByLabelText('created-before');
	});

	it('displays a Modified After field', () => {
		const {getByLabelText} = renderDates();

		getByLabelText('modified-after');
	});

	it('displays a Modified Before field', () => {
		const {getByLabelText} = renderDates();

		getByLabelText('modified-before');
	});
});
