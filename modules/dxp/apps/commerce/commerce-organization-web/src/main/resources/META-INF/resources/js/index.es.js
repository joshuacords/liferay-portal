/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import OrgChartContainer from 'components/OrgChartContainer';
import React from 'react';
import ReactDOM from 'react-dom';

export default function(componentId, id, props) {
	let instance = null;
	const portletFrame = window.document.getElementById(id);

	// eslint-disable-next-line liferay-portal/no-react-dom-render
	ReactDOM.render(
		<OrgChartContainer
			ref={component => {
				instance = component;
			}}
			{...props}
		/>,
		portletFrame
	);

	if (window.Liferay) {
		window.Liferay.component(componentId, instance);
	}

	return instance;
}
