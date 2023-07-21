/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {openToast} from 'frontend-js-web';
import PropTypes from 'prop-types';
import React, {useState} from 'react';

import {updateFragmentEntryLinkCommentAction} from '../../../actions/updateFragmentEntryLinkComment.es';
import useDispatch from '../../../store/hooks/useDispatch.es';
import {addFragmentEntryLinkComment} from '../../../utils/FragmentsEditorFetchUtils.es';
import CommentForm from './CommentForm.es';

const AddCommentForm = props => {
	const [addingComment, setAddingComment] = useState(false);
	const [showButtons, setShowButtons] = useState(false);
	const [textareaContent, setTextareaContent] = useState('');
	const dispatch = useDispatch();

	const _handleCancelButtonClick = () => {
		setShowButtons(false);
		setTextareaContent('');
	};

	const _handleFormFocus = () => {
		setShowButtons(true);
	};

	const _handleCommentButtonClick = () => {
		setAddingComment(true);

		addFragmentEntryLinkComment(props.fragmentEntryLinkId, textareaContent)
			.then(comment => {
				dispatch(
					updateFragmentEntryLinkCommentAction(
						props.fragmentEntryLinkId,
						comment
					)
				);

				setAddingComment(false);
				setShowButtons(false);
				setTextareaContent('');
			})
			.catch(() => {
				openToast({
					message: Liferay.Language.get(
						'the-comment-could-not-be-saved'
					),
					title: Liferay.Language.get('error'),
					type: 'danger'
				});

				setAddingComment(false);
			});
	};

	const _handleTextareaChange = content => {
		if (content) {
			setTextareaContent(content);
		}
	};

	return (
		<div className="px-3">
			<CommentForm
				id="pageEditorCommentEditor"
				loading={addingComment}
				onCancelButtonClick={_handleCancelButtonClick}
				onFormFocus={_handleFormFocus}
				onSubmitButtonClick={_handleCommentButtonClick}
				onTextareaChange={_handleTextareaChange}
				showButtons={showButtons}
				submitButtonLabel={Liferay.Language.get('comment')}
				textareaContent={textareaContent}
			/>
		</div>
	);
};

AddCommentForm.propTypes = {
	fragmentEntryLinkId: PropTypes.string.isRequired
};

export {AddCommentForm};
export default AddCommentForm;
