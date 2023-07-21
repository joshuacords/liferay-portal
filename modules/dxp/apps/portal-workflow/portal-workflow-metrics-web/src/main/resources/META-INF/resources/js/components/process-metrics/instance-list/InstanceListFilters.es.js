/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React, {useContext} from 'react';

import FilterResultsBar from '../../../shared/components/filter/FilterResultsBar.es';
import ProcessStatusFilter from '../filter/ProcessStatusFilter.es';
import ProcessStepFilter from '../filter/ProcessStepFilter.es';
import SLAStatusFilter from '../filter/SLAStatusFilter.es';
import {TimeRangeFilter} from '../filter/TimeRangeFilter.es';
import {ProcessStatusContext} from '../filter/store/ProcessStatusStore.es';
import {ProcessStepContext} from '../filter/store/ProcessStepStore.es';
import {SLAStatusContext} from '../filter/store/SLAStatusStore.es';
import {TimeRangeContext} from '../filter/store/TimeRangeStore.es';
import {filterConstants} from './store/InstanceListStore.es';

const InstanceListFilters = ({totalCount}) => {
	const {isCompletedStatusSelected, processStatuses} = useContext(
		ProcessStatusContext
	);
	const {processSteps} = useContext(ProcessStepContext);
	const {slaStatuses} = useContext(SLAStatusContext);
	const {timeRanges} = useContext(TimeRangeContext);

	const completedStatusSelected = isCompletedStatusSelected();

	const getFilters = () => {
		const asFilterObject = (items, key, name, pinned = false) => ({
			items,
			key,
			name,
			pinned
		});

		const filters = [
			asFilterObject(
				slaStatuses,
				filterConstants.slaStatus,
				Liferay.Language.get('sla-status')
			),
			asFilterObject(
				processStatuses,
				filterConstants.processStatus,
				Liferay.Language.get('process-status')
			)
		];

		if (completedStatusSelected) {
			filters.push(
				asFilterObject(
					timeRanges,
					filterConstants.timeRange,
					Liferay.Language.get('completion-period'),
					true
				)
			);
		}

		filters.push(
			asFilterObject(
				processSteps,
				filterConstants.processStep,
				Liferay.Language.get('process-step')
			)
		);

		return filters;
	};

	return (
		<>
			<nav className="management-bar management-bar-light navbar navbar-expand-md">
				<div className="container-fluid container-fluid-max-xl">
					<ul className="navbar-nav">
						<li className="nav-item">
							<strong className="ml-0 mr-0 navbar-text">
								{Liferay.Language.get('filter-by')}
							</strong>
						</li>

						<SLAStatusFilter />

						<ProcessStatusFilter />

						{completedStatusSelected && <TimeRangeFilter />}

						<ProcessStepFilter />
					</ul>
				</div>
			</nav>

			<FilterResultsBar filters={getFilters()} totalCount={totalCount} />
		</>
	);
};

export default InstanceListFilters;
