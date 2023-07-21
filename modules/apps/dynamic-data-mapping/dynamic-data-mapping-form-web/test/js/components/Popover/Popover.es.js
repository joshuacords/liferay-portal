/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {dom as MetalTestUtil} from 'metal-dom';

import Popover from '../../../../src/main/resources/META-INF/resources/admin/js/components/Popover/Popover.es';

const props = {
	content: 'This content will be displayed when popover appears',
	placement: 0,
	title: 'Liferay',
	visible: false
};

let alignElement;

describe('Popover', () => {
	let component;

	afterEach(() => {
		if (component) {
			component.dispose();
		}
	});

	beforeEach(() => {
		alignElement = document.createElement('div');
		alignElement.classList.add('align-element');

		document.querySelector('body').appendChild(alignElement);

		props.alignElement = alignElement;

		jest.useFakeTimers();
		fetch.resetMocks();
	});

	it('renders the default markup', () => {
		component = new Popover(props);
		expect(component).toMatchSnapshot();
	});

	it('renders popover opened', () => {
		component = new Popover({
			...props,
			visible: true
		});

		expect(component).toMatchSnapshot();
	});

	it('opens when the visible property changes', () => {
		component = new Popover(props);

		jest.runAllTimers();

		jest.useFakeTimers();

		component.willReceiveProps({
			visible: {
				newVal: true
			}
		});

		jest.runAllTimers();

		expect(component.state.displayed).toBeTruthy();
		expect(component).toMatchSnapshot();
	});

	it('opens when alignedElement is clicked', () => {
		component = new Popover(props);

		jest.runAllTimers();

		MetalTestUtil.triggerEvent(alignElement, 'click');

		expect(component.state.displayed).toBeTruthy();
		expect(component).toMatchSnapshot();
	});

	it('closes when it is already opened and the alignedElement is clicked', () => {
		component = new Popover({
			...props,
			visible: true
		});

		jest.runAllTimers();

		MetalTestUtil.triggerEvent(alignElement, 'click');

		expect(component.state.displayed).toBeFalsy();
		expect(component).toMatchSnapshot();
	});

	it('closes when document has mousedown event', () => {
		component = new Popover({
			...props,
			visible: true
		});

		jest.runAllTimers();

		jest.useFakeTimers();

		MetalTestUtil.triggerEvent(document, 'mousedown');

		jest.runAllTimers();

		expect(component.state.displayed).toBeFalsy();
		expect(component).toMatchSnapshot();
	});
});
