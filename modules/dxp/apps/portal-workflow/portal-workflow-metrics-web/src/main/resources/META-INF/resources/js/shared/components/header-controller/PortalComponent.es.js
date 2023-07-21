/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';
import ReactDOM from 'react-dom';

export default class PortalComponent extends React.Component {
	constructor(props) {
		super(props);
		this.element = document.createElement('div');
	}

	componentDidMount() {
		const {container, replace} = this.props;

		if (!container) {
			return;
		}

		if (replace) {
			if (container.children.length) {
				container.removeChild(container.children[0]);
			}
			else {
				container.innerHTML = '';
			}
		}

		container.appendChild(this.element);
	}

	render() {
		const {children, container} = this.props;

		if (!container) {
			return null;
		}

		return <>{ReactDOM.createPortal(children, this.element)}</>;
	}
}
