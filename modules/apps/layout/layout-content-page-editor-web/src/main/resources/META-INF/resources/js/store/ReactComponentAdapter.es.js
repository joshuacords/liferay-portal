/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {ClayIconSpriteContext} from '@clayui/icon';
import {Component} from 'metal-component';
import Soy from 'metal-soy';
import React from 'react';
import ReactDOM from 'react-dom';

import {getConnectedComponent} from './ConnectedComponent.es';
import StateProvider from './StateProvider.es';
import {StoreContext} from './StoreContext.es';

function getConnectedReactComponentAdapter(ReactComponent, templates) {
	class ReactComponentAdapter extends Component {
		disposed() {
			ReactDOM.unmountComponentAtNode(this.refs.app);
		}

		syncStore(store, prevStore) {
			if (store && store !== prevStore) {
				this._mountApp();
			}
		}

		_mountApp() {
			ReactDOM.unmountComponentAtNode(this.refs.app);

			// eslint-disable-next-line liferay-portal/no-react-dom-render
			ReactDOM.render(
				<ClayIconSpriteContext.Provider
					value={this.store.getState().spritemap}
				>
					<StoreContext.Provider value={this.store}>
						<StateProvider>
							<ReactComponent />
						</StateProvider>
					</StoreContext.Provider>
				</ClayIconSpriteContext.Provider>,
				this.refs.app
			);
		}
	}

	const ConnectedReactComponentAdapter = getConnectedComponent(
		ReactComponentAdapter,
		[]
	);

	Soy.register(ConnectedReactComponentAdapter, templates);

	return ConnectedReactComponentAdapter;
}

export {getConnectedReactComponentAdapter};
export default getConnectedReactComponentAdapter;
