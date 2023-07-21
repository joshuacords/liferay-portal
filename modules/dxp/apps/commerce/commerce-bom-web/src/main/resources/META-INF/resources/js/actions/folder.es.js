/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {fetch} from 'frontend-js-web';

export const actionDefinition = {
	GET_FOLDER_FULFILLED: 'getFolderFulfilled',
	GET_FOLDER_PENDING: 'getFolderPending',
	GET_FOLDER_REJECTED: 'getFolderRejected'
};

const getFolder = dispatch => (endpoint, id) => {
	const url =
		endpoint +
		(id ? `/${id}` : '/0') +
		`?p_auth=${window.Liferay.authToken}`;

	dispatch({
		type: actionDefinition.GET_FOLDER_PENDING
	});

	return fetch(url)
		.then(response => response.json())
		.then(data =>
			dispatch({
				payload: data,
				type: actionDefinition.GET_FOLDER_FULFILLED
			})
		)
		.catch(err =>
			dispatch({
				payload: err,
				type: actionDefinition.GET_FOLDER_REJECTED
			})
		);
};

export const actions = {
	getFolder
};
