/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

import {UPDATE_HOVERED_ITEM} from '../../../actions/actions.es';
import {updateActiveItemAction} from '../../../actions/updateActiveItem.es';
import useDispatch from '../../../store/hooks/useDispatch.es';
import useGetComments from '../../../store/hooks/useGetComments.es';
import useSelector from '../../../store/hooks/useSelector.es';
import {getLayoutDataFragmentEntryLinkIds} from '../../../utils/LayoutDataList.es';
import {FRAGMENTS_EDITOR_ITEM_TYPES} from '../../../utils/constants';
import SidebarHeader from '../SidebarHeader.es';
import {NoCommentsMessage} from './NoCommentsMessage.es';
import ResolvedCommentsToggle from './ResolvedCommentsToggle.es';

const FragmentEntryLinksWithComments = () => {
	const dispatch = useDispatch();
	const getComments = useGetComments();

	const fragmentEntryLinksWithComments = useSelector(state =>
		getLayoutDataFragmentEntryLinkIds(state.layoutData)
			.map(
				fragmentEntryLinkId =>
					state.fragmentEntryLinks[fragmentEntryLinkId]
			)
			.filter(fragmentEntryLink => fragmentEntryLink)
			.filter(fragmentEntryLink => getComments(fragmentEntryLink).length)
	);

	const setActiveFragmentEntryLink = fragmentEntryLinkId => () => {
		dispatch(
			updateActiveItemAction(
				fragmentEntryLinkId,
				FRAGMENTS_EDITOR_ITEM_TYPES.fragment
			)
		);

		const fragmentEntryLinkElement = document.querySelector(
			`.fragment-entry-link-list [data-fragments-editor-item-id="${fragmentEntryLinkId}"][data-fragments-editor-item-type="${FRAGMENTS_EDITOR_ITEM_TYPES.fragment}"]`
		);

		if (fragmentEntryLinkElement) {
			fragmentEntryLinkElement.scrollIntoView({
				behavior: 'smooth',
				block: 'center'
			});
		}
	};

	const setHoveredFragmentEntryLink = fragmentEntryLinkId => () => {
		dispatch({
			hoveredItemId: fragmentEntryLinkId,
			hoveredItemType: FRAGMENTS_EDITOR_ITEM_TYPES.fragment,
			type: UPDATE_HOVERED_ITEM
		});
	};

	const getFragmentEntryLinkItem = fragmentEntryLink => {
		const commentCount = getComments(fragmentEntryLink).length;
		const {fragmentEntryLinkId, name} = fragmentEntryLink;

		return (
			<button
				aria-label={Liferay.Language.get('show-comments')}
				className="border-0 list-group-item list-group-item-action"
				key={fragmentEntryLinkId}
				onClick={setActiveFragmentEntryLink(fragmentEntryLinkId)}
				onFocus={setHoveredFragmentEntryLink(fragmentEntryLinkId)}
				onMouseOver={setHoveredFragmentEntryLink(fragmentEntryLinkId)}
				type="button"
			>
				<strong className="d-block text-dark">{name}</strong>

				<span className="text-secondary">
					{Liferay.Util.sub(
						commentCount === 1
							? Liferay.Language.get('x-comment')
							: Liferay.Language.get('x-comments'),
						commentCount
					)}
				</span>
			</button>
		);
	};

	return (
		<>
			<SidebarHeader>{Liferay.Language.get('comments')}</SidebarHeader>

			<ResolvedCommentsToggle />

			{fragmentEntryLinksWithComments.length ? (
				<nav className="list-group">
					{fragmentEntryLinksWithComments.map(
						getFragmentEntryLinkItem
					)}
				</nav>
			) : (
				<NoCommentsMessage />
			)}
		</>
	);
};

export {FragmentEntryLinksWithComments};
export default FragmentEntryLinksWithComments;
