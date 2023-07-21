/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';
import ReactDOM from 'react-dom';

import App from './App.es';
import {StoreProvider} from './components/StoreContext.es';

export default function(componentId, id, props) {
	const portletFrame = window.document.getElementById(id);
	let instance = null;

	// eslint-disable-next-line liferay-portal/no-react-dom-render
	ReactDOM.render(
		<StoreProvider>
			<App
				ref={component => {
					instance = component;
				}}
				{...props}
			/>
		</StoreProvider>,
		portletFrame
	);
	if (window.Liferay) {
		window.Liferay.component(componentId, instance);
	}

	return instance;
}
