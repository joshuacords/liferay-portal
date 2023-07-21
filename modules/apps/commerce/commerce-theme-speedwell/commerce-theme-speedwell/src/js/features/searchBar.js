/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

(function(w) {
	var searchToggles = w.document.querySelectorAll('.js-toggle-search'),
		HAS_SEARCH_CLASS = 'has-search',
		IS_OPEN_CLASS = 'is-open',
		IS_ACTIVE_CLASS = 'is-active',
		SEARCHBAR_SELECTOR = '.speedwell-search';

	var searchBarElement;

	var searchBar = w.Liferay.component('search-bar');

	if (searchBar) {
		searchBarElement = w.document.querySelector(SEARCHBAR_SELECTOR);

		searchBar.on('toggled', status => {
			searchToggles.forEach(el => {
				el.classList.toggle(IS_ACTIVE_CLASS, status);
			});

			w.document
				.getElementById('speedwell')
				.classList.toggle(HAS_SEARCH_CLASS, status);

			if (searchBarElement) {
				searchBarElement.classList.toggle(IS_OPEN_CLASS, status);
			}
		});
	}
})(window);
