/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';
import renderer from 'react-test-renderer';

import SLAListCard from '../../../src/main/resources/META-INF/resources/js/components/sla/SLAListCard.es';
import {MockRouter as Router} from '../../mock/MockRouter.es';
import fetch from '../../mock/fetch.es';

test('Should render component', () => {
	const data = {
		items: [
			{
				dateModified: new Date(
					Date.UTC('2019', '04', '06', '20', '32', '18')
				),
				description: 'Total time to complete the request.',
				duration: 1553879089,
				name: 'Total resolution time'
			}
		],
		totalCount: 0
	};

	const component = renderer.create(
		<Router client={fetch(data)}>
			<SLAListCard />
		</Router>
	);

	const tree = component.toJSON();

	expect(tree).toMatchSnapshot();
});

test('Should render component after item was removed', () => {
	const data = {items: [], totalCount: 0};
	const component = renderer.create(
		<Router client={fetch(data)}>
			<SLAListCard itemRemoved={'test'} />
		</Router>
	);
	const tree = component.toJSON();

	expect(tree).toMatchSnapshot();
});

test('Should render toast with SLA saved message', () => {
	const data = {items: [], totalCount: 0};

	const component = mount(
		<Router client={fetch(data)}>
			<SLAListCard />
		</Router>
	);

	const instance = component.find(SLAListCard).instance();

	instance.showStatusMessage();

	expect(component).toMatchSnapshot();
});

test('Should render toast with SLA updated message', () => {
	const data = {items: [], totalCount: 0};

	const component = mount(
		<Router client={fetch(data)}>
			<SLAListCard />
		</Router>
	);

	const instance = component.find(SLAListCard).instance();

	instance.showStatusMessage();

	expect(component).toMatchSnapshot();
});

test('Should remove a item', () => {
	const data = {items: [], totalCount: 0};

	const component = mount(
		<Router client={fetch(data)}>
			<SLAListCard />
		</Router>
	);
	const instance = component.find(SLAListCard).instance();

	instance.removeItem();
	expect(component).toMatchSnapshot();
});

test('Should test props change', () => {
	const data = {items: [], totalCount: 0};

	const component = mount(
		<Router client={fetch(data)}>
			<SLAListCard />
		</Router>
	);
	const instance = component.find(SLAListCard).instance();

	instance.componentWillReceiveProps({});
	expect(component).toMatchSnapshot();
});
