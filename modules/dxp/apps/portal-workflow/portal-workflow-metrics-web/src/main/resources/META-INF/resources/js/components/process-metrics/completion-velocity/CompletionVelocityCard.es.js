/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

import Panel from '../../../shared/components/Panel.es';
import {getFiltersParam} from '../../../shared/components/filter/util/filterUtil.es';
import Request from '../../../shared/components/request/Request.es';
import {ProcessVelocityInfo} from './ProcessVelocityInfo.es';
import VelocityChart from './VelocityChart.es';
import VelocityFilters from './VelocityFilters.es';
import {VelocityDataProvider} from './store/VelocityDataStore.es';
import {VelocityFiltersProvider} from './store/VelocityFiltersStore.es';

const CompletionVelocityCard = ({processId, query}) => {
	const {velocityTimeRange = [], velocityUnit = []} = getFiltersParam(query);

	return (
		<Request>
			<VelocityFiltersProvider
				timeRangeKeys={velocityTimeRange}
				velocityUnitKeys={velocityUnit}
			>
				<VelocityDataProvider processId={processId}>
					<Panel>
						<Panel.HeaderWithOptions
							description={Liferay.Language.get(
								'completion-velocity-description'
							)}
							elementClasses="dashboard-panel-header pb-0"
							title={Liferay.Language.get('completion-velocity')}
						>
							<Request.Success>
								<VelocityFilters />
							</Request.Success>
						</Panel.HeaderWithOptions>

						<Request.Error />

						<Request.Loading />

						<Request.Success>
							<Panel.Body elementClasses="pt-0">
								<ProcessVelocityInfo />

								<VelocityChart />
							</Panel.Body>
						</Request.Success>
					</Panel>
				</VelocityDataProvider>
			</VelocityFiltersProvider>
		</Request>
	);
};

export default CompletionVelocityCard;
