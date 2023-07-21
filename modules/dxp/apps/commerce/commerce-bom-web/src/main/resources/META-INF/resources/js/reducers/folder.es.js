/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {actionDefinition} from '../actions/folder.es';

export const initialState = {
	brands: null,
	items: null,
	loading: null
};

export default function reducer(state = initialState, action) {
	switch (action.type) {
		case actionDefinition.GET_FOLDER_FULFILLED:
			return {
				...state,
				brands: action.payload.data.brands,
				items: action.payload.data.items,
				loading: false
			};
		case actionDefinition.GET_FOLDER_PENDING:
			return {
				...state,
				loading: true
			};
		default:
			return state;
	}
}
