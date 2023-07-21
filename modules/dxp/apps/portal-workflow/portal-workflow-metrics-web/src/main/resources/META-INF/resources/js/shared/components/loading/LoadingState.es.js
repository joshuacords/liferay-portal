/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

/**
 * LoadingState.
 * @extends React.Component
 */
export default class LoadingState extends React.Component {
	render() {
		return <span aria-hidden="true" className="loading-animation" />;
	}
}
