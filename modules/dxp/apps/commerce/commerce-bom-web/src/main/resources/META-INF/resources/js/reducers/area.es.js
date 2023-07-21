/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {actionDefinition} from '../actions/area.es';

export const initialState = {
	highlightedDetail: null,
	imageUrl: null,
	name: null,
	products: [],
	spots: []
};

export default function reducer(state = initialState, action) {
	switch (action.type) {
		case actionDefinition.HIGHLIGHT_DETAIL:
			return {
				...state,
				highlightedDetail: action.payload
			};
		case actionDefinition.GET_AREA_FULFILLED:
			return {
				...state,
				imageUrl: action.payload.data.imageUrl,
				name: action.payload.data.name,
				products: action.payload.data.products
					? action.payload.data.products
					: [],
				spots: action.payload.data.spots
					? action.payload.data.spots
					: []
			};
		default:
			return state;
	}
}
