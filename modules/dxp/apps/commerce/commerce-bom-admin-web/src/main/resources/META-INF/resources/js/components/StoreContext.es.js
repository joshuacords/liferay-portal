/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React, {createContext, useReducer} from 'react';
import {combineReducers} from 'redux';

import {actions as appActions} from '../actions/app.es';
import {actions as areaActions} from '../actions/area.es';
import applyMiddleware from '../middleware/index.es';
import appReducer, {initialState as initialAppState} from '../reducers/app.es';
import areaReducer, {
	initialState as initialAreaState
} from '../reducers/area.es';

export const StoreContext = createContext();

export function initializeActions(actions, dispatch) {
	return Object.keys(actions).reduce(
		(curriedActions, actionName) => ({
			...curriedActions,
			[actionName]: actions[actionName](dispatch)
		}),
		{}
	);
}

const reducers = combineReducers({
	app: appReducer,
	area: areaReducer
});

export function StoreProvider(props) {
	const [state, dispatch] = useReducer(reducers, {
		app: initialAppState,
		area: initialAreaState
	});

	const actions = initializeActions(
		{...appActions, ...areaActions},
		applyMiddleware(dispatch)
	);

	return (
		<StoreContext.Provider value={{actions, state}}>
			{props.children}
		</StoreContext.Provider>
	);
}

export default StoreContext;
export const StoreConsumer = StoreContext.Consumer;
