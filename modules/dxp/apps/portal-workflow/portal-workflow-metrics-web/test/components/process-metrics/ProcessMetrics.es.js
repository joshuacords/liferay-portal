/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

import ProcessMetrics from '../../../src/main/resources/META-INF/resources/js/components/process-metrics/ProcessMetrics.es';
import PendingItemsCard from '../../../src/main/resources/META-INF/resources/js/components/process-metrics/process-items/PendingItemsCard.es';
import WorkloadByStepCard from '../../../src/main/resources/META-INF/resources/js/components/process-metrics/workload-by-step/WorkloadByStepCard.es';
import {withParams} from '../../../src/main/resources/META-INF/resources/js/shared/components/router/routerUtil.es';
import {MockRouter as Router} from '../../mock/MockRouter.es';
import fetch from '../../mock/fetch.es';
import fetchFailure from '../../mock/fetchFailure.es';

beforeAll(() => {
	const vbody = document.createElement('div');

	vbody.innerHTML = `
		<div id="workflow">
			<div class="user-control-group">
				<div class="control-menu-icon"></div>
			</div>
		</div>
	`;
	document.body.appendChild(vbody);
});

test('Should render component with completed tab activated', () => {
	const component = mount(
		<Router client={fetchFailure()} initialPath="/metrics/35315">
			<ProcessMetrics processId={35315} />
		</Router>
	);

	expect(component).toMatchSnapshot();
});

test('Should render component with default tab activated', () => {
	const data = {
		id: 35315,
		onTimeInstanceCount: 1,
		overdueInstanceCount: 0,
		title: 'Single Approver',
		totalCount: 1
	};

	const component = mount(
		<Router client={fetch({data})} initialPath="/metrics/35315">
			<ProcessMetrics processId={35315} />
		</Router>
	);

	expect(component).toMatchSnapshot();
});

test('Should render component with failure state', () => {
	const component = mount(
		<Router client={fetchFailure()} initialPath="/metrics/35315/completed">
			<ProcessMetrics processId={35315} />
		</Router>
	);

	expect(component).toMatchSnapshot();
});

test('Should render dashboard route children', () => {
	const component = mount(
		<Router client={fetch({data: {}})}>
			{withParams(
				PendingItemsCard,
				WorkloadByStepCard
			)({
				location: {
					search: ''
				},
				match: {
					params: {
						processId: 35315
					}
				}
			})}
		</Router>
	);

	expect(component).toMatchSnapshot();
});

test('Should render with blocked SLA', () => {
	const component = mount(
		<Router client={fetchFailure()} initialPath="/metrics/35315/completed">
			<ProcessMetrics processId="123" />
		</Router>
	);

	const instance = component.find(ProcessMetrics).instance();

	instance.setState({blockedSLACount: 1}, () => {
		expect(component).toMatchSnapshot();
	});
});
