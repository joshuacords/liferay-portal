/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';
import renderer from 'react-test-renderer';

import {FilterResultsItem} from '../../../../src/main/resources/META-INF/resources/js/shared/components/filter/FilterResultsItem.es';
import {MockRouter as Router} from '../../../mock/MockRouter.es';

test('Should render component', () => {
	const filter = {
		items: [
			{
				active: true,
				key: 'overdue',
				name: 'Overdue'
			},
			{
				active: false,
				key: 'ontime',
				name: 'On Time'
			}
		],
		key: 'slaStatus',
		name: 'SLA Status'
	};

	const component = renderer.create(
		<Router>
			<FilterResultsItem
				filter={filter}
				item={filter.items[0]}
				location={{
					pathname: '/instances/3',
					search: '?filters.slaStatus%5B0%5D=overdue'
				}}
				match={{params: {page: 3}, path: '/instances/:page'}}
			/>
		</Router>
	);

	const tree = component.toJSON();

	expect(tree).toMatchSnapshot();
});

test('Should remove item', () => {
	const filter = {
		items: [
			{
				active: true,
				key: 'overdue',
				name: 'Overdue'
			},
			{
				active: false,
				key: 'ontime',
				name: 'On Time'
			}
		],
		key: 'slaStatus',
		name: 'SLA Status'
	};

	const mockHistory = {
		push: jest.fn()
	};

	const component = mount(
		<Router>
			<FilterResultsItem
				filter={filter}
				history={mockHistory}
				item={filter.items[0]}
				location={{
					pathname: '/instances/3',
					search: '?filters.slaStatus%5B0%5D=overdue'
				}}
				match={{params: {page: 3}, path: '/instances/:page'}}
			/>
		</Router>
	);

	const instance = component.find(FilterResultsItem).instance();

	instance.onRemoveButtonClick();

	expect(mockHistory.push).toHaveBeenCalled();
});
