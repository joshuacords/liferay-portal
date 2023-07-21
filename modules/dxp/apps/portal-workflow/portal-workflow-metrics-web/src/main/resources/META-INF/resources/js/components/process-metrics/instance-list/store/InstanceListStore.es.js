/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React, {createContext, useContext, useState} from 'react';

import {getFiltersParam} from '../../../../shared/components/filter/util/filterUtil.es';
import {AppContext} from '../../../AppContext.es';
import {ProcessStatusContext} from '../../filter/store/ProcessStatusStore.es';
import {ProcessStepContext} from '../../filter/store/ProcessStepStore.es';
import {SLAStatusContext} from '../../filter/store/SLAStatusStore.es';
import {TimeRangeContext} from '../../filter/store/TimeRangeStore.es';

const filterConstants = {
	processStatus: 'statuses',
	processStep: 'taskKeys',
	slaStatus: 'slaStatuses',
	timeRange: 'timeRange',
	timeRangeDateEnd: 'dateEnd',
	timeRangeDateStart: 'dateStart'
};

const reduceFilters = (filterItems, paramKey) =>
	filterItems.reduce((acc, cur) => `&${paramKey}=${cur.key}${acc}`, '');

const useInstanceListData = (page, pageSize, processId, query) => {
	const [instanceId, setInstanceId] = useState();
	const [items, setItems] = useState([]);
	const [loading] = useState([]);
	const [searching, setSearching] = useState();
	const [totalCount, setTotalCount] = useState();

	const {client} = useContext(AppContext);
	const {getSelectedProcessStatuses, isCompletedStatusSelected} = useContext(
		ProcessStatusContext
	);
	const {getSelectedProcessSteps} = useContext(ProcessStepContext);
	const {getSelectedSLAStatuses} = useContext(SLAStatusContext);
	const {getSelectedTimeRange} = useContext(TimeRangeContext);

	const filters = getFiltersParam(query);

	const getInstancesRequestURL = () => {
		setSearching(false);

		let baseURL = `/processes/${processId}/instances?page=${page}&pageSize=${pageSize}`;

		const selectedProcessStatuses = getSelectedProcessStatuses(
			filters[filterConstants.processStatus]
		);
		const selectedProcessSteps = getSelectedProcessSteps(
			filters[filterConstants.processStep]
		);
		const selectedSLAStatuses = getSelectedSLAStatuses(
			filters[filterConstants.slaStatus]
		);
		const selectedTimeRange = getSelectedTimeRange(
			filters[filterConstants.timeRange],
			filters[filterConstants.timeRangeDateEnd],
			filters[filterConstants.timeRangeDateStart]
		);

		if (selectedProcessStatuses && selectedProcessStatuses.length) {
			setSearching(true);

			baseURL += reduceFilters(
				selectedProcessStatuses,
				filterConstants.processStatus
			);
		}

		if (selectedProcessSteps && selectedProcessSteps.length) {
			setSearching(true);

			baseURL += reduceFilters(
				selectedProcessSteps,
				filterConstants.processStep
			);
		}

		if (selectedSLAStatuses && selectedSLAStatuses.length) {
			setSearching(true);

			baseURL += reduceFilters(
				selectedSLAStatuses,
				filterConstants.slaStatus
			);
		}

		if (
			isCompletedStatusSelected(filters[filterConstants.processStatus]) &&
			selectedTimeRange
		) {
			setSearching(true);

			baseURL += `&${
				filterConstants.timeRangeDateEnd
			}=${selectedTimeRange.dateEnd.toISOString()}`;
			baseURL += `&${
				filterConstants.timeRangeDateStart
			}=${selectedTimeRange.dateStart.toISOString()}`;
		}

		return baseURL;
	};

	const fetchInstances = () => {
		return client.get(getInstancesRequestURL()).then(({data}) => {
			setItems(data.items);
			setTotalCount(data.totalCount);

			return data;
		});
	};

	return {
		fetchInstances,
		instanceId,
		items,
		loading,
		searching,
		setInstanceId,
		totalCount
	};
};

const InstanceListContext = createContext(null);

const InstanceListProvider = ({children, page, pageSize, processId, query}) => {
	return (
		<InstanceListContext.Provider
			value={useInstanceListData(page, pageSize, processId, query)}
		>
			{children}
		</InstanceListContext.Provider>
	);
};

export {
	filterConstants,
	InstanceListContext,
	InstanceListProvider,
	useInstanceListData
};
