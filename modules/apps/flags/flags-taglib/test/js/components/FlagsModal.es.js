/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {cleanup, render, waitForElement} from '@testing-library/react';
import React from 'react';

import FlagsModal from '../../../src/main/resources/META-INF/resources/flags/js/components/FlagsModal.es';
import {
	STATUS_ERROR,
	STATUS_LOGIN,
	STATUS_REPORT,
	STATUS_SUCCESS
} from '../../../src/main/resources/META-INF/resources/flags/js/constants.es';

function _renderFlagsModalComponent({
	captchaURI = '',
	companyName = 'Liferay',
	handleClose = () => {},
	handleInputChange = () => {},
	handleSubmit = () => {},
	isSending = false,
	pathTermsOfUse = '/',
	selectedReason = 'value',
	reasons = {value: 'text', value2: 'text2'},
	signedIn = true,
	status = STATUS_REPORT
} = {}) {
	const observer = {
		dispatch: () => {},
		mutation: [1, 1]
	};

	return render(
		<FlagsModal
			captchaURI={captchaURI}
			companyName={companyName}
			handleClose={handleClose}
			handleInputChange={handleInputChange}
			handleSubmit={handleSubmit}
			isSending={isSending}
			observer={observer}
			pathTermsOfUse={pathTermsOfUse}
			reasons={reasons}
			selectedReason={selectedReason}
			signedIn={signedIn}
			status={status}
		/>,
		{
			baseElement: document.body
		}
	);
}

describe('FlagsModal', () => {
	afterEach(cleanup);

	it('renders', async () => {
		const {getByRole, getByText} = _renderFlagsModalComponent();

		await waitForElement(() => getByText('report-inappropriate-content'));
		await waitForElement(() => getByRole('form'));
	});

	it('renders as guess and render email field', async () => {
		const {getByLabelText} = _renderFlagsModalComponent({
			signedIn: false
		});

		await waitForElement(() => getByLabelText('email', {exact: false}));
	});

	it('renders error', async () => {
		const {getByText} = _renderFlagsModalComponent({status: STATUS_ERROR});

		await waitForElement(() =>
			getByText('an-error-occurred', {exact: false})
		);
	});

	it('renders login', async () => {
		const {getByText} = _renderFlagsModalComponent({status: STATUS_LOGIN});

		await waitForElement(() => getByText('please-sign-in', {exact: false}));
	});

	it('renders success', async () => {
		const {getByText} = _renderFlagsModalComponent({
			status: STATUS_SUCCESS
		});

		await waitForElement(() => getByText('thank-you', {exact: false}));
	});
});
