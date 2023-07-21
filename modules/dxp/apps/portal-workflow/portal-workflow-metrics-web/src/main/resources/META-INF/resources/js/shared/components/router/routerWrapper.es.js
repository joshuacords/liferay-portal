/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';
import {Link, Redirect, withRouter} from 'react-router-dom';

import {parse, stringify} from './queryString.es';

class BackLinkWrapper extends React.Component {
	render() {
		const {backPath, children, className} = this.props;

		return (
			<Link className={className} to={backPath}>
				{children}
			</Link>
		);
	}
}

class BackRedirectWrapper extends React.Component {
	render() {
		const {backPath} = this.props;

		return <Redirect to={backPath} />;
	}
}

class ChildLinkWrapper extends React.Component {
	render() {
		const {
			children,
			className,
			currentPath,
			query,
			to,
			...props
		} = this.props;

		const eventProps = getEventProps(props);

		return (
			<Link
				{...eventProps}
				className={className}
				to={{
					pathname: to,
					search: stringify({backPath: currentPath, ...query})
				}}
			>
				{children}
			</Link>
		);
	}
}

const withParams = Component =>
	withRouter(({location: {pathname, search}, match: {params}, ...props}) => (
		<Component
			{...params}
			{...parse(search)}
			{...props}
			currentPath={pathname + search}
		/>
	));

function getEventProps(props) {
	return Object.keys(props)
		.filter(key => key.startsWith('on'))
		.reduce((obj, key) => {
			obj[key] = props[key];

			return obj;
		}, {});
}

const BackLink = withParams(BackLinkWrapper);
const BackRedirect = withParams(BackRedirectWrapper);
const ChildLink = withParams(ChildLinkWrapper);

export {BackLink, BackRedirect, ChildLink};
