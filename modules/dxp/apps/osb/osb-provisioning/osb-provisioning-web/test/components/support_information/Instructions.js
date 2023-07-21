/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {cleanup, fireEvent, render} from '@testing-library/react';
import React from 'react';

import Instructions from '../../../src/main/resources/META-INF/resources/js/components/support_information/Instructions';
import {PermissionsProvider} from '../../../src/main/resources/META-INF/resources/js/hooks/permissions';
import {DASH} from '../../../src/main/resources/META-INF/resources/js/utilities/constants';

function renderInstructions(props) {
	return render(
		<PermissionsProvider permissions={{updatePermission: true}}>
			<Instructions
				accountAttachmentURL="account/attachment/url"
				accountKey="123"
				fileName="OEM instruction file"
				instructions="Sample support instructions text"
				updateAccountAttachmentURL="update/account/attachment/URL"
				updateInstructionsURL="update/instructions/url"
				{...props}
			/>
		</PermissionsProvider>
	);
}

function renderInstructionsWithoutPermission(props) {
	return render(
		<PermissionsProvider permissions={{updatePermission: false}}>
			<Instructions
				accountAttachmentURL="account/attachment/url"
				accountKey="123"
				fileName="OEM instruction file"
				instructions="Sample support instructions text"
				updateAccountAttachmentURL="update/account/attachment/URL"
				updateInstructionsURL="update/instructions/url"
				{...props}
			/>
		</PermissionsProvider>
	);
}

describe('Instructions', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderInstructions();

		expect(container).toBeTruthy();
	});

	it('displays Instructions title', () => {
		const {getByText} = renderInstructions();

		getByText('oem-instructions');
		getByText('support-instructions');
	});

	it('displays Support Instructions text', () => {
		const {getByText} = renderInstructions();

		getByText('Sample support instructions text');
	});

	it('shows OEM instructions file when one is provided', () => {
		const {getByText} = renderInstructions();

		getByText('OEM instruction file');
	});

	it('shows a message when support project does not exist', () => {
		// This happens when there is no support project on the Customer system connected to the current account

		const {getByText} = renderInstructions({
			updateAccountAttachmentURL: '',
			updateInstructionsURL: ''
		});

		getByText('support-project-does-not-exist');
	});

	describe('Instructions with full editing privilege', () => {
		it('shows Support Instructions as editable when clicked on', () => {
			const {getByText} = renderInstructions();

			fireEvent.click(getByText('Sample support instructions text'));

			getByText('save');
			getByText('cancel');
		});

		it('shows no OEM instructions file when one is not provided', () => {
			const {container, queryByText} = renderInstructions({fileName: ''});

			expect(container.querySelector('a')).toBe(null);
			expect(queryByText(DASH)).toBeFalsy();
		});

		it('shows a file selection for OEM instructions', () => {
			const {container} = renderInstructions();

			expect(
				container.querySelector('input[type = "file"]')
			).toBeTruthy();
		});
	});

	describe('Instructions with no editing privilege', () => {
		it('prevents Support Instructions from being edited', () => {
			const {
				getByText,
				queryByText
			} = renderInstructionsWithoutPermission();

			fireEvent.click(getByText('Sample support instructions text'));

			expect(queryByText('save')).toBeFalsy();
			expect(queryByText('cancel')).toBeFalsy();
		});

		it('shows a dash when no OEM instructions file is provided', () => {
			const {queryByText} = renderInstructionsWithoutPermission({
				fileName: ''
			});

			expect(queryByText(DASH)).toBeTruthy();
		});

		it('does not show a file selection for OEM instructions', () => {
			const {container} = renderInstructionsWithoutPermission();

			expect(container.querySelector('input[type = "file"]')).toBeFalsy();
		});
	});
});
