/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {OrderedMap, Record} from 'immutable';
import React, {useContext, useState} from 'react';

import {
	NOTE_FORMAT_PLAIN,
	NOTE_STATUS_APPROVED,
	NOTE_TYPE_GENERAL
} from '../utilities/constants';

// Notes definition with default values

export const NoteRecord = Record({
	content: '',
	createDate: null,
	creatorName: '-',
	creatorPortraitURL: null,
	edited: false,
	format: NOTE_FORMAT_PLAIN,
	htmlContent: '',
	id: null,
	pinned: false,
	status: NOTE_STATUS_APPROVED,
	type: NOTE_TYPE_GENERAL,
	updateURL: null
});

const NotesContext = React.createContext();

function createNote(note) {
	const {
		content,
		createDate,
		creatorName,
		creatorPortraitURL,
		edited,
		format,
		htmlContent,
		key,
		pinned,
		status,
		type,
		updateNoteURL
	} = note;

	return [
		key,
		NoteRecord({
			content,
			createDate,
			creatorName,
			creatorPortraitURL,
			edited,
			format,
			htmlContent,
			id: key,
			pinned,
			status,
			type,
			updateURL: updateNoteURL
		})
	];
}

export function NotesProvider({initialNotes = [], children}) {
	const processedNotes = initialNotes.map(note => createNote(note));
	const [notes, setNotes] = useState(OrderedMap(processedNotes));

	return (
		<NotesContext.Provider
			value={[
				notes,
				{
					addNote(note) {
						setNotes(OrderedMap([createNote(note)]).merge(notes));
					},
					archiveNote(id, status) {
						setNotes(notes.setIn([id, 'status'], status));
					},
					editNote(id, content, htmlContent, edited) {
						setNotes(
							notes
								.setIn([id, 'content'], content)
								.setIn([id, 'htmlContent'], htmlContent)
								.setIn([id, 'edited'], edited)
						);
					},
					pinNote(id, pinned) {
						setNotes(notes.setIn([id, 'pinned'], pinned));
					}
				}
			]}
		>
			{children}
		</NotesContext.Provider>
	);
}

export function useNotes() {
	return useContext(NotesContext);
}
