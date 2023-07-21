/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';
import renderer from 'react-test-renderer';

import SLAConfirmDialog from '../../../src/main/resources/META-INF/resources/js/components/sla/SLAConfirmDialog.es';
import SLAListCard from '../../../src/main/resources/META-INF/resources/js/components/sla/SLAListCard.es';
import {MockRouter as Router} from '../../mock/MockRouter.es';
import fetch from '../../mock/fetch.es';

jest.useFakeTimers();

test('Should render component', () => {
	const component = renderer.create(<SLAConfirmDialog itemToRemove={1234} />);

	const tree = component.toJSON();

	expect(tree).toMatchSnapshot();
});

test('Should cancel dialog', () => {
	const component = mount(
		<Router>
			<SLAConfirmDialog itemToRemove={1234} />
		</Router>
	);

	const dialog = component.find(SLAConfirmDialog).instance();

	dialog.context = {
		hideConfirmDialog: () => {}
	};

	dialog.cancel();

	expect(component).toMatchSnapshot();
});

test('Should cancel dialog through SLA List', () => {
	const data = {
		items: [
			{
				description: 'Total time to complete the request.',
				duration: 1553879089,
				id: 1234,
				name: 'Total resolution time'
			}
		],
		totalCount: 0
	};

	const component = mount(
		<Router client={fetch(data)}>
			<SLAListCard />
		</Router>
	);

	const {slaContextState} = component.find(SLAListCard).instance();

	slaContextState.showConfirmDialog(1234);
	jest.runAllTimers();
	expect(component).toMatchSnapshot();

	slaContextState.hideConfirmDialog();
	jest.runAllTimers();
	expect(component).toMatchSnapshot();
});

test('Should remove item', () => {
	const component = mount(
		<Router>
			<SLAConfirmDialog itemToRemove={1234} />
		</Router>
	);

	const dialog = component.find(SLAConfirmDialog).instance();

	dialog.context = {
		removeItem: () => {}
	};

	dialog.removeItem();

	expect(component).toMatchSnapshot();
});
