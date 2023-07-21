/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React, {useContext} from 'react';

import {getFiltersParam} from '../../../shared/components/filter/util/filterUtil.es';
import Request from '../../../shared/components/request/Request.es';
import {TimeRangeFilter} from '../filter/TimeRangeFilter.es';
import {
	TimeRangeContext,
	TimeRangeProvider
} from '../filter/store/TimeRangeStore.es';
import ProcessItemsCard from './ProcessItemsCard.es';

function CompletedItemsCard({processId, query}) {
	const {timeRange = []} = getFiltersParam(query);

	return (
		<Request>
			<TimeRangeProvider timeRangeKeys={timeRange}>
				<CompletedItemsCard.Body processId={processId} />
			</TimeRangeProvider>
		</Request>
	);
}

const Body = ({processId}) => {
	const {getSelectedTimeRange} = useContext(TimeRangeContext);

	return (
		<ProcessItemsCard
			completed
			description={Liferay.Language.get('completed-items-description')}
			processId={processId}
			timeRange={getSelectedTimeRange()}
			title={Liferay.Language.get('completed-items')}
		>
			<Request.Success>
				<TimeRangeFilter
					filterKey="timeRange"
					hideControl={true}
					position="right"
					showFilterName={false}
				/>
			</Request.Success>
		</ProcessItemsCard>
	);
};

CompletedItemsCard.Body = Body;

export default CompletedItemsCard;
