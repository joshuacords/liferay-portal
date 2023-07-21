/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {deleteIn, setIn} from '../utils/FragmentsEditorUpdateUtils.es';

/**
 * @param {string|string[]} keyPath
 * @return {function}
 */
function createSetValueReducer(keyPath) {
	const path = Array.isArray(keyPath) ? keyPath : [keyPath];

	/**
	 * @type {function}
	 * @param {object} state
	 * @param {object} action
	 * @param {any} [action.value]
	 */
	return (state, action) => {
		let nextState = state;

		if ('value' in action) {
			nextState = setIn(nextState, path, action.value);
		}
		else {
			nextState = deleteIn(nextState, path);
		}

		return nextState;
	};
}

export {createSetValueReducer};
