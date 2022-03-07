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

import {cleanup, fireEvent, render, within} from '@testing-library/react';
import React from 'react';

import AddContact from '../../../src/main/resources/META-INF/resources/js/components/account_contacts/AddContact';

function renderAddContact(props) {
	const allRoles = [
		{key: 'KEY-100', name: 'Manager'},
		{key: 'KEY-101', name: 'Member'},
		{key: 'KEY-102', name: 'Analyst'},
		{key: 'KEY-103', name: 'Designer'},
		{key: 'KEY-ADMINISTRATOR', name: 'Support Administrator'},
		{key: 'KEY-PARTNER1', name: 'Partner Member'},
		{key: 'KEY-PARTNER2', name: 'Partner Watcher'},
		{key: 'KEY-SUPPORT1', name: 'Support Requester'},
		{key: 'KEY-SUPPORT2', name: 'Support User'}
	];

	return render(
		<AddContact
			accountName={'Test Account'}
			allRoles={allRoles}
			redirect="/"
			{...props}
		/>
	);
}

describe('AddContact', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderAddContact();

		expect(container).toBeTruthy();
	});

	it('displays email, roles, and account name headers', () => {
		const {getByText} = renderAddContact({});

		getByText('email');
		getByText('roles');
		getByText('account');
	});

	it('displays first and last name if first and last name, email, and roles are provided', () => {
		const {getByText} = renderAddContact({
			currentRoles: ['KEY-100'],
			emailAddress: 'test1@liferay.com',
			firstName: 'TestFirst',
			lastName: 'TestLast'
		});

		getByText('first-name');
		getByText('last-name');
		getByText('TestFirst');
		getByText('TestLast');
	});

	it('displays contact roles if name, email, and roles are provided', () => {
		const {container} = renderAddContact({
			currentRoles: ['KEY-100', 'KEY-101'],
			emailAddress: 'test1@liferay.com',
			firstName: 'TestFirst',
			lastName: 'TestLast'
		});

		const {getByText} = within(
			container.querySelector('.input-group-item')
		);

		getByText('Manager');
		getByText('Member');
	});

	it('does not display first and last name if email and roles are not provided', () => {
		const {queryByText} = renderAddContact({
			firstName: 'TestFirst',
			lastName: 'TestLast'
		});

		expect(queryByText('first-name')).toBeFalsy();
		expect(queryByText('last-name')).toBeFalsy();
		expect(queryByText('TestFirst')).toBeFalsy();
		expect(queryByText('TestLast')).toBeFalsy();
	});

	it('adds contact roles when selected from dropdown', () => {
		const {container, getByText, getByTitle} = renderAddContact();

		fireEvent.click(getByTitle('add'));

		fireEvent.click(getByText('Manager'));

		expect(
			within(container.querySelector('.input-group-item')).queryByText(
				'Manager'
			)
		).toBeTruthy();
	});

	it('removes contact roles when clicked on close', () => {
		const {container, getAllByTitle} = renderAddContact({
			currentRoles: ['KEY-100', 'KEY-101'],
			emailAddress: 'test1@liferay.com'
		});

		fireEvent.click(getAllByTitle('delete')[0]);

		expect(
			within(container.querySelector('.input-group-item')).queryByText(
				'Manager'
			)
		).toBeFalsy();
	});

	it('disables Save button if email is blank', () => {
		const {getByText} = renderAddContact({
			currentRoles: ['KEY-100', 'KEY-101'],
			emailAddress: ''
		});

		expect(getByText('save').disabled).toBeTruthy();
	});

	it('disables Save button if first name is blank', () => {
		const {getByText} = renderAddContact({
			currentRoles: ['KEY-100', 'KEY-101'],
			emailAddress: 'test1@liferay.com',
			firstName: '',
			lastName: 'TestLast',
			newContact: true
		});

		expect(getByText('save').disabled).toBeTruthy();
	});

	it('disables Save button if last name is blank', () => {
		const {getByText} = renderAddContact({
			currentRoles: ['KEY-100', 'KEY-101'],
			emailAddress: 'test1@liferay.com',
			firstName: 'TestLast',
			lastName: '',
			newContact: true
		});

		expect(getByText('save').disabled).toBeTruthy();
	});

	it('disables Save button if no contact roles are selected', () => {
		const {getByText} = renderAddContact({
			currentRoles: [],
			emailAddress: 'test1@liferay.com'
		});

		expect(getByText('save').disabled).toBeTruthy();
	});

	it('disables Save button if more than one Support roles are selected', () => {
		const {getByText} = renderAddContact({
			currentRoles: ['KEY-SUPPORT1', 'KEY-SUPPORT2'],
			emailAddress: 'test1@liferay.com'
		});

		expect(getByText('save').disabled).toBeTruthy();
	});

	it('disables Save button if a Support role and an Administrator role are both selected', () => {
		const {getByText} = renderAddContact({
			currentRoles: ['KEY-SUPPORT1', 'KEY-ADMINISTRATOR'],
			emailAddress: 'test1@liferay.com'
		});

		expect(getByText('save').disabled).toBeTruthy();
	});

	it('disables Save button if more than one Partner roles are selected', () => {
		const {getByText} = renderAddContact({
			currentRoles: ['KEY-PARTNER1', 'KEY-PARTNER2'],
			emailAddress: 'test1@liferay.com'
		});

		expect(getByText('save').disabled).toBeTruthy();
	});

	it('enables Save button if email and contact roles are populated', () => {
		const {getByText} = renderAddContact({
			currentRoles: ['KEY-100', 'KEY-101'],
			emailAddress: 'test1@liferay.com'
		});

		expect(getByText('save').disabled).toBeFalsy();
	});

	it('enables Save button if all the duplicate Partner and Support roles are removed', () => {
		const {getAllByTitle, getByText} = renderAddContact({
			currentRoles: ['KEY-SUPPORT1', 'KEY-PARTNER1', 'KEY-PARTNER2'],
			emailAddress: 'test1@liferay.com'
		});

		fireEvent.click(getAllByTitle('delete')[0]);
		fireEvent.click(getAllByTitle('delete')[1]);

		expect(getByText('save').disabled).toBeFalsy();
	});

	it('displays a warning message if more than one Partner roles are selected', () => {
		const {getByText} = renderAddContact({
			currentRoles: ['KEY-PARTNER1', 'KEY-PARTNER2'],
			emailAddress: 'test1@liferay.com'
		});

		getByText('overlapping-roles');
	});

	it('removes the a warning message if overlapping roles are removed', () => {
		const {getAllByTitle, queryByText} = renderAddContact({
			currentRoles: ['KEY-SUPPORT1', 'KEY-SUPPORT2'],
			emailAddress: 'test1@liferay.com'
		});

		fireEvent.click(getAllByTitle('delete')[0]);

		expect(queryByText('overlapping-roles')).toBeFalsy();
	});
});
