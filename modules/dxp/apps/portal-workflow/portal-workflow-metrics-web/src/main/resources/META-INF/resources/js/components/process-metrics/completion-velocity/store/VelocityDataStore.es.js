/* eslint-disable react-hooks/exhaustive-deps */
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React, {createContext, useContext, useEffect, useState} from 'react';

import {ErrorContext} from '../../../../shared/components/request/Error.es';
import {LoadingContext} from '../../../../shared/components/request/Loading.es';
import {AppContext} from '../../../AppContext.es';
import {TimeRangeContext} from '../../filter/store/TimeRangeStore.es';
import {VelocityUnitContext} from '../../filter/store/VelocityUnitStore.es';

const useVelocityData = processId => {
	const {client} = useContext(AppContext);
	const {getSelectedTimeRange} = useContext(TimeRangeContext);
	const {getSelectedVelocityUnit} = useContext(VelocityUnitContext);
	const {setError} = useContext(ErrorContext);
	const {setLoading} = useContext(LoadingContext);
	const [velocityData, setVelocityData] = useState();

	const velocityTimeRange = getSelectedTimeRange();
	const velocityUnit = getSelectedVelocityUnit();

	const fetchData = (processId, dateEnd, dateStart, unitKey) => {
		setError(null);
		setLoading(true);

		client
			.get(
				`/processes/${processId}/metric?dateEnd=${dateEnd.toISOString()}&dateStart=${dateStart.toISOString()}&unit=${unitKey}`
			)
			.then(({data}) => {
				setVelocityData(data);
			})
			.catch(error => {
				setError(error);
			})
			.then(() => {
				setLoading(false);
			});
	};

	useEffect(() => {
		if (
			processId &&
			velocityTimeRange &&
			velocityTimeRange.dateEnd &&
			velocityTimeRange.dateStart &&
			velocityUnit
		) {
			fetchData(
				processId,
				velocityTimeRange.dateEnd,
				velocityTimeRange.dateStart,
				velocityUnit.key
			);
		}
	}, [processId, velocityUnit]);

	return {
		velocityData
	};
};

const VelocityDataContext = createContext();

const VelocityDataProvider = ({children, processId}) => {
	return (
		<VelocityDataContext.Provider value={useVelocityData(processId)}>
			{children}
		</VelocityDataContext.Provider>
	);
};

export {VelocityDataProvider, VelocityDataContext, useVelocityData};
