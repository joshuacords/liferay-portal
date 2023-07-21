/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';
import renderer from 'react-test-renderer';

import SLAListItem from '../../../src/main/resources/META-INF/resources/js/components/sla/SLAListItem.es';
import {MockRouter as Router} from '../../mock/MockRouter.es';

test('Should render component', () => {
	const component = renderer.create(
		<Router>
			<SLAListItem
				dateModified={
					new Date(Date.UTC('2019', '04', '06', '20', '32', '18'))
				}
				id={1234}
				instancesCount="10"
				onTime="5"
				overdue="5"
				processName="Process test"
			/>
		</Router>
	);

	const tree = component.toJSON();

	expect(tree).toMatchSnapshot();
});

test('Should render component', () => {
	const component = mount(
		<Router>
			<SLAListItem
				dateModified={
					new Date(Date.UTC('2019', '04', '06', '20', '32', '18'))
				}
				instancesCount="10"
				onTime="5"
				overdue="5"
				processName="Process test"
			/>
		</Router>
	);

	const instance = component.find(SLAListItem).instance();

	instance.context = {
		showConfirmDialog: () => {}
	};

	instance.showConfirmDialog();

	expect(component).toMatchSnapshot();
});

test('Should render component blocked', () => {
	const component = mount(
		<Router>
			<SLAListItem
				dateModified={
					new Date(Date.UTC('2019', '04', '06', '20', '32', '18'))
				}
				instancesCount="10"
				onTime="5"
				overdue="5"
				processName="Process test"
				status={2}
			/>
		</Router>
	);

	const instance = component.find(SLAListItem).instance();

	instance.context = {
		showConfirmDialog: () => {}
	};

	instance.showConfirmDialog();

	expect(component).toMatchSnapshot();
});
