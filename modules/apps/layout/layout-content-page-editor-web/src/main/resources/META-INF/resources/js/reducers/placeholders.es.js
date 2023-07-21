/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {
	CLEAR_ACTIVE_ITEM,
	CLEAR_DROP_TARGET,
	CLEAR_HOVERED_ITEM,
	UPDATE_ACTIVE_ITEM,
	UPDATE_DROP_TARGET,
	UPDATE_HOVERED_ITEM
} from '../actions/actions.es';
import {setIn} from '../utils/FragmentsEditorUpdateUtils.es';

/**
 * Updates active element data with the information sent.
 * @param {!object} state
 * @param {object} action
 * @param {string} action.activeItemId
 * @param {string} action.activeItemType
 * @param {boolean} action.appendItem
 * @param {string} action.type
 * @return {object}
 * @review
 */
function updateActiveItemReducer(state, action) {
	let nextState = state;

	if (action.type === CLEAR_ACTIVE_ITEM) {
		nextState = setIn(nextState, ['activeItemId'], null);
		nextState = setIn(nextState, ['activeItemType'], null);
		nextState = setIn(nextState, ['selectedItems'], []);
	}
	else if (action.type === UPDATE_ACTIVE_ITEM) {
		nextState = setIn(nextState, ['activeItemId'], action.activeItemId);
		nextState = setIn(nextState, ['activeItemType'], action.activeItemType);

		let selectedItems = [
			{
				itemId: action.activeItemId,
				itemType: action.activeItemType
			}
		];

		if (action.appendItem && state.contentCreationEnabled) {
			selectedItems = nextState.selectedItems
				.filter(
					item =>
						item.itemId !== action.activeItemId ||
						item.itemType !== action.activeItemType
				)
				.concat(selectedItems);
		}

		nextState = setIn(nextState, ['selectedItems'], selectedItems);
	}

	return nextState;
}

/**
 * Updates drop target element with the information sent.
 * @param {!object} state
 * @param {object} action
 * @param {string} action.dropTargetBorder
 * @param {string} action.dropTargetItemId
 * @param {string} action.dropTargetItemType
 * @param {string} action.type
 * @return {object}
 * @review
 */
function updateDropTargetReducer(state, action) {
	let nextState = state;

	if (action.type === CLEAR_DROP_TARGET) {
		nextState = setIn(nextState, ['dropTargetBorder'], null);
		nextState = setIn(nextState, ['dropTargetItemId'], null);
		nextState = setIn(nextState, ['dropTargetItemType'], null);
	}
	else if (action.type === UPDATE_DROP_TARGET) {
		nextState = setIn(
			nextState,
			['dropTargetBorder'],
			action.dropTargetBorder
		);

		nextState = setIn(
			nextState,
			['dropTargetItemId'],
			action.dropTargetItemId
		);

		nextState = setIn(
			nextState,
			['dropTargetItemType'],
			action.dropTargetItemType
		);
	}

	return nextState;
}

/**
 * Updates hovered element data with the information sent.
 * @param {object} state
 * @param {object} action
 * @param {string} action.hoveredItemId
 * @param {string} action.hoveredItemType
 * @param {string} action.type
 * @return {object}
 * @review
 */
function updateHoveredItemReducer(state, action) {
	let nextState = state;

	if (action.type === CLEAR_HOVERED_ITEM) {
		nextState = setIn(nextState, ['hoveredItemId'], null);
		nextState = setIn(nextState, ['hoveredItemType'], null);
	}
	else if (action.type === UPDATE_HOVERED_ITEM) {
		nextState = setIn(nextState, ['hoveredItemId'], action.hoveredItemId);

		nextState = setIn(
			nextState,
			['hoveredItemType'],
			action.hoveredItemType
		);
	}

	return nextState;
}

export {
	updateActiveItemReducer,
	updateDropTargetReducer,
	updateHoveredItemReducer
};
