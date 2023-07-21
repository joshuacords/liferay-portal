/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

import {ChildLink} from '../../../shared/components/router/routerWrapper.es';
import {AppContext} from '../../AppContext.es';
import {processStatusConstants} from '../filter/store/ProcessStatusStore.es';
import {filterConstants} from '../instance-list/store/InstanceListStore.es';

class WorkloadByStepItem extends React.Component {
	constructor(props) {
		super(props);
	}

	getFiltersQuery(slaStatusFilter) {
		const {taskKey} = this.props;

		return {
			[filterConstants.processStatus]: [processStatusConstants.pending],
			[filterConstants.processStep]: [taskKey],
			[filterConstants.slaStatus]: [slaStatusFilter]
		};
	}

	render() {
		const {defaultDelta} = this.context;
		const {
			instanceCount = '-',
			name,
			onTimeInstanceCount = '-',
			overdueInstanceCount = '-',
			processId
		} = this.props;

		const instancesListPath = `/instances/${processId}/${defaultDelta}/1`;

		return (
			<tr>
				<td className="lfr-title-column table-cell-expand table-cell-minw-200 table-title">
					{name}
				</td>

				<td className="text-right">
					<ChildLink
						className="workload-by-step-link"
						query={{filters: this.getFiltersQuery('Overdue')}}
						to={instancesListPath}
					>
						{overdueInstanceCount}
					</ChildLink>
				</td>

				<td className="text-right">
					<ChildLink
						className="workload-by-step-link"
						query={{filters: this.getFiltersQuery('OnTime')}}
						to={instancesListPath}
					>
						{onTimeInstanceCount}
					</ChildLink>
				</td>

				<td className="text-right">
					<ChildLink
						className="workload-by-step-link"
						query={{filters: this.getFiltersQuery()}}
						to={instancesListPath}
					>
						{instanceCount}
					</ChildLink>
				</td>
			</tr>
		);
	}
}

WorkloadByStepItem.contextType = AppContext;
export default WorkloadByStepItem;
