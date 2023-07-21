/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {UPDATE_FRAGMENT_ENTRY_LINK_SEARCH_OPTIONS} from './actions.es';
import {
	disableSavingChangesStatusAction,
	enableSavingChangesStatusAction,
	updateLastSaveDateAction
} from './saveChanges.es';
import {updatePageEditorLayoutDataAction} from './updatePageEditorLayoutData.es';

function updateFragmentSearchOptions(fragmentEntryLinkId, nonIndexable) {
	return function(dispatch) {
		dispatch(enableSavingChangesStatusAction());

		dispatch(
			updateFragmentSearchOptionsConfig(fragmentEntryLinkId, nonIndexable)
		);

		dispatch(updatePageEditorLayoutDataAction());

		dispatch(updateLastSaveDateAction());
		dispatch(disableSavingChangesStatusAction());
	};
}

function updateFragmentSearchOptionsConfig(fragmentEntryLinkId, nonIndexable) {
	return {
		fragmentEntryLinkId,
		nonIndexable,
		type: UPDATE_FRAGMENT_ENTRY_LINK_SEARCH_OPTIONS
	};
}

export {updateFragmentSearchOptions};
