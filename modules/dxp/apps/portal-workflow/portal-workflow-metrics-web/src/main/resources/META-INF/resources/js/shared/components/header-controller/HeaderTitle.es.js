/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

import PortalComponent from './PortalComponent.es';

export default class HeaderTitle extends React.Component {
	componentDidUpdate({title: prevTitle}) {
		const {title} = this.props;

		if (prevTitle != title) {
			this.setDocumentTitle(prevTitle, title);
		}
	}

	setDocumentTitle(prevTitle, title) {
		document.title = document.title.replace(prevTitle, title);
	}

	render() {
		const {container, title} = this.props;

		return (
			<PortalComponent container={container} replace>
				{title}
			</PortalComponent>
		);
	}
}
