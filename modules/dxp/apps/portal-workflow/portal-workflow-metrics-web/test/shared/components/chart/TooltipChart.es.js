/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import TooltipChart from '../../../../src/main/resources/META-INF/resources/js/shared/components/chart/TooltipChart.es';

test('Should render component', () => {
	const header = [{label: 'Thu, Aug 1', weight: 'semibold', width: 160}];
	const rows = [{columns: [{label: '10 Inst / Day', weight: 'normal'}]}];

	const body = TooltipChart({header, rows});

	expect(body.includes('Thu, Aug 1')).toBe(true);
	expect(body.includes('10 Inst / Day')).toBe(true);
});
