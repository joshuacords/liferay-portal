/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';
import {Link, withRouter} from 'react-router-dom';

import Icon from '../Icon.es';
import {parse} from '../router/queryString.es';
import PortalComponent from './PortalComponent.es';

class HeaderMenuBackItem extends React.Component {
	render() {
		const {
			basePath,
			container,
			location: {pathname, search}
		} = this.props;

		const isFirstPage = pathname === basePath || pathname === '/';
		const query = parse(search);

		return (
			<PortalComponent container={container}>
				{!isFirstPage && query.backPath && (
					<li className="control-menu-nav-item">
						<Link
							className="control-menu-icon lfr-icon-item"
							to={query.backPath}
						>
							<span className="icon-monospaced">
								<Icon iconName="angle-left" />
							</span>
						</Link>
					</li>
				)}
			</PortalComponent>
		);
	}
}

export default withRouter(HeaderMenuBackItem);
