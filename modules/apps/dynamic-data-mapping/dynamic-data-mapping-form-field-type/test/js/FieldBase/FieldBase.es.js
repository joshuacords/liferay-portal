/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import FieldBase from '../../../src/main/resources/META-INF/resources/FieldBase/FieldBase.es';

let component;
const spritemap = 'icons.svg';

describe('FieldBase', () => {
	afterEach(() => {
		if (component) {
			component.dispose();
		}
	});

	it('renders the default markup', () => {
		component = new FieldBase({
			spritemap
		});

		expect(component).toMatchSnapshot();
	});

	it('renders the FieldBase with required', () => {
		component = new FieldBase({
			required: true,
			spritemap
		});

		expect(component).toMatchSnapshot();
	});

	it('renders the FieldBase with id', () => {
		component = new FieldBase({
			id: 'Id',
			spritemap
		});

		expect(component).toMatchSnapshot();
	});

	it('renders the FieldBase with help text', () => {
		component = new FieldBase({
			spritemap,
			tip: 'Type something!'
		});

		expect(component).toMatchSnapshot();
	});

	it('renders the FieldBase with label', () => {
		component = new FieldBase({
			label: 'Text',
			spritemap
		});

		expect(component).toMatchSnapshot();
	});

	it('does not render the label if showLabel is false', () => {
		component = new FieldBase({
			label: 'Text',
			showLabel: false,
			spritemap
		});

		expect(component).toMatchSnapshot();
	});

	it('renders the FieldBase with contentRenderer', () => {
		component = new FieldBase({
			contentRenderer: `
                <div>
                    <h1>Foo bar</h1>
                </div>
            `,
			spritemap
		});

		expect(component).toMatchSnapshot();
	});
});
