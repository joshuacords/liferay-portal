/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClaySticker from '@clayui/sticker';
import PropTypes from 'prop-types';
import React, {useState} from 'react';

import {NoteRecord, useNotes} from '../../hooks/notes';
import {usePermissions} from '../../hooks/permissions';
import {
	NOTE_PRIORITY_PINNED,
	NOTE_PRIORITY_UNPINNED,
	NOTE_STATUS_APPROVED,
	NOTE_STATUS_ARCHIVED
} from '../../utilities/constants';
import {request} from '../../utilities/helpers';
import ActionMenu from './ActionMenu';
import AddNote from './AddNote';
import PanelDropdownMenu from './PanelDropdownMenu';

function Note({note}) {
	const [editNote, setEditNote] = useState(false);
	const [showActionMenu, setShowActionMenu] = useState(false);
	const [, {archiveNote, pinNote}] = useNotes();
	const {updatePermission} = usePermissions();

	const {
		content,
		format,
		htmlContent,
		id,
		pinned,
		status,
		type,
		updateURL
	} = note;

	const noteData = prop => {
		return {
			content,
			format,
			priority: pinned ? NOTE_PRIORITY_PINNED : NOTE_PRIORITY_UNPINNED,
			status,
			type,
			...prop
		};
	};

	function handleArchive() {
		const formData = noteData({
			status:
				status === NOTE_STATUS_APPROVED
					? NOTE_STATUS_ARCHIVED
					: NOTE_STATUS_APPROVED
		});

		request(updateURL, formData, 'formData')
			.then(data => {
				const {note} = data;

				archiveNote(note.key, note.status);
			})
			.catch(err =>
				console.error(
					`Request to archive/unarchive Note failed with: ${err}`
				)
			);
	}

	function handleCancel() {
		setEditNote(false);
	}

	function handleEdit() {
		setEditNote(true);
	}

	function handlePinning() {
		const formData = noteData({
			priority: pinned ? NOTE_PRIORITY_UNPINNED : NOTE_PRIORITY_PINNED
		});

		request(updateURL, formData, 'formData')
			.then(data => {
				const {note} = data;

				pinNote(note.key, note.pinned);
			})
			.catch(err =>
				console.error(`Request to pin/unpin Note failed with: ${err}`)
			);
	}

	return (
		<div
			className="note"
			onMouseEnter={() =>
				setShowActionMenu(status === NOTE_STATUS_APPROVED)
			}
			onMouseLeave={() => setShowActionMenu(false)}
		>
			<div className="note-header">
				<div className="note-metadata">
					<ClaySticker
						displayType="secondary"
						shape="circle"
						size="md"
					>
						<img
							alt={Liferay.Language.get('note-author-avatar')}
							className="sticker-img"
							src={note.creatorPortraitURL}
						/>
					</ClaySticker>

					<div className="metadata">
						<h4 className="note-author">{note.creatorName}</h4>
						<div className="note-create-date">
							{note.createDate}{' '}
							{note.edited && (
								<span className="edited">
									({Liferay.Language.get('edited')})
								</span>
							)}
						</div>
					</div>
				</div>

				{updatePermission && (
					<div className="note-menu">
						{showActionMenu && (
							<ActionMenu
								onEdit={handleEdit}
								onPinning={handlePinning}
								pinned={pinned}
								tabType={type}
							/>
						)}

						<PanelDropdownMenu
							id={id}
							onArchive={handleArchive}
							onEdit={handleEdit}
							onPinning={handlePinning}
							pinned={pinned}
							status={status}
							tabType={type}
						/>
					</div>
				)}
			</div>

			{editNote ? (
				<AddNote
					actionURL={updateURL}
					content={content}
					format={format}
					id={id}
					onCancel={handleCancel}
					pinned={pinned}
					status={status}
					type={type}
				/>
			) : (
				<section
					className="note-content"
					dangerouslySetInnerHTML={{__html: htmlContent}}
				/>
			)}
		</div>
	);
}

Note.propTypes = {
	note: PropTypes.instanceOf(NoteRecord)
};

export default Note;
