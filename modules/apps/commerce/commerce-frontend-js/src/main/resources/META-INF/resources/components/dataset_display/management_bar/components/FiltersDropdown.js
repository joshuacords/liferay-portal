/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {ClayButtonWithIcon} from '@clayui/button';
import ClayDropDown from '@clayui/drop-down';
import Icon from '@clayui/icon';
import React, {useEffect, useMemo, useState} from 'react';

import getAppContext from './Context';
import {Filter} from './filters/index';

function FiltersDropdown() {
	const [active, setActive] = useState(false);
	const [query, setQuery] = useState('');
	const {state} = getAppContext();
	const [visibleFilters, setVisibleFilter] = useState(
		state.filters.filter(filter => !filter.invisible)
	);
	const [activeFilterId, setActiveFilterId] = useState(null);
	const activeFilter = useMemo(() => {
		return (
			activeFilterId &&
			visibleFilters.find(filter => filter.id === activeFilterId)
		);
	}, [visibleFilters, activeFilterId]);
	const {actions} = getAppContext();

	useEffect(() => {
		const results = state.filters.filter(filter => {
			switch (true) {
				case !!filter.invisible:
					return false;
				case query &&
					!(
						filter.id.toLowerCase().includes(query) ||
						filter.label.toLowerCase().includes(query)
					):
					return false;
				default:
					return true;
			}
		});

		setVisibleFilter(results);
	}, [state.filters, query]);

	return state.filters.length ? (
		<ClayDropDown
			active={active}
			className="filters-dropdown"
			onActiveChange={setActive}
			trigger={
				<button
					className="btn btn-unstyled dropdown-toggle filters-dropdown-button"
					type="button"
				>
					<span className="navbar-text-truncate">
						{Liferay.Language.get('filter')}
					</span>
					{active ? (
						<Icon className="ml-2" symbol="caret-top" />
					) : (
						<Icon className="ml-2" symbol="caret-bottom" />
					)}
				</button>
			}
		>
			{activeFilterId ? (
				<>
					<li className="dropdown-subheader">
						<ClayButtonWithIcon
							className="btn-filter-navigation"
							displayType="unstyled"
							onClick={() => setActiveFilterId(null)}
							small
							symbol="angle-left"
						/>
						{activeFilter.label}
					</li>
					<Filter {...{...activeFilter, actions}} />
				</>
			) : (
				<>
					<li className="dropdown-subheader">
						{Liferay.Language.get('filters')}
					</li>
					<ClayDropDown.Search
						onChange={e => setQuery(e.target.value)}
						value={query}
					/>
					<ClayDropDown.Divider className="m-0" />
					{visibleFilters.length ? (
						<ClayDropDown.ItemList>
							{visibleFilters.map(item => (
								<ClayDropDown.Item
									active={
										item.value !== undefined &&
										item.value !== null
									}
									key={item.id}
									onClick={() => setActiveFilterId(item.id)}
								>
									{item.label}
								</ClayDropDown.Item>
							))}
						</ClayDropDown.ItemList>
					) : (
						<div className="dropdown-section text-muted">
							{Liferay.Language.get('no-filters-were-found')}
						</div>
					)}
				</>
			)}
		</ClayDropDown>
	) : null;
}

export default FiltersDropdown;
