/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React, {useContext} from 'react';

import Filter from '../../../shared/components/filter/Filter.es';
import {ProcessStepContext} from './store/ProcessStepStore.es';

const ProcessStepFilter = ({
	filterKey = 'taskKeys',
	hideControl = false,
	position = 'left'
}) => {
	const {processSteps} = useContext(ProcessStepContext);

	return (
		<Filter
			filterKey={filterKey}
			hideControl={hideControl}
			items={processSteps}
			multiple={true}
			name={Liferay.Language.get('process-step')}
			position={position}
		/>
	);
};

export default ProcessStepFilter;
