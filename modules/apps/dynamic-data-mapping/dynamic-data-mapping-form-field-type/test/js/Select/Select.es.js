/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {dom as MetalTestUtil} from 'metal-dom';

import Select from '../../../src/main/resources/META-INF/resources/Select/Select.es';

let component;
const spritemap = 'icons.svg';

const createOptions = length => {
	const options = [];

	for (let counter = 1; counter <= length; counter++) {
		options.push({
			label: 'label' + counter,
			name: 'name' + counter,
			value: 'item' + counter
		});
	}

	return options;
};

describe('Select', () => {
	beforeEach(() => {
		jest.useFakeTimers();
	});

	afterEach(() => {
		if (component) {
			component.dispose();
		}
	});

	it('does not render and empty option', () => {
		const option = {
			checked: false,
			disabled: false,
			id: 'id',
			inline: false,
			label: 'label',
			name: 'name',
			showLabel: true,
			value: 'item'
		};

		component = new Select({
			options: [option],
			showEmptyOption: false,
			spritemap
		});

		const dropDownItem = component.element.querySelector(
			'.dropdown-menu .dropdown-item'
		);

		expect(dropDownItem.innerHTML).toBe(option.label);
	});

	it('does not show an empty option when the search input is available', () => {
		const handleFieldEdited = jest.fn();

		const events = {fieldEdited: handleFieldEdited};

		component = new Select({
			dataSourceType: 'manual',
			events,
			multiple: false,
			options: createOptions(12),
			showEmptyOption: false,
			spritemap
		});

		const dropdownTrigger = component.element.querySelector(
			'.form-builder-select-field.input-group-container'
		);

		MetalTestUtil.triggerEvent(dropdownTrigger, 'click');

		jest.runAllTimers();

		const emptyOption = component.element.querySelector(
			'[label=choose-an-option]'
		);

		expect(emptyOption).toBeNull();
	});

	it('is not editable', () => {
		component = new Select({
			readOnly: false,
			spritemap
		});

		expect(component).toMatchSnapshot();
	});

	it('has a helptext', () => {
		component = new Select({
			spritemap,
			tip: 'Type something'
		});

		expect(component).toMatchSnapshot();
	});

	it('has an id', () => {
		component = new Select({
			id: 'ID',
			spritemap
		});

		expect(component).toMatchSnapshot();
	});

	it('renders an empty option', () => {
		component = new Select({
			options: [
				{
					checked: false,
					disabled: false,
					id: 'id',
					inline: false,
					label: 'label',
					name: 'name',
					showLabel: true,
					value: 'item'
				}
			],
			showEmptyOption: true,
			spritemap
		});

		const dropDownItem = component.element.querySelector(
			'.dropdown-menu .dropdown-item'
		);

		expect(dropDownItem.innerHTML).toBe('choose-an-option');
	});

	it('renders options', () => {
		component = new Select({
			options: [
				{
					checked: false,
					disabled: false,
					id: 'id',
					inline: false,
					label: 'label',
					name: 'name',
					showLabel: true,
					value: 'item'
				}
			],
			spritemap
		});

		expect(component).toMatchSnapshot();
	});

	it('renders no options when options come empty', () => {
		component = new Select({
			options: [],
			spritemap
		});

		expect(component).toMatchSnapshot();
	});

	it('has a label', () => {
		component = new Select({
			label: 'label',
			spritemap
		});

		expect(component).toMatchSnapshot();
	});

	it('is closed by default', () => {
		component = new Select({
			open: false,
			spritemap
		});

		expect(component).toMatchSnapshot();
	});

	it("has class dropdown-opened when it's opened", () => {
		component = new Select({
			open: true,
			spritemap
		});

		expect(component).toMatchSnapshot();
	});

	it('has a placeholder', () => {
		component = new Select({
			placeholder: 'Placeholder',
			spritemap
		});

		expect(component).toMatchSnapshot();
	});

	it('has a predefinedValue', () => {
		component = new Select({
			predefinedValue: ['Select'],
			spritemap
		});

		expect(component).toMatchSnapshot();
	});

	it('is not required', () => {
		component = new Select({
			required: false,
			spritemap
		});

		expect(component).toMatchSnapshot();
	});

	it('puts an asterisk when field is required', () => {
		component = new Select({
			label: 'This is the label',
			required: true,
			spritemap
		});

		expect(component).toMatchSnapshot();
	});

	it('renders Label if showLabel is true', () => {
		component = new Select({
			label: 'text',
			showLabel: true,
			spritemap
		});

		expect(component).toMatchSnapshot();
	});

	it('has a spritemap', () => {
		component = new Select({
			spritemap
		});

		expect(component).toMatchSnapshot();
	});

	it('has a value', () => {
		component = new Select({
			spritemap,
			value: ['value']
		});

		expect(component).toMatchSnapshot();
	});

	it('has a key', () => {
		component = new Select({
			key: 'key',
			spritemap
		});

		expect(component).toMatchSnapshot();
	});

	it('emits a field edit event when an item is selected', () => {
		const handleFieldEdited = jest.fn();

		const events = {fieldEdited: handleFieldEdited};

		jest.useFakeTimers();

		component = new Select({
			dataSourceType: 'manual',
			events,
			options: [
				{
					checked: false,
					disabled: false,
					id: 'id',
					inline: false,
					label: 'label',
					name: 'name',
					showLabel: true,
					value: 'item'
				}
			],
			spritemap
		});

		const spy = jest.spyOn(component, 'emit');

		jest.runAllTimers();

		component._handleItemClicked({
			data: {
				item: {
					value: 'Liferay'
				}
			},
			preventDefault: () => 0
		});

		expect(spy).toHaveBeenCalled();
	});

	it('renders the dropdown with search when there are more than six options', () => {
		component = new Select({
			dataSourceType: 'manual',
			options: [
				{
					label: 'label',
					name: 'name',
					value: 'item'
				},
				{
					label: 'label',
					name: 'name',
					value: 'item'
				},
				{
					label: 'label',
					name: 'name',
					value: 'item'
				},
				{
					label: 'label',
					name: 'name',
					value: 'item'
				},
				{
					label: 'label',
					name: 'name',
					value: 'item'
				},
				{
					label: 'label',
					name: 'name',
					value: 'item'
				},
				{
					label: 'label',
					name: 'name',
					value: 'item'
				}
			],
			spritemap
		});

		expect(component).toMatchSnapshot();
	});

	it('shows an empty option when the search input is available', () => {
		const handleFieldEdited = jest.fn();

		const events = {fieldEdited: handleFieldEdited};

		component = new Select({
			dataSourceType: 'manual',
			events,
			multiple: false,
			options: createOptions(12),
			showEmptyOption: true,
			spritemap
		});

		const dropdownTrigger = component.element.querySelector(
			'.form-builder-select-field.input-group-container'
		);

		MetalTestUtil.triggerEvent(dropdownTrigger, 'click');

		jest.runAllTimers();

		const emptyOption = component.element.querySelector(
			'[label=choose-an-option]'
		);

		expect(emptyOption).not.toBeNull();
	});
});
