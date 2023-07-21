import {updateWidgets} from '../utils/FragmentsEditorUpdateUtils.es';

/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

/**
 * @param {object} state
 * @param {object} action
 * @param {Array} action.fragmentEntryLinkIds
 * @param {string} action.type
 * @return {object}
 * @review
 */
function updateWigetsReducer(state, action) {
	let nextState = state;

	nextState = updateWidgets(state, action.fragmentEntryLinkIds);

	return nextState;
}

export {updateWigetsReducer};
