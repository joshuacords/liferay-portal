/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayButton from '@clayui/button';
import {openToast} from 'frontend-js-web';
import PropTypes from 'prop-types';
import React, {useState} from 'react';

import {updateFragmentEntryLinkCommentReplyAction} from '../../../actions/updateFragmentEntryLinkCommentReply.es';
import useDispatch from '../../../store/hooks/useDispatch.es';
import {addFragmentEntryLinkCommentReply} from '../../../utils/FragmentsEditorFetchUtils.es';
import CommentForm from './CommentForm.es';

const ReplyCommentForm = props => {
	const [addingComment, setAddingComment] = useState(false);
	const [showForm, setShowForm] = useState(false);
	const [textareaContent, setTextareaContent] = useState('');
	const dispatch = useDispatch();

	const handleReplyButtonClick = () => {
		setAddingComment(true);

		addFragmentEntryLinkCommentReply(
			props.fragmentEntryLinkId,
			props.parentCommentId,
			textareaContent
		)
			.then(comment => {
				dispatch(
					updateFragmentEntryLinkCommentReplyAction(
						props.fragmentEntryLinkId,
						props.parentCommentId,
						comment
					)
				);

				setAddingComment(false);
				setShowForm(false);
				setTextareaContent('');
			})
			.catch(() => {
				openToast({
					message: Liferay.Language.get(
						'the-reply-could-not-be-saved'
					),
					title: Liferay.Language.get('error'),
					type: 'danger'
				});

				setAddingComment(false);
			});
	};

	return (
		<div className="mr-3 pb-2">
			{showForm ? (
				<CommentForm
					autoFocus
					id={`pageEditorCommentReplyEditor_${props.parentCommentId}`}
					loading={addingComment}
					onCancelButtonClick={() => {
						setShowForm(false);
						setTextareaContent('');
					}}
					onSubmitButtonClick={handleReplyButtonClick}
					onTextareaChange={content =>
						content && setTextareaContent(content)
					}
					showButtons={true}
					submitButtonLabel={Liferay.Language.get('reply')}
					textareaContent={textareaContent}
				/>
			) : (
				<ClayButton
					borderless
					disabled={props.disabled}
					displayType="secondary"
					onClick={() => setShowForm(true)}
					small
				>
					{Liferay.Language.get('reply')}
				</ClayButton>
			)}
		</div>
	);
};

ReplyCommentForm.propTypes = {
	disabled: PropTypes.bool,
	fragmentEntryLinkId: PropTypes.string.isRequired,
	parentCommentId: PropTypes.string.isRequired
};

export {ReplyCommentForm};
export default ReplyCommentForm;
