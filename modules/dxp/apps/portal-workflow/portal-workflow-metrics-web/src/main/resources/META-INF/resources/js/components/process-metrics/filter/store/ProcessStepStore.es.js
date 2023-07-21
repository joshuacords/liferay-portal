/* eslint-disable react-hooks/exhaustive-deps */
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React, {createContext, useContext, useEffect, useState} from 'react';

import {buildFallbackItems} from '../../../../shared/components/filter/util/filterEvents.es';
import {ErrorContext} from '../../../../shared/components/request/Error.es';
import {LoadingContext} from '../../../../shared/components/request/Loading.es';
import {compareArrays} from '../../../../shared/util/array.es';
import {usePrevious} from '../../../../shared/util/hooks.es';
import {AppContext} from '../../../AppContext.es';

const useProcessStep = (processId, processStepKeys) => {
	const {client} = useContext(AppContext);
	const [processSteps, setProcessSteps] = useState([]);
	const {setError} = useContext(ErrorContext);
	const {setLoading} = useContext(LoadingContext);

	const fetchData = () => {
		setError(null);
		setLoading(true);

		return client
			.get(`/processes/${processId}/tasks?page=0&pageSize=0`)
			.then(({data}) => {
				const items = data.items || [];

				const processSteps = items.map(processStep => ({
					...processStep,
					active: processStepKeys.includes(processStep.key)
				}));

				setProcessSteps(processSteps);
			})
			.catch(error => {
				setError(error);
			})
			.then(() => {
				setLoading(false);
			});
	};

	const getSelectedProcessSteps = fallbackKeys => {
		if (!processSteps || !processSteps.length) {
			return buildFallbackItems(fallbackKeys);
		}

		return processSteps.filter(item => item.active);
	};

	const updateData = () => {
		setProcessSteps(
			processSteps.map(processStep => ({
				...processStep,
				active: processStepKeys.includes(processStep.key)
			}))
		);
	};

	useEffect(() => {
		fetchData();
	}, []);

	const previousKeys = usePrevious(processStepKeys);

	useEffect(() => {
		const filterChanged = !compareArrays(previousKeys, processStepKeys);

		if (filterChanged && processSteps.length) {
			updateData();
		}
	}, [processStepKeys]);

	return {
		fetchData,
		getSelectedProcessSteps,
		processSteps
	};
};

const ProcessStepContext = createContext(null);

const ProcessStepProvider = ({children, processId, processStepKeys}) => {
	return (
		<ProcessStepContext.Provider
			value={useProcessStep(processId, processStepKeys)}
		>
			{children}
		</ProcessStepContext.Provider>
	);
};

export {ProcessStepContext, ProcessStepProvider, useProcessStep};
