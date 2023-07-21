/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {cleanup, render} from '@testing-library/react';
import React from 'react';

import InstanceListTable from '../../../../src/main/resources/META-INF/resources/js/components/process-metrics/instance-list/InstanceListTable.es';
import {InstanceListContext} from '../../../../src/main/resources/META-INF/resources/js/components/process-metrics/instance-list/store/InstanceListStore.es';

const instances = [
	{
		assetTitle: 'New Post 1',
		assetType: 'Blog',
		dateCreated: new Date('2019-01-01'),
		id: 1,
		taskNames: []
	},
	{
		assetTitle: 'New Post 2',
		assetType: 'Blog',
		creatorUser: {
			name: 'User 1'
		},
		dateCreated: new Date('2019-01-03'),
		id: 1,
		taskNames: ['Update']
	}
];

describe('The instance list table should', () => {
	afterEach(cleanup);

	test('Be rendered with two items', () => {
		const {getAllByTestId} = render(
			<InstanceListContext.Provider value={{setInstanceId: jest.fn()}}>
				<InstanceListTable items={instances} />
			</InstanceListContext.Provider>
		);

		const instanceRows = getAllByTestId('instanceRow');

		expect(instanceRows.length).toBe(2);
	});
});
