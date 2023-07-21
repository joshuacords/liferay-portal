/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React, {useContext, useEffect, useState} from 'react';

import StateContext from './StateContext.es';
import StoreContext from './StoreContext.es';

const StateProvider = props => {
	const store = useContext(StoreContext);
	const [storeState, setStoreState] = useState(store ? store.getState() : {});

	useEffect(() => {
		if (store) {
			const subscriber = store.on('change', () =>
				setStoreState(store.getState())
			);

			return () => subscriber.removeListener();
		}
	}, [store]);

	return (
		<StateContext.Provider value={storeState}>
			{props.children}
		</StateContext.Provider>
	);
};

export {StateProvider};
export default StateProvider;
