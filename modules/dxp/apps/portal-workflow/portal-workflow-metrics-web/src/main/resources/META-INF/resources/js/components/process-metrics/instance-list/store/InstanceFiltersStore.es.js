/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

import {ProcessStatusProvider} from '../../filter/store/ProcessStatusStore.es';
import {ProcessStepProvider} from '../../filter/store/ProcessStepStore.es';
import {SLAStatusProvider} from '../../filter/store/SLAStatusStore.es';
import {TimeRangeProvider} from '../../filter/store/TimeRangeStore.es';

const InstanceFiltersProvider = ({
	children,
	processId,
	processStatusKeys,
	processStepKeys,
	slaStatusKeys,
	timeRangeKeys
}) => {
	return (
		<SLAStatusProvider slaStatusKeys={slaStatusKeys}>
			<ProcessStatusProvider processStatusKeys={processStatusKeys}>
				<TimeRangeProvider timeRangeKeys={timeRangeKeys}>
					<ProcessStepProvider
						processId={processId}
						processStepKeys={processStepKeys}
					>
						{children}
					</ProcessStepProvider>
				</TimeRangeProvider>
			</ProcessStatusProvider>
		</SLAStatusProvider>
	);
};

export {InstanceFiltersProvider};
