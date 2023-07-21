/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import pathToRegexp from 'path-to-regexp';
import React from 'react';
import {Link} from 'react-router-dom';

export default class TabItem extends React.Component {
	render() {
		const {active, name, params, path, query} = this.props;

		const activeClassName = active ? 'active' : '';
		const activeLabel = active ? Liferay.Language.get('current-page') : '';

		return (
			<Link
				aria-label={activeLabel}
				className={`${activeClassName} nav-link`}
				to={{
					pathname: getPathname(params, path),
					search: query
				}}
			>
				<span className="navbar-text-truncate">{name}</span>
			</Link>
		);
	}
}

export function getPathname(params, path) {
	return pathToRegexp.compile(path)(params);
}
