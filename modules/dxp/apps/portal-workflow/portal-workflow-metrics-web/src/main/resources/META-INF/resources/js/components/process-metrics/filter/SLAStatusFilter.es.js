/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React, {useContext} from 'react';

import Filter from '../../../shared/components/filter/Filter.es';
import {SLAStatusContext} from './store/SLAStatusStore.es';

const SLAStatusFilter = ({
	filterKey = 'slaStatuses',
	hideControl = false,
	position = 'left'
}) => {
	const {slaStatuses} = useContext(SLAStatusContext);

	return (
		<Filter
			filterKey={filterKey}
			hideControl={hideControl}
			items={slaStatuses}
			multiple={true}
			name={Liferay.Language.get('sla-status')}
			position={position}
		/>
	);
};

export default SLAStatusFilter;
