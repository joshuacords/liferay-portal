/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {cleanup, fireEvent, render, wait} from '@testing-library/react';
import React from 'react';

import AddNote from '../../../src/main/resources/META-INF/resources/js/components/side_panel/AddNote';
import {NotesProvider} from '../../../src/main/resources/META-INF/resources/js/hooks/notes';
import {
	CURRENT_TIME,
	NOTE_FORMAT_HTML,
	NOTE_STATUS_APPROVED,
	NOTE_TYPE_GENERAL,
	NOTE_TYPE_SALES
} from '../../../src/main/resources/META-INF/resources/js/utilities/constants';

const exampleNotes = [
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

const mockResponse = {
	note: {
		content: 'pinned note',
		createDate: CURRENT_TIME.toLocaleString('en-US'),
		creatorName: 'Jane Doe',
		creatorPortraitURL: '/',
		deleteNoteURL: '/delete/url',
		edited: false,
		format: 'Plain',
		htmlContent: 'pinned note',
		key: '123',
		pinned: false,
		status: NOTE_STATUS_APPROVED,
		type: 'General',
		updateNoteURL: '/update/url'
	},
	ok: true,
	successMessage: 'Note added successfully.'
};

function renderAddNote(props) {
	return render(
		<NotesProvider initialNotes={[]}>
			<AddNote actionURL="add url" {...props} />
		</NotesProvider>
	);
}

function renderEditNote(notes = exampleNotes, props) {
	return render(
		<NotesProvider initialNotes={notes}>
			<AddNote actionURL="edit url" content="test content" {...props} />
		</NotesProvider>
	);
}

describe('AddNote', () => {
	beforeEach(() => {
		jest.spyOn(global, 'fetch').mockResolvedValue(
			jest.fn().mockResolvedValue(mockResponse)
		);
	});

	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderAddNote();

		expect(container).toBeTruthy();
	});

	it('displays a textarea for adding a new note', () => {
		const {getByPlaceholderText} = renderAddNote();

		getByPlaceholderText('write-a-note');
	});

	it('displays a different textarea placeholder for Sales notes', () => {
		const {getByPlaceholderText} = renderAddNote({type: NOTE_TYPE_SALES});

		getByPlaceholderText('write-sales-info');
	});

	it('displays a "Cancel" button when the textarea for adding a new note is focused', () => {
		const {container, getByText} = renderAddNote();

		fireEvent.focus(container.querySelector('textarea'));

		getByText('cancel');
	});

	it('displays a "Save" button when the textarea for adding a new note is focused', () => {
		const {container, getByText} = renderAddNote();

		fireEvent.focus(container.querySelector('textarea'));

		getByText('save');
	});

	it('clears the textarea for adding a note when "Cancel" button is pressed', () => {
		const {container, getByText} = renderAddNote();

		const textarea = container.querySelector('textarea');

		fireEvent.focus(textarea);
		fireEvent.change(textarea, {
			target: {value: 'test'}
		});
		fireEvent.click(getByText('cancel'));

		expect(textarea.value).toMatch('');
	});

	it('displays no "Cancel" or "Save" button after "Cancel" button is pressed', () => {
		const {container, getByText, queryByText} = renderAddNote();

		const textarea = container.querySelector('textarea');

		fireEvent.focus(textarea);
		fireEvent.click(getByText('cancel'));

		expect(queryByText('save')).toBeFalsy();
		expect(queryByText('cancel')).toBeFalsy();
	});

	it('enables the "Save" button when text is entered in the textarea', () => {
		const {container, getByText} = renderAddNote();
		const textarea = container.querySelector('textarea');

		fireEvent.focus(textarea);

		const saveButton = getByText('save');

		expect(saveButton.disabled).toBeTruthy();

		fireEvent.change(textarea, {
			target: {value: 'test'}
		});

		expect(saveButton.disabled).toBeFalsy();
	});

	it('disables the "Save" and "Cancel" buttons after the "Save" button has been pressed', async () => {
		const {container, getByText} = renderAddNote();
		const textarea = container.querySelector('textarea');

		fireEvent.focus(textarea);
		fireEvent.change(textarea, {
			target: {value: 'test'}
		});

		const saveButton = getByText('save');

		await wait(() => {
			fireEvent.click(saveButton);

			expect(saveButton.disabled).toBeTruthy();
			expect(getByText('cancel').disabled).toBeTruthy();
		});
	});

	it('prefills the textarea with original value for editing a note', () => {
		const {container} = renderEditNote();

		expect(container.querySelector('textarea').value).toMatch(
			'test content'
		);
	});

	it('displays a "Cancel" and a "Save" button when editing a note', () => {
		const {getByText} = renderEditNote();

		getByText('cancel');
		getByText('save');
	});

	it('restores the note content when the "Cancel" button is pressed', () => {
		const {container, getByText} = renderEditNote();
		const textarea = container.querySelector('textarea');

		fireEvent.change(textarea, {
			target: {value: 'new content'}
		});
		fireEvent.click(getByText('cancel'));

		expect(textarea.value).toMatch('test content');
	});
});
