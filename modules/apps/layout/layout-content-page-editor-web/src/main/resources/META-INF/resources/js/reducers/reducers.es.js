/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {
	ADD_FRAGMENT_ENTRY_LINK,
	ADD_MAPPED_ASSET_ENTRY,
	ADD_PORTLET,
	ADD_ROW,
	CHANGE_LANGUAGE_ID,
	CLEAR_ACTIVE_ITEM,
	CLEAR_DROP_TARGET,
	CLEAR_HOVERED_ITEM,
	CREATE_SEGMENTS_EXPERIENCE,
	DELETE_FRAGMENT_ENTRY_LINK_COMMENT,
	DELETE_SEGMENTS_EXPERIENCE,
	DUPLICATE_FRAGMENT_ENTRY_LINK,
	DUPLICATE_SEGMENTS_EXPERIENCE,
	EDIT_SEGMENTS_EXPERIENCE,
	MOVE_FRAGMENT_ENTRY_LINK,
	MOVE_ROW,
	REMOVE_FRAGMENT_ENTRY_LINK,
	REMOVE_ROW,
	SELECT_SEGMENTS_EXPERIENCE,
	TOGGLE_SHOW_RESOLVED_COMMENTS,
	UPDATE_ACTIVE_ITEM,
	UPDATE_CONFIG_ATTRIBUTES,
	UPDATE_DROP_TARGET,
	UPDATE_EDITABLE_VALUE_ERROR,
	UPDATE_EDITABLE_VALUE_LOADING,
	UPDATE_FRAGMENT_ENTRY_LINK_COMMENT,
	UPDATE_FRAGMENT_ENTRY_LINK_COMMENT_REPLY,
	UPDATE_FRAGMENT_ENTRY_LINK_CONTENT,
	UPDATE_FRAGMENT_ENTRY_LINK_SEARCH_OPTIONS,
	UPDATE_HOVERED_ITEM,
	UPDATE_LAST_SAVE_DATE,
	UPDATE_PAGE_CONTENTS,
	UPDATE_ROW_COLUMNS_ERROR,
	UPDATE_ROW_COLUMNS_LOADING,
	UPDATE_ROW_COLUMNS_NUMBER_SUCCESS,
	UPDATE_ROW_CONFIG,
	UPDATE_SAVING_CHANGES_STATUS,
	UPDATE_SEGMENTS_EXPERIENCE_PRIORITY,
	UPDATE_SELECTED_SIDEBAR_PANEL_ID,
	UPDATE_WIDGETS
} from '../actions/actions.es';
import {saveChangesReducer} from './changes.es';
import {createSetValueReducer} from './createSetValueReducer.es';
import {
	addFragmentEntryLinkReducer,
	deleteFragmentEntryLinkCommentReducer,
	duplicateFragmentEntryLinkReducer,
	moveFragmentEntryLinkReducer,
	removeFragmentEntryLinkReducer,
	toggleShowResolvedCommentsReducer,
	updateEditableValueReducer,
	updateFragmentEntryLinkCommentReducer,
	updateFragmentEntryLinkConfigReducer,
	updateFragmentEntryLinkContentReducer,
	updateFragmentEntryLinkSearchOptionsReducer
} from './fragments.es';
import {addMappingAssetEntry} from './mapping.es';
import {
	updateActiveItemReducer,
	updateDropTargetReducer,
	updateHoveredItemReducer
} from './placeholders.es';
import {addPortletReducer} from './portlets.es';
import {
	addRowReducer,
	moveRowReducer,
	removeRowReducer,
	updateRowColumnsNumberReducer,
	updateRowConfigReducer
} from './rows.es';
import {
	createSegmentsExperienceReducer,
	deleteSegmentsExperienceReducer,
	duplicateSegmentsExperienceReducer,
	editSegmentsExperienceReducer,
	selectSegmentsExperienceReducer,
	updateSegmentsExperiencePriorityReducer
} from './segmentsExperiences.es';
import {updateWigetsReducer} from './widgets.es';

/**
 * List of reducers
 * @type {object}
 */
