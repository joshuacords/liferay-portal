/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {cleanup, render} from '@testing-library/react';
import React from 'react';

import ActionMenu from '../../../src/main/resources/META-INF/resources/js/components/side_panel/ActionMenu';
import {NOTE_TYPE_SALES} from '../../../src/main/resources/META-INF/resources/js/utilities/constants';

function renderActionMenu(props) {
	return render(
		<ActionMenu onEdit={jest.fn()} onPinning={jest.fn()} {...props} />
	);
}

describe('ActionMenu', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderActionMenu();

		expect(container).toBeTruthy();
	});

	it('displays an edit icon', () => {
		const {getByLabelText} = renderActionMenu();

		getByLabelText('edit-note-icon');
	});

	it('displays a pin icon if note has not been pinned on Genearl Notes tab', () => {
		const {getByLabelText} = renderActionMenu();

		getByLabelText('pin-note-icon');
	});

	it('displays an unpin icon if note has been pinned on Genearl Notes tab', () => {
		const {getByLabelText} = renderActionMenu({pinned: true});

		getByLabelText('unpin-note-icon');
	});

	it('displays no pin or unpin icon on Sales Info tab', () => {
		const {queryByLabelText} = renderActionMenu({tabType: NOTE_TYPE_SALES});

		expect(queryByLabelText('pin-note-icon')).toBeFalsy();
		expect(queryByLabelText('unpin-note-icon')).toBeFalsy();
	});
});
