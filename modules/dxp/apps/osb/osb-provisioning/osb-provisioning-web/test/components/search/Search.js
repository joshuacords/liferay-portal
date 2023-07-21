/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {cleanup, fireEvent, render} from '@testing-library/react';
import times from 'lodash.times';
import React from 'react';

import {default as AccountSearch} from '../../../src/main/resources/META-INF/resources/js/components/search/account/Search';
import {default as LicenseKeySearch} from '../../../src/main/resources/META-INF/resources/js/components/search/license/Search';

function renderAccountSearch() {
	return render(
		<AccountSearch
			accountsHomeURL="/accounts/home/URL"
			activeSLANames={[]}
			countryNames={[]}
			regionNames={[]}
			resourceURL="/resource/URL"
			selectAccountURL="/select/account/url"
			selectFirstLineSupportURL="/select/fls/url"
			selectPartnerURL="/select/partner/url"
			subscriptionStateNames={[]}
			tierNames={[]}
		/>
	);
}

const dummyData = index => {
	return {
		label: `name ${index}`,
		value: `value${index}`
	};
};

function renderLicenseKeySearch() {
	return render(
		<LicenseKeySearch
			licenseHomeURL="/license/home/URL"
			licenseTypes={times(Math.random() * 100, dummyData)}
			products={times(Math.random() * 100, dummyData)}
			productVersions={times(Math.random() * 100, dummyData)}
		/>
	);
}

describe('Search', () => {
	afterEach(cleanup);

	describe('Account Search', () => {
		it('renders', () => {
			const {container} = renderAccountSearch();

			expect(container).toBeTruthy();
		});

		it('displays a search input', () => {
			const {getByPlaceholderText} = renderAccountSearch();

			getByPlaceholderText('search-accounts');
		});

		it('displays a search icon', () => {
			const {getByLabelText} = renderAccountSearch();

			getByLabelText('search-icon');
		});

		it('displays a caret to trigger Advanced Search', () => {
			const {getByLabelText} = renderAccountSearch();

			getByLabelText('advanced-search-icon');
		});

		it('disables autocomplete when Advanced Search is expanded', () => {
			const {
				getByLabelText,
				getByPlaceholderText
			} = renderAccountSearch();

			fireEvent.click(getByLabelText('advanced-search-icon'));

			expect(getByPlaceholderText('search-accounts').disabled).toBe(true);
		});

		it('disables keyword search button when Advanced Search is expanded', () => {
			const {getByLabelText} = renderAccountSearch();

			const keywordSearchButton = getByLabelText('keyword-search');

			expect(keywordSearchButton.disabled).toBe(false);

			fireEvent.click(getByLabelText('advanced-search-icon'));

			expect(keywordSearchButton.disabled).toBe(true);
		});

		it('opens the Advanced Search when the caret is clicked', () => {
			const {getByLabelText} = renderAccountSearch();

			fireEvent.click(getByLabelText('advanced-search-icon'));

			const advancedSearchToggler = getByLabelText(
				'close-advanced-search'
			);

			expect(
				advancedSearchToggler.getAttribute('aria-expanded')
			).toBeTruthy();
		});
	});

	describe('License Search', () => {
		it('renders', () => {
			const {container} = renderLicenseKeySearch();

			expect(container).toBeTruthy();
		});

		it('displays a search input', () => {
			const {getByPlaceholderText} = renderLicenseKeySearch();

			getByPlaceholderText('search-licenses');
		});

		it('displays a search icon', () => {
			const {getByLabelText} = renderLicenseKeySearch();

			getByLabelText('search-icon');
		});

		it('displays a caret to trigger Advanced Search', () => {
			const {getByLabelText} = renderLicenseKeySearch();

			getByLabelText('advanced-search-icon');
		});

		it('disables keywords search when Advanced Search is expanded', () => {
			const {
				getByLabelText,
				getByPlaceholderText
			} = renderLicenseKeySearch();

			const keywordSearchButton = getByLabelText('keyword-search');
			const keywordSearchInput = getByPlaceholderText('search-licenses');

			expect(keywordSearchInput.disabled).toBe(false);
			expect(keywordSearchButton.disabled).toBe(false);

			fireEvent.click(getByLabelText('advanced-search-icon'));

			expect(keywordSearchInput.disabled).toBe(true);
			expect(keywordSearchButton.disabled).toBe(true);
		});

		it('opens the Advanced Search when the caret is clicked', () => {
			const {getByLabelText} = renderLicenseKeySearch();

			fireEvent.click(getByLabelText('advanced-search-icon'));

			const advancedSearchToggler = getByLabelText(
				'close-advanced-search'
			);

			expect(
				advancedSearchToggler.getAttribute('aria-expanded')
			).toBeTruthy();
		});
	});
});
