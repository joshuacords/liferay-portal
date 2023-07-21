/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {cleanup, fireEvent, render, within} from '@testing-library/react';
import React from 'react';

import NotesTabPane from '../../../src/main/resources/META-INF/resources/js/components/side_panel/NotesTabPane';
import {NotesProvider} from '../../../src/main/resources/META-INF/resources/js/hooks/notes';
import {PermissionsProvider} from '../../../src/main/resources/META-INF/resources/js/hooks/permissions';
import {
	CURRENT_TIME,
	NOTE_FORMAT_HTML,
	NOTE_STATUS_APPROVED,
	NOTE_STATUS_ARCHIVED,
	NOTE_TYPE_GENERAL,
	NOTE_TYPE_SALES
} from '../../../src/main/resources/META-INF/resources/js/utilities/constants';

function mockNotes({type}) {
	return [
		{
			content: 'a pinned note',
			createDate: 'Apr 30, 2020 11:53 PM',
			creatorName: 'Jane Doe',
			creatorPortraitURL: '/',
			edited: false,
			format: NOTE_FORMAT_HTML,
			htmlContent: '<div>a pinned note</div>',
			key: '123',
			pinned: true,
			status: NOTE_STATUS_APPROVED,
			type,
			updateNoteURL: '/'
		},
		{
			content: 'top most note',
			createDate: 'May 01, 2020 12:10 AM',
			creatorName: 'Jane Doe',
			creatorPortraitURL: '/',
			edited: false,
			format: NOTE_FORMAT_HTML,
			htmlContent: '<div>top most note</div>',
			key: '321',
			pinned: true,
			status: NOTE_STATUS_APPROVED,
			type,
			updateNoteURL: '/'
		},
		{
			content: 'an unpinned note',
			createDate: CURRENT_TIME.toLocaleString('en-US'),
			creatorName: 'Jane Doe',
			creatorPortraitURL: '/',
			edited: false,
			format: NOTE_FORMAT_HTML,
			htmlContent: '<div>an unpinned note</div>',
			key: '456',
			pinned: false,
			status: NOTE_STATUS_APPROVED,
			type,
			updateNoteURL: '/'
		},
		{
			content: 'an archived note',
			createDate: CURRENT_TIME.toLocaleString('en-US'),
			creatorName: 'Jane Doe',
			creatorPortraitURL: '/',
			edited: false,
			format: NOTE_FORMAT_HTML,
			htmlContent: '<div>an archived note</div>',
			key: '789',
			pinned: false,
			status: NOTE_STATUS_ARCHIVED,
			type,
			updateNoteURL: '/'
		}
	];
}

function renderNotesTabPane({
	type = NOTE_TYPE_GENERAL,
	notes = mockNotes({type}),
	...props
} = {}) {
	return render(
		<PermissionsProvider permissions={{updatePermission: true}}>
			<NotesProvider initialNotes={notes}>
				<NotesTabPane addURL="/" tabType={type} {...props} />
			</NotesProvider>
		</PermissionsProvider>
	);
}

describe('NotesTabPane', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderNotesTabPane();

		expect(container).toBeTruthy();
	});

	it('renders the latest notes first', () => {
		const {container} = renderNotesTabPane();

		const notes = container.querySelectorAll('.note');

		within(notes.item(0)).getByText('May 01, 2020 12:10 AM');
		within(notes.item(1)).getByText('Apr 30, 2020 11:53 PM');
	});

	describe('approved notes', () => {
		it('displays a pinned general note in a "Pinned" section', () => {
			const {getByText} = renderNotesTabPane();

			getByText('pinned');
			getByText('a pinned note');
		});

		it('displays an approved general note in a "General" section', () => {
			const {getByText} = renderNotesTabPane();

			getByText('general');
			getByText('an unpinned note');
		});

		it('displays a textarea for adding a new note', () => {
			const {getByPlaceholderText} = renderNotesTabPane();

			getByPlaceholderText('write-a-note');
		});

		it('does not display a textarea for adding new note if user does not have update privilege', () => {
			const {queryByPlaceholderText} = render(
				<PermissionsProvider permissions={{updatePermission: false}}>
					<NotesProvider
						initialNotes={mockNotes({NOTE_TYPE_GENERAL})}
					>
						<NotesTabPane addURL="/" tabType={NOTE_TYPE_GENERAL} />
					</NotesProvider>
				</PermissionsProvider>
			);

			expect(queryByPlaceholderText('write-a-note')).toBeFalsy();
		});

		it('displays a sales note with no pinned or general section', () => {
			const {queryByText} = renderNotesTabPane({
				type: NOTE_TYPE_SALES
			});

			expect(queryByText('pinned')).toBeFalsy();
			expect(queryByText('general')).toBeFalsy();
		});

		it('displays a button to view archived notes when there are archives', () => {
			const {getByText} = renderNotesTabPane();

			getByText('view-archived-notes');
		});

		it('does not display a button to view archived notes when none are available', () => {
			const {queryByText} = renderNotesTabPane({
				notes: [],
				type: NOTE_TYPE_SALES
			});

			expect(queryByText('view-archived-notes')).toBeNull();
		});

		it('displays a message when there is no data for general notes', () => {
			const {container} = renderNotesTabPane({
				notes: [],
				type: NOTE_TYPE_GENERAL
			});

			expect(container.querySelector('.empty-state').textContent).toEqual(
				'no-notes-were-found'
			);
		});

		it('displays a message when there is no data for sales notes', () => {
			const {container} = renderNotesTabPane({
				notes: [],
				type: NOTE_TYPE_SALES
			});

			expect(container.textContent).toEqual('no-sales-info-were-found');
		});
	});

	describe('archived notes', () => {
		it('displays archived notes when "view Archived Notes" button is clicked', () => {
			const {getByText} = renderNotesTabPane();

			fireEvent.click(getByText('view-archived-notes'));

			getByText('an archived note');
		});

		it('displays a heading', () => {
			const {getByText} = renderNotesTabPane();

			fireEvent.click(getByText('view-archived-notes'));

			getByText('archive');
		});

		it('displays a back button', () => {
			const {getByText} = renderNotesTabPane();

			fireEvent.click(getByText('view-archived-notes'));

			getByText('back');
		});

		it('goes back to the general notes when the back button is clicked', () => {
			const {getByText, queryByText} = renderNotesTabPane();

			fireEvent.click(getByText('view-archived-notes'));
			fireEvent.click(getByText('back'));

			expect(queryByText('archived note')).toBeNull();
		});
	});
});
