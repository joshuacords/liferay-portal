/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React, {createContext, useContext, useReducer} from 'react';

import {actions} from '../actions/index';
import reducer, {initialState} from '../reducers/index';

export const StoreContext = createContext(null);

export function serializeActions(actions, dispatch) {
	return Object.keys(actions).reduce(
		(curriedActions, actionName) => ({
			...curriedActions,
			[actionName]: actions[actionName](dispatch)
		}),
		{}
	);
}

export function StoreProvider({children, ...stateProps}) {
	const [state, dispatch] = useReducer(reducer, {
		...initialState,
		...stateProps
	});

	const serializedActions = serializeActions(actions, dispatch);

	return (
		<StoreContext.Provider value={{actions: serializedActions, state}}>
			{children}
		</StoreContext.Provider>
	);
}

export function useAppState() {
	return useContext(StoreContext);
}

export default useAppState;
