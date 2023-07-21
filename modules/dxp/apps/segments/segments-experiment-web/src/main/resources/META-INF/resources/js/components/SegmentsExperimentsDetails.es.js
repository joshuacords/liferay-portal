/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

import {SegmentsExperimentType} from '../types.es';
import {indexToPercentageString} from '../util/percentages.es';
import {STATUS_DRAFT} from '../util/statuses.es';

function SegmentsExperimentsDetails({segmentsExperiment}) {
	const {
		confidenceLevel,
		goal,
		segmentsEntryName,
		status
	} = segmentsExperiment;

	return (
		<>
			<h4 className="mb-3 mt-4 sheet-subtitle">
				{Liferay.Language.get('details')}
			</h4>

			<dl>
				<div className="d-flex">
					<dt>{Liferay.Language.get('segment') + ':'} </dt>
					<dd className="ml-2 text-secondary">{segmentsEntryName}</dd>
				</div>

				<div className="d-flex">
					<dt>{Liferay.Language.get('goal') + ':'} </dt>
					<dd className="ml-2 text-secondary">{goal.label}</dd>
				</div>

				{status.value !== STATUS_DRAFT && (
					<div className="d-flex">
						<dt>
							{Liferay.Language.get('confidence-level') + ':'}{' '}
						</dt>
						<dd className="ml-2 text-secondary">
							{indexToPercentageString(confidenceLevel)}
						</dd>
					</div>
				)}
			</dl>
		</>
	);
}

SegmentsExperimentsDetails.propTypes = {
	segmentsExperiment: SegmentsExperimentType
};

export default SegmentsExperimentsDetails;
