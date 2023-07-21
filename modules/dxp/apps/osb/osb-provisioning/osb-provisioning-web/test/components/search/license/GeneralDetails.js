/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {cleanup, render} from '@testing-library/react';
import React from 'react';

import LicenseDetails from '../../../../src/main/resources/META-INF/resources/js/components/search/license/LicenseDetails';

function renderDetails() {
	return render(<LicenseDetails />);
}

describe('License Search General Details', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderDetails();

		expect(container).toBeTruthy();
	});

	it('displays a Koroneiki Account Key field', () => {
		const {getByLabelText} = renderDetails();

		getByLabelText('account-key');
	});

	it('displays a Koroneiki Product Purchase Key field', () => {
		const {getByLabelText} = renderDetails();

		getByLabelText('product-purchase-key');
	});

	it('displays a Created By email field', () => {
		const {getByLabelText} = renderDetails();

		expect(getByLabelText('created-by').type).toBe('email');
	});

	it('displays a Last Edited By email field', () => {
		const {getByLabelText} = renderDetails();

		expect(getByLabelText('last-edited-by').type).toBe('email');
	});

	it('displays an Active checkbox fieldset', () => {
		const {getByLabelText, getByText} = renderDetails();

		getByLabelText('yes');
		getByLabelText('no');
		getByText('active');
	});
});
