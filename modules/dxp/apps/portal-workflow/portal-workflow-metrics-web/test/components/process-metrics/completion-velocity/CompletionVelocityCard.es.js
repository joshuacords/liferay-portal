/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

import CompletionVelocityCard from '../../../../src/main/resources/META-INF/resources/js/components/process-metrics/completion-velocity/CompletionVelocityCard.es';
import {MockRouter as Router} from '../../../mock/MockRouter.es';
import fetch from '../../../mock/fetch.es';

test('Should render component', () => {
	const props = {
		processId: 12345,
		query:
			'?backPath=%2Fprocesses%2F20%2F1%2FoverdueInstanceC…eRange%5B0%5D=30&filters.velocityUnit%5B0%5D=Days'
	};

	const data = {
		items: [
			{
				instanceCount: 1,
				name: 'Task Name',
				onTimeInstanceCount: 1,
				overdueInstanceCount: 0
			}
		],
		totalCount: 1
	};

	const component = mount(
		<Router client={fetch(data)}>
			<CompletionVelocityCard {...props} />
		</Router>
	);

	expect(
		component
			.find('.dashboard-panel-header .mr-2')
			.html()
			.includes(Liferay.Language.get('completion-velocity'))
	).toEqual(true);
});
