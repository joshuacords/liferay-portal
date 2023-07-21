/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {UPDATE_FRAGMENT_ENTRY_LINK_COMMENT_REPLY} from './actions.es';

/**
 * Adds/updates a given comment reply
 * @param {string} fragmentEntryLinkId
 * @param {string} parentCommentId
 * @param {object} comment
 */
const updateFragmentEntryLinkCommentReplyAction = (
	fragmentEntryLinkId,
	parentCommentId,
	comment
) => ({
	comment,
	fragmentEntryLinkId,
	parentCommentId,
	type: UPDATE_FRAGMENT_ENTRY_LINK_COMMENT_REPLY
});

export {updateFragmentEntryLinkCommentReplyAction};
