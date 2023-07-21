/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

import useSelector from '../../../store/hooks/useSelector.es';
import {getItemPath} from '../../../utils/FragmentsEditorGetUtils.es';
import {FRAGMENTS_EDITOR_ITEM_TYPES} from '../../../utils/constants';
import {FragmentComments} from './FragmentComments.es';
import {FragmentEntryLinksWithComments} from './FragmentEntryLinksWithComments.es';

const SidebarComments = () => {
	const activeItemId = useSelector(state => state.activeItemId);
	const activeItemType = useSelector(state => state.activeItemType);
	const structure = useSelector(state => state.layoutData.structure);
	const activeFragmentEntryLink = getItemPath(
		activeItemId,
		activeItemType,
		structure
	).find(
		activeItem =>
			activeItem.itemType === FRAGMENTS_EDITOR_ITEM_TYPES.fragment
	);
	const fragmentEntryLink = useSelector(state =>
		activeFragmentEntryLink
			? state.fragmentEntryLinks[activeFragmentEntryLink.itemId]
			: null
	);

	if (fragmentEntryLink) {
		return <FragmentComments fragmentEntryLink={fragmentEntryLink} />;
	}

	return <FragmentEntryLinksWithComments />;
};

export {SidebarComments};
export default SidebarComments;
