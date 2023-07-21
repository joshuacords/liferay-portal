/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

export const actionDefinition = {
	INITIALIZE_APP_DATA: 'initializeAppData',
	SET_ERROR: 'setError',
	SET_LOADING: 'setLoading',
	SET_SPRITEMAP: 'setSpritemap',
	UPDATE_BREADCRUMBS: 'updateBreadcrumbs'
};

const initializeAppData = dispatch => data =>
	dispatch({
		payload: {
			areaApiUrl: data.areaApiUrl,
			areaId: data.areaId,
			productApiUrl: data.productApiUrl,
			spritemap: data.spritemap
		},
		type: actionDefinition.INITIALIZE_APP_DATA
	});

const setError = dispatch => error =>
	dispatch({
		payload: error,
		type: actionDefinition.SET_ERROR
	});

const setLoading = dispatch => loading =>
	dispatch({
		payload: loading,
		type: actionDefinition.SET_LOADING
	});

const setSpritemap = dispatch => spritemap =>
	dispatch({
		payload: spritemap,
		type: actionDefinition.SET_SPRITEMAP
	});

export const actions = {
	initializeAppData,
	setError,
	setLoading,
	setSpritemap
};

export default actions;
