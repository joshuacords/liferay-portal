/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {updatePageEditorLayoutData} from '../utils/FragmentsEditorFetchUtils.es';
import {getRowIndex} from '../utils/FragmentsEditorGetUtils.es';
import {setIn} from '../utils/FragmentsEditorUpdateUtils.es';
import {
	UPDATE_ROW_COLUMNS_ERROR,
	UPDATE_ROW_COLUMNS_LOADING
} from './actions.es';
import {
	disableSavingChangesStatusAction,
	enableSavingChangesStatusAction,
	updateLastSaveDateAction
} from './saveChanges.es';

/**
 * @param {Array} columns
 * @param {string} rowId
 * @return {function}
 * @review
 */
function updateRowColumnsAction(columns, rowId) {
	return function(dispatch, getState) {
		const state = getState();

		const rowIndex = getRowIndex(state.layoutData.structure, rowId);

		const previousData = state.layoutData;

		let nextData = previousData;

		if (rowIndex !== -1) {
			nextData = setIn(
				previousData,
				['structure', rowIndex.toString(), 'columns'],
				columns
			);
		}

		dispatch(updateRowColumnsLoadingAction(nextData));
		dispatch(enableSavingChangesStatusAction());

		updatePageEditorLayoutData(nextData, state.segmentsExperienceId)
			.then(() => {
				dispatch(disableSavingChangesStatusAction());
				dispatch(updateLastSaveDateAction());
			})
			.catch(() => {
				dispatch(updateRowColumnsErrorAction(previousData));
				dispatch(disableSavingChangesStatusAction());
			});
	};
}

/**
 * @param {Array} layoutData
 * @return {object}
 * @review
 */
function updateRowColumnsErrorAction(layoutData) {
	return {
		type: UPDATE_ROW_COLUMNS_ERROR,
		value: layoutData
	};
}

/**
 * @param {Array} layoutData
 * @return {object}
 * @review
 */
function updateRowColumnsLoadingAction(layoutData) {
	return {
		type: UPDATE_ROW_COLUMNS_LOADING,
		value: layoutData
	};
}

export {updateRowColumnsAction};
