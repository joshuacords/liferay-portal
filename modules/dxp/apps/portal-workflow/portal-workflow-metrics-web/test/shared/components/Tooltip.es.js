/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

import Tooltip, {
	TooltipBase
} from '../../../src/main/resources/META-INF/resources/js/shared/components/Tooltip.es';

describe('Tooltip', () => {
	let component;

	const props = {
		message: 'Tooltip Message',
		position: 'top',
		width: '200'
	};

	afterEach(() => {
		if (component) {
			component.unmount();
		}
	});

	it('renders component with tooltip after mouse over', () => {
		component = shallow(
			<Tooltip {...props} position={'right'}>
				{'Target'}
			</Tooltip>
		);

		component.find('.tooltip-trigger').simulate('mouseover');

		expect(component).toMatchSnapshot();
	});

	it('renders component with tooltip markup', () => {
		component = shallow(<Tooltip {...props}>{'Target'}</Tooltip>);

		const instance = component.instance();

		instance.showTooltip();

		expect(component).toMatchSnapshot();
	});

	it('renders component with tooltip with bottom position', () => {
		jest.useFakeTimers();

		component = shallow(
			<Tooltip {...props} position={'bottom'}>
				{'Target'}
			</Tooltip>
		);

		const instance = component.instance();

		instance.showTooltip();

		expect(component.find(TooltipBase).render()).toMatchSnapshot();
	});

	it('renders component with tooltip with left position', () => {
		component = shallow(
			<Tooltip {...props} position={'right'}>
				{'Target'}
			</Tooltip>
		);

		const instance = component.instance();

		instance.showTooltip();

		expect(component.find(TooltipBase).render()).toMatchSnapshot();
	});

	it('renders component with tooltip with right position', () => {
		component = shallow(
			<Tooltip {...props} position={'right'}>
				{'Target'}
			</Tooltip>
		);

		const instance = component.instance();

		instance.showTooltip();

		expect(component.find(TooltipBase).render()).toMatchSnapshot();
	});

	it('renders component with tooltip with top position', () => {
		component = shallow(<Tooltip {...props}>{'Target'}</Tooltip>);

		const instance = component.instance();

		instance.showTooltip();

		expect(component.find(TooltipBase).render()).toMatchSnapshot();
	});

	it('renders component without tooltip after mouse leave', () => {
		component = shallow(
			<Tooltip {...props} position={'right'}>
				{'Target'}
			</Tooltip>
		);

		component.find('.tooltip-trigger').simulate('mouseover');
		component.find('.workflow-tooltip').simulate('mouseleave');

		expect(component).toMatchSnapshot();
	});

	it('renders component without tooltip markup', () => {
		component = shallow(<Tooltip {...props}>{'Target'}</Tooltip>);

		expect(component).toMatchSnapshot();
	});
});
