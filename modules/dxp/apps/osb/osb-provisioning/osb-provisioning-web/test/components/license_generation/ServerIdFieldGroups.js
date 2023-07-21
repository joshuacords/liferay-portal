/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {cleanup, fireEvent, render} from '@testing-library/react';
import React from 'react';

import ServerIdFieldGroups from '../../../src/main/resources/META-INF/resources/js/components/license_generation/ServerIdFieldGroups';
import {NewLicenseProvider} from '../../../src/main/resources/META-INF/resources/js/hooks/newLicense';

function renderServerIdFields(props) {
	return render(
		<NewLicenseProvider>
			<ServerIdFieldGroups {...props} />{' '}
		</NewLicenseProvider>
	);
}

describe('ServerIdFieldGroups', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderServerIdFields();

		expect(container).toBeTruthy();
	});

	it('renders a Host Name field', () => {
		const {getByLabelText} = renderServerIdFields();

		getByLabelText('host-name');
	});

	it('renders an IP Address textarea', () => {
		const {getByLabelText} = renderServerIdFields();

		getByLabelText('ip-addresses');
	});

	it('renders a Mac Address textarea', () => {
		const {getByLabelText} = renderServerIdFields();

		getByLabelText('mac-addresses');
	});

	it('renders an Add button', () => {
		const {getByLabelText} = renderServerIdFields();

		getByLabelText('add');
	});

	it('does not render a Delete button when there is only one Server Id Fields section', () => {
		const {queryByLabelText} = renderServerIdFields();

		expect(queryByLabelText('delete')).toBeFalsy();
	});

	it('displays a second set of field groups when the Add button is clicked', () => {
		const {getAllByLabelText, getByLabelText} = renderServerIdFields();

		fireEvent.click(getByLabelText('add'));

		expect(getAllByLabelText('host-name').length).toBe(2);
		expect(getAllByLabelText('ip-addresses').length).toBe(2);
		expect(getAllByLabelText('mac-addresses').length).toBe(2);
		expect(getAllByLabelText('add').length).toBe(2);
	});

	it('displays Delete buttons for each of the field group sections when more than one section is added', () => {
		const {getAllByLabelText, getByLabelText} = renderServerIdFields();

		fireEvent.click(getByLabelText('add'));

		expect(getAllByLabelText('delete').length).toBe(2);
	});

	it('removes a field group when the Delete button is clicked', () => {
		const {getAllByLabelText, getByLabelText} = renderServerIdFields();

		fireEvent.click(getByLabelText('add'));
		fireEvent.click(getAllByLabelText('delete')[0]);

		expect(getAllByLabelText('host-name').length).toBe(1);
		expect(getAllByLabelText('ip-addresses').length).toBe(1);
		expect(getAllByLabelText('mac-addresses').length).toBe(1);
		expect(getAllByLabelText('add').length).toBe(1);
	});

	it('removes the correct field group where the Delete button is associated with', () => {
		const {
			getAllByLabelText,
			getByLabelText,
			queryByDisplayValue
		} = renderServerIdFields();

		fireEvent.change(getByLabelText('host-name'), {
			target: {value: 'Host Name 1'}
		});
		fireEvent.click(getByLabelText('add'));

		expect(queryByDisplayValue('Host Name 1')).toBeTruthy();

		fireEvent.click(getAllByLabelText('delete')[0]);

		expect(queryByDisplayValue('Host Name 1')).toBeFalsy();
	});
});
