import {UPDATE_ACTIVE_ITEM} from './actions.es';

/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

/**
 * Updates the active item
 * @param {string} activeItemId
 * @param {string} activeItemType
 * @param {object} [options={}]
 * @param {boolean} [options.appendItem=false]
 */
function updateActiveItemAction(
	activeItemId,
	activeItemType,
	{appendItem = false} = {}
) {
	return {
		activeItemId,
		activeItemType,
		appendItem,
		type: UPDATE_ACTIVE_ITEM
	};
}

export {updateActiveItemAction};
