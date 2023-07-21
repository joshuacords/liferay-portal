/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {removeExperience} from '../utils/FragmentsEditorFetchUtils.es';
import {
	getRowFragmentEntryLinkIds,
	getRowIndex
} from '../utils/FragmentsEditorGetUtils.es';
import {containsFragmentEntryLinkId} from '../utils/LayoutDataList.es';
import {REMOVE_ROW} from './actions.es';
import {removeFragmentEntryLinksAction} from './removeFragmentEntryLinks.es';
import {updatePageEditorLayoutDataAction} from './updatePageEditorLayoutData.es';
import {updateWidgetsAction} from './updateWidgets.es';

/**
 * Removes a row of the layout data structure
 * @param {number} rowId
 * @review
 */
function removeRowAction(rowId) {
	return function(dispatch, getState) {
		const state = getState();

		dispatch(_removeRowAction(rowId));

		const fragmentEntryLinkIds = getRowFragmentEntryLinkIds(
			state.layoutData.structure[
				getRowIndex(state.layoutData.structure, rowId)
			]
		);

		const fragmentEntryLinkIdsToRemove = fragmentEntryLinkIds.filter(
			fragmentEntryLinkId =>
				!containsFragmentEntryLinkId(
					state.layoutDataList,
					fragmentEntryLinkId,
					state.segmentsExperienceId ||
						state.defaultSegmentsExperienceId
				)
		);

		const fragmentEntryLinkIdsToRemoveExperience = fragmentEntryLinkIds.filter(
			fragmentEntryLinkId =>
				containsFragmentEntryLinkId(
					state.layoutDataList,
					fragmentEntryLinkId,
					state.segmentsExperienceId ||
						state.defaultSegmentsExperienceId
				)
		);

		if (fragmentEntryLinkIdsToRemoveExperience.length > 0) {
			removeExperience(
				state.segmentsExperienceId || state.defaultSegmentsExperienceId,
				fragmentEntryLinkIdsToRemoveExperience,
				false
			);
		}

		dispatch(updateWidgetsAction(fragmentEntryLinkIds));

		dispatch(updatePageEditorLayoutDataAction());

		dispatch(removeFragmentEntryLinksAction(fragmentEntryLinkIdsToRemove));
	};
}

function _removeRowAction(rowId) {
	return {
		rowId,
		type: REMOVE_ROW
	};
}

export {removeRowAction};
