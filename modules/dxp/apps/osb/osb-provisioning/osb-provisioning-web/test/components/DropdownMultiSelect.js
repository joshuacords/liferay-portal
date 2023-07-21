/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {cleanup, fireEvent, render} from '@testing-library/react';
import React from 'react';

import DropdownMultiSelect from '../../src/main/resources/META-INF/resources/js/components/DropdownMultiSelect';

const addFn = jest.fn();
const removeFn = jest.fn();

function renderDropdownMultiSelect() {
	return render(
		<DropdownMultiSelect
			addFn={addFn}
			allOptions={[
				{key: 'KEY1', name: 'One'},
				{key: 'KEY2', name: 'Two'},
				{key: 'KEY3', name: 'Three'},
				{key: 'KEY4', name: 'Four'},
				{key: 'KEY5', name: 'Five'}
			]}
			newOptions={['KEY1', 'KEY2', 'KEY3']}
			removeFn={removeFn}
		/>
	);
}

describe('DropdownMultiSelect', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderDropdownMultiSelect();

		expect(container).toBeTruthy();
	});

	it('displays selected options as labels', () => {
		const {getByText} = renderDropdownMultiSelect();

		expect(getByText('One').getAttribute('class')).not.toBe(
			'dropdown-item'
		);
		expect(getByText('Two').getAttribute('class')).not.toBe(
			'dropdown-item'
		);
		expect(getByText('Three').getAttribute('class')).not.toBe(
			'dropdown-item'
		);
	});

	it('displays unselected options in the dropdowns', () => {
		const {getByText} = renderDropdownMultiSelect();

		expect(getByText('Four').getAttribute('class')).toBe('dropdown-item');
		expect(getByText('Five').getAttribute('class')).toBe('dropdown-item');
	});

	it('adds option when selected from dropdown', () => {
		const {getByText, getByTitle} = renderDropdownMultiSelect();

		fireEvent.click(getByTitle('add'));
		fireEvent.click(getByText('Four'));

		expect(addFn).toHaveBeenCalled();
	});

	it('removes option when clicked on Close button', () => {
		const {getAllByTitle} = renderDropdownMultiSelect();

		fireEvent.click(getAllByTitle('delete')[0]);

		expect(removeFn).toHaveBeenCalled();
	});
});
