/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {setIn} from '../utils/FragmentsEditorUpdateUtils.es';

/**
 * @param {object} state
 * @param {object} action
 * @param {string} action.classNameId
 * @param {string} action.classPK
 * @param {string} action.title
 * @param {string} action.type
 */
function addMappingAssetEntry(state, action) {
	let nextState = state;

	const hasAssetEntry = nextState.mappedAssetEntries.some(
		assetEntry =>
			assetEntry.classNameId === action.classNameId &&
			assetEntry.classPK === action.classPK
	);

	if (!hasAssetEntry) {
		nextState = setIn(
			nextState,
			['mappedAssetEntries'],
			[...nextState.mappedAssetEntries, action]
		);
	}

	return nextState;
}

export {addMappingAssetEntry};
