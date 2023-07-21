/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import PropTypes from 'prop-types';
import React, {useEffect} from 'react';

import ActiveFiltersBar from './components/ActiveFiltersBar';
import BulkActions from './components/BulkActions';
import useAppState, {StoreProvider} from './components/Context';
import NavBar from './components/NavBar';

function ManagementBar(props) {
	const {state} = useAppState();

	useEffect(() => {
		props.onFiltersChange(state.filters);

		// eslint-disable-next-line react-hooks/exhaustive-deps
	}, [JSON.stringify(state.filters)]);

	return (
		<>
			{props.selectionType === 'multiple' && (
				<BulkActions
					bulkActions={props.bulkActions}
					fluid={props.fluid}
					selectAllItems={props.selectAllItems}
					selectedItemsKey={props.selectedItemsKey}
					selectedItemsValue={props.selectedItemsValue}
					totalItemsCount={props.totalItemsCount}
				/>
			)}
			{(!props.selectedItemsValue.length ||
				props.selectionType === 'single') && (
				<NavBar
					activeView={props.activeView}
					creationMenuItems={props.creationMenuItems}
					setActiveView={props.setActiveView}
					showSearch={props.showSearch}
					views={props.views}
				/>
			)}
			<ActiveFiltersBar disabled={!!props.selectedItemsValue.length} />
		</>
	);
}

function Wrapper(props) {
	const {filters, ...otherProps} = props;

	return (
		<StoreProvider filters={filters}>
			<ManagementBar {...otherProps} />
		</StoreProvider>
	);
}

Wrapper.propTypes = {
	activeView: PropTypes.number.isRequired,
	creationMenuItems: PropTypes.array,
	filters: PropTypes.array,
	fluid: PropTypes.bool,
	onFiltersChange: PropTypes.func.isRequired,
	selectedItemsKey: PropTypes.string.isRequired,
	selectionType: PropTypes.oneOf(['single', 'multiple']).isRequired,
	setActiveView: PropTypes.func.isRequired,
	showSearch: PropTypes.bool,
	views: PropTypes.array.isRequired
};

Wrapper.defaultProps = {
	filters: [],
	fluid: false,
	showSearch: true
};

export default Wrapper;
