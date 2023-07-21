/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {cleanup, fireEvent, render, within} from '@testing-library/react';
import React from 'react';

import InlineEdit from '../../src/main/resources/META-INF/resources/js/components/InlineEdit';
import {
	FIELD_TYPE_EXTERNAL,
	FIELD_TYPE_SELECT,
	FIELD_TYPE_TEXTAREA,
	FIELD_TYPE_TOGGLE
} from '../../src/main/resources/META-INF/resources/js/utilities/constants';

const mockSubmitFn = jest.fn();

function renderInlineEdit(props) {
	return render(
		<InlineEdit
			fieldName="field1"
			fieldValue="test"
			saveFn={mockSubmitFn}
			{...props}
		/>
	);
}

describe('InlineEdit', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderInlineEdit();

		expect(container).toBeTruthy();
	});

	it('displays an Edit icon when the user hovers over the field', () => {
		const {container} = renderInlineEdit();
		const {getByLabelText, getByText} = within(container);

		fireEvent.mouseEnter(getByText('test'));

		getByLabelText('edit-field-icon');
	});

	it('displays a Save and a Cancel button after the field is clicked on', () => {
		const {container} = renderInlineEdit();
		const {getByText} = within(container);

		fireEvent.click(getByText('test'));

		getByText('save');
		getByText('cancel');
	});

	it('displays Save button as enabled when the initial value of a dropdown field is empty', () => {
		const {container} = renderInlineEdit({
			fieldValue: '-',
			options: [
				{label: '1', value: '1'},
				{label: '2', value: '2'},
				{label: '3', value: '3'},
				{label: 'test', value: 'test'}
			],
			type: FIELD_TYPE_SELECT
		});

		const {getByText} = within(container);

		fireEvent.click(getByText('-'));

		expect(getByText('save').disabled).toBeFalsy();
	});

	it('enables the Save button after the user has made edits to the field', () => {
		const {container} = renderInlineEdit();
		const {getByText} = within(container);

		fireEvent.click(getByText('test'));

		expect(getByText('save').disabled).toBeTruthy();

		fireEvent.change(container.querySelector('input'), {
			target: {value: 'test123'}
		});

		expect(getByText('save').disabled).toBeFalsy();
	});

	it('hides the Cancel and Save button after the Cancel button is clicked', () => {
		const {container} = renderInlineEdit();
		const {getByText, queryByText} = within(container);

		fireEvent.click(getByText('test'));
		fireEvent.click(getByText('cancel'));

		expect(queryByText('save')).toBeFalsy();
		expect(queryByText('cancel')).toBeFalsy();
	});

	it('restores the field value after the Cancel button is clicked', () => {
		const {container} = renderInlineEdit();
		const {getByText, queryByText} = within(container);

		fireEvent.click(getByText('test'));
		fireEvent.change(container.querySelector('input'), {
			target: {value: 'test123'}
		});

		fireEvent.click(getByText('cancel'));

		expect(queryByText('test123')).toBeFalsy();
		getByText('test');
	});

	it('does not show the edit icon around the field after the Cancel button is clicked', () => {
		const {container} = renderInlineEdit();
		const {getByText, queryByLabelText} = within(container);

		fireEvent.click(getByText('test'));
		fireEvent.click(getByText('cancel'));

		expect(queryByLabelText('edit-field-icon')).toBeFalsy();
	});

	it('calls the provided submit function', () => {
		const {container} = renderInlineEdit();
		const {getByText} = within(container);

		fireEvent.click(getByText('test'));
		fireEvent.change(container.querySelector('input'), {
			target: {value: 'test123'}
		});
		fireEvent.click(getByText('save'));

		expect(mockSubmitFn).toHaveBeenCalled();
	});

	it('displays an external field type as a disabled input, a Select button, and a Delete icon', () => {
		const {container} = renderInlineEdit({type: FIELD_TYPE_EXTERNAL});
		const {getByLabelText, getByText} = within(container);

		fireEvent.click(getByText('test'));

		expect(container.querySelector('input').disabled).toBeTruthy();
		getByText('select');
		expect(getByText('select').type).toBe('button');
		getByLabelText('delete-field-icon');
	});

	it('displays an editable select input correctly', () => {
		const {container} = renderInlineEdit({
			options: [
				{label: '1', value: '1'},
				{label: '2', value: '2'},
				{label: '3', value: '3'},
				{label: 'test', value: 'test'}
			],
			type: FIELD_TYPE_SELECT
		});
		const {getByText} = within(container);

		fireEvent.click(getByText('test'));

		const select = container.querySelector('select');

		expect(select).toBeTruthy();
		getByText('1');
		getByText('2');
		getByText('3');
	});

	it('displays an editable text input correctly', () => {
		const {container} = renderInlineEdit();
		const {getByText} = within(container);

		fireEvent.click(getByText('test'));

		expect(container.querySelector('input')['type']).toBe('text');
	});

	it('displays an editable textarea input correctly', () => {
		const {container} = renderInlineEdit({
			type: FIELD_TYPE_TEXTAREA
		});
		const {getByText} = within(container);

		fireEvent.click(getByText('test'));

		expect(container.querySelector('textarea')).toBeTruthy();
	});

	describe('Toggle', () => {
		it('displays correctly', () => {
			const {getByLabelText} = renderInlineEdit({
				fieldValue: true,
				type: FIELD_TYPE_TOGGLE
			});

			expect(getByLabelText('field1').type).toBe('checkbox');
		});

		it('displays saving controls when interacted with', () => {
			const {getByLabelText, getByText} = renderInlineEdit({
				fieldValue: true,
				type: FIELD_TYPE_TOGGLE
			});

			fireEvent.click(getByLabelText('field1'));

			expect(getByText('save'));
			expect(getByText('save').disabled).toBeFalsy();
			expect(getByText('cancel'));
		});

		it('displays Save button as disabled when toggling back to original state', () => {
			const {getByLabelText, getByText} = renderInlineEdit({
				fieldValue: true,
				type: FIELD_TYPE_TOGGLE
			});

			const toggle = getByLabelText('field1');
			fireEvent.click(toggle);
			fireEvent.click(toggle);

			expect(getByText('save').disabled).toBeTruthy();
		});
	});
});
