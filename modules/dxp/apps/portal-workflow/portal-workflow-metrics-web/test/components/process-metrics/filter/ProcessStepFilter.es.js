/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {cleanup, render} from '@testing-library/react';
import React from 'react';

import ProcessStepFilter from '../../../../src/main/resources/META-INF/resources/js/components/process-metrics/filter/ProcessStepFilter.es';
import {ProcessStepContext} from '../../../../src/main/resources/META-INF/resources/js/components/process-metrics/filter/store/ProcessStepStore.es';
import {MockRouter} from '../../../mock/MockRouter.es';

const processSteps = [
	{
		key: 'review',
		name: 'Review'
	},
	{
		key: 'update',
		name: 'Update'
	}
];

describe('The time range filter should', () => {
	let getAllByTestId;

	afterEach(cleanup);

	beforeEach(() => {
		const renderResult = render(
			<MockRouter>
				<ProcessStepContext.Provider value={{processSteps}}>
					<ProcessStepFilter />
				</ProcessStepContext.Provider>
			</MockRouter>
		);

		getAllByTestId = renderResult.getAllByTestId;
	});

	test('Be rendered with "Review" and "Update" items', () => {
		const filterItemNames = getAllByTestId('filterItemName');

		expect(filterItemNames[0].innerHTML).toBe('Review');
		expect(filterItemNames[1].innerHTML).toBe('Update');
	});
});
