/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

(function(w) {
	'use strict';

	var KEYDOWN_EVENT = 'keydown';
	var TAB_KEYCODE = 9;
	var ACCESSIBILITY_CLASS = 'is-accessible';
	var TIMEOUT = 5000;

	const removeAfter = setTimeout(() => {
		w.removeEventListener(KEYDOWN_EVENT, needsAccessibility);
		clearTimeout(removeAfter);
	}, TIMEOUT);

	function needsAccessibility(e) {
		const isTabbing = e.which === TAB_KEYCODE;

		if (isTabbing) {
			w.document.body.classList.add(ACCESSIBILITY_CLASS);
			w.removeEventListener(KEYDOWN_EVENT, needsAccessibility);
		}
	}

	w.addEventListener(KEYDOWN_EVENT, needsAccessibility);
})(window);
