/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React, {createContext, useReducer} from 'react';
import {combineReducers} from 'redux';

import {actions as appActions} from '../actions/app.es';
import {actions as areaActions} from '../actions/area.es';
import {actions as folderActions} from '../actions/folder.es';
import applyMiddleware from '../middleware/index.es';
import appReducer, {initialState as initialAppState} from '../reducers/app.es';
import areaReducer, {
	initialState as initialAreaState
} from '../reducers/area.es';
import folderReducer, {
	initialState as initialFolderState
} from '../reducers/folder.es';

export const StoreContext = createContext();

function initializeActions(actions, dispatch) {
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
	area: areaReducer,
	folder: folderReducer
});

export function StoreProvider(props) {
	const [state, dispatch] = useReducer(reducers, {
		app: initialAppState,
		area: initialAreaState,
		folder: initialFolderState
	});

	const actions = initializeActions(
		{
			...appActions,
			...areaActions,
			...folderActions
		},
		applyMiddleware(dispatch)
	);

	return (
		<StoreContext.Provider value={{actions, state}}>
			{props.children}
		</StoreContext.Provider>
	);
}

export const StoreConsumer = StoreContext.Consumer;
