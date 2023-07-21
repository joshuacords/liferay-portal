/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {actionsDefinition} from '../actions/index';

export const initialState = {
	filters: [],
	onFiltersChange: null
};

function reducer(state = initialState, action) {
	switch (action.type) {
		case actionsDefinition.UPDATE_FILTER_STATE:
			return {
				...state,
				filters: state.filters.map(el => ({
					...el,
					...(el.id === action.payload.id ? action.payload : {})
				}))
			};
		case actionsDefinition.RESET_FILTERS_VALUE:
			return {
				...state,
				filters: state.filters.map(el => ({
					...el,
					additionalData: null,
					odataFilterString: null,
					resumeCustomLabel: null,
					value: null
				}))
			};
		default:
			return state;
	}
}

export default reducer;
