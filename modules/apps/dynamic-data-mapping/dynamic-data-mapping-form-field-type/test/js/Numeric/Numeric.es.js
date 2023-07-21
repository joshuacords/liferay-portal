/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {dom} from 'metal-dom';

import Numeric from '../../../src/main/resources/META-INF/resources/Numeric/Numeric.es';

let component;
const spritemap = 'icons.svg';

const defaultNumericConfig = {
	name: 'numericField',
	spritemap
};

describe('Field Numeric', () => {
	beforeEach(() => {
		jest.useFakeTimers();
	});

	afterEach(() => {
		if (component) {
			component.dispose();
		}
	});

	it('renders the default markup', () => {
		component = new Numeric({
			...defaultNumericConfig,
			readOnly: false
		});

		expect(component).toMatchSnapshot();
	});

	it('is not readOnly', () => {
		component = new Numeric({
			...defaultNumericConfig,
			readOnly: false
		});

		expect(component).toMatchSnapshot();
	});

	it('has a helptext', () => {
		component = new Numeric({
			...defaultNumericConfig,
			tip: 'Type something'
		});

		expect(component).toMatchSnapshot();
	});

	it('has an id', () => {
		component = new Numeric({
			...defaultNumericConfig,
			id: 'ID'
		});

		expect(component).toMatchSnapshot();
	});

	it('has a label', () => {
		component = new Numeric({
			...defaultNumericConfig,
			label: 'label'
		});

		expect(component).toMatchSnapshot();
	});

	it('has a placeholder', () => {
		component = new Numeric({
			...defaultNumericConfig,
			placeholder: 'Placeholder'
		});

		expect(component).toMatchSnapshot();
	});

	it('is not required', () => {
		component = new Numeric({
			...defaultNumericConfig,
			required: false
		});

		expect(component).toMatchSnapshot();
	});

	it('renders Label if showLabel is true', () => {
		component = new Numeric({
			...defaultNumericConfig,
			label: 'Numeric Field',
			showLabel: true
		});

		expect(component).toMatchSnapshot();
	});

	it('has a spritemap', () => {
		component = new Numeric(defaultNumericConfig);

		expect(component).toMatchSnapshot();
	});

	it('has a value', () => {
		component = new Numeric({
			...defaultNumericConfig,
			value: '123'
		});

		expect(component).toMatchSnapshot();
	});

	it('has a key', () => {
		component = new Numeric({
			...defaultNumericConfig,
			key: 'key'
		});

		expect(component).toMatchSnapshot();
	});

	it('emits a field edit event on field value change', () => {
		jest.useFakeTimers();

		const handleFieldEdited = jest.fn();

		const events = {fieldEdited: handleFieldEdited};

		component = new Numeric({
			...defaultNumericConfig,
			events
		});

		dom.triggerEvent(component.element.querySelector('input'), 'input', {});

		jest.runAllTimers();

		expect(handleFieldEdited).toHaveBeenCalled();
	});

	it('propagates the field edit event', () => {
		jest.useFakeTimers();

		component = new Numeric({
			...defaultNumericConfig,
			key: 'input'
		});

		const spy = jest.spyOn(component, 'emit');

		dom.triggerEvent(component.element.querySelector('input'), 'input', {});

		jest.runAllTimers();

		expect(spy).toHaveBeenCalled();
		expect(spy).toHaveBeenCalledWith('fieldEdited', expect.any(Object));
	});

	it('changes the mask type', () => {
		component = new Numeric({
			...defaultNumericConfig
		});

		jest.runAllTimers();

		component.setState({
			dataType: 'double'
		});

		jest.runAllTimers();

		expect(component.dataType).toBe('double');
	});

	it('check if event is sent when decimal is being writen', done => {
		const handleFieldEdited = data => {
			expect(data).toEqual(
				expect.objectContaining({
					fieldInstance: component,
					originalEvent: expect.any(Object),
					value: '3.0'
				})
			);
			done();
		};

		const events = {fieldEdited: handleFieldEdited};

		component = new Numeric({
			...defaultNumericConfig,
			events,
			key: 'input'
		});

		component._handleFieldChanged({
			target: {
				value: '3.0'
			}
		});
	});

	it('check field value is rounded when fieldType is integer but it receives a double', () => {
		component = new Numeric({
			...defaultNumericConfig,
			key: 'input',
			value: '3.8'
		});

		expect(component.value).toEqual('4');
	});
});
