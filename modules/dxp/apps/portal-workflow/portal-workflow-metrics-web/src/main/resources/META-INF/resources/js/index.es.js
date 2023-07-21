/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import 'string.prototype.startswith';
import React from 'react';
import ReactDOM from 'react-dom';

import AppComponent from './components/App.es';

export default function(defaultDelta, deltas, isAmPm, maxPages, namespace) {
	const container = document.getElementById(`${namespace}root`);

	const buildContainer = () => {
		// eslint-disable-next-line liferay-portal/no-react-dom-render
		ReactDOM.render(
			<AppComponent
				companyId={Liferay.ThemeDisplay.getCompanyId()}
				defaultDelta={defaultDelta}
				deltas={deltas}
				isAmPm={isAmPm}
				maxPages={maxPages}
				namespace={namespace}
			/>,
			container
		);
		container.setAttribute('data-rendered', true);
	};

	if (!container.getAttribute('data-rendered')) {
		buildContainer();
	}

	Liferay.once('destroyPortlet', () => {
		ReactDOM.unmountComponentAtNode(container);
	});
}
