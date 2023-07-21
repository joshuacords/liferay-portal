/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {duplicateFragmentEntryLink} from '../utils/FragmentsEditorFetchUtils.es';
import {FRAGMENTS_EDITOR_ITEM_TYPES} from '../utils/constants';
import {DUPLICATE_FRAGMENT_ENTRY_LINK} from './actions.es';
import {updateActiveItemAction} from './updateActiveItem.es';
import {updatePageEditorLayoutDataAction} from './updatePageEditorLayoutData.es';

/**
 * @param {string} fragmentEntryLinkId
 * @param {string} fragmentEntryLinkRowType
 * @review
 */
function duplicateFragmentEntryLinkAction(
	fragmentEntryLinkId,
	fragmentEntryLinkRowType
) {
	return function(dispatch) {
		let newFragmentEntryLinkId;

		duplicateFragmentEntryLink(fragmentEntryLinkId)
			.then(fragmentEntryLink => {
				newFragmentEntryLinkId = fragmentEntryLink.fragmentEntryLinkId;

				return dispatch(
					_duplicateFragmentEntryLinkAction(
						fragmentEntryLinkId,
						fragmentEntryLink,
						fragmentEntryLinkRowType
					)
				);
			})
			.then(() => dispatch(updatePageEditorLayoutDataAction()))
			.then(() =>
				dispatch(
					updateActiveItemAction(
						newFragmentEntryLinkId,
						FRAGMENTS_EDITOR_ITEM_TYPES.fragment
					)
				)
			);
	};
}

/**
 * @param {string} originalFragmentEntryLinkId
 * @param {object} fragmentEntryLink
 * @param {string} fragmentEntryLinkRowType
 */
function _duplicateFragmentEntryLinkAction(
	originalFragmentEntryLinkId,
	fragmentEntryLink,
	fragmentEntryLinkRowType
) {
	return {
		fragmentEntryLink,
		fragmentEntryLinkId: originalFragmentEntryLinkId,
		fragmentEntryLinkRowType,
		type: DUPLICATE_FRAGMENT_ENTRY_LINK
	};
}

export {duplicateFragmentEntryLinkAction};
