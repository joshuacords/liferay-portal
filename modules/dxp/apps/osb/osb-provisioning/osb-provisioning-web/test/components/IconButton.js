/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {cleanup, fireEvent, render} from '@testing-library/react';
import React from 'react';

import IconButton from '../../src/main/resources/META-INF/resources/js/components/IconButton';

const mockOnClickFn = jest.fn();

function renderIconButton() {
	return render(
		<IconButton
			cssClass="test-css-class"
			labelName="test-label"
			onClick={mockOnClickFn}
			svgId="test"
		/>
	);
}

describe('IconButton', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderIconButton();

		expect(container).toBeTruthy();
	});

	it('renders a button', () => {
		const {getByRole} = renderIconButton();

		getByRole('button');
	});

	it('renders with the specified css class', () => {
		const {container} = renderIconButton();

		expect(container.querySelector('.test-css-class')).toBeTruthy();
	});

	it('renders with the specified label name', () => {
		const {getByLabelText} = renderIconButton();

		getByLabelText('test-label');
	});

	it('calls onClick function', () => {
		const {container} = renderIconButton();

		fireEvent.click(container.querySelector('button'));

		expect(mockOnClickFn).toHaveBeenCalled();
	});
});
