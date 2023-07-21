/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {fetch} from 'frontend-js-web';

export const actionDefinition = {
	GET_AREA_FULFILLED: 'getAreaFulfilled',
	GET_AREA_PENDING: 'getAreaPending',
	GET_AREA_REJECTED: 'getAreaRejected',
	HIGHLIGHT_DETAIL: 'highlightDetail',
	SELECT_DETAIL: 'selectDetail'
};

const highlightDetail = dispatch => (number, showFirstResume = false) =>
	dispatch({
		payload: {
			number,
			showFirstResume
		},
		type: actionDefinition.HIGHLIGHT_DETAIL
	});

const select = dispatch => id =>
	dispatch({
		payload: id,
		type: actionDefinition.SELECT_DETAIL
	});

const getArea = dispatch => (endpoint, id) => {
	const url =
		endpoint + (id ? `/${id}` : '') + `?p_auth=${window.Liferay.authToken}`;

	dispatch({
		type: actionDefinition.GET_AREA_PENDING
	});

	return fetch(url)
		.then(response => response.json())
		.then(data =>
			dispatch({
				payload: data,
				type: actionDefinition.GET_AREA_FULFILLED
			})
		)
		.catch(err =>
			dispatch({
				payload: err,
				type: actionDefinition.GET_AREA_REJECTED
			})
		);
};

export const actions = {
	getArea,
	highlightDetail,
	select
};
