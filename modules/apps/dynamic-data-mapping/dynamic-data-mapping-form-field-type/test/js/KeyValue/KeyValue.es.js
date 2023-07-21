/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import KeyValue from '../../../src/main/resources/META-INF/resources/KeyValue/KeyValue.es';

let component;
const spritemap = 'icons.svg';

describe('KeyValue', () => {
	afterEach(() => {
		if (component) {
			component.dispose();
		}
	});

	it('is not edidable', () => {
		component = new KeyValue({
			name: 'keyValue',
			readOnly: false,
			spritemap
		});

		expect(component).toMatchSnapshot();
	});

	it('has a helptext', () => {
		component = new KeyValue({
			name: 'keyValue',
			spritemap,
			tip: 'Type something'
		});

		expect(component).toMatchSnapshot();
	});

	it('has an id', () => {
		component = new KeyValue({
			id: 'ID',
			name: 'keyValue',
			spritemap
		});

		expect(component).toMatchSnapshot();
	});

	it('has a label', () => {
		component = new KeyValue({
			label: 'label',
			name: 'keyValue',
			spritemap
		});

		expect(component).toMatchSnapshot();
	});

	it('has a predefined Value', () => {
		component = new KeyValue({
			name: 'keyValue',
			placeholder: 'Option 1',
			spritemap
		});

		expect(component).toMatchSnapshot();
	});

	it('is not required', () => {
		component = new KeyValue({
			name: 'keyValue',
			required: false,
			spritemap
		});

		expect(component).toMatchSnapshot();
	});

	it('renders Label if showLabel is true', () => {
		component = new KeyValue({
			label: 'text',
			name: 'keyValue',
			showLabel: true,
			spritemap
		});

		expect(component).toMatchSnapshot();
	});

	it('has a spritemap', () => {
		component = new KeyValue({
			name: 'keyValue',
			spritemap
		});

		expect(component).toMatchSnapshot();
	});

	it('has a value', () => {
		component = new KeyValue({
			name: 'keyValue',
			spritemap,
			value: 'value'
		});

		expect(component).toMatchSnapshot();
	});

	it('renders component with a key', () => {
		component = new KeyValue({
			keyword: 'key',
			name: 'keyValue',
			spritemap
		});

		expect(component).toMatchSnapshot();
	});
});
