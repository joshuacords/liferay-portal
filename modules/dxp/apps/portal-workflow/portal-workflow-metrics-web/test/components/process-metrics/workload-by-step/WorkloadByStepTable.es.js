/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';
import renderer from 'react-test-renderer';

import WorkloadByStepTable from '../../../../src/main/resources/META-INF/resources/js/components/process-metrics/workload-by-step/WorkloadByStepTable.es';
import {MockRouter as Router} from '../../../mock/MockRouter.es';
import fetch from '../../../mock/fetch.es';

test('Should display hyphen when the task has no count', () => {
	const data = [
		{
			name: 'Single Approver'
		}
	];

	const component = renderer.create(
		<Router client={fetch(data)}>
			<WorkloadByStepTable items={data} />
		</Router>
	);

	const tree = component.toJSON();

	expect(tree).toMatchSnapshot();
});

test('Should render component', () => {
	const data = [
		{
			instanceCount: 1,
			name: 'Single Approver',
			onTimeInstanceCount: 1,
			overdueInstanceCount: 0
		}
	];

	const component = renderer.create(
		<Router client={fetch(data)}>
			<WorkloadByStepTable items={data} />
		</Router>
	);

	const tree = component.toJSON();

	expect(tree).toMatchSnapshot();
});