const reducers = {
	[ADD_FRAGMENT_ENTRY_LINK]: addFragmentEntryLinkReducer,
	[ADD_MAPPED_ASSET_ENTRY]: addMappingAssetEntry,
	[ADD_PORTLET]: addPortletReducer,
	[ADD_ROW]: addRowReducer,
	[CHANGE_LANGUAGE_ID]: createSetValueReducer('languageId'),
	[CLEAR_ACTIVE_ITEM]: updateActiveItemReducer,
	[CLEAR_DROP_TARGET]: updateDropTargetReducer,
	[CLEAR_HOVERED_ITEM]: updateHoveredItemReducer,
	[CREATE_SEGMENTS_EXPERIENCE]: createSegmentsExperienceReducer,
	[DELETE_FRAGMENT_ENTRY_LINK_COMMENT]: deleteFragmentEntryLinkCommentReducer,
	[DELETE_SEGMENTS_EXPERIENCE]: deleteSegmentsExperienceReducer,
	[DUPLICATE_FRAGMENT_ENTRY_LINK]: duplicateFragmentEntryLinkReducer,
	[DUPLICATE_SEGMENTS_EXPERIENCE]: duplicateSegmentsExperienceReducer,
	[EDIT_SEGMENTS_EXPERIENCE]: editSegmentsExperienceReducer,
	[MOVE_FRAGMENT_ENTRY_LINK]: moveFragmentEntryLinkReducer,
	[MOVE_ROW]: moveRowReducer,
	[REMOVE_FRAGMENT_ENTRY_LINK]: removeFragmentEntryLinkReducer,
	[REMOVE_ROW]: removeRowReducer,
	[SELECT_SEGMENTS_EXPERIENCE]: selectSegmentsExperienceReducer,
	[TOGGLE_SHOW_RESOLVED_COMMENTS]: toggleShowResolvedCommentsReducer,
	[UPDATE_ACTIVE_ITEM]: updateActiveItemReducer,
	[UPDATE_CONFIG_ATTRIBUTES]: updateFragmentEntryLinkConfigReducer,
	[UPDATE_DROP_TARGET]: updateDropTargetReducer,
	[UPDATE_EDITABLE_VALUE_ERROR]: updateEditableValueReducer,
	[UPDATE_EDITABLE_VALUE_LOADING]: updateEditableValueReducer,
	[UPDATE_FRAGMENT_ENTRY_LINK_COMMENT]: updateFragmentEntryLinkCommentReducer,
	[UPDATE_FRAGMENT_ENTRY_LINK_COMMENT_REPLY]: updateFragmentEntryLinkCommentReducer,
	[UPDATE_FRAGMENT_ENTRY_LINK_CONTENT]: updateFragmentEntryLinkContentReducer,
	[UPDATE_FRAGMENT_ENTRY_LINK_SEARCH_OPTIONS]: updateFragmentEntryLinkSearchOptionsReducer,
	[UPDATE_HOVERED_ITEM]: updateHoveredItemReducer,
	[UPDATE_LAST_SAVE_DATE]: saveChangesReducer,
	[UPDATE_PAGE_CONTENTS]: createSetValueReducer('pageContents'),
	[UPDATE_ROW_COLUMNS_ERROR]: createSetValueReducer('layoutData'),
	[UPDATE_ROW_COLUMNS_LOADING]: createSetValueReducer('layoutData'),
	[UPDATE_ROW_COLUMNS_NUMBER_SUCCESS]: updateRowColumnsNumberReducer,
	[UPDATE_ROW_CONFIG]: updateRowConfigReducer,
	[UPDATE_SAVING_CHANGES_STATUS]: saveChangesReducer,
	[UPDATE_SEGMENTS_EXPERIENCE_PRIORITY]: updateSegmentsExperiencePriorityReducer,
	[UPDATE_SELECTED_SIDEBAR_PANEL_ID]: createSetValueReducer(
		'selectedSidebarPanelId'
	),
	[UPDATE_WIDGETS]: updateWigetsReducer
};

/**
 * @param {object} state
 * @param {object} action
 * @param {string} action.type
 * @return {object}
 */
function reducer(state, action) {
	let nextState = state;
	const selectedReducer = reducers[action.type];

	if (selectedReducer) {
		nextState = selectedReducer(nextState, action);
	}

	return nextState;
}

export {reducer, reducers};
export default reducer;
