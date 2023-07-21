/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

(function(w) {
	'use strict';

	var TOPBAR_CLASS = 'speedwell-topbar',
		TRANSLUCENT_CLASS = TOPBAR_CLASS + '--translucent',
		TOGGLE_PREFIX = '.js-toggle-',
		SPEEDWELL_PREFIX = '.speedwell-',
		IS_OPEN = 'is-open',
		IS_BEHIND = 'is-behind';

	var TOGGLES = {
		ACCOUNT: {name: 'account'},
		MAIN_MENU: {name: 'main-menu'},
		SEARCH: {name: 'search'}
	};

	var CONTAINER = window.document.getElementById('speedwell');

	var TOPBAR,
		translucencyIsEnabled = false;

	function hideFiltersButtonOnMenuOpen() {
		Liferay.componentReady('SpeedwellMobileHelpers').then(mobileHelpers => {
			const catalogFiltersButton = mobileHelpers.getFiltersButton();

			if (catalogFiltersButton) {
				catalogFiltersButton.classList.toggle(
					IS_BEHIND,
					!isOpen(catalogFiltersButton)
				);
			}
		});
	}

	function attachListener(currentToggle) {
		const toggleWrapper = TOGGLES[currentToggle].wrapper;

		TOGGLES[currentToggle].buttons.forEach(button => {
			button.addEventListener('click', _e => {
				Liferay.componentReady('SpeedwellCategoryMenu').then(
					categoryMenu => {
						const categoryEl = categoryMenu.getElement();

						button.focus();
						toggleWrapper.classList.toggle(
							IS_OPEN,
							!isOpen(toggleWrapper)
						);
						categoryEl.classList.remove(IS_OPEN);

						if (Liferay.Browser.isMobile) {
							hideFiltersButtonOnMenuOpen();
						}
					}
				);
			});
		});
	}

	function enableToggles() {
		Object.keys(TOGGLES).forEach(attachListener);
	}

	function wipeToggles() {
		Object.keys(TOGGLES).forEach(currentToggle => {
			delete TOGGLES[currentToggle].buttons;
			delete TOGGLES[currentToggle].wrapper;
		});
	}

	function prepareToggles() {
		wipeToggles();

		Object.keys(TOGGLES).forEach(toggle => {
			TOGGLES[toggle].buttons = Array.from(
				TOPBAR.querySelectorAll(TOGGLE_PREFIX + TOGGLES[toggle].name)
			);

			TOGGLES[toggle].wrapper = TOPBAR.querySelector(
				SPEEDWELL_PREFIX + TOGGLES[toggle].name
			);
		});
	}

	function isOpen(toggleElement) {
		return toggleElement.classList.contains(IS_OPEN);
	}

	function toggleTranslucencyOnScroll(scrollThreshold) {
		const isBeyond = w.scrollY <= scrollThreshold;

		if (translucencyIsEnabled) {
			TOPBAR.classList.toggle(TRANSLUCENT_CLASS, isBeyond);
		}
	}

	function isTranslucent() {
		translucencyIsEnabled = TOPBAR.classList.contains(TRANSLUCENT_CLASS);
	}

	function selectElements() {
		TOPBAR = CONTAINER.querySelector('.' + TOPBAR_CLASS);
	}

	selectElements();
	prepareToggles();
	enableToggles();
	isTranslucent();

	if (translucencyIsEnabled) {
		Liferay.componentReady('SpeedwellScrollHandler').then(scrollHandler => {
			scrollHandler.registerCallback(toggleTranslucencyOnScroll);
		});
	}
})(window);
