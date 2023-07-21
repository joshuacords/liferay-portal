/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import pathToRegexp from 'path-to-regexp';
import React from 'react';
import {Link, withRouter} from 'react-router-dom';

import Icon from '../Icon.es';

/**
 * @class
 * @memberof shared/components/list
 */
class ListHeadItem extends React.Component {
	render() {
		const {
			iconColor,
			iconName,
			location: {search},
			match: {params, path},
			name,
			title
		} = this.props;

		const sort = params && params.sort ? params.sort : `${name}:asc`;

		const [field, order] = decodeURIComponent(sort).split(':');

		const sorted = field === name;

		const nextSort = `${name}:${
			sorted && order === 'desc' ? 'asc' : 'desc'
		}`;

		const sortIcon =
			order === 'asc' ? 'order-arrow-down' : 'order-arrow-up';

		const pathname = pathToRegexp.compile(path)({
			...params,
			sort: nextSort
		});

		return (
			<Link
				className="inline-item text-truncate-inline"
				to={{pathname, search}}
			>
				{iconName && (
					<span className="inline-item inline-item-before mr-2">
						<span className="sticker sticker-sm">
							<span className="inline-item">
								<Icon
									elementClasses={`text-${iconColor}`}
									iconName={iconName}
								/>
							</span>
						</span>
					</span>
				)}

				<span
					className="text-truncate title"
					data-title={title}
					title={title}
				>
					{title}
				</span>

				{sorted && (
					<span className="inline-item inline-item-after">
						<Icon
							iconName={sortIcon}
							key={`${name}_icon_${sortIcon}`}
						/>
					</span>
				)}
			</Link>
		);
	}
}

export default withRouter(ListHeadItem);
