/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';
import ReactDOM from 'react-dom';

export default function launcher(
	Component,
	componentId,
	containerId,
	props = {}
) {
	const {portletId} = props;
	const container = window.document.getElementById(containerId);
	const destroyOnNavigate = !portletId;

	if (Liferay.component) {
		Liferay.component(
			componentId,
			{
				destroy: () => {
					ReactDOM.unmountComponentAtNode(container);
				}
			},
			{
				destroyOnNavigate,
				portletId
			}
		);
	}

	// eslint-disable-next-line liferay-portal/no-react-dom-render
	ReactDOM.render(<Component {...props} />, container);
}
