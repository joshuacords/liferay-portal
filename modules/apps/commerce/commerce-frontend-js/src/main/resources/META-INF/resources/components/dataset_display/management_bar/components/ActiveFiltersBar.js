/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayButton from '@clayui/button';
import React from 'react';

import getAppContext from './Context';
import FilterResume from './FilterResume';

function ActiveFiltersBar(props) {
	const {actions, state} = getAppContext();

	const filtersActive = state.filters.reduce(
		(acc, filter) =>
			filter.value && !filter.invisible ? acc.concat(filter.id) : acc,
		[]
	);

	return filtersActive.length ? (
		<div className="management-bar management-bar-light navbar navbar-expand-md">
			<div className="container-fluid container-fluid-max-xl">
				<nav className="mb-0 py-3 subnav-tbar subnav-tbar-light subnav-tbar-primary w-100">
					<ul className="tbar-nav">
						<li className="p-0 tbar-item tbar-item-expand">
							<div className="tbar-section">
								{filtersActive.map(id => {
									const filter = state.filters.reduce(
										(found, filter) =>
											found ||
											(filter.id === id ? filter : null),
										null
									);

									if (!filter) {
										throw new Error(
											`Filter "${id}" not found.`
										);
									}

									return (
										<FilterResume
											disabled={props.disabled}
											key={filter.id}
											{...filter}
										/>
									);
								})}
							</div>
						</li>
						<li className="tbar-item">
							<div className="tbar-section">
								<ClayButton
									disabled={props.disabled}
									displayType="unstyled"
									onClick={actions.resetFiltersValue}
								>
									{Liferay.Language.get('reset-filters')}
								</ClayButton>
							</div>
						</li>
					</ul>
				</nav>
			</div>
		</div>
	) : null;
}

export default ActiveFiltersBar;
