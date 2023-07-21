/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React, {useContext} from 'react';

import {formatNumber} from '../../../shared/util/numeral.es';
import {VelocityUnitContext} from '../filter/store/VelocityUnitStore.es';
import {VelocityDataContext} from './store/VelocityDataStore.es';

const ProcessVelocityInfo = () => {
	const {getSelectedVelocityUnit} = useContext(VelocityUnitContext);
	const {velocityData = {}} = useContext(VelocityDataContext);

	const formattedValue = formatNumber(velocityData.value, '0[.]00');
	const selectedVelocityUnit = getSelectedVelocityUnit() || {};

	return (
		velocityData && (
			<div className="pb-2">
				<span className="velocity-value">{formattedValue}</span>
				<span className="velocity-unit">
					{selectedVelocityUnit.name}
				</span>
			</div>
		)
	);
};

export {ProcessVelocityInfo};
