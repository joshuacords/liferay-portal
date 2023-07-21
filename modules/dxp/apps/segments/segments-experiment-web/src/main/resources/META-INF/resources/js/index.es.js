/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {render} from 'frontend-js-react-web';
import React from 'react';

import SegmentsExperimentsSidebar from './components/SegmentsExperimentsSidebar.es';
import SegmentsExperimentsContext from './context.es';
import APIService from './util/APIService.es';

function renderComponent({context, props}) {
	const {assetsPath, endpoints, page} = context;
	const {
		calculateSegmentsExperimentEstimatedDurationURL,
		createSegmentsExperimentURL,
		createSegmentsVariantURL,
		deleteSegmentsExperimentURL,
		deleteSegmentsVariantURL,
		editSegmentsExperimentStatusURL,
		editSegmentsExperimentURL,
		editSegmentsVariantLayoutURL,
		editSegmentsVariantURL,
		runSegmentsExperimentURL
	} = endpoints;

	return (
		<SegmentsExperimentsContext.Provider
			value={{
				APIService: APIService({
					contentPageEditorNamespace:
						context.contentPageEditorNamespace,
					endpoints: {
						calculateSegmentsExperimentEstimatedDurationURL,
						createSegmentsExperimentURL,
						createSegmentsVariantURL,
						deleteSegmentsExperimentURL,
						deleteSegmentsVariantURL,
						editSegmentsExperimentStatusURL,
						editSegmentsExperimentURL,
						editSegmentsVariantURL,
						runSegmentsExperimentURL
					},
					namespace: context.namespace
				}),
				assetsPath,
				editVariantLayoutURL: editSegmentsVariantLayoutURL,
				page
			}}
		>
			<SegmentsExperimentsSidebar
				initialExperimentHistory={props.historySegmentsExperiments}
				initialGoals={props.segmentsExperimentGoals}
				initialSegmentsExperiment={props.segmentsExperiment}
				initialSegmentsVariants={props.initialSegmentsVariants}
				initialSelectedSegmentsExperienceId={
					props.selectedSegmentsExperienceId
				}
				winnerSegmentsVariantId={props.winnerSegmentsVariantId}
			/>
		</SegmentsExperimentsContext.Provider>
	);
}

export default function(containerId, data) {
	render(renderComponent, data, document.getElementById(containerId));
}
