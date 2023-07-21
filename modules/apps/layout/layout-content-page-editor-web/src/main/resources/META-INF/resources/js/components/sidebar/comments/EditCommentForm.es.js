/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {openToast} from 'frontend-js-web';
import PropTypes from 'prop-types';
import React, {useState} from 'react';

import {editFragmentEntryLinkComment} from '../../../utils/FragmentsEditorFetchUtils.es';
import CommentForm from './CommentForm.es';

const EditCommentForm = props => {
	const [editingComment, setEditingComment] = useState(false);
	const [textareaContent, setTextareaContent] = useState(props.comment.body);

	const _handleCommentButtonClick = () => {
		setEditingComment(true);

		editFragmentEntryLinkComment(props.comment.commentId, textareaContent)
			.then(comment => {
				setEditingComment(false);

				props.onEdit(comment);
				props.onCloseForm();
			})
			.catch(() => {
				openToast({
					message: Liferay.Language.get(
						'the-comment-could-not-be-edited'
					),
					title: Liferay.Language.get('error'),
					type: 'danger'
				});

				setEditingComment(false);
			});
	};

	return (
		<CommentForm
			autoFocus
			id={`pageEditorCommentEditor_${props.comment.commentId}`}
			loading={editingComment}
			onCancelButtonClick={() => props.onCloseForm()}
			onSubmitButtonClick={_handleCommentButtonClick}
			onTextareaChange={content => setTextareaContent(content)}
			showButtons
			submitButtonLabel={Liferay.Language.get('update')}
			textareaContent={textareaContent}
		/>
	);
};

EditCommentForm.defaultProps = {
	onEdit: () => {}
};

EditCommentForm.propTypes = {
	comment: PropTypes.shape({
		body: PropTypes.string.isRequired,
		commentId: PropTypes.string.isRequired
	}),
	onCloseForm: PropTypes.func.isRequired,
	onEdit: PropTypes.func
};

export {EditCommentForm};
export default EditCommentForm;
