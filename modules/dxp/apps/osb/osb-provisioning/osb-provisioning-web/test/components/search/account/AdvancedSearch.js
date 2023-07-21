/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {cleanup, fireEvent, render} from '@testing-library/react';
import React from 'react';

import AdvancedSearch from '../../../../src/main/resources/META-INF/resources/js/components/search/account/AdvancedSearch';

function renderAdvancedSearch() {
	return render(
		<AdvancedSearch
			activeSLANames={[]}
			clickOutsideCallback={jest.fn()}
			countryNames={[]}
			formAction="/url"
			regionNames={[]}
			selectAccountURL="/select/account/url"
			selectFirstLineSupportURL="/select/fls/url"
			selectPartnerURL="/select/partner/url"
			subscriptionStateNames={[]}
			tierNames={[]}
		/>
	);
}

describe('Account AdvancedSearch', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderAdvancedSearch();

		expect(container).toBeTruthy();
	});

	it('displays a Clear button and a Search button', () => {
		const {getByText} = renderAdvancedSearch();

		getByText('clear');
		getByText('search');
	});

	it('displays a set of match results radio buttons with the options of Any or All', () => {
		const {getByText} = renderAdvancedSearch();

		getByText('match:');
		getByText('any');
		getByText('all');
	});

	it('displays the All match results option as checked by default', () => {
		const {getByLabelText} = renderAdvancedSearch();

		expect(getByLabelText('all').checked).toBeTruthy();

		fireEvent.click(getByLabelText('any'));

		expect(getByLabelText('any').checked).toBeTruthy();
		expect(getByLabelText('all').checked).toBeFalsy();
	});

	it('displays an Account section, a Categorization section, and a Dates section', () => {
		const {getByText} = renderAdvancedSearch();

		getByText('account');
		getByText('categorization');
		getByText('dates');
	});
});
