/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {cleanup, render} from '@testing-library/react';
import React from 'react';

import SLAStatusFilter from '../../../../src/main/resources/META-INF/resources/js/components/process-metrics/filter/SLAStatusFilter.es';
import {SLAStatusProvider} from '../../../../src/main/resources/META-INF/resources/js/components/process-metrics/filter/store/SLAStatusStore.es';
import {MockRouter} from '../../../mock/MockRouter.es';

describe('The SLA status filter should', () => {
	afterEach(cleanup);

	test('Be rendered with "onTime", "overdue", and "pending" items', () => {
		const {getAllByTestId} = render(
			<MockSLAStatusContext>
				<SLAStatusFilter />
			</MockSLAStatusContext>
		);

		const filterItemNames = getAllByTestId('filterItemName');

		expect(filterItemNames[0].innerHTML).toBe(
			Liferay.Language.get('on-time')
		);
		expect(filterItemNames[1].innerHTML).toBe(
			Liferay.Language.get('overdue')
		);
		expect(filterItemNames[2].innerHTML).toBe(
			Liferay.Language.get('untracked')
		);
	});
});

const MockSLAStatusContext = ({children}) => (
	<MockRouter>
		<SLAStatusProvider slaStatusKeys={[]}>{children}</SLAStatusProvider>
	</MockRouter>
);
