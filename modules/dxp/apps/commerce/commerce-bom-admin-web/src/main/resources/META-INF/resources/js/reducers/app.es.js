/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {actionDefinition} from '../actions/app.es';

export const initialState = {
	areaApiUrl: null,
	error: null,
	initialized: false,
	loading: false,
	productApiUrl: null,
	spritemap: null
};

export default function reducer(state = initialState, action) {
	switch (action.type) {
		case actionDefinition.SET_ERROR:
			return {
				...state,
				error: action.payload
			};
		case actionDefinition.INITIALIZE_APP_DATA:
			return {
				...state,
				...action.payload,
				initialized: true
			};
		case actionDefinition.SET_LOADING:
			return {
				...state,
				loading: action.payload
			};
		case actionDefinition.SET_SPRITEMAP:
			return {
				...state,
				spritemap: action.payload
			};
		default:
			return state;
	}
}
