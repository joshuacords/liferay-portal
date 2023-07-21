/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import '@testing-library/jest-dom/extend-expect';
import {cleanup, render} from '@testing-library/react';
import React from 'react';

import SegmentsExperimentsClickGoal from '../../../src/main/resources/META-INF/resources/js/components/ClickGoalPicker/ClickGoalPicker.es';
import {segmentsExperiment} from '../fixtures.es';

describe('SegmentsExperimentsClickGoal', () => {
	afterEach(cleanup);

	it('renders when goal value is "click"', () => {
		const experiment = {
			...segmentsExperiment,
			goal: {
				value: 'click'
			}
		};

		const {asFragment} = render(
			<SegmentsExperimentsClickGoal segmentsExperiment={experiment} />
		);

		expect(asFragment().children.length).not.toBe(0);
	});

	test.todo('actually make the one above a meaningful test');
});
