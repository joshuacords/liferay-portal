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
 * @memberof shared/components
 */
class PageItem extends React.Component {
	render() {
		const {
			disabled,
			highlighted,
			location: {search},
			match,
			page,
			type
		} = this.props;
		const classNames = ['page-item'];

		if (disabled) {
			classNames.push('disabled');
		}
		if (highlighted) {
			classNames.push('active');
		}

		const renderLink = () => {
			const pathname = pathToRegexp.compile(match.path)({
				...match.params,
				page
			});

			if (type) {
				const isNext = type === 'next';

				const iconType = isNext ? 'angle-right' : 'angle-left';
				const displayType = isNext ? 'Next' : 'Previous';

				const renderLink = () => {
					const children = () => (
						<>
							<Icon iconName={iconType} />
							<span className="sr-only">{displayType}</span>
						</>
					);

					if (disabled) {
						return (
							<a className="page-link" href="javascript:;">
								{children()}
							</a>
						);
					}

					return (
						<Link
							className="page-link"
							to={{
								pathname,
								search
							}}
						>
							{children()}
						</Link>
					);
				};

				return renderLink();
			}

			return (
				<Link
					className="page-link"
					to={{
						pathname,
						search
					}}
				>
					{page}
				</Link>
			);
		};

		return <li className={classNames.join(' ')}>{renderLink()}</li>;
	}
}

export default withRouter(PageItem);
