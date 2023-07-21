/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

export const actionsDefinition = {
	RESET_FILTERS_VALUE: 'resetFiltersValue',
	UPDATE_FILTER_STATE: 'updateFilterState'
};

const updateFilterState = dispatch => (
	id,
	value = null,
	formattedValue = null,
	odataFilterString = null
) =>
	dispatch({
		payload: {
			formattedValue,
			id,
			odataFilterString,
			value
		},
		type: actionsDefinition.UPDATE_FILTER_STATE
	});

const resetFiltersValue = dispatch => () =>
	dispatch({
		type: actionsDefinition.RESET_FILTERS_VALUE
	});

export const actions = {
	resetFiltersValue,
	updateFilterState
};

export default actions;
