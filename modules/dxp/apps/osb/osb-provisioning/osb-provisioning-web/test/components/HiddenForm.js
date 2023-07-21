/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {cleanup, render} from '@testing-library/react';
import React from 'react';

import HiddenForm from '../../src/main/resources/META-INF/resources/js/components/HiddenForm';

const formRef = jest.fn();

function renderHiddenForm() {
	return render(
		<HiddenForm
			fields={{1: 'a', 2: 'b', 3: 'c'}}
			formAction="/"
			ref={formRef}
		/>
	);
}

describe('HiddenForm', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderHiddenForm();

		expect(container).toBeTruthy();
	});

	it('displays all inputs to be type hidden', () => {
		const {container} = renderHiddenForm();

		expect(container.querySelectorAll('[type]').length).toBe(3);
	});

	it("namespaces the input's name attribute", () => {
		const {container} = renderHiddenForm();

		const inputs = container.querySelectorAll('input');

		expect(inputs[0]['name']).toBe('namespace1');
		expect(inputs[1]['name']).toBe('namespace2');
		expect(inputs[2]['name']).toBe('namespace3');
	});

	it('displays the correct value for each input per the data provided', () => {
		const {container} = renderHiddenForm();

		const inputs = container.querySelectorAll('input');

		expect(inputs[0]['value']).toBe('a');
		expect(inputs[1]['value']).toBe('b');
		expect(inputs[2]['value']).toBe('c');
	});
});
