/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {cleanup, render} from '@testing-library/react';
import React from 'react';

import {VelocityUnitFilter} from '../../../../src/main/resources/META-INF/resources/js/components/process-metrics/filter/VelocityUnitFilter.es';
import {TimeRangeContext} from '../../../../src/main/resources/META-INF/resources/js/components/process-metrics/filter/store/TimeRangeStore.es';
import {VelocityUnitProvider} from '../../../../src/main/resources/META-INF/resources/js/components/process-metrics/filter/store/VelocityUnitStore.es';
import {MockRouter} from '../../../mock/MockRouter.es';

const timeRange = {
	dateEnd: new Date(2018, 3, 1),
	dateStart: new Date(2018, 1, 1)
};

describe('The velocity unit filter should', () => {
	afterEach(cleanup);

	test('Be rendered with "inst-day", "inst-week", and "inst-month" items', () => {
		const {getAllByTestId} = render(
			<MockVelocityUnitContext
				dateEnd={new Date(2018, 3, 1)}
				dateStart={new Date(2018, 1, 1)}
			>
				<VelocityUnitFilter />
			</MockVelocityUnitContext>
		);

		const filterItemNames = getAllByTestId('filterItemName');

		expect(filterItemNames[0].innerHTML).toBe(
			Liferay.Language.get('inst-day')
		);
		expect(filterItemNames[1].innerHTML).toBe(
			Liferay.Language.get('inst-week')
		);
		expect(filterItemNames[2].innerHTML).toBe(
			Liferay.Language.get('inst-month')
		);
	});
});

const MockVelocityUnitContext = ({children}) => (
	<MockRouter>
		<TimeRangeContext.Provider
			value={{
				getSelectedTimeRange: () => timeRange
			}}
		>
			<VelocityUnitProvider velocityUnitKeys={[]}>
				{children}
			</VelocityUnitProvider>
		</TimeRangeContext.Provider>
	</MockRouter>
);
