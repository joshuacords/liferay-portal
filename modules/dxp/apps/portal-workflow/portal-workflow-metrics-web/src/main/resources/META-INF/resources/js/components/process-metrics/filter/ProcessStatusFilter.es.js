/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React, {useContext} from 'react';

import Filter from '../../../shared/components/filter/Filter.es';
import {ProcessStatusContext} from './store/ProcessStatusStore.es';

const ProcessStatusFilter = ({
	filterKey = 'statuses',
	hideControl = false,
	position = 'left'
}) => {
	const {processStatuses} = useContext(ProcessStatusContext);

	return (
		<Filter
			filterKey={filterKey}
			hideControl={hideControl}
			items={processStatuses}
			multiple={true}
			name={Liferay.Language.get('process-status')}
			position={position}
		/>
	);
};

export default ProcessStatusFilter;
