/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {
	UPDATE_LAST_SAVE_DATE,
	UPDATE_SAVING_CHANGES_STATUS
} from '../actions/actions.es';
import {setIn} from '../utils/FragmentsEditorUpdateUtils.es';

/**
 * @param {!object} state
 * @param {object} action
 * @param {Date} action.lastSaveDate
 * @param {boolean} action.savingChanges
 * @param {string} action.type
 * @return {object}
 * @review
 */
function saveChangesReducer(state, action) {
	let nextState = state;

	if (action.type === UPDATE_LAST_SAVE_DATE) {
		const newDate = action.lastSaveDate.toLocaleTimeString(
			Liferay.ThemeDisplay.getBCP47LanguageId()
		);

		nextState = setIn(nextState, ['lastSaveDate'], newDate);
	}
	else if (action.type === UPDATE_SAVING_CHANGES_STATUS) {
		nextState = setIn(
			nextState,
			['savingChanges'],
			Boolean(action.savingChanges)
		);
	}

	return nextState;
}

export {saveChangesReducer};
