/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 */

import {cleanup, fireEvent, render} from '@testing-library/react';
import React from 'react';

import Note from '../../../src/main/resources/META-INF/resources/js/components/side_panel/Note';
import {
	NoteRecord,
	NotesProvider
} from '../../../src/main/resources/META-INF/resources/js/hooks/notes';
import {PermissionsProvider} from '../../../src/main/resources/META-INF/resources/js/hooks/permissions';
import {
	CURRENT_TIME,
	NOTE_FORMAT_HTML,
	NOTE_STATUS_APPROVED,
	NOTE_STATUS_ARCHIVED,
	NOTE_TYPE_GENERAL
} from '../../../src/main/resources/META-INF/resources/js/utilities/constants';

const initialNotes = [
	{
		content: 'pinned note',
		createDate: CURRENT_TIME.toLocaleString('en-US'),
		creatorName: 'Jane Doe',
		creatorPortraitURL: '/',
		edited: false,
		format: NOTE_FORMAT_HTML,
		htmlContent: '<div>pinned note</div>',
		key: '123',
		pinned: true,
		status: NOTE_STATUS_APPROVED,
		type: NOTE_TYPE_GENERAL,
		updateNoteURL: '/'
	}
];

const note = props =>
	NoteRecord({
		content: '<div>note 1</div>',
		createDate: CURRENT_TIME.toLocaleString('en-US'),
		creatorName: 'Jane Doe',
		creatorPortraitURL: '/',
		edited: false,
		format: NOTE_FORMAT_HTML,
		id: '123',
		pinned: true,
		status: NOTE_STATUS_APPROVED,
		type: NOTE_TYPE_GENERAL,
		updateURL: '/',
		...props
	});

function renderNote(props) {
	return render(
		<PermissionsProvider permissions={{updatePermission: true}}>
			<NotesProvider initialNotes={initialNotes}>
				<Note note={note(props)} />
			</NotesProvider>
		</PermissionsProvider>
	);
}

function renderNoteWithLimitedPrivilege(props) {
	return render(
		<PermissionsProvider permissions={{updatePermission: false}}>
			<NotesProvider initialNotes={initialNotes}>
				<Note note={note(props)} />
			</NotesProvider>
		</PermissionsProvider>
	);
}

describe('Note', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderNote();

		expect(container).toBeTruthy();
	});

	it('displays a note with author avatar', () => {
		const {getAllByAltText} = renderNote();

		getAllByAltText('note-author-avatar');
	});

	it('displays a note with author name', () => {
		const {getByText} = renderNote();

		getByText('Jane Doe');
	});

	it('displays a note with the create date', () => {
		const {getAllByText} = renderNote({
			createDate: 'May 01, 2020 12:10 AM'
		});

		getAllByText('May 01, 2020 12:10 AM');
	});

	it('displays the "edited" text next to the date of the note that has been modified', () => {
		const {getByText} = renderNote({edited: true});

		getByText('(edited)');
	});

	describe('Note for a user with update privilege', () => {
		it('displays a three-dot menu icon', () => {
			const {getByLabelText} = renderNote();

			getByLabelText('action-menu-icon');
		});

		it('displays the action menu icons on hover', () => {
			const {container, getByLabelText, queryByLabelText} = renderNote();

			expect(queryByLabelText('edit-note-icon')).toBeNull();

			const note = container.querySelector('.note');

			fireEvent.mouseEnter(note);

			getByLabelText('edit-note-icon');

			fireEvent.mouseLeave(note);

			expect(queryByLabelText('edit-note-icon')).toBeNull();
		});

		it('displays no action menu icons for an archived note', () => {
			const {container, queryByLabelText} = renderNote({
				status: NOTE_STATUS_ARCHIVED
			});

			const note = container.querySelector('.note');

			fireEvent.mouseEnter(note);

			expect(queryByLabelText('edit-note-icon')).toBeNull();
		});
	});

	describe('Note for a user without update previlege', () => {
		it('displays no three-dot menu icon', () => {
			const {queryByLabelText} = renderNoteWithLimitedPrivilege();

			expect(queryByLabelText('action-menu-icon')).toBeFalsy();
		});

		it('displays no action menu icons on hover', () => {
			const {
				container,
				queryByLabelText
			} = renderNoteWithLimitedPrivilege();

			expect(queryByLabelText('edit-note-icon')).toBeNull();

			const note = container.querySelector('.note');

			fireEvent.mouseEnter(note);

			expect(queryByLabelText('edit-note-icon')).toBeNull();
		});
	});
});
