/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {actionDefinition} from '../actions/app.es';

export const initialState = {
	areasEndpoint: null,
	basePathUrl: '/',
	basename: '/',
	breadcrumbs: null,
	error: null,
	foldersEndpoint: null,
	history: null,
	loading: false,
	spritemap: null
};

export default function reducer(state = initialState, action) {
	switch (action.type) {
		case actionDefinition.SET_ERROR:
			return {
				...state,
				error: action.payload
			};
		case actionDefinition.INITIALIZE:
			return {
				...state,
				areasEndpoint: action.payload.areasEndpoint,
				basePathUrl: action.payload.basePathUrl,
				basename: action.payload.basename,
				foldersEndpoint: action.payload.foldersEndpoint,
				history: action.payload.history,
				spritemap: action.payload.spritemap
			};
		case actionDefinition.UPDATE_BREADCRUMBS:
			return {
				...state,
				breadcrumbs: action.payload
			};
		case actionDefinition.SET_HISTORY:
			return {
				...state,
				history: action.payload
			};
		case actionDefinition.SET_BASE_PATH_URL:
			return {
				...state,
				basePathUrl: action.payload
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
		case actionDefinition.SET_BASENAME:
			return {
				...state,
				basename: action.payload
			};
		default:
			return state;
	}
}
