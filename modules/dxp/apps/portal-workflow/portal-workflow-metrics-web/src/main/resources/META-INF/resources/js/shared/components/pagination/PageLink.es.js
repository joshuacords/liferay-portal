/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import pathToRegexp from 'path-to-regexp';
import React from 'react';
import {Link, withRouter} from 'react-router-dom';

/**
 * @class
 * @memberof shared/components
 */
class PageLink extends React.Component {
	render() {
		const {
			location: {search},
			match: {params, path},
			page
		} = this.props;

		const pathname = pathToRegexp.compile(path)({...params, page});

		return (
			<li className="page-item">
				<Link className="page-link" to={{pathname, search}}>
					<span className="sr-only" />
					{page}
				</Link>
			</li>
		);
	}
}

export default withRouter(PageLink);
