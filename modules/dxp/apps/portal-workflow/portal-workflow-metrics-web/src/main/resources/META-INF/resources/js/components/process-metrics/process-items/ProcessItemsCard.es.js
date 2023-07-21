/* eslint-disable react-hooks/exhaustive-deps */
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React, {useContext, useEffect, useState} from 'react';

import Icon from '../../../shared/components/Icon.es';
import Panel from '../../../shared/components/Panel.es';
import Tooltip from '../../../shared/components/Tooltip.es';
import {ErrorContext} from '../../../shared/components/request/Error.es';
import {LoadingContext} from '../../../shared/components/request/Loading.es';
import Request from '../../../shared/components/request/Request.es';
import {AppContext} from '../../AppContext.es';
import PANELS from './Panels.es';
import SummaryCard from './SummaryCard.es';

function ProcessItemsCard({
	children,
	completed,
	description,
	processId,
	timeRange,
	title
}) {
	return (
		<Request>
			<Panel>
				<ProcessItemsCard.Header
					description={description}
					title={title}
				>
					{children}
				</ProcessItemsCard.Header>

				<ProcessItemsCard.Body
					completed={completed}
					processId={processId}
					timeRange={timeRange}
				/>
			</Panel>
		</Request>
	);
}

const Body = ({completed = false, processId, timeRange}) => {
	const {client, setTitle} = useContext(AppContext);
	const {setError} = useContext(ErrorContext);
	const {setLoading} = useContext(LoadingContext);

	const [process, setProcess] = useState(null);

	const fetchData = () => {
		setError(null);
		setLoading(true);

		const isValidDate = date => date && !isNaN(date);

		let urlRequest = `/processes/${processId}?completed=${completed}`;

		if (
			timeRange &&
			isValidDate(timeRange.dateEnd) &&
			isValidDate(timeRange.dateStart)
		) {
			const {dateEnd, dateStart} = timeRange;

			urlRequest += `&dateEnd=${dateEnd.toISOString()}&dateStart=${dateStart.toISOString()}`;
		}

		return client
			.get(urlRequest)
			.then(({data}) => {
				setTitle(data.title);
				setProcess(data);
			})
			.catch(error => {
				setError(error);
			})
			.then(() => {
				setLoading(false);
			});
	};

	useEffect(() => {
		fetchData();
	}, [timeRange]);

	return (
		<Panel.Body>
			<Request.Error />

			<Request.Loading />

			<Request.Success>
				{process && (
					<div className={'d-flex pb-4 pt-1'}>
						{PANELS.map((panel, index) => (
							<SummaryCard
								{...panel}
								completed={completed}
								key={index}
								processId={processId}
								timeRange={timeRange}
								total={
									panel.addressedToField === panel.totalField
								}
								totalValue={process[panel.totalField]}
								value={process[panel.addressedToField]}
							/>
						))}
					</div>
				)}
			</Request.Success>
		</Panel.Body>
	);
};

const Header = ({children, description, title}) => (
	<Panel.Header
		elementClasses={['dashboard-panel-header', children && 'pb-0']}
	>
		<div className="autofit-row">
			<div className="autofit-col autofit-col-expand flex-row">
				<span className="mr-2">{title}</span>

				<Tooltip message={description} position="right" width="288">
					<Icon iconName={'question-circle-full'} />
				</Tooltip>
			</div>

			{children && (
				<Request.Success>
					<div className="autofit-col m-0 management-bar management-bar-light navbar">
						<ul className="navbar-nav">{children}</ul>
					</div>
				</Request.Success>
			)}
		</div>
	</Panel.Header>
);

ProcessItemsCard.Body = Body;
ProcessItemsCard.Header = Header;

export default ProcessItemsCard;
