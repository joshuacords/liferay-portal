/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React, {useContext} from 'react';

import Filter from '../../../shared/components/filter/Filter.es';
import {VelocityUnitContext} from './store/VelocityUnitStore.es';

const VelocityUnitFilter = ({
	filterKey = 'velocityUnit',
	hideControl = false,
	position = 'left'
}) => {
	const {
		defaultVelocityUnit,
		getSelectedVelocityUnit,
		velocityUnits
	} = useContext(VelocityUnitContext);

	const selectedVelocityUnit = getSelectedVelocityUnit() || {};

	return (
		<Filter
			defaultItem={defaultVelocityUnit}
			elementClasses="pl-3"
			filterKey={filterKey}
			hideControl={hideControl}
			items={[...velocityUnits]}
			multiple={false}
			name={selectedVelocityUnit.name}
			position={position}
		/>
	);
};

export {VelocityUnitFilter};
