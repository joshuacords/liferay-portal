/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayList from '@clayui/list';
import classNames from 'classnames';
import PropTypes from 'prop-types';
import React from 'react';

function TimelineEntry(props) {
	return (
		<li className="timeline-item">
			<div className="panel panel-secondary">
				<div className="timeline-increment">
					<span className="timeline-icon"></span>
				</div>
				<div className="panel-body">
					<div className="mb-2 row">
						<div className="col">
							<h4 className="mb-0">{props.title}</h4>
						</div>
						<div className="col-auto">{props.description}</div>
					</div>
					<small>{props.date}</small>
				</div>
			</div>
		</li>
	);
}

TimelineEntry.propTypes = {
	date: PropTypes.string.isRequired,
	description: PropTypes.string.isRequired,
	title: PropTypes.string.isRequired
};

TimelineEntry.defaultProps = {};

function Timeline(props) {
	return (
		<ClayList className={classNames('mb-0', 'timeline')}>
			{props.items.map((item, i) => (
				<TimelineEntry
					key={i}
					{...item}
					borderBottom={i !== props.items.length - 1}
					datasetDisplayContext={props.datasetDisplayContext}
				/>
			))}
		</ClayList>
	);
}

Timeline.propTypes = {
	dataRenderers: PropTypes.object,
	datasetDisplayContext: PropTypes.any,
	items: PropTypes.array
};

Timeline.defaultProps = {
	items: []
};

export default Timeline;
