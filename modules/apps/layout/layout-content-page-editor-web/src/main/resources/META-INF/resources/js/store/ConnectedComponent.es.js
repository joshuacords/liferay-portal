/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {Config} from 'metal-state';
import React, {useContext, useEffect, useState} from 'react';

import StoreContext from './StoreContext.es';
import INITIAL_STATE from './state.es';
import {Store, connect, disconnect} from './store.es';

/**
 * HOC that returns a component that connects automatically
 * to a Store parameter.
 * @param {Component} Component
 * @param {string[]} [properties=[]] List of properties to be fetched from store
 * @return {Component}
 */
const getConnectedComponent = (Component, properties) => {
	/**
	 * ConnectedComponent
	 */
	class ConnectedComponent extends Component {
		/**
		 * @inheritdoc
		 * @param {object} props
		 * @param  {...any} ...args
		 */
		constructor(props, ...args) {
			super(props, ...args);

			this.on('storeChanged', change => {
				const newStore = change.newVal;
				const prevStore = change.prevVal;

				if (newStore !== prevStore) {
					disconnect(this);

					if (newStore instanceof Store) {
						connect(this, newStore);
					}
				}
			});

			this.on('disposed', () => {
				disconnect(this);
			});

			if (props.store instanceof Store) {
				connect(this, props.store);
			}
		}
	}

	/**
	 * Connected component state
	 */
	ConnectedComponent.STATE = {
		store: Config.instanceOf(Store).value(null)
	};

	properties.forEach(property => {
		try {
			ConnectedComponent.STATE[property] = INITIAL_STATE[
				property
			].internal();
		}
		catch (e) {
			throw new Error(
				`${property} is not available from ${Component.name}`
			);
		}
	});

	return ConnectedComponent;
};

/**
 * Second order function to produce a Connected Component Wrapper
 * @param {Function} mapStateToProps - Recieves the state and returns mapped version of it ready for consumption by the Wrapped Component
 * @param {Function} mapDispatchToProps - Recieves the dispatch and returns a set of component props that use it to call the modify the state tree
 * @returns {object}
 */
function getConnectedReactComponent(mapStateToProps, mapDispatchToProps) {
	/**
	 *
	 * @param {React.Component} WrappedComponent - The Component to connect to the store
	 * @returns {React.Component} - Wrapper Connected Component that propagates every store change mapped to the component
	 */
	return function _getConnectedWrapperComponent(WrappedComponent) {
		return function ConnectedWrapperComponent(props) {
			const store = useContext(StoreContext);

			const [storeState, setStoreState] = useState(
				store ? store.getState() : {}
			);

			useEffect(() => {
				if (store) {
					const handleStoreChange = () =>
						setStoreState(store.getState());
					const subscriber = store.on('change', handleStoreChange);

					return () => subscriber.removeListener();
				}
			}, [store]);

			return store ? (
				<WrappedComponent
					{...props}
					{...mapStateToProps(storeState, props)}
					{...mapDispatchToProps(store.dispatch, props)}
				/>
			) : null;
		};
	};
}

export {getConnectedComponent, getConnectedReactComponent};
export default getConnectedComponent;
