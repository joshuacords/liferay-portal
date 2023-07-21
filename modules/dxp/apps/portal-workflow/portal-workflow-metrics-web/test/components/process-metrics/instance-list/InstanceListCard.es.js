/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {cleanup, render} from '@testing-library/react';
import React from 'react';

import InstanceListCard from '../../../../src/main/resources/META-INF/resources/js/components/process-metrics/instance-list/InstanceListCard.es';
import {MockRouter} from '../../../mock/MockRouter.es';

const items = [
	{
		key: 'review',
		name: 'Review'
	},
	{
		key: 'update',
		name: 'Update'
	}
];

describe('The instance list card should', () => {
	const clientMock = {
		get: jest.fn().mockResolvedValue({data: {items}})
	};
	let renderResult;

	afterEach(cleanup);

	beforeEach(() => {
		renderResult = render(
			<MockRouter client={clientMock}>
				<InstanceListCard />
			</MockRouter>
		);
	});

	test('Be rendered with "sla-status", "process-status", and "process-step" filters', () => {
		const {getAllByTestId} = renderResult;

		const filterNames = getAllByTestId('filterName');

		expect(filterNames[0].innerHTML).toBe('sla-status');
		expect(filterNames[1].innerHTML).toBe('process-status');
		expect(filterNames[2].innerHTML).toBe('process-step');
	});
});
