/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

(function() {
	AUI().ready('liferay-sign-in-modal', A => {
		var signIn = A.one('a.sign-in');

		if (signIn && signIn.getData('redirect') !== 'true') {
			signIn.plug(Liferay.SignInModal);
		}
	});

	var porygonSearch = document.querySelector('.porygon-search');
	var porygonSearchButton = document.querySelector('.porygon-search-button');
	var porygonSearchInput = document.querySelector(
		'.porygon-search .search-portlet-keywords-input'
	);

	if (porygonSearch && porygonSearchButton && porygonSearchInput) {
		porygonSearchButton.addEventListener('click', _event => {
			porygonSearch.classList.toggle('active');
			porygonSearchInput.focus();
		});

		porygonSearch.addEventListener('keydown', event => {
			if (event.keyCode === 27) {
				porygonSearch.classList.remove('active');
			}
		});
	}
})();
