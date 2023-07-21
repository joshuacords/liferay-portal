/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';
import renderer from 'react-test-renderer';

import ResultsBar from '../../../src/main/resources/META-INF/resources/js/components/process-list/ResultsBar.es';
import {MockRouter as Router} from '../../mock/MockRouter.es';

test('Should render component with one item', () => {
	const component = renderer.create(
		<Router>
			<ResultsBar
				match={{
					params: {
						page: 1,
						pageSize: 5,
						search: 'test',
						sort: encodeURIComponent('title:asc')
					},
					path: '/processes/:pageSize/:page/:sort/:search?'
				}}
				totalCount={1}
			/>
		</Router>
	);

	const tree = component.toJSON();

	expect(tree).toMatchSnapshot();
});

test('Should render component with one list item', () => {
	const component = renderer.create(
		<Router>
			<ResultsBar
				match={{
					params: {
						page: 1,
						pageSize: 5,
						search: 'test',
						sort: encodeURIComponent('title:asc')
					},
					path: '/processes/:pageSize/:page/:sort/:search?'
				}}
				totalCount={5}
			/>
		</Router>
	);

	const tree = component.toJSON();

	expect(tree).toMatchSnapshot();
});
