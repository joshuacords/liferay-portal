/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {cleanup, render} from '@testing-library/react';
import React from 'react';

import ProcessStatusFilter from '../../../../src/main/resources/META-INF/resources/js/components/process-metrics/filter/ProcessStatusFilter.es';
import {ProcessStatusProvider} from '../../../../src/main/resources/META-INF/resources/js/components/process-metrics/filter/store/ProcessStatusStore.es';
import {MockRouter} from '../../../mock/MockRouter.es';

describe('The process status filter should', () => {
	afterEach(cleanup);

	test('Be rendered with "completed" and "pending" items', () => {
		const {getAllByTestId} = render(
			<MockProcessStatusContext>
				<ProcessStatusFilter />
			</MockProcessStatusContext>
		);

		const filterItemNames = getAllByTestId('filterItemName');

		expect(filterItemNames[0].innerHTML).toBe(
			Liferay.Language.get('completed')
		);
		expect(filterItemNames[1].innerHTML).toBe(
			Liferay.Language.get('pending')
		);
	});
});

const MockProcessStatusContext = ({children}) => (
	<MockRouter>
		<ProcessStatusProvider processStatusKeys={[]}>
			{children}
		</ProcessStatusProvider>
	</MockRouter>
);
