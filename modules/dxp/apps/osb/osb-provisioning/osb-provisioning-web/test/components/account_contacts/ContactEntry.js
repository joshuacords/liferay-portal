/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {cleanup, fireEvent, render, within} from '@testing-library/react';
import React from 'react';

import ContactEntry from '../../../src/main/resources/META-INF/resources/js/components/account_contacts/ContactEntry';

const mockAddKeyFn = jest.fn();
const mockRemoveKeyFn = jest.fn();
const mockSetEmailAddressFn = jest.fn();

function renderContactLine(props) {
	const allRoles = [
		{key: 'KEY-100', name: 'Manager'},
		{key: 'KEY-101', name: 'Member'},
		{key: 'KEY-102', name: 'Analyst'},
		{key: 'KEY-103', name: 'Designer'}
	];

	return render(
		<table>
			<tbody>
				<ContactEntry
					accountName={'Test Account'}
					addFn={mockAddKeyFn}
					allRoles={allRoles}
					emailAddress={''}
					knownContact={false}
					newRoles={[]}
					removeFn={mockRemoveKeyFn}
					setEmailAddress={mockSetEmailAddressFn}
					{...props}
				/>
			</tbody>
		</table>
	);
}

describe('AccountAddress', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderContactLine();

		expect(container).toBeTruthy();
	});

	it('displays account name', () => {
		const {getByText} = renderContactLine();

		getByText('Test Account');
	});

	it('displays first and last name and email if provided', () => {
		const {getByText} = renderContactLine({
			emailAddress: 'test1@liferay.com',
			firstName: 'TestFirst',
			knownContact: true,
			lastName: 'TestLast',
			newRoles: ['KEY-100']
		});

		getByText('TestFirst');
		getByText('TestLast');
		getByText('test1@liferay.com');
	});

	it('displays email as an input if enabled', () => {
		const {container} = renderContactLine();

		expect(container.querySelectorAll('input')[0].type).toBe('text');
	});

	it('hides email as an input if disabled', () => {
		const {container} = renderContactLine({
			emailAddress: 'test1@liferay.com',
			knownContact: true,
			newRoles: ['KEY-100']
		});

		expect(container.querySelectorAll('input')[0].type).toBe('hidden');
	});

	it('does not display full name if email is enabled', () => {
		const {queryByText} = renderContactLine({
			firstName: 'TestFirst',
			lastName: 'TestLast'
		});

		expect(queryByText('TestFirst')).toBeFalsy();
		expect(queryByText('TestLast')).toBeFalsy();
	});

	it('displays contact roles if provided', () => {
		const {container} = renderContactLine({
			emailAddress: 'test1@liferay.com',
			knownContact: true,
			newRoles: ['KEY-100', 'KEY-101']
		});

		const {getByText} = within(
			container.querySelector('.input-group-item')
		);

		getByText('Manager');
		getByText('Member');
	});

	it('calls Add function when contact roles are selected from dropdown', () => {
		const {getByText, getByTitle} = renderContactLine();

		fireEvent.click(getByTitle('add'));

		fireEvent.click(getByText('Manager'));

		expect(mockAddKeyFn).toHaveBeenCalled();
	});

	it('calls Remove function when contact roles are removed', () => {
		const {queryAllByTitle} = renderContactLine({
			emailAddress: 'test1@liferay.com',
			knownContact: true,
			newRoles: ['KEY-100', 'KEY-101']
		});

		fireEvent.click(queryAllByTitle('delete')[0]);

		expect(mockRemoveKeyFn).toHaveBeenCalled();
	});
});
