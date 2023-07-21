/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import Paragraph from '../../../src/main/resources/META-INF/resources/Paragraph/Paragraph.es';

let component;
const spritemap = 'icons.svg';

const defaultParagraphConfig = {
	name: 'textField',
	spritemap
};

describe('Field Paragraph', () => {
	afterEach(() => {
		if (component) {
			component.dispose();
		}
	});

	it('is readOnly', () => {
		component = new Paragraph({
			...defaultParagraphConfig,
			readOnly: true
		});

		expect(component).toMatchSnapshot();
	});

	it('has an id', () => {
		component = new Paragraph({
			...defaultParagraphConfig,
			id: 'ID'
		});

		expect(component).toMatchSnapshot();
	});

	it('has a label', () => {
		component = new Paragraph({
			...defaultParagraphConfig,
			label: 'label'
		});

		expect(component).toMatchSnapshot();
	});

	it('has a placeholder', () => {
		component = new Paragraph({
			...defaultParagraphConfig,
			placeholder: 'Placeholder'
		});

		expect(component).toMatchSnapshot();
	});

	it('is not required', () => {
		component = new Paragraph({
			...defaultParagraphConfig,
			required: false
		});

		expect(component).toMatchSnapshot();
	});

	it('renders Label if showLabel is true', () => {
		component = new Paragraph({
			...defaultParagraphConfig,
			label: 'text',
			showLabel: true
		});

		expect(component).toMatchSnapshot();
	});

	it('has a spritemap', () => {
		component = new Paragraph(defaultParagraphConfig);

		expect(component).toMatchSnapshot();
	});

	it('has a value', () => {
		component = new Paragraph({
			...defaultParagraphConfig,
			value: 'value'
		});

		expect(component).toMatchSnapshot();
	});

	it('has a key', () => {
		component = new Paragraph({
			...defaultParagraphConfig,
			key: 'key'
		});

		expect(component).toMatchSnapshot();
	});
});
