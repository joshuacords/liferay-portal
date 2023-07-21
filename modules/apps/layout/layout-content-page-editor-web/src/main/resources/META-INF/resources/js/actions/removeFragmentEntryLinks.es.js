/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {
	removeExperience,
	removeFragmentEntryLinks
} from '../utils/FragmentsEditorFetchUtils.es';
import {containsFragmentEntryLinkId} from '../utils/LayoutDataList.es';
import {REMOVE_FRAGMENT_ENTRY_LINK} from './actions.es';
import {updatePageContentsAction} from './updatePageContents.es';
import {updatePageEditorLayoutDataAction} from './updatePageEditorLayoutData.es';
import {updateWidgetsAction} from './updateWidgets.es';

/**
 * Removes a list of fragment entry links
 * @param {array} fragmentEntryLinks
 * @review
 */
function removeFragmentEntryLinksAction(fragmentEntryLinks) {
	return function(dispatch) {
		return removeFragmentEntryLinks(fragmentEntryLinks).then(() =>
			dispatch(updatePageContentsAction())
		);
	};
}

/**
 * Removes a fragment entry link
 * @param {string} fragmentEntryLinkId
 * @review
 */
function removeFragmentEntryLinkAction(fragmentEntryLinkId) {
	return function(dispatch, getState) {
		const state = getState();

		const fragmentEntryLinkIsUsedInOtherExperience = containsFragmentEntryLinkId(
			state.layoutDataList,
			fragmentEntryLinkId,
			state.segmentsExperienceId || state.defaultSegmentsExperienceId
		);

		dispatch(updateWidgetsAction([fragmentEntryLinkId]));

		dispatch(
			_removeFragmentEntryAction(
				fragmentEntryLinkId,
				fragmentEntryLinkIsUsedInOtherExperience
			)
		);

		dispatch(updatePageEditorLayoutDataAction());

		if (!fragmentEntryLinkIsUsedInOtherExperience) {
			dispatch(removeFragmentEntryLinksAction([fragmentEntryLinkId]));
		}
		else {
			removeExperience(
				state.segmentsExperienceId,
				[fragmentEntryLinkId],
				false
			);
		}
	};
}

function _removeFragmentEntryAction(
	fragmentEntryLinkId,
	fragmentEntryLinkIsUsedInOtherExperience
) {
	return {
		fragmentEntryLinkId,
		fragmentEntryLinkIsUsedInOtherExperience,
		type: REMOVE_FRAGMENT_ENTRY_LINK
	};
}

export {removeFragmentEntryLinksAction, removeFragmentEntryLinkAction};
