/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

import {TimeRangeFilter} from '../filter/TimeRangeFilter.es';
import {VelocityUnitFilter} from '../filter/VelocityUnitFilter.es';

const VelocityFilters = () => {
	return (
		<div className="autofit-col m-0 management-bar management-bar-light navbar">
			<ul className="navbar-nav">
				<TimeRangeFilter
					filterKey="velocityTimeRange"
					hideControl={true}
					position="right"
					showFilterName={false}
				/>

				<VelocityUnitFilter hideControl={true} position="right" />
			</ul>
		</div>
	);
};

export default VelocityFilters;
