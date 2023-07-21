/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';
import renderer from 'react-test-renderer';

import SLAListTable from '../../../src/main/resources/META-INF/resources/js/components/sla/SLAListTable.es';
import {MockRouter as Router} from '../../mock/MockRouter.es';

test('Should render component', () => {
	const data = [
		{
			dateModified: new Date(
				Date.UTC('2019', '04', '06', '20', '32', '18')
			),
			description: 'Total time to complete the request.',
			duration: 863999940000,
			name: 'Total resolution time'
		},
		{
			dateModified: new Date(
				Date.UTC('2019', '04', '06', '20', '32', '18')
			),
			description: 'Total time to complete the request.',
			duration: 60000,
			name: 'Total resolution time'
		},
		{
			dateModified: new Date(
				Date.UTC('2019', '04', '06', '20', '32', '18')
			),
			description: 'Total time to complete the request.',
			duration: 7140000,
			name: 'Total resolution time'
		}
	];

	const component = renderer.create(
		<Router>
			<SLAListTable items={data} />
		</Router>
	);

	const tree = component.toJSON();

	expect(tree).toMatchSnapshot();
});
