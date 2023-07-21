/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {cleanup, fireEvent, render} from '@testing-library/react';
import React from 'react';

import SupportDetails from '../../../src/main/resources/META-INF/resources/js/components/support_information/SupportDetails';
import {PermissionsProvider} from '../../../src/main/resources/META-INF/resources/js/hooks/permissions';

function renderSupportDetails(props) {
	return render(
		<PermissionsProvider permissions={{updatePermission: true}}>
			<SupportDetails
				account={{
					code: '123',
					editAccountURL: 'edit/account/url',
					key: '123',
					name: 'Test Account',
					region: 'United States',
					status: 'Active',
					tier: 'Regular'
				}}
				language={{id: 'en_US', name: 'English'}}
				languageList={[
					{id: 'en_US', name: 'English'},
					{id: 'zh_CN', name: 'Chinese'},
					{id: 'es_ES', name: 'Spanish'}
				]}
				regionNames={['United States', 'China', 'Spain']}
				updateAccountURL="edit/account/url"
				updateLanguageIdURL="update/language/id/url"
				{...props}
			/>
		</PermissionsProvider>
	);
}

function renderSupportDetailsWithoutPermission(props) {
	return render(
		<PermissionsProvider permissions={{updatePermission: false}}>
			<SupportDetails
				account={{
					code: '123',
					editAccountURL: 'edit/account/url',
					key: '123',
					name: 'Test Account',
					region: 'United States',
					status: 'Active',
					tier: 'Regular'
				}}
				language={{id: 'en_US', name: 'English'}}
				languageList={[
					{id: 'en_US', name: 'English'},
					{id: 'zh_CN', name: 'Chinese'},
					{id: 'es_ES', name: 'Spanish'}
				]}
				regionNames={['United States', 'China', 'Spain']}
				updateAccountURL="edit/account/url"
				updateLanguageIdURL="update/language/id/url"
				{...props}
			/>
		</PermissionsProvider>
	);
}

describe('SupportDetails', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderSupportDetails();

		expect(container).toBeTruthy();
	});

	it('displays Details header', () => {
		const {getByText} = renderSupportDetails();

		getByText('details');
	});

	it('displays Support region title', () => {
		const {getByText} = renderSupportDetails();

		getByText('support-region');
	});

	it('displays Support language title', () => {
		const {getByText} = renderSupportDetails();

		getByText('support-language');
	});

	it('shows a message when support project does not exist', () => {
		// This happens when there is no support project on the Customer system connected to the current account

		const {getByText} = renderSupportDetails({
			updateLanguageIdURL: ''
		});

		getByText('support-project-does-not-exist');
	});

	describe('Support Details with full editing privilege', () => {
		it('displays region options when the user clicks on the Region field', () => {
			const {getByText} = renderSupportDetails();

			fireEvent.click(getByText('United States'));

			getByText('China');
			getByText('Spain');
		});

		it('displays language options when the user clicks on the Language field', () => {
			const {getByText} = renderSupportDetails();

			fireEvent.click(getByText('English'));

			getByText('Chinese');
			getByText('Spanish');
		});
	});

	describe('Support Details without editing privilege', () => {
		it('prevents the Region field from being interacted', () => {
			const {
				getByText,
				queryByText
			} = renderSupportDetailsWithoutPermission();

			fireEvent.click(getByText('United States'));

			expect(queryByText('China')).toBeFalsy();
			expect(queryByText('Spain')).toBeFalsy();
		});

		it('prevents the Language field from being interacted', () => {
			const {
				getByText,
				queryByText
			} = renderSupportDetailsWithoutPermission();

			fireEvent.click(getByText('English'));

			expect(queryByText('Chinese')).toBeFalsy();
			expect(queryByText('Spanish')).toBeFalsy();
		});
	});
});
