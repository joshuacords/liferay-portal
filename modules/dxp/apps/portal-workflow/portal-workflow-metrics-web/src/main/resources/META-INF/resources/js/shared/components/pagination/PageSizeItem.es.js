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
class PageSizeItem extends React.Component {
	render() {
		const {
			location: {search},
			match,
			pageSize
		} = this.props;

		const params = {...match.params, page: 1, pageSize};

		const pathname = pathToRegexp.compile(match.path)(params);

		return (
			<Link
				className="dropdown-item"
				to={{
					pathname,
					search
				}}
			>
				{pageSize}
			</Link>
		);
	}
}

export default withRouter(PageSizeItem);
