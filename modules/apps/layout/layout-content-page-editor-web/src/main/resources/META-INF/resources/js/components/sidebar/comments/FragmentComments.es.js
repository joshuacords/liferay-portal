/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayButton from '@clayui/button';
import ClayIcon from '@clayui/icon';
import PropTypes from 'prop-types';
import React from 'react';

import {CLEAR_ACTIVE_ITEM} from '../../../actions/actions.es';
import {deleteFragmentEntryLinkCommentAction} from '../../../actions/deleteFragmentEntryLinkComment.es';
import {updateFragmentEntryLinkCommentAction} from '../../../actions/updateFragmentEntryLinkComment.es';
import {updateFragmentEntryLinkCommentReplyAction} from '../../../actions/updateFragmentEntryLinkCommentReply.es';
import useDispatch from '../../../store/hooks/useDispatch.es';
import useGetComments from '../../../store/hooks/useGetComments.es';
import {FRAGMENTS_EDITOR_ITEM_TYPES} from '../../../utils/constants';
import SidebarHeader from '../SidebarHeader.es';
import AddCommentForm from './AddCommentForm.es';
import FragmentComment from './FragmentComment.es';
import ResolvedCommentsToggle from './ResolvedCommentsToggle.es';

const FragmentComments = props => {
	const fragmentEntryLink = props.fragmentEntryLink;
	const getComments = useGetComments();
	const fragmentEntryLinkComments = getComments(fragmentEntryLink);
	const dispatch = useDispatch();

	const {
		clearActiveItem,
		deleteComment,
		editComment,
		editCommentReply
	} = getActions(dispatch, props);

	return (
		<>
			<SidebarHeader className="comments-sidebar-title">
				<ClayButton
					borderless
					className="text-dark"
					onClick={clearActiveItem}
					small
				>
					<ClayIcon symbol="angle-left" />
				</ClayButton>

				<span>{fragmentEntryLink.name}</span>
			</SidebarHeader>

			<ResolvedCommentsToggle />

			<div
				data-fragments-editor-item-id={
					fragmentEntryLink.fragmentEntryLinkId
				}
				data-fragments-editor-item-type={
					FRAGMENTS_EDITOR_ITEM_TYPES.fragment
				}
			>
				<AddCommentForm
					fragmentEntryLinkId={fragmentEntryLink.fragmentEntryLinkId}
				/>

				{fragmentEntryLinkComments.map((_, i) => {
					const comment =
						fragmentEntryLinkComments[
							fragmentEntryLinkComments.length - 1 - i
						];

					return (
						<FragmentComment
							comment={comment}
							fragmentEntryLinkId={
								fragmentEntryLink.fragmentEntryLinkId
							}
							key={comment.commentId}
							onDelete={deleteComment}
							onEdit={editComment}
							onEditReply={editCommentReply}
						/>
					);
				})}
			</div>
		</>
	);
};

FragmentComments.propTypes = {
	fragmentEntryLink: PropTypes.object.isRequired
};

const getActions = (dispatch, ownProps) => ({
	clearActiveItem: () =>
		dispatch({
			type: CLEAR_ACTIVE_ITEM
		}),
	deleteComment: comment =>
		dispatch(
			deleteFragmentEntryLinkCommentAction(
				ownProps.fragmentEntryLink.fragmentEntryLinkId,
				comment
			)
		),
	editComment: comment =>
		dispatch(
			updateFragmentEntryLinkCommentAction(
				ownProps.fragmentEntryLink.fragmentEntryLinkId,
				comment
			)
		),
	editCommentReply: parentCommentId => comment =>
		dispatch(
			updateFragmentEntryLinkCommentReplyAction(
				ownProps.fragmentEntryLink.fragmentEntryLinkId,
				parentCommentId,
				comment
			)
		)
});

export {FragmentComments};
export default FragmentComments;
