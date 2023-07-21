/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {cleanup, fireEvent, render} from '@testing-library/react';
import React from 'react';

import CheckboxGroups from '../../../src/main/resources/META-INF/resources/js/components/search/CheckboxGroups';

function renderCheckboxGroups(props) {
	return render(
		<CheckboxGroups
			fieldValues={[
				{label: 'One', value: '1'},
				{label: 'Two', value: '2'}
			]}
			inputName={'test input'}
			{...props}
		/>
	);
}

describe('Search', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderCheckboxGroups();

		expect(container).toBeTruthy();
	});

	it('displays checkboxes in the number of columns specified', () => {
		const {container} = renderCheckboxGroups({columns: 2});

		expect(container.querySelectorAll('.col-md-6').length).toBe(2);
	});

	it('displays the checked property correctly', () => {
		const {getByLabelText} = renderCheckboxGroups({
			fieldValues: [
				{checked: true, label: 'One', value: '1'},
				{checked: false, label: 'Two', value: '2'}
			]
		});

		expect(getByLabelText('One').checked).toBeTruthy();
	});

	it('updates the checked property correctly', () => {
		const {getByLabelText} = renderCheckboxGroups({
			fieldValues: [
				{checked: true, label: 'One', value: '1'},
				{checked: false, label: 'Two', value: '2'}
			]
		});

		fireEvent.click(getByLabelText('One'));

		expect(getByLabelText('One').checked).toBeFalsy();
	});
});
