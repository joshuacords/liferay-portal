/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

/* eslint-disable no-console */

import PropTypes from 'prop-types';
import React from 'react';

import config from '../utilities/config';

export default class ErrorBoundary extends React.Component {
	state = {
		error: '',
		hasError: false,
		info: ''
	};

	static propTypes = {
		children: PropTypes.any
	};

	componentDidCatch(error, info) {
		this.setState({
			error,
			hasError: true,
			info
		});

		if (config.env === 'development') {
			console.log(`Error: ${error}`);
			console.log(`Error Info: ${JSON.stringify(info)}`);
		}
	}

	render() {
		return this.state.hasError ? (
			<div className="alert alert-danger" role="alert">
				{Liferay.Language.get('your-component-failed-to-render')}
			</div>
		) : (
			this.props.children
		);
	}
}
