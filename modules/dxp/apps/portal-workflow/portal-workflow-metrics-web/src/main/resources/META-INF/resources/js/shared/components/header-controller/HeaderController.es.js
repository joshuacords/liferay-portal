/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

import HeaderMenuBackItem from './HeaderMenuBackItem.es';
import HeaderTitle from './HeaderTitle.es';

export default class HeaderController extends React.Component {
	componentWillMount() {
		const {namespace} = this.props;

		const headerContainer = document.getElementById(
			`${namespace}controlMenu`
		);

		if (headerContainer) {
			this.backButtonContainer = headerContainer.querySelector(
				'.sites-control-group .control-menu-nav'
			);
			this.titleContainer = headerContainer.querySelector(
				'.tools-control-group .control-menu-level-1-heading'
			);
		}
	}

	render() {
		const {basePath, title} = this.props;

		return (
			<>
				<HeaderMenuBackItem
					basePath={basePath}
					container={this.backButtonContainer}
				/>

				<HeaderTitle container={this.titleContainer} title={title} />
			</>
		);
	}
}
