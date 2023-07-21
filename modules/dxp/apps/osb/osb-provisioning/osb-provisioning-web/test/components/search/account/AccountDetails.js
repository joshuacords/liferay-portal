/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {cleanup, render} from '@testing-library/react';
import React from 'react';

import AccountDetails from '../../../../src/main/resources/META-INF/resources/js/components/search/account/AccountDetails';

function renderAccount() {
	return render(
		<AccountDetails
			countryNames={['Afghanistan', 'Aland Islands', 'Albania']}
			selectAccountURL="/select/account/url"
			selectFirstLineSupportURL="/select/fls/url"
			selectPartnerURL="/select/partner/url"
		/>
	);
}

describe('AccountDetails', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderAccount();

		expect(container).toBeTruthy();
	});

	it('displays an Account Name field', () => {
		const {getByLabelText} = renderAccount();

		getByLabelText('account-name');
	});

	it('displays a Code field', () => {
		const {getByLabelText} = renderAccount();

		getByLabelText('code');
	});

	it('displays a Parent Account field', () => {
		const {getByText} = renderAccount();

		getByText('parent-account');
	});

	it('displays a Project Worker email field', () => {
		const {getByLabelText} = renderAccount();

		expect(getByLabelText('project-worker').type).toBe('email');
	});

	it('displays a Partner/Reseller/SI field', () => {
		const {getByText} = renderAccount();

		getByText('partner-reseller-si');
	});

	it('displays a First Line Support field', () => {
		const {getByText} = renderAccount();

		getByText('first-line-support');
	});

	it('displays a Country dropdown field', () => {
		const {getByLabelText, getByText} = renderAccount();

		getByLabelText('country');
		getByText('Afghanistan');
	});

	it('displays an External Account Key field', () => {
		const {getByText} = renderAccount();

		getByText('external-account-key');
	});

	it('displays a Notes field', () => {
		const {getByText} = renderAccount();

		getByText('notes');
	});

	it('displays a Sales Info field', () => {
		const {getByText} = renderAccount();

		getByText('sales-info');
	});
});
