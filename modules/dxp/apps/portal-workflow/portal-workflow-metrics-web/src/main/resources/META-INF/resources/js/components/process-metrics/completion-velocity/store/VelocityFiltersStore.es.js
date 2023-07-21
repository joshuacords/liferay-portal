/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

import {TimeRangeProvider} from '../../filter/store/TimeRangeStore.es';
import {VelocityUnitProvider} from '../../filter/store/VelocityUnitStore.es';

const VelocityFiltersProvider = ({
	children,
	timeRangeKeys,
	velocityUnitKeys
}) => {
	return (
		<TimeRangeProvider timeRangeKeys={timeRangeKeys}>
			<VelocityUnitProvider velocityUnitKeys={velocityUnitKeys}>
				{children}
			</VelocityUnitProvider>
		</TimeRangeProvider>
	);
};

export {VelocityFiltersProvider};
