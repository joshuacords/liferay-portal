/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React, {useContext} from 'react';

import {StoreContext} from './StoreContext.es';

function Breadcrumbs(props) {
	const {state} = useContext(StoreContext);

	return props.data ? (
		<ol className="breadcrumb mb-3">
			{props.data.map((el, i) => {
				const content = (
					<span className="breadcrumb-text-truncate">{el.label}</span>
				);

				function handleBreadcrumbLink(e) {
					e.preventDefault();
					const filteredId = /^.*\/(.*)$/.exec(el.url)[1];
					const formattedUrl = `?folderId=${filteredId}`;
					state.app.history.push(formattedUrl);
				}

				return (
					<li className="breadcrumb-item" key={i}>
						{el.url ? (
							<a
								data-senna-off
								href="#"
								key={i}
								onClick={handleBreadcrumbLink}
							>
								{content}
							</a>
						) : (
							content
						)}
					</li>
				);
			})}
		</ol>
	) : null;
}

export default Breadcrumbs;
