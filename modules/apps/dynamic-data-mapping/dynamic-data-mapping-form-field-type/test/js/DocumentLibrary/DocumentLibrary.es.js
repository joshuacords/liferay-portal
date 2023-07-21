/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import DocumentLibrary from '../../../src/main/resources/META-INF/resources/DocumentLibrary/DocumentLibrary.es';

let component;
const spritemap = 'icons.svg';

const defaultDocumentLibraryConfig = {
	name: 'textField',
	spritemap
};

describe('Field DocumentLibrary', () => {
	afterEach(() => {
		if (component) {
			component.dispose();
		}
	});

	it('is not readOnly', () => {
		component = new DocumentLibrary({
			...defaultDocumentLibraryConfig,
			readOnly: false
		});

		expect(component).toMatchSnapshot();
	});

	it('has a helptext', () => {
		component = new DocumentLibrary({
			...defaultDocumentLibraryConfig,
			tip: 'Type something'
		});

		expect(component).toMatchSnapshot();
	});

	it('has an id', () => {
		component = new DocumentLibrary({
			...defaultDocumentLibraryConfig,
			id: 'ID'
		});

		expect(component).toMatchSnapshot();
	});

	it('has a label', () => {
		component = new DocumentLibrary({
			...defaultDocumentLibraryConfig,
			label: 'label'
		});

		expect(component).toMatchSnapshot();
	});

	it('has a placeholder', () => {
		component = new DocumentLibrary({
			...defaultDocumentLibraryConfig,
			placeholder: 'Placeholder'
		});

		expect(component).toMatchSnapshot();
	});

	it('is not required', () => {
		component = new DocumentLibrary({
			...defaultDocumentLibraryConfig,
			required: false
		});

		expect(component).toMatchSnapshot();
	});

	it('renders Label if showLabel is true', () => {
		component = new DocumentLibrary({
			...defaultDocumentLibraryConfig,
			label: 'text',
			showLabel: true
		});

		expect(component).toMatchSnapshot();
	});

	it('has a spritemap', () => {
		component = new DocumentLibrary(defaultDocumentLibraryConfig);

		expect(component).toMatchSnapshot();
	});

	it('has a value', () => {
		component = new DocumentLibrary({
			...defaultDocumentLibraryConfig,
			value: {
				id: '123'
			}
		});

		expect(component).toMatchSnapshot();
	});
});
