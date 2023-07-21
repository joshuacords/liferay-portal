/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React, {useContext, useEffect, useMemo} from 'react';

import {getFiltersParam} from '../../../shared/components/filter/util/filterUtil.es';
import EmptyState from '../../../shared/components/list/EmptyState.es';
import ReloadButton from '../../../shared/components/list/ReloadButton.es';
import LoadingState from '../../../shared/components/loading/LoadingState.es';
import PaginationBar from '../../../shared/components/pagination/PaginationBar.es';
import PromisesResolver from '../../../shared/components/request/PromisesResolver.es';
import Request from '../../../shared/components/request/Request.es';
import {AppContext} from '../../AppContext.es';
import InstanceItemDetail from './InstanceItemDetail.es';
import InstanceListFilters from './InstanceListFilters.es';
import InstanceListTable from './InstanceListTable.es';
import {InstanceFiltersProvider} from './store/InstanceFiltersStore.es';
import {
	InstanceListContext,
	InstanceListProvider
} from './store/InstanceListStore.es';

export function InstanceListCard({page, pageSize, processId, query}) {
	const filters = getFiltersParam(query);
	const {
		slaStatuses = [],
		statuses = [],
		taskKeys = [],
		timeRange = []
	} = filters;

	const {client, setTitle} = useContext(AppContext);

	useEffect(() => {
		client.get(`/processes/${processId}/title`).then(({data}) => {
			setTitle(`${data}: ${Liferay.Language.get('all-items')}`);

			return data;
		});

		// eslint-disable-next-line react-hooks/exhaustive-deps
	}, []);

	return (
		<Request>
			<InstanceFiltersProvider
				processId={processId}
				processStatusKeys={statuses}
				processStepKeys={taskKeys}
				slaStatusKeys={slaStatuses}
				timeRangeKeys={timeRange}
			>
				<InstanceListProvider
					page={page}
					pageSize={pageSize}
					processId={processId}
					query={query}
				>
					<InstanceListCard.Header
						processId={processId}
						query={query}
					/>

					<InstanceListCard.Body
						page={page}
						pageSize={pageSize}
						processId={processId}
						query={query}
					/>
				</InstanceListProvider>
			</InstanceFiltersProvider>
		</Request>
	);
}

const Body = ({page, pageSize, processId, query}) => {
	const {fetchInstances, items, searching, totalCount} = useContext(
		InstanceListContext
	);

	const emptyMessageText = searching
		? Liferay.Language.get('no-results-were-found')
		: Liferay.Language.get(
				'once-there-are-active-processes-metrics-will-appear-here'
		  );
	const errorMessageText = Liferay.Language.get(
		'there-was-a-problem-retrieving-data-please-try-reloading-the-page'
	);

	// eslint-disable-next-line react-hooks/exhaustive-deps
	const promises = useMemo(() => [fetchInstances()], [
		page,
		pageSize,
		processId,
		query
	]);

	return (
		<>
			<div className="container-fluid-1280 mt-4">
				<PromisesResolver promises={promises}>
					<PromisesResolver.Pending>
						<div className={`border-1 pb-6 pt-6 sheet`}>
							<LoadingState />
						</div>
					</PromisesResolver.Pending>

					<PromisesResolver.Resolved>
						{items && items.length ? (
							<>
								<InstanceListTable items={items} />

								<PaginationBar
									page={page}
									pageCount={items.length}
									pageSize={pageSize}
									totalCount={totalCount}
								/>
							</>
						) : (
							<EmptyState
								className="border-1"
								hideAnimation={false}
								message={emptyMessageText}
								type="not-found"
							/>
						)}
					</PromisesResolver.Resolved>

					<PromisesResolver.Rejected>
						<EmptyState
							actionButton={<ReloadButton />}
							className="border-1"
							hideAnimation={true}
							message={errorMessageText}
							messageClassName="small"
							type="error"
						/>
					</PromisesResolver.Rejected>
				</PromisesResolver>
			</div>

			<InstanceItemDetail processId={processId} />
		</>
	);
};

const Header = () => {
	const {totalCount} = useContext(InstanceListContext);

	return (
		<Request.Success>
			<InstanceListFilters totalCount={totalCount} />
		</Request.Success>
	);
};

InstanceListCard.Body = Body;
InstanceListCard.Header = Header;

export default InstanceListCard;
